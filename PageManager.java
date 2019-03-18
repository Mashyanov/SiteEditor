package javaapplication58;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
/**Класс-обработчик html-страниц
 *
 * @author Александр Машьянов, mashyanov1987@gmail.com
 */
public class PageManager {
    SystemOptions options = SystemOptions.getInstance();
    private final  MyFTPClient client = MyFTPClient.getInstace();
    private final String lineSeparator = System.getProperty("line.separator");
    private String candidates, competents, reservs, candidatesMob, 
            competentsMob, reservsMob;
    private FileWriter fw;
    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.YYYY", Locale.ENGLISH);

    private final File doneFile = new File("temp/done.bron");
    private final File ruleFile = new File("temp/rules.txt");
    private final File rgltFile = new File("temp/ustav.txt");
    
    private LinkedList<String>       list    = new LinkedList<>();
    private LinkedList<GalleryImage> gallery = new LinkedList<>();
    private LinkedList<GalleryVideo> videos  = new LinkedList<>();
    
    public PageManager() {}
    /**Метод заменяет в тексте объявления о наборе новых членов команды все упоминания
     бойцов, снаряжения, вооружения, устава и правил гиперссылками на соотвествующие
     страницы сайта */
    private String editNoobiesLinks(String toEdit){
        toEdit = toEdit.replaceAll("боец",  "<a href=\"fighters.html\">боец</a>");
        toEdit = toEdit.replaceAll("бойцы", "<a href=\"fighters.html\">бойцы</a>");
        toEdit = toEdit.replaceAll("Боец",  "<a href=\"fighters.html\">Боец</a>");
        toEdit = toEdit.replaceAll("Бойцы", "<a href=\"fighters.html\">Бойцы</a>");
        toEdit = toEdit.replaceAll("БОЕЦ",  "<a href=\"fighters.html\">БОЕЦ</a>");
        toEdit = toEdit.replaceAll("БОЙЦЫ", "<a href=\"fighters.html\">БОЙЦЫ</a>");
        
        toEdit = toEdit.replaceAll("бойца",  "<a href=\"fighters.html\">бойца</a>");
        toEdit = toEdit.replaceAll("Бойца",  "<a href=\"fighters.html\">Бойца</a>");
        toEdit = toEdit.replaceAll("БОЙЦА",  "<a href=\"fighters.html\">БОЙЦА</a>");
        toEdit = toEdit.replaceAll("бойцов", "<a href=\"fighters.html\">бойцов</a>");
        toEdit = toEdit.replaceAll("Бойцов", "<a href=\"fighters.html\">Бойцов</a>");
        toEdit = toEdit.replaceAll("БОЙЦОВ", "<a href=\"fighters.html\">БОЙЦОВ</a>");
        
        toEdit = toEdit.replaceAll("бойцу",  "<a href=\"fighters.html\">бойцу</a>");
        toEdit = toEdit.replaceAll("Бойцу",  "<a href=\"fighters.html\">Бойцу</a>");
        toEdit = toEdit.replaceAll("БОЙЦУ",  "<a href=\"fighters.html\">БОЙЦУ</a>");
        toEdit = toEdit.replaceAll("бойцам", "<a href=\"fighters.html\">бойцам</a>");
        toEdit = toEdit.replaceAll("Бойцам", "<a href=\"fighters.html\">Бойцам</a>");
        toEdit = toEdit.replaceAll("БОЙЦАМ", "<a href=\"fighters.html\">БОЙЦАМ</a>");
        
        toEdit = toEdit.replaceAll("бойцом",  "<a href=\"fighters.html\">бойцом</a>");
        toEdit = toEdit.replaceAll("Бойцом",  "<a href=\"fighters.html\">Бойцом</a>");
        toEdit = toEdit.replaceAll("БОЙЦОМ",  "<a href=\"fighters.html\">БОЙЦОМ</a>");
        toEdit = toEdit.replaceAll("бойцами", "<a href=\"fighters.html\">бойцами</a>");
        toEdit = toEdit.replaceAll("Бойцами", "<a href=\"fighters.html\">Бойцами</a>");
        toEdit = toEdit.replaceAll("БОЙЦАМИ", "<a href=\"fighters.html\">БОЙЦАМИ</a>");
        
        toEdit = toEdit.replaceAll("бойце",  "<a href=\"fighters.html\">бойце</a>");
        toEdit = toEdit.replaceAll("Бойце",  "<a href=\"fighters.html\">Бойце</a>");
        toEdit = toEdit.replaceAll("БОЙЦЕ", "<a href=\"fighters.html\">БОЙЦЕ</a>");
        toEdit = toEdit.replaceAll("бойцах", "<a href=\"fighters.html\">бойцах</a>");
        toEdit = toEdit.replaceAll("Бойцах", "<a href=\"fighters.html\">Бойцах</a>");
        toEdit = toEdit.replaceAll("БОЙЦАХ", "<a href=\"fighters.html\">БОЙЦАХ</a>");
        
        toEdit = toEdit.replaceAll("снаряжение", "<a href=\"snaryaga.html\">снаряжение</a>");
        toEdit = toEdit.replaceAll("Снаряжение", "<a href=\"snaryaga.html\">Снаряжение</a>");
        toEdit = toEdit.replaceAll("СНАРЯЖЕНИЕ", "<a href=\"snaryaga.html\">СНАЯРЖЕНИЕ</a>");

        toEdit = toEdit.replaceAll("снаряжения", "<a href=\"snaryaga.html\">снаряжения</a>");
        toEdit = toEdit.replaceAll("Снаряжения", "<a href=\"snaryaga.html\">Снаряжения</a>");
        toEdit = toEdit.replaceAll("СНАРЯЖЕНИЯ", "<a href=\"snaryaga.html\">СНЯРЯЖЕНИЯ</a>");
        
        toEdit = toEdit.replaceAll("снаряжению", "<a href=\"snaryaga.html\">снаряжению</a>");
        toEdit = toEdit.replaceAll("Снаряжению", "<a href=\"snaryaga.html\">Снаряжению</a>");
        toEdit = toEdit.replaceAll("СНАРЯЖЕНИЮ", "<a href=\"snaryaga.html\">СНАРЯЖЕНИЮ</a>");
        
        toEdit = toEdit.replaceAll("снаряжением", "<a href=\"snaryaga.html\">снаряжением</a>");
        toEdit = toEdit.replaceAll("Снаряжением", "<a href=\"snaryaga.html\">Снаряжением</a>");
        toEdit = toEdit.replaceAll("СНАРЯЖЕНИЕМ", "<a href=\"snaryaga.html\">СНАРЯЖЕНИЕМ</a>");
        
        toEdit = toEdit.replaceAll("снаряжении", "<a href=\"snaryaga.html\">снаряжении</a>");
        toEdit = toEdit.replaceAll("Снаряжении", "<a href=\"snaryaga.html\">Снаряжении</a>");
        toEdit = toEdit.replaceAll("СНАРЯЖЕНИИ", "<a href=\"snaryaga.html\">СНАРЯЖЕНИИ</a>");
        
        toEdit = toEdit.replaceAll("вооружение", "<a href=\"guns.html\">вооружение</a>");
        toEdit = toEdit.replaceAll("Вооружение", "<a href=\"guns.html\">Вооружение</a>");
        toEdit = toEdit.replaceAll("ВООРУЖЕНИЕ", "<a href=\"guns.html\">ВООРУЖЕНИЕ</a>");
        
        toEdit = toEdit.replaceAll("вооружения", "<a href=\"guns.html\">вооружения</a>");
        toEdit = toEdit.replaceAll("Вооружения", "<a href=\"guns.html\">Вооружения</a>");
        toEdit = toEdit.replaceAll("ВООРУЖЕНИЯ", "<a href=\"guns.html\">ВООРУЖЕНИЯ</a>");
        
        toEdit = toEdit.replaceAll("вооружению", "<a href=\"guns.html\">вооружению</a>");
        toEdit = toEdit.replaceAll("Вооружению", "<a href=\"guns.html\">Вооружению</a>");
        toEdit = toEdit.replaceAll("ВООРУЖЕНИЮ", "<a href=\"guns.html\">ВООРУЖЕНИЮ</a>");
        
        toEdit = toEdit.replaceAll("вооружением", "<a href=\"guns.html\">вооружением</a>");
        toEdit = toEdit.replaceAll("Вооружением", "<a href=\"guns.html\">Вооружением</a>");
        toEdit = toEdit.replaceAll("ВООРУЖЕНИЕМ", "<a href=\"guns.html\">ВООРУЖЕНИЕМ</a>");
        
        toEdit = toEdit.replaceAll("вооружении", "<a href=\"guns.html\">вооружении</a>");
        toEdit = toEdit.replaceAll("Вооружении", "<a href=\"guns.html\">Вооружении</a>");
        toEdit = toEdit.replaceAll("ВООРУЖЕНИИ", "<a href=\"guns.html\">ВООРУЖЕНИИ</a>");
        
        toEdit = toEdit.replaceAll("устав ", "<a href=\"ustav.html\">Устав</a> ");
        toEdit = toEdit.replaceAll("Устав ", "<a href=\"ustav.html\">Устав</a> ");
        toEdit = toEdit.replaceAll("УСТАВ ", "<a href=\"ustav.html\">УСТАВ</a> ");
        
        toEdit = toEdit.replaceAll("устава", "<a href=\"ustav.html\">Устава</a> ");
        toEdit = toEdit.replaceAll("Устава", "<a href=\"ustav.html\">Устава</a> ");
        toEdit = toEdit.replaceAll("УСТАВА", "<a href=\"ustav.html\">УСТАВА</a> ");
        
        toEdit = toEdit.replaceAll("уставу", "<a href=\"ustav.html\">Уставу</a>");
        toEdit = toEdit.replaceAll("Уставу", "<a href=\"ustav.html\">Уставу</a>");
        toEdit = toEdit.replaceAll("УСТАВУ", "<a href=\"ustav.html\">УСТАВУ</a> ");
        
        toEdit = toEdit.replaceAll("уставом", "<a href=\"ustav.html\">Уставом</a>");
        toEdit = toEdit.replaceAll("Уставом", "<a href=\"ustav.html\">Уставом</a>");
        toEdit = toEdit.replaceAll("УСТАВОМ", "<a href=\"ustav.html\">УСТАВОМ</a> ");
        
        toEdit = toEdit.replaceAll("уставе", "<a href=\"ustav.html\">Уставе</a>");
        toEdit = toEdit.replaceAll("Уставе", "<a href=\"ustav.html\">Уставе</a>");
        toEdit = toEdit.replaceAll("УСТАВЕ", "<a href=\"ustav.html\">УСТАВЕ</a> ");
        
        toEdit = toEdit.replaceAll("правила ", "<a href=\"rules.html\">правила</a> ");
        toEdit = toEdit.replaceAll("Правила ", "<a href=\"rules.html\">Правила</a> ");
        toEdit = toEdit.replaceAll("ПРАВИЛА ", "<a href=\"rules.html\">ПРАВИЛА</a> ");
        
        toEdit = toEdit.replaceAll("правил ", "<a href=\"rules.html\">правил</a> ");
        toEdit = toEdit.replaceAll("Правил ", "<a href=\"rules.html\">Правил</a> ");
        toEdit = toEdit.replaceAll("ПРАВИЛ ", "<a href=\"rules.html\">ПРАВИЛ</a> ");
        
        toEdit = toEdit.replaceAll("правилам ", "<a href=\"rules.html\">правилам</a> ");
        toEdit = toEdit.replaceAll("Правилам ", "<a href=\"rules.html\">Правилам</a> ");
        toEdit = toEdit.replaceAll("ПРАВИЛАМ ", "<a href=\"rules.html\">ПРАВИЛАМ</a> ");
        
        toEdit = toEdit.replaceAll("правилами", "<a href=\"rules.html\">правилами</a>");
        toEdit = toEdit.replaceAll("Правилами", "<a href=\"rules.html\">Правилами</a>");
        toEdit = toEdit.replaceAll("ПРАВИЛАМИ", "<a href=\"rules.html\">ПРАВИЛАМИ</a>");
        
        toEdit = toEdit.replaceAll("правилах", "<a href=\"rules.html\">правилах</a>");
        toEdit = toEdit.replaceAll("Правилах", "<a href=\"rules.html\">Правилах</a>");
        toEdit = toEdit.replaceAll("ПРАВИЛАХ", "<a href=\"rules.html\">ПРАВИЛАХ</a>");
        
        return toEdit;
    }
    /**Метод редактирует страницу о наборе новичков в команду
     * @param isNoobiesWanted true - набор открыт, false - набор закрыт 
     * @param editLinks true - ключевые слова заменяются гиперссылками, false - не заменяются
     */
    public void editNoobiesPage(boolean isNoobiesWanted, boolean editLinks){
        StringBuilder stb = new StringBuilder();
        try {
            String text, link, linkMobile;
            if(isNoobiesWanted) 
            {    
                text = Team.getNoobYesText();
                link = "<a href=\"" + Team.getNoobVkLink() +
                        "\" target=\"_blank\"><img src=\"img/vk.jpg\" width=\"160\" height=\"100\" alt=\"Написать\" border='2px solid #808000'></a>";
                linkMobile = "<a href=\"" + Team.getNoobVkLink() +
                        " target=\"_blank\"><img src=\"img/vk.png\" width=\"190\" height=\"155\" alt=\"Написать\"></a><br>";
            }
            else {
                text = Team.getNoobNoText();
                link = " ";
                linkMobile = " ";
            }
            stb.append("<p class=\"text\">");
            stb.append(text.replaceAll("\\n", "<br>"));
            stb.append("</span></p>");
            String finalString = (editLinks) ? editNoobiesLinks(stb.toString()) : stb.toString();
            //ПК ВЕРСИЯ
            String temp = HTMLDefaults.NOOBIES.replaceFirst("!!!TEXT", finalString);
            temp = temp.replaceFirst("!!!LINK", link);
            doneFile.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(doneFile));
            bw.write(temp);
            bw.close();
            client.uploadFile(doneFile, client.MAIN_ADDRESS   +"noobies.html");
            doneFile.delete();
            //МОБИЛЬНАЯ ВЕРСИЯ
            finalString = finalString.replaceAll("<a", "<i><a");
            finalString = finalString.replaceAll("</a>", "</a></i>");
            temp = HTMLDefaults.NOOBIES_MOB.replaceFirst("!!!TEXT", finalString);
            temp = temp.replaceFirst("!!!LINK", linkMobile);
            doneFile.createNewFile();
            bw = new BufferedWriter(new FileWriter(doneFile));
            bw.write(temp);
            bw.close();
            client.uploadFile(doneFile, client.M_MAIN_ADDRESS +"noobies.html");
            doneFile.delete();
        } catch (IOException ex) {
            Logger.getLogger(PageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**Метод редактирует страницу сайта, содержащую правила*/
    public void editRulesPage(){
        StringBuilder stb = new StringBuilder();
        try {
            ruleFile.createNewFile();
            fw = new FileWriter(ruleFile);
            for (Chapter c : Team.RULES.getChapters()) {
                if(c.getNumber() != Regulation.RULES_SOURCE){
                    fw.write(c.getName() + lineSeparator + lineSeparator);
                    fw.write(c.getBody());
                    fw.write(lineSeparator + lineSeparator);
                    String text = c.getBody();
                    stb.append("<div>\n" +
                    " <a href=\"\" class=\"spoiler_links\">");
                    stb.append(c.getName());
                    stb.append("</a>\n" +
                    " <div class=\"spoiler_body bron\"><br>");
                    stb.append(text.replaceAll("\\n", "<br>"));
                    stb.append("<br><br></div></div>");
                }
            }
            fw.close();
            String temp = HTMLDefaults.RULES.replaceFirst("!!!CHAPTERS", stb.toString());
            temp = temp.replaceFirst("!!!SOURCE", Team.RULES.getChapterByNumber(Regulation.RULES_SOURCE).getName());
            doneFile.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(doneFile));
            bw.write(temp);
            bw.close();
            client.uploadFile(doneFile, client.MAIN_ADDRESS   + "rules.html");
            if(ruleFile.exists())client.uploadFile(ruleFile, client.M_MAIN_ADDRESS + "rules.txt");
            doneFile.delete();
            ruleFile.delete();
        } catch (IOException ex) {
            Logger.getLogger(PageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     /**Метод редактирует страницу сайта, содержащую Устав*/
    public void editRegulationPage(){
        StringBuilder stb = new StringBuilder();
        try {
        rgltFile.createNewFile();
        fw = new FileWriter(rgltFile);
        for (Chapter c : Team.REGULATION.getChapters()) {
            fw.write(c.getName() + lineSeparator + lineSeparator);
            fw.write(c.getBody());
            fw.write(lineSeparator + lineSeparator);
            String text = c.getBody();
            
            stb.append("<div>\n" +
            " <a href=\"\" class=\"spoiler_links\">");
            stb.append(c.getName());
            stb.append("</a>\n" +
            " <div class=\"spoiler_body bron\"><br>");
            stb.append(text.replaceAll("\\n", "<br>"));
            stb.append("<br><br></div></div>");

        }
        String temp = HTMLDefaults.REGULATION.replaceFirst("!!!CHAPTERS", stb.toString());
        doneFile.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(doneFile));
        bw.write(temp);
        bw.close();
        client.uploadFile(doneFile, client.MAIN_ADDRESS   +"ustav.html");
        if(ruleFile.exists()) client.uploadFile(ruleFile, client.M_MAIN_ADDRESS +"ustav.txt");
        doneFile.delete();
        rgltFile.delete();
        } catch (IOException ex) {
            Logger.getLogger(PageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     /**Метод редактирует страницу сайта, содержащую галерею*/
    public void editGalleryPage(){
        StringBuilder stb = new StringBuilder();
        gallery = Gallery.getGalleryImages();
        videos  = Gallery.getGalleryVideos();
        String photoString, videoString;
        for (GalleryImage img : gallery) {
            stb.append("<a href=\"img/fullsize/");
            stb.append(img.getName());
            stb.append(".jpg\" rel=\"lightgallery[1]\" title=\"\"><img src=\"img/");
            stb.append(img.getName());
            stb.append(".png\" alt=\"\"/></a> ");
        }
        photoString = stb.toString();
        stb = new StringBuilder();
        for (GalleryVideo video : videos) {
            stb.append(video.getLink());
            stb.append(" ");           
        }
        videoString = stb.toString();
        try {
           String temp = HTMLDefaults.GALLERY.replaceFirst("!!!PHOTO", photoString);
           temp = temp.replaceFirst("!!!VIDEO", videoString);
           doneFile.createNewFile();
           BufferedWriter bw = new BufferedWriter(new FileWriter(doneFile));
           bw.write(temp);
           bw.close();
           client.uploadFile(doneFile, client.MAIN_ADDRESS+"gallery.html");
           doneFile.delete();
        } catch (IOException ex) {
            if(options.isCatchPrint()) System.out.println(ex.getMessage());
        }
    }
    
    /**Метод редактирует страницу сайта, содержащую информацию о полноправном члене команды
    * @param m член команды, чья страница редактируется   */
    public void editFighterPage(Member m){
        try {
            //ПК ВЕРСИЯ
            String temp = HTMLDefaults.FIGHTER.replaceAll("!!!NAME", m.getName() + " &quot;" + m.getCallSign() + "&quot;");
            temp = temp.replaceAll("!!!POSITION", m.getPosition() + " &quot;СК БрОН&quot;" + m.getSpec1());
            temp = temp.replaceAll("!!!LINK", m.getLink());
            temp = temp.replaceAll("!!!CALLSIGN", m.getCallSign());
            doneFile.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(doneFile));
            bw.write(temp);
            bw.close();
            client.uploadFile(doneFile, client.FIGHTERS_ADDRESS + m.getLink() + ".html");
            //МОБИЛЬНАЯ ВЕРСИЯ
            temp = HTMLDefaults.FIGHTER_MOB.replaceAll("!!!NAME", m.getName() + " &quot;" + m.getCallSign() + "&quot;");;
            temp = temp.replaceAll("!!!POSITION", m.getPosition() + " &quot;СК БрОН&quot; c " + format.format(m.getDateCandidate().getTime()));
            temp = temp.replaceAll("!!!CALLSIGN", m.getCallSign());
            doneFile.createNewFile();
            bw = new BufferedWriter(new FileWriter(doneFile));
            bw.write(temp);
            bw.close();
            client.uploadFile(doneFile, client.M_FIGHTERS_ADDRESS + m.getLink() + ".html");
            doneFile.delete();
            editNavigationPage();
        } catch (IOException ex) {
            if(options.isCatchPrint()) System.out.println(ex.getMessage());
        }
    }

    /**Метод редактирует страницу сайта, содержащую информацию о кандидате в команду
    * @param m член команды, чья страница редактируется   */
    public void editCandidatePage(Member m){
        try {
            //ПК ВЕРСИЯ
            String temp = HTMLDefaults.CANDIDATE.replaceAll("!!!NAME", m.getName() + " &quot;" + m.getCallSign() + "&quot;");
            temp = temp.replaceAll("!!!POSITION", m.getPosition() + " &quot;СК БрОН&quot; c " + format.format(m.getDateCandidate().getTime()));
            temp = temp.replaceAll("!!!LINK", m.getLink());
            temp = temp.replaceAll("!!!CALLSIGN", m.getCallSign());
            doneFile.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(doneFile));
            bw.write(temp);
            bw.close();
            client.uploadFile(doneFile, client.FIGHTERS_ADDRESS + m.getLink() + ".html");
            //МОБИЛЬНАЯ ВЕРСИЯ
            temp = HTMLDefaults.CANDIDATE_MOB.replaceAll("!!!NAME", m.getName() + " &quot;" + m.getCallSign() + "&quot;");;
            temp = temp.replaceAll("!!!POSITION", m.getPosition() + " &quot;СК БрОН&quot; c " + format.format(m.getDateCandidate().getTime()));
            temp = temp.replaceAll("!!!LINK", m.getLink());
            temp = temp.replaceAll("!!!CALLSIGN", m.getCallSign());
            doneFile.createNewFile();
            bw = new BufferedWriter(new FileWriter(doneFile));
            bw.write(temp);
            bw.close();
            client.uploadFile(doneFile, client.M_FIGHTERS_ADDRESS + m.getLink() + ".html");
            doneFile.delete();
            editNavigationPage();
        } catch (IOException ex) {
            if(options.isCatchPrint()) System.out.println(ex.getMessage());
        }
    }

    /**Метод редактирует страницу сайта, содержащую всех членов команды*/
    public void editNavigationPage(){
        try {
            navigationParcer();
            String temp = HTMLDefaults.NAVIGATION.replaceFirst( "!!!FIGHTERS", competents);
            temp = temp.replaceFirst("!!!CANDIDATES", candidates);
            temp = temp.replaceFirst("!!!RESERVES", reservs);
            doneFile.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(doneFile));
            bw.write(temp);
            bw.close();
            client.uploadFile(doneFile, client.MAIN_ADDRESS + "fighters.html");
            doneFile.delete();
            mobileNavigationParser();
            temp = HTMLDefaults.NAVIGATION_MOB.replaceFirst( "!!!FIGHTERS", competentsMob);
            temp = temp.replaceFirst("!!!CANDIDATES", candidatesMob);
            temp = temp.replaceFirst("!!!RESERVES", reservsMob);
            doneFile.createNewFile();
            bw = new BufferedWriter(new FileWriter(doneFile));
            bw.write(temp);
            bw.close();
            client.uploadFile(doneFile, client.M_MAIN_ADDRESS + "fighters.html");
            doneFile.delete();
        } catch (IOException ex) {
            if(options.isCatchPrint()) System.out.println(ex.getMessage());
        }
    }

    /**Метод редактирует главную страницу сайта*/
    public void editMainPage(){
        try {
            int candidates = 0, fighters = 0, reserves = 0;
            String candidatesString, fightersString, reservesString;
            for (Member member : Team.getTeamMembers()) {
                if(!member.isReserved() && member.isCompetent())  fighters++;
                if(!member.isReserved() && !member.isCompetent()) candidates++;
                if(member.isReserved()) reserves++;
            }
            if(candidates%10 == 1) candidatesString = "а";
            else                   candidatesString = "ов";
            if(candidates == 0)    candidatesString = "ов";
            if(candidates == 1)    candidatesString = "а";
            if(candidates == 11)   candidatesString = "ов";

            if(fighters%10 == 1) fightersString = "а";
            else                 fightersString = "ов";
            if(fighters == 0)    fightersString = "ов";
            if(fighters == 1)    fightersString = "а";
            if(fighters == 11)   fightersString = "ов";

            if(reserves%10 == 1) reservesString = "а";
            else                 reservesString = "ов";
            if(reserves == 0)    reservesString = "ов";
            if(reserves == 1)    reservesString = "а";
            if(reserves == 11)   reservesString = "ов";
            //ПК ВЕРСИЯ
            String temp = HTMLDefaults.MAINPAGE.replaceFirst("!!!F",  Integer.toString(fighters));
            temp = temp.replaceFirst("!!!C",  Integer.toString(candidates));
            temp = temp.replaceFirst("!!!R",  Integer.toString(reserves));
            temp = temp.replaceFirst("!!!SF", fightersString);
            temp = temp.replaceFirst("!!!SC", candidatesString);
            temp = temp.replaceFirst("!!!SR", reservesString);
            doneFile.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(doneFile));
            bw.write(temp);
            bw.close();
            client.uploadFile(doneFile, client.MAIN_ADDRESS   + "main.html");
            //МОБИЛЬНАЯ ВЕРСИЯ
            temp = HTMLDefaults.MAINPAGE_MOB.replaceFirst("!!!F",  Integer.toString(fighters));
            temp = temp.replaceFirst("!!!C",  Integer.toString(candidates));
            temp = temp.replaceFirst("!!!R",  Integer.toString(reserves));
            temp = temp.replaceFirst("!!!SF", fightersString);
            temp = temp.replaceFirst("!!!SC", candidatesString);
            temp = temp.replaceFirst("!!!SR", reservesString);
            doneFile.createNewFile();
            bw = new BufferedWriter(new FileWriter(doneFile));
            bw.write(temp);
            bw.close();
            client.uploadFile(doneFile, client.M_MAIN_ADDRESS + "main.html");
            doneFile.delete();
        } catch (IOException ex) {
            if(options.isCatchPrint()) System.out.println(ex.getMessage());
        }
    }

    
    private void navigationParcer(){
        //СТРОКА С БОЙЦАМИ
        int counter = 0;
        StringBuilder stb = new StringBuilder("<p>");
        for (int i = 0; i < Team.getTeamMembers().size(); i++) {
            if(!Team.getTeamMembers().get(i).isReserved() && Team.getTeamMembers().get(i).isCompetent() == true){
                counter++;
                stb.append("<a href=\"Fighters/");
                stb.append(Team.getTeamMembers().get(i).getLink());
                stb.append(".html\"><img src=\"img/Fighters/mini/");
                stb.append(Team.getTeamMembers().get(i).getLink());
                stb.append(".png\" width=\"224\" height=\"200\"  border='2px solid #808000' alt=\"");
                stb.append(Team.getTeamMembers().get(i).getLink());
                stb.append("\"></a> ");
                if((counter%3)==0) stb.append("</p><p>");
                }}
        if(counter%3 != 0) stb.append("</p>");
        competents = stb.toString();
        //СТРОКА С КАНДИДАТАМИ
        stb = new StringBuilder();
        counter = 0;
        stb.append("</p>");
        for (int i = 0; i < Team.getTeamMembers().size(); i++) {
            if(!Team.getTeamMembers().get(i).isReserved() && Team.getTeamMembers().get(i).isCompetent() == false){
                counter++;
                stb.append("<a href=\"Fighters/");
                stb.append(Team.getTeamMembers().get(i).getLink());
                stb.append(".html\"><img src=\"img/Fighters/mini/");
                stb.append(Team.getTeamMembers().get(i).getLink());
                stb.append(".png\" width=\"224\" height=\"200\"  border='2px solid #808000' alt=\"");
                stb.append(Team.getTeamMembers().get(i).getLink());
                stb.append("\"></a> ");
                if((counter%3)==0) stb.append("</p><p>");
            }
        }
        if(counter%3 != 0) stb.append("</p>");
        candidates = stb.toString();
        //СТРОКА С РЕЗЕРВИСТАМИ
        stb = new StringBuilder();
        counter = 0;
        stb.append("</p>");
        for (int i = 0; i < Team.getTeamMembers().size(); i++) {
            if(Team.getTeamMembers().get(i).isReserved()){
                counter++;
                stb.append("<a href=\"Fighters/");
                stb.append(Team.getTeamMembers().get(i).getLink());
                stb.append(".html\"><img src=\"img/Fighters/mini/");
                stb.append(Team.getTeamMembers().get(i).getLink());
                stb.append(".png\" width=\"224\" height=\"200\"  border='2px solid #808000' alt=\"");
                stb.append(Team.getTeamMembers().get(i).getLink());
                stb.append("\"></a> ");
                if((counter%3)==0) stb.append("</p><p>");
            }
        }
        if(counter%3 != 0) stb.append("</p>");
        reservs = stb.toString();
    }

    private void mobileNavigationParser(){
        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < Team.getTeamMembers().size(); i++) {
            if(!Team.getTeamMembers().get(i).isReserved() && Team.getTeamMembers().get(i).isCompetent() == true){
                stb.append("<a href=\"Fighters/");
                stb.append(Team.getTeamMembers().get(i).getLink());
                stb.append(".html\"><img src=\"img/fighters/");
                stb.append(Team.getTeamMembers().get(i).getLink());
                stb.append(".png\" width=\"150\" height=\"135\"  border='2px solid #5E5D01' alt=\"\"></a> ");
            }
        }
        competentsMob = stb.toString();
        stb = new StringBuilder();
        stb.append("</p>");
        for (int i = 0; i < Team.getTeamMembers().size(); i++) {
            if(!Team.getTeamMembers().get(i).isReserved() && Team.getTeamMembers().get(i).isCompetent() == false){
                stb.append("<a href=\"Fighters/");
                stb.append(Team.getTeamMembers().get(i).getLink());
                stb.append(".html\"><img src=\"img/fighters/");
                stb.append(Team.getTeamMembers().get(i).getLink());
                stb.append(".png\" width=\"150\" height=\"135\"  border='2px solid #5E5D01' alt=\"\"></a> ");
            }
        }
        candidatesMob = stb.toString();
        stb = new StringBuilder();
        stb.append("</p>");
        for (int i = 0; i < Team.getTeamMembers().size(); i++) {
            if(Team.getTeamMembers().get(i).isReserved()){
                stb.append("<a href=\"Fighters/");
                stb.append(Team.getTeamMembers().get(i).getLink());
                stb.append(".html\"><img src=\"img/fighters/");
                stb.append(Team.getTeamMembers().get(i).getLink());
                stb.append(".png\" width=\"150\" height=\"135\"  border='2px solid #5E5D01' alt=\"\"></a> ");
            }
        }
        reservsMob = stb.toString();    
    }
}