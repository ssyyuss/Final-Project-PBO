package src.gui;

import com.toedter.calendar.JDateChooser;
import src.dao.MahasiswaDAO;
import src.model.Mahasiswa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MahasiswaGUI extends JFrame {
    private JTextField nimField, namaField, alamatField;
    private JDateChooser tglLahirChooser;
    private JRadioButton lakiRadio, perempuanRadio;
    private ButtonGroup jenisKelaminGroup;
    private JButton addButton, updateButton, deleteButton, searchButton, viewAllButton;
    private JTable mahasiswaTable;
    private DefaultTableModel tableModel;
    private MahasiswaDAO mahasiswaDAO;

    public MahasiswaGUI() {
        mahasiswaDAO = new MahasiswaDAO();

        setTitle("Sistem Informasi Akademik Mahasiswa");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nimLabel = new JLabel("NIM:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nimLabel, gbc);

        nimField = new JTextField(30);
        gbc.gridx = 1;
        add(nimField, gbc);

        JLabel namaLabel = new JLabel("Nama:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(namaLabel, gbc);

        namaField = new JTextField(30);
        gbc.gridx = 1;
        add(namaField, gbc);

        JLabel tglLahirLabel = new JLabel("Tgl Lahir:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(tglLahirLabel, gbc);

        tglLahirChooser = new JDateChooser();
        gbc.gridx = 1;
        add(tglLahirChooser, gbc);

        JLabel alamatLabel = new JLabel("Alamat:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(alamatLabel, gbc);

        alamatField = new JTextField(30);
        gbc.gridx = 1;
        add(alamatField, gbc);

        JLabel jenisKelaminLabel = new JLabel("Jenis Kelamin:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(jenisKelaminLabel, gbc);

        lakiRadio = new JRadioButton("Laki-laki");
        perempuanRadio = new JRadioButton("Perempuan");

        jenisKelaminGroup = new ButtonGroup();
        jenisKelaminGroup.add(lakiRadio);
        jenisKelaminGroup.add(perempuanRadio);

        JPanel jenisKelaminPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jenisKelaminPanel.add(lakiRadio);
        jenisKelaminPanel.add(perempuanRadio);

        gbc.gridx = 1;
        add(jenisKelaminPanel, gbc);

        addButton = createModernButton("Add");
        updateButton = createModernButton("Update");
        deleteButton = createModernButton("Delete");
        searchButton = createModernButton("Search");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        String[] columnNames = {"NIM", "Nama", "Tanggal Lahir", "Alamat", "Jenis Kelamin"};
        tableModel = new DefaultTableModel(columnNames, 0);
        mahasiswaTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(mahasiswaTable);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mahasiswa m = new Mahasiswa();
                m.setNim(Integer.parseInt(nimField.getText()));
                m.setNamaMhs(namaField.getText());
                m.setTglLahir(new SimpleDateFormat("yyyy-MM-dd").format(tglLahirChooser.getDate()));
                m.setAlamat(alamatField.getText());
                if (lakiRadio.isSelected()) {
                    m.setJenisKelamin('L');
                } else if (perempuanRadio.isSelected()) {
                    m.setJenisKelamin('P');
                }
                mahasiswaDAO.addMahasiswa(m);
                loadMahasiswaTable();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mahasiswa m = new Mahasiswa();
                m.setNim(Integer.parseInt(nimField.getText()));
                m.setNamaMhs(namaField.getText());
                m.setTglLahir(new SimpleDateFormat("yyyy-MM-dd").format(tglLahirChooser.getDate()));
                m.setAlamat(alamatField.getText());
                if (lakiRadio.isSelected()) {
                    m.setJenisKelamin('L');
                } else if (perempuanRadio.isSelected()) {
                    m.setJenisKelamin('P');
                }
                mahasiswaDAO.updateMahasiswa(m);
                loadMahasiswaTable();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nim = Integer.parseInt(nimField.getText());
                mahasiswaDAO.deleteMahasiswa(nim);
                loadMahasiswaTable();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nim = Integer.parseInt(nimField.getText());
                Mahasiswa m = mahasiswaDAO.getMahasiswa(nim);
                if (m != null) {
                    namaField.setText(m.getNamaMhs());
                    try {
                        Date tglLahir = new SimpleDateFormat("yyyy-MM-dd").parse(m.getTglLahir());
                        tglLahirChooser.setDate(tglLahir);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    alamatField.setText(m.getAlamat());
                    if (m.getJenisKelamin() == 'L') {
                        lakiRadio.setSelected(true);
                    } else if (m.getJenisKelamin() == 'P') {
                        perempuanRadio.setSelected(true);
                    }
                }
            }
        });

        loadMahasiswaTable(); // Load table data on startup
    }

    private void loadMahasiswaTable() {
        List<Mahasiswa> mahasiswaList = mahasiswaDAO.getAllMahasiswa();
        tableModel.setRowCount(0); 
        for (Mahasiswa m : mahasiswaList) {
            Object[] row = {
                m.getNim(),
                m.getNamaMhs(),
                m.getTglLahir(),
                m.getAlamat(),
                m.getJenisKelamin() == 'L' ? "Laki-laki" : "Perempuan"
            };
            tableModel.addRow(row);
        }
    }

    private JButton createModernButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(59, 89, 182));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Tahoma", Font.BOLD, 12));
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MahasiswaGUI().setVisible(true);
            }
        });
    }
}
