# clinic-booking-system1


# Clinic Booking System

Sistema gestionale full-stack per cliniche e poliambulatori, con focus su:

- gestione pazienti, medici e prenotazioni
- dashboard operativa e direzionale
- gestione strutture territoriali
- pricing esami, reminder e report esportabili

## Stack Tecnologico

- **Frontend**: React + TypeScript + Material UI
- **Backend**: Spring Boot (microservizi)
- **Gateway**: Spring Cloud Gateway
- **Database**: H2 file-based (persistenza locale)

## Architettura

Servizi backend:

- `api-gateway` -> porta `8080`
- `patient-service` -> porta `8081`
- `doctor-service` -> porta `8082`
- `booking-service` -> porta `8083`

Frontend:

- `frontend` -> porta `3000`

> Nota: il frontend attualmente usa chiamate dirette ai microservizi (`8081/8082/8083`) per semplificare CORS in ambiente locale.

## Funzionalita Principali

### 1) Dashboard Clinica

- KPI principali (pazienti, medici, prenotazioni filtrate)
- KPI temporali (oggi/settimana, completate/cancellate, tasso cancellazione)
- reminder prenotazioni prossime 24 ore
- prossime prenotazioni ordinate per data/ora

### 2) Gestione Pazienti

- CRUD completo
- validazioni su codice fiscale, email, telefono
- anagrafica estesa (indirizzo, regione, citta, CAP, farmaci)

### 3) Gestione Medici

- CRUD completo
- dati professionali:
  - specializzazione
  - regione di appartenenza
  - regime attivita (`INTRA_MOENIA` / `EXTRA_MOENIA`)
  - tipo struttura (`STUDIO` / `CLINICA` / `OSPEDALE`)
- filtri per regione/regime/tipo struttura

### 4) Gestione Prenotazioni

- CRUD con filtri avanzati (stato, data, medico, struttura)
- workflow rapido stato (conferma, completa, annulla)
- blocco doppie prenotazioni (stesso medico + stesso orario)
- blocco prenotazioni nel passato
- export CSV prenotazioni filtrate
- backup JSON + reset sicuro dati demo

### 5) Strutture Sanitarie Territoriali

- sezione dedicata CRUD strutture custom
- salvataggio locale browser (localStorage)
- integrazione con selettori regione/citta/struttura in prenotazioni

### 6) Pricing Esami

- listino configurabile per tipologia esame
- salvataggio locale browser
- integrazione con prenotazioni (prezzo stimato)

### 7) Report Direzionale

- filtro per periodo
- KPI globali
- ricavo stimato da listino
- distribuzione per esame
- top 5 strutture per ricavo
- top 5 medici per ricavo
- top 5 prestazioni per ricavo
- export CSV report

## Avvio in Locale

### Prerequisiti

- Node.js 18+ e npm
- Java 17+
- Maven installato globalmente (`mvn`)

### 1) Avvio Backend (4 terminali)

Avvia i servizi uno per volta:

```bash
cd backend/patient-service
mvn spring-boot:run
```

```bash
cd backend/doctor-service
mvn spring-boot:run
```

```bash
cd backend/booking-service
mvn spring-boot:run
```

```bash
cd backend/api-gateway
mvn spring-boot:run
```

### 2) Avvio Frontend

```bash
cd frontend
npm install
npm start
```

App disponibile su: [http://localhost:3000](http://localhost:3000)

## Dati Demo

Nella sezione Prenotazioni sono disponibili:

- `Genera dati demo`
- `Export backup demo` (JSON)
- `Reset dati demo` (solo record demo marcati)

## Struttura Cartelle

```text
clinic-booking-system/
  backend/
    api-gateway/
    patient-service/
    doctor-service/
    booking-service/
  frontend/
```

## Roadmap

Roadmap operativa disponibile in:

- `ROADMAP_DEMO_VENDIBILE.md`

## Licenza

Uso interno / dimostrativo.
# 🏥 Clinic Booking System

Sistema di prenotazione visite mediche semplice e robusto.

## 🏗️ Architettura

### Backend (3 Microservizi)
- **Patient Service** (8081) - Gestione pazienti
- **Doctor Service** (8082) - Gestione medici e disponibilità  
- **Booking Service** (8083) - Gestione prenotazioni
- **API Gateway** (8080) - Router principale

### Frontend
- **React** + **TypeScript** + **Material-UI**

## 🚀 Avvio Rapido

### Backend
```bash
# 4 terminali separati:
cd backend/patient-service && mvn spring-boot:run
cd backend/doctor-service && mvn spring-boot:run  
cd backend/booking-service && mvn spring-boot:run
cd backend/api-gateway && mvn spring-boot:run
```

### Frontend
```bash
cd frontend && npm start
```

## 🌐 Accesso
- Frontend: http://localhost:3000
- API Gateway: http://localhost:8080
- H2 Console: http://localhost:8081/h2-console

## 📊 Stack
- **Java 17** + **Spring Boot 3.2**
- **H2 Database** in-memory
- **React 18** + **TypeScript**
- **Material-UI** per UI

Al momento è caricato solo lo strato di Backend, il Frontend( React) pur essendo completo e funzionante sarà caricato in seguito
