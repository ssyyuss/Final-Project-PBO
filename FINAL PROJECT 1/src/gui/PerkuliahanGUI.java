package src.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.dao.PerkuliahanDAO;
import src.model.Perkuliahan;

public class PerkuliahanGUI extends JFrame {
    private JTextField nimField, kodeMKField, nipField, nilaiField;
    private JButton addButton, updateButton, deleteButton, searchButton;
    private PerkuliahanDAO perkuliahanDAO;

    public PerkuliahanGUI() {
        perkuliahanDAO = new PerkuliahanDAO();
        setTitle("Perkuliahan Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel nimLabel = new JLabel("NIM:");
        nimLabel.setBounds(20, 20, 80, 25);
        add(nimLabel);
        nimField = new JTextField();
        nimField.setBounds(100, 20, 160, 25);
        add(nimField);

        JLabel kodeMKLabel = new JLabel("Kode MK:");
        kodeMKLabel.setBounds(20, 50, 80, 25);
        add(kodeMKLabel);
        kodeMKField = new JTextField();
        kodeMKField.setBounds(100, 50, 160, 25);
        add(kodeMKField);

        JLabel nipLabel = new JLabel("NIP:");
        nipLabel.setBounds(20, 80, 80, 25);
        add(nipLabel);
        nipField = new JTextField();
        nipField.setBounds(100, 80, 160, 25);
        add(nipField);

        JLabel nilaiLabel = new JLabel("Nilai:");
        nilaiLabel.setBounds(20, 110, 80, 25);
        add(nilaiLabel);
        nilaiField = new JTextField();
        nilaiField.setBounds(100, 110, 160, 25);
        add(nilaiField);

        addButton = createModernButton("Add");
        addButton.setBounds(20, 150, 80, 25);
        add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Perkuliahan p = new Perkuliahan();
                    p.setNim(Integer.parseInt(nimField.getText()));
                    p.setKodeMK(kodeMKField.getText());
                    p.setNip(Integer.parseInt(nipField.getText()));
                    p.setNilai(nilaiField.getText());
                    perkuliahanDAO.addPerkuliahan(p);
                    JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Input tidak valid, periksa kembali data yang dimasukkan!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        updateButton = createModernButton("Update");
        updateButton.setBounds(110, 150, 80, 25);
        add(updateButton);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Perkuliahan p = new Perkuliahan();
                    p.setNim(Integer.parseInt(nimField.getText()));
                    p.setKodeMK(kodeMKField.getText());
                    p.setNip(Integer.parseInt(nipField.getText()));
                    p.setNilai(nilaiField.getText());
                    perkuliahanDAO.updatePerkuliahan(p);
                    JOptionPane.showMessageDialog(null, "Data berhasil diupdate!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Input tidak valid, periksa kembali data yang dimasukkan!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteButton = createModernButton("Delete");
        deleteButton.setBounds(200, 150, 80, 25);
        add(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int nim = Integer.parseInt(nimField.getText());
                    String kodeMK = kodeMKField.getText();
                    perkuliahanDAO.deletePerkuliahan(nim, kodeMK);
                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Input tidak valid, periksa kembali data yang dimasukkan!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        searchButton = createModernButton("Search");
        searchButton.setBounds(290, 150, 80, 25);
        add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int nim = Integer.parseInt(nimField.getText());
                    String kodeMK = kodeMKField.getText();
                    Perkuliahan p = perkuliahanDAO.getPerkuliahan(nim, kodeMK);
                    if (p != null) {
                        nipField.setText(String.valueOf(p.getNip()));
                        nilaiField.setText(p.getNilai());
                        JOptionPane.showMessageDialog(null, "Data ditemukan!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Data tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Input tidak valid, periksa kembali data yang dimasukkan!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private JButton createModernButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(59, 89, 182));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Tahoma", Font.BOLD, 12));
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PerkuliahanGUI().setVisible(true);
            }
        });
    }
}
