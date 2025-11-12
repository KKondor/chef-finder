import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

interface Restaurant {
  id: number;
  name: string;
  street: string;
  city: string;
  buildingNumber: string;
}

interface Chef {
  compCode?: string;
  name: string;
  age: number;
  level: string;
  specialty: string;
  restaurant?: Restaurant;
}

@Component({
  selector: 'app-chef-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './chef-form-component.html'
})
export class ChefFormComponent {
  restaurants: Restaurant[] = [];
  selectedRestaurantId: number | null = null;
  chef: Chef = { name: '', age: 18, level: 'JUNIOR', specialty: '' };
  error: string | null = null;
  isEdit = false;

  constructor(
    private http: HttpClient,
    private router: Router,
    private activatedRoute: ActivatedRoute,) {}

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    this.fetchRestaurants();
    if (id) {
      this.isEdit = true;
      this.fetchChef(id);
    }
  }

  fetchRestaurants() {
    this.http.get<Restaurant[]>('http://localhost:8081/api/restaurant').subscribe({
      next: (data) => (
        this.restaurants = data.sort((a,b) => a.name.localeCompare(b.name))),
      error: (err) => {
        console.error('Error fetching restaurants:', err);
        this.error = 'Failed to load restaurants.';
      },
    });
  }

  fetchChef(id: string) {
    const headers = { Authorization: 'Basic ' + btoa('user:pass') };
    this.http.get<Chef>(`http://localhost:8081/api/chef/${id}`,{headers}).subscribe({
      next: data => {
        this.chef = data;
        if (this.chef.restaurant) {
          this.selectedRestaurantId = this.chef.restaurant.id;
        }
      },
      error: err => this.error = 'Failed to load chef.'
    });
  }

  submit() {
    const headers = { Authorization: 'Basic ' + btoa('user:pass') };
    const restaurant = this.restaurants.find(r => r.id === this.selectedRestaurantId);

    if (restaurant) {
      this.chef.restaurant = restaurant;
    }

    if (this.isEdit && this.chef.compCode) {
      // Edit existing chef
      this.http.put<Chef>(
        `http://localhost:8081/api/chef/${this.chef.compCode}`,
        this.chef,
        { headers }
      ).subscribe({
        next: () => this.router.navigate(['/chef']),
        error: err => this.error = 'Failed to update chef.'
      });
    } else {
      // Add new chef
      this.http.post<Chef>('http://localhost:8081/api/chef', this.chef, { headers })
        .subscribe({
          next: () => this.router.navigate(['/chef']),
          error: err => this.error = 'Failed to add chef.'
        });
    }
  }
}
