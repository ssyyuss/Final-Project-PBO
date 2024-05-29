package src.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import src.dao.DosenDAO;
import src.model.Dosen;

public class DosenGUI extends JFrame {
    private JTextField nipField, namaDosenField;
    private JButton addButton, updateButton, deleteButton, searchButton, viewAllButton;
    private JTable dosenTable;
    private DefaultTableModel tableModel;
    private DosenDAO dosenDAO;

    public DosenGUI() {
        dosenDAO = new DosenDAO();
        setTitle("Dosen Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 240, 240));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Define the button style
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);
        Color buttonColor = new Color(50, 50, 50);
        Color buttonHoverColor = new Color(80, 80, 80);

        JLabel nipLabel = new JLabel("NIP:");
        nipField = new JTextField(20);
        JLabel namaDosenLabel = new JLabel("Nama Dosen:");
        namaDosenField = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(nipLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(nipField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(namaDosenLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(namaDosenField, gbc);

        addButton = createButton("Add", buttonFont, buttonColor, buttonHoverColor, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dosen d = new Dosen();
                d.setNip(Integer.parseInt(nipField.getText()));
                d.setNamaDosen(namaDosenField.getText());
                dosenDAO.addDosen(d);
                loadDosenTable();
            }
        });

        updateButton = createButton("Update", buttonFont, buttonColor, buttonHoverColor, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dosen d = new Dosen();
                d.setNip(Integer.parseInt(nipField.getText()));
                d.setNamaDosen(namaDosenField.getText());
                dosenDAO.updateDosen(d);
                loadDosenTable();
            }
        });

        deleteButton = createButton("Delete", buttonFont, buttonColor, buttonHoverColor, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nip = Integer.parseInt(nipField.getText());
                dosenDAO.deleteDosen(nip);
                loadDosenTable();
            }
        });

        searchButton = createButton("Search", buttonFont, buttonColor, buttonHoverColor, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nip = Integer.parseInt(nipField.getText());
                Dosen d = dosenDAO.getDosen(nip);
                if (d != null) {
                    namaDosenField.setText(d.getNamaDosen());
                }
            }
        });

        viewAllButton = createButton("View All", buttonFont, buttonColor, buttonHoverColor, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDosenTable();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(viewAllButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        String[] columnNames = {"NIP", "Nama Dosen"};
        tableModel = new DefaultTableModel(columnNames, 0);
        dosenTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(dosenTable);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);

        loadDosenTable(); // Load table data on startup
    }

    private void loadDosenTable() {
        List<Dosen> dosenList = dosenDAO.getAllDosen();
        tableModel.setRowCount(0); // Clear existing data
        for (Dosen d : dosenList) {
            Object[] row = {d.getNip(), d.getNamaDosen()};
            tableModel.addRow(row);
        }
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
                new DosenGUI().setVisible(true);
            }
        });
    }
}
