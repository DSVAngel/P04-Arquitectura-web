export interface Movimiento {
  idMovimiento: number;
  idProducto: number;
  nombreProducto: string;
  idUsuario: number;
  nombreUsuario: string;
  tipo: 'ENTRADA' | 'SALIDA';
  cantidad: number;
  fechaHora: string;
  comentario?: string;
  existenciaResultante: number;
}

export interface NuevoMovimientoRequest {
  idProducto: number;
  idUsuario: number;
  tipo: 'ENTRADA' | 'SALIDA';
  cantidad: number;
  comentario?: string;
}
