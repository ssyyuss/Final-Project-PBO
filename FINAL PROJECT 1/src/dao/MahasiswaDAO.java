package src.dao;

import src.db.DatabaseConnection;
import src.model.Mahasiswa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaDAO {
    public void addMahasiswa(Mahasiswa m) {
        String sql = "INSERT INTO mahasiswa (Nim, Nama_Mhs, Tgl_Lahir, Alamat, Jenis_Kelamin) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            
            ps.setInt(1, m.getNim());
            ps.setString(2, m.getNamaMhs());
            ps.setString(3, m.getTglLahir());
            ps.setString(4, m.getAlamat());
            ps.setString(5, String.valueOf(m.getJenisKelamin()));
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Mahasiswa getMahasiswa(int nim) {
        String sql = "SELECT * FROM mahasiswa WHERE Nim = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            
            ps.setInt(1, nim);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Mahasiswa(
                            rs.getInt("Nim"),
                            rs.getString("Nama_Mhs"),
                            rs.getString("Tgl_Lahir"),
                            rs.getString("Alamat"),
                            rs.getString("Jenis_Kelamin").charAt(0)
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Mahasiswa> getAllMahasiswa() {
        String sql = "SELECT * FROM mahasiswa";
        List<Mahasiswa> list = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            
            while (rs.next()) {
                list.add(new Mahasiswa(
                        rs.getInt("Nim"),
                        rs.getString("Nama_Mhs"),
                        rs.getString("Tgl_Lahir"),
                        rs.getString("Alamat"),
                        rs.getString("Jenis_Kelamin").charAt(0)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateMahasiswa(Mahasiswa m) {
        String sql = "UPDATE mahasiswa SET Nama_Mhs = ?, Tgl_Lahir = ?, Alamat = ?, Jenis_Kelamin = ? WHERE Nim = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            
            ps.setString(1, m.getNamaMhs());
            ps.setString(2, m.getTglLahir());
            ps.setString(3, m.getAlamat());
            ps.setString(4, String.valueOf(m.getJenisKelamin()));
            ps.setInt(5, m.getNim());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMahasiswa(int nim) {
        String sql = "DELETE FROM mahasiswa WHERE Nim = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            
            ps.setInt(1, nim);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
