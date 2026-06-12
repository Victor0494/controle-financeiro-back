create table tb_bill (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(200) NOT NULL,
    bill_value DECIMAL(10,2) NOT NULL,
    due_date DATE NOT NULL,
    paid BOOLEAN DEFAULT FALSE
)