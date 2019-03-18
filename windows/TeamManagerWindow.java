
package javaapplication58.windows;

import java.awt.Cursor;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javaapplication58.Member;
import javaapplication58.data.Data;
import javaapplication58.MyFTPClient;
import javaapplication58.PageManager;
import javaapplication58.SystemOptions;
import javaapplication58.Team;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;

public class TeamManagerWindow extends javax.swing.JFrame {
private final Data data = new Data();
private SystemOptions options = SystemOptions.getInstance();
private Cursor waitCursor = new Cursor(Cursor.WAIT_CURSOR);
private Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
private String selected = "";
private boolean showMenu;
private final String DELIMETER = "-=================-";
private final Icon iconWasted = new ImageIcon("images/sys/wasted.png");
private final Icon iconWaist = new ImageIcon("images/sys/waist.png");
private final Icon iconInfo = new ImageIcon("images/sys/info.png");
private final Icon iconLupa = new ImageIcon("images/sys/lupa.png");
private final MyFTPClient client = MyFTPClient.getInstace();
private ImageEditorWindow imageEditorWindow;
private final PageManager pageManager;
private final JPopupMenu menu       = new JPopupMenu();
private final JMenuItem  addItem    = new JMenuItem("Добавить бойца");
private final JMenuItem  editItem   = new JMenuItem("Редактировать");
private final JMenuItem  openItem   = new JMenuItem("Открыть на сайте");
private final JMenuItem  infoItem   = new JMenuItem("Информация");
private final JMenuItem  photoItem  = new JMenuItem("Фотографии профиля");
private final JMenuItem  deleteItem = new JMenuItem("Отчислить");



    public TeamManagerWindow(MyFTPClient client) {
        initComponents();
        menu.add(editItem);
        menu.add(infoItem);
        menu.add(openItem);
        menu.add(photoItem);
        menu.add(deleteItem);
        menu.addSeparator();
        menu.add(addItem);
        addItem.   addActionListener((e) -> { addMember();             });
        infoItem.  addActionListener((e) -> { showInfoWindow();        });
        editItem.  addActionListener((e) -> { showEditWindow();        });
        openItem.  addActionListener((e) -> { openHTMLPage();          });
        photoItem. addActionListener((e) -> { showImageEditorWindow(); });
        deleteItem.addActionListener((e) -> { deleteMember();          });
        
        membersList.setModel(data);
        searchLabel.setText("");
        ToolTipManager.sharedInstance().setInitialDelay(100);
        ToolTipManager.sharedInstance().setDismissDelay(10000);
        pageManager = new PageManager();
        Team.sort();
        fillTheList();
        this.client.uploadFile(Team.userData, MyFTPClient.FTPuser);
        if(options.isDebugPrint()) System.out.println("Окно редактирование состава");
    }
    
    public JList<String> getMembersList() {  return membersList;    }
    
    private void fillTheList(){
        int id = 99999;
        data.getList().clear();
        for (Member member : Team.getTeamMembers()) {
            if(id  < 777 && member.getId() == 777) data.getList().add(DELIMETER);
            if(id == 777 && member.getId()  > 777) data.getList().add(DELIMETER);
            if(id == 999 && member.getId()  > 999) data.getList().add(DELIMETER);
            data.getList().add(member.getCallSign() + " - " +member.getPosition());
            id = member.getId();
        }
    }
  
    private void addMember(){
        setCursor(waitCursor);
        setCursor(defaultCursor);
       new AddNewFighterWindow(this, true).setVisible(true);
       fillTheList();
       data.fireContentsChanged();       
    }
   
    private void deleteMember(){
        setCursor(waitCursor);
        setCursor(defaultCursor);
        UIManager.put("OptionPane.yesButtonText"   , "Отчислить!");
        UIManager.put("OptionPane.noButtonText"    , "Оставить в команде");
        if( JOptionPane.showConfirmDialog(this, selected + " будет окончательно и бесповоротно отчислен. Уверен?", 
            "Подтверждение отчисления", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, iconWaist) == 0) 
            {
                Member member = Team.getMember(selected);
                member.removeFromTeam();
                client.deletePage(member.getLink(), member.isCompetent());
                fillTheList();
                avatarButton.setEnabled(false);
                editButton.setEnabled(false);
                deleteButton.setEnabled(false);
                membersList.clearSelection();
                client.uploadTeamDataFile();
                pageManager.editNavigationPage();
                pageManager.editMainPage();
                JOptionPane.showMessageDialog(this, "Сказано - сделано! " + selected + " отчислен из команды",
                    selected +  " отчислен", JOptionPane.INFORMATION_MESSAGE, iconWasted);
                data.fireContentsChanged();
        }              
    }
    
    private void showEditWindow(){
        setCursor(waitCursor);
        new MemberEditWindow(Team.getMember(selected)).setVisible(true);
        dispose();      
    }
    
    private void showInfoWindow(){
        Member m = Team.getMember(selected);
        InfoWindow infoWindow = new InfoWindow(m);
        JOptionPane.showMessageDialog(this, infoWindow.getMainPanel(), m.getName() + ' ' + m.getCallSign() + " - краткая информация", JOptionPane.INFORMATION_MESSAGE, iconInfo);
    }
    
    private void showImageEditorWindow(){
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        imageEditorWindow = new ImageEditorWindow(Team.getMember(selected));
        imageEditorWindow.setVisible(true);
        dispose();
    }
    
    private void openHTMLPage(){
        Member m = Team.getMember(selected);
        try {
            Desktop.getDesktop().browse(new URI("www.sk-bron.ru/Fighters/"+ m.getLink()+".html"));
        } catch (URISyntaxException ex) {
            if(options.isCatchPrint()) System.out.println(ex.getMessage());
        } catch (IOException ex) {
            if(options.isCatchPrint()) System.out.println(ex.getMessage());
        }
    }
    
    private void filter(){
        Member member;
        int id = 99999;
        ArrayList<String> fullsize = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        
        data.getList().clear();
        
        for (Member m : Team.getTeamMembers())  fullsize.add(m.getCallSign());
        
        for (String string : fullsize) {
            member = Team.getMember(string);
            if(compCheckBox.isSelected()){
                if(member.isCompetent() && !member.isReserved())
                    temp.add(member.getCallSign()+" - " +member.getPosition());
            }
            if(candCheckBox.isSelected()){
                if(!member.isCompetent() && !member.isReserved())
                    temp.add(member.getCallSign()+" - " +member.getPosition());
            }
            if(resCheckBox.isSelected()){
                if(member.isReserved())
                    temp.add(member.getCallSign()+" - " +member.getPosition());
            }
        }
        
        String toSearch = searchField.getText();
        String tmp;
         
        ArrayList<Member> mArray = new ArrayList<>();
            for (String s : temp) {

                if(toSearch.length()<=s.length()){
                    tmp = s.substring(0, toSearch.length());
                    if(toSearch.compareTo(tmp) == 0 ){
                        StringTokenizer stk =new StringTokenizer(s);
                        s = stk.nextToken();
                        mArray.add(Team.getMember(s));
                    }
                }
            }
        
        for (Member m : mArray) {
            if(id  < 777 && m.getId() == 777) data.getList().add(DELIMETER);
            if(id == 777 && m.getId()  > 777) data.getList().add(DELIMETER);
            if(id == 999 && m.getId()  > 999) data.getList().add(DELIMETER);
            data.getList().add(m.getCallSign() + " - " + m.getPosition());
            id = m.getId();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jDialog3 = new javax.swing.JDialog();
        jDialog4 = new javax.swing.JDialog();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        infoButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        avatarButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        openButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        membersList = new javax.swing.JList<>();
        searchField = new javax.swing.JTextField();
        candCheckBox = new javax.swing.JCheckBox();
        compCheckBox = new javax.swing.JCheckBox();
        resCheckBox = new javax.swing.JCheckBox();
        searchLabel = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog3Layout = new javax.swing.GroupLayout(jDialog3.getContentPane());
        jDialog3.getContentPane().setLayout(jDialog3Layout);
        jDialog3Layout.setHorizontalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog3Layout.setVerticalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog4Layout = new javax.swing.GroupLayout(jDialog4.getContentPane());
        jDialog4.getContentPane().setLayout(jDialog4Layout);
        jDialog4Layout.setHorizontalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog4Layout.setVerticalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("БрОНонастройка");
        setFocusTraversalPolicyProvider(true);
        setIconImage(options.ICON_MAIN);
        setResizable(false);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(94, 93, 1), null));

        addButton.setText("Добавить бойца");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        infoButton.setText("Информация");
        infoButton.setEnabled(false);
        infoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoButtonActionPerformed(evt);
            }
        });

        editButton.setText("Редактировать");
        editButton.setEnabled(false);
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        avatarButton.setText("Фотографии профиля");
        avatarButton.setEnabled(false);
        avatarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avatarButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Отчислить! ");
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        backButton.setText("К предыдущему меню");
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

        openButton.setText("Открыть на сайте");
        openButton.setToolTipText("Открыть на сайте");
        openButton.setEnabled(false);
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(backButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(avatarButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(infoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(openButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(infoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(openButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(avatarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(backButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exitButton)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(94, 93, 1), null));

        jScrollPane1.setToolTipText("");

        membersList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 120, 0)));
        membersList.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        membersList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Щепа", "Юрец", "Маэстро", "Ил", "Тапок", "Китаец" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        membersList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        membersList.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        membersList.setSelectionBackground(new java.awt.Color(94, 93, 1));
        membersList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                membersListMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                membersListMouseEntered(evt);
            }
        });
        membersList.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                membersListCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        membersList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                membersListKeyPressed(evt);
            }
        });
        membersList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                membersListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(membersList);

        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchFieldKeyTyped(evt);
            }
        });

        candCheckBox.setSelected(true);
        candCheckBox.setText("Кандидаты");
        candCheckBox.setToolTipText("");
        candCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                candCheckBoxItemStateChanged(evt);
            }
        });

        compCheckBox.setSelected(true);
        compCheckBox.setText("Бойцы ");
        compCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                compCheckBoxItemStateChanged(evt);
            }
        });
        compCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                compCheckBoxStateChanged(evt);
            }
        });

        resCheckBox.setSelected(true);
        resCheckBox.setText("Резервисты");
        resCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                resCheckBoxItemStateChanged(evt);
            }
        });

        searchLabel.setBackground(new java.awt.Color(255, 255, 255));
        searchLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        searchLabel.setIcon(iconLupa);
        searchLabel.setText("jLabel1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(searchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchField))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(compCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(candCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(candCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(compCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(searchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        setSize(new java.awt.Dimension(580, 501));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void membersListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_membersListValueChanged
        
        if(!membersList.isSelectionEmpty()){
            showMenu = true;
            if(membersList.getSelectedValue().equals(DELIMETER)){
                membersList.setSelectedIndex(membersList.getSelectedIndex() - 1);
                 showMenu = false;
            }
            StringTokenizer stk = new StringTokenizer(membersList.getSelectedValue());
            selected = stk.nextToken();}
            openButton.setEnabled(!membersList.isSelectionEmpty());
            infoButton.setEnabled(!membersList.isSelectionEmpty());
            avatarButton.setEnabled(!membersList.isSelectionEmpty());
            editButton.setEnabled(!membersList.isSelectionEmpty());
            deleteButton.setEnabled(!membersList.isSelectionEmpty() && 
                    Team.getMember(selected).getId() != 1 && 
                    Team.getMember(selected).getId() != 11 && 
                    Team.getMember(selected).getId() != 22);
        
    }//GEN-LAST:event_membersListValueChanged

    private void membersListCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_membersListCaretPositionChanged
        // TODO add your handling code here:  
    }//GEN-LAST:event_membersListCaretPositionChanged

    private void avatarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_avatarButtonActionPerformed
       showImageEditorWindow();
    }//GEN-LAST:event_avatarButtonActionPerformed
 
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        deleteMember();
    }//GEN-LAST:event_deleteButtonActionPerformed
    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        showEditWindow();    
    }//GEN-LAST:event_editButtonActionPerformed
    
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
       addMember();     
    }//GEN-LAST:event_addButtonActionPerformed
 
    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
             
    }//GEN-LAST:event_formFocusGained

    private void membersListMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_membersListMouseEntered
    }//GEN-LAST:event_membersListMouseEntered

    private void infoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoButtonActionPerformed
        showInfoWindow();
    }//GEN-LAST:event_infoButtonActionPerformed
    
    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        openHTMLPage();
    }//GEN-LAST:event_openButtonActionPerformed
    
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        setCursor(waitCursor);
        new ActionMenuWindow().setVisible(true);
        dispose();        
    }//GEN-LAST:event_backButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        SystemOptions.windowClosing();
    }//GEN-LAST:event_exitButtonActionPerformed

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
      
    }//GEN-LAST:event_searchFieldActionPerformed

    private void searchFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyTyped

    }//GEN-LAST:event_searchFieldKeyTyped

    private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyReleased
        data.getList().clear();
        String toSearch = searchField.getText();
        String temp;
        if(toSearch!=null){
            if(toSearch.length() == 1){
                toSearch = toSearch.toUpperCase();
                searchField.setText(toSearch);
            }
            toSearch = searchField.getText();
        
            for (Member member : Team.getTeamMembers()) {

                if(toSearch.length()<=member.getCallSign().length()){
                    temp = member.getCallSign().substring(0, toSearch.length());
                    if(toSearch.compareTo(temp) == 0 )
                      data.getList().add(member.getCallSign()+ " - " +member.getPosition());
                }
            }
            
        }
        else{
            for (Member member : Team.getTeamMembers())
                data.getList().add(member.getCallSign()+ " - " +member.getPosition());
            
            
        }
            filter();
            membersList.updateUI();
            membersList.repaint();    
    }//GEN-LAST:event_searchFieldKeyReleased

    private void compCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_compCheckBoxStateChanged
        
    }//GEN-LAST:event_compCheckBoxStateChanged

    private void compCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_compCheckBoxItemStateChanged
        filter();
    }//GEN-LAST:event_compCheckBoxItemStateChanged

    private void candCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_candCheckBoxItemStateChanged
        filter();
    }//GEN-LAST:event_candCheckBoxItemStateChanged

    private void resCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_resCheckBoxItemStateChanged
        filter();
    }//GEN-LAST:event_resCheckBoxItemStateChanged

    private void membersListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_membersListKeyPressed
        if(evt.getKeyCode()==40){
            evt.consume();
            if(membersList.getSelectedIndex() < data.getSize()-1 && data.getList().get(membersList.getSelectedIndex()+1).equals(DELIMETER)){
                membersList.ensureIndexIsVisible(membersList.getSelectedIndex()+2);
                membersList.setSelectedIndex(membersList.getSelectedIndex()+2);
            }
            else if(membersList.getSelectedIndex() < data.getSize() -1){
                membersList.ensureIndexIsVisible(membersList.getSelectedIndex()+1);
                membersList.setSelectedIndex(membersList.getSelectedIndex() + 1 );
            }
        }
    }//GEN-LAST:event_membersListKeyPressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        SystemOptions.windowClosing();
    }//GEN-LAST:event_formWindowClosing

    private void membersListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_membersListMouseClicked
        if(evt.getButton() == 3){
            
            if(menu.isShowing()) menu.setVisible(false);
            membersList.setSelectedIndex(membersList.locationToIndex(evt.getPoint()));
            menu.show(membersList, evt.getX(), evt.getY());
            menu.setVisible(showMenu);
        }
        else{
            setCursor(waitCursor);
            if(evt.getClickCount() == 2 && evt.getButton() == 1){   
                new MemberEditWindow(Team.getMember(selected)).setVisible(true);
                dispose();
            }   
        }    
    }//GEN-LAST:event_membersListMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton avatarButton;
    private javax.swing.JButton backButton;
    private javax.swing.JCheckBox candCheckBox;
    private javax.swing.JCheckBox compCheckBox;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton infoButton;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JDialog jDialog3;
    private javax.swing.JDialog jDialog4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> membersList;
    private javax.swing.JButton openButton;
    private javax.swing.JCheckBox resCheckBox;
    private javax.swing.JTextField searchField;
    private javax.swing.JLabel searchLabel;
    // End of variables declaration//GEN-END:variables
}
