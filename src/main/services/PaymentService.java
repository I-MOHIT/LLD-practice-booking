package main.services;


import main.exceptions.BookingCancelledException;
import main.exceptions.BookingNotFoundException;
import main.exceptions.IncorrectBookingStatusException;
import main.models.Booking;
import main.models.BookingStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PaymentService {
    BookingService bookingService;
    private Map<UUID, Integer> bookingPaymentTriesMap;

    public PaymentService(BookingService bookingService) {
        this.bookingService = bookingService;
        this.bookingPaymentTriesMap = new HashMap<>();
    }

    public boolean paymentFailed(UUID bookingId) {
        if (!this.bookingService.getUuidBookingMap().containsKey(bookingId)) {
            throw new BookingNotFoundException();
        }
        Booking booking = this.bookingService.getUuidBookingMap().get(bookingId);
        if (!booking.getBookingStatus().equals(BookingStatus.CREATED)) {
            throw new IncorrectBookingStatusException();
        }
        if (new Date().getTime() - booking.getBookingTime() > 100 * 1000) {
            throw new BookingCancelledException();
        }
        if (!this.bookingPaymentTriesMap.containsKey(bookingId)) {
            bookingPaymentTriesMap.put(bookingId, 0);
        }
        int bookingTries = bookingPaymentTriesMap.get(bookingId);
        bookingTries++;
        bookingPaymentTriesMap.put(bookingId, bookingTries);
        // If the number of tries has been reached, cancel the booking
        if (bookingTries == 3) {
            this.bookingService.cancelBooking(bookingId);
            return false;
        }
        return true;
    }

    public Map<UUID, Integer> getBookingPaymentTriesMap() {
        return bookingPaymentTriesMap;
    }

    public void setBookingPaymentTriesMap(Map<UUID, Integer> bookingPaymentTriesMap) {
        this.bookingPaymentTriesMap = bookingPaymentTriesMap;
    }
}
