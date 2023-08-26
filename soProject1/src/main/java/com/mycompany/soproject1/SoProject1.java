/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.soproject1;

import Frontend.Principal;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 *
 * @author Caili
 * @author alex
 */
public class SoProject1 {

    /**
     * Metodo main del programa
     * @param args 
     */
    public static void main(String[] args) throws Exception {
        presentarLogo();
        Principal p = new Principal();
        p.show();
       
         
    }
    
    /**
     * Funcion para presentar el logo del compilador
     * @throws InterruptedException 
     */
    private static void presentarLogo() throws InterruptedException {
        JFrame frame = new JFrame("Logo Window");
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        
        ImageIcon logoIcon = new ImageIcon("src/main/java/Frontend/assets/logo.png");
        JLabel logoLabel = new JLabel(logoIcon);

        // Agrega el JLabel al contenido de la ventana
        frame.getContentPane().add(logoLabel, BorderLayout.CENTER);

        // Muestra la ventana
        frame.setVisible(true);
        Thread.sleep(2000);
        frame.dispose();
    }
    
    
}
