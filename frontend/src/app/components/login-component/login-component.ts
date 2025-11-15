import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'app-login',
    standalone: true,
    imports: [FormsModule],
    templateUrl: 'login-component.html',
})
export class LoginComponent {
    username = '';
    password = '';
    error: string | null = null;
    loading = false;

    constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) {}

    ngOnInit() {
        this.route.queryParams.subscribe(params => {
            if (params['reason'] === 'unauthorized') {
                this.error = 'Please log in to access this action.';
            }
        });
    }

    login() {
        this.loading = true;
        this.error = null;

        const authHeader = 'Basic ' + btoa(`${this.username}:${this.password}`);

        this.http.get('http://localhost:8081/api/chef', {
            headers: { Authorization: authHeader },
        }).subscribe({
            next: () => {
                localStorage.setItem('authHeader', authHeader);
                this.loading = false;
                this.router.navigate(['/']);
            },
            error: (err) => {
                console.error('Login failed:', err);
                this.error = 'Invalid credentials. Please try again.';
                this.loading = false;
            },
        });
    }
}
