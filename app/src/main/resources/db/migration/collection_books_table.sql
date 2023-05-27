CREATE TABLE collection_books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    collection_id INT,
    book_id INT,
    CONSTRAINT fk_collection FOREIGN KEY (collection_id) REFERENCES collection(id),
    CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES book(id)
);
