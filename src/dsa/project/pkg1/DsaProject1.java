/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dsa.project.pkg1;
import edd.*;

import interfaz.InterfazPrincipal;

/**
 *
 * @author manch
 */
public class DsaProject1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfazPrincipal().setVisible(true);
            }
        });
    }
    
}
