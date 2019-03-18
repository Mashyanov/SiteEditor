
package javaapplication58.windows;

import java.awt.Cursor;
import javaapplication58.Chapter;
import javaapplication58.MyFTPClient;
import javaapplication58.Rules;
import javaapplication58.Team;
import javaapplication58.SystemOptions;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class AddOrEditChapterWindow extends javax.swing.JDialog {
private Chapter chapter;
public static final int REGULATION_WINDOW = 0;
public static final int RULES_WINDOW = 1;
private int windowType;
private final SystemOptions options = SystemOptions.getInstance();
private final boolean creatingNewChapter;
private final int MAX_STRING_SIZE = 654;
private final MyFTPClient client = MyFTPClient.getInstace();

    public AddOrEditChapterWindow(java.awt.Frame parent, boolean modal, int windowType) {
        super(parent, modal);
        this.windowType = windowType;
        initComponents();
        headLabel.setText("Добавление новой главы");
        creatingNewChapter = true;
        errorLabel.setVisible(false);
        bodyTextArea.setSize(MAX_STRING_SIZE, bodyTextArea.getHeight());
        setTitle("Добавление новой главы");
        if(options.isCatchPrint())System.out.println("Окно работы с главами");
        
    }

    public AddOrEditChapterWindow(java.awt.Frame parent, boolean modal, int windowType, Chapter chapter) {
        super(parent, modal);
        this.windowType = windowType;
        initComponents();
        setTitle(chapter.getName() + " - редактирование");
        creatingNewChapter = false;
        this.chapter = chapter;
        headLabel.setText(chapter.getName() + " - редактирование");
        okButton.setEnabled(true);
        headTextField.setText(chapter.getName());
        bodyTextArea.setSize(MAX_STRING_SIZE, bodyTextArea.getHeight());
        if(!chapter.getBody().isEmpty())
            bodyTextArea.setText(chapter.getBody());
        errorLabel.setVisible(bodyTextArea.getWidth() > MAX_STRING_SIZE);
        bodyTextArea.moveCaretPosition(0);
        
    }

    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headTextField = new javax.swing.JTextField();
        headLabel = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        nameLabel = new javax.swing.JLabel();
        bodyLabel = new javax.swing.JLabel();
        errorLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        bodyTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(options.ICON_MAIN);
        setResizable(false);

        headTextField.setSelectionColor(new java.awt.Color(94, 93, 1));
        headTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                headTextFieldKeyTyped(evt);
            }
        });

        headLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        headLabel.setForeground(new java.awt.Color(94, 93, 1));
        headLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headLabel.setText("jLabel1");

        okButton.setText("Сохранить");
        okButton.setEnabled(false);
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Отмена");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        nameLabel.setText("Название главы");

        bodyLabel.setText("Содержание главы");

        errorLabel.setForeground(new java.awt.Color(255, 0, 0));
        errorLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        errorLabel.setText("Одна или несколько строк в разделе слишком длинные!");

        bodyTextArea.setColumns(20);
        bodyTextArea.setRows(5);
        bodyTextArea.setMargin(new java.awt.Insets(2, 0, 2, 0));
        bodyTextArea.setSelectionColor(new java.awt.Color(94, 93, 1));
        bodyTextArea.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                bodyTextAreaComponentResized(evt);
            }
        });
        jScrollPane2.setViewportView(bodyTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bodyLabel)
                            .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(headLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cancelButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 496, Short.MAX_VALUE)
                                        .addComponent(okButton))
                                    .addComponent(headTextField, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headLabel)
                .addGap(18, 18, 18)
                .addComponent(nameLabel)
                .addGap(3, 3, 3)
                .addComponent(headTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bodyLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorLabel)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton))
                .addGap(5, 5, 5))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void headTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_headTextFieldKeyTyped
        okButton.setEnabled(!headTextField.getText().isEmpty());
    }//GEN-LAST:event_headTextFieldKeyTyped

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        UIManager.put("OptionPane.yesButtonText", "Да");
        UIManager.put("OptionPane.noButtonText", "Нет");
        if(((errorLabel.isVisible() && JOptionPane.showConfirmDialog(this, 
                "Одна или несколько строк в разделе слишком длинные!\n"
                        + "Это не критично, но будет кривоватенько смотреться на сайте.\n"
                        + "Сохранить раздел, как есть?", "Подтверждение действия", 
                        JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE)==0 ) 
                || ( !errorLabel.isVisible() )) ){
            
                    if(creatingNewChapter){
                        chapter = new Chapter(headTextField.getText());
                        if(!bodyTextArea.getText().isEmpty())
                            chapter.setBody(bodyTextArea.getText());
                        switch(windowType){
                            case 0:
                                Team.REGULATION.addChapter(chapter);
                                break;
                            case 1:
                                Team.RULES.addChapter(chapter);
                                break;
                        }
                    }
                    else{
                        chapter.setName(headTextField.getText());
                        if(!bodyTextArea.getText().isEmpty())
                            chapter.setBody(bodyTextArea.getText());
                    }
                               
            dispose();
        }
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        UIManager.put("OptionPane.yesButtonText", "Да");
        UIManager.put("OptionPane.noButtonText", "Нет");
        if(JOptionPane.showConfirmDialog(this, "Точно хочешь закрыть это окно?\n"
                + "Все внесенные изменения будут утеряны", "Подтверждение действия", 
                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE)==0)
            dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void bodyTextAreaComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_bodyTextAreaComponentResized
        errorLabel.setVisible(evt.getComponent().getWidth() > MAX_STRING_SIZE);
       
    }//GEN-LAST:event_bodyTextAreaComponentResized

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bodyLabel;
    private javax.swing.JTextArea bodyTextArea;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel headLabel;
    private javax.swing.JTextField headTextField;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables
}
