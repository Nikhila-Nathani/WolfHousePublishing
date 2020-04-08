package services;

import constants.Constants;
import entity.PublicationTopic;
import utility.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PublicationTopicService {

    private static final String GET_ALL_PUBLICATION_TOPICS = "SELECT * FROM PUBLICATION_TOPIC";

    public List<Object> getAllPublicationTopics(){
        List<Object> publicationTopics = new ArrayList<>();
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PUBLICATION_TOPICS);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    Integer id = resultSet.getInt("ID");
                    String topic = resultSet.getString("NAME");
                    publicationTopics.add(new PublicationTopic(id,topic));
                }
            }
        } catch (Exception e) {
            System.out.println(Constants.CONNECTION_ERROR.getMessage());
            return publicationTopics;
        } finally {
            try{
                DatabaseUtility.closeconnection();
            }catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return publicationTopics;
        }
    }



}
