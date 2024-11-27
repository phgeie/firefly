import { Component, OnDestroy, OnInit } from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import { interval, Subscription } from 'rxjs';
import { ApiService } from '../services/api.service';import {
  FormGroup,
  FormControl,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';

@Component({
  selector: 'app-start',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
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
  
  subStart: any;
  subStop: any;
  running: boolean = false;
  data = new FormGroup({
    coupling: new FormControl(0.5),
    row: new FormControl(10),
    column: new FormControl(25),
    updateTime: new FormControl(50)
  });

  start(data: any): void {
    this.subStart = this.apiService.start(data.row,data.column,data.coupling,data.updateTime).subscribe(res =>
      this.subscription = interval(data.updateTime).subscribe(() => {
      this.apiService.getFireflies().subscribe(res => {this.fireflies= res;});
    }));
    this.running = true;
  }

  stop(): void {
    this.subStop = this.apiService.stop().subscribe(res => {console.log(res); this.ngOnDestroy();});
    this.running = false;
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
    this.subStart.unsubscribe();
    this.subStop.unsubscribe();
  }

}
