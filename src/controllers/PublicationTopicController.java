package controllers;

import services.PublicationTopicService;

import java.util.List;

public class PublicationTopicController {
    private PublicationTopicService publicationTopicService;
    public PublicationTopicController(){
        publicationTopicService = new PublicationTopicService();
    }

    public List<Object> getAllPublicationTopics(){
        return publicationTopicService.getAllPublicationTopics();
    }
}
