package src.dao;

import src.db.DatabaseConnection;
import src.model.MataKuliah;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MataKuliahDAO {
    public void addMataKuliah(MataKuliah mk) {
        String sql = "INSERT INTO matakuliah (Kode_MK, Nama_MK, Sks) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            
            ps.setString(1, mk.getKodeMK());
            ps.setString(2, mk.getNamaMK());
            ps.setInt(3, mk.getSks());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public MataKuliah getMataKuliah(String kodeMK) {
        String sql = "SELECT * FROM matakuliah WHERE Kode_MK = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            
            ps.setString(1, kodeMK);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new MataKuliah(
                            rs.getString("Kode_MK"),
                            rs.getString("Nama_MK"),
                            rs.getInt("Sks")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MataKuliah> getAllMataKuliah() {
        String sql = "SELECT * FROM matakuliah";
        List<MataKuliah> list = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            
            while (rs.next()) {
                list.add(new MataKuliah(
                        rs.getString("Kode_MK"),
                        rs.getString("Nama_MK"),
                        rs.getInt("Sks")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateMataKuliah(MataKuliah mk) {
        String sql = "UPDATE matakuliah SET Nama_MK = ?, Sks = ? WHERE Kode_MK = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            
            ps.setString(1, mk.getNamaMK());
            ps.setInt(2, mk.getSks());
            ps.setString(3, mk.getKodeMK());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMataKuliah(String kodeMK) {
        String sql = "DELETE FROM matakuliah WHERE Kode_MK = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            
            ps.setString(1, kodeMK);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
