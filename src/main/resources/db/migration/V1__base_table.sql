CREATE SCHEMA test;

CREATE TABLE test.content
(
    id          serial primary key,
    description character varying(255),
    genres      character varying(255),
    image       character varying(255),
    name        character varying(255),
    person_id   bigint NOT NULL
);

CREATE TABLE test.person
(
    id            serial primary key,
    age           bigint,
    email         character varying(255),
    login         character varying(255),
    name          character varying(255),
    pass          character varying(255),
    date_of_birth date
);
