package services;

import constants.Constants;
import entity.Publication;
import entity.PublicationTopic;
import org.mariadb.jdbc.internal.util.dao.PrepareResult;
import utility.DatabaseUtility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PublicationService {

    private static final String GET_ALL_PUBLICATIONS =
            "SELECT P1.ID AS ID, P1.TITLE AS TITLE, P1.PUBLICATION_DATE AS PUB_DATE, P1.PRICE AS PRICE," +
            " P2.ID AS TOPIC_ID, P2.NAME AS TOPIC " +
            "FROM PUBLICATION P1, PUBLICATION_TOPIC P2 WHERE P1.PUBLICATION_TOPIC = P2.ID;";

    private static final String CREATE_PUBLICATION = "INSERT INTO PUBLICATION (TITLE, PUBLICATION_DATE, PUBLICATION_TOPIC, PRICE) VALUES (?,?,?,?)";

    private static final String UPDATE_PUBLICATION = "UPDATE PUBLICATION SET TITLE=?, PUBLICATION_DATE=?, PUBLICATION_TOPIC=?, PRICE = ? WHERE ID = ?";

    private static final String GET_PUBLICATION_BY_ID = "SELECT P1.ID AS ID, P1.TITLE AS TITLE, P1.PUBLICATION_DATE AS PUB_DATE, P1.PRICE AS PRICE, P2.ID AS TOPIC_ID, P2.NAME AS TOPIC FROM PUBLICATION P1, PUBLICATION_TOPIC P2 WHERE P1.PUBLICATION_TOPIC = P2.ID AND P1.ID = ?";

    private static final String GET_PUBLICATION_BY_TITLE= "SELECT P1.ID AS ID, P1.TITLE AS TITLE, P1.PUBLICATION_DATE AS PUB_DATE, P1.PRICE AS PRICE, P2.ID AS TOPIC_ID, P2.NAME AS TOPIC FROM PUBLICATION P1, PUBLICATION_TOPIC P2 WHERE P1.PUBLICATION_TOPIC = P2.ID AND P1.TITLE LIKE ?";

    private static final String GET_PUBLICATION_BY_DATE= "SELECT P1.ID AS ID, P1.TITLE AS TITLE, P1.PUBLICATION_DATE AS PUB_DATE, P1.PRICE AS PRICE, P2.ID AS TOPIC_ID, P2.NAME AS TOPIC FROM PUBLICATION P1, PUBLICATION_TOPIC P2 WHERE P1.PUBLICATION_TOPIC = P2.ID AND P1.PUBLICATION_DATE = ?";

    private static final String GET_PUBLICATION_BY_TOPIC = "SELECT P1.ID AS ID, P1.TITLE AS TITLE, P1.PUBLICATION_DATE AS PUB_DATE, P1.PRICE AS PRICE, P2.ID AS TOPIC_ID, P2.NAME AS TOPIC FROM PUBLICATION P1, PUBLICATION_TOPIC P2 WHERE P1.PUBLICATION_TOPIC = P2.ID AND P2.ID = ?;";


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
            preparedStatement.setInt(4,publication.getPrice());

            int result = preparedStatement.executeUpdate();
            flag = result ==1 ? true: false;

        } catch(Exception e){
            if(connection!=null){
                System.out.println(Constants.CONSTRAINT_VIOLATED.getMessage());
            }else{
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }
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

    public boolean updatePublication(Publication publication){
        Connection connection = null;
        boolean flag = false;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PUBLICATION);

            preparedStatement.setString(1,publication.getPublicationTitle());
            preparedStatement.setDate(2,publication.getPublicationDate());
            preparedStatement.setInt(3,publication.getPublicationTopic().getPublicationTopicId());
            preparedStatement.setInt(4,publication.getPrice());
            preparedStatement.setInt(5,publication.getPublicationId());

            flag = preparedStatement.executeUpdate()==1 ? true: false ;

        } catch(Exception e){
            if(connection!=null){
                System.out.println(Constants.CONSTRAINT_VIOLATED.getMessage());
            }else{
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }
            return false;
        } finally{
            try{
                DatabaseUtility.closeconnection();
            }catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return flag;
        }
    }


    public Publication getPublicationById(Integer publicationId){
        Connection connection = null;
        Publication publication = null;
        try{
            connection = DatabaseUtility.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(GET_PUBLICATION_BY_ID);

            preparedStatement.setInt(1,publicationId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!= null){
                while(resultSet.next()){
                    publication = new Publication(resultSet.getInt("ID"),resultSet.getString("TITLE"),resultSet.getDate("PUB_DATE"),resultSet.getInt("PRICE")
                            ,new PublicationTopic(resultSet.getInt("TOPIC_ID"),resultSet.getString("TOPIC")));
                }
            }
        }catch (Exception e) {
            System.out.println(Constants.CONNECTION_ERROR.getMessage());
            return publication;
        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return publication;
        }

    }

    public Publication getPublicationByTitle (String title){
        Connection connection = null;
        Publication publication = null;
        try{
            connection = DatabaseUtility.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(GET_PUBLICATION_BY_TITLE);

            preparedStatement.setString(1,title);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!= null){
                while(resultSet.next()){
                    publication = new Publication(resultSet.getInt("ID"),resultSet.getString("TITLE"),resultSet.getDate("PUB_DATE"),resultSet.getInt("PRICE")
                            ,new PublicationTopic(resultSet.getInt("TOPIC_ID"),resultSet.getString("TOPIC")));
                }
            }
        }catch (Exception e) {
            System.out.println(Constants.CONNECTION_ERROR.getMessage());
            return publication;
        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return publication;
        }
    }


    public List<Object> getPublicationByDate(String date){
        Connection connection = null;
        List<Object> publications = new ArrayList<>();
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PUBLICATION_BY_DATE);
            preparedStatement.setDate(1,Date.valueOf(date));
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet!=null){
                while(resultSet.next()){
                    Publication publication = new Publication(resultSet.getInt("ID"),resultSet.getString("TITLE"),resultSet.getDate("PUB_DATE"),resultSet.getInt("PRICE")
                            ,new PublicationTopic(resultSet.getInt("TOPIC_ID"),resultSet.getString("TOPIC")));
                    publications.add(publication);
                }
            }
        }catch (Exception e) {
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

    public List<Object> getPublicationByTopic(PublicationTopic publicationTopic){
        Connection connection = null;
        List<Object> publications = new ArrayList<>();
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PUBLICATION_BY_TOPIC);
            preparedStatement.setInt(1,publicationTopic.getPublicationTopicId());
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet!=null){
                while(resultSet.next()){
                    Publication publication = new Publication(resultSet.getInt("ID"),resultSet.getString("TITLE"),resultSet.getDate("PUB_DATE"),resultSet.getInt("PRICE")
                            ,new PublicationTopic(resultSet.getInt("TOPIC_ID"),resultSet.getString("TOPIC")));
                    publications.add(publication);
                }
            }
        }catch (Exception e) {
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



}
