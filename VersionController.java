package javaapplication58;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class VersionController {
private SystemOptions options = SystemOptions.getInstance();
private boolean print = SystemOptions.getInstance().isDebugPrint();
private final File file = new File("vers.dat"); 
private final MyFTPClient client = MyFTPClient.getInstace();
private Version actualVersion = null;
private ObjectInputStream ois = null;

    public VersionController() {
       try {
        ois = new ObjectInputStream(new FileInputStream(file));
        actualVersion = (Version) ois.readObject();
    } catch (FileNotFoundException ex) {
        if(options.isCatchPrint())System.out.println(ex.getMessage());
    } catch (IOException | ClassNotFoundException ex) {
        if(options.isCatchPrint())System.out.println(ex.getMessage());
    }
    finally{
            try {
                ois.close();
            } catch (IOException ex) {
                if(options.isCatchPrint())System.out.println(ex.getMessage());
            }
    }}
  
public final void setRegFailure(Version v){
    try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
    if(!file.exists()) file.createNewFile();
    actualVersion.setVersionOfRegistrationFailure(v);
    oos.writeObject(actualVersion);
    client.uploadFile(file, MyFTPClient.FTPvers);
    }   catch (FileNotFoundException ex) {
        if(options.isCatchPrint())System.out.println(ex.getMessage());
    } catch (IOException ex) {
        if(options.isCatchPrint())System.out.println(ex.getMessage());
    }
}

public final Version getRegFailure(){
    return actualVersion.getVersionOfRegistrationFailure();
}
    
public final void setVersion(int a, int b, int c){
try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
    if(!file.exists()) file.createNewFile();
    actualVersion.setA(a);
    actualVersion.setB(b);
    actualVersion.setC(c);
    oos.writeObject(actualVersion);
    client.uploadFile(file, MyFTPClient.FTPvers);
    }   catch (FileNotFoundException ex) {
        if(options.isCatchPrint())System.out.println(ex.getMessage());
    } catch (IOException ex) {
        if(options.isCatchPrint())System.out.println(ex.getMessage());
    }
}

public final void setVersion(Version v){
try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
    if(!file.exists()) file.createNewFile();
    oos.writeObject(v);
    client.uploadFile(file, MyFTPClient.FTPvers);
    }   catch (FileNotFoundException ex) {
        if(options.isCatchPrint())System.out.println(ex.getMessage());
    } catch (IOException ex) {
        if(options.isCatchPrint())System.out.println(ex.getMessage());
    }
}

public boolean checkVersion(){
    if(print)System.out.println("Проверяем актуальность версии...");
    if(print)System.out.println("Версия программы  - " + Team.getVersion());
    if(print)System.out.println("Актуальная версия - " + actualVersion);
    if(!actualVersion.equals(Team.getVersion())){
        if(print)System.out.println("неактуальная версия");
        if(JOptionPane.showConfirmDialog(new JPanel(), "Обнаружена новая версия " + actualVersion + "! Скачать?",
                "Обнаружено обновление", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE)==0){
            client.downloadUpdate();
            System.exit(0);
        }
        else
            System.exit(0);
    }
    if(print)System.out.println("Все ок, актуальная версия");
    return true;
}

public String getVersionString(Version v)
{
    StringBuilder stb = new StringBuilder();
    stb.append(Integer.toString(v.getA()));
    stb.append('.');
    stb.append(Integer.toString(v.getB()));
    stb.append('.');
    stb.append(Integer.toString(v.getC()));
    return stb.toString();
}}