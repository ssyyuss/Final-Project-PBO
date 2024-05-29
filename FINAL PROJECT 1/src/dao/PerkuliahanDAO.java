package src.dao;

import src.db.DatabaseConnection;
import src.model.Perkuliahan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PerkuliahanDAO {
    private static final Logger LOGGER = Logger.getLogger(PerkuliahanDAO.class.getName());

    public void addPerkuliahan(Perkuliahan p) {
        String sql = "INSERT INTO perkuliahan (Nim, Kode_MK, Nip, Nilai) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            
            ps.setInt(1, p.getNim());
            ps.setString(2, p.getKodeMK());
            ps.setInt(3, p.getNip());
            ps.setString(4, p.getNilai());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding perkuliahan", e);
        }
    }

    public Perkuliahan getPerkuliahan(int nim, String kodeMK) {
        String sql = "SELECT * FROM perkuliahan WHERE Nim = ? AND Kode_MK = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            
            ps.setInt(1, nim);
            ps.setString(2, kodeMK);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Perkuliahan(
                            rs.getInt("Nim"),
                            rs.getString("Kode_MK"),
                            rs.getInt("Nip"),
                            rs.getString("Nilai")
                    );
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting perkuliahan", e);
        }
        return null;
    }

    public List<Perkuliahan> getAllPerkuliahan() {
        String sql = "SELECT * FROM perkuliahan";
        List<Perkuliahan> list = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            
            while (rs.next()) {
                list.add(new Perkuliahan(
                        rs.getInt("Nim"),
                        rs.getString("Kode_MK"),
                        rs.getInt("Nip"),
                        rs.getString("Nilai")
                ));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting all perkuliahan", e);
        }
        return list;
    }

    public void updatePerkuliahan(Perkuliahan p) {
        String sql = "UPDATE perkuliahan SET Nip = ?, Nilai = ? WHERE Nim = ? AND Kode_MK = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            
            ps.setInt(1, p.getNip());
            ps.setString(2, p.getNilai());
            ps.setInt(3, p.getNim());
            ps.setString(4, p.getKodeMK());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating perkuliahan", e);
        }
    }

    public void deletePerkuliahan(int nim, String kodeMK) {
        String sql = "DELETE FROM perkuliahan WHERE Nim = ? AND Kode_MK = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            
            ps.setInt(1, nim);
            ps.setString(2, kodeMK);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting perkuliahan", e);
        }
    }
}
