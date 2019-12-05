-- Table: public.accountdetail

-- DROP TABLE public.accountdetail;

CREATE TABLE public.accountdetail
(
  id integer,
  cardnum character varying,
  type character varying,
  change character varying,
  balance double precision,
  "time" date
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.accountdetail
  OWNER TO postgres;


-- Table: public.depositaccount

-- DROP TABLE public.depositaccount;

CREATE TABLE public.depositaccount
(
  id integer,
  name character varying,
  password character varying,
  identitynum character varying,
  cardnum character varying,
  phonenum character varying,
  balance double precision,
  rate double precision,
  interest double precision,
  createtime date,
  count integer,
  begintime date,
  endtime date,
  type integer
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.depositaccount
  OWNER TO postgres;


-- Table: public.loanaccount

-- DROP TABLE public.loanaccount;

CREATE TABLE public.loanaccount
(
  id integer,
  name character varying,
  identitynum character varying,
  cardnum character varying,
  loanlimit double precision,
  credit integer,
  balance double precision,
  rate double precision,
  interest double precision,
  count integer,
  begintime date,
  endtime date
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.loanaccount
  OWNER TO postgres;
