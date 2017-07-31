ALTER TABLE EG_DEPARTMENT
ADD COLUMN CREATEDBY BIGINT NOT NULL DEFAULT (1), 
ADD COLUMN CREATEDDATE TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN LASTMODIFIEDBY BIGINT NOT NULL DEFAULT (1),
ADD COLUMN LASTMODIFIEDDATE TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE EG_DEPARTMENT ALTER COLUMN CREATEDBY DROP DEFAULT;
ALTER TABLE EG_DEPARTMENT ALTER COLUMN CREATEDDATE DROP DEFAULT;
ALTER TABLE EG_DEPARTMENT ALTER COLUMN LASTMODIFIEDBY DROP DEFAULT;
ALTER TABLE EG_DEPARTMENT ALTER COLUMN LASTMODIFIEDDATE DROP DEFAULT;
