package controllers;

import services.AuthorService;

import java.util.List;

public class AuthorController {
    private AuthorService authorService;

    public AuthorController() {
        authorService = new AuthorService();
    }

    public List<Object> getAllAuthors(){
        return authorService.getAllAUthors();
    }
}
