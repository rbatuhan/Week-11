package com.patika.Library.controller;

import com.patika.Library.core.config.modelMapper.IModelMapperService;
import com.patika.Library.core.result.Result;
import com.patika.Library.core.result.ResultData;
import com.patika.Library.core.utilies.ResultHelper;
import com.patika.Library.dto.CursorResponse;
import com.patika.Library.dto.request.borrow.BookBorrowingSaveRequest;
import com.patika.Library.dto.request.borrow.BookBorrowingUpdateRequest;
import com.patika.Library.dto.response.borrow.BookBorrowingResponse;
import com.patika.Library.entity.BookBorrowing;
import com.patika.Library.service.abstracts.IBookBorrowingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/borrows")
public class BookBorrowingController {
    @Autowired
    private IBookBorrowingService bookBorrowingService;
    @Autowired
    private IModelMapperService modelMapper;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookBorrowingResponse> save(@Valid @RequestBody BookBorrowingSaveRequest bookBorrowingSaveRequest) {
        BookBorrowing saveBorrow = this.modelMapper.forRequest().map(bookBorrowingSaveRequest, BookBorrowing.class);
        this.bookBorrowingService.save(saveBorrow);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveBorrow, BookBorrowingResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookBorrowingResponse> get(@PathVariable("id") long id) {
        BookBorrowing bookBorrowing = this.bookBorrowingService.get(id);
        BookBorrowingResponse bookBorrowingResponse = this.modelMapper.forResponse().map(bookBorrowing, BookBorrowingResponse.class);
        return ResultHelper.success(bookBorrowingResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BookBorrowingResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "2") int pageSize){

        Page<BookBorrowing> bookBorrowingPage = this.bookBorrowingService.cursor(page,pageSize);
        Page<BookBorrowingResponse> bookBorrowResponsePage = bookBorrowingPage
                .map(bookBorrowing -> this.modelMapper.forResponse().map(bookBorrowing, BookBorrowingResponse.class));
        return ResultHelper.cursor(bookBorrowResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookBorrowingResponse> update(@Valid @RequestBody BookBorrowingUpdateRequest bookBorrowingUpdateRequest){
        this.bookBorrowingService.get(bookBorrowingUpdateRequest.getId());
        BookBorrowing updateBookBorrow = this.modelMapper.forRequest().map(bookBorrowingUpdateRequest, BookBorrowing.class);
        this.bookBorrowingService.update(updateBookBorrow);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateBookBorrow, BookBorrowingResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.bookBorrowingService.delete(id);
        return ResultHelper.successResult();
    }

    @GetMapping("/getAll")
    public List<BookBorrowing> getAll(){
        return bookBorrowingService.getAll();
    }
}
