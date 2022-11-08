DROP TABLE IF EXISTS ms_product;

CREATE TABLE ms_product (
    id VARCHAR(20) PRIMARY KEY NOT NULL,
    type VARCHAR(30) NOT NULL,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255) NULL,
    ltv NUMERIC(3,2) NOT NULL,
    tenor INT4 NOT NULL,
    admin_opening_type VARCHAR(10) NOT NULL,
    admin_opening_cost NUMERIC(17, 2) NOT NULL,
    admin_closing_type VARCHAR(10) NOT NULL,
    admin_closing_cost NUMERIC(17, 2) NOT NULL,
    saving_service_type VARCHAR(10) NOT NULL,
    saving_service_cost NUMERIC(17, 2) NOT NULL,
    penalty_bill_type VARCHAR(10) NOT NULL,
    penalty_bill_cost NUMERIC(17, 2) NOT NULL,
    created_date 	TIMESTAMP NOT NULL,
    creator_id 		INT4 NULL,
    updated_date 	TIMESTAMP NULL,
    updater_id 		INT4 NULL,
    deleted_date 	TIMESTAMP NULL,
    deleter_id 		INT4 NULL,    
)
WITH (
    OIDS=FALSE
);

SELECT * FROM ms_product;