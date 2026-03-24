package com.clinic.booking.doctor.service;

import com.clinic.booking.doctor.model.Doctor;
import com.clinic.booking.doctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    
    @Autowired
    private DoctorRepository doctorRepository;
    
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
    
    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }
    
    public List<Doctor> searchDoctors(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllDoctors();
        }
        return doctorRepository.findBySearch(searchTerm.trim());
    }
    
    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.findBySpecializzazioneContainingIgnoreCase(specialization);
    }
    
    public List<Doctor> getActiveDoctors() {
        return doctorRepository.findByAttivoTrue();
    }
    
    public Doctor createDoctor(Doctor doctor) {
        if (doctorRepository.existsByCodiceFiscale(doctor.getCodiceFiscale())) {
            throw new IllegalArgumentException("Codice fiscale già esistente");
        }
        if (doctor.getRegione() == null || doctor.getRegione().trim().isEmpty()) {
            doctor.setRegione("Lombardia");
        }
        if (doctor.getRegimeAttivita() == null || doctor.getRegimeAttivita().trim().isEmpty()) {
            doctor.setRegimeAttivita("EXTRA_MOENIA");
        }
        if (doctor.getTipoStruttura() == null || doctor.getTipoStruttura().trim().isEmpty()) {
            doctor.setTipoStruttura("CLINICA");
        }
        if (doctor.getOrarioInizio() == null) {
            doctor.setOrarioInizio(LocalTime.of(9, 0));
        }
        if (doctor.getOrarioFine() == null) {
            doctor.setOrarioFine(LocalTime.of(18, 0));
        }
        if (doctor.getDurataVisita() == null) {
            doctor.setDurataVisita(30);
        }
        if (doctor.getAttivo() == null) {
            doctor.setAttivo(true);
        }
        return doctorRepository.save(doctor);
    }
    
    public Doctor updateDoctor(Long id, Doctor doctorDetails) {
        Optional<Doctor> existingDoctor = doctorRepository.findById(id);
        if (existingDoctor.isEmpty()) {
            throw new IllegalArgumentException("Medico non trovato");
        }
        
        Doctor doctor = existingDoctor.get();
        doctor.setNome(doctorDetails.getNome());
        doctor.setCognome(doctorDetails.getCognome());
        doctor.setSpecializzazione(doctorDetails.getSpecializzazione());
        doctor.setRegione(
                doctorDetails.getRegione() == null || doctorDetails.getRegione().trim().isEmpty()
                        ? "Lombardia"
                        : doctorDetails.getRegione()
        );
        doctor.setEmail(doctorDetails.getEmail());
        doctor.setTelefono(doctorDetails.getTelefono());
        doctor.setRegimeAttivita(
                doctorDetails.getRegimeAttivita() == null || doctorDetails.getRegimeAttivita().trim().isEmpty()
                        ? "EXTRA_MOENIA"
                        : doctorDetails.getRegimeAttivita()
        );
        doctor.setTipoStruttura(
                doctorDetails.getTipoStruttura() == null || doctorDetails.getTipoStruttura().trim().isEmpty()
                        ? "CLINICA"
                        : doctorDetails.getTipoStruttura()
        );
        doctor.setOrarioInizio(doctorDetails.getOrarioInizio());
        doctor.setOrarioFine(doctorDetails.getOrarioFine());
        doctor.setDurataVisita(doctorDetails.getDurataVisita());
        doctor.setAttivo(doctorDetails.getAttivo());
        
        if (!doctor.getCodiceFiscale().equals(doctorDetails.getCodiceFiscale())) {
            if (doctorRepository.existsByCodiceFiscale(doctorDetails.getCodiceFiscale())) {
                throw new IllegalArgumentException("Codice fiscale già esistente");
            }
            doctor.setCodiceFiscale(doctorDetails.getCodiceFiscale());
        }
        
        return doctorRepository.save(doctor);
    }
    
    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new IllegalArgumentException("Medico non trovato");
        }
        doctorRepository.deleteById(id);
    }
}
