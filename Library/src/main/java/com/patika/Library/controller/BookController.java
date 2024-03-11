package com.patika.Library.controller;

import com.patika.Library.core.config.modelMapper.IModelMapperService;
import com.patika.Library.core.result.Result;
import com.patika.Library.core.result.ResultData;
import com.patika.Library.core.utilies.ResultHelper;
import com.patika.Library.dto.request.author.AuthorUpdateRequest;
import com.patika.Library.dto.request.book.BookSaveRequest;
import com.patika.Library.dto.response.author.AuthorResponse;
import com.patika.Library.dto.response.book.BookResponse;
import com.patika.Library.dto.response.category.CategoryResponse;
import com.patika.Library.entity.Author;
import com.patika.Library.entity.Book;
import com.patika.Library.entity.Publisher;
import com.patika.Library.service.abstracts.IAuthorService;
import com.patika.Library.service.abstracts.IBookService;
import com.patika.Library.service.abstracts.IPublisherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/v1/books")
public class BookController {
    @Autowired
    private IBookService bookService;
    @Autowired
    private IAuthorService authorService;
    @Autowired
    private IPublisherService publisherService;
    @Autowired
    private IModelMapperService modelMapper;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookResponse> save(@Valid @RequestBody BookSaveRequest bookSaveRequest) {
        Book saveBook = this.modelMapper.forRequest().map(bookSaveRequest, Book.class);

        Author author = this.authorService.get(bookSaveRequest.getAuthorId());
        saveBook.setAuthor(author);

        Publisher publisher = this.publisherService.get(bookSaveRequest.getPublisherId());
        saveBook.setPublisher(publisher);

        this.bookService.save(saveBook);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveBook, BookResponse.class));
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> get(@PathVariable("id") int id){
        Book book = this.bookService.get(id);
        BookResponse bookResponse = this.modelMapper.forResponse().map(book, BookResponse.class);
        return ResultHelper.success(bookResponse);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> update(@Valid @RequestBody AuthorUpdateRequest authorUpdateRequest) {
        Author updateAuthor = this.modelMapper.forRequest().map(authorUpdateRequest, Author.class);
        this.authorService.update(updateAuthor);
        return ResultHelper.created(this.modelMapper.forResponse().map(updateAuthor, AuthorResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.authorService.delete(id);
        return ResultHelper.successResult();
    }
    @GetMapping("/{id}/category")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> getCategory(@PathVariable("id") int id){
        Book book = this.bookService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(book.getCategoryList(), CategoryResponse.class));
    }
}
