package controllers;

import entity.Book;
import entity.WritesBook;
import services.WriteBookService;

import java.util.ArrayList;
import java.util.List;

public class WriteBookController {
    private WriteBookService writeBookService;

    public WriteBookController() {
        this.writeBookService = new WriteBookService();
    }

    public List<Object> getBooksByAuthor(String authorName){
        List<Object> writesBook = writeBookService.getBooksByAuthor(authorName);
        List<Object> books = new ArrayList<>();
        for(Object wb : writesBook){
            books.add(((WritesBook)wb).getBook());
        }
        return books;
    }

    public List<Object> getWritesBookForBook(Book book) {
        return writeBookService.getWritesBookForBook(book);
    }

    public boolean deleteBookAuthorMappings(List<Object> writesBook) {
        for(Object o : writesBook){
            if(!writeBookService.deleteBookAuthorMapping((WritesBook)o)){
                return false;
            }
        }
        return  true;
    }
}
