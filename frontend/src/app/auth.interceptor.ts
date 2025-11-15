import { inject } from '@angular/core';
import { HttpInterceptorFn, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';

export const AuthInterceptor: HttpInterceptorFn = (req, next) => {
    const router = inject(Router);
    const token = localStorage.getItem('authHeader');

    let modifiedReq = req;

    if (token) {
        modifiedReq = req.clone({
            setHeaders: { Authorization: token }
        });
    }

    return next(modifiedReq).pipe(
        catchError((error: HttpErrorResponse) => {
            if (error.status === 401) {
                localStorage.removeItem('authHeader');
                router.navigate(['/login'],{
                    queryParams: { reason: 'unauthorized' }
                });
            }

            return throwError(() => error);
        })
    );
};
