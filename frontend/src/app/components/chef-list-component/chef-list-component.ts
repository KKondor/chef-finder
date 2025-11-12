import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {RouterLink} from '@angular/router';
import {NgClass, NgStyle} from '@angular/common';

interface Restaurant {
  id: number;
  name: string;
  street: string;
  city: string;
  buildingNumber: string;
}

interface Chef {
  compCode: string;
  name: string;
  age: number;
  level: string;
  specialty: string;
  restaurant?: Restaurant;
}

@Component({
  selector: 'app-chef-list',
  standalone: true,
  templateUrl: './chef-list-component.html',
  styleUrls: ['chef-list-component.css'],
  imports: [
    RouterLink,
    NgClass,
    NgStyle,
  ]
})
export class ChefListComponent implements OnInit {
  chefs: Chef[] = [];
  loading = true;
  error: string | null = null;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchChefs();
  }

  fetchChefs(): void {
    this.loading = true;
    this.error = null;

    this.http.get<Chef[]>('http://localhost:8081/api/chef').subscribe({
      next: (data) => {
        this.chefs = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error fetching chefs:', err);
        this.error = 'Failed to load chefs.';
        this.loading = false;
      },
    });
  }

  deleteChef(id: string | undefined) {
    if (!id) return;

    const headers = { Authorization: 'Basic ' + btoa('user:pass') };

    if (!confirm('Are you sure you want to delete this chef?')) return;

    this.http.delete(`http://localhost:8081/api/chef/${id}`, { headers })
      .subscribe({
        next: () => {
          // remove the chef from the list locally
          this.chefs = this.chefs.filter(c => c.compCode !== id);
        },
        error: err => console.error('Failed to delete chef', err)
      });
  }
}
