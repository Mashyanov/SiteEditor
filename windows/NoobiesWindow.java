
package javaapplication58.windows;
import java.awt.Desktop;
import java.awt.Font;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication58.HTMLDefaults;
import javaapplication58.MyFTPClient;
import javaapplication58.PageManager;
import javaapplication58.SystemOptions;
import javaapplication58.Team;
import javaapplication58.panels.LinkHelpPanel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class NoobiesWindow extends javax.swing.JFrame {

            
    private final MyFTPClient client = MyFTPClient.getInstace();
    private final PageManager pageManager = new PageManager();
    private final SystemOptions options = SystemOptions.getInstance();
    public NoobiesWindow() {
        initComponents();
        WantedCheckBox.setSelected(Team.isNoobiesWanted());
        linkCheckBox.setSelected(Team.isNoobLinkShowing());
        linkTextField.setText(Team.getNoobVkLink());
        checkInputs();
    }

    private void checkInputs(){
        if(WantedCheckBox.isSelected())
        {
            linkLabel.setEnabled(true);
            linkTextField.setEnabled(true);
            bodyTextArea.setText(Team.getNoobYesText());
        }
            
        else 
        {
            bodyTextArea.setText(Team.getNoobNoText());
            linkLabel.setEnabled(false);
            linkTextField.setEnabled(false);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headLabel = new javax.swing.JLabel();
        WantedCheckBox = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        bodyTextArea = new javax.swing.JTextArea();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        linkTextField = new javax.swing.JTextField();
        bodyLabel = new javax.swing.JLabel();
        linkLabel = new javax.swing.JLabel();
        linkCheckBox = new javax.swing.JCheckBox();
        helpLabel = new javax.swing.JLabel();
        defaultButton = new javax.swing.JButton();
        openButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Набор в Команду");
        setIconImage(options.ICON_MAIN);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        headLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        headLabel.setForeground(new java.awt.Color(94, 93, 1));
        headLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headLabel.setText("Набор в команду");

        WantedCheckBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        WantedCheckBox.setText("Набор открыт");
        WantedCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                WantedCheckBoxStateChanged(evt);
            }
        });

        bodyTextArea.setColumns(20);
        bodyTextArea.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        bodyTextArea.setRows(5);
        bodyTextArea.setText(" Наша команда всегда рада принять в свои ряды достойных бойцов!\n\nМы хотим видеть в своих рядах активных, адекватных, спортивных молодых людей, славян, в возрасте от \n20-ти лет. Бойцов, готовых посещать командные мероприятия минимум 2 раза в месяц. Способных в ближайшие \nсроки купить минимально-необходимый комплект снаряжения.\n\nБольшинство бойцов проживает на Юго-Западе Санкт-Петербурга. Средний возраст бойцов в команде - 30 лет.\n\nМы хотим собрать единомышленников не только для выезда на игры, но и совместного досуга вне страйкбола: \nвместе отмечать праздники, ходить в спортзал и т.д. У команды есть свой полигон для тренировок и \nпроведения игр.\n\n     Если вы хотите присоединиться к нашему дружному коллективу, то учтите, что:\n\n- В команде собираются ежеквартальные материальные взносы, в том числе и с кандидатов;\n- Мы выезжаем на игры и тренировки круглый год, а не только в теплый сезон;\n- Мы не боимся запачкаться с ног до головы и промокнуть до нитки; \n- Кандидаты в конце испытательного срока сдают обязательный экзамен по ОФП; \n- Команда - это семья! Это единый организм, а не сборище одиночек! У нас один за всех, и все за одного!\n\n- Еще раз ВНИМАТЕЛЬНО ознакомьтесь с правилами страйкбола, нашим Уставом , требованиями по снаряжению   \n  и вооружению. \n- Обратитесь к Старшине команды в ВК, он расскажет Вам, как действовать дальше, и ответит на все вопросы, \n  которых у вас наверняка накопилось довольно много:");
        bodyTextArea.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        bodyTextArea.setMargin(new java.awt.Insets(2, 4, 2, 2));
        bodyTextArea.setSelectionColor(new java.awt.Color(94, 93, 1));
        jScrollPane1.setViewportView(bodyTextArea);

        okButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        okButton.setText("ОК");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cancelButton.setText("Отмена");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        bodyLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bodyLabel.setText("Текст на сайте");

        linkLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        linkLabel.setText("Ссылка на страницу VK ответственного за прием");

        linkCheckBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        linkCheckBox.setText("Заменять ключевые слова гиперссылками");
        linkCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linkCheckBoxActionPerformed(evt);
            }
        });

        helpLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        helpLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        helpLabel.setText("(что это значит?)");
        helpLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                helpLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                helpLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                helpLabelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                helpLabelMousePressed(evt);
            }
        });

        defaultButton.setText("Вернуть значения по умолчанию");
        defaultButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defaultButtonActionPerformed(evt);
            }
        });

        openButton.setText("Открыть раздел на сайте");
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(linkLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(headLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bodyLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(WantedCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linkCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(helpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 166, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addComponent(linkTextField)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(defaultButton, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(openButton, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(WantedCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(linkCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(helpLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bodyLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(linkLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linkTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton)
                    .addComponent(defaultButton)
                    .addComponent(openButton))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void WantedCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_WantedCheckBoxStateChanged
        checkInputs();
    }//GEN-LAST:event_WantedCheckBoxStateChanged

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        new ActionMenuWindow().setVisible(true);
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        if(WantedCheckBox.isSelected()){
            Team.setNoobYesText(bodyTextArea.getText());
            Team.setNoobVkLink(linkTextField.getText());
        }
        else Team.setNoobNoText(bodyTextArea.getText());
        Team.setNoobiesWanted(WantedCheckBox.isSelected());
        Team.setNoobLinkShowing(linkCheckBox.isSelected());
        Team.setNoobLinkShowing(linkCheckBox.isSelected());

        Team.saveNoobiesToFile();
        client.uploadNoobDataFile();
        pageManager.editNoobiesPage(WantedCheckBox.isSelected(), linkCheckBox.isSelected());
        new ActionMenuWindow().setVisible(true);
        dispose();        
    }//GEN-LAST:event_okButtonActionPerformed

    private void helpLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpLabelMouseEntered
        helpLabel.setFont(new Font("Tahoma", 3, 14));
    }//GEN-LAST:event_helpLabelMouseEntered

    private void helpLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpLabelMouseExited
        helpLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
    }//GEN-LAST:event_helpLabelMouseExited

    private void helpLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpLabelMouseClicked
        JOptionPane.showMessageDialog(this, new LinkHelpPanel(), "Что такое гиперссылка", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_helpLabelMouseClicked

    private void helpLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpLabelMousePressed
        JOptionPane.showMessageDialog(this, new LinkHelpPanel(), "Что такое гиперссылка", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_helpLabelMousePressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        SystemOptions.windowClosing();
    }//GEN-LAST:event_formWindowClosing

    private void linkCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linkCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_linkCheckBoxActionPerformed

    private void defaultButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_defaultButtonActionPerformed
        UIManager.put("OptionPane.yesButtonText"   , "Да");
        UIManager.put("OptionPane.noButtonText"    , "Нет");
        if(JOptionPane.showConfirmDialog(this, "Вернуть значения по умолчанию?\n"
                + "Все внесенные изменения будут утрачены!", "Подтверждение действия", 
                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE)==0){
            Team.setNoobLinkShowing(true);
            Team.setNoobNoText(HTMLDefaults.NOOBIES_NOT_WANTED);
            Team.setNoobYesText(HTMLDefaults.NOOBIES_WANTED);
            Team.setNoobVkLink(HTMLDefaults.NOOBIES_CONTACT_LINK);
            new NoobiesWindow().setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_defaultButtonActionPerformed

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        try {
            Desktop.getDesktop().browse(new URI("http://www.sk-bron.ru/noobies.html"));
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(NoobiesWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_openButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox WantedCheckBox;
    private javax.swing.JLabel bodyLabel;
    private javax.swing.JTextArea bodyTextArea;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton defaultButton;
    private javax.swing.JLabel headLabel;
    private javax.swing.JLabel helpLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox linkCheckBox;
    private javax.swing.JLabel linkLabel;
    private javax.swing.JTextField linkTextField;
    private javax.swing.JButton okButton;
    private javax.swing.JButton openButton;
    // End of variables declaration//GEN-END:variables
}
