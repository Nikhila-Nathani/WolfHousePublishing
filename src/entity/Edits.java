package entity;

public class Edits {
    Editor editor;
    Publication publication;


    public Edits(Editor editor, Publication publication) {
        this.editor = editor;
        this.publication = publication;
    }

    @Override
    public String toString() {
        return "Editor Id: \t"+editor.getEmployee().getEmployeeId()+"\tEditor Name : \t"+editor.getEmployee().getEmployeeName()+"\tPublication Id: \t"+getPublication().getPublicationId()+"\tPublication Name : \t"+getPublication().getPublicationTitle();
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }
}
