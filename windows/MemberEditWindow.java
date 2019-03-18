
package javaapplication58.windows;

import javaapplication58.panels.MemberHelpPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javaapplication58.panels.ApplyFighterChangesDialog;
import javaapplication58.Member;
import javaapplication58.MyFTPClient;
import javaapplication58.MyImageEditor;
import javaapplication58.PageManager;
import javaapplication58.SystemOptions;
import javaapplication58.Team;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.apache.commons.net.ftp.FTP;


public class MemberEditWindow extends javax.swing.JFrame {
    
    private final MyFTPClient client = MyFTPClient.getInstace();
    private final Icon questionIcon = new ImageIcon("images/sys/question.png");
    private final PageManager pageManager;
    private final Color color = new Color(94, 93, 1);
    private final File noPic = new File("images/sys/nopic.jpg");
    private Member member, memberTemp, memberSuckerTemp, memberSucker=null;
    private final MyImageEditor imageEditor;
    private SystemOptions options = SystemOptions.getInstance();
    private final ButtonGroup specialistButtonGroup = new ButtonGroup();
    private final ButtonGroup positionButtonGroup = new ButtonGroup();
    private File file, file1, file2, file3, fileFull1, fileFull2, fileFull3, dir;
    private ApplyFighterChangesDialog applyWindow = new ApplyFighterChangesDialog(this, true);
    private DatesEditWindow datesEditWindow = null;
    private String linkToDelete = null;
    private boolean wasCompetent = false;
    
    public MemberEditWindow(Member member) {
        BufferedImage image = null;
        initComponents();
        avatarImageLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.imageEditor = new MyImageEditor();
        pageManager =  new PageManager();
        this.member = member;
        this.memberTemp = new Member(this.member);
        if(this.member.isCompetent())
            dateLabel.setText("Дата вступления в бойцы: " + 
                Team.dateFormat.format(this.member.getDateCompetent().getTime()));
        else
            dateLabel.setText("Дата вступления в кандидаты: " + 
                Team.dateFormat.format(this.member.getDateCandidate().getTime()));
        if(this.member.isReserved())
            dateLabel.setText("Дата зачисления в резервисты: " + 
                Team.dateFormat.format(this.member.getDateReserve().getTime()));
        
        setTitle(member.getName() +' ' + member.getCallSign() + " - редактирование");
        dir = new File("images");
        File[] dirlist = dir.listFiles();
        for (File f : dirlist) {
            if(f.isFile()) f.delete();
        }
      
        file      = new File("images/temp/"+member.getLink()+".jpg");
        fileFull1 = new File("images/temp/"+member.getLink()+"1.jpg");
        fileFull2 = new File("images/temp/"+member.getLink()+"2.jpg");
        fileFull3 = new File("images/temp/"+member.getLink()+"3.jpg");
        file1     = new File("images/temp/"+member.getLink()+"1.png");
        file2     = new File("images/temp/"+member.getLink()+"2.png");
        file3     = new File("images/temp/"+member.getLink()+"3.png");
        

        client.retriveFile(file, "/www/sk-bron.ru/img/Fighters/"+member.getLink()+".jpg");
        try{    
            image = ImageIO.read(file);
        } catch (IOException ex) {
            if(options.isCatchPrint()) System.out.println(ex.getMessage());
        }
        
    if(this.member.isCompetent()){
        client.retriveFile(fileFull1, "/www/sk-bron.ru/img/Fighters/fullsize/"+member.getLink()+"1.jpg");
        client.retriveFile(fileFull2, "/www/sk-bron.ru/img/Fighters/fullsize/"+member.getLink()+"2.jpg");
        client.retriveFile(fileFull3, "/www/sk-bron.ru/img/Fighters/fullsize/"+member.getLink()+"3.jpg");
        client.retriveFile(file1, "/www/sk-bron.ru/img/Fighters/"+member.getLink()+"1.png");
        client.retriveFile(file2, "/www/sk-bron.ru/img/Fighters/"+member.getLink()+"2.png");        
        client.retriveFile(file3, "/www/sk-bron.ru/img/Fighters/"+member.getLink()+"3.png");
        
    }
    
        titleLabel.setText(member.getName() + "  \"" + member.getCallSign() + "\"");
        specialistButtonGroup.add(buttonSpecRifleman);
        specialistButtonGroup.add(buttonSpecSniper);
        specialistButtonGroup.add(buttonSpecMachinegunner);
        specialistButtonGroup.add(buttonSpecMiner);
        specialistButtonGroup.add(buttonSpecScout);
        specialistButtonGroup.add(buttonSpecStormTrooper);
        
        positionButtonGroup.add(buttonPosCompetent);
        positionButtonGroup.add(buttonPosCraftsman);
        positionButtonGroup.add(buttonPosSergeant);
        positionButtonGroup.add(buttonPosSubCommander);
        positionButtonGroup.add(buttonPosCommander);
        
        changeSignTextField.setText(member.getCallSign());

        changeNameTextField.setText(member.getName());
        
        setButtonsEnabled(member.isCompetent() && !member.isReserved());
        
        if(member.isCompetent() && !member.isReserved()) {
            statusListBox.setSelectedIndex(1);
            specialisationLabel.setForeground(Color.BLACK);
            positionLabel.setForeground(Color.BLACK);
            
            buttonSpecRifleman.setSelected(member.getSpec1().equals(""));
            buttonSpecSniper.setSelected(member.getSpec1().equals(", снайпер"));
            buttonSpecMachinegunner.setSelected(member.getSpec1().equals(", пулеметчик"));
            buttonSpecMiner.setSelected(member.getSpec1().equals(", сапер"));
            
            buttonPosCommander.setSelected(member.getPosition().equals("Командир"));
            buttonPosCompetent.setSelected(member.getPosition().equals("Боец"));
            buttonPosCraftsman.setSelected(member.getPosition().equals("Мастер-оружейник"));
            buttonPosSergeant.setSelected(member.getPosition().equals("Старшина"));
            buttonPosSubCommander.setSelected(member.getPosition().equals("Зам. командира"));
                    
        }
        
        if(member.isReserved()) {
            statusListBox.setSelectedIndex(2);
            specialisationLabel.setForeground(Color.GRAY);
            positionLabel.setForeground(Color.GRAY);
        }

        if(image != null)
           avatarImageLabel.setIcon(new javax.swing.ImageIcon(image));
        datesEditWindow = new DatesEditWindow(this, memberTemp, true);
        
        titleLabel.setForeground(color);
        buttonHelp.setIcon(questionIcon);
        System.out.println("Редактор страницы бойца " + member.getName() + ' ' + member.getCallSign());
    }

    private void setButtonsEnabled(boolean b){
        
        buttonSpecRifleman.setEnabled(b);
        buttonSpecMachinegunner.setEnabled(b);
        buttonSpecSniper.setEnabled(b);
        buttonSpecMiner.setEnabled(b);
        buttonSpecScout.setEnabled(b);
        buttonSpecStormTrooper.setEnabled(b);
        
        buttonPosCommander.setEnabled(b);
        buttonPosCompetent.setEnabled(b);
        buttonPosCraftsman.setEnabled(b);
        buttonPosSergeant.setEnabled(b);
        buttonPosSubCommander.setEnabled(b);
      
        if(b) {
            specialisationLabel.setForeground(Color.BLACK);
            positionLabel.setForeground(Color.BLACK);
        }
        else {
            specialisationLabel.setForeground(Color.GRAY);
            positionLabel.setForeground(Color.GRAY);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        buttonSpecRifleman = new javax.swing.JRadioButton();
        buttonSpecMachinegunner = new javax.swing.JRadioButton();
        buttonSpecSniper = new javax.swing.JRadioButton();
        specialisationLabel = new javax.swing.JLabel();
        positionLabel = new javax.swing.JLabel();
        avatarImageLabel = new javax.swing.JLabel();
        buttonPosCompetent = new javax.swing.JRadioButton();
        buttonPosCraftsman = new javax.swing.JRadioButton();
        buttonPosSergeant = new javax.swing.JRadioButton();
        buttonPosSubCommander = new javax.swing.JRadioButton();
        buttonPosCommander = new javax.swing.JRadioButton();
        changeSignTextField = new javax.swing.JTextField();
        statusListBox = new javax.swing.JComboBox<>();
        statusLabel = new javax.swing.JLabel();
        buttonSave = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        buttonChangeCallSign = new javax.swing.JToggleButton();
        errorSignLabel = new javax.swing.JLabel();
        changeNameTextField = new javax.swing.JTextField();
        buttonChangeName = new javax.swing.JToggleButton();
        errorNameLabel = new javax.swing.JLabel();
        buttonSpecMiner = new javax.swing.JRadioButton();
        buttonSpecScout = new javax.swing.JRadioButton();
        buttonSpecStormTrooper = new javax.swing.JRadioButton();
        dateLabel = new javax.swing.JLabel();
        changeDateButtom = new javax.swing.JButton();
        buttonOpen = new javax.swing.JButton();
        buttonHelp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconImage(options.ICON_MAIN);
        setResizable(false);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        titleLabel.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        titleLabel.setText("Сергей Тапок");

        buttonSpecRifleman.setText("автоматчик");

        buttonSpecMachinegunner.setText("пулемётчик");
        buttonSpecMachinegunner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSpecMachinegunnerActionPerformed(evt);
            }
        });

        buttonSpecSniper.setText("снайпер");

        specialisationLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        specialisationLabel.setForeground(new java.awt.Color(102, 102, 102));
        specialisationLabel.setText("Специализация");

        positionLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        positionLabel.setForeground(new java.awt.Color(102, 102, 102));
        positionLabel.setText("Должность");

        avatarImageLabel.setBackground(new java.awt.Color(255, 255, 255));
        avatarImageLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        avatarImageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        avatarImageLabel.setText("ТУТ ДОЛЖЕН БЫТЬ АВАТАР");
        avatarImageLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(120, 120, 0), 4, true));
        avatarImageLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                avatarImageLabelMouseClicked(evt);
            }
        });

        buttonPosCompetent.setText("Боец");

        buttonPosCraftsman.setText("Мастер-оружейник");
        buttonPosCraftsman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPosCraftsmanActionPerformed(evt);
            }
        });

        buttonPosSergeant.setText("Старшина");

        buttonPosSubCommander.setText("Зам. командира");

        buttonPosCommander.setText("Командир");

        changeSignTextField.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        changeSignTextField.setSelectionColor(new java.awt.Color(94, 93, 1));
        changeSignTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                changeSignTextFieldFocusGained(evt);
            }
        });
        changeSignTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                changeSignTextFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                changeSignTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                changeSignTextFieldKeyTyped(evt);
            }
        });

        statusListBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Кандидат", "Боец", "Резервист" }));
        statusListBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                statusListBoxItemStateChanged(evt);
            }
        });
        statusListBox.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                statusListBoxPropertyChange(evt);
            }
        });

        statusLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        statusLabel.setText("Статус");

        buttonSave.setText("Сохранить");
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });

        buttonCancel.setText("Отмена");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        buttonChangeCallSign.setText("сменить позывной");
        buttonChangeCallSign.setToolTipText("");
        buttonChangeCallSign.setEnabled(false);
        buttonChangeCallSign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChangeCallSignActionPerformed(evt);
            }
        });

        errorSignLabel.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        errorSignLabel.setForeground(java.awt.Color.green);
        errorSignLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        errorSignLabel.setText("Актуальный позывной бойца ");
        errorSignLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        changeNameTextField.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        changeNameTextField.setSelectionColor(new java.awt.Color(94, 93, 1));
        changeNameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                changeNameTextFieldFocusGained(evt);
            }
        });
        changeNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                changeNameTextFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                changeNameTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                changeNameTextFieldKeyTyped(evt);
            }
        });

        buttonChangeName.setText("сменить имя");
        buttonChangeName.setToolTipText("");
        buttonChangeName.setEnabled(false);

        errorNameLabel.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        errorNameLabel.setForeground(java.awt.Color.green);
        errorNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        errorNameLabel.setText("Актуальное имя бойца ");
        errorNameLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        buttonSpecMiner.setText("сапёр");
        buttonSpecMiner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSpecMinerActionPerformed(evt);
            }
        });

        buttonSpecScout.setText("разведчик");

        buttonSpecStormTrooper.setText("штурмовик");

        dateLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dateLabel.setText("Дата вступления в должность: ");

        changeDateButtom.setText("Изменить даты");
        changeDateButtom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeDateButtomActionPerformed(evt);
            }
        });

        buttonOpen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buttonOpen.setText("Открыть на сайте");
        buttonOpen.setActionCommand("");
        buttonOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOpenActionPerformed(evt);
            }
        });

        buttonHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHelpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(140, 140, 140)
                                .addComponent(errorNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(errorSignLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(120, 120, 120)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(changeNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buttonChangeName, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(changeSignTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(buttonChangeCallSign))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(positionLabel)
                                .addGap(130, 130, 130))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(statusLabel)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(statusListBox, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(specialisationLabel)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(buttonPosSubCommander)
                                            .addComponent(buttonPosSergeant)
                                            .addComponent(buttonPosCommander, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(buttonPosCraftsman)
                                            .addComponent(buttonPosCompetent)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(buttonSpecMachinegunner)
                                            .addComponent(buttonSpecRifleman)
                                            .addComponent(buttonSpecSniper)
                                            .addComponent(buttonSpecMiner)
                                            .addComponent(buttonSpecScout)
                                            .addComponent(buttonSpecStormTrooper))))
                                .addGap(44, 44, 44)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(avatarImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(changeDateButtom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buttonOpen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(216, 216, 216))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(statusLabel)
                        .addGap(11, 11, 11)
                        .addComponent(statusListBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(specialisationLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSpecRifleman)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSpecMachinegunner)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSpecSniper)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSpecMiner)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSpecScout)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSpecStormTrooper)
                        .addGap(6, 6, 6)
                        .addComponent(positionLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonPosCompetent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonPosCraftsman)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonPosSergeant)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonPosSubCommander)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonPosCommander)
                        .addGap(36, 36, 36))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buttonOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(changeDateButtom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(avatarImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errorNameLabel)
                            .addComponent(errorSignLabel))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(changeNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonChangeName)
                    .addComponent(changeSignTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonChangeCallSign))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(743, 631));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSpecMachinegunnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSpecMachinegunnerActionPerformed

    }//GEN-LAST:event_buttonSpecMachinegunnerActionPerformed

    private void statusListBoxPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_statusListBoxPropertyChange
        
    }//GEN-LAST:event_statusListBoxPropertyChange

    private void statusListBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_statusListBoxItemStateChanged
        switch(statusListBox.getSelectedIndex()){
            case 0:
            case 2:
                specialistButtonGroup.clearSelection();
                positionButtonGroup.clearSelection();
                setButtonsEnabled(false);
                break;
            
            case 1:
                setButtonsEnabled(true);
                buttonSpecRifleman.setSelected(true);
                buttonPosCompetent.setSelected(true);
                
                buttonSpecRifleman.setSelected(member.getSpec1().equals(", автоматчик"));
                buttonSpecSniper.setSelected(member.getSpec1().equals(", снайпер"));
                buttonSpecMachinegunner.setSelected(member.getSpec1().equals(", пулеметчик"));
                buttonSpecMiner.setSelected(member.getSpec1().equals(", гренадер"));
                buttonSpecScout.setSelected(member.getSpec1().equals(", разведчик"));
                buttonSpecStormTrooper.setSelected(member.getSpec1().equals(", штурмовик"));
                
                buttonPosCommander.setSelected(member.getPosition().equals("Командир"));
                buttonPosCompetent.setSelected(member.getPosition().equals("Боец"));
                buttonPosCraftsman.setSelected(member.getPosition().equals("Мастер-оружейник"));
                buttonPosSergeant.setSelected(member.getPosition().equals("Старшина"));
                buttonPosSubCommander.setSelected(member.getPosition().equals("Зам. командира"));
              
                break;

        }
    }//GEN-LAST:event_statusListBoxItemStateChanged

    private void changeSignTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_changeSignTextFieldKeyPressed
           
    }//GEN-LAST:event_changeSignTextFieldKeyPressed

    private void changeSignTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_changeSignTextFieldKeyReleased
    buttonChangeCallSign.setEnabled(checkCallSign());
    
    }//GEN-LAST:event_changeSignTextFieldKeyReleased

    private void changeSignTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_changeSignTextFieldKeyTyped
        Team.eliminateEnglishLetters(evt);
        buttonChangeCallSign.setSelected(false);
    }//GEN-LAST:event_changeSignTextFieldKeyTyped

    private void changeNameTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_changeNameTextFieldKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_changeNameTextFieldKeyPressed

    private void changeNameTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_changeNameTextFieldKeyReleased
     buttonChangeName.setEnabled(checkName());
    }//GEN-LAST:event_changeNameTextFieldKeyReleased

    private void changeNameTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_changeNameTextFieldKeyTyped
        Team.eliminateEnglishLetters(evt);
        buttonChangeName.setSelected(false);
    }//GEN-LAST:event_changeNameTextFieldKeyTyped

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
     
        if(memberSucker!=null) memberSucker.copy(memberSuckerTemp);
        if (file.exists()) file.delete();
        new TeamManagerWindow(client).setVisible(true);
        dispose(); 
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
    setCursor(new Cursor(Cursor.WAIT_CURSOR));
    if(datesEditWindow!=null && datesEditWindow.isChanges())
        {
        if(!memberTemp.getDateCandidate().equals(datesEditWindow.getDateCan())){
            memberTemp.getDateCandidate().clear();
            memberTemp.setCandidateDate(datesEditWindow.getDateCan().getTime());
            applyWindow.getDateCanValueLabel().setForeground(Color.red);
            applyWindow.getDateCanValueLabel().setText(Team.dateFormat.format(datesEditWindow.getDateCan().getTime()));
        }
        else applyWindow.getDateCanValueLabel().setForeground(Color.black);

        if(memberTemp.isCompetent() && !memberTemp.getDateCompetent().equals(datesEditWindow.getDateCom())){
            memberTemp.getDateCompetent().clear();
            memberTemp.setCompetentDate(datesEditWindow.getDateCom().getTime());               
            applyWindow.getDateComValueLabel().setForeground(Color.red);
        }
        else applyWindow.getDateComValueLabel().setForeground(Color.black);


        if(memberTemp.isReserved() && !memberTemp.getDateReserve().equals(datesEditWindow.getDateRes())){
            memberTemp.getDateReserve().clear();
            memberTemp.setReserveDate(datesEditWindow.getDateRes().getTime());
            applyWindow.getDateResValueLabel().setForeground(Color.red);
        }
        else applyWindow.getDateResValueLabel().setForeground(Color.black);





    }
    applyWindow.getDateCanValueLabel().setText(Team.dateFormat.format(datesEditWindow.getDateCan().getTime()));
    applyWindow.getDateComValueLabel().setText(Team.dateFormat.format(datesEditWindow.getDateCom().getTime()));
    applyWindow.getDateResValueLabel().setText(Team.dateFormat.format(datesEditWindow.getDateRes().getTime()));


    if(memberTemp.isCompetent()) {
        applyWindow.getDateComLabel().setForeground(Color.BLACK);
    }
    else{
        applyWindow.getDateComLabel().setForeground(Color.GRAY);
        applyWindow.getDateComValueLabel().setText("");
    }

    if(memberTemp.isReserved()) {
        applyWindow.getDateResLabel().setForeground(Color.BLACK);


    }
    else{
        applyWindow.getDateResLabel().setForeground(Color.GRAY);
        applyWindow.getDateResValueLabel().setText("");
    }


    if (buttonChangeName.isSelected()){
        memberTemp.setName(changeNameTextField.getText());
        applyWindow.getNameValueLabel().setForeground(Color.red);
        applyWindow.getNameValueLabel().setText(changeNameTextField.getText());}
    else
        applyWindow.getNameValueLabel().setText(member.getName());


    //////////////РАБОТА С ПОЗЫВНЫМИ//////////////////
    if (buttonChangeCallSign.isSelected()){
        linkToDelete = memberTemp.getLink();
        wasCompetent = memberTemp.isCompetent();
        memberTemp.setCallSign(changeSignTextField.getText());
        imageEditor.editAvatarPicture(file, memberTemp.getCallSign(), memberTemp.getLink());
        if(memberTemp.isCompetent())
        {
            client.uploadFile(file1, "/www/sk-bron.ru/img/Fighters/"+memberTemp.getLink()+"1.png");
            client.uploadFile(file2, "/www/sk-bron.ru/img/Fighters/"+memberTemp.getLink()+"2.png");
            client.uploadFile(file3, "/www/sk-bron.ru/img/Fighters/"+memberTemp.getLink()+"3.png");

            client.uploadFile(fileFull1, "/www/sk-bron.ru/img/Fighters/fullsize/"+memberTemp.getLink()+"1.jpg");
            client.uploadFile(fileFull2, "/www/sk-bron.ru/img/Fighters/fullsize/"+memberTemp.getLink()+"2.jpg");
            client.uploadFile(fileFull3, "/www/sk-bron.ru/img/Fighters/fullsize/"+memberTemp.getLink()+"3.jpg");
        }
        applyWindow.getSignValueLabel().setForeground(Color.red);
        applyWindow.getSignValueLabel().setText(changeSignTextField.getText());}
    else
        applyWindow.getSignValueLabel().setText(member.getCallSign());

    ///////////////////////////////////////

    switch(statusListBox.getSelectedIndex()){
        case 0:
            if(member.getId()!=999 ){
                memberTemp.setCandidate();
                applyWindow.getStatusValueLabel().setForeground(Color.red);
            }
            else applyWindow.setForeground(Color.black);
            applyWindow.getStatusValueLabel().setText("Кандидат");

            break;

        case 1:
            if(member.getId()==999 || member.getId()==1111){
                memberTemp.setCompetent();
                for (int i = 1; i < 4; i++) {
                   imageEditor.editPhoto(noPic, memberTemp.getLink(), i);

                }
                applyWindow.getStatusValueLabel().setForeground(Color.red);
                applyWindow.getDateComLabel().setForeground(Color.black);
                applyWindow.getDateComValueLabel().setForeground(Color.red);
                applyWindow.getDateComValueLabel().setText(Team.dateFormat.format(datesEditWindow.getDateCom().getTime()));
            }
            else applyWindow.setForeground(Color.black);
            applyWindow.getStatusValueLabel().setText("Боец");
            break;

        case 2:
            if(member.getId()!=1111){
                memberTemp.setReserve();
                applyWindow.getStatusValueLabel().setForeground(Color.red);
                applyWindow.getDateResLabel().setForeground(Color.BLACK);
                applyWindow.getDateResValueLabel().setForeground(Color.red);
                applyWindow.getDateResValueLabel().setText(Team.dateFormat.format(datesEditWindow.getDateRes().getTime()));

            }
            else applyWindow.setForeground(Color.black);
            applyWindow.getStatusValueLabel().setText("Резервист");
            break;
    }

    if(buttonSpecRifleman.isSelected()) {
        if(!member.getSpec1().equals(", автоматчик"))
        {
            memberTemp.setAssaultRifle();
            applyWindow.getSpecValueLabel().setForeground(Color.red);
        }
        else applyWindow.setForeground(Color.black);
        applyWindow.getSpecValueLabel().setText("Автоматчик");}

    else if(buttonSpecMachinegunner.isSelected()) {
        if(!member.getSpec1().equals(", пулеметчик"))
        {   
            memberTemp.setMachineGunner();
            applyWindow.getSpecValueLabel().setForeground(Color.red);
        }
        else applyWindow.setForeground(Color.black);
        applyWindow.getSpecValueLabel().setText("Пулеметчик");}

    else if(buttonSpecMiner.isSelected()) {
        if(!member.getSpec1().equals(", гренадер"))
        {
            memberTemp.setMiner();
            applyWindow.getSpecValueLabel().setForeground(Color.red);
        }
        else applyWindow.setForeground(Color.black);
        applyWindow.getSpecValueLabel().setText("Гренадер");}


    else if(buttonSpecSniper.isSelected()) {
        if(!member.getSpec1().equals(", снайпер"))
        {
            memberTemp.setSniper();
            applyWindow.getSpecValueLabel().setForeground(Color.red);
        }
        else applyWindow.setForeground(Color.black);
        applyWindow.getSpecValueLabel().setText("Снайпер");}

    else if(buttonSpecScout.isSelected()) {
        if(!member.getSpec1().equals(", разведчик"))
        {
            memberTemp.setScout();
            applyWindow.getSpecValueLabel().setForeground(Color.red);
        }
        else applyWindow.setForeground(Color.black);
        applyWindow.getSpecValueLabel().setText("Разведчик");}

    else if(buttonSpecStormTrooper.isSelected()) {
        if(!member.getSpec1().equals(", штурмовик"))
        {
            memberTemp.setStormTrooper();
            applyWindow.getSpecValueLabel().setForeground(Color.red);
        }
        applyWindow.setForeground(Color.black);
        applyWindow.getSpecValueLabel().setText("Штурмовик");}

    else {
        if(!member.getSpec1().equals(""))
        {
            memberTemp.setUsual();
            applyWindow.getSpecValueLabel().setForeground(Color.red);
        }    
        else applyWindow.setForeground(Color.black);
        applyWindow.getSpecValueLabel().setText("отсутствует");}

    ///////////////////////////////////////

    if(buttonPosCompetent.isSelected()){
        if(!member.getPosition().equals("Боец")){
            if(!memberTemp.isCompetent())memberTemp.setCompetent();
            else if(memberTemp.getId() == 1 ||memberTemp.getId() == 11 ||memberTemp.getId() == 22 || memberTemp.getId() == 31 ) memberTemp.bustCompetent();
            else memberTemp.setCompetent();
            applyWindow.getPositionValueLabel().setForeground(Color.red);}
        else applyWindow.setForeground(Color.black);
        applyWindow.getPositionValueLabel().setText("Боец");}

    else if(buttonPosCommander.isSelected()){
        if(!member.getPosition().equals("Командир"))
        {   
            memberSucker = Team.getCommander();
            if(memberSucker!=null)
                memberSuckerTemp.copy(memberSucker);
            memberTemp.setCommander();


        applyWindow.getPositionValueLabel().setForeground(Color.red);
        }
        else applyWindow.setForeground(Color.black);
        applyWindow.getPositionValueLabel().setText("Командир");}

    else if(buttonPosCraftsman.isSelected()){
        if(!member.getPosition().equals("Мастер-оружейник"))
        {
            memberTemp.setCraftsman();
            applyWindow.getPositionValueLabel().setForeground(Color.red);
        }
        else applyWindow.setForeground(Color.black);
        applyWindow.getPositionValueLabel().setText("Мастер-оружейник");}

    else if(buttonPosSergeant.isSelected()){
        if(!member.getPosition().equals("Старшина"))
        {

            memberSucker = Team.getSergeant();
            if(memberSucker!=null)
                memberSuckerTemp.copy(memberSucker);
            memberTemp.setSergeant();

            applyWindow.getPositionValueLabel().setForeground(Color.red);
        }
        else applyWindow.setForeground(Color.black);
        applyWindow.getPositionValueLabel().setText("Старшина");}

    else if(buttonPosSubCommander.isSelected()){
        if(!member.getPosition().equals("Зам. командира"))
        {
            memberTemp.setSubCommander();
            applyWindow.getPositionValueLabel().setForeground(Color.red);
        }
        else applyWindow.setForeground(Color.black);
        applyWindow.getPositionValueLabel().setText("Зам. командира");}


    else {
        if(!member.getPosition().equals("Кандидат") && statusListBox.getSelectedIndex() == 0)
        {
            applyWindow.getPositionValueLabel().setForeground(Color.red);
            applyWindow.getPositionValueLabel().setText("Кандидат");
        }
        else if(member.getPosition().equals("Кандидат") && statusListBox.getSelectedIndex() == 0){
            applyWindow.setForeground(Color.black);
            applyWindow.getPositionValueLabel().setText("Кандидат");}

        else if(!member.getPosition().equals("Резервист") && statusListBox.getSelectedIndex() == 2)
        {
            applyWindow.getPositionValueLabel().setForeground(Color.red);
            applyWindow.getPositionValueLabel().setText("Резервист");
            }
        else {
            applyWindow.setForeground(Color.black);
            applyWindow.getPositionValueLabel().setText("Резервист");}
        }

    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////
    if(JOptionPane.showConfirmDialog(this, applyWindow.getMainPanel(),
            "Подтверждение изменений", JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE) == 0)
    {    
        if(!member.equals(memberTemp))     {       
            member.copy(memberTemp);
            if(member.isCompetent())
                pageManager.editFighterPage(member);
            else pageManager.editCandidatePage(member);
            if(memberSucker!=null) pageManager.editFighterPage(memberSucker);
            memberSucker = null;
            memberTemp.getLog().add("=====================");
            Team.saveTeamToFile();
            client.uploadTeamDataFile();
            client.uploadTeamUserFile();
            if (buttonChangeCallSign.isSelected())
                client.deletePage(linkToDelete, wasCompetent);
        }
        file.delete();
        new TeamManagerWindow(client).setVisible(true);
        pageManager.editNavigationPage();
        pageManager.editMainPage();
        dispose(); 
    }
  
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        ///////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////
        
    }//GEN-LAST:event_buttonSaveActionPerformed

    private void buttonPosCraftsmanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPosCraftsmanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonPosCraftsmanActionPerformed

    private void buttonSpecMinerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSpecMinerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSpecMinerActionPerformed

    private void changeDateButtomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeDateButtomActionPerformed
        
 
        datesEditWindow.setVisible(true);
    }//GEN-LAST:event_changeDateButtomActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained

        
        
    }//GEN-LAST:event_formFocusGained

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered

        if(datesEditWindow.isChanges()){
            dateLabel.setForeground(Color.red);
            dateLabel.setText("В даты будут внесены изменения");
        }
        else{
            dateLabel.setForeground(Color.black);
        if(member.isCompetent())
        dateLabel.setText("Дата вступления в бойцы: " + 
            Team.dateFormat.format(this.member.getDateCompetent().getTime()));
        else
            dateLabel.setText("Дата вступления в кандидаты: " + 
                Team.dateFormat.format(this.member.getDateCandidate().getTime()));
        if(member.isReserved())
            dateLabel.setText("Дата зачисления в резервисты: " + 
                Team.dateFormat.format(this.member.getDateReserve().getTime()));
        }
    }//GEN-LAST:event_formMouseEntered

    private void changeSignTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_changeSignTextFieldFocusGained
        changeSignTextField.selectAll();
    }//GEN-LAST:event_changeSignTextFieldFocusGained

    private void changeNameTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_changeNameTextFieldFocusGained
      changeNameTextField.selectAll();
    }//GEN-LAST:event_changeNameTextFieldFocusGained

    private void buttonOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOpenActionPerformed
       String s = "www.sk-bron.ru/Fighters/" + member.getLink() + ".html";
        try {
            Desktop.getDesktop().browse(new URI(s));
        } catch (URISyntaxException ex) {
            if(options.isCatchPrint()) System.out.println(ex.getMessage());
        } catch (IOException ex) {
            if(options.isCatchPrint()) System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_buttonOpenActionPerformed

    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
       JOptionPane.showMessageDialog(this, new MemberHelpPanel(), "Подсказка", JOptionPane.DEFAULT_OPTION);
    }//GEN-LAST:event_buttonHelpActionPerformed

    private void buttonChangeCallSignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChangeCallSignActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonChangeCallSignActionPerformed

    private void avatarImageLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_avatarImageLabelMouseClicked
        UIManager.put("OptionPane.yesButtonText"       , "Да");
        UIManager.put("OptionPane.noButtonText"        , "Нет");
        if(evt.getClickCount() == 2 && evt.getButton() == 1 && 
                JOptionPane.showConfirmDialog(this, "Закрыть это окно и перейти в редактор фотографий профиля?\n"
                        + "Внесенные в этом разделе изменения не будут сохранены!!!", "Редактор фото", JOptionPane.YES_NO_OPTION) == 0){
            new ImageEditorWindow(member).setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_avatarImageLabelMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        SystemOptions.windowClosing();
    }//GEN-LAST:event_formWindowClosing
    
    private boolean checkName(){
        String s = changeNameTextField.getText();
        if(s.equals(member.getName())){
            errorNameLabel.setForeground(Color.GREEN);
            errorNameLabel.setText("Актуальное имя бойца ");
            return false;
        }
        if (s.length()<2){
            errorNameLabel.setForeground(Color.RED);
            errorNameLabel.setText("Слишком короткое имя ");
            return false;
        }
        char[] c = s.toCharArray();
        c[0] = Character.toUpperCase(c[0]);
        for (int i = 1; i < c.length; i++) 
            c[i] = Character.toLowerCase(c[i]);
            
        s = "";
        for (int i = 0; i < c.length; i++) {
            boolean nameOk  = false;
            for (char d : Team.RUSSIAN_LETTERS) {
                if(d == c[i]) nameOk = true;
            }
            if(!nameOk) {
                errorNameLabel.setForeground(Color.red);
                errorNameLabel.setText("Недопустимые символы в имени ");
                return  false;
            }
            if (c[i] == ' ') {
                errorNameLabel.setForeground(Color.RED);
                errorNameLabel.setText("Не должно содержать пробелов ");
                return false;
            }
            s+=c[i];
            }
        changeNameTextField.setText(s);
        errorNameLabel.setText("");
        return true;
    }
    private boolean checkCallSign(){
        String s = changeSignTextField.getText();
        if(s.equals(member.getCallSign())){
            errorSignLabel.setForeground(Color.GREEN);
            errorSignLabel.setText("Актуальный позывной бойца ");
            return false;
        }
         
        if (s.length()<2){
            errorSignLabel.setForeground(Color.RED);
            errorSignLabel.setText("Слишком короткий позывной ");
            return false;
        }
            char[] c = s.toCharArray();
            c[0] = Character.toUpperCase(c[0]);
            for (int i = 1; i < c.length; i++) {
                c[i] = Character.toLowerCase(c[i]);
            }
            s = "";
            for (int i = 0; i < c.length; i++) {
                 boolean signOk  = false;
            for (char d : Team.RUSSIAN_LETTERS) {
                if(d == c[i]) signOk = true;
            }
            if(!signOk) {
                errorSignLabel.setForeground(Color.red);
                errorSignLabel.setText("Недопустимые символы в позывном ");
                return  false;
            }
                if (c[i] == ' ') {
                    errorSignLabel.setForeground(Color.RED);
                    errorSignLabel.setText("Не должен содержать пробелов ");
                    return false;
                }
                s+=c[i];
            }

        changeSignTextField.setText(s);
        for (Member m : Team.getTeamMembers()) 
            if(s.equals(m.getCallSign())) {
                errorSignLabel.setForeground(Color.RED);
                errorSignLabel.setText("Позывной уже используется ");
                return false;
            }
        errorSignLabel.setText("");
        return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel avatarImageLabel;
    private javax.swing.JButton buttonCancel;
    private javax.swing.JToggleButton buttonChangeCallSign;
    private javax.swing.JToggleButton buttonChangeName;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonOpen;
    private javax.swing.JRadioButton buttonPosCommander;
    private javax.swing.JRadioButton buttonPosCompetent;
    private javax.swing.JRadioButton buttonPosCraftsman;
    private javax.swing.JRadioButton buttonPosSergeant;
    private javax.swing.JRadioButton buttonPosSubCommander;
    private javax.swing.JButton buttonSave;
    protected javax.swing.JRadioButton buttonSpecMachinegunner;
    protected javax.swing.JRadioButton buttonSpecMiner;
    protected javax.swing.JRadioButton buttonSpecRifleman;
    protected javax.swing.JRadioButton buttonSpecScout;
    private javax.swing.JRadioButton buttonSpecSniper;
    protected javax.swing.JRadioButton buttonSpecStormTrooper;
    private javax.swing.JButton changeDateButtom;
    private javax.swing.JTextField changeNameTextField;
    private javax.swing.JTextField changeSignTextField;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel errorNameLabel;
    private javax.swing.JLabel errorSignLabel;
    private javax.swing.JLabel positionLabel;
    private javax.swing.JLabel specialisationLabel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JComboBox<String> statusListBox;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
