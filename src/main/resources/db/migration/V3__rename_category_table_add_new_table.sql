ALTER TABLE categories RENAME TO expense_categories;

CREATE TABLE income_categories (
    id          BIGSERIAL PRIMARY KEY,
    created_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    is_deleted  BOOLEAN NOT NULL DEFAULT FALSE,
    name        VARCHAR(50) NOT NULL,
    user_id     BIGINT NOT NULL,

    CONSTRAINT fk_income_categories_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);