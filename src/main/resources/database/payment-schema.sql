-- Delete ms_product table if exist
DROP TABLE IF EXIST tx_payment;

--create ms_usert table
CREATE TABLE tx_payment(
    payment_number VARCHAR(14) PRIMARY KEY NOT NULL,
    transaction_number VARCHAR(11) NOT NULL,
    total_installment_cost numeric(17,2) NOT NULL,
    total_installment_penalty numeric(17,2) NOT NULL,
    admin_closing_cost numeric(17,2) NOT NULL,
    total_bill numeric(17,2) NOT NULL,
    rounding_up numeric(17,2) NOT NULL,
    total_payment numeric(17,2) NOT NULL,
    payment_method VARCHAR(20) NOT NULL,

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
SELECT * FROM tx_payment;