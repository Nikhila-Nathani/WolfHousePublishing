package controllers;

import entity.Publication;
import services.PublicationService;

import java.util.List;

public class PublicationController {
    private PublicationService publicationService;

    public PublicationController(){
        publicationService = new PublicationService();
    }

    public List<Object> getAllPublications(){
        return publicationService.getAllPublications();
    }
}
