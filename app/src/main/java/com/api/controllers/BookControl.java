package com.api.controllers;

import com.api.entities.Book;
import com.api.repos.BookRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class BookControl {
    private final BookRepo bookRepo;

    public BookControl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @PostMapping("/book")
    public Book newBook(@RequestBody Book newBook) {
        return this.bookRepo.save(newBook);
    }

    @GetMapping("/book/{id}")
    public Optional<Book> getBook(@PathVariable Long id) {
        return this.bookRepo.findById(id);
    }

    @GetMapping("/book/list")
    public List<Book> listBooks(@RequestParam(name="page", defaultValue="1") int page,
                                @RequestParam(name="limit", defaultValue="10") int limit){
        Pageable pagination = PageRequest.of(page, limit);

        List<Book> result = new ArrayList<>();
        this.bookRepo.findAll(pagination).forEach(result::add);

        return result;
    }
}
