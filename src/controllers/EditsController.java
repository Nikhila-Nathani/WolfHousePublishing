package controllers;

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
        Connection connection = null;
        try {
            connection = DatabaseUtility.getConnection();
            for(Edits e : edits){
                if(!editService.assignEditorToPublication(e,connection)){
                    connection.rollback();
                    System.out.println("Rolling back");
                    return e;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            DatabaseUtility.closeconnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Object> getPublicationsForEditor(Editor e){
        return editService.getPublicationsForEditor(e);
    }

    public List<Object> getEditorForPublication(Publication publication){
        return editService.getEditorForPublication(publication);
    }

}
