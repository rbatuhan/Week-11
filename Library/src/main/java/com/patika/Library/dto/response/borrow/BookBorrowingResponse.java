package com.patika.Library.dto.response.borrow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowingResponse {
    private int id;
    private String borrowerName;
    private LocalDate borrowingDate;
    private LocalDate returnDate;
}
