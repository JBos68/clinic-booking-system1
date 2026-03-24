package com.clinic.booking.patient.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "patients")
public class Patient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Il nome è obbligatorio")
    @Size(max = 50, message = "Il nome non può superare 50 caratteri")
    @Column(nullable = false)
    private String nome;
    
    @NotBlank(message = "Il cognome è obbligatorio")
    @Size(max = 50, message = "Il cognome non può superare 50 caratteri")
    @Column(nullable = false)
    private String cognome;
    
    @NotBlank(message = "Il codice fiscale è obbligatorio")
    @Pattern(regexp = "[A-Z]{6}[0-9LMNPQRSTUV]{2}[ABCDEHLMPRST]{1}[0-9LMNPQRSTUV]{2}[A-Z]{1}[0-9LMNPQRSTUV]{3}[A-Z]{1}", 
             message = "Codice fiscale non valido")
    @Column(unique = true, nullable = false, length = 16)
    private String codiceFiscale;
    
    @Column(nullable = false)
    private LocalDate dataNascita;
    
    @Size(max = 200, message = "L'indirizzo non può superare 200 caratteri")
    private String indirizzo;

    @Size(max = 50, message = "La regione non può superare 50 caratteri")
    private String regione;
    
    @Size(max = 50, message = "La città non può superare 50 caratteri")
    private String citta;
    
    @Size(max = 5, message = "Il CAP non può superare 5 caratteri")
    private String cap;

    @Size(max = 500, message = "I farmaci non possono superare 500 caratteri")
    private String farmaci;
    
    @Pattern(regexp = "^\\+?[0-9]{9,15}$", message = "Il telefono deve contenere tra 9 e 15 cifre")
    private String telefono;
    
    @Email(message = "Email non valida")
    private String email;
    
    // Costruttori
    public Patient() {}
    
    public Patient(String nome, String cognome, String codiceFiscale, LocalDate dataNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.dataNascita = dataNascita;
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }
    
    public String getCodiceFiscale() { return codiceFiscale; }
    public void setCodiceFiscale(String codiceFiscale) { this.codiceFiscale = codiceFiscale; }
    
    public LocalDate getDataNascita() { return dataNascita; }
    public void setDataNascita(LocalDate dataNascita) { this.dataNascita = dataNascita; }
    
    public String getIndirizzo() { return indirizzo; }
    public void setIndirizzo(String indirizzo) { this.indirizzo = indirizzo; }

    public String getRegione() { return regione; }
    public void setRegione(String regione) { this.regione = regione; }
    
    public String getCitta() { return citta; }
    public void setCitta(String citta) { this.citta = citta; }
    
    public String getCap() { return cap; }
    public void setCap(String cap) { this.cap = cap; }

    public String getFarmaci() { return farmaci; }
    public void setFarmaci(String farmaci) { this.farmaci = farmaci; }
    
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
