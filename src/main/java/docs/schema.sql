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
    role        VARCHAR(255) NOT NULL
    --IMAGE ? - Jeśli chcesz przechowywać obraz jako bajty, możesz użyć typu BYTEA. Jeśli chcesz przechowywać ścieżkę do obrazu, użyj VARCHAR.
    --image       BYTEA DEFAULT NULL,
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
