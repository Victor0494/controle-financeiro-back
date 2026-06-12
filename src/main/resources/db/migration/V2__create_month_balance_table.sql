create table tb_month_balance (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    balance_year INTEGER,
    balance_month INTEGER,
    balance DECIMAL(10,2),
    money_input DECIMAL(10,2),
    money_output DECIMAL(10,2)
)