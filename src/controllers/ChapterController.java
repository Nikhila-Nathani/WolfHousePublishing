package controllers;

import entity.Book;
import entity.Chapter;
import services.ChapterService;

import java.util.List;

public class ChapterController {
    private ChapterService chapterService;

    public ChapterController(){
        chapterService = new ChapterService();
    }

    public Chapter addNewChapters(List<Chapter> chapters){
        for(Chapter chapter: chapters){
            if(!chapterService.addNewChapter(chapter)){
                return  chapter;
            }
        }
        return null;
    }

    public List<Object> getChaptersForABook(Book book){
        return chapterService.getChaptersForABook(book);
    }

    public Chapter deleteChapters(List<Chapter> chaptersToBeDeleted) {
        for(Chapter chapter : chaptersToBeDeleted){
           if(!(chapterService.deleteChapter(chapter))){
               return chapter;
           }
        }
        return null;
    }
}
