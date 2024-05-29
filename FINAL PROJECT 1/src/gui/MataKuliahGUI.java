package src.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.dao.MataKuliahDAO;
import src.model.MataKuliah;

public class MataKuliahGUI extends JFrame {
    private JTextField kodeMKField, namaMKField, sksField;
    private JButton addButton, updateButton, deleteButton, searchButton;
    private MataKuliahDAO mataKuliahDAO;

    public MataKuliahGUI() {
        mataKuliahDAO = new MataKuliahDAO();
        setTitle("Mata Kuliah Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 240, 240));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Define the button style
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);
        Color buttonColor = new Color(50, 50, 50);
        Color buttonHoverColor = new Color(80, 80, 80);

        // Create input fields
        JLabel kodeMKLabel = new JLabel("Kode MK:");
        kodeMKField = new JTextField(20);
        JLabel namaMKLabel = new JLabel("Nama MK:");
        namaMKField = new JTextField(20);
        JLabel sksLabel = new JLabel("SKS:");
        sksField = new JTextField(20);

        // Add input fields to the frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(kodeMKLabel, gbc);
        gbc.gridx = 1;
        add(kodeMKField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(namaMKLabel, gbc);
        gbc.gridx = 1;
        add(namaMKField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(sksLabel, gbc);
        gbc.gridx = 1;
        add(sksField, gbc);

        // Create buttons with modern and minimalist style
        addButton = createButton("Add", buttonFont, buttonColor, buttonHoverColor, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MataKuliah mk = new MataKuliah();
                mk.setKodeMK(kodeMKField.getText());
                mk.setNamaMK(namaMKField.getText());
                mk.setSks(Integer.parseInt(sksField.getText()));
                mataKuliahDAO.addMataKuliah(mk);
            }
        });

        updateButton = createButton("Update", buttonFont, buttonColor, buttonHoverColor, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MataKuliah mk = new MataKuliah();
                mk.setKodeMK(kodeMKField.getText());
                mk.setNamaMK(namaMKField.getText());
                mk.setSks(Integer.parseInt(sksField.getText()));
                mataKuliahDAO.updateMataKuliah(mk);
            }
        });

        deleteButton = createButton("Delete", buttonFont, buttonColor, buttonHoverColor, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kodeMK = kodeMKField.getText();
                mataKuliahDAO.deleteMataKuliah(kodeMK);
            }
        });

        searchButton = createButton("Search", buttonFont, buttonColor, buttonHoverColor, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kodeMK = kodeMKField.getText();
                MataKuliah mk = mataKuliahDAO.getMataKuliah(kodeMK);
                if (mk != null) {
                    namaMKField.setText(mk.getNamaMK());
                    sksField.setText(String.valueOf(mk.getSks()));
                }
            }
        });

        // Add buttons to the frame
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(addButton, gbc);
        gbc.gridx = 2;
        add(updateButton, gbc);
        gbc.gridx = 3;
        add(deleteButton, gbc);
        gbc.gridx = 4;
        add(searchButton, gbc);
    }

    private JButton createButton(String text, Font font, Color color, Color hoverColor, ActionListener listener) {
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
        button.addActionListener(listener);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MataKuliahGUI().setVisible(true);
            }
        });
    }
}