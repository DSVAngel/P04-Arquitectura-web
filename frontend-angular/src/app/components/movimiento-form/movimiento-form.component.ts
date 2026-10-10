import { Component, OnInit } from '@angular/core';
import { InventarioService } from '../../services/inventario.service';
import { Producto, Usuario } from '../../models/producto.model';
import { Movimiento } from '../../models/movimiento.model';
import { HttpErrorResponse } from '@angular/common/http';

/**
 * RF08: consume la operacion de cambio del flujo principal (POST /api/movimientos)
 * y muestra los errores 4xx que devuelve la API (ej. salida mayor a la existencia).
 */
@Component({
  selector: 'app-movimiento-form',
  templateUrl: './movimiento-form.component.html'
})
export class MovimientoFormComponent implements OnInit {

  productos: Producto[] = [];
  usuarios: Usuario[] = [];
  recientes: Movimiento[] = [];

  idProducto: number | null = null;
  idUsuario: number | null = null;
  tipo: 'ENTRADA' | 'SALIDA' = 'ENTRADA';
  cantidad: number | null = null;
  comentario = '';

  mensaje: string | null = null;
  error: string | null = null;
  enviando = false;

  constructor(private inventarioService: InventarioService) {}

  ngOnInit(): void {
    this.cargarListas();
  }

  private cargarListas(): void {
    this.inventarioService.listarProductos().subscribe({ next: (p) => (this.productos = p) });
    this.inventarioService.listarUsuarios().subscribe({ next: (u) => (this.usuarios = u) });
    this.inventarioService.listarMovimientosRecientes(10).subscribe({ next: (m) => (this.recientes = m) });
  }

  registrar(): void {
    this.mensaje = null;
    this.error = null;

    if (!this.idProducto || !this.idUsuario || !this.cantidad) {
      this.error = 'Completa producto, usuario y cantidad antes de registrar.';
      return;
    }

    this.enviando = true;
    this.inventarioService.registrarMovimiento({
      idProducto: this.idProducto,
      idUsuario: this.idUsuario,
      tipo: this.tipo,
      cantidad: this.cantidad,
      comentario: this.comentario || undefined
    }).subscribe({
      next: () => {
        this.mensaje = 'Movimiento registrado correctamente.';
        this.cantidad = null;
        this.comentario = '';
        this.enviando = false;
        this.cargarListas();
      },
      error: (err: HttpErrorResponse) => {
        // La API devuelve { mensaje: '...' } en los errores 4xx (ver GlobalExceptionHandler).
        this.error = err.error?.mensaje || 'No fue posible registrar el movimiento.';
        this.enviando = false;
      }
    });
  }
}
