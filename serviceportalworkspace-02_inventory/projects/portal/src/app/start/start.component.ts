import { Component, OnDestroy, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import { interval, Subscription } from 'rxjs';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-start',
  standalone: true,
  imports: [
  ],
  templateUrl: './start.component.html',
  styleUrl: './start.component.scss'
})
export class StartComponent implements OnDestroy{

  constructor(private router: Router,
    private apiService: ApiService
  ) {
  }
// Initialisiere das Array mit der Größe 10
  private subscription!: Subscription;
  fireflies: any[][] = [];
  phase: number[][] = [[1,1,1,1,1,1,1,1,1,1],
  [1,1,1,1,1,1,1,1,1,1],
  [1,1,1,1,1,1,1,1,1,1],
  [1,1,1,1,1,1,1,1,1,1],
  [1,1,1,1,1,1,1,1,1,1],
  [1,1,1,1,1,1,1,1,1,1],
  [1,1,1,1,1,1,1,1,1,1],
  [1,1,1,1,1,1,1,1,1,1],
  [1,1,1,1,1,1,1,1,1,1],
  [1,1,1,1,1,1,1,1,1,1],];
  subStart: any;
  subStop: any;
  size: number = 10;
  coupling: number = 0.005;
  threadSleepTime: number = 50;

  start(): void {
    this.subStart = this.apiService.start(this.size,this.coupling,this.threadSleepTime).subscribe(res =>
      this.subscription = interval(this.threadSleepTime).subscribe(() => {
      this.apiService.getFireflies().subscribe(res => {this.fireflies= res;});
    }));
    
  }

  stop(): void {
    this.subStop = this.apiService.stop().subscribe(res => {console.log(res); this.ngOnDestroy();});
    
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
    this.subStart.unsubscribe();
    this.subStop.unsubscribe();
  }
}
