-- Delete ms_product table if exist
DROP TABLE IF EXIST ms_product;

--create ms_usert table
CREATE TABLE ms_product(
    id SERIAL PRIMARY KEY NOT NULL,
    type VARCHAR(30) NOT NULL,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    ltv numeric(3,2) NOT NULL,
    tenor INT4(3) NOT NULL,
    admin_opening_type VARCHAR(25) NOT NULL,
    admin_opening_cost numeric(17,2) NOT NULL,
    admin_closing_type VARCHAR(25) NOT NULL,
    admin_closing_cost numeric(17,2) NOT NULL,
    saving_service_percent numeric(3,2) NOT NULL,
    saving_service_numeric INT4(3) NOT NULL,
    penalty_bill_percent numeric(3,2) NOT NULL,
    penalty_bill_numeric INT4(3) NOT NULL,

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
SELECT * FROM ms_product;