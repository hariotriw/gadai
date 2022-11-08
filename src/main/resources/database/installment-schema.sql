-- Delete ms_product table if exist
DROP TABLE IF EXISTS tx_installment;

--create ms_usert table
CREATE TABLE tx_installment(
    id SERIAL PRIMARY KEY NOT NULL,
    transaction_number VARCHAR(11) NOT NULL,
    installment_to INT4 NOT NULL,
    base_installment numeric(17,2) NOT NULL,
    saving_service_cost numeric(17,2) NOT NULL,
    late_penalty numeric(17,2) NOT NULL,
    total_installment numeric(17,2) NOT NULL,
    installment_status VARCHAR(20) NOT NULL,
    active_installment_date TIMESTAMP NULL,
    paid_date TIMESTAMP NULL,

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
SELECT * FROM tx_installment;