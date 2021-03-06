/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Notepad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import static java.lang.ProcessBuilder.Redirect.Type.APPEND;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author kotoromo
 */
public class Principal extends javax.swing.JFrame {

    private String fileName = "New File", uneditedText = "";
    private String pathToFile;
    private String defaultPath = "/home/kotoromo/";

    /**
     * Creates new form Principal
     */
    public Principal() {

        initComponents();
        this.pathToFile = defaultPath;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FileTab = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        Texto = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        Menu = new javax.swing.JMenu();
        NewItem = new javax.swing.JMenuItem();
        OpenItem = new javax.swing.JMenuItem();
        SaveItem = new javax.swing.JMenuItem();
        SaveAsItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Texto.setColumns(20);
        Texto.setRows(5);
        Texto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextoKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(Texto);

        FileTab.addTab("New File", jScrollPane1);

        Menu.setText("File");

        NewItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        NewItem.setText("New");
        NewItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewItemActionPerformed(evt);
            }
        });
        Menu.add(NewItem);

        OpenItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        OpenItem.setText("Open");
        OpenItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenItemActionPerformed(evt);
            }
        });
        Menu.add(OpenItem);

        SaveItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        SaveItem.setText("Save");
        SaveItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveItemActionPerformed(evt);
            }
        });
        Menu.add(SaveItem);

        SaveAsItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        SaveAsItem.setText("Save As...");
        SaveAsItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveAsItemActionPerformed(evt);
            }
        });
        Menu.add(SaveAsItem);

        jMenuBar1.add(Menu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(FileTab, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(FileTab, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OpenItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenItemActionPerformed
        // TODO add your handling code here:
        
        /* Limpiamos el TextArea */
        this.Texto.setText("");
        
        /* Creamos una instancia de JFileChooser */
        JFileChooser chooser = new JFileChooser();
        
        /* Filtro porque crashea mi programa si uso imagenes :( */
        chooser.setFileFilter(new FileNameExtensionFilter("Plain Text/Source Code", "txt", "c", "java", "cpp"));
        
        /* La opcion que seleccionemos del JFileChooser nos devuelve un entero */
        int selection = chooser.showOpenDialog(this);
        
        /* La seleccion está dada por la constante APPROVE_OPTION */
        if(selection == JFileChooser.APPROVE_OPTION){
            try {
                /* String donde almacenaremos lo obtenido del flujo */
                String readText;
                
                this.fileName = chooser.getSelectedFile().getName();
                this.pathToFile = chooser.getSelectedFile().getPath();
                
                /* Abrimos el flujo con un FileReader. El FILE va a ser el archivo seleccionado en el JFileChooser */
                BufferedReader br = new BufferedReader(new FileReader(chooser.getSelectedFile()));
                
                /* Le añadimos el asterisco para que nos lance el JOptionPane*/
                //this.FileTab.setTitleAt(0, "*"+chooser.getSelectedFile().getName());
                
                /* Renombramos el tab al nombre del archivo: */
                this.FileTab.setTitleAt(0, fileName);
                
                /* Mientras la linea leida en el flujo no sea un puntero a nulo...*/
                while((readText =  br.readLine()) != null){
                    /* Vamos a concatenar el contenido de readText en nuestro TextArea */
                    this.Texto.append(readText + "\n");
                }
                
                br.close();
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Something went wrong!\n"+ex, "Error", JOptionPane.ERROR_MESSAGE);
            }
            this.uneditedText = this.Texto.getText();
        }else if(selection == JFileChooser.CANCEL_OPTION){
            /* Esto es una ñerada pero no se me ocurrio mejor :9*/
            Texto.setText(uneditedText);
        }
        
        
    }//GEN-LAST:event_OpenItemActionPerformed

    private void NewItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewItemActionPerformed
        // TODO add your handling code here:
        
        if(this.FileTab.getTitleAt(0).contains("*")){
            String dialogText[] = {"Do you wish to create a new file without saving first?", "Warning!"};
            int n = JOptionPane.showConfirmDialog(this, dialogText[0], dialogText[1], JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
            if(n == (JOptionPane.YES_OPTION))
                this.Texto.setText("");
        }else{
            this.Texto.setText("");
            this.FileTab.setName("New File");
        }
        
        this.pathToFile = defaultPath;
    }//GEN-LAST:event_NewItemActionPerformed

    private void TextoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextoKeyTyped
        // TODO add your handling code here:
        /* Vamos a hacer que siempre esté a la escucha */
        new Runnable() {
            @Override
            public void run() {
                if(!uneditedText.equals(Texto.getText()))
                    FileTab.setTitleAt(0,"*"+fileName);
            }
        }.run();
    }//GEN-LAST:event_TextoKeyTyped

    private void SaveAsItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveAsItemActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        int option = chooser.showSaveDialog(this);
        if(option == JFileChooser.APPROVE_OPTION){
            try{
                BufferedWriter bw = new BufferedWriter(new FileWriter(chooser.getSelectedFile().getPath()));
                bw.write(this.Texto.getText());
                bw.close();
                fileName = chooser.getSelectedFile().getName();
                pathToFile = chooser.getSelectedFile().getPath();
            }catch(Exception ex){
                System.out.println(ex);
                JOptionPane.showMessageDialog(this, "Something went wrong!\n"+ex, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        this.FileTab.setTitleAt(0, fileName);
    }//GEN-LAST:event_SaveAsItemActionPerformed

    private void SaveItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveItemActionPerformed
        // TODO add your handling code here:
        this.FileTab.setTitleAt(0, fileName+".txt");
        try{
            System.out.println(pathToFile+fileName+".txt");
            File newFile = new File(pathToFile+fileName+".txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));
            bw.write(this.Texto.getText());
            bw.close();
            this.uneditedText = this.Texto.getText();
        }catch(Exception ex){
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "Something went wrong!\n"+ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_SaveItemActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane FileTab;
    private javax.swing.JMenu Menu;
    private javax.swing.JMenuItem NewItem;
    private javax.swing.JMenuItem OpenItem;
    private javax.swing.JMenuItem SaveAsItem;
    private javax.swing.JMenuItem SaveItem;
    private javax.swing.JTextArea Texto;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
