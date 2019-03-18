
package javaapplication58.windows;

import java.awt.Cursor;
import java.io.File;
import javaapplication58.Member;
import javaapplication58.MyFTPClient;
import javaapplication58.MyImageEditor;
import javaapplication58.PageManager;
import javaapplication58.SystemOptions;
import javaapplication58.Team;


public class AddNewFighterWindow extends javax.swing.JDialog {

private final MyFTPClient client = MyFTPClient.getInstace();
private final PageManager pageManager;
File defaultImage = new File("images/sys/nopic.jpg");

 
    public AddNewFighterWindow(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        if(SystemOptions.getInstance().isDebugPrint())System.out.println("Добавление нового члена команды");
        
        this.pageManager = new PageManager();
    }
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        labelName = new javax.swing.JLabel();
        textfieldName = new javax.swing.JTextField();
        labelCallsign = new javax.swing.JLabel();
        textfieldCallsign = new javax.swing.JTextField();
        buttonCancel = new javax.swing.JButton();
        buttonAdd = new javax.swing.JButton();
        errorLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Добавление нового бойца");

        labelName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelName.setForeground(new java.awt.Color(51, 51, 51));
        labelName.setText("Имя(только русские буквы)");

        textfieldName.setSelectionColor(new java.awt.Color(94, 93, 1));
        textfieldName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfieldNameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textfieldNameKeyTyped(evt);
            }
        });

        labelCallsign.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelCallsign.setForeground(new java.awt.Color(51, 51, 51));
        labelCallsign.setText("Позывной(только русские буквы)");

        textfieldCallsign.setSelectionColor(new java.awt.Color(94, 93, 1));
        textfieldCallsign.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfieldCallsignKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textfieldCallsignKeyTyped(evt);
            }
        });

        buttonCancel.setText("Отмена");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        buttonAdd.setText("Добавить");
        buttonAdd.setEnabled(false);
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });

        errorLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        errorLabel.setForeground(new java.awt.Color(255, 0, 0));
        errorLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        errorLabel.setText("Введи данные бойца ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonAdd))
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelCallsign, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textfieldName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textfieldCallsign)
                            .addComponent(labelName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(errorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textfieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelCallsign)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textfieldCallsign, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(errorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void textfieldNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfieldNameKeyReleased
        buttonAdd.setEnabled(checkInputData());   
    }//GEN-LAST:event_textfieldNameKeyReleased

    private void textfieldCallsignKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfieldCallsignKeyReleased
        buttonAdd.setEnabled(checkInputData());   
    }//GEN-LAST:event_textfieldCallsignKeyReleased

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        dispose();
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        Member m = new Member(textfieldName.getText(), textfieldCallsign.getText());
        MyImageEditor imageEditor = new MyImageEditor();
        m.addToTeam();
        Team.saveTeamToFile();
        client.uploadTeamDataFile();
        pageManager.editCandidatePage(m);
        pageManager.editNavigationPage();
        pageManager.editMainPage();
        imageEditor.editAvatarPicture(defaultImage, m.getCallSign(), m.getLink());
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        dispose();
        
        
    }//GEN-LAST:event_buttonAddActionPerformed

    private void textfieldNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfieldNameKeyTyped
      Team.eliminateEnglishLetters(evt);
    }//GEN-LAST:event_textfieldNameKeyTyped

    private void textfieldCallsignKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfieldCallsignKeyTyped
      Team.eliminateEnglishLetters(evt);
    }//GEN-LAST:event_textfieldCallsignKeyTyped
   
    
   private boolean checkInputData(){
       boolean nameOk = false;
       //ПРОВЕРЯЕМ ИМЯ
        String s = textfieldName.getText();
        if(s.length()<2) {
            errorLabel.setText("Слишком короткое имя ");
            return false;}
        char[] c = s.toCharArray();
        c[0] = Character.toUpperCase(c[0]);
        for (int i = 1; i < c.length; i++) 
            c[i] = Character.toLowerCase(c[i]);
        for (char d : c) {
            nameOk = false;
            for (char e : Team.RUSSIAN_LETTERS) {
                if(d==e)nameOk = true;
            }
            if(!nameOk) return false;
        }
            
        s = "";
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') {
                errorLabel.setText("Имя не должно содержать пробелов ");
                return false;
            }
            s+=c[i];
        }
        textfieldName.setText(s);
        
//ПРОВЕРЯМ ПОЗЫВНОЙ       

        s = textfieldCallsign.getText();
       
        if(s.length()<2) {
            errorLabel.setText("Слишком короткий позывной ");          
            return false; 
        }
        c = s.toCharArray();
        c[0] = Character.toUpperCase(c[0]);
        for (int i = 1; i < c.length; i++) 
            c[i] = Character.toLowerCase(c[i]);
        s = "";
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') {
            errorLabel.setText("Позывной не должен содержать пробелов ");
                return false;
            }
            s+=c[i];
            }
        
        for (Member m : Team.getTeamMembers()) {
            if(s.equals(m.getCallSign())){
            errorLabel.setText("Позывной уже занят ");      
            return false;}
       }
       textfieldCallsign.setText(s);
       errorLabel.setText("");
       return true;
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonCancel;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelCallsign;
    private javax.swing.JLabel labelName;
    private javax.swing.JTextField textfieldCallsign;
    private javax.swing.JTextField textfieldName;
    // End of variables declaration//GEN-END:variables
}
