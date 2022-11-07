DROP TABLE IF EXISTS ms_customer;

CREATE TABLE ms_customer (
    id              VARCHAR(10) PRIMARY KEY NOT NULL,
    name            VARCHAR(30) NOT NULL,
    id_number       VARCHAR(16) NOT NULL,
	phone_number    VARCHAR(12) NULL,
	gender          VARCHAR(1) NOT NULL,
	business_type   VARCHAR(10) NOT NULL,
	max_limit       NUMERIC(17,2) NOT NULL, 
    created_date    TIMESTAMP NOT NULL,
    creator_id      INT4 NULL,
    updated_date    TIMESTAMP NULL,
    updater_id      INT4 NULL,
    deleted_date    TIMESTAMP NULL,
    deleter_id      INT4 NULL,
    rec_status      VARCHAR(1) NULL DEFAULT 'N'::VARCHAR
)
WITH (
    OIDS=FALSE
);

SELECT * FROM ms_customer;