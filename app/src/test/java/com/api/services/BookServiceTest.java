package com.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.api.entities.Book;
import com.api.repos.BookRepo;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BookServiceTest {

  @Mock
  private BookRepo bookRepo;
  private Book mockBook;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    this.mockBook = buildMockBook();
  }

  private Book buildMockBook() {
    return Book.builder().id(1L)
        .name("Sorry, I love u")
        .author("kumin")
        .content("It is empty")
        .build();
  }

  @Test
  @DisplayName("book repo test")
  public void testBookRepo_HappyCase() {
    when(bookRepo.save(any())).thenReturn(mockBook);
    when(bookRepo.findById(mockBook.getId())).thenReturn(Optional.of(mockBook));
    var book = bookRepo.save(mockBook);
    assertEquals(1L, book.getId());
    var optionalBook = bookRepo.findById(1L);
    assertTrue(optionalBook.isPresent());
    assertEquals(1L, optionalBook.get().getId());
  }
}
