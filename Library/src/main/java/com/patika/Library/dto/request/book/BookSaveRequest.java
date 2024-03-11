package com.patika.Library.dto.request.book;

import com.patika.Library.entity.BookBorrowing;
import com.patika.Library.entity.Category;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookSaveRequest {
    @NotNull(message = "Kitap adı boş olamaz.")
    private String name;
    private int publicationYear;
    private int stock;
    private long authorId;
    private long publisherId;
    private List<Category> categoryList;
    private List<BookBorrowing> bookBorrowingList;
}
