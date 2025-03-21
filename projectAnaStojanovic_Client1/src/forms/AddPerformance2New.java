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
import model.GlumacUloga;
import tableModel.TableModelGlumacUloga;
import tableModel.TableModelGlumci;

/**
 *
 * @author Ana
 */
public class AddPerformance2New extends javax.swing.JDialog {
    List<GlumacUloga> listaGlumacUloga;
    List<Glumac> listaGlumaca=Controller.getInstance().vratiListuGlumaca();
     private Locale currentLocale;
    private ResourceBundle messages;
    /**
     * Creates new form AddPerformance2New
     */
    public AddPerformance2New(AddPerformance parent, boolean modal,List<GlumacUloga> lista, Locale currentLocale) {
        super(parent, modal);
        initComponents();
         TableModelGlumci tmg=new TableModelGlumci(listaGlumaca);
        jTable1.setModel(tmg);
        listaGlumacUloga=lista;
        
        this.currentLocale = currentLocale;
        
        loadLanguage();
        updateTexts();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabelUloga = new javax.swing.JLabel();
        jLabel2glum = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextFieldUloga = new javax.swing.JTextField();

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

        jLabelUloga.setText("Uloga:");

        jLabel2glum.setText("Glumci:");

        jButton1.setText("dodaj");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2glum)
                            .addComponent(jLabelUloga, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldUloga, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(337, 337, 337)
                        .addComponent(jButton1)))
                .addContainerGap(183, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUloga)
                    .addComponent(jTextFieldUloga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2glum))
                .addGap(35, 35, 35)
                .addComponent(jButton1)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String uloga=jTextFieldUloga.getText();
         if(uloga.trim().isEmpty()){
             switch (currentLocale.getLanguage()) {
                    case "srpski" ->
                      JOptionPane.showMessageDialog(this,"Morate uneti naziv uloge", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    case "engleski" ->
                       JOptionPane.showMessageDialog(this, "You must enter the name of the role", "ERROR", JOptionPane.ERROR_MESSAGE);
                    case "nemacki" ->
                        JOptionPane.showMessageDialog(this, "Sie müssen den Namen der Rolle eingeben", "FEHLER", JOptionPane.ERROR_MESSAGE);
            }
              
            return;
          }
          if(jTable1.getSelectedRow()==-1){
              switch (currentLocale.getLanguage()) {
                    case "srpski" ->
                      JOptionPane.showMessageDialog(this,"Morate selektovati", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    case "engleski" ->
                       JOptionPane.showMessageDialog(this, ": You must select", "ERROR", JOptionPane.ERROR_MESSAGE);
                    case "nemacki" ->
                        JOptionPane.showMessageDialog(this, "Sie müssen auswählen", "FEHLER", JOptionPane.ERROR_MESSAGE);
            }
            
            return;
        }
         
          int index=jTable1.getSelectedRow();
          Glumac g=listaGlumaca.get(index);
          listaGlumacUloga.add(new GlumacUloga(uloga, g));
          switch (currentLocale.getLanguage()) {
                    case "srpski" ->
                          JOptionPane.showMessageDialog(this,"Uspesno dodato", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                    case "engleski" ->
                       JOptionPane.showMessageDialog(this, "Successfully added", "Successfully", JOptionPane.ERROR_MESSAGE);
                    case "nemacki" ->
                        JOptionPane.showMessageDialog(this, "Erfolgreich hinzugefügt", "Erfolgreich", JOptionPane.ERROR_MESSAGE);
                }
         
           this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2glum;
    private javax.swing.JLabel jLabelUloga;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldUloga;
    // End of variables declaration//GEN-END:variables

    private void loadLanguage() {
        try {
            messages = ResourceBundle.getBundle("prevod.messages", currentLocale);
        } catch (Exception e) {
            System.err.println("Greska" + e.getMessage());
        }
    }

    private void updateTexts() {
        jButton1.setText(messages.getString("jMenuItem1.text"));
        jLabel2glum.setText(messages.getString("jLabelGlumci.text"));
        jLabelUloga.setText(messages.getString("jLabelUloga.text"));
    }

}
