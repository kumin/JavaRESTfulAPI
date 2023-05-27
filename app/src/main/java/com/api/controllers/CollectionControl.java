package com.api.controllers;

import com.api.entities.Book;
import com.api.entities.Collection;
import com.api.repos.CollectionRepo;
import com.api.utils.Utils;
import jakarta.websocket.server.PathParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class CollectionControl {
    private final CollectionRepo collectionRepo;

    public CollectionControl(CollectionRepo collectionRepo) {
        this.collectionRepo = collectionRepo;
    }

    @PostMapping("/collection")
    public Collection newCollection(@RequestBody Collection collection) {
        return this.collectionRepo.save(collection);
    }

    @GetMapping("/collection/list")
    public List<Collection> listCollection(@RequestParam(name="page", defaultValue="0") int page,
                                           @RequestParam(name="limit", defaultValue="1") int limit) {
        Page<Collection> pageColl = this.collectionRepo.findAll(PageRequest.of(page, limit));
        List<Collection> result = new ArrayList<>();
        for (Collection col : pageColl) {
           result.add(col);
        }

        return result;
    }

    @PostMapping("/collection/{collectionId}/books")
    public ResponseEntity<Collection> addBookToCollection(
            @PathParam(value = "collectionId") long collectionId,
            @RequestBody Book book){

        return null;
    }
}
