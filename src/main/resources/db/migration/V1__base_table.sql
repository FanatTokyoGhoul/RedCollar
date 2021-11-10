CREATE TABLE person
(
    id            serial primary key,
    age           bigint,
    email         character varying(255),
    name          character varying(255),
    date_of_birth date
);

CREATE TABLE content
(
    id          serial primary key,
    description character varying(255),
    genres      character varying(255),
    image       character varying(255),
    name        character varying(255),
    id_person   bigint REFERENCES person(id) NOT NULL
);
