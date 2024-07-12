import { Component } from '@angular/core';

@Component({
  selector: 'app-coursel',
  templateUrl: './coursel.component.html',
  styleUrl: './coursel.component.css'
})
export class CourselComponent {
images = [944, 1011, 984].map((n) => `https://picsum.photos/id/${n}/900/500`);
}
