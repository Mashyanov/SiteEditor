
package javaapplication58;

import java.io.Serializable;
import java.util.Calendar;
/**
 *
 * @author Александр Машьянов, mashyanov1987@gmail.com
 */
public class GalleryVideo implements Serializable{
    private String name, link;
    private int numb;
    private final Calendar date;

    public GalleryVideo(String name, String link, int number) {
        this.name = name;
        this.link = link;
        this.numb = number;
        this.date = Calendar.getInstance();
    }
     public GalleryVideo(String name, String link) {
        this.name = name;
        this.link = link;
        this.numb = 9999;
        this.date = Calendar.getInstance();
    }

    public Calendar getDate()   {   return date;    }
    public String   getName()   {   return name;    }
    public String   getLink()   {   return link;    }
    public int      getNumb()   {   return numb;    }
    
    public void setName(String name)  {    this.name  = name;    }
    public void setLink(String link)  {    this.link  = link;    }
    public void setNumb(int  number)  {    this.numb  = number;  }
    }
    
    
    
    

