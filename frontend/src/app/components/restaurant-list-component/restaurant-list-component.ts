import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {RouterLink} from '@angular/router';
import {FormsModule} from '@angular/forms'


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
    RouterLink,
    FormsModule
  ],
  templateUrl: 'restaurant-list-component.html'
})
export class RestaurantListComponent {
  restaurants: Restaurant[] = [];
  loading = true;
  error: string | null = null;
  searchQuery: string = '';
  searchTimeout: any = null;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchRestaurants();
  }

    refresh(): void {
        this.searchQuery = '';
        this.fetchRestaurants();
    }

  fetchRestaurants(query: string = ''): void {
    this.loading = true;
    this.error = null;

    const url = query
       ? `http://localhost:8081/api/restaurant/search?q=${encodeURIComponent(query)}`
       : 'http://localhost:8081/api/restaurant';

    this.http.get<Restaurant[]>(url).subscribe({
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

    onSearch() {
        clearTimeout(this.searchTimeout);

        this.searchTimeout = setTimeout(() => {
            this.fetchRestaurants(this.searchQuery);
        }, 300);
    }

  deleteRestaurant(id: number): void {
    //const headers = { Authorization: 'Basic ' + btoa('user:pass') };
    if (!confirm('Are you sure you want to delete this restaurant?')) return;
    this.http.delete(`http://localhost:8081/api/restaurant/${id}`)
      .subscribe({
        next: () => {

            this.restaurants = this.restaurants.filter(c => c.id !== id);
        },
        error: (err) => {
            if (err.status === 400) {
                this.error = 'Restaurant is assigned to a chef and cannot be deleted.';
            } else {
                this.error = 'Failed to delete restaurant.';
            }
        }
      });
  }
}
