CREATE TABLE Users
(
    user_id     BIGSERIAL PRIMARY KEY,
    first_name  VARCHAR(50) NOT NULL,
    last_name   VARCHAR(50) NOT NULL,
    username   VARCHAR(50) NOT NULL,
    email       VARCHAR(100) NOT NULL,
    password    VARCHAR(255) DEFAULT NULL,
    city     VARCHAR(255) DEFAULT NULL,
    bio         VARCHAR(255) DEFAULT NULL,
    enabled     BOOLEAN DEFAULT FALSE,
    non_locked  BOOLEAN DEFAULT TRUE,
    creation_date DATE DEFAULT CURRENT_DATE,
    role        VARCHAR(255) NOT NULL,
    is_public_account BOOLEAN DEFAULT FALSE,
    image       BYTEA DEFAULT NULL
);

CREATE TABLE token (
                       token_id BIGSERIAL PRIMARY KEY,
                       token VARCHAR(255) UNIQUE NOT NULL,
                       token_type VARCHAR(255) DEFAULT 'BEARER' NOT NULL,
                       revoked BOOLEAN NOT NULL,
                       expired BOOLEAN NOT NULL,
                       user_id BIGINT,
                       FOREIGN KEY (user_id) REFERENCES "users"(user_id) ON DELETE CASCADE
);

CREATE TABLE irregular_verbs (
    verb_id BIGSERIAL PRIMARY KEY,
    base_form VARCHAR(50) UNIQUE NOT NULL,
    past_simple VARCHAR(50) NOT NULL,
    past_participle VARCHAR(50) NOT NULL,
    translation VARCHAR(50) NOT NULL,
    example_sentence VARCHAR(255) NOT NULL,
    level VARCHAR(2) CHECK (level IN ('A1', 'A2', 'B1', 'B2', 'C1', 'C2'))
)
