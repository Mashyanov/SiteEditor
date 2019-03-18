package javaapplication58;

import java.awt.Image;
import java.awt.Panel;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public final class SystemOptions {

 private final File conn = new File("conn.dat");
    /** Хранилище системных настроек<br>
     * <table {border: 1px solid grey;}>
     * <tr><b>№ строки</b> <th> Значение</th></tr>
     * <tr><center>0</center><td> Адрес Хоста                    </td></tr>
     * <tr><center>1</center><td> Логин                          </td></tr>
     * <tr><center>2</center><td> Пароль                         </td></tr>
     * <tr><center>3</center><td> Обход FireWall                 </td></tr>
     * <tr><center>4</center><td> Отладочная печать              </td></tr>
     * <tr><center>5</center><td> Печать о проверке соединения   </td></tr>
     * <tr><center>6</center><td> Печать ошибок из блока catch   </td></tr>
     </table>*/
   public  Image ICON_MAIN;    
   private ArrayList<String> data = new ArrayList<>();
   private static  ArrayList<Icon>  icons = null;
   private static  ArrayList<Image> images = null;
   private static  ArrayList<BufferedImage> bufferedImages = null;
   private String login, password, host;
   private boolean debugPrint, connectionPrint, firewallCutOff, catchPrint;
   private ObjectInputStream  ois;
   private ObjectOutputStream oos;
   private static SystemOptions instance = null;
   
   
    private SystemOptions() {
    try {
        if(!conn.exists()) conn.createNewFile();
        ois = new ObjectInputStream(new FileInputStream(conn));
        data = (ArrayList<String>) ois.readObject();
        icons           = new ArrayList<>();
        images          = new ArrayList<>();
        bufferedImages  = new ArrayList<>();
        host            = data.get(0);
        login           = data.get(1);
        password        = data.get(2);
        firewallCutOff  =(data.get(3)).equals("true");
        debugPrint      =(data.get(4)).equals("true");
        connectionPrint =(data.get(5)).equals("true");
        catchPrint      =(data.get(6)).equals("true");
        
        try {
        ICON_MAIN  = ImageIO.read(new FileImageInputStream(new File("images/sys/icon.png")));
        }catch (IOException ex) {
           if(isCatchPrint()) System.out.println(ex.getMessage());
           JOptionPane.showMessageDialog(new JPanel(), "Ошибка при создании окна опций\n" + ex.getMessage(), "ошибка", JOptionPane.ERROR_MESSAGE);
        }
    } catch (FileNotFoundException ex) {
        if(catchPrint) System.out.println(ex.getMessage());
    } catch (IOException | ClassNotFoundException ex) {
        if(catchPrint) System.out.println(ex.getMessage());
    }}

    public String  getHost()          {  return host;            }       
    public String  getLogin()         {  return login;           }
    public String  getPassword()      {  return password;        }
    public boolean isConnectionPrint(){  return connectionPrint; }
    public boolean isFirewallCutOff() {  return firewallCutOff;  }
    public boolean isCatchPrint()     {  return catchPrint;      }
    public boolean isDebugPrint()     {  return debugPrint;      }
    
    
    public static final Icon getIcon(int iconIndex){
        return icons.get(iconIndex);
    }
    
    public static final Image getImage(int imageIndex){
        return images.get(imageIndex);
    }
    
    public static final BufferedImage getBufferedImage(int bufferedImageIndex){
        return bufferedImages.get(bufferedImageIndex);
    }
    
    public static final SystemOptions getInstance() {
        if(instance==null) instance = new SystemOptions();
        return instance;
    }
    
    public void setOptions(String host, String login, String password, 
            boolean firewallCutOff, boolean debugPrint,  
            boolean catchPrint ) {
        this.login = login;
        this.password = password;
        this.host = host;
        this.debugPrint = debugPrint;
        this.firewallCutOff = firewallCutOff;
        this.catchPrint = catchPrint;
        try {
        oos = new ObjectOutputStream(new FileOutputStream(conn));
        data.set(0, host);
        data.set(1, login);
        data.set(2, password);
        if(firewallCutOff) data.set(3, "true");
        else data.set(3, "false");
        if(debugPrint) data.set(4, "true");
        else data.set(4, "false");
        if(connectionPrint) data.set(5, "true");
        else data.set(5, "false");
        if(catchPrint) data.set(6, "true");
        else data.set(6, "false");
        
        oos.writeObject(data);
        oos.close();
     } catch (FileNotFoundException ex) {
        if(catchPrint) System.out.println(ex.getMessage());
     } catch (IOException ex) {
        if(catchPrint) System.out.println(ex.getMessage());
    }}
   
    public void setLogin(String login) {
     try {
        oos = new ObjectOutputStream(new FileOutputStream(conn));
        this.login = login;
        data.set(1, login);
        oos.writeObject(data);
        oos.close();
     } catch (FileNotFoundException ex) {
        if(catchPrint) System.out.println(ex.getMessage());
     } catch (IOException ex) {
        if(catchPrint) System.out.println(ex.getMessage());
    }}

    public void setPassword(String password) {
        try {
        oos = new ObjectOutputStream(new FileOutputStream(conn));
        this.password = password;
        data.set(2, password);
        oos.writeObject(data);
        oos.close();
    } catch (FileNotFoundException ex) {
       if(catchPrint) System.out.println(ex.getMessage());
    } catch (IOException ex) {
       if(catchPrint) System.out.println(ex.getMessage());
    }}

    public void setHost(String host) {
       try {
       oos = new ObjectOutputStream(new FileOutputStream(conn));
       this.host = host;
       data.set(0, host);
       oos.writeObject(data);
       oos.close();
    } catch (FileNotFoundException ex) {
       if(catchPrint) System.out.println(ex.getMessage());
    } catch (IOException ex) {
       if(catchPrint) System.out.println(ex.getMessage());
    }}

    public void setDebugPrint(boolean debugPrint) {
       try {
       oos = new ObjectOutputStream(new FileOutputStream(conn));
       this.debugPrint = debugPrint;
       if(debugPrint) data.add(4, "true");
       else data.add(4, "false");
       oos.writeObject(data);
       oos.close();
    } catch (FileNotFoundException ex) {
       if(catchPrint) System.out.println(ex.getMessage());
    } catch (IOException ex) {
       if(catchPrint) System.out.println(ex.getMessage());
    }}

    public void setConnectionPrint(boolean connectionPrint) {
       try {
       oos = new ObjectOutputStream(new FileOutputStream(conn));
       this.connectionPrint = connectionPrint;
       if(connectionPrint) data.add(5, "true");
       else data.add(5, "false");
       oos.writeObject(data);
       oos.close();
    } catch (FileNotFoundException ex) {
       if(catchPrint) System.out.println(ex.getMessage());
    } catch (IOException ex) {
       if(catchPrint) System.out.println(ex.getMessage());
    }}
  
    public void setFirewallCutOff(boolean firewallCutOff) {
       try {
       oos = new ObjectOutputStream(new FileOutputStream(conn));
       this.firewallCutOff = firewallCutOff;
       if(firewallCutOff) data.set(3, "true");
       else data.set(3, "false");
       oos.writeObject(data);
       oos.close();
    } catch (FileNotFoundException ex) {
       if(catchPrint) System.out.println(ex.getMessage());
    } catch (IOException ex) {
       if(catchPrint) System.out.println(ex.getMessage());
    }}
    
    /** действия при закрытии программы*/
    public static final void windowClosing(){
        File tempDir; 
        File[] tempFiles; 
        UIManager.put("OptionPane.yesButtonText"   , "Выходим!");
        UIManager.put("OptionPane.noButtonText"    , "Не-не-не, пока остаемся");
        if(JOptionPane.showConfirmDialog(new Panel(), "Точно хочешь выйти?", "Выходим?", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE) == 0)
        {
            tempDir = new File("images\\temp\\gallery");
            tempFiles = tempDir.listFiles();
            for (File tempFile : tempFiles) 
                if(!tempFile.isDirectory()) tempFile.delete();

            tempDir = new File("images\\temp");
            tempFiles = tempDir.listFiles();
            for (File tempFile : tempFiles) 
                if(!tempFile.isDirectory()) tempFile.delete();

            tempDir = new File("images\\temp\\gallery\\fullsize");
            tempFiles = tempDir.listFiles();
            for (File tempFile : tempFiles) 
                if(!tempFile.isDirectory()) tempFile.delete();
            
            tempDir = new File("temp\\done");
            tempFiles = tempDir.listFiles();
            for (File tempFile : tempFiles) 
                if(!tempFile.isDirectory()) tempFile.delete();
            
            tempDir = new File("temp");
            tempFiles = tempDir.listFiles();
            for (File tempFile : tempFiles) 
                if(!tempFile.isDirectory()) tempFile.delete();
            
            MyFTPClient client = MyFTPClient.getInstace();
            client.disconnect();
            System.out.println("public static void windowClosing()");
            System.exit(0);
        }
    }

    public void setCatchPrint(boolean catchPrint) {
        try {
        oos = new ObjectOutputStream(new FileOutputStream(conn));
        this.catchPrint = catchPrint;
        if(catchPrint) data.set(6, "true");
        else data.set(6, "false");
        oos.writeObject(data);
        oos.close();
     } catch (FileNotFoundException ex) {
        if(catchPrint) System.out.println(ex.getMessage());
     } catch (IOException ex) {
        if(catchPrint) System.out.println(ex.getMessage());
     }
   }
}
