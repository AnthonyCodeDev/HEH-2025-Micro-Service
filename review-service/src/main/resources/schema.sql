CREATE TABLE IF NOT EXISTS reviews (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    version INT,
    product_id INT NOT NULL,
    review_id INT NOT NULL,
    author VARCHAR(255),
    subject VARCHAR(255),
    content TEXT,
    UNIQUE KEY unique_product_review (product_id, review_id)
);
