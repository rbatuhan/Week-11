package com.patika.Library.service.abstracts;

import com.patika.Library.entity.Book;
import org.springframework.data.domain.Page;

public interface IBookService {
    Book save(Book book);
    Book get(long id);
    Page<Book> cursor(int page, int pageSize);
    Book update(Book book);
    boolean delete(long id);
}
