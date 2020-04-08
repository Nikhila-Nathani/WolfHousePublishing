package constants;

public enum Constants{

    CONNECTION_ERROR("Error in connecting to database"),
    CONNECTION_CLOSE_ERROR("Error in closing the database connection !"),
    CONSTRAINT_VIOLATED("Constraint violated while entering data, please verify again!"),
    RECORD_NOT_FOUND("Record not found in database! ");
    private String message;
    private Constants(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }

}
