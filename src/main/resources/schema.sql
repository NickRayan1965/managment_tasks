--drop schema public cascade;
--create schema public;
CREATE TABLE IF NOT EXISTS public."users" (
  "id" bigserial PRIMARY KEY ,
  "username" VARCHAR(50) NOT NULL UNIQUE,
  "password" VARCHAR(100) NOT NULL,
  "role" varchar(30) NOT NULL DEFAULT 'USER',
  "enabled" BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS public."task_types" (
  "id" bigserial PRIMARY KEY ,
  "name" VARCHAR(50) NOT NULL UNIQUE,
  "enabled" BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS public."tasks" (
  "id" bigserial PRIMARY KEY ,
  "name" VARCHAR(50) NOT NULL,
  "description" TEXT,
  "type_id" bigint NULL,
  "user_id" bigint NOT NULL,
  "enabled" BOOLEAN NOT NULL DEFAULT TRUE,
  FOREIGN KEY ("type_id") REFERENCES task_types("id"),
  FOREIGN KEY ("user_id") REFERENCES users("id")

);

CREATE TABLE IF NOT EXISTS public."subtasks" (
  "id" BIGSERIAL PRIMARY KEY,
  "task_id" BIGINT NOT NULL,
  "name" VARCHAR(50) NOT NULL,
  "is_completed" BOOLEAN NOT NULL DEFAULT TRUE,
  FOREIGN KEY ("task_id") REFERENCES tasks("id")
)