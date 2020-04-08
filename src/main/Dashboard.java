package main;

import constants.Constants;
import controllers.*;
import entity.*;
import services.PublicationTopicService;
import utility.DatabaseUtility;
import utility.PageUtility;
import utility.PropertiesUtility;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dashboard {

    private static Scanner scanner;

    private static PublicationController publicationController;
    private static EditorController editorController;
    private static EditsController editsController;
    private  static PublicationTopicController publicationTopicController;
    private  static ArticleController articleController;
    private static PeriodicPublicationController periodicPublicationController;
    private  static HasArticleController hasArticleController;
    private static  BookController bookController;
    private static  ChapterController chapterController;
    static{
        scanner = new Scanner(System.in);
        publicationController = new PublicationController();
        editorController = new EditorController();
        editsController = new EditsController();
        publicationTopicController = new PublicationTopicController();
        articleController = new ArticleController();
        periodicPublicationController = new PeriodicPublicationController();
        hasArticleController = new HasArticleController();
        bookController = new BookController();
        chapterController = new ChapterController();
    }

    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        PropertiesUtility.init();
        DatabaseUtility.init();
        //getEditorsForPublication();
//        getPublicationById();
        //updatePublication();
        //getPublicationByTitle();
        //getPublicationByDate();
        //getPublicationByTopic();

        //addArticlesToPeriodicPublication();
        // addChaptersToBook();
        //deleteChaptersFromBooks();
        //deleteArticlesForPeriodicPublication();
//        articleController.getAllArticles();
//
        //publicationTopicController.getAllPublicationTopics();
        /*Editing and publishing.
        1. Enter basic information on a new publication.
             createPublication();
        2. Update information.
            updatePublication()
        3. Assign editor(s) to publication.
        4. Let each editor view the information on the publications he/she is responsible for.
        5. Edit table of contents of a publication, by adding/deleting articles (for periodic publications) or chapters/sections (for books).*/

        //assignEditors();
//        getPublicationForEditors();


        /*Operations 2 */

        //getBookByTopic();
        //getBookByDate();
        //getArticlesByTopic();
        //getArticlesByDate();
        //createArticle();
        deleteArticle();

    }

    private static void deleteArticle() {
        System.out.println("------------------------ DELETE ARTICLE -------------------------------");
        List<Object> articles = articleController.getAllArticles();
        System.out.println("Enter the serial number of article you wish to delete : ");
        String column = "SerialNo\tTitle\tDateOfCreation";
        PageUtility.displayOptions(column,articles);
        int serialNumber = scanner.nextInt();
        if(articleController.deleteArticle((Article) articles.get(serialNumber-1))){
            System.out.println("Article deleted successfully!");
        }
    }

    private static void createArticle(){
        System.out.println("------------------------ CREATE ARTICLE -------------------------------");
        System.out.println("Enter the title for article : ");
        String title = scanner.nextLine();
        System.out.println("Enter the date of creation for article (YYYY-MM-DD) :");
        String date = scanner.next();
        Article a = new Article(title,Date.valueOf(date));
        if(articleController.createArticle(a)){
            System.out.println("Article was created successfully!");
        }
    }

    private static void getArticlesByDate() {
        //TODO Articles have date of creation of its own as well as date of creation of publication (IF they are mapped in has_articles table)
        //This function is written considering date of creation
        System.out.println("----------------------GET ARTICLES BY DATE -------------------------");
        System.out.println("Enter the date of creation of the article you want to retrieve (YYYY-MM-DD): ");
        String date = scanner.next();
        List<Object> articles = articleController.getArticlesByDate(date);
        if(articles.size()!=0){
            System.out.println("The books for the given topic are as follows : ");
            String columns1 = "SerialNo\tTitle\tTDateOFCreation";
            PageUtility.displayOptions(columns1,articles);
        }else{
            System.out.println(Constants.RECORD_NOT_FOUND.getMessage());
        }
    }

    private static void getArticlesByTopic() {
        System.out.println("----------------------GET ARTICLES BY TOPIC -------------------------");
        List<Object> publicationTopics = publicationTopicController.getAllPublicationTopics();
        System.out.println("Select the serial no of publication topic for which you want to retireve the articles :");
        String columns = "SerialNo\tId\tName";
        PageUtility.displayOptions(columns,publicationTopics);
        Integer topicId = scanner.nextInt();
        List<Object> articles = articleController.getArticlesByTopic(((PublicationTopic)publicationTopics.get(topicId-1)).getPublicationTopicName());
        if(articles.size()!=0){
            System.out.println("The books for the given topic are as follows : ");
            String columns1 = "SerialNo\tTitle\tTDateOFCreation";
            PageUtility.displayOptions(columns1,articles);
        }else{
            System.out.println(Constants.RECORD_NOT_FOUND.getMessage());
        }
    }


    private static void getBookByTopic() {
        System.out.println("----------------------GET BOOKS BY TOPIC -------------------------");
        List<Object> publicationTopics = publicationTopicController.getAllPublicationTopics();
        System.out.println("Select the serial no of publication topic for which you want to retireve the books :");
        String columns = "SerialNo\tId\tName";
        PageUtility.displayOptions(columns,publicationTopics);
        Integer topicId = scanner.nextInt();
        List<Object> books = bookController.getBookByTopic(((PublicationTopic)publicationTopics.get(topicId-1)).getPublicationTopicName());
        if(books.size()!=0){
            System.out.println("The books for the given topic are as follows : ");
            String columns1 = "SerialNo\tId\tTitle\tTopic\tISBN\tEdition";
            PageUtility.displayOptions(columns1,books);
        }else{
            System.out.println(Constants.RECORD_NOT_FOUND.getMessage());
        }
    }

    private static void getBookByDate() {
        System.out.println("----------------------GET BOOKS BY DATE -------------------------");
        System.out.println("Enter the publication date of the book you want to retrieve (YYYY-MM-DD): ");
        String date = scanner.next();
        List<Object> books = bookController.getBookSByDate(date);
        if(books.size()!=0){
            System.out.println("The books for the given topic are as follows : ");
            String columns1 = "SerialNo\tId\tTitle\tTopic\tISBN\tEdition";
            PageUtility.displayOptions(columns1,books);
        }else{
            System.out.println(Constants.RECORD_NOT_FOUND.getMessage());
        }
    }

    private static void deleteChaptersFromBooks() {
            System.out.println("---------------------- DELETE CHAPTERS FROM BOOKS -------------------------");
            List<Object> books = bookController.getAllBooks();
            List<Object> chapters = new ArrayList<>();
            List<Chapter> chaptersToBeDeleted = new ArrayList<>();
            while(true){
                System.out.println("Select the serial number of the book for which you want to delete a chapter : ");
                String columns = "SerialNo\tId\tTitle\tTopic\tISBN\tedition";
                PageUtility.displayOptions(columns,books);
                int num = scanner.nextInt();
                Book book = (Book) books.get(num-1);
                chapters = chapterController.getChaptersForABook(book);
                System.out.println("Enter the serial numbers for chapters you wish to delete for the current book (seperated by \",\") :");
                String columns1 = "SerialNo\tBookId\tBook Name\tChapterNo";
                PageUtility.displayOptions(columns1,chapters);
                String input = scanner.next();
                String serialNochaptersToBeDeleted[] = input.split(",");
                for(String id : serialNochaptersToBeDeleted){
                    chaptersToBeDeleted.add((Chapter) chapters.get(Integer.parseInt(id)-1));
                }
                System.out.println("Do you wish to delete chapters of any other book (Y/N)?");
                String option = scanner.next();
                while(!option.equalsIgnoreCase("Y") && !option.equalsIgnoreCase("N")){
                    System.out.println("Please enter valid option (Y/N).");
                    option = scanner.next();
                }
                if(option.equalsIgnoreCase("N")){
                    break;
                }
            }
            Chapter result = chapterController.deleteChapters(chaptersToBeDeleted);
            if(result == null){
                System.out.println("All the chapters for the respective books have been deleted!");
            }else{
                System.out.println("Deleted of chapter failed for the following chapter :\n"+result.toString());
                System.out.println("All the previous pairs have been successfully deleted.!");
                System.out.println("All the later pairs have been skipped!");
            }
    }

    private static void addChaptersToBook() {
        //TODO check the foreign key constraint on book_id of chapter
        /*chapter table right now contains publication_ids which are not books (which should not happen)*/
        System.out.println("---------------------- ADD CHAPTERS TO BOOKS -------------------------");
        List<Object> books = bookController.getAllBooks();
        List<Chapter> chapters = new ArrayList<>();
        while(true){
            System.out.println("Select the serial number of the book for which you want to add a chapter : ");
            String columns = "SerialNo\tId\tTitle\tTopic\tISBN\tedition";
            PageUtility.displayOptions(columns,books);
            int num = scanner.nextInt();
            Book book = (Book) books.get(num-1);
            while(true){
                System.out.println("Enter the chapter No for the new chapter :");
                int chapterNo = scanner.nextInt();
                System.out.println("Enter the text for the new chapter : ");
                String dummy = scanner.nextLine();
                String text = scanner.nextLine();
                Chapter chapter = new Chapter(book,chapterNo,text);
                chapters.add(chapter);
                System.out.println("Do you wish to add more chapters to current book (Y/N)?");
                String option = scanner.next();
                while(!option.equalsIgnoreCase("Y") && !option.equalsIgnoreCase("N")){
                    System.out.println("Please enter valid option (Y/N).");
                    option = scanner.next();
                }
                if(option.equalsIgnoreCase("N")){
                    break;
                }
            }
            System.out.println("Do you wish to chapters to any other book (Y/N)?");
            String option = scanner.next();
            while(!option.equalsIgnoreCase("Y") && !option.equalsIgnoreCase("N")){
                System.out.println("Please enter valid option (Y/N).");
                option = scanner.next();
            }
            if(option.equalsIgnoreCase("N")){
                break;
            }
        }

        Chapter result = chapterController.addNewChapters(chapters);
        if(result == null){
            System.out.println("All the chapters for the respective books have been added!");
        }else{
            System.out.println("Addition of chapter failed for the following chapter :\n"+result.toString());
            System.out.println("All the previous pairs have been successfully added.!");
            System.out.println("All the later pairs have been skipped!");
        }
    }

    public static void addArticlesToPeriodicPublication(){
        System.out.println("---------------------- ADD ARTICLES TO PERIODIC PUBLICATION -------------------------");

        List<HasArticle> hasArticles = new ArrayList<>();

        List<Object> periodicPublications = periodicPublicationController.getAllPeriodicPublications();
        List<Object> articles = articleController.getAllArticles();

        while(true){
            System.out.println("Select the serial number of the periodic publication to which you want to add an article : ");
            String columns = "SerialNo\tID\tTitle\tPublication Date\tPrice\tTopic\tPeriodicity";
            PageUtility.displayOptions(columns,periodicPublications);
            int num = scanner.nextInt();
            PeriodicPublication periodicPublication = (PeriodicPublication) periodicPublications.get(num-1);

            System.out.println("Enter the articles you wish to add for the given periodic publication :");

            System.out.println("Enter the serial numbers for articles to be assigned the current periodic publication (seperated by \",\") :");
            String columns2 = "SerialNo\tTitle\tDate Of creation";
            PageUtility.displayOptions(columns2,articles);
            String articleSerialNumbers = scanner.next();
            String articlesToBeAssigned[] = articleSerialNumbers.split(",");

            for(String id : articlesToBeAssigned){
                String title = ((Article)(articles.get(Integer.parseInt(id)-1))).getTitle();
                hasArticles.add(new HasArticle(periodicPublication,title));
            }
            System.out.println("Do you wish to add articles to more periodic publications (Y/N)?");
            String option = scanner.next();
            while(!option.equalsIgnoreCase("Y") && !option.equalsIgnoreCase("N")){
                System.out.println("Please enter valid option (Y/N).");
                option = scanner.next();
            }
            if(option.equalsIgnoreCase("N")){
                break;
            }
        }

        HasArticle result = hasArticleController.addArticleToPeriodicPublication(hasArticles);
        if(result == null){
            System.out.println("Articles have been assigned to given periodic publications successfully!");
        }else{
            System.out.println("Assignment failed for the given pair :\n"+result.toString());
            System.out.println("All the previous pairs have been successfully added.!");
            System.out.println("All the later pairs have been skipped!");
        }
    }


    public static void deleteArticlesForPeriodicPublication(){
        System.out.println("---------------------- DELETE ARTICLES FOR PERIODIC PUBLICATION -------------------------");
        List<Object> periodicPublications = periodicPublicationController.getAllPeriodicPublications();
        List<Object> articles = new ArrayList<>();
        List<HasArticle> hasArticles = new ArrayList<>();
        while(true){
            System.out.println("Select the serial number of the periodic publication for which you want to delete an article : ");
            String columns = "SerialNo\tID\tTitle\tPublication Date\tPrice\tTopic\tPeriodicity";
            PageUtility.displayOptions(columns,periodicPublications);
            int num = scanner.nextInt();
            PeriodicPublication periodicPublication = (PeriodicPublication) periodicPublications.get(num-1);
            articles = hasArticleController.getArticlesForPeriodicPublication(periodicPublication);
            System.out.println("Enter the articles you wish to delete for the given periodic publication :");
            System.out.println("Enter the serial numbers for articles to be deleted for the current periodic publication (seperated by \",\") :");
            String columns2 = "SerialNo\tTitle\tDate Of creation";
            PageUtility.displayOptions(columns2,articles);
            String articleSerialNumbers = scanner.next();
            String articlesToBeDeleted[] = articleSerialNumbers.split(",");

            for(String id : articlesToBeDeleted){
                String title = ((Article)(articles.get(Integer.parseInt(id)-1))).getTitle();
                hasArticles.add(new HasArticle(periodicPublication,title));
            }
            System.out.println("Do you wish to delete articles for some more periodic publications (Y/N)?");
            String option = scanner.next();
            while(!option.equalsIgnoreCase("Y") && !option.equalsIgnoreCase("N")){
                System.out.println("Please enter valid option (Y/N).");
                option = scanner.next();
            }
            if(option.equalsIgnoreCase("N")){
                break;
            }
        }

        HasArticle result = hasArticleController.deleteArticlesForPeriodicPublication(hasArticles);
        if(result == null){
            System.out.println("Articles corresponding to given periodic publications have been deleted successfully!");
        }else{
            System.out.println("Deletion failed for the given pair :\n"+result.toString());
            System.out.println("All the previous pairs have been deleted added.!");
            System.out.println("All the later pairs have been skipped!");
        }
    }


    public static void getPublicationByTopic(){
        System.out.println("---------------------- GET PUBLICATION BY TOPIC -------------------------");
        List<Object> publicationTopics = publicationTopicController.getAllPublicationTopics();
        System.out.println("Enter the serial Number of the publication topic you wish to retrieve publications for : ");
        String columns = "SerialNo\tId\tName";
        PageUtility.displayOptions(columns,publicationTopics);
        int option = scanner.nextInt();
        PublicationTopic currentPublicationTopic = (PublicationTopic) publicationTopics.get(option-1);
        List<Object> publications = publicationController.getPublicationByTopic(currentPublicationTopic);
        if(publications.size()!=0){
            System.out.println("The publications for the given date are as follows : ");
            String columns1 = "SerialNo\tId\tTitle\tPublicationDate\tPrice\tPublicationTopic";
            PageUtility.displayOptions(columns1,publications);
        }else{
            System.out.println(Constants.RECORD_NOT_FOUND.getMessage());
        }

    }


    public static void getPublicationByDate(){
        System.out.println("---------------------- GET PUBLICATION BY DATE -------------------------");
        System.out.println("Enter the date of the publication you want to retrieve (YYYY-MM-DD): ");
        String date = scanner.next();
        List<Object> publications = publicationController.getPublicationByDate(date);
        if(publications.size()!=0){
            System.out.println("The publications for the given date are as follows : ");
            String columns1 = "SerialNo\tId\tTitle\tPublicationDate\tPrice\tPublicationTopic";
            PageUtility.displayOptions(columns1,publications);
        }else{
            System.out.println(Constants.RECORD_NOT_FOUND.getMessage());
        }
    }


    public static void getPublicationById(){
        System.out.println("---------------------- GET PUBLICATION BY ID -------------------------");
        System.out.println("Enter the Id of the publication you want to retrieve : ");
        Integer publicationId = scanner.nextInt();
        Publication publication = publicationController.getPublicationById(publicationId);
        if(publication!=null){
            String columns1 = "\tId\tTitle\tPublicationDate\tPrice\tPublicationTopic";
            System.out.println(columns1);
            System.out.println("-----------------------------------------------------");
            System.out.println(publication.toString());
        }else{
            System.out.println(Constants.RECORD_NOT_FOUND.getMessage());
        }
    }

    public static void getPublicationByTitle(){
        System.out.println("---------------------- GET PUBLICATION BY TITLE -------------------------");
        System.out.println("Enter the title of the publication you want to retrieve : ");
        String title = scanner.nextLine();
        Publication publication = publicationController.getPublicationByTitle(title);
        if(publication!=null){
            String columns1 = "\tId\tTitle\tPublicationDate\tPrice\tPublicationTopic";
            System.out.println(columns1);
            System.out.println("-----------------------------------------------------");
            System.out.println(publication.toString());
        }else{
            System.out.println(Constants.RECORD_NOT_FOUND.getMessage());
        }
    }

    public static  void updatePublication(){
        System.out.println("---------------------- UPDATE PUBLICATION -------------------------");
        List<Object> publications = publicationController.getAllPublications();
        System.out.println("Enter the serial number of the publication you wish to update: ");
        String columns1 = "SerialNo\tId\tTitle\tPublicationDate\tPrice\tPublicationTopic";
        PageUtility.displayOptions(columns1,publications);
        int publicationSerialNumber= scanner.nextInt();
        Publication currentPublication = (Publication) publications.get(publicationSerialNumber-1);
        System.out.println("Do you want to update 'Title'? (Y/N) ");
        String option = scanner.next();
        while(!option.equalsIgnoreCase("Y") && !option.equalsIgnoreCase("N")){
            System.out.println("Please enter valid option (Y/N).");
            option = scanner.next();
        }
        if(option.equalsIgnoreCase("y")){
            System.out.println("Enter new title : \t");
            String newTitle = scanner.nextLine();
            String str1 = scanner.nextLine();
            currentPublication.setPublicationTitle(str1);
        }
        System.out.println("Do you want to update 'Publication Date'? (Y/N) ");
        option = scanner.next();
        while(!option.equalsIgnoreCase("Y") && !option.equalsIgnoreCase("N")){
            System.out.println("Please enter valid option (Y/N).");
            option = scanner.next();
        }
        if(option.equalsIgnoreCase("y")){
            System.out.println("Enter new Publication Date (YYYY-MM-DD) : \t");
            String newDate = scanner.next();
            currentPublication.setPublicationDate(Date.valueOf(newDate));
        }
        System.out.println("Do you want to update 'Price'? (Y/N) ");
        option = scanner.next();
        while(!option.equalsIgnoreCase("Y") && !option.equalsIgnoreCase("N")){
            System.out.println("Please enter valid option (Y/N).");
            option = scanner.next();
        }
        if(option.equalsIgnoreCase("y")){
            System.out.println("Enter new Price: \t");
            Integer newPrice = scanner.nextInt();
            currentPublication.setPrice(newPrice);
        }
        System.out.println("Do you want to update 'Publication Topic'? (Y/N) ");
        option = scanner.next();
        while(!option.equalsIgnoreCase("Y") && !option.equalsIgnoreCase("N")){
            System.out.println("Please enter valid option (Y/N).");
            option = scanner.next();
        }
        if(option.equalsIgnoreCase("y")){
            List<Object> publicationTopics = publicationTopicController.getAllPublicationTopics();
            System.out.println("Enter the serial Number of the publication topic you wish to assign for this publication : ");
            String columns = "SerialNo\tId\tName";
            PageUtility.displayOptions(columns,publicationTopics);
            int newTopic = scanner.nextInt();
            System.out.println(publicationTopics.get(newTopic-1).toString());
            currentPublication.setPublicationTopic((PublicationTopic) publicationTopics.get(newTopic-1));
            System.out.println(currentPublication.toString());
        }
        if (publicationController.updatePublication(currentPublication)){
            System.out.println("Publication updated successfully!");
        }
    }

    public static void createPublication(){
        System.out.println("---------------------- CREATE PUBLICATION -------------------------");
        System.out.println("Enter the Title for the Publication : ");
        String title = scanner.nextLine();
        System.out.println("Enter the price for the publication : ");
        Integer price = scanner.nextInt();
        System.out.println("Enter the publication date : (YYYY-MM-DD)");
        String date = scanner.next();
        Date date1 = Date.valueOf(date);
        List<Object> publicationTopics = publicationTopicController.getAllPublicationTopics();
        System.out.println("Enter the serial Number of the publication topic you wish to assign for this publication : ");
        String columns = "SerialNo\tId\tName";
        PageUtility.displayOptions(columns,publicationTopics);
        int option = scanner.nextInt();
        PublicationTopic currentPublicationTopic = (PublicationTopic) publicationTopics.get(option-1);
        if(publicationController.createPublication(new Publication(title,date1,price,currentPublicationTopic))){
            System.out.println("Publication created successfully!");
        }
    }


    public static void getEditorsForPublication(){
        System.out.println("------------- GET EDITORS FOR PUBLICATION -----------------------");
        List<Object> publications = publicationController.getAllPublications();
        System.out.println("Choose the publication serial no you want to fetch the editors for : ");
        String columns1 = "SerialNo\tId\tTitle\tPublicationDate\tPublicationTopic";
        PageUtility.displayOptions(columns1,publications);
        int publicationSerialNumber= scanner.nextInt();
        Publication publication = (Publication) publications.get(publicationSerialNumber-1);
        List<Object> editors = editsController.getEditorForPublication(publication);
        String columns = "SerialNo\tId\tName\tPay\tPeriodicty";
        System.out.println("The editors assigned to given publication are as follows : ");
        PageUtility.displayOptions(columns,editors);
    }

    public static void getPublicationForEditors(){
        System.out.println("------------- GET PUBLICATION FOR EDITORS -----------------------");
        List<Object> editors = editorController.getAllEditors();
        System.out.println("Enter the serial number of editor for which you will like to see the publications :");
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
            String columns1 = "SerialNo\tId\tTitle\tPublicationDate\tPrice\tPublicationTopic";
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
            System.out.println("Assignment failed for the given pair :\n"+result.toString());
            System.out.println("All the previous pairs have been successfully added.!");
            System.out.println("All the later pairs have been skipped!");
        }

    }
}