import { Component, signal } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';

@Component({
    selector: 'app-root',
    imports: [RouterOutlet],
    templateUrl: './app.html',
    standalone: true,
    styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('chef-finder');
    isAuthenticated(): boolean {
        return !!localStorage.getItem('authHeader');
    }

    logout() {
        localStorage.removeItem('authHeader');
    }
}
