package com.clinic.booking.booking.service;

import com.clinic.booking.booking.model.Booking;
import com.clinic.booking.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> getBookingsByPatient(Long patientId) {
        return bookingRepository.findByPatientId(patientId);
    }

    public List<Booking> getBookingsByDoctor(Long doctorId) {
        return bookingRepository.findByDoctorId(doctorId);
    }

    public List<Booking> searchBookings(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllBookings();
        }
        return bookingRepository.findBySearch(searchTerm.trim());
    }

    public Booking createBooking(Booking booking) {
        validateBookingDateTime(booking.getDateTime());
        validateBookingSlotAvailability(booking.getDoctorName(), booking.getDateTime(), null);

        if (booking.getBookingDate() == null) {
            booking.setBookingDate(LocalDateTime.now());
        }
        if (booking.getStatus() == null) {
            booking.setStatus(Booking.BookingStatus.IN_ATTESA);
        }
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Long id, Booking bookingDetails) {
        Optional<Booking> existingBooking = bookingRepository.findById(id);
        if (existingBooking.isEmpty()) {
            throw new IllegalArgumentException("Prenotazione non trovata");
        }

        validateBookingDateTime(bookingDetails.getDateTime());
        validateBookingSlotAvailability(bookingDetails.getDoctorName(), bookingDetails.getDateTime(), id);

        Booking booking = existingBooking.get();
        booking.setPatientId(bookingDetails.getPatientId());
        booking.setDoctorId(bookingDetails.getDoctorId());
        booking.setPatientName(bookingDetails.getPatientName());
        booking.setDoctorName(bookingDetails.getDoctorName());
        booking.setExamType(bookingDetails.getExamType());
        booking.setFacilityName(bookingDetails.getFacilityName());
        booking.setDateTime(bookingDetails.getDateTime());
        booking.setStatus(bookingDetails.getStatus());
        booking.setNotes(bookingDetails.getNotes());

        return bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new IllegalArgumentException("Prenotazione non trovata");
        }
        bookingRepository.deleteById(id);
    }

    public Booking confirmBooking(Long id) {
        Optional<Booking> existingBooking = bookingRepository.findById(id);
        if (existingBooking.isEmpty()) {
            throw new IllegalArgumentException("Prenotazione non trovata");
        }
        Booking booking = existingBooking.get();
        booking.setStatus(Booking.BookingStatus.CONFERMATA);
        return bookingRepository.save(booking);
    }

    public Booking cancelBooking(Long id) {
        Optional<Booking> existingBooking = bookingRepository.findById(id);
        if (existingBooking.isEmpty()) {
            throw new IllegalArgumentException("Prenotazione non trovata");
        }
        Booking booking = existingBooking.get();
        booking.setStatus(Booking.BookingStatus.CANCELLATA);
        return bookingRepository.save(booking);
    }

    public Booking completeBooking(Long id) {
        Optional<Booking> existingBooking = bookingRepository.findById(id);
        if (existingBooking.isEmpty()) {
            throw new IllegalArgumentException("Prenotazione non trovata");
        }
        Booking booking = existingBooking.get();
        booking.setStatus(Booking.BookingStatus.COMPLETATA);
        return bookingRepository.save(booking);
    }

    private void validateBookingSlotAvailability(String doctorName, LocalDateTime dateTime, Long bookingIdToExclude) {
        if (doctorName == null || doctorName.trim().isEmpty() || dateTime == null) {
            return;
        }

        String normalizedDoctorName = doctorName.trim();
        boolean hasConflict = bookingIdToExclude == null
                ? bookingRepository.existsByDoctorNameAndDateTime(normalizedDoctorName, dateTime)
                : bookingRepository.existsByDoctorNameAndDateTimeAndIdNot(normalizedDoctorName, dateTime, bookingIdToExclude);

        if (hasConflict) {
            throw new IllegalArgumentException("Conflitto agenda: medico gia prenotato in questo orario");
        }
    }

    private void validateBookingDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return;
        }

        if (dateTime.isBefore(LocalDateTime.now().minusMinutes(1))) {
            throw new IllegalArgumentException("Data/ora non valida: la prenotazione non puo essere nel passato");
        }
    }
}
