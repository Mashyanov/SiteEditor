
package javaapplication58.windows;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication58.panels.ImageHelpPanel;
import javaapplication58.Member;
import javaapplication58.MyFTPClient;
import javaapplication58.MyImageEditor;
import javaapplication58.SystemOptions;
import javaapplication58.Team;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.apache.commons.net.ftp.FTP;


public class ImageEditorWindow extends javax.swing.JFrame {
private final SimpleDateFormat logDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
private final MyFTPClient client = MyFTPClient.getInstace();
private SystemOptions options = SystemOptions.getInstance();
private final Member member;
private FileOutputStream fos;
private final FileFilterExt eff;
private JFileChooser fileChooser;
private File imageMain, imageFirst, imageSecond, imageThird, dir;
private boolean mainChanged, firstChanged, secondChanged, thirdChanged;
private final MyImageEditor imageEditor;
private static String directory;      
    
    public ImageEditorWindow(Member member) {
        fos = null;
        initComponents();
        setTitle("Редактирование фотографий");
        this.member = member;
        imageEditor = new MyImageEditor();
        eff = new FileFilterExt("jpg", "Фото должно быть JPEG");
        fileChooser = new JFileChooser("");
        fileChooser.addChoosableFileFilter(eff);
        fileChooser.setFileFilter(eff);
        if(directory!=null)
            fileChooser.setCurrentDirectory(new File(directory));
        
   
        titleLabel.setText(member.getName() +' ' + member.getCallSign()+" - редактирование фото");
        
        imageMain   = new File("images/temp/"+member.getLink()+".jpg");
        imageFirst  = new File("images/temp/"+member.getLink()+"1.jpg");
        imageSecond = new File("images/temp/"+member.getLink()+"2.jpg");
        imageThird  = new File("images/temp/"+member.getLink()+"3.jpg");
        
        try {
        client.retriveFile(imageMain, "/www/sk-bron.ru/img/Fighters/"+member.getLink()+".jpg");
        imageMainLabel.setText("");
        BufferedImage image = ImageIO.read(imageMain);
        imageMainLabel.setIcon(new javax.swing.ImageIcon(image));
        
        imageFirstLabel.setText("Недоступно для кандидата");
        imageSecondLabel.setText("Недоступно для кандидата");
        imageThirdLabel.setText("Недоступно для кандидата");
        if(this.member.isCompetent()){
            client.retriveFile(imageFirst, "/www/sk-bron.ru/img/Fighters/fullsize/"+member.getLink()+"1.jpg");
            client.retriveFile(imageSecond, "/www/sk-bron.ru/img/Fighters/fullsize/"+member.getLink()+"2.jpg");
            client.retriveFile(imageThird, "/www/sk-bron.ru/img/Fighters/fullsize/"+member.getLink()+"3.jpg");
                       
            imageFirstLabel.setText("");
            imageSecondLabel.setText("");
            imageThirdLabel.setText("");
            image = ImageIO.read(imageFirst);
            imageFirstLabel.setIcon(new javax.swing.ImageIcon(image));
            image = ImageIO.read(imageSecond); 
            imageSecondLabel.setIcon(new javax.swing.ImageIcon(image));
            image = ImageIO.read(imageThird);
            imageThirdLabel.setIcon(new javax.swing.ImageIcon(image));
        }
    } catch (FileNotFoundException ex) {
        if(options.isCatchPrint()) System.out.println(ex.getMessage());
    } catch (IOException ex) {
        if(options.isCatchPrint()) System.out.println(ex.getMessage());
     
    }
 
        
        fileChooser.setMultiSelectionEnabled(false);
        if(options.isCatchPrint())System.out.println("Окно фоторедактора");
         
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        jTabbetPain = new javax.swing.JTabbedPane();
        imageMainPanel = new javax.swing.JPanel();
        imageMainLabel = new javax.swing.JLabel();
        imageFirstPanel = new javax.swing.JPanel();
        imageFirstLabel = new javax.swing.JLabel();
        imageSecondPanel = new javax.swing.JPanel();
        imageSecondLabel = new javax.swing.JLabel();
        imageThirdPanel = new javax.swing.JPanel();
        imageThirdLabel = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconImage(options.ICON_MAIN);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(94, 93, 1));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("jLabel1");

        jTabbetPain.setForeground(new java.awt.Color(94, 93, 1));

        imageMainPanel.setMaximumSize(new java.awt.Dimension(512, 384));
        imageMainPanel.setMinimumSize(new java.awt.Dimension(512, 384));

        imageMainLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        imageMainLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageMainLabel.setText("imageMainLabel");
        imageMainLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(94, 93, 1), 2, true));
        imageMainLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imageMainLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout imageMainPanelLayout = new javax.swing.GroupLayout(imageMainPanel);
        imageMainPanel.setLayout(imageMainPanelLayout);
        imageMainPanelLayout.setHorizontalGroup(
            imageMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imageMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageMainLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                .addContainerGap())
        );
        imageMainPanelLayout.setVerticalGroup(
            imageMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imageMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageMainLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTabbetPain.addTab("Основной аватар", imageMainPanel);

        imageFirstLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        imageFirstLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageFirstLabel.setText("imageFirstLabel");
        imageFirstLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(94, 93, 1), 2, true));
        imageFirstLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imageFirstLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout imageFirstPanelLayout = new javax.swing.GroupLayout(imageFirstPanel);
        imageFirstPanel.setLayout(imageFirstPanelLayout);
        imageFirstPanelLayout.setHorizontalGroup(
            imageFirstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imageFirstPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageFirstLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                .addContainerGap())
        );
        imageFirstPanelLayout.setVerticalGroup(
            imageFirstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imageFirstPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageFirstLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbetPain.addTab("Фото №1 (Горка)    ", imageFirstPanel);

        imageSecondLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        imageSecondLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageSecondLabel.setText("imageSecondLabel");
        imageSecondLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(94, 93, 1), 2, true));
        imageSecondLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imageSecondLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout imageSecondPanelLayout = new javax.swing.GroupLayout(imageSecondPanel);
        imageSecondPanel.setLayout(imageSecondPanelLayout);
        imageSecondPanelLayout.setHorizontalGroup(
            imageSecondPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imageSecondPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageSecondLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                .addContainerGap())
        );
        imageSecondPanelLayout.setVerticalGroup(
            imageSecondPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imageSecondPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageSecondLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbetPain.addTab("Фото №2 (Цифра)    ", imageSecondPanel);

        imageThirdLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        imageThirdLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageThirdLabel.setText("imageThirdLabel");
        imageThirdLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(94, 93, 1), 2, true));
        imageThirdLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imageThirdLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout imageThirdPanelLayout = new javax.swing.GroupLayout(imageThirdPanel);
        imageThirdPanel.setLayout(imageThirdPanelLayout);
        imageThirdPanelLayout.setHorizontalGroup(
            imageThirdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imageThirdPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageThirdLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                .addContainerGap())
        );
        imageThirdPanelLayout.setVerticalGroup(
            imageThirdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imageThirdPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageThirdLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbetPain.addTab("Фото №3 (Гражданка)", imageThirdPanel);

        cancelButton.setText("Отмена");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        okButton.setText("Применить");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jTabbetPain, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbetPain, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(okButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbetPain.getAccessibleContext().setAccessibleName("Основной аватар");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void imageMainLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageMainLabelMouseClicked
        mainChanged = setPhoto(imageMainLabel, fileChooser.getSelectedFile());
        if(mainChanged){
            imageEditor.editAvatarPicture(fileChooser.getSelectedFile(), member.getCallSign(), member.getLink());
            System.out.println("редактируем основаное фото");
            
        }
        
    }//GEN-LAST:event_imageMainLabelMouseClicked

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        
        if(mainChanged) {
            imageEditor.editAvatarPicture(imageMain, member.getCallSign(), member.getLink());
        }
        if(firstChanged) {
            imageEditor.editPhoto(imageFirst, member.getLink(), 1);
        }
        if(secondChanged) {
            imageEditor.editPhoto(imageSecond, member.getLink(), 2);
        }
        if(thirdChanged){
            imageEditor.editPhoto(imageThird, member.getLink(), 3);
        }
        imageFirst.delete();
        imageMain.delete();
        imageSecond.delete();
        imageThird.delete();
        new TeamManagerWindow(client).setVisible(true);
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void imageFirstLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageFirstLabelMouseClicked
        if(member.isCompetent()){
            firstChanged = setPhoto(imageFirstLabel, fileChooser.getSelectedFile());
            if(firstChanged)
                imageEditor.editPhoto(fileChooser.getSelectedFile(), member.getLink(), 1);
        }
        else JOptionPane.showMessageDialog(this, "Сначала переведи в бойцы!", "Недоступно для кандидата", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_imageFirstLabelMouseClicked

    private void imageSecondLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageSecondLabelMouseClicked
        if(member.isCompetent()){
            secondChanged = setPhoto(imageSecondLabel, fileChooser.getSelectedFile());
            if(secondChanged)
                imageEditor.editPhoto(fileChooser.getSelectedFile(), member.getLink(), 2);}
        else JOptionPane.showMessageDialog(this, "Сначала переведи в бойцы!", "Недоступно для кандидата", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_imageSecondLabelMouseClicked

    private void imageThirdLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageThirdLabelMouseClicked
        
        if(member.isCompetent()){
            thirdChanged = setPhoto(imageThirdLabel, fileChooser.getSelectedFile());
            if(thirdChanged)
                imageEditor.editPhoto(fileChooser.getSelectedFile(), member.getLink(), 3); }
        
        
        else JOptionPane.showMessageDialog(this, "Сначала переведи в бойцы!", "Недоступно для кандидата", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_imageThirdLabelMouseClicked

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        Calendar logDate = Calendar.getInstance();
//    if(mainChanged){
            member.getLog().add(logDateFormat.format(logDate.getTime())+ ": изменена основная фотография" + Team.getActiveUser());
        //    imageEditor.editAvatarPicture(imageMain, member.getCallSign(), member.getLink());
      //  }
        if(firstChanged)  {
            member.getLog().add(logDateFormat.format(logDate.getTime())+ ": изменена первая доп. фотография" + Team.getActiveUser());
            //imageEditor.editPhoto(imageFirst, member.getLink(), 1);
        }
        if(secondChanged) {
            member.getLog().add(logDateFormat.format(logDate.getTime())+ ": изменена вторая доп. фотография" + Team.getActiveUser());
            //imageEditor.editPhoto(imageSecond, member.getLink(), 2);
        }
        if(thirdChanged)  {
            member.getLog().add(logDateFormat.format(logDate.getTime())+ ": изменена третья доп. фотография" + Team.getActiveUser());
           // imageEditor.editPhoto(imageThird, member.getLink(), 3);
        }
        imageFirst.delete();
        imageMain.delete();
        imageSecond.delete();
        imageThird.delete();
        new TeamManagerWindow(client).setVisible(true);
        dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        SystemOptions.windowClosing();
    }//GEN-LAST:event_formWindowClosing

    private boolean setPhoto(JLabel label, File file){
    JOptionPane.showMessageDialog(this, new ImageHelpPanel());  
    if(fileChooser.showDialog(this, "Выбрать") == 0){
        try {
            file = fileChooser.getSelectedFile();
            directory = file.getParent();
            fileChooser.setCurrentDirectory(new File(directory));
            BufferedImage image = ImageIO.read(file);
            label.setIcon(new javax.swing.ImageIcon(image));
            return true;
        } catch (IOException ex) {
            if(options.isCatchPrint()) System.out.println(ex.getMessage());
            return false;  
        }
    }
        return false;  
    } 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel imageFirstLabel;
    private javax.swing.JPanel imageFirstPanel;
    private javax.swing.JLabel imageMainLabel;
    private javax.swing.JPanel imageMainPanel;
    private javax.swing.JLabel imageSecondLabel;
    private javax.swing.JPanel imageSecondPanel;
    private javax.swing.JLabel imageThirdLabel;
    private javax.swing.JPanel imageThirdPanel;
    private javax.swing.JTabbedPane jTabbetPain;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables

class FileFilterExt extends javax.swing.filechooser.FileFilter 
{
    String extension  ;  // расширение файла
    String description;  // описание типа файлов

    FileFilterExt(String extension, String descr)
    {
        this.extension = extension;
        this.description = descr;
    }
    @Override
    public boolean accept(java.io.File file)
    {
        if(file != null) {
            if (file.isDirectory())
                return true;
            if( extension == null )
                return (extension.length() == 0);
            return file.getName().endsWith(extension);
        }
        return false;
    }
    // Функция описания типов файлов
    @Override
    public String getDescription() {
        return description;
    }
}

}
