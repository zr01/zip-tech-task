CREATE TABLE users (
  id BIGSERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  email_address TEXT UNIQUE NOT NULL,
  monthly_salary NUMERIC NOT NULL,
  monthly_expenses NUMERIC NOT NULL,
  created_on TIMESTAMP WITH TIME ZONE NOT NULL,
  modified_by BIGINT NULL,
  modified_on TIMESTAMP WITH TIME ZONE NULL
);

CREATE TABLE accounts (
  id BIGSERIAL PRIMARY KEY,
  user_id BIGINT NOT NULL REFERENCES users (id),
  credit_limit NUMERIC NOT NULL,
  debit_record NUMERIC NOT NULL DEFAULT 0.0,
  created_by BIGINT NOT NULL,
  created_on TIMESTAMP WITH TIME ZONE NOT NULL,
  modified_by BIGINT NULL,
  modified_on TIMESTAMP WITH TIME ZONE NULL
);