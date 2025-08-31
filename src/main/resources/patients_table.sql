CREATE TABLE IF NOT EXISTS patients (
  patient_id   INTEGER PRIMARY KEY,
  first_name   VARCHAR(100) NOT NULL,
  last_name    VARCHAR(100) NOT NULL,
  dob          DATE NOT NULL
);