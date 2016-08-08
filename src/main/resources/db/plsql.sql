CREATE TABLE process1
(
  msg_id integer,
  seq_no integer,
  msg_datetime date,
  msg_status integer,
  CONSTRAINT process1_pk PRIMARY KEY (msg_id)
)
WITH (
  OIDS=FALSE
);




CREATE TABLE process2
(
  msg_id integer,
  seq_no integer,
  msg_datetime date,
  output_batch_id character(20),
  thickness integer,
  width integer,
  msg_status integer,
  CONSTRAINT process2_pk PRIMARY KEY (msg_id)
)
WITH (
  OIDS=FALSE
);


CREATE TABLE process12
(
  msg_id integer,
  seq_no integer,
  msg_datetime date,
  rejection_id character(20),
  input_batch_id character(20),
  rejection_type integer,
  thickness integer,
  width integer,
  msg_status integer,
  CONSTRAINT process12_pk PRIMARY KEY (msg_id)
)
WITH (
  OIDS=FALSE
);