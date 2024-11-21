import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideHttpClient, withInterceptorsFromDi, withXsrfConfiguration } from "@angular/common/http";

export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes),
    provideHttpClient(
      withInterceptorsFromDi(),
      withXsrfConfiguration({
        cookieName: 'XSRF-TOKEN', // default is 'XSRF-TOKEN'
        headerName: 'X-XSRF-TOKEN', // default is 'X-XSRF-TOKEN'
      })
    ),]
};
