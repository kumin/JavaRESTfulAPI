CREATE TABLE collection_books (
    collection_id INT,
    book_id INT,
    PRIMARY KEY (collection_id, book_id),
    CONSTRAINT fk_collection FOREIGN KEY (collection_id) REFERENCES collection(id),
    CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES book(id)
);
