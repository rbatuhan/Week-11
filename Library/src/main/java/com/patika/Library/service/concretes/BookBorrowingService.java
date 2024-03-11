package com.patika.Library.service.concretes;

import com.patika.Library.core.exception.NotFoundException;
import com.patika.Library.core.utilies.Message;
import com.patika.Library.entity.BookBorrowing;
import com.patika.Library.repository.BookBorrowingRepository;
import com.patika.Library.service.abstracts.IBookBorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookBorrowingService implements IBookBorrowingService {

    @Autowired
    private BookBorrowingRepository bookBorrowingRepository;

    @Override
    public BookBorrowing save(BookBorrowing bookBorrowing) {
        return this.bookBorrowingRepository.save(bookBorrowing);
    }

    @Override
    public BookBorrowing get(long id) {
        return this.bookBorrowingRepository.findById(id).orElseThrow(()-> new NotFoundException(Message.NOT_FOUND));
    }

    @Override
    public Page<BookBorrowing> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.bookBorrowingRepository.findAll(pageable);
    }

    @Override
    public BookBorrowing update(BookBorrowing bookBorrowing) {
        this.get(bookBorrowing.getId());
        return this.bookBorrowingRepository.save(bookBorrowing);    }

    @Override
    public boolean delete(long id) {
        BookBorrowing bookBorrowing = this.get(id);
        this.bookBorrowingRepository.delete(bookBorrowing);
        return true;
    }

    @Override
    public List<BookBorrowing> getAll() {
        return this.bookBorrowingRepository.findAll();
    }
}
