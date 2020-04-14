package controllers;

import constants.Constants;
import entity.Editor;
import entity.Edits;
import entity.Publication;
import services.EditService;
import utility.DatabaseUtility;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EditsController {
    private EditService editService;

    public EditsController(){
        this.editService = new EditService();
    }

    public Edits assignEditorToPublication(List<Edits> edits){
        Edits result = null;
        try {
            Connection connection = DatabaseUtility.getConnection();
            DatabaseUtility.beginTransaction();
            for(Edits e : edits){
                if(!editService.assignEditorToPublication(e,connection)){
                    connection.rollback();
                    result = e;
                    return e;
                }
            }
            connection.commit();
            DatabaseUtility.endTransaction();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                DatabaseUtility.closeconnection();
            } catch (Exception e) {
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return result;
        }


    }

    public List<Object> getPublicationsForEditor(Editor e){
        return editService.getPublicationsForEditor(e);
    }

    public List<Object> getEditorForPublication(Publication publication){
        return editService.getEditorForPublication(publication);
    }

}
