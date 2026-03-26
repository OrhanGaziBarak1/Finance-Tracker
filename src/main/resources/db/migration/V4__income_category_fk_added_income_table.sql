ALTER TABLE incomes ADD COLUMN category_id BIGINT NOT NULL;
ALTER TABLE incomes ADD CONSTRAINT fk_income_categories FOREIGN KEY (category_id) REFERENCES income_categories(id) ON DELETE CASCADE;
