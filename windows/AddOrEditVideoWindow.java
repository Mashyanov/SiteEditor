
package javaapplication58.windows;

import javaapplication58.PageManager;
import javaapplication58.SystemOptions;
import javaapplication58.MyFTPClient;
import javaapplication58.GalleryVideo;
import javaapplication58.Gallery;
import java.awt.Cursor;
import java.awt.Font;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class AddOrEditVideoWindow extends javax.swing.JDialog {
private final MyFTPClient client = MyFTPClient.getInstace();
private final SystemOptions options =SystemOptions.getInstance();
private final PageManager pageManager = new PageManager();
private boolean creatingNewVideo;
private GalleryVideo video = null;
private final LinkedList<GalleryVideo> videos = Gallery.getGalleryVideos();
    
    /**Окно создания нового видео
     * @param parent родительский элемент окна. Указывать <i>this</i>.
     * @param modal  флаг модальности*/
    public AddOrEditVideoWindow(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        creatingNewVideo = true;
        initComponents();
        headLabel.setText("Добавление нового видео");
        checkInputData();
        if(options.isCatchPrint())System.out.println("Окно работы с видео");
    }

    /**Окно редактирования уже существующего видео.
     * @param parent родительский элемент окна. Указывать <i>this</i>.
     * @param modal  флаг модальности
     * @param video  видео для редактирования*/
    public AddOrEditVideoWindow(java.awt.Frame parent, boolean modal, GalleryVideo video) {
        super(parent, modal);
        creatingNewVideo = true;
        initComponents();
        nameTextField.setText(video.getName());
        linkTextField.setText(video.getLink());
        creatingNewVideo = false;
        headLabel.setText("Редактирование видео \"" + video.getName()+ "\"");
        this.video = video;
        checkInputData();
    }
    
    /**Метод проверяет верность введеных данных:<br><ol>
     * <li>Введенное имя не пустое и не конфликтует с именами других видео</li>
     * <li>Введенная ссылка не пустая</li> </ol>
     * и, в зависимоти от результатов, делает кнопку <b>СОХРАНИТЬ</b> активной 
     * либо не активной, а так же прописывает в <i>errorLabel</i> все найденные ошибки,
     * если таковые есть, и делает ее видимой для пользователя, либо скрывает ее, 
     * если введенные пользователем данные верны и ошибок не найдено */
    private boolean checkInputData(){
        boolean allIsFine = true;
        StringBuilder stb = new StringBuilder();
        for (GalleryVideo v : videos) {
            if(( creatingNewVideo &&nameTextField.getText().equals(v.getName()) ) 
                    || ( !creatingNewVideo && nameTextField.getText().equals(v.getName()) && 
                    !nameTextField.getText().equals(video.getName())) ){
                stb.append("Видео с таким именем уже существует. ");
                allIsFine = false;
            }
        }

        if(linkTextField.getText().isEmpty()){
            stb.append("Задай ссылку. ");
            allIsFine = false;
        }    
      
        if(nameTextField.getText().isEmpty()){
            stb.append("Задай имя. ");
            allIsFine = false;
        }
        
        okButton.setEnabled(allIsFine);
        errorLabel.setVisible(!allIsFine);
        errorLabel.setText(stb.toString());
        return allIsFine;    
    }
  
    /**Метод редактирует ссылку встраиваемое на видео таким образом, чтоб в 
     * галерее сайта в один ряд помещались три видеозаписи*/
    private String editVideoSize(String link){
        String h = link.substring(link.indexOf("height"), link.indexOf(" src="));
        String w = link.substring(link.indexOf("width"), link.indexOf("height")); 
        link = link.replaceAll(w, "width=\"279\" ");
        link = link.replaceAll(h, "height=\"156\"");
        return link;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headLabel = new javax.swing.JLabel();
        linkTextField = new javax.swing.JTextField();
        nameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        linkLabel = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        helpLabel = new javax.swing.JLabel();
        errorLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(options.ICON_MAIN);
        setResizable(false);

        headLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        headLabel.setForeground(new java.awt.Color(94, 93, 1));
        headLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headLabel.setText("jLabel1");

        linkTextField.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        linkTextField.setSelectionColor(new java.awt.Color(94, 93, 1));
        linkTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linkTextFieldActionPerformed(evt);
            }
        });
        linkTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                linkTextFieldKeyReleased(evt);
            }
        });

        nameLabel.setText("Имя видео (для отображения в списке программы, на сайт не влияет)");

        nameTextField.setSelectionColor(new java.awt.Color(94, 93, 1));
        nameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nameTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nameTextFieldKeyTyped(evt);
            }
        });

        linkLabel.setText("Ссылка на видео (НЕ НАДО СЮДА ТУПО КОПИРОВАТЬ ССЫЛКУ ИЗ БРАУЗЕРА! НЕ СРАБОТАЕТ!! СМОТРИ ИНСТРУКЦИЮ, ЕСЛИ НЕ ЗНАЕШЬ, ЧТО ДЕЛАТЬ!)");

        okButton.setText("Сохранить");
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

        helpLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        helpLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        helpLabel.setText("Где и как брать ссылку на видео?");
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
        });

        errorLabel.setForeground(new java.awt.Color(255, 0, 0));
        errorLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        errorLabel.setText("errorLabel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(linkTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
                    .addComponent(headLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameTextField)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(okButton))
                    .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(errorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(linkLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(292, 292, 292)
                .addComponent(helpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(linkLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linkTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(helpLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(errorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void helpLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpLabelMouseEntered
        helpLabel.setFont(new Font("Tahoma", 1, 12));
        helpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
    }//GEN-LAST:event_helpLabelMouseEntered

    private void helpLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpLabelMouseExited
         helpLabel.setFont(new Font("Tahoma", 0, 12));
    }//GEN-LAST:event_helpLabelMouseExited

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // СОЗДАНИЕ НОВОГО ВИДЕО
        if(creatingNewVideo){
            Gallery.addGalleryVideo(new GalleryVideo(nameTextField.getText(), editVideoSize(linkTextField.getText())));
            Gallery.saveVideosToFile();
        }
        //РЕДАКТИРОВАНИЕ ВИДЕО
        else{
            if(!video.getName().equals(nameTextField.getText()) || 
                    !video.getLink().equals(linkTextField.getText()))
            {
                video.setName(nameTextField.getText());
                video.setLink(editVideoSize(linkTextField.getText()));
                Gallery.saveVideosToFile();
        }}
        client.uploadGalleryFile();
        pageManager.editGalleryPage();
        dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void linkTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linkTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_linkTextFieldActionPerformed

    private void helpLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpLabelMouseClicked
        JOptionPane.showMessageDialog(this, new javaapplication58.panels.VideoHelpPanel(), "Как добавить видео?", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_helpLabelMouseClicked

    private void nameTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameTextFieldKeyTyped

    }//GEN-LAST:event_nameTextFieldKeyTyped

    private void nameTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameTextFieldKeyReleased
       checkInputData();
    }//GEN-LAST:event_nameTextFieldKeyReleased

    private void linkTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_linkTextFieldKeyReleased
       checkInputData();
    }//GEN-LAST:event_linkTextFieldKeyReleased

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel headLabel;
    private javax.swing.JLabel helpLabel;
    private javax.swing.JLabel linkLabel;
    private javax.swing.JTextField linkTextField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables
}
