-- Delete ms_product table if exist
DROP TABLE IF EXIST ms_user;

--create ms_usert table
CREATE TABLE ms_user(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(30) NOT NULL,
    phone_number VARCHAR(12) NULL UNIQUE,
    max_limit numeric(17,2) NOT NULL,
    description VARCHAR(50),

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
SELECT * FROM ms_user;