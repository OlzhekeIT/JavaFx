package Market_DB;

import Market_Model.Laptop;
import Market_Model.Product;
import Market_Model.Smartphone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {


    public List<Product> findByCategoryName(String categoryName) {
        String sql = "SELECT * FROM product WHERE category = ? ORDER BY id";
        List<Product> list = new ArrayList<>();

        try (Connection conn = DataBase.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, categoryName);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(map(rs));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Product load error: " + e.getMessage(), e);
        }

        return list;
    }


    public Product findById(int id) {
        String sql = "SELECT * FROM product WHERE id = ?";

        try (Connection conn = DataBase.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Product by id error: " + e.getMessage(), e);
        }

        return null;
    }


    private Product map(ResultSet rs) throws Exception {

        int id          = rs.getInt("id");
        String name     = rs.getString("name");
        double price    = rs.getDouble("price");
        String desc     = rs.getString("description");
        String imageUrl = rs.getString("image_url");
        String category = rs.getString("category");
        double screen   = rs.getDouble("screen_size");
        String model    = rs.getString("model");

        if ("Laptops".equalsIgnoreCase(category)) {
            // 🔹 Ноутбук жасадық
            String cpu = rs.getString("cpu");
            int ram    = rs.getInt("ram_gb");
            int ssd    = rs.getInt("ssd_gb");
            String os  = rs.getString("os");

            return new Laptop(
                    id, name,imageUrl,price,desc,
                    cpu, ram, ssd, screen, os, model
            );
        }



        else if ("Smartphones".equalsIgnoreCase(category)) {
            int ram        = rs.getInt("ram_gb");
            int storage    = rs.getInt("storage_gb");
            int battery    = rs.getInt("battery");
            String camera  = rs.getString("camera");

            return new Smartphone(
                    id, name, price, desc, imageUrl,
                    screen, ram, storage, battery, camera, model
            );
        }


        throw new IllegalStateException("Белгісіз category  " + category);
    }
}
