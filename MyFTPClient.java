
package javaapplication58;

import javaapplication58.panels.UpdatePanel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
/**
 *
 * @author Александр Машьянов, mashyanov1987@gmail.com
 */
public class MyFTPClient {
private FTPClient client;
private static MyFTPClient instance = null;
private String login;
private SystemOptions options = SystemOptions.getInstance();
private boolean print = options.isDebugPrint();

//АДРЕСА НА ХОСТИНГЕ
public final static String FIGHTERS_ADDRESS   = "/www/sk-bron.ru/Fighters/";
public final static String M_FIGHTERS_ADDRESS = "/www/mobile.sk-bron.ru/Fighters/";
public final static String MAIN_ADDRESS       = "/www/sk-bron.ru/";
public final static String M_MAIN_ADDRESS     = "/www/mobile.sk-bron.ru/";

//АКТУАЛЬНЫЙ СЕРВЕР
private final File connControl = new File("cc.dat");
private static  String FTPlogin = "account";
public  static  String FTPpassword = "D8oo5L8PU6K";
private static  String FTPhost = "sk-bron.ru";
private static final String testUploadFileName = "/www/sk-bron.ru/uploadCheck.txt";
//СИСТЕМНЫЕ ФАЙЛЫ НА ХОСТИНГЕ
private static final String FTPteam = "/www/sk-bron.ru/Fighters/data.dat";
public  static final String FTPuser = "/www/sk-bron.ru/users.dat";
public  static final String FTPvers = "/www/sk-bron.ru/vers.dat";
public  static final String FTPgalP = "/www/sk-bron.ru/galp.dat";
public  static final String FTPgalV = "/www/sk-bron.ru/galv.dat";
public  static final String FTPrglt = "/www/sk-bron.ru/rglt.dat";
public  static final String FTPrule = "/www/sk-bron.ru/rule.dat";
public  static final String FTPnoob = "/www/sk-bron.ru/noob.dat";
public  static final String update  = "/www/sk-bron.ru/updates/update.rar";
//ШАБЛОНЫ ВЕБ-СТРАНИЦ НА ХОСТИНГЕ
private static final String FTPGallDef = "/www/sk-bron.ru/webdef/galleryDef.bron";

private FileInputStream  fis = null;
private FileOutputStream fos = null;

//МЕТОДЫ ПОЛУЧЕНИЯ
public static MyFTPClient getInstace(){
    if(instance == null) instance = new MyFTPClient();
    return instance;
}
private MyFTPClient() {
    client = new FTPClient();
    
    if(connControl.exists()) connControl.delete();

    FTPhost      = options.getHost();
    FTPlogin     = options.getLogin();
    FTPpassword  = options.getPassword();
        
   if(options.isFirewallCutOff()){
        try{
            if(print)System.out.println("Договариваемся с FW.");
            Runtime p = Runtime.getRuntime();
            p.exec("netsh advfirewall set global StatefulFTP disable");
        } catch (IOException ex) {
            if(options.isCatchPrint()) System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(new JPanel(), ex.getMessage(), 
                    "Системная ошибка FTPClient", JOptionPane.ERROR_MESSAGE);
    }}
   else if(print)System.out.println("Игнорируем FW");
}

//СЕТТЕРЫ И ГЕТТЕРЫ
public String    getLogin()             {    return login;       }
public void      setLogin(String login) {    this.login = login; }

//РАБОТА С FTP-СОЕДИНЕНИЕМ
/**Метод осуществляет подключение к FTP-хостингу
     * @param psw пароль для подключения к хостингу
     * @return ноль, если подключение успешно, либо код ошибки, если нет.*/
public int connect(String psw){
    try {
        if(print)System.out.println("Подключаемся к FTP-серверу...");
        client.connect(FTPhost);
        if(!client.isConnected()) return 11;
        client.enterLocalPassiveMode();
        boolean logged = client.login(FTPlogin, psw);
        if(logged) {
            if(print)System.out.println("Успешное подключение!");
            
            return 0;
        }
    } catch (IOException ex) {
        if(print)System.out.println("не удалось подключиться " + ex.getMessage());
        JOptionPane.showMessageDialog(new JPanel(), ex.getMessage(), "Системная ошибка FTPClient", JOptionPane.ERROR_MESSAGE);
        return 1111;
    }
    if(print)System.out.println("Не удалось подключиться к FTP-серверу:\nВозможно, неверные логин или пароль...");
    disconnect();
    if(print)System.out.println("Отключено...");
    return 22;
}

/**Метод проверяет наличие активного соединения с FTP-хостингом и предлагает
* попробовать его восстановить, если соединения нет.
* @return<b>true </b> если соединение в порядке<br> 
<b>false </b> если соединения нет и не удалось его восстановить*/
//public boolean checkConnection(){
// return true;   
//}
/**Метод отключает от FTP-хостинга*/
public void disconnect(){
    if(client.isConnected()){
    try {
        client.disconnect();
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(new JPanel(), "ошибка отключения\n" + 
                ex.getMessage(), "Системная ошибка FTPClient", JOptionPane.ERROR_MESSAGE);
        System.out.println("не удалось отключиться " + ex.getMessage());
    }}
}

///////////////////////////////РАБОТА С ФАЙЛАМИ/////////////////////////////////

//ЗАГРУЗКА НА ХОСТИНГ
/**Метод загрузки любого файла на FTР-хостинг
     * @param file файл для загрузки
     * @param address адрес на хостинге
     * @return <b>true </b> если операция удалась<br> 
     *         <b>false</b> если нет*/
public boolean uploadFile(File file, String address) {
boolean result = false; 
    try {
        fis = new FileInputStream(file);
        client.deleteFile(address);
        result = client.appendFile(address, fis);
        fis.close();
    }catch(FTPConnectionClosedException ex) {
        disconnect();
        connect(options.getPassword());
    }
    catch (IOException ex) {
        JOptionPane.showMessageDialog(new JPanel(), "ошибка при загрузке файла " +
                file.getName()+"\n"+ex.getMessage(), "Системная ошибка FTPClient", JOptionPane.ERROR_MESSAGE);
        if(options.isCatchPrint())System.out.println("IO error " + ex.getMessage());
    }
    return result;
}

/**Метод загужает на хостинг файл с данными о членах команды <b>data.dat</b><br>
* Файл загружается на хостинг по нужному адресу, поэтому, для загрузки <b>data.dat</b>
* стоит использовать этот метод, а не общий {@link #uploadFile(java.io.File, java.lang.String)   }
    * @return <b>true </b> если операция удалась<br> 
    *         <b>false</b> если нет*/
public boolean uploadTeamDataFile(){
    boolean result = false; 
    try {
        fis = new FileInputStream(Team.teamData);
        result = client.storeFile(FTPteam, fis);
        fis.close();
    } catch(FTPConnectionClosedException ex) {
            disconnect();
            connect(options.getPassword());
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(new JPanel(), "ошибка при загрузке файла " +
                "TeamDataFile\n"+ex.getMessage(), "Системная ошибка FTPClient", JOptionPane.ERROR_MESSAGE);
        if(options.isCatchPrint())System.out.println("IO error " + ex.getMessage());
    }
    return result;
}

/**Метод загужает на хостинг файл с данными о разделе "Набор в команду" <b>noob.dat</b><br>
* Файл загружается на хостинг по нужному адресу, поэтому, для загрузки <b>noob.dat</b>
* стоит использовать этот метод, а не общий {@link #uploadFile(java.io.File, java.lang.String)   }
    * @return <b>true </b> если операция удалась<br> 
    *         <b>false</b> если нет*/
public boolean uploadNoobDataFile(){
    boolean result = false; 
    try {
        fis = new FileInputStream(Team.noobData);
        result = client.storeFile(FTPnoob, fis);
        fis.close();
    } catch(FTPConnectionClosedException ex){
        disconnect();
        connect(options.getPassword());
    }catch (IOException ex) {
        JOptionPane.showMessageDialog(new JPanel(), "ошибка при загрузке файла " +
                "NoobDataFile\n"+ex.getMessage(), "Системная ошибка FTPClient", JOptionPane.ERROR_MESSAGE);
        if(options.isCatchPrint())System.out.println("IO error " + ex.getMessage());
    }
    return result;
}

/**Метод загужает на хостинг файлы с данными о фотографиях и видеозаписях галереи 
* <b>galp.dat</b> и <b>galv.dat</b><br>
* Файлы загружается на хостинг по нужному адресу, поэтому, для загрузки <b>galp.dat</b> и <b>galv.dat</b>
* стоит использовать этот метод, а не общий {@link #uploadFile(java.io.File, java.lang.String)   }
* @return <b>true </b> если обе операции удались<br> 
* <b>false </b> если хотя бы одна не удалась*/
public boolean uploadGalleryFile(){
    boolean resultPhoto = false; 
    boolean resultVideo = false; 
    try {
        fis = new FileInputStream(Gallery.galpData);
        resultPhoto = client.storeFile(FTPgalP, fis);
        fis.close();
        
        fis = new FileInputStream(Gallery.galvData);
        resultVideo = client.storeFile(FTPgalV, fis);
        fis.close();
    } catch (FTPConnectionClosedException ex){
        disconnect();
        connect(options.getPassword());
    }
    catch (IOException ex) {
        JOptionPane.showMessageDialog(new JPanel(), "ошибка при загрузке файла " +
                "GallDataFile\n"+ex.getMessage(), "Системная ошибка FTPClient", JOptionPane.ERROR_MESSAGE);
        if(options.isCatchPrint())System.out.println("IO error " + ex.getMessage());
    }
    return resultPhoto & resultVideo;
}

/**Метод загужает на хостинг файл с данными об администраторах программы <b>users.dat</b><br>
* Файл загружается на хостинг по нужному адресу, поэтому, для загрузки <b>users.dat</b>
* стоит использовать этот метод, а не общий {@link #uploadFile(java.io.File, java.lang.String)   }
    * @return <b>true </b> если операция удалась<br> 
    *         <b>false</b> если нет*/
public boolean uploadTeamUserFile(){
    boolean result = false; 
    try {
        fis = new FileInputStream(Team.userData);
        result = client.storeFile(FTPuser, fis);
        fis.close();
    }catch (FTPConnectionClosedException ex) {
        disconnect();
        connect(options.getPassword());
    }
    catch (IOException ex) {
        JOptionPane.showMessageDialog(new JPanel(), "ошибка при загрузке файла " +
                "UserDataFile\n"+ex.getMessage(), "Системная ошибка FTPClient", JOptionPane.ERROR_MESSAGE);
        if(options.isCatchPrint())System.out.println("IO error " + ex.getMessage());
    }
    return result;
}

/**Метод загужает на хостинг файл с данными о разделе "Устав Команды" <b>rglt.dat</b><br>
* Файл загружается на хостинг по нужному адресу, поэтому, для загрузки <b>rglt.dat</b>
* стоит использовать этот метод, а не общий {@link #uploadFile(java.io.File, java.lang.String)   }
    * @return <b>true </b> если операция удалась<br> 
    *         <b>false</b> если нет*/
public boolean uploadRegulationFile(){
    boolean result = false; 
    try {
        if(Team.rgltData.exists()){
            fis = new FileInputStream(Team.rgltData);
            result = client.storeFile(FTPrglt, fis);
            fis.close();
        }
    } catch (FTPConnectionClosedException ex){
        disconnect();
        connect(options.getPassword());
    }
    catch (IOException ex) {
        JOptionPane.showMessageDialog(new JPanel(), "ошибка при загрузке файла " +
                "RgltDataFile\n"+ex.getMessage(), "Системная ошибка FTPClient", JOptionPane.ERROR_MESSAGE);
        if(options.isCatchPrint())System.out.println("IO error " + ex.getMessage());
    }
    return result;
}

/**Метод загужает на хостинг файл с данными о разделе "Правила Страйкбола" <b>rule.dat</b><br>
* Файл загружается на хостинг по нужному адресу, поэтому, для загрузки <b>rule.dat</b>
* стоит использовать этот метод, а не общий {@link #uploadFile(java.io.File, java.lang.String)   }
    * @return <b>true </b> если операция удалась<br> 
    *         <b>false</b> если нет*/
public boolean uploadRulesFile(){
    boolean result = false; 
    try {
        if(Team.ruleData.exists()){
            fis = new FileInputStream(Team.ruleData);
            result = client.storeFile(FTPrule, fis);
            fis.close();
        }
    }catch(FTPConnectionClosedException ex) {
        disconnect();
        connect(options.getPassword());
    }
    catch (IOException ex) {
        JOptionPane.showMessageDialog(new JPanel(), "ошибка при загрузке файла " +
                "RuleDataFile\n"+ex.getMessage(), "Системная ошибка FTPClient", JOptionPane.ERROR_MESSAGE);
        if(options.isCatchPrint())System.out.println("IO error " + ex.getMessage());
    }
    return result;
}

//СКАЧИВАНИЕ С ХОСТИНГА
/**Метод скачивает с хостинга полноразмерный вариант изображения
     * @param name имя изображения*/
public void downloadFullImage(int name){
    try {
        fos = new FileOutputStream("images/temp/gallery/fullsize/" + name + ".jpg");
        client.setFileType(FTP.BINARY_FILE_TYPE);
        if(client.retrieveFile(MAIN_ADDRESS +"img/fullsize/" +name+".jpg" , fos))
            if(print)System.out.println(name+".png успешно загружен");
        fos.close();
    } catch(FTPConnectionClosedException ex) {
        disconnect();
        connect(options.getPassword());
    }
    catch (FileNotFoundException ex) {
        JOptionPane.showMessageDialog(new JPanel(), "FileNotFoundException при "
                + "скачивании файла " + name + " в downloadFullImage(int name)\n"
                +ex.getMessage(), "Системная ошибка FTPClient", JOptionPane.ERROR_MESSAGE);
        if(options.isCatchPrint()) System.out.println(ex.getMessage());
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(new JPanel(), "IOException при "
                + "скачивании файла " + name + " в downloadFullImage(int name)\n"
                +ex.getMessage(), "Системная ошибка FTPClient", JOptionPane.ERROR_MESSAGE);
        if(options.isCatchPrint()) System.out.println(ex.getMessage());
    }
}

/**Метод скачивает с хостинга мини-вариант изображения
     * @param name имя изображения*/
public void downloadMiniImage(int name){
    try {
        fos = new FileOutputStream("images/temp/gallery/" + name + ".png");
        client.setFileType(FTP.BINARY_FILE_TYPE);
        if(client.retrieveFile(MAIN_ADDRESS +"img/" +name+".png" , fos))
            if(print)System.out.println(name+".png успешно загружен");
        fos.close();
    }catch(FTPConnectionClosedException ex) {
        disconnect();
        connect(options.getPassword());
    }
    catch (FileNotFoundException ex) {
        JOptionPane.showMessageDialog(new JPanel(), "FileNotFoundException при "
                + "скачивании файла " + name + " в downloadMiniImage(int name)\n"
                +ex.getMessage(), "Системная ошибка FTPClient", JOptionPane.ERROR_MESSAGE);
        if(options.isCatchPrint()) System.out.println(ex.getMessage());
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(new JPanel(), "IOException при "
                + "скачивании файла " + name + " в downloadMiniImage(int name)\n"
                +ex.getMessage(), "Системная ошибка FTPClient", JOptionPane.ERROR_MESSAGE);
        if(options.isCatchPrint()) System.out.println(ex.getMessage());
    }
}

/**Метод скачивает с хостинга архив с обновлением программы*/
public void downloadUpdate(){
    try {
        fos = new FileOutputStream(Team.updateFile.getPath());
        client.setFileType(FTP.BINARY_FILE_TYPE);
        if(client.retrieveFile(update, fos)){
            if(print)System.out.println("Файл обновления успешно загружен");
            JOptionPane.showMessageDialog(new JPanel(), new UpdatePanel());
        }
        else 
            JOptionPane.showMessageDialog(new JPanel(), "Не удалось скачать обновление. "
                    + "Time to call Maestro :)", "Что-то явно наебнулось...", JOptionPane.ERROR_MESSAGE);
        fos.close();
        
    } catch (FTPConnectionClosedException ex) {
        disconnect();
        connect(options.getPassword()); 
    }
    catch (FileNotFoundException ex) {
        JOptionPane.showMessageDialog(new JPanel(), "FileNotFoundException при "
                + "скачивании файла обновления\n" + ex.getMessage(), 
                "Системная ошибка FTPClient", JOptionPane.ERROR_MESSAGE);
        if(options.isCatchPrint()) System.out.println(ex.getMessage());
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(new JPanel(), "IOException при "
                + "скачивании файла обновления\n" + ex.getMessage(), 
                "Системная ошибка FTPClient", JOptionPane.ERROR_MESSAGE);
        if(options.isCatchPrint()) System.out.println(ex.getMessage());
    }
    
}

/**Метод скачивает с хостинга все файлы данных, необходимых для работы программы, а именно:
 * <ol>
 *      <li><b>data.dat</b>  - содержит информацию о членах команды</li>
 *      <li><b>users.dat</b> - содержит информацию об администраторах программы</li> 
 *      <li><b>vers.dat</b>  - содержит информацию об актуальной версии программы</li>
 *      <li><b>galp.dat</b>  - содержит информацию о фотографиях галереи</li>
 *      <li><b>galv.dat</b>  - содержит информацию о видеозаписях галереи</li>
 *      <li><b>rglt.dat</b>  - содержит информацию о раздулу "Устав Команды"</li>
 *      <li><b>rule.dat</b>  - содержит информацию о разделе "Правила Стайкбола"</li>
 * </ol>*/
public void downloadTeamDataFiles(){
    try {
        fos = new FileOutputStream(Team.teamData.getPath());
        client.setFileType(FTP.BINARY_FILE_TYPE);
        if(client.retrieveFile(FTPteam, fos))
            if(print)System.out.println("DataTeamFile успешно загружен");
        fos.close();
        
        fos = new FileOutputStream(Team.userData.getPath());
        client.setFileType(FTP.BINARY_FILE_TYPE);
        if(client.retrieveFile(FTPuser, fos))
            if(print)System.out.println("DataUserFile успешно загружен");
        fos.close();

        fos = new FileOutputStream(Team.versData.getPath());
        client.setFileType(FTP.BINARY_FILE_TYPE);
        if(client.retrieveFile(FTPvers, fos))
            if(print)System.out.println("DataVersFile успешно загружен");
        fos.close();       
        
        fos = new FileOutputStream(Gallery.galpData.getPath());
        client.setFileType(FTP.BINARY_FILE_TYPE);
        if(client.retrieveFile(FTPgalP, fos))
            if(print)System.out.println("DataGalPFile успешно загружен");
        fos.close();

        fos = new FileOutputStream(Gallery.galvData.getPath());
        client.setFileType(FTP.BINARY_FILE_TYPE);
        if(client.retrieveFile(FTPgalV, fos))
            if(print)System.out.println("DataGalVFile успешно загружен");
        
        fos = new FileOutputStream(Team.rgltData.getPath());
        client.setFileType(FTP.BINARY_FILE_TYPE);
        if(client.retrieveFile(FTPrglt, fos))
            if(print)System.out.println("DataRgltFile успешно загружен");
        fos.close();
        
        fos = new FileOutputStream(Team.ruleData.getPath());
        client.setFileType(FTP.BINARY_FILE_TYPE);
        if(client.retrieveFile(FTPrule, fos))
            if(print)System.out.println("DataRuleFile успешно загружен");
        fos.close();
        
        fos = new FileOutputStream(Team.noobData.getPath());
        client.setFileType(FTP.BINARY_FILE_TYPE);
        if(client.retrieveFile(FTPnoob, fos))
            if(print)System.out.println("DataNoobFile успешно загружен");
        fos.close();
    } catch (FTPConnectionClosedException ex){
        disconnect();
        connect(options.getPassword());
    }
    catch (IOException ex) {
        JOptionPane.showMessageDialog(new JPanel(), "IOException при "
                + "скачивании системных файлов\n" + ex.getMessage(), 
                "Системная ошибка FTPClient", JOptionPane.ERROR_MESSAGE);
        if(options.isCatchPrint())System.out.println("downloading error " + ex.getMessage());
    }
}

//УДАЛЕНИЕ С ХОСТИНГА
/**Метод удаляет файл на хостинге
     * @param pathname имя файла для удаления
     * @return <b>true </b> если операция удалась<br> 
     *         <b>false</b> если нет*/
public boolean deleteFile(String pathname){
    try {
        return client.deleteFile(pathname);
    } catch (FTPConnectionClosedException ex){
        disconnect();
        connect(options.getPassword());
    }
    catch (IOException ex) {
        if(options.isCatchPrint()) System.out.println(ex.getMessage());
    }
    return false;
}

public void retriveFile(File file, String link){
    try {
        fos = new FileOutputStream(file);
        client.setFileType(FTP.BINARY_FILE_TYPE);
        client.retrieveFile(link, fos);
        fos.close();
    }catch (FTPConnectionClosedException ex){
        disconnect();
        connect(options.getPassword());
    } catch (FileNotFoundException ex) {
        Logger.getLogger(MyFTPClient.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(MyFTPClient.class.getName()).log(Level.SEVERE, null, ex);
    }
}

/**Метод удаляет с хостинга страницу члена команды и все связанные с ней фотографии<br>
 * Именно поэтому, если надо удалить старницу бойца целиком, стоит использовать этот метод, а
 * не общий {@link #deleteFile(java.lang.String)}
     * @param link ссылка на страницу члена команды
     * @param competent флаг полнлправности удаляемого члена команды:<br>
     *     &nbsp&nbsp&nbsp<b>true</b> - если член команды является полноправным бойцом<br>
     *     &nbsp&nbsp&nbsp<b>false</b> - если член команды является кандидиатом<br>
     */
public void deletePage(String link, boolean  competent){
   
    try {
        client.deleteFile(FIGHTERS_ADDRESS +link +".html");
        client.deleteFile(MAIN_ADDRESS + "img/Fighters/"+ link+".jpg");
        client.deleteFile(MAIN_ADDRESS + "img/Fighters/fullsize/" + link+".jpg");
        client.deleteFile(MAIN_ADDRESS + "img/Fighters/mini/"+ link +".png");
        
        client.deleteFile(M_FIGHTERS_ADDRESS + link +".html");
        client.deleteFile(M_MAIN_ADDRESS + "img/fighters/"+ link+".png");
        client.deleteFile(M_MAIN_ADDRESS + "img/fighters/fullsize/" + link+".png");
        
        if(competent){
            try {
                client.deleteFile(MAIN_ADDRESS + "img/Fighters/" + link + "1.png");
                client.deleteFile(MAIN_ADDRESS + "img/Fighters/" + link + "2.png");
                client.deleteFile(MAIN_ADDRESS + "img/Fighters/" + link + "3.png");
                client.deleteFile(MAIN_ADDRESS + "img/Fighters/fullsize/" + link + "1.jpg");
                client.deleteFile(MAIN_ADDRESS + "img/Fighters/fullsize/" + link + "2.jpg");
                client.deleteFile(MAIN_ADDRESS + "img/Fighters/fullsize/" + link + "3.jpg");
                
                client.deleteFile(M_MAIN_ADDRESS + "img/fighters/fullsize/" + link + "1.png");
                client.deleteFile(M_MAIN_ADDRESS + "img/fighters/fullsize/" + link + "2.png");
                client.deleteFile(M_MAIN_ADDRESS + "img/fighters/fullsize/" + link + "3.png");
            } catch (IOException ex) {
                if(options.isCatchPrint()) System.out.println(ex.getMessage());
            }
        }
    } catch (FTPConnectionClosedException ex){
        disconnect();
        connect(options.getPassword());
    }
    catch (IOException ex) {
        if(options.isCatchPrint()) System.out.println(ex.getMessage());
    }
}}

