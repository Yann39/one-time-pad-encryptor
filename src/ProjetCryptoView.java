/*
 * ProjetCryptoView.java
 *
 * Created on 19 nov. 2008
 * By Yann39 <rockyracer@mailfence.com>
 */

package projetcrypto;

import java.awt.Color;
import java.awt.Desktop;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * The application's main frame.
 */
public class ProjetCryptoView extends FrameView {

    public ProjetCryptoView(SingleFrameApplication app) {
        super(app);
        
        initComponents();
        jButton4.setEnabled(false);
        jButton5.setEnabled(false);
        jButton7.setEnabled(false);
        
        //initialise le LFSR
        lfsr[0] = 5; lfsr[1] = 6; lfsr[2] = 15; lfsr[3] = 16;
        for (int i=4;i<lfsr.length;i++)
            lfsr[i] = 0;

        int nbProc = Runtime.getRuntime().availableProcessors();
        if (nbProc > 1)
            statusMessageLabel.setText("Votre machine possède "+nbProc+" processeurs");
        else
            statusMessageLabel.setText("Votre machine possède un seul processeur");
        
        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = ProjetCryptoApp.getApplication().getMainFrame();
            aboutBox = new ProjetCryptoAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        ProjetCryptoApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        ouvrirMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem3 = new javax.swing.JRadioButtonMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jRadioButtonMenuItem7 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem8 = new javax.swing.JRadioButtonMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        jRadioButtonMenuItem9 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem10 = new javax.swing.JRadioButtonMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jRadioButtonMenuItem4 = new javax.swing.JRadioButtonMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jRadioButtonMenuItem5 = new javax.swing.JRadioButtonMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        jRadioButtonMenuItem6 = new javax.swing.JRadioButtonMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(projetcrypto.ProjetCryptoApp.class).getContext().getResourceMap(ProjetCryptoView.class);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
        jTextField2.setName("jTextField2"); // NOI18N

        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setIcon(resourceMap.getIcon("jLabel3.icon")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setIcon(resourceMap.getIcon("jLabel4.icon")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jTextField3.setText(resourceMap.getString("jTextField3.text")); // NOI18N
        jTextField3.setName("jTextField3"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setIcon(resourceMap.getIcon("jLabel6.icon")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jButton10.setIcon(resourceMap.getIcon("jButton10.icon")); // NOI18N
        jButton10.setText(resourceMap.getString("jButton10.text")); // NOI18N
        jButton10.setName("jButton10"); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                        .addGap(122, 122, 122))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                        .addGap(122, 122, 122))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                        .addGap(219, 219, 219))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(7, 7, 7))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addContainerGap()))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jTextField4.setText(resourceMap.getString("jTextField4.text")); // NOI18N
        jTextField4.setName("jTextField4"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel8.setIcon(resourceMap.getIcon("jLabel8.icon")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jButton3.setIcon(resourceMap.getIcon("jButton3.icon")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(resourceMap.getIcon("jButton4.icon")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(resourceMap.getIcon("jButton5.icon")); // NOI18N
        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setIcon(resourceMap.getIcon("jButton6.icon")); // NOI18N
        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setName("jButton6"); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton8.setIcon(resourceMap.getIcon("jButton8.icon")); // NOI18N
        jButton8.setText(resourceMap.getString("jButton8.text")); // NOI18N
        jButton8.setName("jButton8"); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 129, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7))
                            .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8)
                    .addComponent(jButton5)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(resourceMap.getColor("jPanel3.background")); // NOI18N
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(resourceMap.getColor("jPanel3.border.lineColor"))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(resourceMap.getColor("jScrollPane1.border.lineColor"))); // NOI18N
        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setFont(resourceMap.getFont("jTextArea1.font")); // NOI18N
        jTextArea1.setRows(4);
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        jButton7.setIcon(resourceMap.getIcon("jButton7.icon")); // NOI18N
        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setAutoscrolls(true);
        jButton7.setName("jButton7"); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton9.setIcon(resourceMap.getIcon("jButton9.icon")); // NOI18N
        jButton9.setText(resourceMap.getString("jButton9.text")); // NOI18N
        jButton9.setName("jButton9"); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton9)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName(resourceMap.getString("jPanel1.AccessibleContext.accessibleName")); // NOI18N

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        ouvrirMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        ouvrirMenuItem1.setIcon(resourceMap.getIcon("ouvrirMenuItem1.icon")); // NOI18N
        ouvrirMenuItem1.setText(resourceMap.getString("ouvrirMenuItem1.text")); // NOI18N
        ouvrirMenuItem1.setToolTipText(resourceMap.getString("ouvrirMenuItem1.toolTipText")); // NOI18N
        ouvrirMenuItem1.setName("ouvrirMenuItem1"); // NOI18N
        ouvrirMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        fileMenu.add(ouvrirMenuItem1);

        jMenuItem2.setIcon(resourceMap.getIcon("jMenuItem2.icon")); // NOI18N
        jMenuItem2.setText(resourceMap.getString("jMenuItem2.text")); // NOI18N
        jMenuItem2.setName("jMenuItem2"); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem2);

        jMenuItem3.setIcon(resourceMap.getIcon("jMenuItem3.icon")); // NOI18N
        jMenuItem3.setText(resourceMap.getString("jMenuItem3.text")); // NOI18N
        jMenuItem3.setName("jMenuItem3"); // NOI18N
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem3);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(projetcrypto.ProjetCryptoApp.class).getContext().getActionMap(ProjetCryptoView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setIcon(resourceMap.getIcon("exitMenuItem.icon")); // NOI18N
        exitMenuItem.setText(resourceMap.getString("exitMenuItem.text")); // NOI18N
        exitMenuItem.setToolTipText(resourceMap.getString("exitMenuItem.toolTipText")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        jMenu1.setText(resourceMap.getString("jMenu1.text")); // NOI18N
        jMenu1.setName("jMenu1"); // NOI18N

        jMenu2.setIcon(resourceMap.getIcon("jMenu2.icon")); // NOI18N
        jMenu2.setText(resourceMap.getString("jMenu2.text")); // NOI18N
        jMenu2.setName("jMenu2"); // NOI18N

        jRadioButtonMenuItem1.setText(resourceMap.getString("jRadioButtonMenuItem1.text")); // NOI18N
        jRadioButtonMenuItem1.setIcon(resourceMap.getIcon("jRadioButtonMenuItem1.icon")); // NOI18N
        jRadioButtonMenuItem1.setName("jRadioButtonMenuItem1"); // NOI18N
        jRadioButtonMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jRadioButtonMenuItem1);

        jRadioButtonMenuItem2.setSelected(true);
        jRadioButtonMenuItem2.setText(resourceMap.getString("jRadioButtonMenuItem2.text")); // NOI18N
        jRadioButtonMenuItem2.setIcon(resourceMap.getIcon("jRadioButtonMenuItem2.icon")); // NOI18N
        jRadioButtonMenuItem2.setName("jRadioButtonMenuItem2"); // NOI18N
        jRadioButtonMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jRadioButtonMenuItem2);

        jRadioButtonMenuItem3.setText(resourceMap.getString("jRadioButtonMenuItem3.text")); // NOI18N
        jRadioButtonMenuItem3.setIcon(resourceMap.getIcon("jRadioButtonMenuItem3.icon")); // NOI18N
        jRadioButtonMenuItem3.setName("jRadioButtonMenuItem3"); // NOI18N
        jRadioButtonMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jRadioButtonMenuItem3);

        jMenu1.add(jMenu2);

        jMenu4.setIcon(resourceMap.getIcon("jMenu4.icon")); // NOI18N
        jMenu4.setText(resourceMap.getString("jMenu4.text")); // NOI18N
        jMenu4.setName("jMenu4"); // NOI18N

        jRadioButtonMenuItem7.setSelected(true);
        jRadioButtonMenuItem7.setText(resourceMap.getString("jRadioButtonMenuItem7.text")); // NOI18N
        jRadioButtonMenuItem7.setIcon(resourceMap.getIcon("jRadioButtonMenuItem7.icon")); // NOI18N
        jRadioButtonMenuItem7.setName("jRadioButtonMenuItem7"); // NOI18N
        jRadioButtonMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem7ActionPerformed(evt);
            }
        });
        jMenu4.add(jRadioButtonMenuItem7);

        jRadioButtonMenuItem8.setText(resourceMap.getString("jRadioButtonMenuItem8.text")); // NOI18N
        jRadioButtonMenuItem8.setIcon(resourceMap.getIcon("jRadioButtonMenuItem8.icon")); // NOI18N
        jRadioButtonMenuItem8.setName("jRadioButtonMenuItem8"); // NOI18N
        jRadioButtonMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem8ActionPerformed(evt);
            }
        });
        jMenu4.add(jRadioButtonMenuItem8);

        jSeparator3.setName("jSeparator3"); // NOI18N
        jMenu4.add(jSeparator3);

        jRadioButtonMenuItem9.setSelected(true);
        jRadioButtonMenuItem9.setText(resourceMap.getString("jRadioButtonMenuItem9.text")); // NOI18N
        jRadioButtonMenuItem9.setIcon(resourceMap.getIcon("jRadioButtonMenuItem9.icon")); // NOI18N
        jRadioButtonMenuItem9.setName("jRadioButtonMenuItem9"); // NOI18N
        jRadioButtonMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem9ActionPerformed(evt);
            }
        });
        jMenu4.add(jRadioButtonMenuItem9);

        jRadioButtonMenuItem10.setText(resourceMap.getString("jRadioButtonMenuItem10.text")); // NOI18N
        jRadioButtonMenuItem10.setIcon(resourceMap.getIcon("jRadioButtonMenuItem10.icon")); // NOI18N
        jRadioButtonMenuItem10.setName("jRadioButtonMenuItem10"); // NOI18N
        jRadioButtonMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem10ActionPerformed(evt);
            }
        });
        jMenu4.add(jRadioButtonMenuItem10);

        jMenu1.add(jMenu4);

        jMenu3.setIcon(resourceMap.getIcon("jMenu3.icon")); // NOI18N
        jMenu3.setText(resourceMap.getString("jMenu3.text")); // NOI18N
        jMenu3.setName("jMenu3"); // NOI18N

        jRadioButtonMenuItem4.setSelected(true);
        jRadioButtonMenuItem4.setText(resourceMap.getString("jRadioButtonMenuItem4.text")); // NOI18N
        jRadioButtonMenuItem4.setIcon(resourceMap.getIcon("jRadioButtonMenuItem4.icon")); // NOI18N
        jRadioButtonMenuItem4.setName("jRadioButtonMenuItem4"); // NOI18N
        jRadioButtonMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jRadioButtonMenuItem4);

        jSeparator1.setName("jSeparator1"); // NOI18N
        jMenu3.add(jSeparator1);

        jRadioButtonMenuItem5.setText(resourceMap.getString("jRadioButtonMenuItem5.text")); // NOI18N
        jRadioButtonMenuItem5.setActionCommand(resourceMap.getString("jRadioButtonMenuItem5.actionCommand")); // NOI18N
        jRadioButtonMenuItem5.setIcon(resourceMap.getIcon("jRadioButtonMenuItem5.icon")); // NOI18N
        jRadioButtonMenuItem5.setName("jRadioButtonMenuItem5"); // NOI18N
        jRadioButtonMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jRadioButtonMenuItem5);

        jSeparator2.setName("jSeparator2"); // NOI18N
        jMenu3.add(jSeparator2);

        jRadioButtonMenuItem6.setText(resourceMap.getString("jRadioButtonMenuItem6.text")); // NOI18N
        jRadioButtonMenuItem6.setActionCommand(resourceMap.getString("jRadioButtonMenuItem6.actionCommand")); // NOI18N
        jRadioButtonMenuItem6.setIcon(resourceMap.getIcon("jRadioButtonMenuItem6.icon")); // NOI18N
        jRadioButtonMenuItem6.setName("jRadioButtonMenuItem6"); // NOI18N
        jRadioButtonMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jRadioButtonMenuItem6);

        jMenu1.add(jMenu3);

        jMenuItem1.setIcon(resourceMap.getIcon("jMenuItem1.icon")); // NOI18N
        jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        menuBar.add(jMenu1);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setIcon(resourceMap.getIcon("aboutMenuItem.icon")); // NOI18N
        aboutMenuItem.setText(resourceMap.getString("aboutMenuItem.text")); // NOI18N
        aboutMenuItem.setToolTipText(resourceMap.getString("aboutMenuItem.toolTipText")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        jMenuItem4.setIcon(resourceMap.getIcon("jMenuItem4.icon")); // NOI18N
        jMenuItem4.setText(resourceMap.getString("jMenuItem4.text")); // NOI18N
        jMenuItem4.setName("jMenuItem4"); // NOI18N
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        helpMenu.add(jMenuItem4);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 390, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

//==============================================================================
//======================= CRYPTER/DECRYPTER LES DONNEES ========================
//==============================================================================
public byte[] CrypterDecrypter(byte[] masque, byte[] donnees, int nbOctets) {
    //effectue un ou exclusif bit à bit entre le masque et les données
    byte ouex[] = new byte[nbOctets];
    for (int i=0; i<nbOctets; i++)
        ouex[i] = (byte)(donnees[i] ^ masque[i]);
    return ouex;
}

//==============================================================================
//======================== GENERER UNE GRAINE ALEATOIRE ========================
//==============================================================================
public int[] GenererGraine(int nbBits) {
    int[] graine = new int[nbBits];
    if (grainealeatoire == true) {
        Random rand = new Random();
        for (int i=0;i<nbBits;i++)
            graine[i] = rand.nextInt(2);
    }
    else {
        graine[0] = 1;
        for (int i=1;i<nbBits;i++)
            if (graine[i-1] == 0) graine[i] = 1; else graine[i] = 0;
    }
    return graine;
}

//==============================================================================
//======================= GENERER UN MASQUE ALEATOIRE ==========================
//==============================================================================
public int[] GenererMasqueAleatoire(int longueur, int[] nDerniersBits) {
    int bufferint[] = new int[longueur*8];

    //calcul la valeurs de tous les bits suivants dans le cas d'un ou exclusif de 4 lfsr
    if (jRadioButtonMenuItem5.isSelected() == true) {
        int vn[] = new int[(longueur*8)+lfsr[3]];
        int xn[] = new int[(longueur*8)+lfsr[7]];
        int yn[] = new int[(longueur*8)+lfsr[11]];
        int zn[] = new int[(longueur*8)+lfsr[15]];
        
        //initialise avec les derniers bits
        for (int i=0;i<lfsr[3];i++) vn[i] = nDerniersBits[i];
        for (int i=lfsr[3];i<lfsr[3]+lfsr[7];i++) xn[i-lfsr[3]] = nDerniersBits[i];
        for (int i=lfsr[3]+lfsr[7];i<lfsr[3]+lfsr[7]+lfsr[11];i++) yn[i-(lfsr[3]+lfsr[7])] = nDerniersBits[i];
        for (int i=lfsr[3]+lfsr[7]+lfsr[11];i<lfsr[3]+lfsr[7]+lfsr[11]+lfsr[15];i++) zn[i-(lfsr[3]+lfsr[7]+lfsr[11])] = nDerniersBits[i];
        
        //créé les bits suivants
        for (int i=0;i<(longueur*8);i++) {
            vn[i+lfsr[3]] = vn[(i+lfsr[3])-lfsr[0]] ^ vn[(i+lfsr[3])-lfsr[1]] ^ vn[(i+lfsr[3])-lfsr[2]] ^ vn[(i+lfsr[3])-lfsr[3]];
            xn[i+lfsr[7]] = xn[(i+lfsr[7])-lfsr[4]] ^ xn[(i+lfsr[7])-lfsr[5]] ^ xn[(i+lfsr[7])-lfsr[6]] ^ xn[(i+lfsr[7])-lfsr[7]];
            yn[i+lfsr[11]] = yn[(i+lfsr[11])-lfsr[8]] ^ yn[(i+lfsr[11])-lfsr[9]] ^ yn[(i+lfsr[11])-lfsr[10]] ^ yn[(i+lfsr[11])-lfsr[11]];
            zn[i+lfsr[15]] = zn[(i+lfsr[15])-lfsr[12]] ^ zn[(i+lfsr[15])-lfsr[13]] ^ zn[(i+lfsr[15])-lfsr[14]] ^ zn[(i+lfsr[15])-lfsr[15]];
            bufferint[i] = vn[i+lfsr[3]] ^ xn[i+lfsr[7]] ^ yn[i+lfsr[11]] ^ zn[i+lfsr[15]];
        }
    }
    //dans le cas d'un seul lfsr
    else {
        int tmp[] = new int[(longueur*8)+nDerniersBits.length];
        
        //initialise avec les derniers bits
        for (int i=0;i<nDerniersBits.length;i++) tmp[i] = nDerniersBits[i];
        
        //récupère le nombre de valeurs du lfsr
        int lg=0;
        for (int i=0;i<lfsr.length;i++)
            if (lfsr[i] != 0) lg++;
        
        //créé les bits suivants
        int k=0;
        for (int i=nDerniersBits.length;i<(longueur*8)+nDerniersBits.length;i++) {
            k = tmp[i-lfsr[0]];
            for (int j=1;j<lg;j++)
                k = k ^ tmp[i-lfsr[j]];
            tmp[i] = k;
            bufferint[i-nDerniersBits.length] = k;
        }
    }
    return bufferint;
}

//==============================================================================
//================= GENERER UN FICHIER CONTENANT LE MASQUE =====================
//==============================================================================
public void GenererFichierCle(int octetsFichier, String PathFichier) {
    BufferedOutputStream bos;
    FileOutputStream fos;
    int bitsGraine=0;
    
    //on test si les données sont plus longues que la graine, on fera le traitement en fonction
    if (octetsFichier < nbBitsGraine/8) {
        bitsGraine=octetsFichier*8;
        if (ecriregraine == true)
            jTextArea1.setText(jTextArea1.getText()+"\nLa graine est plus longue que les données, seul "+octetsFichier*8+" bits de la graine seront utilisés...");
    }
    else
        bitsGraine = nbBitsGraine;
    
    int octetsEcrits=0; //nombre d'octets déjà écrits
    int octetsAEcrire=0; //nombre d'octets à écrire par boucle
    int octetsRestants=1; //nombre d'octets total restant à écrire
    byte buffer[] = new byte[paquet]; //buffer contenant les octets à écrire
    int bufferint[] = new int[paquet*8]; //buffer contenant les bits à transformer en octets
    int partie[] = new int[nbBitsGraine]; //tableau contenant les nbBitsGraine dernier bits de la dernière génération
    
    try {
        fos = new FileOutputStream(PathFichier.substring(0,PathFichier.length()-longFichier)+"cle_"+PathFichier.substring(PathFichier.length()-longFichier,PathFichier.length()));
        bos = new BufferedOutputStream(fos);
        
        //on génère une graine aléatoire
        partie = GenererGraine(nbBitsGraine);

        if (ecriregraine == true) {
            //on écrit déjà la graine (ou les 'octetsFichier*8' premiers bits de la graine)
            int j=0;
            for (int i=0;i<bitsGraine/8;i++) {
                buffer[i] = (byte)Integer.parseInt(""+partie[j]+partie[j+1]+partie[j+2]+partie[j+3]+partie[j+4]+partie[j+5]+partie[j+6]+partie[j+7],2);
                j=j+8;
            }
            bos.write(buffer,0,bitsGraine/8);
            octetsEcrits = bitsGraine/8;
        }
        
        //on génère les "paquet" octets suivants du masque et on les écrit
        while (octetsRestants != 0) {
            octetsRestants = octetsFichier-octetsEcrits;
            if (octetsRestants >= paquet) octetsAEcrire = paquet; else octetsAEcrire=octetsRestants;
            
            //génère les 'octetsAEcrire' octets suivants du masque 
            bufferint = GenererMasqueAleatoire(octetsAEcrire,partie);
            
            //transforme les bits en octets et les stock dans le tableau de bytes
            int j=0;
            for (int i=0;i<octetsAEcrire;i++) {
                buffer[i] = (byte)Integer.parseInt(""+bufferint[j]+bufferint[j+1]+bufferint[j+2]+bufferint[j+3]+bufferint[j+4]+bufferint[j+5]+bufferint[j+6]+bufferint[j+7],2);
                j=j+8;
            }
            
            bos.write(buffer,0,octetsAEcrire);
            
            //récupère les n derniers bits nécéssaires au prochain calcul du masque
            for (int i=0;i<nbBitsGraine;i++) {
                partie[(nbBitsGraine-1)-i] = bufferint[(bufferint.length-1)-i];
            }

            avancement = avancement+(octetsAEcrire);
            popupAvancement.modifAvancement(avancement);
            if (octetsRestants < paquet) octetsRestants = 0; else octetsEcrits = octetsEcrits+paquet;
            if (ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed() > memusage)
                memusage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
        }
        bos.close();
    }
    catch(FileNotFoundException ef){
            System.out.println("fichier introuvable : "+ef);
    }
    catch(IOException e){
            System.out.println("(Fonction GenererMasqueAleatoire) Erreur : "+e);
    }
}    

//==============================================================================
//================== FORMATER l'AFFICHAGE DU TEMPS D'EXECUTION =================
//==============================================================================
public String FormaterTemps(long temps, boolean mili) {
    int seconds = (int)((temps/1000) % 60);
    int minutes = (int)((temps/60000) % 60);
    int hours = (int)((temps/3600000) % 24);
    String secondsStr = (seconds<10 ? "0" : "")+seconds;
    String minutesStr = (minutes<10 ? "0" : "")+minutes;
    String hoursStr = (hours<10 ? "0" : "")+hours;
    if (mili == true) {
        int milliseconds = (int)(temps % 1000);
        String millisecondsStr = (milliseconds<10 ? "00" : (milliseconds<100 ? "0" : ""))+milliseconds;
        return new String(hoursStr+":"+minutesStr+":"+secondsStr+":"+millisecondsStr);
    }
    else
        return new String(hoursStr+":"+minutesStr+":"+secondsStr);
}  

//==============================================================================
//===================== APPUI SUR LE BOUTON OUVRIR FICHIER =====================
//==============================================================================
private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    JFileChooser chooser = new JFileChooser();
    chooser.setDialogTitle("Choisissez le fichier à crypter ou à décrypter");
    int returnVal = chooser.showOpenDialog(this.getFrame());
    if(returnVal == JFileChooser.APPROVE_OPTION) {
        jTextField4.setText(chooser.getSelectedFile().getAbsolutePath());
        longFichier = chooser.getSelectedFile().getName().length();
        nomFic = chooser.getSelectedFile().getName();
    }
}//GEN-LAST:event_jButton3ActionPerformed

//==============================================================================
//==================== APPUI SUR LE BOUTON CRYPTER FICHIER =====================
//==============================================================================
private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    jTextField1.setBackground(blanc);
    jTextField2.setBackground(blanc);
    jTextField3.setBackground(blanc);
    jTextField4.setBackground(blanc);
    jButton4.setEnabled(false);
    jButton5.setEnabled(false);
    jButton7.setEnabled(false);
    if (jTextField4.getText().length() == 0) {
        jTextArea1.setText("Aucun fichier à crypter n'a été spécifié.");
        jTextField4.setBackground(rose);
    }
    else {
        popupAvancement = new ProjetCryptoPopup("cryptage");
        popupAvancement.setVisible(true);
        busyIconTimer.start();
        new Thread() {
            public void run() {
                long timeStart = System.currentTimeMillis();
                long timeStartcle=0,timeStopcle=0,timeStartouex=0,timeStopouex=0;
                BufferedInputStream bis,bis2;
                BufferedOutputStream bos;
                int octetLu,longueurtot=0;
                byte donnees[] = new byte[paquet];
                byte masque[] = new byte[paquet];
                byte ouex[] = new byte[paquet];
                boolean afficher = true;
                try {
                    bis = new BufferedInputStream(new FileInputStream(jTextField4.getText()));
                    bos = new BufferedOutputStream(new FileOutputStream(jTextField4.getText().substring(0,jTextField4.getText().length()-longFichier)+"crypte_"+nomFic));
                    longueurtot = bis.available();
                    popupAvancement.AvancementMax((int)(longueurtot*1.1));
                    jTextArea1.setText(longueurtot+" octets à crypter\ntraitement en cours...");
                    timeStartcle = System.currentTimeMillis();
                    GenererFichierCle(longueurtot,jTextField4.getText());
                    timeStopcle = System.currentTimeMillis();
                    bis2 = new BufferedInputStream(new FileInputStream(jTextField4.getText().substring(0,jTextField4.getText().length()-longFichier)+"cle_"+nomFic));
                    timeStartouex = System.currentTimeMillis();
                    while((octetLu=bis.read(donnees,0,paquet)) != -1) {
                        bis2.read(masque,0,octetLu);
                        ouex = CrypterDecrypter(masque, donnees, octetLu);
                        bos.write(ouex,0,octetLu);
                        avancement = avancement+(octetLu/10);
                        popupAvancement.modifAvancement(avancement);
                        if (ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed() > memusage)
                            memusage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
                    }
                    timeStopouex = System.currentTimeMillis();
                    bis.close();
                    bis2.close();
                    bos.close();
                }
                catch(FileNotFoundException ef){
                    jTextArea1.setText("Le fichier spécifié est introuvable.");
                    jTextField4.setBackground(rose);
                    afficher = false;
                }
                catch(IOException e){
                    jTextArea1.setText("Erreur lors de la lecture ou l'écriture du fichier.");
                    jTextField4.setBackground(rose);
                    afficher = false;
                }
                if (afficher == true) {
                    jButton4.setText("Voir le fichier crypté");
                    typeTrait = "cryptage";
                    jButton4.setEnabled(true);
                    jButton5.setVisible(true);
                    jButton5.setEnabled(true);
                    jButton7.setEnabled(true);
                    String elefaisair="";
                    if (jRadioButtonMenuItem4.isSelected() == true)
                        elefaisair = jRadioButtonMenuItem4.getText();
                    else if (jRadioButtonMenuItem5.isSelected() == true) {
                        elefaisair = jRadioButtonMenuItem5.getText();
                        elefaisair = elefaisair.replace("<br/>","; ");
                    }
                    else {
                        elefaisair = jRadioButtonMenuItem6.getText();
                        elefaisair = elefaisair.replace("<br/>"," ");
                    }
                    elefaisair = elefaisair.replace("<html>","");
                    elefaisair = elefaisair.replace("</html>","");
                    String graine_ecr="";
                    if (jRadioButtonMenuItem9.isSelected()) graine_ecr="(graine écrite)";
                    else graine_ecr="(graine non écrite)";
                    long timeStop = System.currentTimeMillis();
                    texte = FormaterTemps(timeStart,false)+" : Fichier à crypter : "+jTextField4.getText()+"\n"+FormaterTemps(timeStart,false)+" : Taille du fichier : "+longueurtot+" octets\n"+FormaterTemps(timeStart,false)+" : Lecture/écriture par paquets de : "+paquet+" octets ("+(int)Math.ceil((float)longueurtot/(float)paquet)+" boucle(s) pour ce fichier)\n"+FormaterTemps(timeStopcle,false)+" : Temps de génération du masque : "+FormaterTemps(timeStopcle-timeStartcle,true)+" ms\n"+FormaterTemps(timeStopcle,false)+" : Taille de la graine : "+nbBitsGraine+" bits "+graine_ecr+"\n"+FormaterTemps(timeStopcle,false)+" : LFSR : "+elefaisair+"\n"+FormaterTemps(timeStopouex,false)+" : Temps de cryptage : "+FormaterTemps(timeStopouex-timeStartouex,true)+" ms\n"+FormaterTemps(timeStop,false)+" : Temps total de traitement : "+FormaterTemps(timeStop-timeStart,true)+" ms\n"+FormaterTemps(timeStop,false)+" : Mémoire max utilisée : "+memusage/1024+" K\n"+FormaterTemps(timeStop,false)+" : Fichier crypté : "+jTextField4.getText().substring(0,jTextField4.getText().length()-longFichier)+"crypte_"+nomFic+"\n"+FormaterTemps(timeStop,false)+" : Masque : "+jTextField4.getText().substring(0,jTextField4.getText().length()-longFichier)+"cle_"+nomFic;
                    jTextArea1.setText(jTextArea1.getText()+"\n"+longueurtot+" octets cryptés avec succès !");
                    jTextArea1.setText(jTextArea1.getText()+"\n"+"durée du traitement : "+(FormaterTemps(timeStop-timeStart,true))+" ms");
                }
                avancement = 0;
                popupAvancement.dispose();
                busyIconTimer.stop();
                statusAnimationLabel.setIcon(idleIcon);
            }
        }.start();
    }
}//GEN-LAST:event_jButton6ActionPerformed

//==============================================================================
//==================== APPUI SUR LE BOUTON CRYPTER DONNEES =====================
//==============================================================================
private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    jTextField1.setBackground(blanc);
    jTextField2.setBackground(blanc);
    jTextField3.setBackground(blanc);
    jTextField4.setBackground(blanc);
    jButton7.setEnabled(false);
    if (jTextField1.getText().length() == 0) {
        jTextArea1.setText("Aucune donnée à crypter n'a été saisie !");
        jTextField1.setBackground(rose);
    }
    else if (jTextField1.getText().length() > 2048) {
        jTextArea1.setText("La limite de 2048 caractères autorisés a été dépassée ! ("+jTextField1.getText().length()+")");
        jTextField1.setBackground(rose);
    }
    else {
        long timeStart = System.currentTimeMillis();
        long timeStartcle=0,timeStopcle=0,timeStartouex=0,timeStopouex=0;
        int longueur = jTextField1.getText().length();
        int masqueint[] = new int[longueur*8];
        int bitsGraine = longueur*8;
        byte donnees[] = new byte[longueur];
        byte ouex[] = new byte[longueur];
        byte masque[] = new byte[longueur];
        jTextField2.setText("");
        jTextField3.setText("");
        jButton7.setEnabled(true);
        jTextArea1.setText(longueur+" octets à crypter\ntraitement en cours...");
        try {
            donnees = jTextField1.getText().getBytes("ISO-8859-1");
        }
        catch (UnsupportedEncodingException e){System.out.println(e);}
        timeStartcle = System.currentTimeMillis();
        boolean line_feed = true;
        //si le caractère "line feed" (interligne) est trouvé, on regénère une graine ainsi que le masque,
        //ce caractère et interprété comme un espace dans le jTextfield et pose des problèmes pour le décryptage
        while (line_feed==true) {
            line_feed = false;
            int[] graine = GenererGraine(nbBitsGraine);

            if (ecriregraine == true) {
                if (bitsGraine < nbBitsGraine) {
                    jTextArea1.setText(jTextArea1.getText().replace("\nLa graine est plus longue que les données, seul "+bitsGraine+" bits de la graine seront utilisés...",""));
                    jTextArea1.setText(jTextArea1.getText()+"\nLa graine est plus longue que les données, seul "+bitsGraine+" bits de la graine seront utilisés...");
                }
                else
                    bitsGraine = nbBitsGraine;

                //on écrit déjà la graine dans le masque (ou les 'longueur*8' premiers bits)
                for (int i=0;i<bitsGraine;i++)
                    masqueint[i] = graine[i];

                //si les données sont plus grande que la graine, on calcul les bits suivants que l'on concatène à la suite du masque
                if (longueur*8 > nbBitsGraine) {
                    int suitemasqueint[] = new int[longueur-(nbBitsGraine/8)];
                    suitemasqueint = GenererMasqueAleatoire(longueur-(nbBitsGraine/8),graine);
                    for (int i=bitsGraine;i<longueur*8;i++)
                        masqueint[i] = suitemasqueint[i-bitsGraine];
                }
            }
            else {
                    masqueint = GenererMasqueAleatoire(longueur,graine);
            }
            //on transforme chaque série de 8 bits en octet
            int j=0;
            for (int i=0;i<longueur;i++) {
                masque[i] = (byte)Integer.parseInt(""+masqueint[j]+masqueint[j+1]+masqueint[j+2]+masqueint[j+3]+masqueint[j+4]+masqueint[j+5]+masqueint[j+6]+masqueint[j+7],2);
                j=j+8;
            }
            timeStopcle = System.currentTimeMillis();
            timeStartouex = System.currentTimeMillis();
            ouex = CrypterDecrypter(masque, donnees, longueur);
            timeStopouex = System.currentTimeMillis();
            //si on trouve un interligne, line_feed passe à true donc on recommence
            for (int i=0;i<longueur;i++) {
                if ((masque[i]==10) || (ouex[i]==10))
                    line_feed = true;
            }
        }
        try {
            jTextField2.setText(jTextField2.getText()+new String(ouex,"ISO-8859-1"));
            jTextField3.setText(jTextField3.getText()+new String(masque,"ISO-8859-1"));
        }
        catch (UnsupportedEncodingException e) { System.out.println(e); }
        typeTrait = "cryptage";
        String elefaisair="";
        if (jRadioButtonMenuItem4.isSelected() == true)
            elefaisair = jRadioButtonMenuItem4.getText();
        else if (jRadioButtonMenuItem5.isSelected() == true) {
            elefaisair = jRadioButtonMenuItem5.getText();
            elefaisair = elefaisair.replace("<br/>","; ");
        }
        else {
            elefaisair = jRadioButtonMenuItem6.getText();
            elefaisair = elefaisair.replace("<br/>"," ");
        }
        elefaisair = elefaisair.replace("<html>","");
        elefaisair = elefaisair.replace("</html>","");
        String graine_ecr="";
        if (jRadioButtonMenuItem9.isSelected()) graine_ecr="(graine écrite)";
        else graine_ecr="(graine non écrite)";
        long timeStop = System.currentTimeMillis();
        texte=FormaterTemps(timeStart,false)+" : Données à crypter : "+jTextField1.getText()+"\n"+FormaterTemps(timeStart,false)+" : Taille des données : "+longueur+" octets\n"+FormaterTemps(timeStopcle,false)+" : Temps de génération du masque : "+FormaterTemps(timeStopcle-timeStartcle,true)+" ms\n"+FormaterTemps(timeStopcle,false)+" : Taille de la graine : "+nbBitsGraine+" bits "+graine_ecr+"\n"+FormaterTemps(timeStopcle,false)+" : LFSR : "+elefaisair+"\n"+FormaterTemps(timeStopouex,false)+" : Temps de cryptage : "+FormaterTemps(timeStopouex-timeStartouex,true)+" ms\n"+FormaterTemps(timeStop,false)+" : Temps total de traitement : "+FormaterTemps(timeStop-timeStart,true)+" ms\n"+FormaterTemps(timeStop,false)+" : Données cryptées : "+jTextField2.getText()+"\n"+FormaterTemps(timeStop,false)+" : Masque : "+jTextField3.getText();
        jTextArea1.setText(jTextArea1.getText()+"\n"+longueur+" octets cryptés avec succès !");
        jTextArea1.setText(jTextArea1.getText()+"\ndurée du traitement : "+(FormaterTemps(timeStop-timeStart,true))+" ms");
    }
}//GEN-LAST:event_jButton1ActionPerformed

//==============================================================================
//=================== APPUI SUR LE BOUTON DECRYPTER DONNEES ====================
//==============================================================================
private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    jTextField1.setBackground(blanc);
    jTextField2.setBackground(blanc);
    jTextField3.setBackground(blanc);
    jTextField4.setBackground(blanc);
    jButton7.setEnabled(false);
    if (jTextField2.getText().length() == 0 && jTextField3.getText().length() != 0) {
        jTextArea1.setText("Aucune donnée cryptée n'a été saisie !");
        jTextField2.setBackground(rose);
    }
    else if (jTextField3.getText().length() == 0 && jTextField2.getText().length() != 0) {
        jTextArea1.setText("Aucun masque n'a été saisi !");
        jTextField3.setBackground(rose);
    }
    else if (jTextField3.getText().length() == 0 && jTextField2.getText().length() == 0) {
        jTextArea1.setText("Aucune donnée et aucun masque n'a été saisi !");
        jTextField2.setBackground(rose);
        jTextField3.setBackground(rose);
    }
    else if (jTextField2.getText().length() > 2048 || jTextField3.getText().length() > 2048) {
        jTextArea1.setText("La limite de 2048 caractères autorisés a été dépassée ! (données:"+jTextField2.getText().length()+", clé:"+jTextField3.getText().length()+")");
        if (jTextField2.getText().length() > 2048) jTextField2.setBackground(rose);
        else jTextField3.setBackground(rose);
    }
    else if (jTextField3.getText().length() != jTextField2.getText().length()) {
        jTextArea1.setText("Le masque n'a pas la même longueur que les données !");
        jTextField3.setBackground(rose);
        jTextField2.setBackground(rose);
    }
    else {
        long timeStart = System.currentTimeMillis();
        long timeStartouex=0,timeStopouex=0;
        int longueur = jTextField2.getText().length();
        byte donnees[] = new byte[longueur];
        byte masque[] = new byte[longueur];
        byte ouex[] = new byte[longueur];
        jTextField1.setText("");
        typeTrait = "decryptage";
        jButton7.setEnabled(true);
        jTextArea1.setText(longueur+" octets à crypter\ntraitement en cours...");
        try {
            donnees = jTextField2.getText().getBytes("ISO-8859-1");
            masque = jTextField3.getText().getBytes("ISO-8859-1");
        }
        catch (UnsupportedEncodingException e){System.out.println(e);}
        timeStartouex = System.currentTimeMillis();
        ouex = CrypterDecrypter(masque, donnees, longueur);
        timeStopouex = System.currentTimeMillis();
        try {
            jTextField1.setText(new String(ouex,"ISO-8859-1"));
        }
        catch (UnsupportedEncodingException e){System.out.println(e);}
        long timeStop = System.currentTimeMillis();
        texte=FormaterTemps(timeStart,false)+" : Données à décrypter : "+jTextField2.getText()+"\n"+FormaterTemps(timeStart,false)+" : Taille des données : "+longueur+" octets\n"+FormaterTemps(timeStart,false)+" : Masque : "+jTextField3.getText()+"\n"+FormaterTemps(timeStopouex,false)+" : Temps de décryptage : "+FormaterTemps(timeStopouex-timeStartouex,true)+" ms\n"+FormaterTemps(timeStop,false)+" : Temps total de traitement : "+FormaterTemps(timeStop-timeStart,true)+" ms\n"+FormaterTemps(timeStop,false)+" : Données décryptées : "+jTextField1.getText();
        jTextArea1.setText(jTextArea1.getText()+"\n"+longueur+" octets cryptés avec succès !");
        jTextArea1.setText(jTextArea1.getText()+"\ndurée du traitement : "+(FormaterTemps(timeStop-timeStart,true))+" ms");
    }
}//GEN-LAST:event_jButton2ActionPerformed

//==============================================================================
//================= APPUI SUR LE BOUTON VOIR LE FICHIER CRYPTE =================
//==============================================================================
private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    //on vérifie que la classe Desktop soit bien supportée
    if ( Desktop.isDesktopSupported() ) {
        //on récupère l'instance du desktop
        Desktop desktop = Desktop.getDesktop();
        //on vérifie que la fonction open soit bien supportée
        if (desktop.isSupported(Desktop.Action.OPEN)) {
            try {
                //on lance l'application associé au fichier pour l'ouvrir
                if (typeTrait.equals("decryptage"))
                    desktop.open(new File(jTextField4.getText().substring(0,jTextField4.getText().length()-longFichier)+"decrypte_"+nomFic));
                else
                    desktop.open(new File(jTextField4.getText().substring(0,jTextField4.getText().length()-longFichier)+"crypte_"+nomFic));
            }
            catch (IOException e){
                System.out.println(e);
                jTextArea1.setText("Impossible d'ouvrir le fichier spécifié");
            }
            catch (IllegalArgumentException e){
                System.out.println(e);
                jTextArea1.setText("Impossible d'ouvrir le fichier spécifié");
            }
        }
    }
}//GEN-LAST:event_jButton4ActionPerformed

//==============================================================================
//================= APPUI SUR LE BOUTON VOIR LE MASQUE GENERE ==================
//==============================================================================
private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    //on vérifie que la classe Desktop soit bien supportée
    if ( Desktop.isDesktopSupported() ) {
        //on récupère l'instance du desktop
        Desktop desktop = Desktop.getDesktop();
        //on vérifie que la fonction open soit bien supportée
        if (desktop.isSupported(Desktop.Action.OPEN)) {
            try {
                //on lance l'application associé au fichier pour l'ouvrir
                desktop.open(new File(jTextField4.getText().substring(0,jTextField4.getText().length()-longFichier)+"cle_"+nomFic));
            }
            catch (IOException e){
                System.out.println(e);
                jTextArea1.setText("Impossible d'ouvrir le fichier spécifié");
            }
            catch (IllegalArgumentException e){
                System.out.println(e);
                jTextArea1.setText("Impossible d'ouvrir le fichier spécifié");
            }
        }
    }
}//GEN-LAST:event_jButton5ActionPerformed

//==============================================================================
//======================== APPUI SUR LE BOUTON DETAILS =========================
//==============================================================================
private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
    popupDetails = new ProjetCryptoDetails(texte);
    popupDetails.setName(typeTrait);
    popupDetails.setVisible(true);
}//GEN-LAST:event_jButton7ActionPerformed

//==============================================================================
//======================== APPUI SUR LE BOUTON QUITTER =========================
//==============================================================================
private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
    ProjetCryptoApp.getApplication().exit();
}//GEN-LAST:event_jButton9ActionPerformed

//==============================================================================
//=================== APPUI SUR LE BOUTON DECRYPTER FICHIER ====================
//==============================================================================
private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
    jTextField1.setBackground(blanc);
    jTextField2.setBackground(blanc);
    jTextField3.setBackground(blanc);
    jTextField4.setBackground(blanc);
    if (jTextField4.getText().length() == 0) {
        jTextArea1.setText("Aucun fichier à décrypter n'a été spécifié.");
        jTextField4.setBackground(rose);
    }
    else {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Veuillez indiquer le fichier contenant la clé à utiliser pour le décryptage");
        int returnVal = chooser.showOpenDialog(this.getFrame());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            final String cheminFichier = chooser.getSelectedFile().getAbsolutePath();
            popupAvancement = new ProjetCryptoPopup("décryptage");
            popupAvancement.setVisible(true);
            busyIconTimer.start();
            new Thread() {
                public void run() {
                    long timeStart = System.currentTimeMillis();
                    long timeStartouex=0,timeStopouex=0;
                    BufferedInputStream bis,bis2;
                    BufferedOutputStream bos;
                    int octetLu,longueurtot=0;
                    byte donnees[] = new byte[paquet];
                    byte masque[] = new byte[paquet];
                    byte ouex[] = new byte[paquet];
                    boolean afficher = true;            
                    try {
                        bis = new BufferedInputStream(new FileInputStream(jTextField4.getText()));
                        if (nomFic.startsWith("crypte_"))
                            nomFic = nomFic.replaceFirst("crypte_", "");
                        bos = new BufferedOutputStream(new FileOutputStream(jTextField4.getText().substring(0,jTextField4.getText().length()-longFichier)+"decrypte_"+nomFic));
                        longueurtot = bis.available();
                        popupAvancement.AvancementMax(longueurtot);
                        jTextArea1.setText(longueurtot+" octets à décrypter\ntraitement en cours...");
                        bis2 = new BufferedInputStream(new FileInputStream(cheminFichier));
                        timeStartouex = System.currentTimeMillis();
                        while((octetLu=bis.read(donnees,0,paquet)) != -1) {
                            bis2.read(masque,0,octetLu);
                            ouex = CrypterDecrypter(masque, donnees, octetLu);
                            bos.write(ouex,0,octetLu);
                            avancement = avancement+(octetLu);
                            popupAvancement.modifAvancement(avancement);
                            if (ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed() > memusage)
                                memusage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
                        }
                        timeStopouex = System.currentTimeMillis();
                        bis.close();
                        bis2.close();
                        bos.close();
                    }
                    catch(FileNotFoundException ef){
                        jTextArea1.setText("Le fichier spécifié est introuvable.");
                        System.out.println(ef);
                        jTextField4.setBackground(rose);
                        afficher = false;
                    }
                    catch(IOException e){
                        jTextArea1.setText("Erreur lors de la lecture ou l'écriture du fichier.");
                        jTextField4.setBackground(rose);
                        afficher = false;
                    }
                    if (afficher == true) {
                        jButton4.setEnabled(true);
                        jButton5.setEnabled(true);
                        jButton7.setEnabled(true);
                        jButton4.setText("Voir le fichier décrypté");
                        typeTrait = "decryptage";
                        jButton5.setVisible(false);
                        long timeStop = System.currentTimeMillis();
                        texte = FormaterTemps(timeStart,false)+" : Fichier à décrypter : "+jTextField4.getText()+"\n"+FormaterTemps(timeStart,false)+" : Masque : "+cheminFichier+"\n"+FormaterTemps(timeStart,false)+" : Taille du fichier : "+longueurtot+" octets\n"+FormaterTemps(timeStart,false)+" : Lecture/écriture par paquets de : "+paquet+" octets ("+(int)Math.ceil((float)longueurtot/(float)paquet)+" boucle(s) pour ce fichier)\n"+FormaterTemps(timeStopouex,false)+" : Temps de décryptage : "+FormaterTemps(timeStopouex-timeStartouex,true)+" ms\n"+FormaterTemps(timeStop,false)+" : Temps total de traitement : "+FormaterTemps(timeStop-timeStart,true)+" ms\n"+FormaterTemps(timeStop,false)+" : Mémoire max utilisée : "+memusage/1024+" K\n"+FormaterTemps(timeStop,false)+" : Fichier décrypté : "+jTextField4.getText().substring(0,jTextField4.getText().length()-longFichier)+"decrypte_"+nomFic;
                        jTextArea1.setText(jTextArea1.getText()+"\n"+longueurtot+" octets décryptés avec succès !");
                        jTextArea1.setText(jTextArea1.getText()+"\n"+"durée du traitement : "+(FormaterTemps(timeStop-timeStart,true))+" ms");
                    }
                    avancement = 0;
                    popupAvancement.dispose();
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                }
            }.start();
        }
    }
}//GEN-LAST:event_jButton8ActionPerformed

//==============================================================================
//==================== APPUI SUR LE BOUTON GENERER MASQUE ======================
//==============================================================================
private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
    jTextField1.setBackground(blanc);
    jTextField2.setBackground(blanc);
    jTextField3.setBackground(blanc);
    jTextField4.setBackground(blanc);
    if (jTextField2.getText().length() == 0) {
        jTextArea1.setText("Aucune donnée cryptée n'a été saisie !");
        jTextField2.setBackground(rose);
    }
    else if (jTextField2.getText().length() > 2048) {
        jTextArea1.setText("La limite de 2048 caractères autorisés a été dépassée ! ("+jTextField2.getText().length()+")");
        jTextField2.setBackground(rose);
    }
    else {
        long timeStart = System.currentTimeMillis();
        int longueur = jTextField2.getText().length();
        byte masque[] = new byte[longueur];
        int masqueint[] = new int[longueur*8];
        jTextArea1.setText("traitement en cours...");
        masqueint = GenererMasqueAleatoire(longueur,GenererGraine(nbBitsGraine));
        int j=0;
        for (int i=0;i<longueur;i++) {
            masque[i] = (byte)Integer.parseInt(""+masqueint[j]+masqueint[j+1]+masqueint[j+2]+masqueint[j+3]+masqueint[j+4]+masqueint[j+5]+masqueint[j+6]+masqueint[j+7],2);
            j=j+8;
        }
        jTextField3.setText("");
        try {
            jTextField3.setText(new String(masque,"ISO-8859-1"));
        }
        catch (UnsupportedEncodingException e){System.out.println(e);}
        long timeStop = System.currentTimeMillis();
        jTextArea1.setText(jTextArea1.getText()+"\nmasque de "+longueur+" octets généré avec succès !");
        jTextArea1.setText(jTextArea1.getText()+"\ndurée du traitement : "+(FormaterTemps(timeStop-timeStart,true))+" ms");
    }
}//GEN-LAST:event_jButton10ActionPerformed

//==============================================================================
//============ SELECTION 1024 DANS LE MENU "OPTION->PAR PAQUETS DE" ============
//==============================================================================
private void jRadioButtonMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem1ActionPerformed
    jRadioButtonMenuItem1.setSelected(true);
    jRadioButtonMenuItem2.setSelected(false);
    jRadioButtonMenuItem3.setSelected(false);
    paquet = 1024;
}//GEN-LAST:event_jRadioButtonMenuItem1ActionPerformed

//==============================================================================
//============ SELECTION 2048 DANS LE MENU "OPTION->PAR PAQUETS DE" ============
//==============================================================================
private void jRadioButtonMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem2ActionPerformed
    jRadioButtonMenuItem1.setSelected(false);
    jRadioButtonMenuItem2.setSelected(true);
    jRadioButtonMenuItem3.setSelected(false);
    paquet = 2048;
}//GEN-LAST:event_jRadioButtonMenuItem2ActionPerformed

//==============================================================================
//============ SELECTION 4096 DANS LE MENU "OPTION->PAR PAQUETS DE" ============
//==============================================================================
private void jRadioButtonMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem3ActionPerformed
    jRadioButtonMenuItem1.setSelected(false);
    jRadioButtonMenuItem2.setSelected(false);
    jRadioButtonMenuItem3.setSelected(true);
    paquet = 4096;
}//GEN-LAST:event_jRadioButtonMenuItem3ActionPerformed

//==============================================================================
//============= SELECTION LFSR 16 BITS DANS LE MENU "OPTION->LFSR" =============
//==============================================================================
private void jRadioButtonMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem4ActionPerformed
    jRadioButtonMenuItem4.setSelected(true);
    jRadioButtonMenuItem5.setSelected(false);
    jRadioButtonMenuItem6.setSelected(false);
    nbBitsGraine = 16;
    lfsr[0] = 5; lfsr[1] = 6; lfsr[2] = 15; lfsr[3] = 16;
    for (int i=4;i<lfsr.length;i++)
            lfsr[i] = 0;
}//GEN-LAST:event_jRadioButtonMenuItem4ActionPerformed

//==============================================================================
//=========== SELECTION  4 LFSR 128 BITS DANS LE MENU "OPTION->LFSR" ===========
//==============================================================================
private void jRadioButtonMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem5ActionPerformed
    jRadioButtonMenuItem4.setSelected(false);
    jRadioButtonMenuItem5.setSelected(true);
    jRadioButtonMenuItem6.setSelected(false);
    nbBitsGraine = 128;
    lfsr[0] = 5; lfsr[1] = 13; lfsr[2] = 17; lfsr[3] = 25;
    lfsr[4] = 7; lfsr[5] = 15; lfsr[6] = 19; lfsr[7] = 31;
    lfsr[8] = 5; lfsr[9] = 9; lfsr[10] = 29; lfsr[11] = 33;
    lfsr[12] = 3; lfsr[13] = 11; lfsr[14] = 35; lfsr[15] = 39;
}//GEN-LAST:event_jRadioButtonMenuItem5ActionPerformed

//==============================================================================
//============ SELECTION "TOUT REINITIALISER" DANS LE MENU "OPTION" ============
//==============================================================================
private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
    jTextArea1.setText("");
    jTextField1.setText("");
    jTextField2.setText("");
    jTextField3.setText("");
    jTextField4.setText("");
    jTextField1.setBackground(blanc);
    jTextField2.setBackground(blanc);
    jTextField3.setBackground(blanc);
    jTextField4.setBackground(blanc);
    jRadioButtonMenuItem1.setSelected(false);
    jRadioButtonMenuItem2.setSelected(true);
    jRadioButtonMenuItem3.setSelected(false);
    jRadioButtonMenuItem4.setSelected(true);
    jRadioButtonMenuItem5.setSelected(false);
    jRadioButtonMenuItem6.setSelected(false);
    jRadioButtonMenuItem7.setSelected(true);
    jRadioButtonMenuItem8.setSelected(false);
    jRadioButtonMenuItem9.setSelected(true);
    jRadioButtonMenuItem10.setSelected(false);
    jButton4.setText("Voir le fichier crypté");
    jButton4.setEnabled(false);
    jButton5.setVisible(true);
    jButton5.setEnabled(false);
    jButton7.setEnabled(false);
    paquet = 2048;
    nbBitsGraine = 16;
    grainealeatoire = true;
    ecriregraine = true;
    lfsr[0] = 5; lfsr[1] = 6; lfsr[2] = 15; lfsr[3] = 16;
    for (int i=4;i<lfsr.length;i++)
            lfsr[i] = 0;
    texte="";
    nomFic = "";
    typeTrait = "";
    longFichier = 0;
    avancement = 0; 
    memusage = 0;
}//GEN-LAST:event_jMenuItem1ActionPerformed

//==============================================================================
//============ SELECTION LFSR 128 BITS DANS LE MENU "OPTION->LFSR" =============
//==============================================================================
private void jRadioButtonMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem6ActionPerformed
    jRadioButtonMenuItem4.setSelected(false);
    jRadioButtonMenuItem5.setSelected(false);
    jRadioButtonMenuItem6.setSelected(true);
    nbBitsGraine = 128;
    lfsr[0] = 5; lfsr[1] = 13; lfsr[2] = 17; lfsr[3] = 25;
    lfsr[4] = 32; lfsr[5] = 40; lfsr[6] = 44; lfsr[7] = 56;
    lfsr[8] = 61; lfsr[9] = 65; lfsr[10] = 85; lfsr[11] = 89;
    lfsr[12] = 91; lfsr[13] = 100; lfsr[14] = 124; lfsr[15] = 128;
}//GEN-LAST:event_jRadioButtonMenuItem6ActionPerformed

//==============================================================================
//============ SELECTION 'CRYPTER' DANS LE MENU "FICHIER->CRYPTER" =============
//==============================================================================
private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    JFileChooser chooser = new JFileChooser();
    chooser.setDialogTitle("Choisissez le fichier à crypter");
    int returnVal = chooser.showOpenDialog(this.getFrame());
    if(returnVal == JFileChooser.APPROVE_OPTION) {
        jTextField4.setText(chooser.getSelectedFile().getAbsolutePath());
        longFichier = chooser.getSelectedFile().getName().length();
        nomFic = chooser.getSelectedFile().getName();
    }
    jButton6ActionPerformed(evt);
}//GEN-LAST:event_jMenuItem2ActionPerformed

//==============================================================================
//========== SELECTION 'DECRYPTER' DANS LE MENU "FICHIER->DECRYPTER" ===========
//==============================================================================
private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
    jButton8ActionPerformed(evt);
}//GEN-LAST:event_jMenuItem3ActionPerformed

//==============================================================================
//============ SELECTION 'ALEATOIRE' DANS LE MENU "OPTION->GRAINE" =============
//==============================================================================
private void jRadioButtonMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem7ActionPerformed
    jRadioButtonMenuItem7.setSelected(true);
    jRadioButtonMenuItem8.setSelected(false);
    grainealeatoire = true;
}//GEN-LAST:event_jRadioButtonMenuItem7ActionPerformed

//==============================================================================
//============== SELECTION 'FIXEE' DANS LE MENU "OPTION->GRAINE" ===============
//==============================================================================
private void jRadioButtonMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem8ActionPerformed
    jRadioButtonMenuItem7.setSelected(false);
    jRadioButtonMenuItem8.setSelected(true);
    grainealeatoire = false;
}//GEN-LAST:event_jRadioButtonMenuItem8ActionPerformed

//==============================================================================
//============== SELECTION 'ECRIRE' DANS LE MENU "OPTION->GRAINE" ==============
//==============================================================================
private void jRadioButtonMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem9ActionPerformed
    jRadioButtonMenuItem9.setSelected(true);
    jRadioButtonMenuItem10.setSelected(false);
    ecriregraine = true;
}//GEN-LAST:event_jRadioButtonMenuItem9ActionPerformed

//==============================================================================
//========== SELECTION 'NE PAS ECRIRE' DANS LE MENU "OPTION->GRAINE" ===========
//==============================================================================
private void jRadioButtonMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem10ActionPerformed
    jRadioButtonMenuItem9.setSelected(false);
    jRadioButtonMenuItem10.setSelected(true);
    ecriregraine = false;
}//GEN-LAST:event_jRadioButtonMenuItem10ActionPerformed

private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
    //on vérifie que la classe Desktop soit bien supportée
    if ( Desktop.isDesktopSupported() ) {
        //on récupère l'instance du desktop
        Desktop desktop = Desktop.getDesktop();
        //on vérifie que la fonction open soit bien supportée
        if (desktop.isSupported(Desktop.Action.OPEN)) {
            try {
                //on lance l'application associé au fichier pour l'ouvrir
                // disabled for sharing, show message instead
                // desktop.open(new File("src/projetcrypto/resources/Rapport Projet Cryptographie.pdf"));
                jTextArea1.setText("Cette fonctionnalité à été retirée pour le partage de ce projet");
            }
            catch (IOException e){
                System.out.println(e);
                jTextArea1.setText("Impossible d'ouvrir le fichier spécifié");
            }
            catch (IllegalArgumentException e){
                System.out.println(e);
                jTextArea1.setText("Impossible d'ouvrir le fichier spécifié");
            }
        }
    }
}//GEN-LAST:event_jMenuItem4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem10;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem3;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem4;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem5;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem6;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem7;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem8;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem ouvrirMenuItem1;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    
    private String texte = "";
    private String typeTrait = "";
    private String nomFic="";
    
    private int busyIconIndex = 0;
    private int longFichier = 0;
    private int avancement = 0; 
    private int paquet = 2048;
    private int nbBitsGraine = 16;
    private long memusage = 0;
    
    private int lfsr[] = new int[128];
    private boolean grainealeatoire = true;
    private boolean ecriregraine = true;

    private JDialog aboutBox;
    private ProjetCryptoPopup popupAvancement;
    private JFrame popupDetails;
    
    Color rose = new Color(246,233,234);
    Color blanc = new Color(255,255,255);
}
