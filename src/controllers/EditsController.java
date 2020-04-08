package controllers;

import entity.Editor;
import entity.Edits;
import entity.Publication;
import services.EditService;

import java.util.List;

public class EditsController {
    private EditService editService;

    public EditsController(){
        this.editService = new EditService();
    }

    public Edits assignEditorToPublication(List<Edits> edits){
        for(Edits e : edits){
           if(!editService.assignEditorToPublication(e)){
               return e;
           }
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
