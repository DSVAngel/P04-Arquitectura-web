import { Component, OnInit } from '@angular/core';
import { InventarioService } from '../../services/inventario.service';
import { Producto } from '../../models/producto.model';

@Component({
  selector: 'app-catalogo',
  templateUrl: './catalogo.component.html'
})
export class CatalogoComponent implements OnInit {

  productos: Producto[] = [];
  soloAlerta = false;
  cargando = false;
  error: string | null = null;

  constructor(private inventarioService: InventarioService) {}

  ngOnInit(): void {
    this.cargar();
  }

  cargar(): void {
    this.cargando = true;
    this.error = null;
    this.inventarioService.listarProductos(this.soloAlerta).subscribe({
      next: (productos) => {
        this.productos = productos;
        this.cargando = false;
      },
      error: () => {
        this.error = 'No fue posible consultar el catalogo. Verifica que el API este disponible.';
        this.cargando = false;
      }
    });
  }

  alternarFiltro(): void {
    this.soloAlerta = !this.soloAlerta;
    this.cargar();
  }
}
