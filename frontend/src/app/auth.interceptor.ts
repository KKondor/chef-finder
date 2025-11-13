import { inject } from '@angular/core';
import { HttpInterceptorFn } from '@angular/common/http';

export const AuthInterceptor: HttpInterceptorFn = (req, next) => {
    const token = localStorage.getItem('authHeader'); // e.g., store "Basic dXNlcjpwYXNz"

    if (token) {
        req = req.clone({
            setHeaders: { Authorization: token }
        });
    }

    return next(req);
};

