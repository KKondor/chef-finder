import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import {FormsModule} from '@angular/forms';

interface Restaurant {
  id?: number;
  name: string;
  street: string;
  city: string;
  buildingNumber: string;
}

@Component({
  selector: 'app-restaurant-form',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: 'restaurant-form-component.html'
})
export class RestaurantFormComponent {
  restaurant: Restaurant = { name: '', street: '', city: '', buildingNumber: '' };
  error: string | null = null;
  isEdit = false;

  constructor(private http: HttpClient, private router: Router, private activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.fetchRestaurant(id);
    }
  }

  fetchRestaurant(id: string) {
    //const headers = { Authorization: 'Basic ' + btoa('user:pass') };
    this.http.get<Restaurant>(`http://localhost:8081/api/restaurant/${id}`).subscribe({
      next: (data) => this.restaurant = data,
      error: (err) => this.error = 'Failed to load restaurant.'
    });
  }

  submit() {
    //const headers = { Authorization: 'Basic ' + btoa('user:pass') };
    if (this.isEdit && this.restaurant.id) {
      this.http.put<Restaurant>(`http://localhost:8081/api/restaurant/${this.restaurant.id}`, this.restaurant)
        .subscribe({
          next: () => this.router.navigate(['/restaurant']),
          error: () => this.error = 'Failed to update restaurant.'
        });
    } else {
      this.http.post<Restaurant>('http://localhost:8081/api/restaurant/add', this.restaurant)
        .subscribe({
          next: () => this.router.navigate(['/restaurant']),
          error: () => this.error = 'Failed to add restaurant.'
        });
    }
  }
}
