drop schema public cascade;
create schema public;
CREATE TABLE IF NOT EXISTS public."users" (
  "id" serial PRIMARY KEY ,
  "username" VARCHAR(50) NOT NULL UNIQUE,
  "password" VARCHAR(100) NOT NULL,
  "role" varchar(30) NOT NULL DEFAULT 'USER',
  "enabled" BOOLEAN NOT NULL DEFAULT TRUE
);