package com.clinic.booking.booking.repository;

import com.clinic.booking.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByPatientId(Long patientId);

    List<Booking> findByDoctorId(Long doctorId);

    @Query("SELECT b FROM Booking b WHERE " +
            "LOWER(b.patientName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(b.doctorName) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Booking> findBySearch(@Param("search") String search);

    boolean existsByDoctorNameAndDateTime(String doctorName, LocalDateTime dateTime);

    boolean existsByDoctorNameAndDateTimeAndIdNot(String doctorName, LocalDateTime dateTime, Long id);
}
