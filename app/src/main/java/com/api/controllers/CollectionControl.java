package com.api.controllers;

import com.api.entities.Book;
import com.api.entities.Collection;
import com.api.exceptions.ResourceNotFoundException;
import com.api.repos.BookRepo;
import com.api.repos.CollectionRepo;
import com.api.utils.Utils;
import jakarta.websocket.server.PathParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class CollectionControl {
    private final CollectionRepo collectionRepo;
    private final BookRepo bookRepo;

    public CollectionControl(CollectionRepo collectionRepo, BookRepo bookRepo) {
        this.collectionRepo = collectionRepo;
        this.bookRepo = bookRepo;
    }

    @PostMapping("/collection")
    public Collection newCollection(@RequestBody Collection collection) {
        return this.collectionRepo.save(collection);
    }

    @GetMapping("/collection/list")
    public List<Collection> listCollection(@RequestParam(name="page", defaultValue="0") int page,
                                           @RequestParam(name="limit", defaultValue="1") int limit) {
        List<Collection> result = new ArrayList<>();
        this.collectionRepo.findAll(PageRequest.of(page, limit)).forEach(result::add);

        return result;
    }

    @PostMapping("/collection/{collectionId}/books")
    public ResponseEntity<Collection> addBookToCollection(
            @PathVariable(value = "collectionId") long collectionId,
            @RequestBody Book reqBook){
        Collection collection = this.collectionRepo.findById(collectionId).map(coll -> {
            Book book = this.bookRepo.findById(reqBook.getId()).orElseThrow(
                    ()-> new ResourceNotFoundException(String.format("book %d is not found", reqBook.getId())));
            coll.addBook(book);
            this.collectionRepo.save(coll);

            return coll;
        }).orElseThrow(() -> new ResourceNotFoundException(String.format("collection %d is not found", collectionId)));

        return new ResponseEntity<>(collection, HttpStatus.OK);
    }
}
