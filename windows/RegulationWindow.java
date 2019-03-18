

package javaapplication58.windows;

import javaapplication58.Rules;
import javaapplication58.PageManager;
import javaapplication58.Regulation;
import javaapplication58.Chapter;
import javaapplication58.SystemOptions;
import javaapplication58.MyFTPClient;
import javaapplication58.Team;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javaapplication58.data.Data;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;

public class RegulationWindow extends javax.swing.JFrame {
private Image iconUp, iconDown; 
private int windowType;
public static final int REGULATION_WINDOW = 0;
public static final int RULES_WINDOW = 1;
private String htmlAddress;
private final Data data = new Data();
private final MyFTPClient client = MyFTPClient.getInstace();
private final Cursor waitCursor = new Cursor(Cursor.WAIT_CURSOR);
private final PageManager pageManager = new PageManager();
private final Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
private final SystemOptions options = SystemOptions.getInstance();
private final JPopupMenu menu = new JPopupMenu();
private final JMenuItem editItem = new JMenuItem("edit");
private final JMenuItem addItem = new JMenuItem("add");
private final JMenuItem deleteItem = new JMenuItem("del");
private final JMenuItem upItem = new JMenuItem("up");
private final JMenuItem downItem = new JMenuItem("down");
private final JMenuItem HTMLItem = new JMenuItem("html");

    public RegulationWindow(int windowType) {
        this.windowType = windowType;
        initComponents();
        menu.add(editItem);
        menu.add(upItem);
        menu.add(downItem);
        menu.add(deleteItem);
        menu.addSeparator();
        menu.add(addItem);
        menu.add(HTMLItem);
        
        editItem.  addActionListener((e) -> { editChapter();      });
        deleteItem.addActionListener((e) -> { deleteChapter();    });
        addItem.   addActionListener((e) -> { addChapter();       });
        HTMLItem.  addActionListener((e) -> { openHTMLPage();     });
        upItem.    addActionListener((e) -> { moveChapterUp();    });
        downItem.  addActionListener((e) -> { moveChapterDown();  });
        
        switch(windowType){
            
            case REGULATION_WINDOW:
                Team.REGULATION = new Regulation();
                setTitle("Редактирование Устава");
                htmlAddress = "www.sk-bron.ru/ustav.html";
                sourceButton.setVisible(false);
                break;
            case RULES_WINDOW:
                Team.RULES = new Rules();
               
                if(Team.RULES.getChapterByNumber(Regulation.RULES_SOURCE) == null) {
                    Chapter c = new Chapter("");
                    c.setNumber(Regulation.RULES_SOURCE);
                    Team.RULES.addChapter(c);
                }
                
                setTitle("Редактирование раздела \"Правила Страйкбола\"");
                htmlAddress = "www.sk-bron.ru/rules.html";
                sourceButton.setVisible(true);
                break;
        }
        fillTheList();
        
        chapterList.setModel(data);
        try {
            iconUp  = ImageIO.read(new FileImageInputStream(new File("images/sys/up.png")));
            iconDown = ImageIO.read(new FileImageInputStream(new File("images/sys/down.png")));
        } catch (IOException ex) {
           if(options.isCatchPrint()) System.out.println(ex.getMessage());
           JOptionPane.showMessageDialog(new JPanel(), "Ошибка при создании окна опций\n" + ex.getMessage(), "ошибка", JOptionPane.ERROR_MESSAGE);
        }
        upButton.setIcon(new ImageIcon(iconUp));
        downButton.setIcon(new ImageIcon(iconDown));
        checkButtons();
        
    }
    
    private void editChapter(){
        setCursor(waitCursor);
        switch(windowType){
            case 0:
                new AddOrEditChapterWindow(this, true, AddOrEditChapterWindow.REGULATION_WINDOW, Team.REGULATION.getChapterByName(chapterList.getSelectedValue())).setVisible(true);
                fillTheList();
                Team.REGULATION.saveToFile();
                client.uploadRegulationFile();
                pageManager.editRegulationPage();
                break;
            case 1:
                new AddOrEditChapterWindow(this, true, AddOrEditChapterWindow.RULES_WINDOW, Team.RULES.getChapterByName(chapterList.getSelectedValue())).setVisible(true);
                fillTheList();
                Team.RULES.saveToFile();
                client.uploadRulesFile();
                pageManager.editRulesPage();
                break;
        }

        setCursor(defaultCursor);
    }

    private void addChapter(){
        switch (windowType){
            case 0:
                new AddOrEditChapterWindow(this, true, AddOrEditChapterWindow.REGULATION_WINDOW).setVisible(true);
                Team.REGULATION.saveToFile();
                client.uploadRegulationFile();
                pageManager.editRegulationPage();
                break;
            case 1:
                new AddOrEditChapterWindow(this, true, AddOrEditChapterWindow.RULES_WINDOW).setVisible(true);
                Team.RULES.saveToFile();
                client.uploadRulesFile();
                pageManager.editRulesPage();
                break;
        }
        fillTheList();
        chapterList.setSelectedIndex(data.getSize()-1);
    }
    
    private void moveChapterUp(){
        setCursor(waitCursor);
        Chapter toMoveUp, toBeMovedOut;
        switch(windowType){
            case 0:
                toMoveUp = Team.REGULATION.getChapterByName(chapterList.getSelectedValue());
                toBeMovedOut = (Team.REGULATION.getChapterByNumber(toMoveUp.getNumber() - 1));
                toMoveUp.setNumber(toMoveUp.getNumber() - 1);
                toBeMovedOut.setNumber(toBeMovedOut.getNumber()+1);
                Team.REGULATION.sortChapters();
                client.uploadRegulationFile();
                pageManager.editRegulationPage();
                break;

            case 1:
                toMoveUp = Team.RULES.getChapterByName(chapterList.getSelectedValue());
                toBeMovedOut = (Team.RULES.getChapterByNumber(toMoveUp.getNumber() - 1));
                toMoveUp.setNumber(toMoveUp.getNumber() - 1);
                toBeMovedOut.setNumber(toBeMovedOut.getNumber()+1);
                Team.RULES.sortChapters();
                client.uploadRulesFile();
                pageManager.editRulesPage();
                break;    
        }
        fillTheList();
        chapterList.setSelectedIndex(chapterList.getSelectedIndex() - 1);
        setCursor(defaultCursor);

    }
    
    private void moveChapterDown(){
        setCursor(waitCursor);
        Chapter toMoveDown, toBeMovedOut;
        switch(windowType){
            case 0:
                toMoveDown = Team.REGULATION.getChapterByName(chapterList.getSelectedValue());
                toBeMovedOut = (Team.REGULATION.getChapterByNumber(toMoveDown.getNumber() + 1));
                toMoveDown.setNumber(toMoveDown.getNumber() + 1);
                toBeMovedOut.setNumber(toBeMovedOut.getNumber() - 1);
                Team.REGULATION.sortChapters();
                client.uploadRegulationFile();
                pageManager.editRegulationPage();
                break;

            case 1:
                toMoveDown = Team.RULES.getChapterByName(chapterList.getSelectedValue());
                toBeMovedOut = (Team.RULES.getChapterByNumber(toMoveDown.getNumber() + 1));
                toMoveDown.setNumber(toMoveDown.getNumber() + 1);
                toBeMovedOut.setNumber(toBeMovedOut.getNumber() - 1);
                Team.RULES.sortChapters();
                client.uploadRulesFile();
                pageManager.editRulesPage();
                break;
        }                    
        fillTheList();
        chapterList.setSelectedIndex(chapterList.getSelectedIndex() + 1);
     
        setCursor(defaultCursor);
    }
    
    private void openHTMLPage(){
        setCursor(waitCursor);
        try {
            Desktop.getDesktop().browse(new URI(htmlAddress));
        } catch (URISyntaxException ex) {
            if(options.isCatchPrint()) System.out.println(ex.getMessage());
        } catch (IOException ex) {
            if(options.isCatchPrint()) System.out.println(ex.getMessage());
        }

    setCursor(defaultCursor);
    }
    
    private void deleteChapter(){
        setCursor(waitCursor);
        UIManager.put("OptionPane.okButtonText", "Удалить!");
        UIManager.put("OptionPane.cancelButtonText", "Оставить");
        if(JOptionPane.showConfirmDialog(this, "Точно хочешь удалить главу " + 
                chapterList.getSelectedValue()+"?\nВсё её содержание будет утеряно!", "Подтверждение удаления", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE)==0)
        {
            boolean deletingLast = true;
            int max = 0;
            switch(windowType){
                case 0:
                    max = (Team.REGULATION.getChapterByName(chapterList.getSelectedValue())).getNumber();
                    for (Chapter c : Team.REGULATION.getChapters()) {
                        if(c.getNumber() > max)
                            deletingLast = false;
                    }
                    for (String s : data.getList()) {
                        if((Team.REGULATION.getChapterByName(s)).getNumber() > (Team.REGULATION.getChapterByName(chapterList.getSelectedValue())).getNumber())
                            Team.REGULATION.getChapterByName(s).setNumber(Team.REGULATION.getChapterByName(s).getNumber()-1);
                    }
                    Team.REGULATION.deleteChapter(chapterList.getSelectedValue());
                    Team.REGULATION.saveToFile();
                    client.uploadRegulationFile();
                    pageManager.editRegulationPage();
                    break;
                case 1:
                    max = (Team.RULES.getChapterByName(chapterList.getSelectedValue())).getNumber();

                    for (Chapter c : Team.RULES.getChapters()) {
                        if(c.getNumber() > max)
                            deletingLast = false;
                    }
                    for (String s : data.getList()) {
                        if((Team.RULES.getChapterByName(s)).getNumber() > (Team.RULES.getChapterByName(chapterList.getSelectedValue())).getNumber())
                            Team.RULES.getChapterByName(s).setNumber(Team.RULES.getChapterByName(s).getNumber()-1);
                    }
                    Team.RULES.deleteChapter(chapterList.getSelectedValue());
                    Team.RULES.saveToFile();
                    client.uploadRulesFile();
                    pageManager.editRulesPage();
                    break;
            }
            fillTheList();
            if(deletingLast) chapterList.setSelectedIndex(max-2);
        }
        setCursor(defaultCursor);
        checkButtons();
    }    
    
    private void fillTheList(){
        data.getList().clear();
        switch(windowType){
            case 0:
                for (Chapter c : Team.REGULATION.getChapters()) 
                    data.getList().add(c.getName());
                break;
            case 1:
                 for (Chapter c : Team.RULES.getChapters()) 
                    if (c.getNumber() != Regulation.RULES_SOURCE )
                        data.getList().add(c.getName());
                break;
        }
        data.fireContentsChanged();
    }

    private void checkButtons(){
        upItem.setEnabled(true);
        downItem.setEnabled(true);
        editButton.setEnabled(!chapterList.isSelectionEmpty());
        deleteButton.setEnabled(!chapterList.isSelectionEmpty());
        upButton.setEnabled(!chapterList.isSelectionEmpty());
        downButton.setEnabled(!chapterList.isSelectionEmpty());
        if(chapterList.getSelectedIndex()==0) {
            upButton.setEnabled(false);
            upItem.setEnabled(false);
            }
        if(chapterList.getSelectedIndex()==data.getList().size() - 1) {
            downButton.setEnabled(false);
            downItem.setEnabled(false);
        }
        if(data.getList().size() <= 1) deleteButton.setEnabled(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        chapterList = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        upButton = new javax.swing.JButton();
        downButton = new javax.swing.JButton();
        openButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        sourceButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconImage(options.ICON_MAIN);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        chapterList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        chapterList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        chapterList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        chapterList.setToolTipText("");
        chapterList.setFixedCellHeight(25);
        chapterList.setSelectionBackground(new java.awt.Color(94, 93, 1));
        chapterList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chapterListMouseClicked(evt);
            }
        });
        chapterList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                chapterListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(chapterList);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(94, 93, 1), 1, true));

        addButton.setText("Добавить главу");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        upButton.setText("Up");
        upButton.setEnabled(false);
        upButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upButtonActionPerformed(evt);
            }
        });

        downButton.setText("Down");
        downButton.setEnabled(false);
        downButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downButtonActionPerformed(evt);
            }
        });

        openButton.setText("Открыть на сайте");
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Удалить");
        deleteButton.setEnabled(false);
        deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deleteButtonMouseEntered(evt);
            }
        });
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        editButton.setText("Редактировать");
        editButton.setEnabled(false);
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        backButton.setText("Назад");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        exitButton.setText("Выход");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        sourceButton.setText("Источник правил");
        sourceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourceButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(backButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(upButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(downButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(openButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(sourceButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(upButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(downButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sourceButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(openButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exitButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        SystemOptions.windowClosing();
    }//GEN-LAST:event_formWindowClosing
    
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        addChapter();
    }//GEN-LAST:event_addButtonActionPerformed

    private void chapterListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_chapterListValueChanged
        checkButtons();     
    }//GEN-LAST:event_chapterListValueChanged

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        openHTMLPage();
    }//GEN-LAST:event_openButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        deleteChapter();
    }//GEN-LAST:event_deleteButtonActionPerformed
        
    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        editChapter();
    }//GEN-LAST:event_editButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        SystemOptions.windowClosing();
    }//GEN-LAST:event_exitButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        setCursor(waitCursor);
        new ActionMenuWindow().setVisible(true);
        setCursor(defaultCursor);
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void upButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upButtonActionPerformed
        moveChapterUp();
    }//GEN-LAST:event_upButtonActionPerformed

    private void downButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downButtonActionPerformed
        moveChapterDown();
    }//GEN-LAST:event_downButtonActionPerformed

    private void sourceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourceButtonActionPerformed
        UIManager.put("TextField.selectionBackground", new Color(94, 93, 1)); 
        String tempSource = "";
        if(Team.RULES.getChapterByNumber(Regulation.RULES_SOURCE) != null && 
                Team.RULES.getChapterByNumber(Regulation.RULES_SOURCE).getName() != null)
            tempSource  = Team.RULES.getChapterByNumber(Regulation.RULES_SOURCE).getName();

        Chapter source = Team.RULES.getChapterByNumber(Regulation.RULES_SOURCE);
        String temp = JOptionPane.showInputDialog(this, "Введи источник правил", tempSource);
            if(temp!=null)source.setName(temp);
        Team.RULES.addChapter(source);
        Team.RULES.saveToFile();
        client.uploadRulesFile();
        pageManager.editRulesPage();        
    }//GEN-LAST:event_sourceButtonActionPerformed

    private void deleteButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseEntered
        checkButtons();
    }//GEN-LAST:event_deleteButtonMouseEntered

    private void chapterListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chapterListMouseClicked
        if(evt.getButton() == 3){
            if(menu.isShowing()) menu.setVisible(false);
            chapterList.setSelectedIndex(chapterList.locationToIndex(evt.getPoint()));
            menu.show(chapterList, evt.getX(), evt.getY());
        }
        if(evt.getClickCount() == 2 && evt.getButton() == 1) editChapter();
    }//GEN-LAST:event_chapterListMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JList<String> chapterList;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton downButton;
    private javax.swing.JButton editButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton openButton;
    private javax.swing.JButton sourceButton;
    private javax.swing.JButton upButton;
    // End of variables declaration//GEN-END:variables
}
