/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package forms;

import controller.Controller;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import model.Glumac;
import model.Korisnik;
import tableModel.TableModelGlumci;

/**
 *
 * @author Ana
 */
public class ShowActors extends javax.swing.JDialog {
     private Locale currentLocale;
    private ResourceBundle messages;
    Korisnik korisnik;
    List<Glumac> glumciLista=Controller.getInstance().vratiListuGlumaca();
    /**
     * Creates new form ShowActors
     */
    public ShowActors(java.awt.Frame parent, boolean modal,Korisnik k, Locale currentLocale) {
        super(parent, modal);
        initComponents();
    
         jTextField2.setText(k.getEmail());
        jTextField2.setEnabled(false);
        TableModelGlumci tmg=new TableModelGlumci(glumciLista);
        jTable1.setModel(tmg);
         korisnik = k;
         this.currentLocale = currentLocale;
             loadLanguage();
        updateTexts();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonAzuriraj = new javax.swing.JButton();
        jButtondodaj = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButtonAzuriraj.setText("Azuriraj");
        jButtonAzuriraj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAzurirajActionPerformed(evt);
            }
        });

        jButtondodaj.setText("Dodaj");
        jButtondodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtondodajActionPerformed(evt);
            }
        });

        jLabel1.setText("Glumci:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(274, 274, 274)
                        .addComponent(jButtondodaj)
                        .addGap(115, 115, 115)
                        .addComponent(jButtonAzuriraj))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(199, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(46, 46, 46)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAzuriraj)
                    .addComponent(jButtondodaj))
                .addContainerGap(89, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtondodajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtondodajActionPerformed
        AddUpdateActor aua=new AddUpdateActor(this, true, currentLocale, null);
        aua.setVisible(true);
        azurirajTabelu();
    }//GEN-LAST:event_jButtondodajActionPerformed

    private void jButtonAzurirajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAzurirajActionPerformed
        int selektovano=jTable1.getSelectedRow();
        if(selektovano==-1){
            switch (currentLocale.getLanguage()) {
                    case "srpski" ->
                       JOptionPane.showMessageDialog(this,"Morate da oznacite", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    case "engleski" ->
                       JOptionPane.showMessageDialog(this, "You must select a field", "ERROR", JOptionPane.ERROR_MESSAGE);
                    case "nemacki" ->
                        JOptionPane.showMessageDialog(this, "Sie müssen markieren", "FEHLER", JOptionPane.ERROR_MESSAGE);
                }
            
        }else{
            Glumac g=glumciLista.get(selektovano);
        AddUpdateActor aua=new AddUpdateActor(this, true, currentLocale, g);
        aua.setVisible(true);
        azurirajTabelu();
        }
    }//GEN-LAST:event_jButtonAzurirajActionPerformed

  
    
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAzuriraj;
    private javax.swing.JButton jButtondodaj;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
    private void loadLanguage() {
        try {
            messages = ResourceBundle.getBundle("prevod.messages", currentLocale);
        } catch (Exception e) {
            System.err.println("Greska" + e.getMessage());
        }
    }

    private void updateTexts() {
        jLabel1.setText(messages.getString("jLabelGlumci.text"));
        jButtondodaj.setText(messages.getString("jMenuItem1.text"));
        jButtonAzuriraj.setText(messages.getString("jButtonAzuriraj.text"));
        
    }

    private void azurirajTabelu() {
        List<Glumac> glLista=Controller.getInstance().vratiListuGlumaca();
        TableModelGlumci tmg=new TableModelGlumci(glLista);
        jTable1.setModel(tmg);
    }
}


