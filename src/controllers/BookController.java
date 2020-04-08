package controllers;

import services.BookService;

import java.util.List;

public class BookController {
    private BookService bookService;

    public BookController(){
        bookService = new BookService();
    }

    public List<Object> getAllBooks(){
        return bookService.getAllBooks();
    }

    public List<Object> getBookByTopic(String topic){
        return bookService.getBookByTopic(topic);
    }

    public List<Object> getBookSByDate(String date){
        return bookService.getBookSByDate(date);
    }

}
