
package javaapplication58.windows;
 
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import javaapplication58.MyFTPClient;
import javaapplication58.SystemOptions;
import javaapplication58.Team;
import javaapplication58.User;
import javaapplication58.Version;
import javaapplication58.VersionController;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;

public class LoginWindow extends javax.swing.JFrame {

    private final Version version;
    private SystemOptions options = SystemOptions.getInstance();
    private boolean print = options.isDebugPrint(), connected = false;
    private char[] psw;
    private Image imageTitle, imagePling;
    private Icon iconTitle, iconPling;
    private final Color color = new Color(94, 93, 1);
    private final MyFTPClient client = MyFTPClient.getInstace();
    private VersionController versCont=null;
    private final File dirTemp = new File("temp/");
    private final File dirDone = new File("temp/done/");
    private LinkedList<User> users;
    private final Font fontReg = new Font("Impact", 0, 16);
    private final Font fontLog = new Font("Impact", 0, 24);
    int i = 0;
    private final LoadingWindow lw = new LoadingWindow();
    
    public LoginWindow() {
        this.version = Team.getVersion();
        
        try {
            File[] listFile = dirTemp.listFiles();
            for (File file : listFile) {
                if(file.isFile()) file.delete();
            }
            listFile = dirDone.listFiles();
            for (File file : listFile) {
                if(file.isFile()) file.delete();
            }
           imagePling = ImageIO.read(new FileImageInputStream(new File("images/sys/!.png")));
           imageTitle = ImageIO.read(new FileImageInputStream(new File("images/sys/title.png")));
           iconTitle  = new ImageIcon(imageTitle);
           iconPling  = new ImageIcon(imagePling);
           
        } catch (IOException ex) {
            if(options.isCatchPrint()) System.out.println(ex.getMessage());
        }
        initComponents();
        setTitle("Авторизация");
        versionLabel.setText("ver " + version);
        passwordField.grabFocus();
        loginButton.setFont(new Font("Impact", 0, 24));
        loginButton.setForeground(Color.GRAY);
     
        
        login();
//        try {
//            Team.noobData.createNewFile();
//        } catch (IOException ex) {
//            Logger.getLogger(LoginWindow.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        Team.setNoobiesWanted(true);
//        Team.setNoobNoText("NOOB NO");
//        Team.setNoobYesText("NOOB YES");
//        Team.setNoobVkLink("LINK");
//        Team.saveNoobiesToFile();
        if(print)System.out.println("ЗАГРУЗКА ЗАВЕРШЕНА\nМеню Авторизации");
    }
    
    private void checkButtonEnabled(){
        psw = passwordField.getPassword();
        if((psw!=null)){
            loginButton.setEnabled(true);
            loginButton.setForeground(color);
        }
        else{
            loginButton.setEnabled(false);
            loginButton.setForeground(Color.GRAY);
        }
    }
    
    private String getStringFromBuffer(){
        String result = "";
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(null);
        boolean hasTransferableText =
            (contents != null) &&
             contents.isDataFlavorSupported(DataFlavor.stringFlavor);
        if (hasTransferableText) {
        try {
        result = (String)contents.getTransferData(DataFlavor.stringFlavor);
        }catch (UnsupportedFlavorException | IOException ex){
            if(options.isCatchPrint()) System.out.println(ex.getMessage());
        }
    }
     return result;   
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jDialog3 = new javax.swing.JDialog();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel1 = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        versionLabel = new javax.swing.JLabel();
        usersBox = new javax.swing.JComboBox<>();
        settingsLabel = new javax.swing.JLabel();
        connectionErrorLabel = new javax.swing.JLabel();

        jInternalFrame1.setVisible(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("TittyTits :) ");
        setIconImage(options.ICON_MAIN);
        setMinimumSize(new java.awt.Dimension(468, 410));
        setName("loginFrame"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel1MouseEntered(evt);
            }
        });

        titleLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setIcon(iconTitle);
        titleLabel.setToolTipText("");

        jLabel2.setText("Логин:");

        jLabel1.setText("Пароль:");

        passwordField.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        passwordField.setSelectionColor(new java.awt.Color(94, 93, 1));
        passwordField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                passwordFieldMouseClicked(evt);
            }
        });
        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });
        passwordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passwordFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                passwordFieldKeyTyped(evt);
            }
        });

        loginButton.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        loginButton.setForeground(new java.awt.Color(94, 93, 1));
        loginButton.setText("ВОЙТИ");
        loginButton.setEnabled(false);
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        versionLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        versionLabel.setForeground(new java.awt.Color(102, 102, 102));
        versionLabel.setText("ver 1.1.3");

        usersBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                usersBoxItemStateChanged(evt);
            }
        });
        usersBox.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                usersBoxCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });

        settingsLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        settingsLabel.setText("Настройки");
        settingsLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        settingsLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingsLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                settingsLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                settingsLabelMouseExited(evt);
            }
        });

        connectionErrorLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        connectionErrorLabel.setForeground(new java.awt.Color(255, 0, 0));
        connectionErrorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        connectionErrorLabel.setText("!!!!!!!!!!!!!!!!!!!! Нет соединения с FTP-сервером !!!!!!!!!!!!!!!!!!!!");
        connectionErrorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                connectionErrorLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                connectionErrorLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                connectionErrorLabelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(versionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(settingsLabel)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(usersBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(passwordField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))))
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addComponent(connectionErrorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usersBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(settingsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(versionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(connectionErrorLabel)
                .addGap(34, 34, 34))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 420));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void login(){
        Icon iconErr = new ImageIcon("images/sys/error.png");
        passwordField.setText(null);
        loginButton.setEnabled(false);
        switch(client.connect(MyFTPClient.FTPpassword)){
            case 11:
                UIManager.put("OptionPane.yesButtonText"   , "Выход...");

                connected = false;
                connectionErrorLabel.setVisible(true);

                switch(JOptionPane.showConfirmDialog(this, "Ошибка соединения с сервером\nошибка скорее всего на стороне провайдера...", "Ошибочка вышла...", 
                        JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE, iconErr))
                {
                    case 0:
                        System.exit(0);
                }
                break;
        case 22:   
            UIManager.put("OptionPane.yesButtonText"       , "Да");
            UIManager.put("OptionPane.noButtonText"        , "Нет");
            UIManager.put("OptionPane.cancelButtonText"    , "Закрыть программу");
            connected = false;
            connectionErrorLabel.setVisible(true);
            switch(JOptionPane.showConfirmDialog(this, "Ошибка аутентификации...\nСкорее всего неверные системные логин или пароль!\n\n"
                    + "Открыть системные настройки?", "Ошибочка вышла...", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, iconErr))
            {
                case 0:
                    new ConnectionOptionsWindow().setVisible(true);
                    break;
                
                case 2:
                    System.exit(0);
                    break;
            }
            break;
        case 1111:
            UIManager.put("OptionPane.yesButtonText"       , "Да");
            UIManager.put("OptionPane.noButtonText"        , "Нет");
            UIManager.put("OptionPane.cancelButtonText"    , "Закрыть программу");
            connected = false;
            connectionErrorLabel.setVisible(true);
            switch(JOptionPane.showConfirmDialog(this, "Ошибка соединения с сервером...\nВозможно, указан неверный адрес хоста в системных настройках!"
                    + "\nА может быть, просто нет интернета, проверь соединение...\n\n"
                    + "Открыть системные настройки?", "Ошибочка вышла...", 
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, iconErr))
            {
                case 0:
                    new ConnectionOptionsWindow().setVisible(true);
                    break;
                
                case 2:
                    System.exit(0);
                    break;
            }

            break;
        case 0:
            ///////////////////////////////////////////
            ///////////////////////////////////////////
            ///////////////////////////////////////////
            ///////////////////////////////////////////
            
            //ВОТ ТУТ ВСЕ ДЕЙСТВИЯ ПРИ УДАЧНОМ КОННЕКТЕ
            lw.setVisible(true);
            connected = true;
            connectionErrorLabel.setVisible(false);
            client.downloadTeamDataFiles();
            client.setLogin(/*--------------*/": ");
            Team.loadTeamFromFile();
            users = Team.loadUsersFromFile();
            
            Collections.sort(users, new idComparator());
            for (User user : users) {    
                usersBox.addItem(user.getLogin());
            }
            setVisible(true);
            lw.dispose();
            versCont = new VersionController();
            versCont.checkVersion();
            
            break;
        }
      passwordField.grabFocus(); 
    }
    private void passwordFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldKeyTyped
      
        checkButtonEnabled();
        
    }//GEN-LAST:event_passwordFieldKeyTyped

    private void authorization(){
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        User u =Team.getUserFromList(usersBox.getItemAt(usersBox.getSelectedIndex()));
        boolean passOK = false;
        
        if(u.getPassword().length == psw.length){
            passOK = true;
            for (int i = 0; i < psw.length; i++) {
                if(psw[i]!=u.getPassword()[i]) passOK = false;
            }
        }
        if(passOK){
            Team.setActiveUser(u);
            new ActionMenuWindow().setVisible(true);
            setVisible(false);
        }
        else 
        {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            JOptionPane.showMessageDialog(this, "Неверный пароль", "Ошибка авторизации", JOptionPane.ERROR_MESSAGE);
            passwordField.grabFocus();
            passwordField.selectAll();
        }
    
    }
    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        loginButton.setEnabled(false);
        switch(loginButton.getText()){
        case "Регистрация":
            new PasswordWindow(this, Team.getUserFromList(usersBox.getItemAt(usersBox.getSelectedIndex())), client, true).setVisible(true);
            usersBox.setSelectedIndex(0);
            loginButton.setEnabled(true);
            break;
        case "ВОЙТИ": 
            authorization();
            loginButton.setEnabled(true);
            break;
    }
    }//GEN-LAST:event_loginButtonActionPerformed

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldActionPerformed

    private void passwordFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordFieldMouseClicked
    if(evt.getButton()==3){
        passwordField.setText(getStringFromBuffer());
        checkButtonEnabled();
    }  
    }//GEN-LAST:event_passwordFieldMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       SystemOptions.windowClosing();       
    }//GEN-LAST:event_formWindowClosing

    private void passwordFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldKeyPressed
        if(loginButton.isEnabled()){
            loginButton.setEnabled(false);
            switch (evt.getKeyCode()){
                case 10:
                    {
                    authorization();
                    break;
                    }
                case 65:
                    if(evt.isControlDown()){
                        passwordField.selectAll();
                        evt.consume();}
                    break;
            }       
            loginButton.setEnabled(true);
        }
    }//GEN-LAST:event_passwordFieldKeyPressed

    private void passwordFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldKeyReleased
         psw = passwordField.getPassword();       
    }//GEN-LAST:event_passwordFieldKeyReleased

    private void usersBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_usersBoxItemStateChanged

        if(!Team.getUserFromList(usersBox.getItemAt(usersBox.getSelectedIndex())).isRegistered() && evt.getStateChange()==ItemEvent.SELECTED){
            usersBox.hidePopup();
            passwordField.setText("");
            passwordField.setEnabled(false);
            loginButton.setEnabled(true);
            loginButton.setText("Регистрация");
            loginButton.setForeground(color);
            loginButton.setFont(fontReg);
            JOptionPane.showMessageDialog(this, "После критического обновления " + versCont.getRegFailure() + " необходимо вновь зарегистрироваться. \n"
                    + "Это норма. Просто заново вбей свой пароль.", "Критическое обновление", JOptionPane.PLAIN_MESSAGE);
        }
        else{
            passwordField.setText("");
            passwordField.setEnabled(true);
            loginButton.setEnabled(false);
            loginButton.setText("ВОЙТИ");
            loginButton.setForeground(Color.GRAY);
            loginButton.setFont(fontLog);
        }     
    }//GEN-LAST:event_usersBoxItemStateChanged
    private void usersBoxCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_usersBoxCaretPositionChanged
        
    }//GEN-LAST:event_usersBoxCaretPositionChanged

    private void jPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseEntered

    }//GEN-LAST:event_jPanel1MouseEntered

    private void settingsLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsLabelMouseEntered
        settingsLabel.setFont(new Font("Tahoma", 1, 11));
        settingsLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));        
    }//GEN-LAST:event_settingsLabelMouseEntered

    private void settingsLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsLabelMouseExited
       settingsLabel.setFont(new Font("Tahoma", 0, 11));
    }//GEN-LAST:event_settingsLabelMouseExited

    private void settingsLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsLabelMouseClicked
        UIManager.put("OptionPane.yesButtonText"   , "Продолжить...");
        UIManager.put("OptionPane.noButtonText"    , "Отмена");
        int res;
        if(connected){
            res = JOptionPane.showConfirmDialog(this, "Если ты не знаешь, что делать, не лазь сюда.\n"
                    + "Если все  нормально работает, тоже не лазь!\n"
                    + "Кстати, программе кажется, что сейчас все ОК\n"
                    + "Из любопытства можешь и посмотреть, что там ничего интересного,\n"
                    + "только НИЧЕГО НЕ ТРОГАЙ!!!", "Оно тебе надо?..", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, iconPling);
            
        }
        else res = 0;
        if(res==0) new ConnectionOptionsWindow().setVisible(true);
    }//GEN-LAST:event_settingsLabelMouseClicked

    private void connectionErrorLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_connectionErrorLabelMouseEntered
        connectionErrorLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        connectionErrorLabel.setFont(new Font("Tahoma", 1, 14));
        
    }//GEN-LAST:event_connectionErrorLabelMouseEntered

    private void connectionErrorLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_connectionErrorLabelMouseExited
        connectionErrorLabel.setFont(new Font("Tahoma", 0, 14));
    }//GEN-LAST:event_connectionErrorLabelMouseExited

    private void connectionErrorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_connectionErrorLabelMouseClicked
        UIManager.put("OptionPane.yesButtonText"   , "Да");
        UIManager.put("OptionPane.noButtonText"    , "Нет");
        if(JOptionPane.showConfirmDialog(this, "Попробовать соединиться еще раз?"
                  , "Повтор соединения", JOptionPane.YES_NO_OPTION) == 0)
            login();
    }//GEN-LAST:event_connectionErrorLabelMouseClicked

  
    public static void main(String args[]) {
        ToolTipManager.sharedInstance().setEnabled(false);
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
  

  
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if(SystemOptions.getInstance().isDebugPrint())System.out.println("ЗАГРУЗКА...");
                new LoginWindow().setVisible(true);
                
            }
        });
    }

  class idComparator implements Comparator<User>{

        public int compare(User u1, User u2) {
            return u1.getNumber() - u2.getNumber();
                    
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel connectionErrorLabel;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JDialog jDialog3;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel settingsLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JComboBox<String> usersBox;
    private javax.swing.JLabel versionLabel;
    // End of variables declaration//GEN-END:variables
}
