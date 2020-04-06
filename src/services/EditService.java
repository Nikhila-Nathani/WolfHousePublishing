package services;

import constants.Constants;
import entity.Editor;
import entity.Edits;
import entity.Publication;
import entity.PublicationTopic;
import utility.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EditService {
    private static final String ASSIGN_EDITORS = "INSERT INTO EDITS (EDITOR_ID,PUBLICATION_ID) VALUES(?,?)";
    private static final String GET_PUBLICATIONS_FOR_EDITOR = "SELECT P1.ID AS P_ID, P1.TITLE AS TITLE, P1.PUBLICATION_DATE AS DATE, P1.PRICE AS PRICE, P2.ID AS TOPIC_ID, P2.NAME AS TOPIC " +
            "FROM EDITS E1, EMPLOYEE E2, PUBLICATION P1, PUBLICATION_TOPIC P2 " +
            "WHERE E1.EDITOR_ID = E2.ID AND E1.PUBLICATION_ID = P1.ID AND P1.PUBLICATION_TOPIC = P2.ID AND E1.EDITOR_ID = ?";
    public boolean assignEditorToPublication (Edits edit){
        Connection connection = null;
        boolean flag = false;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ASSIGN_EDITORS);
            preparedStatement.setInt(1,edit.getEditor().getEmployee().getEmployeeId());
            preparedStatement.setInt(2,edit.getPublication().getPublicationId());

            int result = preparedStatement.executeUpdate();
            flag = result == 1? true : false;
        } catch (Exception e){
            if(connection!=null){
                System.out.println(Constants.CONSTRAINT_VIOLATED.getMessage());
            }else{
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }
        } finally {
            try{
                DatabaseUtility.closeconnection();
            } catch (Exception e) {
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return flag;
        }
    }

    public List<Object> getPublicationsForEditor(Editor editor){
        List<Object> publications = new ArrayList<>();
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PUBLICATIONS_FOR_EDITOR);
            preparedStatement.setInt(1,editor.getEmployee().getEmployeeId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    publications.add(new Publication(resultSet.getInt("P_ID"), resultSet.getString("TITLE"),
                            resultSet.getDate("DATE"), resultSet.getInt("PRICE"),
                            new PublicationTopic(resultSet.getInt("TOPIC_ID"), resultSet.getString("TOPIC"))));
                }
            }
        }catch(Exception e){
            System.out.println(Constants.CONNECTION_ERROR.getMessage());
        }finally {
            try{
                DatabaseUtility.closeconnection();
            } catch (Exception e) {
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return publications;
        }
    }
}
