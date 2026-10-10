export interface Producto {
  idProducto: number;
  codigo: string;
  nombre: string;
  nombreCategoria: string;
  unidadMedida: string;
  minimoPermitido: number;
  existencia: number;
  alertaStockBajo: boolean;
}

export interface Categoria {
  idCategoria: number;
  nombre: string;
  descripcion?: string;
}

export interface Usuario {
  idUsuario: number;
  nombre: string;
  correo: string;
  rol: string;
  activo: boolean;
}
