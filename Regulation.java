package javaapplication58;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**Класс, описывающий устав команды
 *
 * @author Александр Машьянов, mashyanov1987@gmail.com
 */
public class Regulation {
    protected SystemOptions options = SystemOptions.getInstance();
    protected ArrayList<Chapter> chapters = new ArrayList<>();
    public static final int RULES_SOURCE = -9999;

    public Regulation() {
        ObjectInputStream ois = null;
        try {
        ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(Team.rgltData)));
        } catch (FileNotFoundException ex) { if(options.isCatchPrint()) System.out.println("FileNotFoundException " + ex.getMessage());
        } catch (IOException ex) {if(options.isCatchPrint()) System.out.println("IOException " + ex.getMessage());}
        try {
            this.chapters =  (ArrayList<Chapter>) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
           if(options.isCatchPrint()) System.out.println(ex.getMessage());
        }
    }
       
    public void saveToFile(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Team.rgltData))) { 
            oos.writeObject(chapters);        }
        catch (IOException ex) {
            if(options.isCatchPrint()) System.out.println("НЕ УДАЛОСЬ СОХРАНИТЬ УСТАВ В ФАЙЛ " + ex.getMessage());
        }}
    
    public final ArrayList<Chapter> getChapters(){
            return chapters;
    }
    
    public final ArrayList<String> getChaptersNames(){
        ArrayList<String> a = new ArrayList<>();
        for (Chapter chapter : chapters) {
            a.add(chapter.getName());
        }
        return a;
    }
    
    public final  void sortChapters(){
        Collections.sort(chapters, new idComparator());
        saveToFile();
    }
    
    public final void addChapter(Chapter chapter){
        if(chapter.getNumber() != RULES_SOURCE){
            int max = 0;
            for (Chapter c : chapters) {
                if(c.getNumber() > max && c.getNumber()!= RULES_SOURCE)
                    max = c.getNumber();
            }
            chapter.setNumber(max + 1); 
        }
        chapters.add(chapter);
               
    }
    
    public void deleteChapter(String name){
        for (Chapter chapter : chapters) {
            if(chapter.getName().equals(name)) {
                this.chapters.remove(getChapterByName(name));
                return;
            }
        }
    }
    
    public final Chapter getChapterByName(String name){
        for (Chapter chapter : chapters) {
            if(chapter.getName().equals(name)) return chapter;
        }
         System.out.println("глава с именем " + name +" не найдена");
        return null;
    }
    
    public final Chapter getChapterByNumber(int number){
        for (Chapter chapter : chapters) {
            if(chapter.getNumber() == number) return chapter;
        }
        return null;
    }
   
static class idComparator implements Comparator<Chapter>{
        public int compare(Chapter c1, Chapter c2) {
            return c1.getNumber()-c2.getNumber();
    }}
}
