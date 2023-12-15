# Smart Waste Management System

## Descrizione
Questo progetto è un sistema di gestione intelligente dei rifiuti che consente di monitorare e gestire i bidoni della spazzatura in una determinata area. L'applicazione offre funzionalità per l'installazione, la rimozione, l'aggiornamento e il monitoraggio dei bidoni della spazzatura.

## Funzionalità
- Installazione di nuovi bidoni con informazioni come ID, posizione geografica e capacità.
- Rimozione di bidoni esistenti.
- Caricamento e scaricamento dei rifiuti da parte degli utenti.
- Monitoraggio dello stato dei bidoni, inclusi livelli di riempimento e livelli di allerta.
- Notifiche di allerta quando un bidone raggiunge un certo livello di riempimento.

## Requisiti di sistema
- Java 8 o versione successiva
- RabbitMQ (per la gestione delle notifiche)
- Librerie Java: Jackson, RabbitMQ Java Client

## Come utilizzare l'applicazione
1. Assicurarsi di soddisfare i requisiti di sistema.
2. Eseguire l'applicazione eseguendo il file `Main.java`.
3. Seguire le istruzioni a schermo per utilizzare le diverse funzionalità dell'applicazione.

## Struttura del progetto
Il progetto è organizzato nelle seguenti directory principali:
- `configurations`: Contiene configurazioni per il sistema di messaggistica RabbitMQ.
- `models`: Contiene le classi dei modelli per i bidoni, le notifiche e altro.
- `security`: Contiene la classe per la verifica dell'utente.
- `services`: Contiene i servizi per la gestione dei dati e la pubblicazione delle notifiche.
- `views`: Contiene le interfacce utente e le viste del terminale.

## Frontend

cityHall: http://ec2-34-192-139-225.compute-1.amazonaws.com/home
wasteAgency: http://ec2-3-227-223-40.compute-1.amazonaws.com/home

## Users
user: PDC8+Se5/Ytkb+D/B+WQBvOwJunct386R433OvRKbC0=
user1: tSoHb05Cv6Thfg2iW/3NCPWsVSr7avxDL20+dEjr6hQ=
user2: 3LyV/lO3nh6XaUH5811e+3lKsxwwA0pHFq7jdlp4JIc=