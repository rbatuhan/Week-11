package com.patika.Library.dto.request.borrow;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowingSaveRequest {
    @NotNull(message = "Kiralayan kişi adı boş olamaz")
    private String borrowerName;
    private LocalDate borrowingDate;
    private LocalDate returnDate;
    private int bookId;
}
