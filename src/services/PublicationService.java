package services;

import constants.Constants;
import entity.Publication;
import entity.PublicationTopic;
import utility.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PublicationService {

    private static final String GET_ALL_PUBLICATIONS =
            "SELECT P1.ID AS ID, P1.TITLE AS TITLE, P1.PUBLICATION_DATE AS PUB_DATE, P1.PRICE AS PRICE," +
            " P2.ID AS TOPIC_ID, P2.NAME AS TOPIC " +
            "FROM PUBLICATION P1, PUBLICATION_TOPIC P2 WHERE P1.PUBLICATION_TOPIC = P2.ID;";

    private static final String CREATE_PUBLICATION = "INSERT INTO PUBLICATION (TITLE, PUBLICATION_DATE, PUBLICATION_TOPIC) VALUES (?,?,?)";

    public List<Object> getAllPublications(){
        List<Object> publications= new ArrayList<>();
        Connection connection = null;

        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PUBLICATIONS);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    publications.add(new Publication(resultSet.getInt("ID"),resultSet.getString("TITLE"),resultSet.getDate("PUB_DATE"),resultSet.getInt("PRICE")
                            ,new PublicationTopic(resultSet.getInt("TOPIC_ID"),resultSet.getString("TOPIC"))));
                }
            }
        } catch (Exception e) {
            System.out.println(Constants.CONNECTION_ERROR.getMessage());
            return publications;
        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return publications;
        }
    }

    public boolean createPublication(Publication publication){
        Connection connection =null;
        boolean flag = false;
        try{
            connection = DatabaseUtility.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PUBLICATION);

            preparedStatement.setString(1,publication.getPublicationTitle());
            preparedStatement.setDate(2,publication.getPublicationDate());
            preparedStatement.setInt(3,publication.getPublicationTopic().getPublicationTopicId());

            int result = preparedStatement.executeUpdate();
            flag = result ==1 ? true: false;

        } catch(Exception e){
            System.out.println(Constants.CONNECTION_ERROR.getMessage());
            return false;
        } finally {
            try{
                DatabaseUtility.closeconnection();
            }catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return flag;
        }

    }



}
