
package javaapplication58.panels;

import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javaapplication58.SystemOptions;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VideoHelpPanel extends javax.swing.JPanel {
private int counter = 0;
private javaapplication58.SystemOptions options = SystemOptions.getInstance();
private Image v01, v02, v03, v04, v05, v06;   

public VideoHelpPanel() {
    initComponents();
    jPanel1.grabFocus();
    try {
        v01  = ImageIO.read(new FileImageInputStream(new File("images/sys/v01.png")));
        v02  = ImageIO.read(new FileImageInputStream(new File("images/sys/v02.png")));
        v03  = ImageIO.read(new FileImageInputStream(new File("images/sys/v03.png")));
        v04  = ImageIO.read(new FileImageInputStream(new File("images/sys/v04.png")));
        v05  = ImageIO.read(new FileImageInputStream(new File("images/sys/v05.png")));
        v06  = ImageIO.read(new FileImageInputStream(new File("images/sys/v06.png")));
    } catch (IOException ex) {
        if(options.isCatchPrint()) System.out.println(ex.getMessage());
        JOptionPane.showMessageDialog(new JPanel(), "Ошибка при создании окна опций\n" + ex.getMessage(), "ошибка", JOptionPane.ERROR_MESSAGE);
    }
        fillThePanel();
    }
    
    private final void fillThePanel(){
    switch(counter){
            case 0:
                
                helpLabel.setIcon(null);
                helpLabel.setFont(new Font("Tahoma", 0, 18));
                helpLabel.setHorizontalTextPosition(JLabel.CENTER);
                helpLabel.setHorizontalAlignment(JLabel.CENTER);
                helpLabel.setText("<html><p>У нас дешевенький хостинг, места имеется мало, <br>"
                        + "поэтому нет никаких рациональных причин заливать <br>видео к "
                        + "нам на сервер и хранить их там, тратя на них <br>драгоценные мегабайты, когда есть <strong>YouTube. </strong></p><br>"
                        + "<p>Можно залить свое видео туда и встроить на сайт,  <br> или взять "
                        + "любое понравившееся чужое, и так же <br>встроить к нам в галерею. Это только звучит  очень <br>сложно, но на самом "
                        + "деле - быстро и почти не больно :) <br></p><br>"
                        + "<p>Итак, как это легко и просто сделать, я и расскажу <br>дальше!</p></html>");
                backButton.setEnabled(false);
                backButton.setVisible(false);
                break;
            case 1:
                helpLabel.setHorizontalTextPosition(JLabel.RIGHT);
                helpLabel.setHorizontalAlignment(JLabel.LEADING);
                helpLabel.setFont(new Font("Tahoma", 0, 14));
                helpLabel.setVerticalTextPosition(JLabel.NORTH);
                helpLabel.setText("<html><p>Открываем на <b>YouTube</b> нужное нам видео, и прямо под ним справа находим  кнопку <b>ПОДЕЛИТЬСЯ</b>.<br><br></p>"
                        + "<p>Смело кликаем по ней!</p><br>"
                        + "<p><i>Если ничего не происходит, а только на белом фоне крутится "
                        + "серое колесико, то стоит просто обновить страницу с видео. Это глюк Ютуба, у него бывает "
                        + "случается такое залипание</i></p><br></html>");
                helpLabel.setIcon(new ImageIcon(v01));
                backButton.setEnabled(true);
                backButton.setVisible(true);
                break;
            case 2:
                helpLabel.setVerticalTextPosition(JLabel.NORTH);
                helpLabel.setText("<html><p>В открывшемся меню, в разделе \"Отправить ссылку\", "
                        + "есть круглая серая кнопка <b><ВСТРОИТЬ</b></p><br>"
                        + "<p>По ней кликаем еще смелее!</p><br>"
                        + "<p><i>Если это меню не открывается, а только на белом фоне крутится "
                        + "серое колесико, то стоит просто обновить страницу с видео. Это глюк Ютуба, у него бывает "
                        + "случается такое залипание</i></p><br></html>");
                helpLabel.setIcon(new ImageIcon(v02));
                break;
            case 3:
                helpLabel.setText("<html>Справа, на белом фоне, появится меню настроек с четыремя галочками.<br>"
                        + "Их лучше расставить, как у меня на картинке.<br>"
                        + "Это даст наилучший функционал на нашем сайте без перегрузки интерфейса видео "
                        + "всякой ненужной лабудой</html>");
                helpLabel.setIcon(new ImageIcon(v03));
                break;
            case 4:
                helpLabel.setText("<html>Теперь все там же справа, на белом фоне, "
                        + "над меню настроек с четыремя галочками,нас дожидается <b>готовая ссылка</b>, уже учитывающая выбранные тобой галочки!<br>"
                        + "Копируем ее <b><ins>ВСЮ ЦЕЛИКОМ</ins></b> и...</html>");
                helpLabel.setIcon(new ImageIcon(v04));
                break;
            case 5:
                helpLabel.setHorizontalAlignment(JLabel.LEFT);
                helpLabel.setText("<html>...и вставляем в нашей программе в строку "
                        + "<b>ССЫЛКА НА ВИДЕО</b>. <br>Ссылка длинная, она может даже целиком не "
                        + "отображаться в этой строке, но это нормально.<br>"
                        + "Нам осталось только нажать кнопку <b>СОХРАНИТЬ</b> и проверить, появилось ли "
                        + "видео в Галерее на сайте!<br><br>"
                        + "<i>Если ты все сделал правильно и скопипастил ссылку целиком, не появиться оно "
                        + "просто не может!  </i></html>");
                
                helpLabel.setIcon(new ImageIcon(v05));
                forwardButton.setEnabled(true);
                forwardButton.setVisible(true);
                break;
            case 6:
                helpLabel.setHorizontalAlignment(JLabel.CENTER);
                helpLabel.setText("");
                helpLabel.setIcon(new ImageIcon(v06));
                forwardButton.setEnabled(false);
                forwardButton.setVisible(false);
                break;
            default:
                backButton.setEnabled(true);
                forwardButton.setEnabled(true);
        }
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        helpLabel = new javax.swing.JLabel();
        forwardButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel1KeyPressed(evt);
            }
        });

        helpLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        helpLabel.setToolTipText("");
        helpLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        forwardButton.setText("Далее>>");
        forwardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forwardButtonActionPerformed(evt);
            }
        });

        backButton.setText("<<Назад");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(helpLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 608, Short.MAX_VALUE)
                        .addComponent(forwardButton)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(helpLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(forwardButton)
                    .addComponent(backButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void forwardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forwardButtonActionPerformed
        counter++;
        fillThePanel();
    }//GEN-LAST:event_forwardButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        counter--;
        fillThePanel();      
    }//GEN-LAST:event_backButtonActionPerformed

    private void jPanel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyPressed
        
    }//GEN-LAST:event_jPanel1KeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
       
    }//GEN-LAST:event_formKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton forwardButton;
    private javax.swing.JLabel helpLabel;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
