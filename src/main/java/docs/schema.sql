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
);


CREATE TABLE Readings (
    reading_id BIGSERIAL PRIMARY KEY,
    title VARCHAR(100),
    short_description VARCHAR(255),
    content TEXT,
    level VARCHAR(2)
);

CREATE TABLE Readings_Words (
    word_id BIGSERIAL PRIMARY KEY,
    reading_id BIGINT,
    word VARCHAR(255),
    translation VARCHAR(255),
    definition TEXT,
    FOREIGN KEY (reading_id) REFERENCES Readings(reading_id)
);

CREATE TABLE Audio_Files (
    audio_id BIGSERIAL PRIMARY KEY,
    reading_id BIGINT,
    file_path VARCHAR(255),
    duration TIME,
    format VARCHAR(50),
    FOREIGN KEY (reading_id) REFERENCES Readings(reading_id)
);

CREATE TABLE Readings_Questions (
    question_id BIGSERIAL PRIMARY KEY,
    reading_id BIGINT,
    question_text TEXT,
    answer_marker VARCHAR(255),
    FOREIGN KEY (reading_id) REFERENCES Readings(reading_id) ON DELETE CASCADE
);

CREATE TABLE Readings_Answers (
    answer_id BIGSERIAL PRIMARY KEY,
    question_id BIGINT,
    answer_text TEXT,
    is_correct BOOLEAN,
    FOREIGN KEY (question_id) REFERENCES Readings_Questions(question_id) ON DELETE CASCADE
);

CREATE TABLE Comments (
    comment_id BIGSERIAL PRIMARY KEY,
    body TEXT,
    username TEXT,
    parent_id BIGINT,
    created_at TIMESTAMP,
    section TEXT,
    section_particular_id BIGINT
);

CREATE TABLE Vocabulary (
    word_id BIGSERIAL PRIMARY KEY,
    word TEXT,
    translation TEXT,
    definition TEXT,
    level VARCHAR(2) CHECK (level IN ('A1', 'A2', 'B1', 'B2', 'C1', 'C2')),
    image_location TEXT
);
