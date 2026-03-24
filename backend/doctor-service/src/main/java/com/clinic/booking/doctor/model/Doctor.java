package com.clinic.booking.doctor.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalTime;

@Entity
@Table(name = "doctors")
public class Doctor {
    
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
    
    @Email(message = "Email non valida")
    private String email;
    
    @Pattern(regexp = "^\\+?[0-9]{9,15}$", message = "Il telefono deve contenere tra 9 e 15 cifre")
    private String telefono;
    
    @NotBlank(message = "La specializzazione è obbligatoria")
    @Size(max = 100, message = "La specializzazione non può superare 100 caratteri")
    @Column(nullable = false)
    private String specializzazione;

    @NotBlank(message = "La regione di appartenenza è obbligatoria")
    @Size(max = 50, message = "La regione non può superare 50 caratteri")
    private String regione;

    @NotBlank(message = "Il regime attività è obbligatorio")
    @Size(max = 30, message = "Il regime attività non può superare 30 caratteri")
    private String regimeAttivita;

    @NotBlank(message = "Il tipo struttura è obbligatorio")
    @Size(max = 30, message = "Il tipo struttura non può superare 30 caratteri")
    private String tipoStruttura;
    
    @Column(nullable = false)
    private LocalTime orarioInizio;
    
    @Column(nullable = false)
    private LocalTime orarioFine;
    
    @Column(nullable = false)
    private Integer durataVisita; // in minuti
    
    @Column(nullable = false)
    private Boolean attivo = true;
    
    // Costruttori
    public Doctor() {}
    
    public Doctor(String nome, String cognome, String codiceFiscale, String specializzazione) {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.specializzazione = specializzazione;
        this.regione = "Lombardia";
        this.regimeAttivita = "EXTRA_MOENIA";
        this.tipoStruttura = "CLINICA";
        this.orarioInizio = LocalTime.of(9, 0);
        this.orarioFine = LocalTime.of(18, 0);
        this.durataVisita = 30;
        this.attivo = true;
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
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    
    public String getSpecializzazione() { return specializzazione; }
    public void setSpecializzazione(String specializzazione) { this.specializzazione = specializzazione; }

    public String getRegione() { return regione; }
    public void setRegione(String regione) { this.regione = regione; }

    public String getRegimeAttivita() { return regimeAttivita; }
    public void setRegimeAttivita(String regimeAttivita) { this.regimeAttivita = regimeAttivita; }

    public String getTipoStruttura() { return tipoStruttura; }
    public void setTipoStruttura(String tipoStruttura) { this.tipoStruttura = tipoStruttura; }
    
    public LocalTime getOrarioInizio() { return orarioInizio; }
    public void setOrarioInizio(LocalTime orarioInizio) { this.orarioInizio = orarioInizio; }
    
    public LocalTime getOrarioFine() { return orarioFine; }
    public void setOrarioFine(LocalTime orarioFine) { this.orarioFine = orarioFine; }
    
    public Integer getDurataVisita() { return durataVisita; }
    public void setDurataVisita(Integer durataVisita) { this.durataVisita = durataVisita; }
    
    public Boolean getAttivo() { return attivo; }
    public void setAttivo(Boolean attivo) { this.attivo = attivo; }

}
