
package javaapplication58;

import com.sun.glass.events.KeyEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Locale;

public class Team {
    
private Team() {}

private static final Version version = new Version(4, 2, 0);
public static Regulation REGULATION;
public static Rules RULES;
public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", new Locale("ru", "RU"));
public static final int MAX_MEMBERS = 999;
private static boolean print = SystemOptions.getInstance().isDebugPrint();
private static LinkedList<Member> list = new LinkedList<>();
private static LinkedList<User> users = new LinkedList<>();
private static User user;
private static SystemOptions options = SystemOptions.getInstance();
public static File teamData   = new File("data.dat");
public static File userData   = new File("users.dat");
public static File versData   = new File("vers.dat");
public static File rgltData   = new File("rglt.dat");
public static File ruleData   = new File("rules.dat");
public static File noobData   = new File("noobs.dat");
public static File updateFile = new File("UPDATE.rar");
private static ArrayList<String> NOOBIES;
public static final char [] RUSSIAN_LETTERS ={
        'А','а','Б','б','В','в','Г','г','Д','д','Е','е','Ё','ё','Ж','ж',
        'З','з','И','и','Й','й','К','к','Л','л','М','м','Н','н','О','о',
        'П','п','Р','р','С','с','Т','т','У','у','Ф','ф','Х','х','Ц','ц',
        'Ч','ч','Ш','ш','Щ','щ','Ъ','ъ','Ы','ы','Ь','ь','Э','э','Ю','ю',
        'Я','я', ' ', KeyEvent.VK_BACKSPACE};


    /**
     * @return возвращает текущюю версию сборки программы*/
    public static Version getVersion() {
        return version;
    }

    /**Метод назначает пользователя-администратора, под чьим аккаунтом был 
     произведен вход в приложение
     * @param user пользователь-администратор, которго надо назначить активным*/
    public static void setActiveUser(User user) {
        if(options.isDebugPrint())System.out.println("Выполнен вход пользователем "  +user.getLogin());
        Team.user = user;
    }
    
    /** 
    Метод получения активного администратора, под чьей учетной записью произведен вход.
    @return активный пользователь-администратор */
    public static User getActiveUser() {
        return user;
        
    }
    
    /** Метод получения пользователя администратора из списка пользователей администраторов по логину
    @param name логин администратора(позвыной транслитом)
    @return пользователь-администратор */
    public static  User getUserFromList(String name){
        
        for (User u : users) {
            if(u.getLogin().equals(name)) return u;
        }
        if(print)System.out.println("Администратор " + name + " не найден, все ОК");
        return null;
    }
      
    /** Метод получения коллекции пользователей-администраторов
    @return коллекция пользователей-администраторов    */   
    public static LinkedList<User> getUsers(){
        return users;
    }
     
    /** Метод добавляет пользователя-администратора в коллекцию, проверяет, что такого 
     * пользователя еще нет и вызывает метод {@link #saveUsersToFile()  }
    @param user объект класса User, которого надо добавить в коллекцию администраторов    */  
    public static void addUser(User user){
        for (User u : users) {
            if(u.getLogin().equals(user.getLogin()))
                return;
        }
        users.add(user);
        saveUsersToFile();
            
    }
    
    /** Метод удаляет пользователя-администратора из коллекции, и вызывает метод {@link #saveUsersToFile()  }
    @param user объект класса User, которого надо удалить из коллекции администраторов    */  
    public static void removeUser(User user){
        users.remove(user);
        saveUsersToFile();
    }
    

   
    /** Метод сохраняет все данные о членах команды в локальный файл data.dat */  
    public static void saveTeamToFile(){
        try {
            if(!teamData.exists()) teamData.createNewFile();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(teamData))) { 
                oos.writeObject(list);
            }

        } catch (IOException ex) {
            if(options.isCatchPrint()) System.out.println("НЕ УДАЛОСЬ СОХРАНИТЬ КОМАНДУ В ФАЙЛ " + ex.getMessage());
        }}

    /** Метод сохраняет все данные о пользователях-администраторах в локальный файл users.dat */  
    public static void saveUsersToFile(){
        try {
            if(!userData.exists()) userData.createNewFile();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userData))) { 
                oos.writeObject(users);
            }

        } catch (IOException ex) {
           if(options.isCatchPrint())  System.out.println("НЕ УДАЛОСЬ СОХРАНИТЬ В ФАЙЛ " + ex.getMessage());
        }}

    public static void saveNoobiesToFile(){
        
        try {
            if(!noobData.exists()) noobData.createNewFile();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(noobData))) { 
                oos.writeObject(NOOBIES);
            }

        } catch (IOException ex) {
           if(options.isCatchPrint())  System.out.println("НЕ УДАЛОСЬ СОХРАНИТЬ В ФАЙЛ " + ex.getMessage());
        }
    }
    
    /** Метод присваивает значение статической коллекции пользователей-администраторов users,
     * считывая его из локального файла users.dat
     * @return возвращает заполненную коллекцию users     */  
    public static LinkedList<User> loadUsersFromFile(){

        ObjectInputStream ois = null;
        try {

        ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(userData)));
        } catch (FileNotFoundException ex) { System.out.println("FileNotFoundException " + ex.getMessage());
        } catch (IOException ex) { System.out.println("IOException " + ex.getMessage());}

        try {
            users = (LinkedList<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
          if(options.isCatchPrint())   System.out.println(ex.getMessage());
        }
        return users;
    }

    /** Метод присваивает значение статической коллекции сленов команды list,
     * считывая его из локального файла data.dat
     * @return возвращает заполненную коллекцию list     */  
    public static LinkedList<Member> loadTeamFromFile(){

        ObjectInputStream ois = null;
        try {
        ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(teamData)));
        } catch (FileNotFoundException ex) { if(options.isCatchPrint()) System.out.println("FileNotFoundException " + ex.getMessage());
        } catch (IOException ex) { if(options.isCatchPrint()) System.out.println("IOException " + ex.getMessage());}


        try {
            list = (LinkedList<Member>) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            if(options.isCatchPrint()) System.out.println(ex.getMessage());
        }
        return list;
    }

    public static void setNoobiesWanted(boolean wanted){
        if(NOOBIES == null) NOOBIES = new ArrayList<>();
        if(wanted) NOOBIES.set(0, "true");
        else NOOBIES.set(0, "false");
    }
    
    public static void setNoobNoText(String text){
        if(NOOBIES == null) loadNoobFile();
        NOOBIES.set(1, text);
    } 
        
    public static void setNoobYesText(String text){
        if(NOOBIES == null) loadNoobFile();
        NOOBIES.set(2, text);
    }
        
    public static void setNoobVkLink(String link){
        if(NOOBIES == null) loadNoobFile();
        NOOBIES.set(3, link);
    }
    
    public static void setNoobLinkShowing(boolean isShowing){
        if(NOOBIES == null) loadNoobFile();
        if(isShowing) NOOBIES.set(4, "true");
        else NOOBIES.set(4, "false");
    }
    
   
    
    public static boolean isNoobiesWanted(){
        if(NOOBIES == null) loadNoobFile();
        return(NOOBIES.get(0).equals("true"));
    }
    
    public static String getNoobNoText(){
        if(NOOBIES == null) loadNoobFile();
        return(NOOBIES.get(1));
    }
    
    public static String getNoobYesText(){
        if(NOOBIES == null) loadNoobFile();
        return(NOOBIES.get(2));
    }
        
    public static String getNoobVkLink(){
        if(NOOBIES == null) loadNoobFile();
        return(NOOBIES.get(3));
    }
    
    public static boolean isNoobLinkShowing(){
        if(NOOBIES == null) loadNoobFile();
        return(NOOBIES.get(4).equals("true"));
    }
    
    private static ArrayList<String> loadNoobFile(){

        ObjectInputStream ois = null;
        try {
        ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(noobData)));
        } catch (FileNotFoundException ex) { if(options.isCatchPrint()) System.out.println("FileNotFoundException " + ex.getMessage());
        } catch (IOException ex) { if(options.isCatchPrint()) System.out.println("IOException " + ex.getMessage());}


        try {
            NOOBIES = (ArrayList<String>) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            if(options.isCatchPrint()) System.out.println(ex.getMessage());
        }
        return NOOBIES;
    }
    
    /** Метод возвращает объект класса Member из коллекции членов команды list, 
     * выбирая его по позывному
     * @param callSign позываной члена команды, которого хотим найти
     * @return возвращает члена команды с заданным позывным или null, если члена команды 
     * с таким позывным не существует     */  
    public static Member getMember(String callSign){
        for (Member m : list) {
            if(m.getCallSign().equals(callSign)) return m;
            }
        if(options.isDebugPrint()) System.out.println("Боец с позывным "+ callSign + " не найден."
                + " Проверьте правильность ввода и повторите попытку");
        return null;
    }

    /** Метод сортирует коллекцию с членами команда list сначала по статусу\должности, а
     * потом по дате, и вызывает метод {@link #saveTeamToFile()      */  
    public static void sort(){
        Collections.sort(list, 
            new idComparator().thenComparing(new dateComparator()));
        saveTeamToFile();
    }
  
    /** Метод возвращает объект класса Member, являющегося командиром команды
     @return возвращает объект класса Member, являющегося командиром команды, или
     * null, если командир не назначен     */  
    public static Member getCommander(){
        for (Member member : list) {
            if(member.getId() == 1) return member;
        }
        return null;
    }

    /** Метод возвращает объект класса Member, являющегося старшиной команды
     @return возвращает объект класса Member, являющегося старшиной команды, или
     * null, если командир не назначен     */  
    public static Member getSergeant(){
        for (Member member : list) {
            if(member.getId() == 22) return member;
        }
        return null;
    }
    
    /** Метод удаляет члена команды из коллекции членов команды list и вызывает 
      * метод {@link #sort() 
      * @param delSign строка, содержащая позывной члена команды, которого надо удалить*/  
    public static void removeMember(String delSign){
        boolean deleted = false;
        for (Member m : list) {
            if(m.getCallSign().equals(delSign)){
                if(print)System.out.println(m.getCallSign()+ " отчислен из команды");
                list.remove(m);
                deleted = true;
                sort();
                break;
            }
        }
        if(!deleted && print)System.out.println("Боец с позывным " + delSign +" не найден. "
                + "Проверьте правильность ввода и повторите попытку");
    }

    /** Метод получения списка всех членов команды.
     * @return статическую коллекию членой команды list     */
    public static LinkedList<Member> getTeamMembers() {
        return list;
    }

    /** Метод транслитирует полученную строку. Заменяет русские буквы английскими
     * с учетом регистра, а английские оставляет неизменными. Игнорирует другие 
     * символы, кроме пробелов, заменяя их симовлом '_'
     * @param string  строка, которую необходимо транслитировать
     * @return транлитированная строка   */
    public static String tranlite(String string){
        char[] temp = string.toCharArray();
        StringBuilder stb = new StringBuilder(string);
        stb.delete(0, string.length());
        for (char c : temp) {
            switch (c){
                case 'а': stb.append('a'); break;
                case 'б': stb.append('b'); break;
                case 'в': stb.append('v'); break;
                case 'г': stb.append('g'); break;
                case 'д': stb.append('d'); break;
                case 'е': 
                case 'э':
                case 'ё': stb.append('e'); break;
                case 'ж': stb.append("zh"); break;
                case 'з': stb.append('z'); break;
                case 'и': 
                case 'й': stb.append('i'); break;
                case 'к': stb.append('k'); break;
                case 'л': stb.append('l'); break;
                case 'м': stb.append('m'); break;
                case 'н': stb.append('n'); break;
                case 'о': stb.append('o'); break;
                case 'п': stb.append('p'); break;
                case 'р': stb.append('r'); break;
                case 'с': stb.append('s'); break;
                case 'т': stb.append('t'); break;
                case 'у': stb.append('u'); break;
                case 'ф': stb.append('f'); break;
                case 'х': stb.append('h'); break;
                case 'ц': stb.append('c'); break;
                case 'ч': stb.append("ch"); break;
                case 'ш': 
                case 'щ': stb.append("sh"); break;
                case 'ы': stb.append('y'); break;
                case 'ю': stb.append("yu"); break;
                case 'я': stb.append("ya"); break;

                case 'А': stb.append('A'); break;
                case 'Б': stb.append('B'); break;
                case 'В': stb.append('V'); break;
                case 'Г': stb.append('G'); break;
                case 'Д': stb.append('D'); break;
                case 'Е': 
                case 'Э':
                case 'Ё': stb.append('E'); break;
                case 'Ж': stb.append("Zh"); break;
                case 'З': stb.append('Z'); break;
                case 'И': 
                case 'Й': stb.append('I'); break;
                case 'К': stb.append('K'); break;
                case 'Л': stb.append('L'); break;
                case 'М': stb.append('M'); break;
                case 'Н': stb.append('N'); break;
                case 'О': stb.append('O'); break;
                case 'П': stb.append('P'); break;
                case 'Р': stb.append('R'); break;
                case 'С': stb.append('S'); break;
                case 'Т': stb.append('T'); break;
                case 'У': stb.append('U'); break;
                case 'Ф': stb.append('F'); break;
                case 'Х': stb.append('H'); break;
                case 'Ц': stb.append('C'); break;
                case 'Ч': stb.append("Ch"); break;
                case 'Ш': 
                case 'Щ': stb.append("Sh"); break;
                case 'Ы': stb.append('Y'); break;
                case 'Ю': stb.append("Yu"); break;
                case 'Я': stb.append("Ya"); break;

                case 'a': stb.append('a'); break;
                case 'b': stb.append('b'); break;
                case 'c': stb.append('c'); break;
                case 'd': stb.append('d'); break;
                case 'e': stb.append('e'); break;
                case 'f': stb.append('f'); break;
                case 'g': stb.append('g'); break;
                case 'h': stb.append('h'); break;
                case 'i': stb.append('i'); break;
                case 'j': stb.append('j'); break;
                case 'k': stb.append('k'); break;
                case 'l': stb.append('l'); break;
                case 'm': stb.append('m'); break;
                case 'n': stb.append('n'); break;
                case 'o': stb.append('o'); break;
                case 'p': stb.append('p'); break;
                case 'q': stb.append('q'); break;
                case 'r': stb.append('r'); break;
                case 's': stb.append('s'); break;
                case 't': stb.append('t'); break;
                case 'u': stb.append('u'); break;
                case 'v': stb.append('v'); break;
                case 'w': stb.append('w'); break;
                case 'x': stb.append('x'); break;
                case 'y': stb.append('y'); break;
                case 'z': stb.append('z'); break;

                case 'A': stb.append('A'); break;
                case 'B': stb.append('B'); break;
                case 'C': stb.append('C'); break;
                case 'D': stb.append('D'); break;
                case 'E': stb.append('E'); break;
                case 'F': stb.append('F'); break;
                case 'G': stb.append('G'); break;
                case 'H': stb.append('H'); break;
                case 'I': stb.append('I'); break;
                case 'J': stb.append('J'); break;
                case 'K': stb.append('K'); break;
                case 'L': stb.append('L'); break;
                case 'M': stb.append('M'); break;
                case 'N': stb.append('N'); break;
                case 'O': stb.append('O'); break;
                case 'P': stb.append('P'); break;
                case 'Q': stb.append('Q'); break;
                case 'R': stb.append('R'); break;
                case 'S': stb.append('S'); break;
                case 'T': stb.append('T'); break;
                case 'U': stb.append('U'); break;
                case 'V': stb.append('V'); break;
                case 'W': stb.append('W'); break;
                case 'X': stb.append('X'); break;
                case 'Y': stb.append('Y'); break;
                case 'Z': stb.append('Z'); break;

                case ' ': stb.append('_'); break;
            }}
        return stb.toString();
    }
    
    /** Метод проверяет введенный с клавиатуры символ. Если символ ангилийский,
     * метод заменит его на русский так, как будто ввод производится с русской, 
     * а не с английской раскладки клавиатуры. Если введен символ пробела, метод
     заставит проигнорировать ввод */  
    public static void eliminateEnglishLetters(java.awt.event.KeyEvent evt){
        boolean charIsOk = false;
        for (char c : RUSSIAN_LETTERS) 
            if(evt.getKeyChar() == c) charIsOk = true;
        if(!charIsOk) {
            switch(evt.getKeyChar()){
                case 'f':case 'F':
                    evt.setKeyChar('а');
                    break;
                case ',':case '<':
                    evt.setKeyChar('б');
                    break;
                case 'd':case 'D':
                    evt.setKeyChar('в');
                    break;
                case 'u':case 'U':
                    evt.setKeyChar('г');
                    break;
                case 'l':case 'L':
                    evt.setKeyChar('д');
                    break;
                case 't':case 'T':
                    evt.setKeyChar('е');
                    break;
                case '`':case '~':
                    evt.setKeyChar('ё');
                    break;
                case ';':case ':':
                    evt.setKeyChar('ж');
                    break;
                case 'p':case 'P':
                    evt.setKeyChar('з');
                    break;
                case 'b':case 'B':
                    evt.setKeyChar('и');
                    break;
                case 'q':case 'Q':
                    evt.setKeyChar('й');
                    break;
                case 'r':case 'R':
                    evt.setKeyChar('к');
                    break;
                case 'k':case 'K':
                    evt.setKeyChar('л');
                    break;
                case 'v':case 'V':
                    evt.setKeyChar('м');
                    break;
                case 'y':case 'Y':
                    evt.setKeyChar('н');
                    break;
                case 'j':case 'J':
                    evt.setKeyChar('о');
                    break;
                case 'g':case 'G':
                    evt.setKeyChar('п');
                    break;
                case 'h':case 'H':
                    evt.setKeyChar('р');
                    break;
                case 'c':case 'C':
                    evt.setKeyChar('с');
                    break;
                case 'n':case 'N':
                    evt.setKeyChar('т');
                    break;
                case 'e':case 'E':
                    evt.setKeyChar('у');
                    break;
                case 'a':case 'A':
                    evt.setKeyChar('ф');
                    break;
                case '[':case '{':
                    evt.setKeyChar('х');
                    break;
                case 'w':case 'W':
                    evt.setKeyChar('ц');
                    break;
                case 'x':case 'X':
                    evt.setKeyChar('ч');
                    break;
                case 'i':case 'I':
                    evt.setKeyChar('ш');
                    break;
                case 'o':case 'O':
                    evt.setKeyChar('щ');
                    break;
                case ']':case '}':
                    evt.setKeyChar('ъ');
                    break;
                case 's':case 'S':
                    evt.setKeyChar('ы');
                    break;
                case 'm':case 'M':
                    evt.setKeyChar('ь');
                    break;
                case '\'':case '\"':
                    evt.setKeyChar('э');
                    break;
                case '.':case '>':
                    evt.setKeyChar('ю');
                    break;
                case 'z':case 'Z':
                    evt.setKeyChar('я');
                    break;
                default:
                    evt.consume();   
                    break;
            }
        }
     if(evt.getKeyChar() == ' ') evt.consume();   
    }
    

    /**Класс, обеспечивающий сортировку коллекции объектов класса Member
     по статусу/должности */
    static class idComparator implements Comparator<Member>{
        public int compare(Member m1, Member m2) {
            return m1.getId()-m2.getId();
    }}

    /**Класс, обеспечивающий сортировку коллекции объектов класса Member
     по дате */
    static class dateComparator implements Comparator<Member>{
        public int compare(Member m1, Member m2) {
        if(m1.isCompetent() || m2.isCompetent())
            return (m1.getDateCompetent().compareTo(m2.getDateCompetent()));
        else 
            return (m1.getDateCandidate().compareTo(m2.getDateCandidate()));
        }}

}