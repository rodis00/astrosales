import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  standalone: true,
  name: 'phone',
})
export class PhonePipe implements PipeTransform {
  transform(number: string): string {
    number = '+48' + number;

    const countryCode = number.slice(0, 3);
    const firstSection = number.slice(3, 6);
    const midSection = number.slice(6, 9);
    const lastSection = number.slice(9, 12);

    return `${countryCode} ${firstSection}-${midSection}-${lastSection}`;
  }
}
