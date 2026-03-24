package com.clinic.booking.doctor.repository;

import com.clinic.booking.doctor.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    
    Optional<Doctor> findByCodiceFiscale(String codiceFiscale);
    
    boolean existsByCodiceFiscale(String codiceFiscale);
    
    @Query("SELECT d FROM Doctor d WHERE " +
           "LOWER(d.nome) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(d.cognome) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(d.specializzazione) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Doctor> findBySearch(@Param("search") String search);
    
    List<Doctor> findBySpecializzazioneContainingIgnoreCase(String specializzazione);
    
    List<Doctor> findByAttivoTrue();
}
