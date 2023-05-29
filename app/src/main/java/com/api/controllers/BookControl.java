package com.api.controllers;

import com.api.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.api.repos.BookRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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

    @GetMapping("/book/list")
    public List<Book> listBooks(@RequestParam(name="page", defaultValue="1") int page,
                                @RequestParam(name="limit", defaultValue="10") int limit){
        Pageable pagination = PageRequest.of(page, limit);

        List<Book> result = new ArrayList<>();
        this.bookRepo.findAll(pagination).forEach(result::add);

        return result;
    }
}
