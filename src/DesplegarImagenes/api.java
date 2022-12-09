package DesplegarImagenes;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class api extends javax.swing.JFrame {
    private javax.swing.JMenuItem guardarmen;
    private javax.swing.JMenuItem guardmen1;
    private javax.swing.JMenuItem doubt;
    private javax.swing.JMenuItem contents;
    private javax.swing.JMenuItem ctrl;
    private javax.swing.JMenuItem c;
    private javax.swing.JMenuItem salir;
    private javax.swing.JMenu menu;
    private javax.swing.JMenu ayuda;
    private javax.swing.JMenuBar men;
    private javax.swing.JMenuItem abrirmen;
    private javax.swing.JMenuItem copymen;
    private javax.swing.JMenuItem borrarlo;
    private JDesktopPane desktopPane;
    private javax.swing.JMenu editarlo;
    private javax.swing.JMenuItem escala;
    private javax.swing.JMenuItem rotacion;
    public api() {
        initComponents();
        this.setSize(1200, 1000);
        setLocationRelativeTo(null);
        setTitle("despliegue De imagenes SVG");

    }
    private void initComponents() {
        desktopPane = new JDesktopPane();
        men = new javax.swing.JMenuBar();
        menu = new javax.swing.JMenu();
        abrirmen = new javax.swing.JMenuItem();
        guardmen1 = new javax.swing.JMenuItem();
        guardarmen = new javax.swing.JMenuItem();
        salir = new javax.swing.JMenuItem();
        editarlo = new javax.swing.JMenu();
        c = new javax.swing.JMenuItem();
        ctrl = new javax.swing.JMenuItem();
        copymen = new javax.swing.JMenuItem();
        borrarlo = new javax.swing.JMenuItem();
        escala = new javax.swing.JMenuItem();
        rotacion = new javax.swing.JMenuItem();
        ayuda = new javax.swing.JMenu();
        contents = new javax.swing.JMenuItem();
        doubt = new javax.swing.JMenuItem();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        menu.setMnemonic('f');
        menu.setText("Archivo");

        abrirmen.setMnemonic('o');
        abrirmen.setText("Abrir");
        abrirmen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        menu.add(abrirmen);

        guardmen1.setMnemonic('s');
        guardmen1.setText("guardar");
        menu.add(guardmen1);

        guardarmen.setMnemonic('a');
        guardarmen.setText("guardar como: ");
        guardarmen.setDisplayedMnemonicIndex(5);
        menu.add(guardarmen);

        salir.setMnemonic('x');
        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        menu.add(salir);
        men.add(menu);
        editarlo.setMnemonic('e');
        editarlo.setText("edit");
        c.setMnemonic('t');
        c.setText("cut");
        editarlo.add(c);
        ctrl.setMnemonic('y');
        ctrl.setText("copy");
        editarlo.add(ctrl);
        copymen.setMnemonic('p');
        copymen.setText("paste");
        editarlo.add(copymen);
        borrarlo.setMnemonic('d');
        borrarlo.setText("delete");
        editarlo.add(borrarlo);
        men.add(editarlo);
        ayuda.setMnemonic('h');
        ayuda.setText("clickhelp");
        contents.setMnemonic('c');
        contents.setText("contents");
        ayuda.add(contents);
        doubt.setMnemonic('a');
        doubt.setText("about");
        ayuda.add(doubt);
        men.add(ayuda);
        setJMenuBar(men);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );
        pack();
    }

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        final JFileChooser fc = new JFileChooser();
        String userDir = System.getProperty("user.dir");
        fc.setCurrentDirectory(new File(userDir) );
        fc.setDialogTitle("select SVG img");
        fc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos SVG", "svg");
        fc.addChoosableFileFilter(filter);
        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            System.out.println(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;
            try {
                dBuilder = dbFactory.newDocumentBuilder();
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(api.class.getName()).log(Level.SEVERE, null, ex);
            }
            Document doc = null;
            try {
                doc = dBuilder.parse(file);
                doc.getDocumentElement().normalize();

            } catch (SAXException ex) {
                Logger.getLogger(api.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(api.class.getName()).log(Level.SEVERE, null, ex);
            }
            JInternalFrame intFrame;
            intFrame = new JInternalFrame(file.getName(), true, true, true, true);
           SVG svg = new SVG(doc);
            JScrollPane scrollPane = new JScrollPane(svg);

            intFrame.getContentPane().add(scrollPane);
            intFrame.pack();
            desktopPane.add(intFrame);
            intFrame.setVisible(true);

        } else {

        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(api.class.getName()).log(Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            Logger.getLogger(api.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(api.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(api.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new api().setVisible(true);
            }
        }
        );
    }
}