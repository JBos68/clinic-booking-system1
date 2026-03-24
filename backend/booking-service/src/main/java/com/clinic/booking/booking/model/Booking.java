package com.clinic.booking.booking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long patientId;
    
    private Long doctorId;
    
    @NotBlank(message = "Il nome del paziente è obbligatorio")
    private String patientName;
    
    @NotBlank(message = "Il nome del medico è obbligatorio")
    private String doctorName;

    @NotBlank(message = "Il tipo di esame è obbligatorio")
    private String examType;

    @NotBlank(message = "La struttura è obbligatoria")
    private String facilityName;
    
    @NotNull(message = "La data e ora sono obbligatorie")
    private LocalDateTime dateTime;
    
    @NotNull(message = "Lo stato è obbligatorio")
    @Enumerated(EnumType.STRING)
    private BookingStatus status = BookingStatus.IN_ATTESA;
    
    private String notes;
    
    private LocalDateTime bookingDate;
    
    public enum BookingStatus {
        IN_ATTESA, CONFERMATA, CANCELLATA, COMPLETATA
    }

    @PrePersist
    public void prePersist() {
        if (bookingDate == null) {
            bookingDate = LocalDateTime.now();
        }
        if (status == null) {
            status = BookingStatus.IN_ATTESA;
        }
    }
    
    // Costruttori
    public Booking() {}
    
    public Booking(Long patientId, Long doctorId, String patientName, String doctorName, LocalDateTime dateTime) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.examType = "Visita Specialistica";
        this.facilityName = "Struttura Principale";
        this.dateTime = dateTime;
        this.bookingDate = LocalDateTime.now();
        this.status = BookingStatus.IN_ATTESA;
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    
    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }
    
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    
    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }

    public String getExamType() { return examType; }
    public void setExamType(String examType) { this.examType = examType; }

    public String getFacilityName() { return facilityName; }
    public void setFacilityName(String facilityName) { this.facilityName = facilityName; }
    
    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    
    public BookingStatus getStatus() { return status; }
    public void setStatus(BookingStatus status) { this.status = status; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    public LocalDateTime getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDateTime bookingDate) { this.bookingDate = bookingDate; }
}
