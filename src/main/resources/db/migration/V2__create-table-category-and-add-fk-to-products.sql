CREATE TABLE categories (
    id UUID PRIMARY KEY,
    name varchar(255) NOT NULL  UNIQUE
);

ALTER TABLE products ADD  COLUMN category_id UUID;

ALTER TABLE products ADD CONSTRAINT fk_products_category
FOREIGN KEY (category_id) REFERENCES  categories(id);

