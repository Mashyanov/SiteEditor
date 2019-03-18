
package javaapplication58.panels;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class GalleryFullSizePanel extends javax.swing.JPanel {
    
    public GalleryFullSizePanel() {
        initComponents();
    
    }

    public void setIcon(BufferedImage img){
        fullSizeLabel.setIcon(new ImageIcon(img));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fullSizeLabel = new javax.swing.JLabel();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fullSizeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fullSizeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fullSizeLabel;
    // End of variables declaration//GEN-END:variables
}
