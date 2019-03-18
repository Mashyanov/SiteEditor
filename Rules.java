package javaapplication58;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Rules extends Regulation{

    public Rules() {
        ObjectInputStream ois = null;
        try {
        ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(Team.ruleData)));
        } catch (FileNotFoundException ex) { if(options.isCatchPrint()) System.out.println("FileNotFoundException " + ex.getMessage());
        } catch (IOException ex) {if(options.isCatchPrint()) System.out.println("IOException " + ex.getMessage());}
        try {
            this.chapters =  (ArrayList<Chapter>) ois.readObject();
            if(getChapterByNumber(RULES_SOURCE)== null){
                Chapter c = new Chapter("");
                c.setNumber(RULES_SOURCE);
                addChapter(c);
            }
        } catch (IOException | ClassNotFoundException ex) {
           if(options.isCatchPrint()) System.out.println(ex.getMessage());
        }
    }

    @Override
    public void saveToFile() {
       try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Team.ruleData))) { 
            oos.writeObject(chapters);        }
        catch (IOException ex) {
            if(options.isCatchPrint()) System.out.println("НЕ УДАЛОСЬ СОХРАНИТЬ ПРАВИЛА В ФАЙЛ " + ex.getMessage());
        }}

   
    
    
}
