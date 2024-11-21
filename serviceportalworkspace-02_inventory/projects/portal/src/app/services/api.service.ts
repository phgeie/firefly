import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export const BASE_URI: string = 'http://localhost:8080/data';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  constructor(private httpClient: HttpClient) {}

  start(size: number, coupling: number, threadSleepTime: number) {
    return this.httpClient.get(BASE_URI + '/start?size='+size+'&coupling='+coupling+'&threadSleepTime='+threadSleepTime);
  }

  stop() {
    return this.httpClient.get(BASE_URI + '/stop');
  }

  getFireflies(): Observable<any> {
    return this.httpClient.get(BASE_URI + '/getFireflies');
  }

}
