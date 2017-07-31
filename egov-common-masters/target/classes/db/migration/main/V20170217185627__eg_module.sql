CREATE TABLE if not exists eg_module
(
  ids bigint NOT NULL,
  name character varying(100) NOT NULL,
  enabled boolean,
  contextroot character varying(10),
  parentmodule bigint,
  displayname character varying(50),
  ordernumber bigint,
  CONSTRAINT eg_module_pkey PRIMARY KEY (ids),
  CONSTRAINT fk_eg_module_parentmodule FOREIGN KEY (parentmodule)
      REFERENCES eg_module (ids),
  CONSTRAINT eg_module_module_name_key UNIQUE (name)
);

alter table eg_module add tenantId CHARACTER VARYING(250) NOT NULL;