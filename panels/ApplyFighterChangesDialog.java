
package javaapplication58.panels;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ApplyFighterChangesDialog extends javax.swing.JDialog {

    public ApplyFighterChangesDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public JLabel getDateCanValueLabel() {  return dateCanValueLabel;  }
    public JLabel getDateComValueLabel() {  return dateComValueLabel;  }
    public JLabel getDateResValueLabel() {  return dateResValueLabel;  }
    public JLabel getDateComLabel()      {  return dateComLabel;       }
    public JLabel getDateResLabel()      {  return dateResLabel;       }
    public JPanel getMainPanel()         {  return jPanel2;            }
    public JLabel getNameValueLabel()    {  return nameValueLabel;     }
    public JLabel getPositionValueLabel(){  return positionValueLabel; }
    public JLabel getSignValueLabel()    {  return signValueLabel;     }
    public JLabel getSpecValueLabel()    {  return specValueLabel;     }
    public JLabel getStatusValueLabel()  {  return statusValueLabel;   }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        headerLabel2 = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        signLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        specLabel = new javax.swing.JLabel();
        positionLabel = new javax.swing.JLabel();
        positionValueLabel = new javax.swing.JLabel();
        specValueLabel = new javax.swing.JLabel();
        statusValueLabel = new javax.swing.JLabel();
        signValueLabel = new javax.swing.JLabel();
        nameValueLabel = new javax.swing.JLabel();
        dateCanLabel = new javax.swing.JLabel();
        dateComLabel = new javax.swing.JLabel();
        dateResLabel = new javax.swing.JLabel();
        dateCanValueLabel = new javax.swing.JLabel();
        dateComValueLabel = new javax.swing.JLabel();
        dateResValueLabel = new javax.swing.JLabel();
        headerLabel1 = new javax.swing.JLabel();
        headerLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        headerLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        headerLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headerLabel2.setText("верность всех вносимых данных");
        headerLabel2.setOpaque(true);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(204, 204, 204)));

        nameLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nameLabel.setText("Имя:");

        signLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        signLabel.setText("Позывной:");

        statusLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        statusLabel.setText("Статус:");

        specLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        specLabel.setText("Спец-ия:");

        positionLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        positionLabel.setText("Должность:");

        positionValueLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        specValueLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        statusValueLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        signValueLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        nameValueLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        dateCanLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dateCanLabel.setText("Кандидат с:");

        dateComLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dateComLabel.setText("Боец  с:");

        dateResLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dateResLabel.setText("Резервист с:");

        dateCanValueLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dateCanValueLabel.setText("jLabel2");

        dateComValueLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dateComValueLabel.setText("jLabel2");

        dateResValueLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dateResValueLabel.setText("jLabel2");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(dateCanLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(positionLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(specLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(statusLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(signLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(specValueLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(statusValueLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(signValueLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nameValueLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(positionValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(dateCanValueLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(dateResLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateComLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateComValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateResValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameValueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(signLabel)
                    .addComponent(signValueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusLabel)
                    .addComponent(statusValueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(specLabel)
                    .addComponent(specValueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(positionLabel)
                    .addComponent(positionValueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateCanLabel)
                    .addComponent(dateCanValueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateComLabel)
                    .addComponent(dateComValueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateResLabel)
                    .addComponent(dateResValueLabel))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        headerLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        headerLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headerLabel1.setText("Перед сохранением проверь ");
        headerLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        headerLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        headerLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headerLabel3.setText(" о бойце! ");
        headerLabel3.setOpaque(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(headerLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(headerLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(headerLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 19, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headerLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(headerLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(headerLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Изменения отмечены красным");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(35, 35, 35))
        );

        setSize(new java.awt.Dimension(286, 463));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dateCanLabel;
    private javax.swing.JLabel dateCanValueLabel;
    private javax.swing.JLabel dateComLabel;
    private javax.swing.JLabel dateComValueLabel;
    private javax.swing.JLabel dateResLabel;
    private javax.swing.JLabel dateResValueLabel;
    private javax.swing.JLabel headerLabel1;
    private javax.swing.JLabel headerLabel2;
    private javax.swing.JLabel headerLabel3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel nameValueLabel;
    private javax.swing.JLabel positionLabel;
    private javax.swing.JLabel positionValueLabel;
    private javax.swing.JLabel signLabel;
    private javax.swing.JLabel signValueLabel;
    private javax.swing.JLabel specLabel;
    private javax.swing.JLabel specValueLabel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel statusValueLabel;
    // End of variables declaration//GEN-END:variables
}
