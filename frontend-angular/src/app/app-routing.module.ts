import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CatalogoComponent } from './components/catalogo/catalogo.component';
import { MovimientoFormComponent } from './components/movimiento-form/movimiento-form.component';

const routes: Routes = [
  { path: '', redirectTo: 'catalogo', pathMatch: 'full' },
  { path: 'catalogo', component: CatalogoComponent },
  { path: 'movimientos', component: MovimientoFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
