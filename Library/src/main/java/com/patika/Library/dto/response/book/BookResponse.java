package com.patika.Library.dto.response.book;

import com.patika.Library.entity.BookBorrowing;
import com.patika.Library.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private long id;
    private String name;
    private int publicationYear;
    private int stock;
    private long authorId;
    private long publisherId;
    private List<BookBorrowing> bookBorrowingList;
    private List<Category> categoryList;
}
