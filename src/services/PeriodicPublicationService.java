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
}
