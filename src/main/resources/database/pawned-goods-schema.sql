-- Delete ms_product table if exist
DROP TABLE IF EXISTS tx_pawned_goods;

--create ms_usert table
CREATE TABLE tx_pawned_goods(
    id SERIAL PRIMARY KEY NOT NULL,
    transaction_number VARCHAR(11) NOT NULL,
    goods_name VARCHAR(30) NOT NULL,
    description VARCHAR(150) NOT NULL,
    amount numeric(3) NOT NULL,
    price_per_unit numeric(10,2) NOT NULL,

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
SELECT * FROM tx_pawned_goods;