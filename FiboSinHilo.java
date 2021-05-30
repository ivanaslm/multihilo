package PCM;

import java.awt.BorderLayout;
import javax.swing.*;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FiboSinHilo {

    String comboBoxItems[] = {"A", "B"};
    JTextField tfNumero = new JTextField("", 3);
    JButton btnFib = new JButton("Obtener Fibonacci");
    JLabel lblresultado = new JLabel("Fibonacci :");
    JComboBox jcombo = new JComboBox(comboBoxItems);
    Escuchador escuchador = new Escuchador();

    public void agregarComponentePane(Container panel) {
        JPanel jpanel = new JPanel();
        jpanel.setLayout(new java.awt.GridLayout(3, 2, 5, 5));
        jpanel.add(new JLabel("Introduce número entero"));
        jpanel.add(tfNumero);
        jpanel.add(jcombo);
        jpanel.add(btnFib);
        jpanel.add(lblresultado);

        btnFib.addActionListener(escuchador);
        jcombo.addActionListener(escuchador);

        panel.add(jpanel, BorderLayout.CENTER);
    }//Constructor

    private static void createAndShowGUI() {
//Crear y establecer propiedades de la ventana
        JFrame frame = new JFrame("");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FiboSinHilo newContentPane = new FiboSinHilo();
//Establecer panel
        newContentPane.agregarComponentePane(frame.getContentPane());

//Mostrar ventana
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private static void ventana() {
        JOptionPane.showMessageDialog(null, "hola");
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();

            }
        });
    }//main

    class Escuchador implements ActionListener {

        Thread hilo1, hilo2;

        @Override
        public void actionPerformed(ActionEvent e) {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    long numfib;
                    numfib = Long.parseLong(tfNumero.getText());
                    Runnable r = new FiboConHilo(numfib);
                    hilo1 = new Thread(r);
                    hilo1.start();

                }
            });

//            long numfib;
//            numfib = Long.parseLong(tfNumero.getText());
//
//            //new FiboConHilo(numfib).start();
//            Runnable r = new FiboConHilo(numfib);
//            hilo1 = new Thread(r);
//            hilo1.start();
//
//            System.out.println();
            //JOptionPane.showMessageDialog(null, "Item seleccionado: " + jcombo.getSelectedItem());
            /*if (e.getSource() == btnFib) {
                long numfib;
                numfib = Long.parseLong(tfNumero.getText());
                //lblresultado.setText("Fibonacci " + " de: " + tfNumero.getText() + " es: " + fibonacci(numfib));

                try {
                    
                    
                hilo1 = new FiboConHilo(numfib);
                    hilo1.start();

                    Thread.sleep(1000);

                     hilo2 = new FiboConHilo(numfib);

                    hilo2.start();

                    Thread.sleep(1000);

                } catch (InterruptedException ex) {
                    System.out.println("Errors: " + ex.getMessage());
                }

            } else if (e.getSource() == jcombo) {

                JOptionPane.showMessageDialog(null, "Item seleccionado: " + jcombo.getSelectedItem());
            }*/
        }//actionPerformed

    }//Escuchador

}//CalcularFibonacci

//Hilo del Fibonacci
class FiboConHilo implements Runnable {

    long numero;

    FiboConHilo(long numero) {

        this.numero = numero;

    }

    public void run() {

         javax.swing.SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                   JOptionPane.showMessageDialog(null, "Fibonacci " + " de: " + numero + " es: " + fibonacci(numero));

                }
            });

    }

// Declaración recursiva del método fibonacci
    public long fibonacci(long number) {
        if ((number == 0) || (number == 1)) // base cases
        {
            return number;
        } else // Paso recursivo
        {
            return fibonacci(number - 1) + fibonacci(number - 2);
        }
    }
}
