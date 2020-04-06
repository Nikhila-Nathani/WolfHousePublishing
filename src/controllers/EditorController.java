package controllers;

import services.EditorService;

import java.util.List;

public class EditorController {
    private EditorService editorService;

    public EditorController(){
        editorService = new EditorService();
    }

    public List<Object> getAllEditors(){
        return editorService.getAllEditors();
    }
}
