package javaapplication58.panels;

public class UpdatePanel extends javax.swing.JPanel {
    public UpdatePanel() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("Обновление успешно скачано!\n\nАрхив UPDATE.rar с обновлением находится \nв корневом каталоге программы. \nДля облегчения себе жизни я решил отказаться \nот самоустанавливающегося обновления, так что \nэтот архив придется распаковать ручками. \nДа-да! Своими собственными ручками! В ХХI веке!\nВолюнтаризм? Согласен! А что поделать? \nМонополия - она штука такая, неприятная :)\nВнутри полностью рабочая версия программы. \nСтарую можно удалить, перезаписать при распа-\nковке(но лучше удалить, для надежности), \nили сохранить на память, а архив распаковать в\nкакую-нибудь другую папку.\nНичего сложного, удачи! :) ");
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
