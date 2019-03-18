
package javaapplication58.windows;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javaapplication58.SystemOptions;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class ConnectionOptionsWindow extends javax.swing.JFrame {
   
   private final SystemOptions options = SystemOptions.getInstance();
   private String host, login, password;
   private boolean firewall, dPrint, cPrint, catchPrint;
   private ObjectInputStream ois;
   private ObjectOutputStream oos;
   private final Icon iconOk = new ImageIcon("images/sys/ok.png");
   
   public ConnectionOptionsWindow() {
       
        initComponents();
        setTitle("Системные настройки");
        host       = options.getHost();
        login      = options.getLogin();
        password   = options.getPassword();
        firewall   = options.isFirewallCutOff();
        cPrint     = options.isConnectionPrint();//не используется
        dPrint     = options.isDebugPrint();
        catchPrint = options.isCatchPrint();
        hostField.setText(host);
        loginField.setText(login);
        passwordField.setText(password);
        firewallCheckBox.setSelected(firewall);
        debugPrinting.setSelected(dPrint);
        catchPrinting.setSelected(catchPrint);
        catchPrinting.setEnabled(dPrint);
        if(options.isCatchPrint())System.out.println("Системные настройки");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hostField = new javax.swing.JTextField();
        loginField = new javax.swing.JTextField();
        loginLabel = new javax.swing.JLabel();
        hostLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        headLabel = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        passwordField = new javax.swing.JPasswordField();
        firewallCheckBox = new javax.swing.JCheckBox();
        debugPrinting = new javax.swing.JCheckBox();
        catchPrinting = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setIconImage(options.ICON_MAIN);
        setResizable(false);

        hostField.setSelectionColor(new java.awt.Color(94, 93, 1));

        loginField.setSelectionColor(new java.awt.Color(94, 93, 1));

        loginLabel.setText("Логин");

        hostLabel.setText("Хост");

        passwordLabel.setText("Пароль");

        headLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        headLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headLabel.setText("Настройка подключения и консоли");

        cancelButton.setText("Отмена");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        okButton.setText("ОК");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        passwordField.setSelectionColor(new java.awt.Color(94, 93, 1));

        firewallCheckBox.setText("Обход Firewall");

        debugPrinting.setText("Отладочная печать");
        debugPrinting.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                debugPrintingItemStateChanged(evt);
            }
        });

        catchPrinting.setText("Печать логов обработанных ошибок");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(catchPrinting, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hostField)
                            .addComponent(loginField)
                            .addComponent(headLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                            .addComponent(passwordField)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(firewallCheckBox)
                                    .addComponent(loginLabel)
                                    .addComponent(hostLabel)
                                    .addComponent(passwordLabel)
                                    .addComponent(debugPrinting))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headLabel)
                .addGap(20, 20, 20)
                .addComponent(hostLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hostField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(loginLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(passwordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(firewallCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(debugPrinting)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(catchPrinting)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
       try {
           if(oos!=null) oos.close();
           if(ois!=null) ois.close();
           
       } catch (IOException ex){
           if(options.isCatchPrint()) System.out.println(ex.getMessage());
           JOptionPane.showMessageDialog(this, "Ошибка при заврытии потока ввода или вывода\n" + ex.getMessage(), "ошибка", JOptionPane.ERROR_MESSAGE);}
        
       dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        String password = new String(passwordField.getPassword());
        if(!(options.getHost().equals(hostField.getText()) && options.getLogin().equals(loginField.getText()) && 
                options.getPassword().equals(password) && firewallCheckBox.isSelected()==firewall)){
            options.setOptions(hostField.getText(), loginField.getText(), password, 
                firewallCheckBox.isSelected(), debugPrinting.isSelected(), catchPrinting.isSelected());
            
            JOptionPane.showMessageDialog(this, "Внесенные данные приняты! \nЧтобы изменения вступили в силу, программу надо перезагрузить...\n"
                   + "После нажатия \"ОК\" она будет закрыта.", "Сохранение изменений", JOptionPane.WARNING_MESSAGE, iconOk);
            System.exit(0);
        }
        else if(!(debugPrinting.isSelected() == dPrint && catchPrinting.isSelected() == catchPrint)){
            options.setDebugPrint(debugPrinting.isSelected());
            options.setCatchPrint(catchPrinting.isSelected());
            JOptionPane.showMessageDialog(this, "Внесенные данные приняты!", "Сохранение изменений", JOptionPane.WARNING_MESSAGE, iconOk);
        }
        dispose();      
    }//GEN-LAST:event_okButtonActionPerformed

    private void debugPrintingItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_debugPrintingItemStateChanged
        catchPrinting.setEnabled(debugPrinting.isSelected());
        catchPrinting.setSelected(debugPrinting.isSelected());
    }//GEN-LAST:event_debugPrintingItemStateChanged
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JCheckBox catchPrinting;
    private javax.swing.JCheckBox debugPrinting;
    private javax.swing.JCheckBox firewallCheckBox;
    private javax.swing.JLabel headLabel;
    private javax.swing.JTextField hostField;
    private javax.swing.JLabel hostLabel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField loginField;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    // End of variables declaration//GEN-END:variables
}
