import { Routes } from '@angular/router';
import { ChefListComponent } from '../../../../chef-finder-fe/src/app/components/chef-list-component/chef-list-component';
import { ChefFormComponent } from '../../../../chef-finder-fe/src/app/components/chef-form-component/chef-form-component';
import {RestaurantListComponent} from '../../../../chef-finder-fe/src/app/components/restaurant-list-component/restaurant-list-component';
import {RestaurantFormComponent} from '../../../../chef-finder-fe/src/app/components/restaurant-form-component/restaurant-form-component';

export const routes: Routes = [
  { path: '', redirectTo: '/chef', pathMatch: 'full' },
  { path: 'chef', component: ChefListComponent },
  { path: 'chef/add', component: ChefFormComponent },
  { path: 'chef/:id', component: ChefFormComponent },
  { path: 'restaurant', component: RestaurantListComponent},
  { path: 'restaurant/add', component: RestaurantFormComponent },
  { path: 'restaurant/:id', component: RestaurantFormComponent },
];
