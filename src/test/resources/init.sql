\c postgres;

DROP DATABASE IF EXISTS soc_kv_test_db;
CREATE DATABASE soc_kv_test_db;
GRANT ALL PRIVILEGES ON DATABASE soc_kv_test_db TO justin;

DROP DATABASE IF EXISTS soc_kv_db;
CREATE DATABASE soc_kv_db;
GRANT ALL PRIVILEGES ON DATABASE soc_kv_db TO justin;

\c soc_kv_db;


CREATE TABLE key_values (
   id serial PRIMARY KEY,
   k TEXT UNIQUE NOT NULL,
   v TEXT NOT NULL,
   created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
   updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

CREATE TABLE key_value_log (
   id serial PRIMARY KEY,
   k TEXT NOT NULL,
   v TEXT NOT NULL,
   logged_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);


\c soc_kv_test_db;

CREATE TABLE key_values (
   id serial PRIMARY KEY,
   k TEXT UNIQUE,
   v TEXT,
   created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
   updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);


CREATE TABLE key_value_log (
   id serial PRIMARY KEY,
   k TEXT NOT NULL,
   v TEXT NOT NULL,
   logged_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);
