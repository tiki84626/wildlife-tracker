--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.4
-- Dumped by pg_dump version 9.5.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: animal_sightings; Type: TABLE; Schema: public; Owner: t1k1
--

CREATE TABLE animal_sightings (
    id integer NOT NULL,
    name character varying,
    description character varying,
    location character varying,
    rangerid integer,
    timeoflastsighting timestamp without time zone,
    type character varying,
    health character varying,
    age character varying
);


ALTER TABLE animal_sightings OWNER TO t1k1;

--
-- Name: animal_sightings_id_seq; Type: SEQUENCE; Schema: public; Owner: t1k1
--

CREATE SEQUENCE animal_sightings_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE animal_sightings_id_seq OWNER TO t1k1;

--
-- Name: animal_sightings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: t1k1
--

ALTER SEQUENCE animal_sightings_id_seq OWNED BY animal_sightings.id;


--
-- Name: rangers; Type: TABLE; Schema: public; Owner: t1k1
--

CREATE TABLE rangers (
    id integer NOT NULL,
    name character varying,
    email character varying,
    rank character varying,
    imgurl character varying
);


ALTER TABLE rangers OWNER TO t1k1;

--
-- Name: rangers_id_seq; Type: SEQUENCE; Schema: public; Owner: t1k1
--

CREATE SEQUENCE rangers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE rangers_id_seq OWNER TO t1k1;

--
-- Name: rangers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: t1k1
--

ALTER SEQUENCE rangers_id_seq OWNED BY rangers.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY animal_sightings ALTER COLUMN id SET DEFAULT nextval('animal_sightings_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY rangers ALTER COLUMN id SET DEFAULT nextval('rangers_id_seq'::regclass);


--
-- Data for Name: animal_sightings; Type: TABLE DATA; Schema: public; Owner: t1k1
--

COPY animal_sightings (id, name, description, location, rangerid, timeoflastsighting, type, health, age) FROM stdin;
\.


--
-- Name: animal_sightings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: t1k1
--

SELECT pg_catalog.setval('animal_sightings_id_seq', 1, false);


--
-- Data for Name: rangers; Type: TABLE DATA; Schema: public; Owner: t1k1
--

COPY rangers (id, name, email, rank, imgurl) FROM stdin;
\.


--
-- Name: rangers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: t1k1
--

SELECT pg_catalog.setval('rangers_id_seq', 1, false);


--
-- Name: animal_sightings_pkey; Type: CONSTRAINT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY animal_sightings
    ADD CONSTRAINT animal_sightings_pkey PRIMARY KEY (id);


--
-- Name: rangers_pkey; Type: CONSTRAINT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY rangers
    ADD CONSTRAINT rangers_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: t1k1
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM t1k1;
GRANT ALL ON SCHEMA public TO t1k1;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

