package questions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static questions.Reminder.timer;
import static questions.Reminder.timerFlag;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Pratik
 */
public class Client1 extends javax.swing.JFrame {
    public static javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel8;
    public static int score = 0;
    public Question str1 = new Question();
    public javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
    public String choiceQ = "1";
    public int choiceL = 0;
    public File ip;
    public FileInputStream fis;
    public ObjectInputStream ois;
    public int answer;
    public int quesNo = 0, cur = 1;
    public int a = 0, i;
    public int flag1, colorFlag2, colorFlag3, colorFlag6, colorFlag7;
    public JButton buttons[] = new JButton[40];
    Random r = new Random();
    boolean flag[] = new boolean[50];
    Color c = new Color(52, 152, 219);
    Color d = new Color(240, 240, 240);
    ArrayList<Question> questions = new ArrayList<>();
    int num[] = new int[40];
    int temprand;
    int flag4 = 1;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JTextArea jTextArea7;
    /**
     * Creates new form client1
     */
    public Client1() {
        initComponents();
        init2();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Client1().setVisible(true);
            }
        });
    }

    public void init2() {

        ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof JButton) {
                    String text = ((JButton) e.getSource()).getText();
                    cur = Integer.parseInt(text);

                    jTextArea6.setEnabled(true);
                    jTextArea3.setEnabled(true);
                    jTextArea7.setEnabled(true);
                    jTextArea2.setEnabled(true);

                    jTextArea6.setBackground(d);
                    jTextArea2.setBackground(d);
                    jTextArea3.setBackground(d);
                    jTextArea7.setBackground(d);
                    a = num[cur - 1];

                    colorFlag6 = colorFlag2 = colorFlag7 = colorFlag3 = 0;

                    Question tempQuestion = questions.get(a);
                    jTextArea1.setText("Question no #" + (cur) + "\n" + tempQuestion.ques);
                    jTextArea1.setCaretPosition(0);
                    jTextArea6.setText(tempQuestion.opt[0]);
                    jTextArea6.setCaretPosition(0);
                    jTextArea2.setText(tempQuestion.opt[1]);
                    jTextArea2.setCaretPosition(0);
                    jTextArea7.setText(tempQuestion.opt[2]);
                    jTextArea7.setCaretPosition(0);
                    jTextArea3.setText(tempQuestion.opt[3]);
                    jTextArea3.setCaretPosition(0);

                    buttonGroup1.clearSelection();
                }
            }
        };

        for (i = 0; i < 20; i++) {
            buttons[i] = new JButton("" + (i + 1));

            this.jPanel1.add(buttons[i]);
            buttons[i].setBounds(25, 150 + i * 40, 50, 20);
            buttons[i].setVisible(true);
        }
        int j = 0;
        for (i = 20; i < 40; i++) {
            buttons[i] = new JButton("" + (i + 1));
            this.jPanel1.add(buttons[i]);
            buttons[i].setBounds(100, 150 + j * 40, 50, 20);
            buttons[i].setVisible(true);
            j++;
        }
        for (i = 0; i < 40; i++) {

            buttons[i].addActionListener(listener);
        }

    }

    public void init() throws IOException, ClassNotFoundException, IndexOutOfBoundsException {
        Reminder reminder = new Reminder(1);
        readEverything();
        a = num[0];
        str1 = questions.get(a);
        jTextArea1.setText("Question no #" + (quesNo + 1) + "\n" + str1.ques);
        jTextArea6.setText(questions.get(a).opt[0]);
        jTextArea2.setText(questions.get(a).opt[1]);
        jTextArea7.setText(questions.get(a).opt[2]);
        jTextArea3.setText(questions.get(a).opt[3]);
        jLabel3.setText(" " + 0 + " ");
    }

    public void readEverything() throws IOException, ClassNotFoundException {

        File f;
        f = new File("database.dat");
        if (!f.exists())
            return;
        ois = null;
        ip = new File("database.dat");
        fis = new FileInputStream("database.dat");
        ois = new ObjectInputStream(fis);
        Question tempQues;
        while (fis.available() != 0) {
            tempQues = (Question) ois.readObject();
            questions.add(tempQues);
        }
        for (int k = 0; k < 50; k++)
            flag[k] = false;
        int i = 0;
        while (i != 40) {
            temprand = r.nextInt(40);
            while (flag[temprand]) {
                temprand = r.nextInt(40);
            }
            flag[temprand] = true;
            num[i++] = temprand;
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        javax.swing.ButtonGroup buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        javax.swing.JScrollPane jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        javax.swing.JScrollPane jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea7 = new javax.swing.JTextArea();
        javax.swing.JScrollPane jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea6 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        javax.swing.JButton jButton3 = new javax.swing.JButton();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        javax.swing.JButton jButton2 = new javax.swing.JButton();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(44, 62, 80));
        jPanel1.setPreferredSize(new java.awt.Dimension(1366, 768));

        jLabel2.setFont(new java.awt.Font("Viner Hand ITC", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Mr. Mechanix");

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(236, 240, 241));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextArea3.setEditable(false);
        jTextArea3.setBackground(new java.awt.Color(236, 240, 241));
        jTextArea3.setColumns(20);
        jTextArea3.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jTextArea3.setRows(5);
        jTextArea3.setBorder(null);
        jTextArea3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextArea3MouseClicked(evt);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextArea3MouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextArea3MouseExited(evt);
            }
        });
        jScrollPane3.setViewportView(jTextArea3);

        jTextArea2.setEditable(false);
        jTextArea2.setBackground(new java.awt.Color(236, 240, 241));
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jTextArea2.setRows(5);
        jTextArea2.setBorder(null);
        jTextArea2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextArea2MouseClicked(evt);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextArea2MouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextArea2MouseExited(evt);
            }
        });
        jScrollPane2.setViewportView(jTextArea2);

        jTextArea7.setEditable(false);
        jTextArea7.setBackground(new java.awt.Color(236, 240, 241));
        jTextArea7.setColumns(20);
        jTextArea7.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jTextArea7.setRows(5);
        jTextArea7.setBorder(null);
        jTextArea7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextArea7MouseClicked(evt);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextArea7MouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextArea7MouseExited(evt);
            }
        });
        jScrollPane7.setViewportView(jTextArea7);

        jTextArea6.setEditable(false);
        jTextArea6.setBackground(new java.awt.Color(236, 240, 241));
        jTextArea6.setColumns(20);
        jTextArea6.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jTextArea6.setRows(5);
        jTextArea6.setBorder(null);
        jTextArea6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextArea6MouseClicked(evt);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextArea6MouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextArea6MouseExited(evt);
            }
        });
        jScrollPane6.setViewportView(jTextArea6);

        jLabel3.setFont(new java.awt.Font("Tw Cen MT", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));

        jLabel4.setFont(new java.awt.Font("Tw Cen MT", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Score");

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton6.setText("Submit");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton6MouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton6MouseExited(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tw Cen MT", 0, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Lifeline");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tw Cen MT", 0, 36)); // NOI18N
        jButton1.setText("50-50");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Time");

        jLabel8.setFont(new java.awt.Font("Tw Cen MT", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Time");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));

        jLabel5.setFont(new java.awt.Font("Tw Cen MT", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Time");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Andalus", 1, 36)); // NOI18N
        jButton3.setText("End");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Questions");

        jButton2.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jButton2.setText("Click Here for Image");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("Design by: Pratik Jain & Ankit Jain, Software Design Team ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(203, 203, 203)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(jScrollPane7)
                                                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addGap(98, 98, 98)
                                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(jButton1)))
                                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addGap(113, 113, 113)
                                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(57, 57, 57))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                                                .addComponent(jButton3)
                                                                                .addGap(103, 103, 103))))))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(226, 226, 226)
                                                .addComponent(jButton6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel10)
                                                .addGap(22, 22, 22))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(335, 335, 335)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(110, 110, 110))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 43, Short.MAX_VALUE)))
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(33, 33, 33)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(28, 28, 28)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(80, 80, 80)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(34, 34, 34)
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        if (Reminder.timerFlag == 0)
            if (jTextArea6.getBackground() == c || jTextArea2.getBackground() == c || jTextArea7.getBackground() == c || jTextArea3.getBackground() == c) {
                if (jTextArea6.getBackground() == c)
                    answer = 1;
                if (jTextArea2.getBackground() == c)
                    answer = 2;
                if (jTextArea6.getBackground() == c)
                    answer = 3;
                if (jTextArea6.getBackground() == c)
                    answer = 4;
                if (answer == questions.get(a).ans) {
                    score = score + 3;
                    jLabel3.setText("" + (score) + "");
                } else
                    jLabel3.setText("" + (--score) + "");
                jTextArea6.setEnabled(true);
                jTextArea3.setEnabled(true);
                jTextArea7.setEnabled(true);
                jTextArea2.setEnabled(true);
                jTextArea6.setBackground(d);
                jTextArea2.setBackground(d);
                jTextArea3.setBackground(d);
                jTextArea7.setBackground(d);
                buttons[cur - 1].setEnabled(false);
                buttons[cur - 1].setBackground(Color.RED);
                cur = cur + 1;
                if (cur == 41)
                    cur = 1;
                a = num[(cur - 1)];
                int cur1 = cur;
                while (cur > 40 || buttons[cur - 1].isEnabled() == false) {
                    cur = cur + 1;
                    if (cur == 41)
                        cur = 1;
                    a = num[(cur - 1)];
                    if (cur == cur1) {
                        timerFlag = 1;
                        Reminder.timer.cancel();
                        Score s = new Score();
                        s.dispose();
                        s.setUndecorated(true);
                        s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        s.setSize(1366, 768);
                        s.setVisible(true);
                        s.setOpacity((float) 0.8);
                        String sentence;
                        try (Socket clientSocket = new Socket("10.1.33.51", 1111)) {
                            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                            sentence = "" + score + " | " + Login.jTextField1.getText() + " | " + Login.jTextField2.getText() + " | " + Login.jTextField3.getText() + "\n";
                            outToServer.writeBytes(sentence + '\n');
                        } catch (IOException ex) {
                            Logger.getLogger(Reminder.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;

                    }
                }
                colorFlag6 = colorFlag2 = colorFlag7 = colorFlag3 = 0;
                Question tempQuestion = questions.get(a);
                jTextArea1.setText("Question no #" + (cur) + "\n" + tempQuestion.ques);
                jTextArea1.setCaretPosition(0);
                jTextArea6.setText(tempQuestion.opt[0]);
                jTextArea6.setCaretPosition(0);
                jTextArea2.setText(tempQuestion.opt[1]);
                jTextArea2.setCaretPosition(0);
                jTextArea7.setText(tempQuestion.opt[2]);
                jTextArea7.setCaretPosition(0);
                jTextArea3.setText(tempQuestion.opt[3]);
                jTextArea3.setCaretPosition(0);
                buttonGroup1.clearSelection();

            } else {
                JOptionPane.showMessageDialog(null, "Please Select a valid option");
            }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jTextArea7.setBackground(d);
        jTextArea2.setBackground(d);
        jTextArea3.setBackground(d);
        jTextArea6.setBackground(d);

        jButton1.setEnabled(false);
        answer = questions.get(a).ans;
        if (answer == 1) {
            jTextArea2.setText("");
            jTextArea7.setText("");
            jTextArea2.setEnabled(false);
            jTextArea7.setEnabled(false);
        } else if (answer == 2) {
            jTextArea6.setText("");
            jTextArea3.setText("");
            jTextArea6.setEnabled(false);
            jTextArea3.setEnabled(false);
        } else if (answer == 3) {
            jTextArea6.setText("");
            jTextArea2.setText("");
            jTextArea6.setEnabled(false);
            jTextArea2.setEnabled(false);
        } else if (answer == 4) {
            jTextArea6.setText("");
            jTextArea7.setText("");
            jTextArea6.setEnabled(false);
            jTextArea7.setEnabled(false);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        jButton1.setBackground(c);      // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        jButton1.setBackground(d);         // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseExited

    private void jButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseEntered
        jButton6.setBackground(c);    // TODO add your handling code here:
    }//GEN-LAST:event_jButton6MouseEntered

    private void jButton6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseExited

        jButton6.setBackground(d);   // TODO add your handling code here:
    }//GEN-LAST:event_jButton6MouseExited

    private void jTextArea6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea6MouseEntered


        jTextArea6.setBackground(c);    // TODO add your handling code here:
    }//GEN-LAST:event_jTextArea6MouseEntered

    private void jTextArea6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea6MouseExited
        if (colorFlag6 == 0)
            jTextArea6.setBackground(d);// TODO add your handling code here:
    }//GEN-LAST:event_jTextArea6MouseExited

    private void jTextArea7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea7MouseEntered
        jTextArea7.setBackground(c);  // TODO add your handling code here:
    }//GEN-LAST:event_jTextArea7MouseEntered

    private void jTextArea7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea7MouseExited
        if (colorFlag7 == 0)
            jTextArea7.setBackground(d);  // TODO add your handling code here:
    }//GEN-LAST:event_jTextArea7MouseExited

    private void jTextArea3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea3MouseEntered
        jTextArea3.setBackground(c);  // TODO add your handling code here:
    }//GEN-LAST:event_jTextArea3MouseEntered

    private void jTextArea3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea3MouseExited
        if (colorFlag3 == 0)
            jTextArea3.setBackground(d);  // TODO add your handling code here:
    }//GEN-LAST:event_jTextArea3MouseExited

    private void jTextArea2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea2MouseEntered
        jTextArea2.setBackground(c);  // TODO add your handling code here:
    }//GEN-LAST:event_jTextArea2MouseEntered

    private void jTextArea2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea2MouseExited
        if (colorFlag2 == 0)
            jTextArea2.setBackground(d);  // TODO add your handling code here:
    }//GEN-LAST:event_jTextArea2MouseExited

    private void jTextArea3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea3MouseClicked
        jTextArea7.setBackground(d);
        jTextArea2.setBackground(d);
        jTextArea3.setBackground(c);
        jTextArea6.setBackground(d);
        colorFlag3 = 1;
        colorFlag2 = colorFlag6 = colorFlag7 = 0;
// TODO add your handling code here:
    }//GEN-LAST:event_jTextArea3MouseClicked

    private void jTextArea2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea2MouseClicked
        jTextArea7.setBackground(d);
        jTextArea2.setBackground(c);
        jTextArea3.setBackground(d);
        jTextArea6.setBackground(d);
        colorFlag2 = 1;
        colorFlag3 = colorFlag6 = colorFlag7 = 0;// TODO add your handling code here:
    }//GEN-LAST:event_jTextArea2MouseClicked

    private void jTextArea7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea7MouseClicked
        jTextArea7.setBackground(c);
        jTextArea2.setBackground(d);
        jTextArea3.setBackground(d);
        jTextArea6.setBackground(d);
        colorFlag7 = 1;
        colorFlag2 = colorFlag6 = colorFlag3 = 0;
// TODO add your handling code here:
    }//GEN-LAST:event_jTextArea7MouseClicked

    private void jTextArea6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea6MouseClicked
        jTextArea7.setBackground(d);
        jTextArea2.setBackground(d);
        jTextArea3.setBackground(d);
        jTextArea6.setBackground(c);
        colorFlag6 = 1;
        colorFlag2 = colorFlag7 = colorFlag3 = 0;// TODO add your handling code here:
    }//GEN-LAST:event_jTextArea6MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (timerFlag == 0) {
            timerFlag = 1;
            Reminder.timer.cancel();
            Score s = new Score();
            s.dispose();
            s.setUndecorated(true);
            s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            s.setSize(1366, 768);
            s.setVisible(true);
            s.setOpacity((float) 0.8);
            String sentence;
            try (Socket clientSocket = new Socket("10.1.33.51", 1111)) {
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

                sentence = "" + score + " | " + Login.jTextField1.getText() + " | " + Login.jTextField2.getText() + " | " + Login.jTextField3.getText() + "\n";
                outToServer.writeBytes(sentence + '\n');
            } catch (IOException ex) {
                Logger.getLogger(Reminder.class.getName()).log(Level.SEVERE, null, ex);
            }// TODO add your handling code here:
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        if (timerFlag == 0) {
            System.out.println(a);
            Image in = Toolkit.getDefaultToolkit().getImage("Data\\" + a + ".jpg");
            Images img = new Images();
            Images.jLabel1.setIcon(new ImageIcon(in));
            img.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            img.setSize(1366, 768);
            in = Toolkit.getDefaultToolkit().getImage("Data\\" + (a) + ".jpg");
            Images.jLabel1.setIcon(new ImageIcon(in));
            if (in.getWidth(null) != -1)
                img.setVisible(true);         // TODO add your handling code here:
        }
    }//GEN-LAST:event_jButton2MouseClicked
}
