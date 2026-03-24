package com.clinic.booking.patient.service;

import com.clinic.booking.patient.model.Patient;
import com.clinic.booking.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    
    @Autowired
    private PatientRepository patientRepository;
    
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
    
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }
    
    public Optional<Patient> getPatientByCodiceFiscale(String codiceFiscale) {
        return patientRepository.findByCodiceFiscale(codiceFiscale);
    }
    
    public List<Patient> searchPatients(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllPatients();
        }
        return patientRepository.findBySearch(searchTerm.trim());
    }
    
    public List<Patient> getPatientsByCity(String city) {
        return patientRepository.findByCitta(city);
    }
    
    public Patient createPatient(Patient patient) {
        // Validazioni business
        if (patientRepository.existsByCodiceFiscale(patient.getCodiceFiscale())) {
            throw new IllegalArgumentException("Codice fiscale già esistente");
        }
        
        if (patient.getEmail() != null && !patient.getEmail().trim().isEmpty()) {
            Optional<Patient> existingByEmail = patientRepository.findByEmail(patient.getEmail());
            if (existingByEmail.isPresent()) {
                throw new IllegalArgumentException("Email già esistente");
            }
        }
        
        return patientRepository.save(patient);
    }
    
    public Patient updatePatient(Long id, Patient patientDetails) {
        Optional<Patient> existingPatient = patientRepository.findById(id);
        if (existingPatient.isEmpty()) {
            throw new IllegalArgumentException("Paziente non trovato");
        }
        
        Patient patient = existingPatient.get();
        
        // Aggiorna i campi
        patient.setNome(patientDetails.getNome());
        patient.setCognome(patientDetails.getCognome());
        patient.setDataNascita(patientDetails.getDataNascita());
        patient.setIndirizzo(patientDetails.getIndirizzo());
        patient.setRegione(patientDetails.getRegione());
        patient.setCitta(patientDetails.getCitta());
        patient.setCap(patientDetails.getCap());
        patient.setFarmaci(patientDetails.getFarmaci());
        patient.setTelefono(patientDetails.getTelefono());
        patient.setEmail(patientDetails.getEmail());
        
        // Se cambia il codice fiscale, verifica che non esista già
        if (!patient.getCodiceFiscale().equals(patientDetails.getCodiceFiscale())) {
            if (patientRepository.existsByCodiceFiscale(patientDetails.getCodiceFiscale())) {
                throw new IllegalArgumentException("Codice fiscale già esistente");
            }
            patient.setCodiceFiscale(patientDetails.getCodiceFiscale());
        }
        
        return patientRepository.save(patient);
    }
    
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new IllegalArgumentException("Paziente non trovato");
        }
        patientRepository.deleteById(id);
    }
    
    public long getTotalPatients() {
        return patientRepository.count();
    }
}
