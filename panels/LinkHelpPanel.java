
package javaapplication58.panels;


public class LinkHelpPanel extends javax.swing.JPanel {

    public LinkHelpPanel() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setTabSize(14);
        jTextArea1.setText("Включение этой функции дает следующее:\n\nЕсли в тексте объяления есть слова \n\n- \"боец\" или \"бойцы\", а так же все их склонения, то щелчок по ним \n   мышкой будет открывать страницу сайта с членами команды;\n\n- \"снаряжение\", а так же все его склонения, то щелчок по нему \n   мышкой будет открывать страницу сайта \"Снаряжение\";\n\n- \"вооружение\", а так же все его склонения, то щелчок по нему \n   мышкой будет открывать страницу сайта \"Вооружение\";\n\n- \"устав\", а так же все его склонения, то щелчок по нему мышкой \n   будет открывать страницу сайта \"Устав\";\n\n- \"правила\", а так же все его склонения, то щелчок по нему мышкой\n   будет открывать страницу сайта \"Правила Страйкбола\".\n\nПри этом программа учитывает не только склонения, но и регистр,\nто есть она одинаково нормально отреагирует например на такие \nвариации, как \"вооружение\", \"Вооружение\" и \"ВООРУЖЕНИЕ\".\n\nPS Если понадобиться расширить список ключевых слов - ты знаешь,\nкому писать ;) \n\n\n");
        jTextArea1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
