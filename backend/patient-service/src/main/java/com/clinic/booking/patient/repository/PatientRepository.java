package com.clinic.booking.patient.repository;

import com.clinic.booking.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    
    Optional<Patient> findByCodiceFiscale(String codiceFiscale);
    
    boolean existsByCodiceFiscale(String codiceFiscale);
    
    @Query("SELECT p FROM Patient p WHERE " +
           "LOWER(p.nome) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(p.cognome) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(p.codiceFiscale) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Patient> findBySearch(@Param("search") String search);
    
    @Query("SELECT p FROM Patient p WHERE " +
           "LOWER(p.citta) = LOWER(:citta)")
    List<Patient> findByCitta(@Param("citta") String citta);
    
    @Query("SELECT p FROM Patient p WHERE " +
           "p.email = :email")
    Optional<Patient> findByEmail(@Param("email") String email);
}
