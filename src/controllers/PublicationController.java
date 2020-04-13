package controllers;

import entity.Publication;
import entity.PublicationTopic;
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

//    public boolean createPublication(Publication publication){
//        return publicationService.createPublicationAndGetId(publication);
//    }


    public boolean updatePublication(Publication publication){
        return publicationService.updatePublication(publication);
    }

    public Publication getPublicationById(Integer publicationId){
        return publicationService.getPublicationById(publicationId);
    }

    public Publication getPublicationByTitle(String title){
        return publicationService.getPublicationByTitle(title);
    }

    public List<Object> getPublicationByDate(String date){
        return publicationService.getPublicationByDate(date);
    }

    public List<Object> getPublicationByTopic(PublicationTopic publicationTopic){
        return publicationService.getPublicationByTopic(publicationTopic);
    }

    public int createPublicationAndGetId(Publication publication){
        return publicationService.createPublicationAndGetId(publication);
    }

    public boolean deletePublication(Publication publication) {
        return publicationService.deletePublication(publication);
    }
}
