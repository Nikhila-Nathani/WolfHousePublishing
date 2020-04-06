package main;

import controllers.EditorController;
import controllers.EditsController;
import controllers.PublicationController;
import entity.*;
import services.*;
import utility.DatabaseUtility;
import utility.PageUtility;
import utility.PropertiesUtility;


import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Dashboard {

    private static Scanner scanner;

    private static PublicationController publicationController;
    private static EditorController editorController;
    private static EditsController editsController;

    static{
        scanner = new Scanner(System.in);

        publicationController = new PublicationController();
        editorController = new EditorController();
        editsController = new EditsController();
    }

    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        PropertiesUtility.init();
        DatabaseUtility.init();
        //assignEditors();
        getPublicationForEditors();
    }


    public static void getPublicationForEditors(){
        System.out.println("------------- GET PUBLICATION FOR EDITORS -----------------------");
        List<Object> editors = editorController.getAllEditors();
        System.out.println("Enter the serial number editor for which you will like to see the publications :");
        String columns = "SerialNo\tId\tName";
        PageUtility.displayOptions(columns,editors);
        int editorSerialNuber = scanner.nextInt();
        Editor currentEditor = (Editor) editors.get(editorSerialNuber-1);
        List<Object>publications = editsController.getPublicationsForEditor(currentEditor);
        String columns1 = "SerialNo\tId\tTitle\tPublicationDate\tPrice\tPublicationTopic";
        System.out.println("The publications assigned to "+currentEditor.getEmployee().getEmployeeName()+" are as follows : ");
        PageUtility.displayOptions(columns1,publications);
    }

    public static void assignEditors(){
        System.out.println("---------------------- ASSIGN EDITORS TO PUBLICATIONS ----------");
        List<Object> publications = publicationController.getAllPublications();
        List<Object> editors = editorController.getAllEditors();
        List<Edits> edits = new ArrayList<>();
        while(true){
            System.out.println("Choose the publication serial no you want to assign editors for : ");
            String columns1 = "SerialNo\tId\tTitle\tPublicationDate\tPublicationTopic";
            PageUtility.displayOptions(columns1,publications);
            int publicationSerialNumber= scanner.nextInt();
            Publication currentPublication = (Publication) publications.get(publicationSerialNumber-1);
            System.out.println("Enter the serial numbers for editors to be assigned the current publication (seperated by \",\") :");
            String columns2 = "SerialNo\tId\tName";
            PageUtility.displayOptions(columns2,editors);
            String editorSerialNumebrs = scanner.next();
            String editorsToBeAssigned[] = editorSerialNumebrs.split(",");
            for(String id : editorsToBeAssigned){
                Editor e = (Editor) editors.get(Integer.parseInt(id)-1);
                edits.add(new Edits(e,currentPublication));
            }
            System.out.println("Do you wish to add editors for more publications (Y/N)?");
            String option = scanner.next();
            while(!option.equalsIgnoreCase("Y") && !option.equalsIgnoreCase("N")){
                System.out.println("Please enter valid option (Y/N).");
                option = scanner.next();
            }
            if(option.equalsIgnoreCase("N")){
                break;
            }
        }

        Edits result = editsController.assignEditorToPublication(edits);
        if(result == null){
            System.out.println("Editors have been assigned to given publications successfully!");
        }else{
            System.out.println("Assignment failed for the given pair :"+result.toString());
            System.out.println("All the previous pairs have been successfully added.!");
            System.out.println("All the later pairs have been skipped!");
        }

    }
}