import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Router, RouterLink} from '@angular/router';

interface Restaurant {
  id: number;
  name: string;
  street: string;
  city: string;
  buildingNumber: string;
}

@Component({
  selector: 'app-restaurant-list',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: 'restaurant-list-component.html'
})
export class RestaurantListComponent {
  restaurants: Restaurant[] = [];
  loading = true;
  error: string | null = null;

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    this.fetchRestaurants();
  }

  fetchRestaurants(): void {
    this.loading = true;
    this.error = null;

    this.http.get<Restaurant[]>('http://localhost:8081/api/restaurant').subscribe({
      next: (data) => {
        this.restaurants = data.sort((a, b) => (a.id ?? 0) - (b.id ?? 0));
        this.loading = false;
      },
      error: (err) => {
        console.error('Error fetching restaurants:', err);
        this.error = 'Failed to load restaurants.';
        this.loading = false;
      }
    });
  }

  deleteRestaurant(id: number): void {
    const headers = { Authorization: 'Basic ' + btoa('user:pass') };
    this.http.delete(`http://localhost:8081/api/restaurant/${id}`, { headers })
      .subscribe({
        next: () => this.fetchRestaurants(),
        error: (err) => this.error = 'Failed to delete restaurant.'
      });
  }
}
