package main;

import entity.Publication;
import entity.PublicationTopic;
import services.PublicationService;
import services.PublicationTopicService;
import utility.DatabaseUtility;
import utility.PropertiesUtility;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Dashboard {
    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        System.out.println("Hello World");
        PropertiesUtility.init();
        DatabaseUtility.init();
        PublicationTopicService pts = new PublicationTopicService();
        List<PublicationTopic> publications = pts.getAllPublicationTopics();
        for(PublicationTopic p : publications){
            System.out.println(p.toString());
        }
    }
}