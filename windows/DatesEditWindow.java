
package javaapplication58.windows;

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication58.Member;
import javaapplication58.SystemOptions;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;

public class DatesEditWindow extends javax.swing.JDialog {
    
    private Image iconOk;
    private final Member m;
    Calendar logDate = Calendar.getInstance();
    private SimpleDateFormat logDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    private String nullForDate;
    private final char [] nums ={'1','2','3','4','5','6','7','8','9','0', KeyEvent.VK_BACKSPACE, KeyEvent.VK_DELETE};
    private Calendar dateRes = Calendar.getInstance();
    private Calendar dateCan = Calendar.getInstance();
    private Calendar dateCom = Calendar.getInstance();
    private Calendar foundation = Calendar.getInstance();
    private boolean changes;
    private final SystemOptions options =SystemOptions.getInstance();

    public DatesEditWindow(java.awt.Frame parent, Member m, boolean modal) {
        super(parent, modal);
        if(SystemOptions.getInstance().isDebugPrint())System.out.println("Предварительно создано окно редактирования дат");
        this.changes = false;
        try {
            iconOk   = ImageIO.read(new FileImageInputStream(new File("images/sys/ok.png")));
            
        } catch (IOException ex) {
            if(SystemOptions.getInstance().isCatchPrint()) System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(this, "Ошибка при считывании иконок\n" + ex.getMessage(), "ошибка", JOptionPane.ERROR_MESSAGE);
        }
        initComponents();
        okButton.setEnabled(false);
        this.m  = m;
        
        if(m.isReserved())  dateRes.setTime(m.getDateReserve().getTime());
        if(m.isCompetent()) dateCom.setTime(m.getDateCompetent().getTime());
        dateCan.setTime(m.getDateCandidate().getTime());
        
        foundation.set(2012, 6, 7);
            
        errorLabel1.setForeground(Color.green);
        errorLabel1.setText("Даты актуальны ");
        competentLabel.setForeground(Color.GRAY);
        competentDay.setVisible(false);
        competentMonth.setVisible(false);
        competentYear.setVisible(false);
        comDot1.setVisible(false);
        comDot2.setVisible(false);

        reserveLabel.setForeground(Color.GRAY);
        reserveDay.setVisible(false);
        reserveMonth.setVisible(false);
        reserveYear.setVisible(false);
        resDot1.setVisible(false);
        resDot2.setVisible(false);
    
    if(m.isCompetent()){
        comDot1.setVisible(true);
        comDot2.setVisible(true);
        competentLabel.setForeground(Color.BLACK);
        competentDay.setVisible(true);
        
        if(m.getDateCompetent().get(Calendar.DATE) < 10) nullForDate = "0"; else nullForDate = "";
        competentDay.setText(nullForDate + Integer.toString(m.getDateCompetent().get(Calendar.DATE)));
        competentMonth.setVisible(true);
        
        if(m.getDateCompetent().get(Calendar.MONTH) < 9) nullForDate = "0"; else nullForDate = "";
        competentMonth.setText(nullForDate + (Integer.toString(m.getDateCompetent().get(Calendar.MONTH) + 1)));
        competentYear.setVisible(true);
        competentYear.setText(Integer.toString(m.getDateCompetent().get(Calendar.YEAR)));
    }

    if(m.isReserved()){
        resDot1.setVisible(true);
        resDot2.setVisible(true);
        reserveLabel.setForeground(Color.BLACK);
        reserveDay.setVisible(true);
        if(m.getDateReserve().get(Calendar.DATE) < 10) nullForDate = "0"; else nullForDate = "";
        reserveDay.setText(nullForDate + Integer.toString(m.getDateReserve().get(Calendar.DATE)));
        reserveMonth.setVisible(true);
        if(m.getDateReserve().get(Calendar.MONTH) < 9) nullForDate = "0"; else nullForDate = "";
        reserveMonth.setText(nullForDate + (Integer.toString(m.getDateReserve().get(Calendar.MONTH) + 1)));
        reserveYear.setVisible(true);
        reserveYear.setText(Integer.toString(m.getDateReserve().get(Calendar.YEAR)));
    }
       
    }

    public boolean   isChanges() {     return changes;   }
    public Calendar getDateRes() {     return dateRes;   }
    public Calendar getDateCan() {     return dateCan;   }
    public Calendar getDateCom() {     return dateCom;   }
    
    
    private boolean checkDateInputs(String sDay, String sMonth, String sYear){
      
        if(sDay.length() < 1){
            errorLabel1.setForeground(Color.red);
            errorLabel1.setText("Неверная дата ");
        return false;}
          
        if(sMonth.length() < 1){
            errorLabel1.setForeground(Color.red);
            errorLabel1.setText("Неверная дата ");
            return false;}
        
         if(sYear.length() < 1){
            errorLabel1.setForeground(Color.red);
            errorLabel1.setText("Неверная дата ");
            return false;}
       
       int day = Integer.parseInt(sDay);
       int month = Integer.parseInt(sMonth);
       
        if(day < 1 || day > 31) {
            errorLabel1.setForeground(Color.red);
            errorLabel1.setText("Неверная дата ");
            return false;}
        
         if(month < 1 || month > 12) {
            errorLabel1.setForeground(Color.red);
            errorLabel1.setText("Неверная дата ");
            return false;}
        
        switch (Integer.parseInt(sMonth)){
            case 0: case 2: case 4: case 6: case 7: case 9: case 11:
                if (Integer.parseInt(candidateDay.getText()) > 31)
                {
                    errorLabel1.setForeground(Color.red);
                    errorLabel1.setText("Неверная дата ");
                    return false;
                }
                
                break;
        
            case 3: case 5: case 8: case 10:
                if (Integer.parseInt(candidateDay.getText()) > 30)
                {
                    errorLabel1.setForeground(Color.red);
                    errorLabel1.setText("Неверная дата ");
                    return false;
                }
                
                break;
            
            case 1:
                if ( isLeap(Integer.parseInt(sYear)) && Integer.parseInt(sDay) > 29)
                {
                    errorLabel1.setForeground(Color.red);
                    errorLabel1.setText("Неверная дата ");
                    return false;
                }
                if (!isLeap(Integer.parseInt(sYear)) && Integer.parseInt(sDay) > 28)
                {
                    errorLabel1.setForeground(Color.red);
                    errorLabel1.setText("Неверная дата ");
                    return false;
                }
                break;
        }
        return true;
    }
    
    private boolean checkTimePeriod(){
        Calendar now = Calendar.getInstance();

        if(dateCan.after(now)) {
            errorLabel1.setForeground(Color.red);
            errorLabel1.setText("Дата(кандидат) из будущего ");
            return false;        
        }

        if(foundation.after(dateCan)){
            errorLabel1.setForeground(Color.red);
            errorLabel1.setText("Дата(кандидат) до основания команды ");
            return false;        
        }

        if(dateCom.after(now)) {
            errorLabel1.setForeground(Color.red);
            errorLabel1.setText("Дата(боец) из будущего ");
            return false;        
        }

        if(foundation.after(dateCom)){
            errorLabel1.setForeground(Color.red);
            errorLabel1.setText("Дата(боец) до основания команды ");
            return false;        
        }
        
        if(m.isCompetent() && dateCom.before(dateCan)){
            errorLabel1.setForeground(Color.red);
            errorLabel1.setText("Дата(боец) до принятия в кандидаты ");
            return false;        
        }

        if(dateRes.after(now)) {
            errorLabel1.setForeground(Color.red);
            errorLabel1.setText("Дата(резервист) из будущего ");
            return false;        
        }
        
        if(m.isReserved() && dateRes.before(dateCan)){
            errorLabel1.setForeground(Color.red);
            errorLabel1.setText("Дата(резервист) до принятия в кандидаты ");
            return false;        
        }

        if(dateRes.before(foundation)){
            errorLabel1.setForeground(Color.red);
            errorLabel1.setText("Дата(резервист) до основания команды ");
            return false;        
        }

        return true;
    }
    
    private boolean checkAll(){
        if(!checkDateInputs(candidateDay.getText(), candidateMonth.getText(), candidateYear.getText())) return false;
        dateCan.clear();
        dateCan.set(Integer.parseInt(candidateYear.getText()), (Integer.parseInt(candidateMonth.getText()) - 1), Integer.parseInt(candidateDay.getText()));

        if(!checkDateInputs(competentDay.getText(), competentMonth.getText(), competentYear.getText())) return false;
        dateCom.clear();
        dateCom.set(Integer.parseInt(competentYear.getText()), (Integer.parseInt(competentMonth.getText()) - 1), Integer.parseInt(competentDay.getText()));
        
        if(!checkDateInputs(reserveDay.getText(), reserveMonth.getText(), reserveYear.getText())) return false;
        dateRes.clear();
        dateRes.set(Integer.parseInt(reserveYear.getText()), (Integer.parseInt(reserveMonth.getText()) - 1), Integer.parseInt(reserveDay.getText()));
    
        boolean allIsFine = checkTimePeriod();
        if(allIsFine) {
            changes = !checkDatesEquals();
            errorLabel1.setForeground(Color.GREEN);
            errorLabel1.setText("даты корректны ");}
        return allIsFine;
    }
    
    private boolean checkDatesEquals(){
        String s = candidateDay.getText();
        s = s.startsWith("0") ? s.substring(1) : s;
        if (!s.equals(Integer.toString(m.getDateCandidate().get(Calendar.DATE)))) return false;
        
        s = candidateMonth.getText();
        s = s.startsWith("0") ? s.substring(1) : s;
        if (!s.equals(Integer.toString(m.getDateCandidate().get(Calendar.MONTH)))) return false;
        
        if (!candidateYear.getText().equals(Integer.toString(m.getDateCandidate().get(Calendar.YEAR)))) return false;
        ////////////////////////////////////////////////////////////////////////
        if(m.isCompetent()){
            s = competentDay.getText();
            s = s.startsWith("0") ? s.substring(1) : s;
            if (!s.equals(Integer.toString(m.getDateCompetent().get(Calendar.DATE)))) return false;            

            s = competentMonth.getText();
            s = s.startsWith("0") ? s.substring(1) : s;
            if (!s.equals(Integer.toString(m.getDateCompetent().get(Calendar.MONTH)))) return false;
        
            if (!competentYear.getText().equals(Integer.toString(m.getDateCompetent().get(Calendar.YEAR)))) return false;
        }
        ////////////////////////////////////////////////////////////////////////
        if(m.isReserved()){
            s = reserveDay.getText();
            s = s.startsWith("0") ? s.substring(1) : s;
            if (!s.equals(Integer.toString(m.getDateReserve().get(Calendar.DATE)))) return false;            

            s = reserveMonth.getText();
            s = s.startsWith("0") ? s.substring(1) : s;
            if (!s.equals(Integer.toString(m.getDateReserve().get(Calendar.MONTH)))) return false;
        
            if (!reserveYear.getText().equals(Integer.toString(m.getDateReserve().get(Calendar.YEAR)))) return false;
        }        
        return true;
    }
    
    private boolean isLeap(int y){
        if(y%4 !=0) return false;
        else if (y%100 == 0 && y%400 != 0) return false;
        else return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headLabel = new javax.swing.JLabel();
        candidateLabel = new javax.swing.JLabel();
        competentLabel = new javax.swing.JLabel();
        reserveLabel = new javax.swing.JLabel();
        candidateDay = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        candidateMonth = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        candidateYear = new javax.swing.JTextField();
        competentDay = new javax.swing.JTextField();
        reserveDay = new javax.swing.JTextField();
        comDot1 = new javax.swing.JLabel();
        resDot1 = new javax.swing.JLabel();
        competentMonth = new javax.swing.JTextField();
        reserveMonth = new javax.swing.JTextField();
        comDot2 = new javax.swing.JLabel();
        resDot2 = new javax.swing.JLabel();
        competentYear = new javax.swing.JTextField();
        reserveYear = new javax.swing.JTextField();
        errorLabel1 = new javax.swing.JLabel();
        cancelButton1 = new javax.swing.JButton();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Редактирование даты");
        setIconImage(options.ICON_MAIN);
        setMinimumSize(new java.awt.Dimension(320, 240));
        setModal(true);
        setResizable(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        headLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        headLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headLabel.setText("Редактирование даты");
        getContentPane().add(headLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 282, -1));

        candidateLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        candidateLabel.setText("Принят в кандидаты ");
        getContentPane().add(candidateLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 46, -1, -1));

        competentLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        competentLabel.setText("Переведен в бойцы");
        getContentPane().add(competentLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 86, 138, -1));

        reserveLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        reserveLabel.setText("Переведен в резерв");
        getContentPane().add(reserveLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 138, -1));

        candidateDay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        candidateDay.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        candidateDay.setText("03");
        candidateDay.setSelectionColor(new java.awt.Color(94, 93, 1));
        candidateDay.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                candidateDayFocusGained(evt);
            }
        });
        candidateDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                candidateDayActionPerformed(evt);
            }
        });
        candidateDay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                candidateDayKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                candidateDayKeyTyped(evt);
            }
        });
        getContentPane().add(candidateDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 46, 30, 25));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText(".");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 46, -1, 23));

        candidateMonth.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        candidateMonth.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        candidateMonth.setText("03");
        candidateMonth.setSelectionColor(new java.awt.Color(94, 93, 1));
        candidateMonth.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                candidateMonthFocusGained(evt);
            }
        });
        candidateMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                candidateMonthActionPerformed(evt);
            }
        });
        candidateMonth.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                candidateMonthKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                candidateMonthKeyTyped(evt);
            }
        });
        getContentPane().add(candidateMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 46, 30, 25));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText(".");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(234, 46, -1, 23));

        candidateYear.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        candidateYear.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        candidateYear.setText("1987");
        candidateYear.setMaximumSize(new java.awt.Dimension(6, 23));
        candidateYear.setPreferredSize(new java.awt.Dimension(6, 23));
        candidateYear.setSelectionColor(new java.awt.Color(94, 93, 1));
        candidateYear.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                candidateYearFocusGained(evt);
            }
        });
        candidateYear.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                candidateYearKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                candidateYearKeyTyped(evt);
            }
        });
        getContentPane().add(candidateYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 46, 50, 25));

        competentDay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        competentDay.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        competentDay.setText("03");
        competentDay.setSelectionColor(new java.awt.Color(94, 93, 1));
        competentDay.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                competentDayFocusGained(evt);
            }
        });
        competentDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                competentDayActionPerformed(evt);
            }
        });
        competentDay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                competentDayKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                competentDayKeyTyped(evt);
            }
        });
        getContentPane().add(competentDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 83, 30, 25));

        reserveDay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        reserveDay.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        reserveDay.setText("03");
        reserveDay.setSelectionColor(new java.awt.Color(94, 93, 1));
        reserveDay.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                reserveDayFocusGained(evt);
            }
        });
        reserveDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reserveDayActionPerformed(evt);
            }
        });
        reserveDay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                reserveDayKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                reserveDayKeyTyped(evt);
            }
        });
        getContentPane().add(reserveDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 117, 30, 25));

        comDot1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comDot1.setText(".");
        comDot1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(comDot1, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 80, -1, 23));

        resDot1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        resDot1.setText(".");
        getContentPane().add(resDot1, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 120, -1, -1));

        competentMonth.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        competentMonth.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        competentMonth.setText("03");
        competentMonth.setSelectionColor(new java.awt.Color(94, 93, 1));
        competentMonth.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                competentMonthFocusGained(evt);
            }
        });
        competentMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                competentMonthActionPerformed(evt);
            }
        });
        competentMonth.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                competentMonthKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                competentMonthKeyTyped(evt);
            }
        });
        getContentPane().add(competentMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 83, 30, 25));

        reserveMonth.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        reserveMonth.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        reserveMonth.setText("03");
        reserveMonth.setSelectionColor(new java.awt.Color(94, 93, 1));
        reserveMonth.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                reserveMonthFocusGained(evt);
            }
        });
        reserveMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reserveMonthActionPerformed(evt);
            }
        });
        reserveMonth.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                reserveMonthKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                reserveMonthKeyTyped(evt);
            }
        });
        getContentPane().add(reserveMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 117, 30, 25));

        comDot2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comDot2.setText(".");
        comDot2.setToolTipText("");
        comDot2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(comDot2, new org.netbeans.lib.awtextra.AbsoluteConstraints(234, 86, -1, -1));

        resDot2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        resDot2.setText(".");
        getContentPane().add(resDot2, new org.netbeans.lib.awtextra.AbsoluteConstraints(234, 120, -1, -1));

        competentYear.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        competentYear.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        competentYear.setText("1987");
        competentYear.setMaximumSize(new java.awt.Dimension(6, 23));
        competentYear.setPreferredSize(new java.awt.Dimension(6, 23));
        competentYear.setSelectionColor(new java.awt.Color(94, 93, 1));
        competentYear.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                competentYearFocusGained(evt);
            }
        });
        competentYear.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                competentYearKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                competentYearKeyTyped(evt);
            }
        });
        getContentPane().add(competentYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 83, 50, 25));

        reserveYear.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        reserveYear.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        reserveYear.setText("1987");
        reserveYear.setPreferredSize(new java.awt.Dimension(6, 23));
        reserveYear.setSelectionColor(new java.awt.Color(94, 93, 1));
        reserveYear.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                reserveYearFocusGained(evt);
            }
        });
        reserveYear.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                reserveYearKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                reserveYearKeyTyped(evt);
            }
        });
        getContentPane().add(reserveYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 117, 50, 25));

        errorLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        errorLabel1.setForeground(new java.awt.Color(255, 0, 0));
        errorLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        errorLabel1.setText("Даты актуальны");
        errorLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(errorLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 151, 282, -1));

        cancelButton1.setText("Отмена");
        cancelButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(cancelButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 87, -1));

        okButton.setText("Сохранить");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        getContentPane().add(okButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void candidateDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_candidateDayActionPerformed

    }//GEN-LAST:event_candidateDayActionPerformed

    private void candidateDayKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_candidateDayKeyReleased
        okButton.setEnabled(checkAll());
        if(checkDatesEquals()){
            errorLabel1.setForeground(Color.green);
            errorLabel1.setText("даты актуальны ");
            okButton.setEnabled(false);
        }        
    }//GEN-LAST:event_candidateDayKeyReleased

    private void candidateDayKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_candidateDayKeyTyped
        if(candidateDay.getText()!=null){
            boolean charIsOk = false;
            for (char c : nums)
            if(evt.getKeyChar() == c) charIsOk = true;
            if(!charIsOk) {
                Toolkit.getDefaultToolkit().beep();
                evt.consume();
            }}
    }//GEN-LAST:event_candidateDayKeyTyped

    private void candidateMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_candidateMonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_candidateMonthActionPerformed

    private void candidateMonthKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_candidateMonthKeyReleased
        okButton.setEnabled(checkAll());
        if(checkDatesEquals()){
            errorLabel1.setForeground(Color.green);
            errorLabel1.setText("даты актуальны ");
            okButton.setEnabled(false);
        }        
    }//GEN-LAST:event_candidateMonthKeyReleased

    private void candidateMonthKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_candidateMonthKeyTyped
        boolean charIsOk = false;
        for (char c : nums)
            if(evt.getKeyChar() == c) charIsOk = true;
        if(!charIsOk) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_candidateMonthKeyTyped

    private void candidateYearKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_candidateYearKeyReleased
        okButton.setEnabled(checkAll());
        if(checkDatesEquals()){
            errorLabel1.setForeground(Color.green);
            errorLabel1.setText("даты актуальны ");
            okButton.setEnabled(false);
        }
    }//GEN-LAST:event_candidateYearKeyReleased

    private void candidateYearKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_candidateYearKeyTyped
        boolean charIsOk = false;
        for (char c : nums) if(evt.getKeyChar() == c) charIsOk = true;
        if(!charIsOk) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_candidateYearKeyTyped

    private void competentDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_competentDayActionPerformed

    }//GEN-LAST:event_competentDayActionPerformed

    private void competentDayKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_competentDayKeyReleased
        okButton.setEnabled(checkAll());
        if(checkDatesEquals()){
            errorLabel1.setText("даты актуальны ");
            okButton.setEnabled(false);
        }
    }//GEN-LAST:event_competentDayKeyReleased

    private void competentDayKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_competentDayKeyTyped
        boolean charIsOk = false;
        for (char c : nums)
            if(evt.getKeyChar() == c) charIsOk = true;
        if(!charIsOk) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_competentDayKeyTyped

    private void reserveDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reserveDayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reserveDayActionPerformed

    private void reserveDayKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_reserveDayKeyReleased
        okButton.setEnabled(checkAll());
        if(checkDatesEquals()){
            errorLabel1.setText("даты актуальны ");
            okButton.setEnabled(false);
        }
    }//GEN-LAST:event_reserveDayKeyReleased

    private void reserveDayKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_reserveDayKeyTyped
        boolean charIsOk = false;
        for (char c : nums)
        if(evt.getKeyChar() == c) charIsOk = true;
        if(!charIsOk) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_reserveDayKeyTyped

    private void competentMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_competentMonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_competentMonthActionPerformed

    private void competentMonthKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_competentMonthKeyReleased
        okButton.setEnabled(checkAll());
        if(checkDatesEquals()){
            errorLabel1.setText("даты актуальны ");
            okButton.setEnabled(false);
        }
    }//GEN-LAST:event_competentMonthKeyReleased

    private void competentMonthKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_competentMonthKeyTyped
        boolean charIsOk = false;
        for (char c : nums)
        if(evt.getKeyChar() == c) charIsOk = true;
        if(!charIsOk) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_competentMonthKeyTyped

    private void reserveMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reserveMonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reserveMonthActionPerformed

    private void reserveMonthKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_reserveMonthKeyReleased
        okButton.setEnabled(checkAll());
        if(checkDatesEquals()){
            errorLabel1.setText("даты актуальны ");
            okButton.setEnabled(false);
        }
    }//GEN-LAST:event_reserveMonthKeyReleased

    private void reserveMonthKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_reserveMonthKeyTyped
        boolean charIsOk = false;
        for (char c : nums)
        if(evt.getKeyChar() == c) charIsOk = true;
        if(!charIsOk) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_reserveMonthKeyTyped

    private void competentYearKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_competentYearKeyReleased
        okButton.setEnabled(checkAll());
        if(checkDatesEquals()){
            errorLabel1.setText("даты актуальны ");
            okButton.setEnabled(false);
        }
    }//GEN-LAST:event_competentYearKeyReleased

    private void competentYearKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_competentYearKeyTyped
        boolean charIsOk = false;
        for (char c : nums)
        if(evt.getKeyChar() == c) charIsOk = true;
        if(!charIsOk) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_competentYearKeyTyped

    private void reserveYearKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_reserveYearKeyReleased
         okButton.setEnabled(checkAll());
         if(checkDatesEquals()){
            errorLabel1.setText("даты актуальны ");
            okButton.setEnabled(false);
        }
    }//GEN-LAST:event_reserveYearKeyReleased

    private void reserveYearKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_reserveYearKeyTyped
        boolean charIsOk = false;
        for (char c : nums)
            if(evt.getKeyChar() == c) charIsOk = true;
        if(!charIsOk) {
            Toolkit.getDefaultToolkit().beep();
        evt.consume();
        }
    }//GEN-LAST:event_reserveYearKeyTyped

    private void cancelButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButton1ActionPerformed
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        changes = false;
        dateRes.setTime(m.getDateReserve().getTime());
        dateCom.setTime(m.getDateCompetent().getTime());
        dateCan.setTime(m.getDateCandidate().getTime());
        
        if(m.getDateCandidate().get(Calendar.DATE) < 10) nullForDate = "0"; else nullForDate = "";
        candidateDay.setText(nullForDate + Integer.toString(m.getDateCandidate().get(Calendar.DATE)));

        if(m.getDateCandidate().get(Calendar.MONTH) < 9) nullForDate = "0"; else nullForDate = "";
        candidateMonth.setText(nullForDate + (Integer.toString(m.getDateCandidate().get(Calendar.MONTH) + 1)));

        candidateYear.setText(Integer.toString(m.getDateCandidate().get(Calendar.YEAR)));
        
        if(m.isCompetent()){
            if(m.getDateReserve().get(Calendar.DATE) < 10) nullForDate = "0"; else nullForDate = "";
            reserveDay.setText(nullForDate + Integer.toString(m.getDateReserve().get(Calendar.DATE)));

            if(m.getDateReserve().get(Calendar.MONTH) < 9) nullForDate = "0"; else nullForDate = "";
            reserveMonth.setText(nullForDate + (Integer.toString(m.getDateReserve().get(Calendar.MONTH) + 1)));

            reserveYear.setText(Integer.toString(m.getDateReserve().get(Calendar.YEAR)));
        }
        
        if(m.isReserved()){
            if(m.getDateReserve().get(Calendar.DATE) < 10) nullForDate = "0"; else nullForDate = "";
            reserveDay.setText(nullForDate + Integer.toString(m.getDateReserve().get(Calendar.DATE)));

            if(m.getDateReserve().get(Calendar.MONTH) < 9) nullForDate = "0"; else nullForDate = "";
            reserveMonth.setText(nullForDate + (Integer.toString(m.getDateReserve().get(Calendar.MONTH) + 1)));

            reserveYear.setText(Integer.toString(m.getDateReserve().get(Calendar.YEAR)));
        }
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        setVisible(false);
        
    }//GEN-LAST:event_cancelButton1ActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
       
        setVisible(false);
                
    }//GEN-LAST:event_okButtonActionPerformed

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered

    }//GEN-LAST:event_formMouseEntered

    private void candidateDayFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_candidateDayFocusGained
         ////////////
    if(m.getDateCandidate().get(Calendar.DATE) < 10) nullForDate = "0"; else nullForDate = "";
    candidateDay.setText(nullForDate + Integer.toString(m.getDateCandidate().get(Calendar.DATE)));
    
    if(m.getDateCandidate().get(Calendar.MONTH) < 9) nullForDate = "0"; else nullForDate = "";
    candidateMonth.setText(nullForDate + (Integer.toString(m.getDateCandidate().get(Calendar.MONTH) + 1)));
        
    candidateYear.setText(Integer.toString(m.getDateCandidate().get(Calendar.YEAR)));
    ////////////
    if(m.getDateCompetent().get(Calendar.DATE) < 10) nullForDate = "0"; else nullForDate = "";
    competentDay.setText(nullForDate + Integer.toString(m.getDateCompetent().get(Calendar.DATE)));
    
    if(m.getDateCompetent().get(Calendar.MONTH) < 9) nullForDate = "0"; else nullForDate = "";
    competentMonth.setText(nullForDate + (Integer.toString(m.getDateCompetent().get(Calendar.MONTH) + 1)));
         
    competentYear.setText(Integer.toString(m.getDateCompetent().get(Calendar.YEAR)));
    ////////////
    if(m.getDateReserve().get(Calendar.DATE) < 10) nullForDate = "0"; else nullForDate = "";
    reserveDay.setText(nullForDate + Integer.toString(m.getDateReserve().get(Calendar.DATE)));
    
    if(m.getDateReserve().get(Calendar.MONTH) < 9) nullForDate = "0"; else nullForDate = "";
    reserveMonth.setText(nullForDate + (Integer.toString(m.getDateReserve().get(Calendar.MONTH) + 1)));
        
    reserveYear.setText(Integer.toString(m.getDateReserve().get(Calendar.YEAR)));
    ////////////
        candidateDay.selectAll();
    }//GEN-LAST:event_candidateDayFocusGained

    private void candidateMonthFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_candidateMonthFocusGained
        candidateMonth.selectAll();
    }//GEN-LAST:event_candidateMonthFocusGained

    private void candidateYearFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_candidateYearFocusGained
        candidateYear.selectAll();
    }//GEN-LAST:event_candidateYearFocusGained

    private void competentDayFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_competentDayFocusGained
        competentDay.selectAll();
    }//GEN-LAST:event_competentDayFocusGained

    private void competentMonthFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_competentMonthFocusGained
        competentMonth.selectAll();
    }//GEN-LAST:event_competentMonthFocusGained

    private void competentYearFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_competentYearFocusGained
       competentYear.selectAll();
    }//GEN-LAST:event_competentYearFocusGained

    private void reserveDayFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_reserveDayFocusGained
        reserveDay.selectAll();
    }//GEN-LAST:event_reserveDayFocusGained

    private void reserveMonthFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_reserveMonthFocusGained
        reserveMonth.selectAll();
    }//GEN-LAST:event_reserveMonthFocusGained

    private void reserveYearFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_reserveYearFocusGained
      reserveYear.selectAll();
    }//GEN-LAST:event_reserveYearFocusGained


       
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton1;
    private javax.swing.JTextField candidateDay;
    private javax.swing.JLabel candidateLabel;
    private javax.swing.JTextField candidateMonth;
    private javax.swing.JTextField candidateYear;
    private javax.swing.JLabel comDot1;
    private javax.swing.JLabel comDot2;
    private javax.swing.JTextField competentDay;
    private javax.swing.JLabel competentLabel;
    private javax.swing.JTextField competentMonth;
    private javax.swing.JTextField competentYear;
    private javax.swing.JLabel errorLabel1;
    private javax.swing.JLabel headLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel resDot1;
    private javax.swing.JLabel resDot2;
    private javax.swing.JTextField reserveDay;
    private javax.swing.JLabel reserveLabel;
    private javax.swing.JTextField reserveMonth;
    private javax.swing.JTextField reserveYear;
    // End of variables declaration//GEN-END:variables
}
