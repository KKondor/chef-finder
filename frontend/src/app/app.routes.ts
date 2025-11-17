import { Routes } from '@angular/router';
import {LoginComponent} from './components/login-component/login-component'
import { ChefListComponent } from './components/chef-list-component/chef-list-component';
import { ChefFormComponent } from './components/chef-form-component/chef-form-component';
import {RestaurantListComponent} from './components/restaurant-list-component/restaurant-list-component';
import {RestaurantFormComponent} from './components/restaurant-form-component/restaurant-form-component';
import {AuthGuard} from "./auth.guard"

export const routes: Routes = [
  { path: '', redirectTo: '/chef', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'chef', component: ChefListComponent },
  { path: 'chef/add', component: ChefFormComponent ,canActivate: [AuthGuard] },
  { path: 'chef/:id', component: ChefFormComponent },
  { path: 'restaurant', component: RestaurantListComponent},
  { path: 'restaurant/add', component: RestaurantFormComponent ,canActivate: [AuthGuard]},
  { path: 'restaurant/:id', component: RestaurantFormComponent },
];
