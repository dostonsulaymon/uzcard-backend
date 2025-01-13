-- Create companies table
CREATE TABLE companies
(
    id           VARCHAR(36) PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    address      VARCHAR(255),
    contact      VARCHAR(255),
    role         VARCHAR(50)  NOT NULL,
    created_date TIMESTAMP    NOT NULL,
    visible      BOOLEAN,
    code         VARCHAR(255),
    username     VARCHAR(255),
    password     VARCHAR(255),
    UNIQUE (code, username)
);

-- Create clients table
CREATE TABLE clients
(
    id              VARCHAR(36) PRIMARY KEY,
    name            VARCHAR(50) NOT NULL,
    surname         VARCHAR(50) NOT NULL,
    middle_name     VARCHAR(50),
    phone_number    VARCHAR(15),
    passport_seria  VARCHAR(10) NOT NULL,
    passport_number VARCHAR(10) NOT NULL,
    created_date    TIMESTAMP   NOT NULL,
    status          BOOLEAN     NOT NULL,
    UNIQUE (passport_seria, passport_number)
);

-- Create profiles table
CREATE TABLE profiles
(
    id           VARCHAR(36) PRIMARY KEY,
    name         VARCHAR(255),
    surname      VARCHAR(255),
    role         VARCHAR(50),
    username     VARCHAR(255),
    password     VARCHAR(255),
    status       BOOLEAN,
    created_date TIMESTAMP,
    visible      BOOLEAN
);

-- Create cards table
CREATE TABLE cards
(
    id           VARCHAR(36) PRIMARY KEY,
    number       VARCHAR(255)   NOT NULL UNIQUE,
    expired_date DATE           NOT NULL,
    phone        VARCHAR(255),
    status       VARCHAR(50)    NOT NULL,
    created_date DATE           NOT NULL,
    balance      DECIMAL(19, 2) NOT NULL,
    client_id    VARCHAR(36),
    company_id   VARCHAR(36),
    FOREIGN KEY (client_id) REFERENCES clients (id),
    FOREIGN KEY (company_id) REFERENCES companies (id)
);

-- Create transfers table
CREATE TABLE transfers
(
    id                 VARCHAR(36) PRIMARY KEY,
    from_card_id       VARCHAR(36),
    to_card_id         VARCHAR(36),
    total_amount       DECIMAL(19, 2),
    amount             DECIMAL(19, 2),
    service_amount     DECIMAL(19, 2),
    service_percentage DECIMAL(19, 2),
    created_date       TIMESTAMP,
    status             VARCHAR(50),
    company_id         VARCHAR(36),
    FOREIGN KEY (from_card_id) REFERENCES cards (id),
    FOREIGN KEY (to_card_id) REFERENCES cards (id),
    FOREIGN KEY (company_id) REFERENCES companies (id)
);

-- Create transactions table
CREATE TABLE transactions
(
    id               VARCHAR(36) PRIMARY KEY,
    card_id          VARCHAR(36),
    transfer_id      VARCHAR(36),
    amount           DECIMAL(19, 2) NOT NULL,
    transaction_type VARCHAR(50)    NOT NULL,
    created_date     TIMESTAMP      NOT NULL,
    status           VARCHAR(50)    NOT NULL,
    FOREIGN KEY (card_id) REFERENCES cards (id),
    FOREIGN KEY (transfer_id) REFERENCES transfers (id)
);