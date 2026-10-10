import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Categoria, Producto, Usuario } from '../models/producto.model';
import { Movimiento, NuevoMovimientoRequest } from '../models/movimiento.model';

/**
 * RF08: consume la consulta de productos y la operacion de cambio
 * (registrar movimiento) expuestas por api-spring.
 */
@Injectable({ providedIn: 'root' })
export class InventarioService {

  private readonly baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  listarProductos(soloAlerta = false): Observable<Producto[]> {
    return this.http.get<Producto[]>(`${this.baseUrl}/productos`, { params: { alerta: soloAlerta } });
  }

  listarCategorias(): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(`${this.baseUrl}/categorias`);
  }

  listarUsuarios(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(`${this.baseUrl}/usuarios`);
  }

  listarMovimientosRecientes(limite = 10): Observable<Movimiento[]> {
    return this.http.get<Movimiento[]>(`${this.baseUrl}/movimientos`, { params: { limite } });
  }

  registrarMovimiento(request: NuevoMovimientoRequest): Observable<Movimiento> {
    return this.http.post<Movimiento>(`${this.baseUrl}/movimientos`, request);
  }
}
