-- Delete ms_product table if exist
DROP TABLE IF EXIST tx_fixed_installment;

--create ms_usert table
CREATE TABLE tx_fixed_installment(
    transaction_number VARCHAR(11) PRIMARY KEY NOT NULL,
    customer_id VARCHAR(10) NOT NULL,
    customer_name VARCHAR(30) NOT NULL,
    transfer_date TIMESTAMP NOT NULL,
    product_id INT4 NOT NULL,
    product_name VARCHAR(50) NOT NULL,
    product_desc VARCHAR(255),

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
SELECT * FROM tx_fixed_installment;