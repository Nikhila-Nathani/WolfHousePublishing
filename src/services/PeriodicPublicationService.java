package services;

import constants.Constants;
import entity.PeriodicPublication;
import entity.Periodicity;
import entity.Publication;
import entity.PublicationTopic;
import utility.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PeriodicPublicationService {


    private static final String GET_ALL_PERIODIC_PUBLICATIONS = "select P.ID AS ID, P.TITLE AS TITLE , P.PUBLICATION_DATE AS PUB_DATE, P.PRICE, PT.ID AS TOPIC_ID, PT.NAME AS TOPIC, PR.ID AS PERIODICITY_ID, PR.NAME AS PERIODICITY_NAME  from PERIODIC_PUBLICATION PP, PUBLICATION P, PERIODICITY PR, PUBLICATION_TOPIC PT WHERE PP.PUBLICATION_ID = P.ID AND PP.PERIODICITY = PR.ID AND PT.ID = P.PUBLICATION_TOPIC;";

    private static final String CREATE_PERIODIC_PUBLICATION = "INSERT INTO PERIODIC_PUBLICATION (PUBLICATION_ID, PERIODICITY)  VALUES (?,?)";
    private static final String DELETE_PERIODIC_PUBLICATION = "DELETE FROM PERIODIC_PUBLICATION WHERE PERIODIC_PUBLICATION.PUBLICATION_ID = ?";
    private static final String UPDATE_PERIODIC_PUBLICATION = "UPDATE  PERIODIC_PUBLICATION SET PERIODICITY = ? WHERE PUBLICATION_ID = ?";

    public List<Object> getAllPeriodicPublications(){
        List<Object> periodicPublications = new ArrayList<>();
        Connection connection = null;

        try{
            connection = DatabaseUtility.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PERIODIC_PUBLICATIONS);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet!=null){
                while(resultSet.next()){
                    Periodicity periodicity = new Periodicity(resultSet.getInt("PERIODICITY_ID"), resultSet.getString("PERIODICITY_NAME"));
                    PublicationTopic topic = new PublicationTopic(resultSet.getInt("TOPIC_ID"), resultSet.getString("TOPIC"));

                    Publication publication = new Publication(resultSet.getInt("ID"), resultSet.getString("TITLE"),
                            resultSet.getDate("PUB_DATE"), resultSet.getInt("PRICE"), topic);

                    PeriodicPublication periodicPublication = new PeriodicPublication(publication,periodicity);
                    periodicPublications.add(periodicPublication);
                }
            }

        } catch(Exception e){
            if(connection==null){
                System.out.println(Constants.CONNECTION_ERROR);
            }
            return periodicPublications;
        } finally {
            try{
                DatabaseUtility.closeconnection();
            } catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return periodicPublications;
        }
    }

    public boolean createPeriodicPublication(PeriodicPublication periodicPublication){
        boolean flag = false;
        Connection connection = null;
        try{
            connection= DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PERIODIC_PUBLICATION);
            preparedStatement.setInt(1,periodicPublication.getPublication().getPublicationId());
            preparedStatement.setInt(2,periodicPublication.getPeriodicity().getPeriodicityId());
            int result = preparedStatement.executeUpdate();
            flag = result == 1? true :false;
        }catch(Exception e){
            if(connection==null){
                System.out.println(Constants.CONNECTION_ERROR);
            }
            return flag;
        } finally {
            try{
                DatabaseUtility.closeconnection();
            } catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return flag;
        }
    }

    public boolean deletePeriodicPublication(PeriodicPublication periodicPublication) {
        boolean flag= false;
        Connection connection = null;
        try{
            connection= DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PERIODIC_PUBLICATION);
            preparedStatement.setInt(1,periodicPublication.getPublication().getPublicationId());
            int result = preparedStatement.executeUpdate();
            flag = result == 1? true :false;
        }catch(Exception e){
            if(connection!=null){
                System.out.println(Constants.RECORD_NOT_FOUND.getMessage());
            }else{
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }
            return flag;
        } finally {
            try{
                DatabaseUtility.closeconnection();
            } catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return flag;
        }

    }

    public boolean updatePeriodicPublication(PeriodicPublication periodicPublication) {
        boolean flag = false;
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PERIODIC_PUBLICATION);
            preparedStatement.setInt(1,periodicPublication.getPeriodicity().getPeriodicityId());
            preparedStatement.setInt(2,periodicPublication.getPublication().getPublicationId());
            int result = preparedStatement.executeUpdate();
            flag = result == 1? true:false;

        }catch(Exception e){
            if(connection!=null){
                System.out.println(Constants.CONSTRAINT_VIOLATED.getMessage());
            } else {
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }
            return flag;
        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR);
            }
            return flag;
        }
    }
}
