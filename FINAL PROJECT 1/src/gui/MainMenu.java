package src.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Main Menu");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1, 15, 15)); 

        getContentPane().setBackground(new Color(210, 210, 210));

        Font buttonFont = new Font("Arial", Font.PLAIN, 14);
        Color buttonColor = new Color(50, 50, 50);
        Color buttonHoverColor = new Color(80, 80, 80);

        JButton mahasiswaButton = createButton("Mahasiswa", buttonFont, buttonColor, buttonHoverColor, new MahasiswaGUI());
        JButton dosenButton = createButton("Dosen", buttonFont, buttonColor, buttonHoverColor, new DosenGUI());
        JButton mataKuliahButton = createButton("Mata Kuliah", buttonFont, buttonColor, buttonHoverColor, new MataKuliahGUI());
        JButton perkuliahanButton = createButton("Perkuliahan", buttonFont, buttonColor, buttonHoverColor, new PerkuliahanGUI());

        add(mahasiswaButton);
        add(dosenButton);
        add(mataKuliahButton);
        add(perkuliahanButton);
    }

    private JButton createButton(String text, Font font, Color color, Color hoverColor, final JFrame frame) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(true);
            }
        });
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }
}