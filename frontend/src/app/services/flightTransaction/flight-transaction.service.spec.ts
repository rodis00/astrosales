import { TestBed } from '@angular/core/testing';

import { FlightTransactionService } from './flight-transaction.service';

describe('FlightTransactionService', () => {
  let service: FlightTransactionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FlightTransactionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
