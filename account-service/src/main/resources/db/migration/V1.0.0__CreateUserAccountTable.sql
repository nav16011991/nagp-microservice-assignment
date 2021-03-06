CREATE SEQUENCE "SEQ_ID_USER_ACCOUNT" START WITH 1 INCREMENT BY  1;

CREATE TABLE "USER_ACCOUNT"(
    "ID" NUMBER(19,0),
    "ACCOUNT_ID" VARCHAR2(255 CHAR) NOT NULL,
    "USERNAME" VARCHAR2(255 CHAR) NOT NULL UNIQUE,
    "PASSWORD" VARCHAR2(255 CHAR) NOT NULL,
    "FIRST_NAME" VARCHAR2(255 CHAR) NOT NULL,
    "LAST_NAME" VARCHAR2(255 CHAR) NOT NULL,
    "ROLE" VARCHAR2(255 CHAR) NOT NULL,
    PRIMARY KEY ("ID")
);