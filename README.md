# QAP4 – File I/O + JDBC (PostgreSQL)

## What it does
- Saves **Drug** objects to `drugs.txt` and reads them back (menu 1 & 2).
- Inserts **Patient** objects into PostgreSQL and lists them (menu 3 & 4).

## Run
```bash
export PG_URL=jdbc:postgresql://localhost:5432/qap4
export PG_USER=admin
export PG_PASSWORD=270702
mvn compile
mvn exec:java
