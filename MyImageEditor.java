
package javaapplication58;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author Александр Машьянов, mashyanov1987@gmail.com
 */
public class MyImageEditor {
    private final SystemOptions options = SystemOptions.getInstance();
    private final MyFTPClient client = MyFTPClient.getInstace();
    private BufferedImage inputImage, cutted, predCompressed, back, backMobile, 
            copy;
    private Image compressed;
    
        
    public MyImageEditor() {   }
    
    File navigationFile    = new File("images/temp/navigation.png"); 
    File navigationFileM   = new File("images/temp/navigationM.png"); 
    File avatarFile        = new File("images/temp/avatar.jpg");
    File photoFile         = new File("images/temp/photo.jpg");
    File photoMiniFile     = new File("images/temp/photoMini.png");
    File photoMiniFileM    = new File("images/temp/photoMiniM.png");
    File background        = new File("images/sys/bkg.png");
    File backgroundM       = new File("images/sys/mbkg.png");
    File def               = new File("images/sys/default.png");
    File defM              = new File("images/sys/mdefault.png");
    File miniImage         = new File("images/temp/mini.png");
    
    public void editPhoto(File avatar, String link, int number){
        try {
            if(!photoFile.exists()) photoFile.createNewFile();
            if(!photoMiniFile.exists()) photoMiniFile.createNewFile();
            back  = ImageIO.read(def);
            //СЧИТЫВАЕМ КАРТИНКУ ОТ ПОЛЬЗОВАТЕЛЯ
            inputImage = ImageIO.read(avatar);
            ///////////////////СОЗДАЕМ ОСНОВНОЙ АВАТАР//////////////////////////
            //ОБРЕЗАЕМ ЛИШНЕЕ, ЕСЛИ ЕСТЬ
            cutted = inputImage.getSubimage(1, 1, 499, 332);
            //ВЫВОДИМ В ФАЙЛ            
            ImageIO.write(cutted, "jpg", photoFile);
            ////////////////////ДЕЛАЕМ ФОТО ДЛЯ МЕНЮ НАВИГАЦИИ//////////////////
            //ОБРЕЗАЕМ ФОТО ДО 3х4
            predCompressed = cutted.getSubimage(29, 0, 442, 332);
            //СЖИМАЕМ
            compressed = predCompressed.getScaledInstance(160, 120, Image.SCALE_SMOOTH );
            //КОПИРУЕМ ФОН, РИСУЕМ НА НЕМ КАРТИНКУ
            copy = back.getSubimage(0, 0, back.getWidth(), back.getHeight());
            Graphics gr = copy.getGraphics();
            gr.drawImage(compressed, 0, 0, null);
            //ВЫВОДИМ В ФАЙЛ    
            ImageIO.write(copy, "png", photoMiniFile);
            
            if(options.isDebugPrint()){
                System.out.println(client.MAIN_ADDRESS + "img/Fighters/fullsize/"+link+number+".jpg");
                System.out.println(client.MAIN_ADDRESS + "img/Fighters/"+link+number+".png");
            }
            client.deleteFile(client.MAIN_ADDRESS + "img/Fighters/fullsize/"+link+number+".jpg");
            client.deleteFile(client.MAIN_ADDRESS + "img/Fighters/"+link+number+".png");
            client.uploadFile(photoFile, client.MAIN_ADDRESS + "img/Fighters/fullsize/"+link+number+".jpg");
            client.uploadFile(photoMiniFile, client.MAIN_ADDRESS + "img/Fighters/"+link+number+".png");            
            client.deleteFile(client.M_MAIN_ADDRESS + "img/fighters/fullsize/" + link + number + ".png");
            client.uploadFile(photoMiniFile, client.M_MAIN_ADDRESS + "img/fighters/fullsize/" + link + number + ".png");
            
        } catch (IOException ex) {
            if(options.isCatchPrint()) System.out.println(ex.getMessage());
        }
    }
    
    public BufferedImage createGalleryMiniImage(BufferedImage fullsize){
        return null;
    }
    
    public void createMiniImageForGallery(File fullsize, String name){
         try { 
             miniImage = new File("images/temp/gallery/" + name + ".png");
            if(miniImage.exists())
                miniImage.delete();
            if(!miniImage.exists())
                miniImage.createNewFile();
            back  = ImageIO.read(background);
            inputImage = ImageIO.read(fullsize);
            if(inputImage.getWidth() < 160){
                JOptionPane.showMessageDialog(new JPanel(), "Изображение не будет загружено: ширина < 160!!!", "Слишком маленькое изображение!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(inputImage.getHeight() < 90){
                JOptionPane.showMessageDialog(new JPanel(), "Изображение не будет загружено: высота < 90!!!", "Слишком маленькое изображение!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(inputImage.getWidth() <= inputImage.getHeight()){
                int newY = (inputImage.getWidth() * 9) / 16;
                int k = inputImage.getHeight() - newY;
                cutted = inputImage.getSubimage(0, k/2, inputImage.getWidth(), newY);
                compressed = cutted.getScaledInstance(160, 90, Image.SCALE_SMOOTH );
                
                copy = back.getSubimage(1, 1, 160, 90);
                Graphics gr = copy.getGraphics();
                gr.drawImage(compressed, 0, 0, null);
                ImageIO.write(copy, "png", miniImage);
                client.uploadFile(miniImage, client.MAIN_ADDRESS + "/img/" + name + ".png" );
            }
            else{
                int newY = (inputImage.getWidth() * 9) / 16;
                int k = inputImage.getHeight()- newY;
                cutted = inputImage.getSubimage(0, k/2, inputImage.getWidth(), newY);
                compressed = cutted.getScaledInstance(160, 90, Image.SCALE_SMOOTH );
                copy = back.getSubimage(1, 1, 160, 90);
                Graphics gr = copy.getGraphics();
                gr.drawImage(compressed, 0, 0, null);
                ImageIO.write(copy, "png", miniImage);
                client.uploadFile(miniImage, client.MAIN_ADDRESS + "/img/" + name + ".png" );
            } 
        } catch (IOException ex) {
            if(options.isCatchPrint()) System.out.println(ex.getMessage());
        }
    }

    public void editAvatarPicture(File avatar, String callSign, String link){
        try {
            if(!avatarFile.exists()) avatarFile.createNewFile();
            avatarFile.renameTo(new File("images/temp/"+link+".jpg"));
            back  = ImageIO.read(background);
            inputImage = ImageIO.read(avatar);
            
            //СЧИТЫВАЕМ КАРТИНКУ ОТ ПОЛЬЗОВАТЕЛЯ
            inputImage = ImageIO.read(avatar);
            
            ///////////////////СОЗДАЕМ ОСНОВНОЙ АВАТАР//////////////////////////
            //ОБРЕЗАЕМ ЛИШНЕЕ, ЕСЛИ ЕСТЬ
            if(inputImage.getWidth() < 500){
                JOptionPane.showMessageDialog(new JPanel(), 
                        "Изображение не будет загружено: ширина < 500!!!", 
                        "Слишком маленькое изображение!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(inputImage.getHeight() < 333){
                JOptionPane.showMessageDialog(new JPanel(), 
                        "Изображение не будет загружено: высота < 333!!!", 
                        "Слишком маленькое изображение!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            cutted = inputImage.getSubimage(1, 1, 499, 332);
            //ВЫВОДИМ В ФАЙЛ            
            ImageIO.write(cutted, "jpg", avatarFile);
//            
//            ////////////////////ДЕЛАЕМ ФОТО ДЛЯ МЕНЮ НАВИГАЦИИ//////////////////
//            //ОБРЕЗАЕМ ФОТО ДО 3х4
            predCompressed = cutted.getSubimage(29, 0, 442, 332);
//            //СЖИМАЕМ
            compressed = predCompressed.getScaledInstance(224, 168, Image.SCALE_SMOOTH );
   
//            //КОПИРУЕМ ФОН, РИСУЕМ НА НЕМ КАРТИНКУ
            copy = back.getSubimage(0, 0, back.getWidth(), back.getHeight());
            Graphics gr = copy.getGraphics();
            gr.drawImage(compressed, 0, 0, null);
    
//            //ПИШЕМ НА КАРТИНКЕ ПОЗЫВНОЙ 
            gr.setColor(new Color(128, 128, 0));
            gr.setFont(new Font("Impact", 0 , 28));
            gr.drawString(callSign, (110 - callSign.length()*7), 195);
//                       
//            //ВЫВОДИМ В ФАЙЛ    
            ImageIO.write(copy, "png", navigationFile);
            
        /////////////////ДЛЯ МОБИЛЬНОЙ ВЕРСИИ///////////////////////////////    
        backMobile  = ImageIO.read(backgroundM);    
        compressed = predCompressed.getScaledInstance(151, 113, Image.SCALE_SMOOTH );    
        copy = backMobile.getSubimage(0, 0, backMobile.getWidth(), backMobile.getHeight());
        gr = copy.getGraphics();
        gr.drawImage(compressed, 0, 0, null);
        gr.setColor(new Color(128, 128, 0));
        gr.setFont(new Font("Impact", 0 , 16));
        gr.drawString(callSign, (85 - callSign.length()*7), 130);   
        ImageIO.write(copy, "png", navigationFileM);
        
            client.uploadFile(navigationFile, client.MAIN_ADDRESS + "img/Fighters/mini/"+link+".png");
            client.uploadFile(avatar, client.MAIN_ADDRESS + "img/Fighters/fullsize/"+link+".jpg");
            client.uploadFile(avatar, client.MAIN_ADDRESS + "img/Fighters/"+link+".jpg");            
            client.uploadFile(navigationFileM, client.M_MAIN_ADDRESS + "img/fighters/"+link+".png");
            client.uploadFile(avatarFile, client.M_MAIN_ADDRESS + "img/fighters/fullsize/"+link+".png");
        } catch (IOException ex) {
            if(options.isCatchPrint()) System.out.println(ex.getMessage());
        }
    }
}