package src.dao;

import src.db.DatabaseConnection;
import src.model.Dosen;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DosenDAO {
    public void addDosen(Dosen d) {
        String sql = "INSERT INTO dosen (Nip, Nama_Dosen) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            
            ps.setInt(1, d.getNip());
            ps.setString(2, d.getNamaDosen());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dosen getDosen(int nip) {
        String sql = "SELECT * FROM dosen WHERE Nip = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            
            ps.setInt(1, nip);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Dosen(
                            rs.getInt("Nip"),
                            rs.getString("Nama_Dosen")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Dosen> getAllDosen() {
        String sql = "SELECT * FROM dosen";
        List<Dosen> list = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            
            while (rs.next()) {
                list.add(new Dosen(
                        rs.getInt("Nip"),
                        rs.getString("Nama_Dosen")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateDosen(Dosen d) {
        String sql = "UPDATE dosen SET Nama_Dosen = ? WHERE Nip = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            
            ps.setString(1, d.getNamaDosen());
            ps.setInt(2, d.getNip());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDosen(int nip) {
        String sql = "DELETE FROM dosen WHERE Nip = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            
            ps.setInt(1, nip);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
