package com.api.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.api.entities.Book;
import com.api.entities.Collection;
import com.api.repos.CollectionRepo;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CollectionServicesTest {
    @Mock
    private CollectionRepo collectionRepo;
    private Collection mockCollection;
    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        this.mockCollection = buildMockCollection();
    }
    private Collection buildMockCollection(){
        return Collection.builder()
            .id(1).name("love story")
            .description("romantic books")
            .books(List.of(Book.builder().build()))
            .build();
    }

    @Test
    @DisplayName("get collection test")
    public void testCollectionRpo_HappyCase(){
        when(collectionRepo.findById(anyLong())).thenReturn(Optional.of(this.mockCollection));
        var collection = this.collectionRepo.findById(1L);
        assertTrue(collection.isPresent());
        assertEquals(1L, collection.get().getId());
        verify(collectionRepo).findById(anyLong());
    }
}
