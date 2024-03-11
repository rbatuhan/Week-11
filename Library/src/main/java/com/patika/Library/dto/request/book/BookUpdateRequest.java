package com.patika.Library.dto.request.book;

import com.patika.Library.entity.Category;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateRequest {
    @Positive(message = "ID Değeri pozitif olmalıdır")
    private int id;
    private String name;
    private int publicationYear;
    private int stock;
    private int publisherId;
    private int authorId;
    private List<Category> categoryList;
}
