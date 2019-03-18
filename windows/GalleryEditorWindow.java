
package javaapplication58.windows;


import javaapplication58.GalleryImage;
import javaapplication58.PageManager;
import javaapplication58.SystemOptions;
import javaapplication58.MyFTPClient;
import javaapplication58.GalleryVideo;
import javaapplication58.Gallery;
import javaapplication58.MyImageEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication58.data.Data;

import javaapplication58.panels.GalleryFullSizePanel;
import javaapplication58.panels.GalleryHelpPanel;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class GalleryEditorWindow extends javax.swing.JFrame implements ActionListener{
   private Cursor waitCursor = new Cursor(Cursor.WAIT_CURSOR);
   private Cursor dfltCursor  = new Cursor(Cursor.DEFAULT_CURSOR);
   private ProgressMonitor pm = new ProgressMonitor(null,  "ЗАГРУЖЕНО:", "Init. . .", 0, 100);
   private Image iconLeft, iconRight; 
   private final FileFilterExt jpgFilter = new FileFilterExt("jpg", "Картинки JPEG");
   private final FileFilterExt pngFilter = new FileFilterExt("png", "Картинки PNG");
   private static  int counter = 0;
   private SystemOptions options = SystemOptions.getInstance();
   private final Data data = new Data();
   private final Renderer renderer = new Renderer();
   private LinkedList<GalleryImage> images;
   private LinkedList<GalleryVideo> videos;
   private final MyFTPClient client = MyFTPClient.getInstace();
   private File file, fileMini, dir;
   private final MyImageEditor imageEditor;
   private final PageManager pageManager;
   private static String directory;
   private JFileChooser fileChooser = new JFileChooser();
   private final GalleryFullSizePanel fsp = new GalleryFullSizePanel();
   private final Timer timer = new Timer(100, this);
   
   public GalleryEditorWindow() {
        counter = 0;
        imageEditor = new MyImageEditor();
        pageManager = new PageManager();
        try {
            iconLeft  = ImageIO.read(new FileImageInputStream(new File("images/sys/left.png")));
            iconRight = ImageIO.read(new FileImageInputStream(new File("images/sys/right.png")));
        } catch (IOException ex) {
           if(options.isCatchPrint()) System.out.println(ex.getMessage());
           JOptionPane.showMessageDialog(new JPanel(), "Ошибка при создании окна опций\n" + ex.getMessage(), "ошибка", JOptionPane.ERROR_MESSAGE);
            
        }
        initComponents();
               
        leftButton.setIcon(new ImageIcon(iconLeft));
        rightButton.setIcon(new ImageIcon(iconRight));
        setTitle("Редактирование галереи");
        photosList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        photosList.setFixedCellHeight(100);
        images = Gallery.loadGalleryFromFile();
        videos = Gallery.loadVideosFromFile();
        if(images.isEmpty()){
            fullsizeButton.setEnabled(false);
            removeButton.setEnabled(false);
            changeMiniButton.setEnabled(false);
        }
        dir = new File("images/temp/gallery");
        
        File[] files = dir.listFiles();
        
        for (File file1 : files) {
           if(file1.isFile()) file1.delete();
        }
        UIManager.put("ProgressMonitor.progressText", "Загрузка редактора");
            
        new Thread(() -> {
            timer.start();
            setCursor(waitCursor);
            pm.setMaximum(images.size());
            for (GalleryImage image : images) {
                client.downloadMiniImage(image.getName());
                data.getList().add(Integer.toString(image.getName()));
                counter++;
            }
            pm.setProgress(pm.getMaximum());
            
            timer.stop();
            photosList.setCellRenderer(renderer);
            photosList.setModel(data);
            photosList.setSelectedIndex(0);
            setCursor(dfltCursor);
            photosList.repaint();
            photosList.setVisibleRowCount(images.size()/5 + 1);
            photosList.grabFocus();
            if(options.isCatchPrint())System.out.println("Окно редактирования Галереи");
        }).start();
        
    }
  
   private void removePhoto(int name){
       
       int temp = photosList.getSelectedIndex();
       if(temp == images.size()-1) temp--;
       GalleryImage toDelete = Gallery.getGalleryImageByName(name);
       Gallery.removePhotoFromGallery(toDelete);
       Gallery.sortGallery();
       images = Gallery.getGalleryImages();
       data.getList().clear();
       for (GalleryImage image : images) 
            data.getList().add(Integer.toString(image.getName()));
        photosList.setVisibleRowCount(images.size()/5+1);
        client.deleteFile(client.MAIN_ADDRESS +"img/" + toDelete.getName()+".png");
        client.deleteFile(client.MAIN_ADDRESS +"img/fullsize/" + toDelete.getName()+".jpg");
        if(images.size() >10 && images.size()%5 == 0 )photosList.setVisibleRowCount(images.size()/5);
        pageManager.editGalleryPage();
        Gallery.saveGalleryToFile();
        client.uploadGalleryFile();
   }
   
    private void poolUp(int name){
        int temp = photosList.getSelectedIndex();
        GalleryImage toMoveUp = Gallery.getGalleryImageByName(name);
        int numberToMoveUp = toMoveUp.getNumber();
        GalleryImage second = Gallery.getGalleryImageByNumber(numberToMoveUp - 1);
        second.setNumber(99999);
        toMoveUp.setNumber(numberToMoveUp - 1);
        Gallery.getGalleryImageByNumber(99999).setNumber(numberToMoveUp);
        Gallery.sortGallery();
        images = Gallery.getGalleryImages();
        data.getList().clear();
        for (GalleryImage image : images) 
            data.getList().add(Integer.toString(image.getName()));
        photosList.setSelectedIndex(temp - 1);
        pageManager.editGalleryPage();
        client.uploadGalleryFile();
    }
   
    private void poolDown(int number){
        int temp = photosList.getSelectedIndex();
        GalleryImage toMoveDown = Gallery.getGalleryImageByName(number);
        int numberToMoveDown = toMoveDown.getNumber();
        GalleryImage second = Gallery.getGalleryImageByNumber(numberToMoveDown + 1);
        second.setNumber(99999);
        toMoveDown.setNumber(numberToMoveDown + 1);
        Gallery.getGalleryImageByNumber(99999).setNumber(numberToMoveDown);
        Gallery.sortGallery();
        images = Gallery.getGalleryImages();
        data.getList().clear();
        for (GalleryImage image : images) 
            data.getList().add(Integer.toString(image.getName()));
            
        photosList.setSelectedIndex(temp + 1);
        pageManager.editGalleryPage();
        client.uploadGalleryFile();
    }
    
   private void removePhoto(){
        UIManager.put("OptionPane.yesButtonText"   , "Удаляем!");
        UIManager.put("OptionPane.noButtonText"    , "Не-не-не, пока оставим");
        
        if(JOptionPane.showConfirmDialog(this, "Точно хочешь окончательно УДАЛИТЬ фото?", "Удалить?", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==0) removePhoto(Integer.parseInt(photosList.getSelectedValue()));
        if(images.isEmpty()){
            fullsizeButton.setEnabled(false);
            removeButton.setEnabled(false);
            changeMiniButton.setEnabled(false);
        }
    }
   
   private void addPhoto(){
        fileChooser.setFileFilter(jpgFilter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setMultiSelectionEnabled(true);
        if(fileChooser.showDialog(this, "ВЫБРАТЬ") == 0){
            File[] files = fileChooser.getSelectedFiles();
            try {
                for (File file1 : files) {
                    GalleryImage newImage = new GalleryImage();
                    Gallery.addGalleryImage(newImage);
                    fileMini = new File("images/temp/gallery/" + newImage.getName() + ".png");
                    if(!fileMini.exists()) fileMini.createNewFile();
                    directory = file1.getParent();
                    fileChooser.setCurrentDirectory(new File(directory));
                    imageEditor.createMiniImageForGallery(file1, Integer.toString(newImage.getName()));
                    client.uploadFile(file1, client.MAIN_ADDRESS + "/img/fullsize/" + Integer.toString(newImage.getName()) + ".jpg");
                    pageManager.editGalleryPage();
                    data.getList().add(Integer.toString(newImage.getName()));
                    photosList.updateUI();
                    Gallery.saveGalleryToFile();
                    client.uploadGalleryFile();
                    photosList.setVisibleRowCount(images.size()/5+1);
                }
            } catch (IOException ex) {
                    if(options.isCatchPrint()) System.out.println(ex.getMessage());
            }
        }
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        photosList = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        leftButton = new javax.swing.JButton();
        rightButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        fullsizeButton = new javax.swing.JButton();
        changeMiniButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        videoButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        setFocusTraversalPolicyProvider(true);
        setIconImage(options.ICON_MAIN);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        photosList.setBackground(new java.awt.Color(153, 153, 153));
        photosList.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(94, 93, 1), null));
        photosList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        photosList.setAutoscrolls(false);
        photosList.setFocusCycleRoot(true);
        photosList.setFocusTraversalPolicyProvider(true);
        photosList.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        photosList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                photosListMouseClicked(evt);
            }
        });
        photosList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                photosListKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(photosList);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(94, 93, 1), null));

        leftButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leftButtonActionPerformed(evt);
            }
        });

        rightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rightButtonActionPerformed(evt);
            }
        });

        addButton.setText("Добавить фото");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        removeButton.setText("Удалить фото");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        fullsizeButton.setText("Откр. полный размер");
        fullsizeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fullsizeButtonActionPerformed(evt);
            }
        });

        changeMiniButton.setText("Изменить миниатюру");
        changeMiniButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeMiniButtonActionPerformed(evt);
            }
        });

        backButton.setText("НАЗАД");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        exitButton.setText("ВЫХОД");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        jButton1.setText("На сайт, в галерею");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        videoButton.setText("Работа с Видео");
        videoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                videoButtonActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(94, 93, 1));
        jSeparator1.setForeground(new java.awt.Color(94, 93, 1));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(94, 93, 1)));

        jSeparator2.setBackground(new java.awt.Color(94, 93, 1));
        jSeparator2.setForeground(new java.awt.Color(94, 93, 1));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(94, 93, 1)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fullsizeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(changeMiniButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(backButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exitButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(videoButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(leftButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(videoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(leftButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rightButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(fullsizeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(changeMiniButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeButton)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 869, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void leftButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leftButtonActionPerformed
        setCursor(waitCursor);
        int[] indeces = photosList.getSelectedIndices();
            for (int index : indeces) {
            poolUp(Gallery.getGalleryImageByNumber(index).getName());
        }
        setCursor(dfltCursor);
        photosList.grabFocus();
    }//GEN-LAST:event_leftButtonActionPerformed

    private void rightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rightButtonActionPerformed
        setCursor(waitCursor);
        poolDown(Integer.parseInt(photosList.getSelectedValue()));
        setCursor(dfltCursor);
        photosList.grabFocus();
    }//GEN-LAST:event_rightButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
    removePhoto();
    photosList.grabFocus();    
    }//GEN-LAST:event_removeButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
    setCursor(waitCursor);
        addPhoto();
        if(!images.isEmpty()){
            fullsizeButton.setEnabled(true);
            removeButton.setEnabled(true);
            changeMiniButton.setEnabled(true);
        }
        photosList.setSelectedIndex(images.size()-1);
        setCursor(dfltCursor);
        photosList.grabFocus();    
    }//GEN-LAST:event_addButtonActionPerformed

    private void changeMiniButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeMiniButtonActionPerformed
    JOptionPane.showMessageDialog(this, new GalleryHelpPanel(), "Памятка", JOptionPane.PLAIN_MESSAGE);
    fileChooser.setFileFilter(pngFilter);
    fileChooser.setFileFilter(jpgFilter);
    fileChooser.setMultiSelectionEnabled(false);
    fileChooser.setAcceptAllFileFilterUsed(false);
    if(fileChooser.showDialog(this, "ВЫБРАТЬ") == 0){
        try {
            file = fileChooser.getSelectedFile();
            BufferedImage image = ImageIO.read(file);

            directory = file.getParent();
            fileChooser.setCurrentDirectory(new File(directory));
            if(image.getWidth() > 161 || image.getWidth() < 160 ||
                    image.getHeight() > 91 ||image.getHeight() < 90)
            {
                JOptionPane.showMessageDialog(this, "НЕВЕРНЫЙ РАЗМЕР ФОТО", "Ошибка...", JOptionPane.ERROR_MESSAGE);
                return;
            }
            imageEditor.createMiniImageForGallery(file, photosList.getSelectedValue());
            data.fireContentsChanged();
            renderer.repaint();
        } catch (IOException ex) {
           if(options.isCatchPrint()) System.out.println(ex.getMessage());
        }
    photosList.grabFocus();
    data.fireContentsChanged();
    }
    }//GEN-LAST:event_changeMiniButtonActionPerformed

    private void fullsizeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fullsizeButtonActionPerformed
        openFullScreen();
    }//GEN-LAST:event_fullsizeButtonActionPerformed

    private void openFullScreen(){
       try {
       setCursor(waitCursor);
       client.downloadFullImage(Integer.parseInt(photosList.getSelectedValue()));
       File imgFile =new File("images/temp/gallery/fullsize/"+photosList.getSelectedValue()+".jpg");
       BufferedImage img = ImageIO.read(imgFile);
       Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
       int newWidht = img.getWidth();
       int newHeight = img.getHeight();
       int i = 0;
       if (newWidht > sSize.getWidth() - sSize.getWidth() / 5 ||newHeight > sSize.getHeight() - sSize.getHeight() / 5){
           while(newWidht > sSize.getWidth() - sSize.getWidth() / 5 ||newHeight > sSize.getHeight() - sSize.getHeight() / 5){
               i++;
               newWidht  -= newWidht  / 10;
               newHeight -= newHeight / 10;
           }
           if(options.isDebugPrint())System.out.println("Полноразмерная картинка подогнана под ваше разрешение экрана " + 
                   (int)sSize.getWidth() + 'x' + (int)sSize.getHeight() + " за " + i + " итераций");
           Image temp = img.getScaledInstance(newWidht, newHeight, Image.SCALE_SMOOTH);
           BufferedImage tempBuf = new BufferedImage(temp.getWidth(null), temp.getHeight(null), BufferedImage.TYPE_INT_ARGB);
           Graphics2D bGr = tempBuf.createGraphics();
           bGr.drawImage(temp, 0, 0, null);
           bGr.dispose();
           img = tempBuf;               
       }
       fsp.setIcon(img);
       JOptionPane.showMessageDialog(this, fsp, photosList.getSelectedValue()+".jpg", JOptionPane.PLAIN_MESSAGE);
       imgFile.delete();
       setCursor(dfltCursor);
       photosList.grabFocus();
       photosList.repaint();
       jPanel1.repaint();
       } catch (IOException ex) {
            if(options.isCatchPrint()) System.out.println(ex.getMessage()); 
       }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Desktop.getDesktop().browse(new URI("www.sk-bron.ru/gallery.html"));
        } catch (URISyntaxException ex) {
            if(options.isCatchPrint()) System.out.println(ex.getMessage());
        } catch (IOException ex) {
            if(options.isCatchPrint()) System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
    new ActionMenuWindow().setVisible(true);
    dispose();   
    }//GEN-LAST:event_backButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        SystemOptions.windowClosing();
    }//GEN-LAST:event_exitButtonActionPerformed

    private void photosListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_photosListKeyPressed
        switch(evt.getKeyCode()){
            case 127:
                removePhoto();
                photosList.grabFocus();
                break;
            case 107: case 61:
                addPhoto();
                if(!images.isEmpty()){
                    fullsizeButton.setEnabled(true);
                    removeButton.setEnabled(true);
                    changeMiniButton.setEnabled(true);
                }
                photosList.setSelectedIndex(images.size()-1);
                photosList.grabFocus();
                break;
            case 27:
                new ActionMenuWindow().setVisible(true);
                dispose();
                break;
        }
    }//GEN-LAST:event_photosListKeyPressed

    private void videoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_videoButtonActionPerformed
        new VideoEditorWindow(this, true).setVisible(true);
    }//GEN-LAST:event_videoButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        SystemOptions.windowClosing();
    }//GEN-LAST:event_formWindowClosing

    private void photosListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_photosListMouseClicked
        if(evt.getClickCount() == 2 && evt.getButton() == 1) openFullScreen();
    }//GEN-LAST:event_photosListMouseClicked

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JButton changeMiniButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton fullsizeButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton leftButton;
    private javax.swing.JList<String> photosList;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton rightButton;
    private javax.swing.JButton videoButton;
    // End of variables declaration//GEN-END:variables


//        class Renderer extends DefaultListCellRenderer {
class Renderer extends DefaultListCellRenderer{
        Border border = new BevelBorder(0, new Color(128, 128, 0), new Color(94, 93, 1));
        public Renderer() {}
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
           Icon icon = new ImageIcon("images/temp/gallery/" + value + ".png");
           JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
           label.setIcon(icon);
           setText(" ");
                 
            if (isSelected) {
                boolean selectedLeft = true;
                boolean selectedRight =true;
                int [] indeces = photosList.getSelectedIndices();
                if(indeces!=null){
                    for (int index1 : indeces) {
                        if(index1 == 0) selectedLeft = false;
                        if(index1 == Gallery.getGalleryImages().size() - 1) selectedRight = false;
                    }
                    leftButton.setEnabled(selectedLeft);
                    rightButton.setEnabled(selectedRight);
                    changeMiniButton.setEnabled(true);
                    fullsizeButton.setEnabled(true);
                    if(indeces.length > 1){
                        changeMiniButton.setEnabled(false);
                        fullsizeButton.setEnabled(false);
                    }
                }
                setBorder(border);       
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBorder(null);
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            return label;
        }
    }
     
  @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (pm.isCanceled()) {
                    pm.close();
                    System.exit(1);
                }
            pm.setProgress(counter);
            pm.setNote(counter + "/" +images.size() +" изображений");
            }
        });
    }

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
}}
