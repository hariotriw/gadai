-- Delete ms_product table if exist
DROP TABLE IF EXIST ms_customer;

--create ms_usert table
CREATE TABLE ms_customer(
    id VARCHAR(10) PRIMARY KEY NOT NULL,
    name VARCHAR(30) NOT NULL,
    id_number VARCHAR(16) UNIQUE NOT NULL,
    phone_number VARCHAR(12) NULL UNIQUE,
    gender VARCHAR(1) NOT NULL,
    business_type VARCHAR(50) NOT NULL,
    max_limit numeric(17,2) NOT NULL,
    created_date TIMESTAMP NULL,
    creator_id INT4 NOT NULL,
    updated_date TIMESTAMP NULL,
    updater_id INT4 NULL,
    deleted_date TIMESTAMP NULL,
    deleter_id INT4 NULL,
    rec_status VARCHAR(1) NULL DEFAULT 'N'::VARCHAR
)
WITH (
    OIDS=FALSE
);

-- Query All data in ms_user table
SELECT * FROM ms_customer;