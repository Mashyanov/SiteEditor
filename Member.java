
package javaapplication58;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**Класс, описывающий члена команды. Содержит все характеристики члена команды:
 стаус(боец\кандидат\резервист), должность, имя, позывной, а так же вспомогательные
 параметры и методы их регулирования
 *
 * @author Александр Машьянов, mashyanov1987@gmail.com
 */
public class Member implements Serializable{
    
    /**int id - уникальный опознователь статуса в команде. Это первичный фактор,
     * по которому осуществляется сортировка членов команды
     <table {border: 1px solid grey;}>
     * <tr><b>значение id</b> <th> Должность\статус</th></tr>
     * <tr>1111<td> Резервист       </td></tr>
     * <tr>999 <td> Кандидат        </td></tr>
     * <tr>777 <td> Боец            </td></tr>
     * <tr>31  <td> Мастер-оружейник</td></tr>
     * <tr>22  <td> Старшина        </td></tr>
     * <tr>11  <td> Зам. командира  </td></tr>
     * <tr>1   <td> Командир        </td></tr>
     </table>*/
    private int id;
    
    /**Коллекция строк, содержащих логи производимых с этим членом команды действий,
     таких как: создание, зачисление в команду, изменения данных и изменения фотографий.*/
    private ArrayList<String> log = new ArrayList<>();
    
    /**Строка, содержащая имя бойца*/
    private String name;
    
    /**Строка, содержащая позывной бойца*/
    private String callSign;
    
    /**Строка, содержащая часть ссылку на бойца на FTP-хостинге. Значение задается
     автоматически передачей позывного {@link callSign} в метод {@linkplain  Team.#tranlite() }*/
    private String link;
    private String fullLink, avatarLink, spec2;
    
    /**Строка, содержащая статус члена команды. <br>
     <b>Возможные значения:</b> резервист, кандидат, боец, мастер-оружейник, 
     старшина, зам. командира, командир*/
    private String position;
    
    /**Строка, содержащая специальизацию члена команды. <br>
     <b>Возможные значения:</b>  <i>отсутствие специализации</i>, автоматчик,
     пулеметчик, снайпер, гренадер, штурмовик, разведчик*/
    private String spec1;
    
    /**Календарь, содержащий дату принятий члена команды в кандидаты*/
    private Calendar dateCandidate; 
    
    /**Календарь, содержащий дату перевода члена команды в бойцы*/
    private Calendar dateCompetent; 
    
    /**Календарь, содержащий дату перевода члена команды в резевисты*/
    private Calendar dateReserve;
    
    /**Календарь, содержащий дату примененного изменения для внесения в лог {@link #log}*/
    private Calendar logDate;
    public boolean competent, commander, vip, callLineConflict, reserve;
    private SimpleDateFormat logDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
      
    public Member(String name, String callSign) {
        
        StringBuilder stb = new StringBuilder();
        this.link = Team.tranlite(callSign);
        this.id = 999;
        this.name = name;
        this.callSign = callSign;
        this.dateCandidate = Calendar.getInstance();
        this.dateCompetent = Calendar.getInstance();
        this.dateReserve   = Calendar.getInstance();
        this.logDate = Calendar.getInstance();
        
        this.position  = "Кандидат";
        this.spec1 = "";
        stb.delete(0, stb.length());
        if(SystemOptions.getInstance().isDebugPrint())System.out.println("добавлена страничка бойца " + this.name + ' ' + this.callSign);
        log.add(logDateFormat.format(logDate.getTime())+ ": создана страничка бойца " + this.name + ' ' + this.callSign + Team.getActiveUser());
        
                
    }    
    public Member (Member m){
        this.copy(m);
    }

/////////////////////////////////GETTERS/////////////////////////////////
    /** метод работы с датами.
     * @return дату вступления в кандидаты*/
    public Calendar getDateCandidate() {
        return dateCandidate;
    }

    /** метод работы с датами.
     * @return дату вступления в бойцы*/
    public Calendar getDateCompetent() {
        return dateCompetent;
    }

    /** метод работы с датами.
     * @return дату перевода в резерв*/
    public Calendar getDateReserve() {
        return dateReserve;
    }
    
    /**метод получения строчного массива для логирования изменений члена команды
     * @return ArrayList cо строками логов*/
    public ArrayList<String> getLog() {
        return log;
    }
      
    /**метод получения позывного члена команды
     * @return строку, содержащую позыной*/
    public String getCallSign() {
        return callSign;
    }
    
    /**метод получения ссылки члена команды. Ссылка - записанный транслитом 
     * позывной. Например, при позвном "Щепа" ссылка будет "Shepa".
     * Добавлением к этой строке расширения файла можно ссылаться на изображения,
     * html-страницы и любые другие файлы, относящиеся к данному члену команды.
     * @return строку, содержащую ссылку*/
    public String getLink() {
        return link;
    }
    
    /**метод получения имени члена команды
     * @return строку, содержащую имя*/
    public String getName() {
        return name;
}
    /**метод получения порядкового номера члена команды. Номер зависит от статуса
     * и занимаемой в команде должности. Чем меньше номер - тем выше статус. 
     * Id-номер - основной критерий сортировки членов команды.
     * @return int, содержащий номер*/    
    public int getId() {
    return id;
}
    /**метод проверки члена команды на предмет перевода оного из кандидатов в
     * полноправные члены.
     * @return false - если член команды кандидат, true - если он был переведен  
     * в полноправные члены команды*/
    public boolean isCompetent() {
    return competent;
}

    public String getPosition() {
        return position;
    }
/**Проверяет, не находится ли член команды в резерве
 * @return <b>true </b> - если член команды в резерве, <br>
   *       <b>false</b> - если не в резерве*/
    public boolean isReserved() { return reserve;  }
      
/** @return строку, содержащую должность члена команды*/
    public String getSpec1() {
        return spec1;
    }


/////////////////////////////////SETTERS/////////////////////////////////
    /** Метод присваивает члену команды позывной, делает об этом запись в лог 
     * и создает новое значение для параметра link, вызывая {@link Team.#tranlite() }
     * @param callSign строка, содержащая позывной*/
    public void setCallSign(String callSign) {
        logDate = Calendar.getInstance();
        log.add(logDateFormat.format(logDate.getTime())+ ": позывной " + this.callSign +  " заменен на " + callSign + Team.getActiveUser());
        this.callSign = callSign;
        this.link = Team.tranlite(this.callSign);
       
    }
    
    /** Метод присваивает члену команды имя, делает об этом запись в лог 
    * @param name}*/
    public void setName(String name){
        logDate = Calendar.getInstance();
        log.add(logDateFormat.format(logDate.getTime())+ ": имя " + this.name +  " заменено на " + name + Team.getActiveUser());
        this.name = name;
    }  
    ////////////////////////ДОЛЖНОСТИ/////////////////////////
    
    /** Метод присваивает члену команды статус резервиста и делает об этом запись
    в лог. Если для члена команды был создан профиль пользователя-администратора
    * {@link User}, этот профиль удаляется    */
    public void setReserve(){
        reserve = true;
        id = 1111;
        dateReserve = Calendar.getInstance();
        logDate = Calendar.getInstance();
        log.add(logDateFormat.format(logDate.getTime())+ ": переведен в резерв" + Team.getActiveUser());
        position = "Резервист";
        if(Team.getUserFromList(link)!=null)
            Team.removeUser(Team.getUserFromList(link));
    }
    
    /** Метод присваивает члену команды статус кандидата и делает об этом запись
    в лог. Вызывает метод сортировки команды {@linkplain Team.#sort() }   */
    public void setCandidate() {
        if(reserve) dateCandidate = Calendar.getInstance();
        reserve = false;
        competent = false;
        id=999;          
        position = "Кандидат";
        logDate = Calendar.getInstance();
        log.add(logDateFormat.format(logDate.getTime())+ ": переведен в кандидаты" + Team.getActiveUser());
        
        Team.sort();
    }
    
    /** Метод присваивает члену команды статус бойца и делает в лог  запись о 
     <b>РАЗЖАЛОВАНИИ</b> в бойцы. Если необходима запись о зачислении в бойцы, следует
     * использовать метод {@link #setCompetent()  }
     * Если для члена команды был создан профиль пользователя-администратора,
     * этот профиль удаляется    
     Вызывает метод сортировки команды {@linkplain Team.#sort() }*/
    public void bustCompetent(){
    if(competent)    {
     position = "Боец";  
     logDate = Calendar.getInstance();
    log.add(logDateFormat.format(logDate.getTime())+ ": разжалован в бойцы" + Team.getActiveUser());
    id = 777;
    if(Team.getUserFromList(link)!=null)
        Team.removeUser(Team.getUserFromList(link));
    Team.sort();
    }
    }
    
    /** Метод присваивает члену команды статус бойца и делает в лог  запись о 
     <b>ЗАЧИСЛЕНИИ</b> в бойцы. Если необходима запись о разжаловании в бойцы, следует
     * использовать метод {@link #bustCompetent() }
     Вызывает метод сортировки команды {@linkplain Team.#sort() }*/
    public void setCompetent(){
        if(!competent){
            dateCompetent = Calendar.getInstance();
            reserve = false;
            competent = true;
            position = "Боец";
            logDate = Calendar.getInstance();
            log.add(logDateFormat.format(logDate.getTime())+ ": переведен в бойцы" + Team.getActiveUser());
            id = 777;
            if(Team.getUserFromList(link)!=null)
                Team.removeUser(Team.getUserFromList(link));
            Team.sort();
    }}
    
    /**Метод снимает с члена команды специализацию */
    public void setUsual()        { 
        spec1= ""; 
        logDate = Calendar.getInstance();
        log.add(logDateFormat.format(logDate.getTime())+ ": специализация снята" + Team.getActiveUser());
    }
    
    /**Метод назначает члену команды специализацию "автматчик" */
    public void setAssaultRifle() { 
        spec1= ", автоматчик"; 
        logDate = Calendar.getInstance();
        log.add(logDateFormat.format(logDate.getTime())+ ": назначена специализация автоматчик" + Team.getActiveUser());
    }
    
    /**Метод назначает члену команды специализацию "пулеметчик" */
    public void setMachineGunner(){
        spec1= ", пулеметчик"; 
        logDate = Calendar.getInstance();
        log.add(logDateFormat.format(logDate.getTime())+ ": назначена специализация пулеметчик" + Team.getActiveUser());
    }
    
    /**Метод назначает члену команды специализацию "снайпер" */
    public void setSniper()       {
        spec1= ", снайпер";    
        logDate = Calendar.getInstance();
        log.add(logDateFormat.format(logDate.getTime())+ ": назначена специализация снайпер" + Team.getActiveUser());
    }
    
    /**Метод назначает члену команды специализацию "Сапер" */
    public void setMiner()        { 
        spec1= ", гренадер"; 
        logDate = Calendar.getInstance();       
        log.add(logDateFormat.format(logDate.getTime())+ ": назначена специализация гренадер" + Team.getActiveUser());
    }
    
    /**Метод назначает члену команды специализацию "штурмовик" */
    public void setStormTrooper() { 
        spec1= ", штурмовик"; 
        logDate = Calendar.getInstance();
        log.add(logDateFormat.format(logDate.getTime())+ ": назначена специализация штурмовик" + Team.getActiveUser());
    }
    
    /**Метод назначает члену команды специализацию "разведчик" */
    public void setScout()        { 
        spec1= ", разведчик"; 
        logDate = Calendar.getInstance();
        log.add(logDateFormat.format(logDate.getTime())+ ": назначена специализация разведчик" + Team.getActiveUser());}
    
    /**Метод назначает члену команды специализацию "мастер-оружейник" и Вызывает 
     * метод сортировки команды {@linkplain Team.#sort() } */
    public void setCraftsman(){
    
            if(SystemOptions.getInstance().isDebugPrint())System.out.println(name + ' ' + callSign + " мастер оружейник!" );
            logDate = Calendar.getInstance();
            log.add(logDateFormat.format(logDate.getTime())+ ": назначен на должность мастера-оружейника" + Team.getActiveUser());
            position = "Мастер-оружейник";
            id = 31;
            if(Team.getUserFromList(link)!=null)
                Team.removeUser(Team.getUserFromList(link));
            Team.sort();
        }
         
    /**Метод назначает члену команды должность "старшина" и делает об этом запись в лог
     * Если член команды кандидат или резервист, должность не будет назначена
     * Если в команде уже присутствует старшина, он будет разжалован в бойы вызовом 
     метода {@link #bustCompetent() }. Вызывает метод сортировки команды {@linkplain Team.#sort() }*/
    public void setSergeant() {
       
        for (Member m : Team.getTeamMembers()) {
            if(m.id==22){
                if(SystemOptions.getInstance().isDebugPrint())System.out.println(m.getCallSign() + " разжалован");
                m.commander = false;
                m.bustCompetent();
            }
            }
        if(SystemOptions.getInstance().isDebugPrint())System.out.println(name + ' ' + callSign + " назначен старшиной!" );
        logDate = Calendar.getInstance();
        log.add(logDateFormat.format(logDate.getTime())+ ": назначен на должность старшины" + Team.getActiveUser());
        position = "Старшина";
        id = 22;
        if(Team.getUserFromList(link)!=null)
            Team.removeUser(Team.getUserFromList(link));
        Team.sort();
}
    
    /**Метод назначает члену команды должность "зам. командира" и делает об этом запись в лог
     * Если член команды кандидат или резервист, должность не будет назначена
     * Так же для этого члена команды создается профиль пользователя-администратора {@link User},
     если он еще не был создан*/    
    public void setSubCommander() {
     
            if(SystemOptions.getInstance().isDebugPrint())System.out.println(name + ' ' + callSign + " назначен зам. командира!" );
            position = "Зам. командира";
            logDate = Calendar.getInstance();
            log.add(logDateFormat.format(logDate.getTime())+ ": назначен на должность Зам. командира" + Team.getActiveUser());
            id = 11;
            if(Team.getUserFromList(link)==null)
                Team.addUser(new User(link, id));
            else 
                Team.getUserFromList(link).setNumber(11);
            Team.sort();
     }
    
    
    /**Метод назначает члену команды должность "командир" и делает об этом запись в лог
     * Если член команды кандидат или резервист, должность не будет назначена
     * Если в команде уже присутствует командир, он будет разжалован в бойцы вызовом 
     метода {@link #bustCompetent() }
     Так же для этого члена команды создается профиль пользователя-администратора {@link User}, 
     если он еще не был создан.
     Вызывает метод сортировки команды {@linkplain Team.#sort() }*/
    public void setCommander(){
        if(!competent) if(SystemOptions.getInstance().isDebugPrint())System.out.println("кандидат не может быть командиром");
        else{
            for (Member m : Team.getTeamMembers()) {
                if(m.id==1){
                    if(SystemOptions.getInstance().isDebugPrint())System.out.println(m.name + ' ' + m.callSign + 
                            " разжалован в бойцы");
                    m.commander = false;
                    m.bustCompetent();
                }}
            if(SystemOptions.getInstance().isDebugPrint())System.out.println(name + ' ' + callSign + " назначен командиром!" );
            commander = true;
            position = "Командир";
            logDate = Calendar.getInstance();
            log.add(logDateFormat.format(logDate.getTime())+ ": назначен на должность командира" + Team.getActiveUser());
            id = 1;
            if(Team.getUserFromList(link)==null)
                Team.addUser(new User(link, id));
            else 
                Team.getUserFromList(link).setNumber(1);
            
            Team.sort();
    }}
    
    
    public final void copy(Member m){

        dateCandidate = Calendar.getInstance();
        dateCompetent = Calendar.getInstance();
        dateReserve   = Calendar.getInstance();
        dateCandidate.setTime(m.dateCandidate.getTime());
        dateCompetent.setTime(m.dateCompetent.getTime());
        dateReserve.setTime(m.dateReserve.getTime());

        callSign      = m.callSign;
        commander     = m.commander;
        competent     = m.competent;
        link          = m.link;
        name          = m.name;
        position      = m.position;
        spec1         = m.spec1;
        id            = m.id;
        reserve       = m.reserve;
        log           = m.log;      
    }
    
    public boolean equals(Member m){
        
        if(!callSign.equals(m.callSign))            return false;
        if(commander     != m.commander)            return false;
        if(competent     != m.competent)            return false;
        if(!link.equals(m.link))                    return false;
        if(!name.equals(m.name))                    return false;
        if(!position.equals(m.position))            return false;
        if(!spec1.equals(m.spec1))                  return false;
        if(id            != m.id)                   return false;
        if(reserve       != m.reserve)              return false;
        if(log != m.log)                            return false;
        if(!dateCandidate.equals( m.dateCandidate)) return false;
        if(!dateCompetent.equals( m.dateCompetent)) return false;
        return dateReserve.equals(m.dateReserve);
    }
    /** Метод добавляет члена команды в команду и делает об этом соответвующую 
     * запись в лог. Выполняется проверка на максимальное количество бойцов в 
     * команде. Так же проверяется уникальность заданного позывного.
     * Автоматически присваивается статус "Кандидат".
     * Вызывает метод сортировки команды {@linkplain Team.#sort() }
     */
    public void addToTeam() {
    
        Team.getTeamMembers().add(this);
        System.out.println(callSign + " успешно зачислен в команду");
        position = "Кандидат";
        logDate = Calendar.getInstance();
        log.add(logDateFormat.format(logDate.getTime())+ ": зачислен в команду");
        log.add("=====================");
        Team.sort();
       }
        
    /**Удаляет этого члена команды из команды
     Вызывает метод сортировки команды {@linkplain Team.#sort() }*/
    public void removeFromTeam(){
        Team.getTeamMembers().remove(this);
        Team.sort();
        }

    /**Метод изменяет дату принятия члена команды в кандидаты и далает об этом 
     * соотвествующую запись в лог.
     * @param date новая дата принятия в кандидаты*/
    public void setCandidateDate(Date date) {
        dateCandidate.setTime(date);
        logDate = Calendar.getInstance();
        log.add(logDateFormat.format(logDate.getTime())+ ": дата назаначения кандидатом изменена на " + Team.dateFormat.format(dateCandidate.getTime()) + Team.getActiveUser());
    }

    /**Метод изменяет дату перевода члена команды в бойцы и далает об этом 
     * соотвествующую запись в лог.
     * @param date новая дата принятия в бойцы*/
    public void setCompetentDate(Date date) {
        dateCompetent.setTime(date);
        logDate = Calendar.getInstance();
        log.add(logDateFormat.format(logDate.getTime())+ ": дата назаначения бойцом изменена на " + Team.dateFormat.format(dateCompetent.getTime()) + Team.getActiveUser());
    }
    
    /**Метод изменяет дату перевода члена команды в резервисты и далает об этом 
     * соотвествующую запись в лог.
     * @param date новая дата принятия в кандидаты*/
    public void setReserveDate(Date date) {
        dateReserve.setTime(date);
        logDate = Calendar.getInstance();
        log.add(logDateFormat.format(logDate.getTime())+ ": дата назаначения резервистом изменена на " + Team.dateFormat.format(dateReserve.getTime()) + Team.getActiveUser());
    }
    
    /**Метод для получения заглавия html-страницы члена команды на сайте в формате
    "статус" СК БрОН, "специализация
    * @return строку-заглавие, например Старшнина "СК БрОН", пулемётчик*/
    public String getPageTitle(){
    return position + " СК БрОН" + spec1;
}
    
    @Override
    public String toString() {
        Calendar date;
        if(competent) date = dateCompetent; else date = dateCandidate;
        if(reserve)   date = dateReserve;
        return name + " " + callSign + " - " + position + " СК БрОН с " + 
                Team.dateFormat.format(date.getTime()) + spec1;
    }

}

