CREATE TABLE users (
    id          BIGSERIAL PRIMARY KEY,
    created_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    is_deleted  BOOLEAN NOT NULL DEFAULT FALSE,
    full_name   VARCHAR NOT NULL,
    email       VARCHAR NOT NULL,
    password    VARCHAR NOT NULL
);

CREATE TABLE categories (
    id          BIGSERIAL PRIMARY KEY,
    created_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    is_deleted  BOOLEAN NOT NULL DEFAULT FALSE,
    name        VARCHAR(50) NOT NULL,
    user_id     BIGINT NOT NULL,

    CONSTRAINT fk_categories_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE sources (
    id          BIGSERIAL PRIMARY KEY,
    created_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    is_deleted  BOOLEAN NOT NULL DEFAULT FALSE,
    name        VARCHAR(50) NOT NULL,
    user_id     BIGINT NOT NULL,

    CONSTRAINT fk_sources_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE incomes (
     id          BIGSERIAL PRIMARY KEY,
     created_at  TIMESTAMP NOT NULL DEFAULT NOW(),
     updated_at  TIMESTAMP NOT NULL DEFAULT NOW(),
     is_deleted  BOOLEAN NOT NULL DEFAULT FALSE,
     amount      DECIMAL(19,2) NOT NULL,
     name        VARCHAR NOT NULL,
     income_date DATE NOT NULL,
     user_id     BIGINT NOT NULL,

     CONSTRAINT fk_incomes_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE expenses (
     id          BIGSERIAL PRIMARY KEY,
     created_at  TIMESTAMP NOT NULL DEFAULT NOW(),
     updated_at  TIMESTAMP NOT NULL DEFAULT NOW(),
     is_deleted  BOOLEAN NOT NULL DEFAULT FALSE,
     amount      DECIMAL(19,2) NOT NULL,
     good        VARCHAR NOT NULL,
     description VARCHAR,
     user_id     BIGINT NOT NULL,
     category_id BIGINT,
     source_id   BIGINT,
     expense_date DATE NOT NULL,

     CONSTRAINT fk_expenses_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
     CONSTRAINT fk_expenses_categories FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE SET NULL,
     CONSTRAINT fk_expenses_sources FOREIGN KEY (source_id) REFERENCES sources(id) ON DELETE SET NULL
);

