package com.example.learning.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcExample {
    private static final String URL = "jdbc:postgresql://localhost:5432/test_db";
    private static final String USER = "admin";
    private static final String PASS = "admin";

    public static void main(String[] args) {
        Product newProduct = new Product(null, "iPhone 15", 1000.0);

        saveProduct(newProduct); // Lưu
        List<Product> list = getAllProducts(); // Lấy ra

        list.forEach(p -> System.out.println(p.getName() + " - " + p.getPrice()));
    }

    // --- HÀM LƯU DỮ LIỆU ---
    public static void saveProduct(Product product) {
        String sql = "INSERT INTO products (name, price) VALUES (?, ?)";

        // Dùng try-with-resources để tự động đóng Connection, PreparedStatement
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Bạn phải "cầm tay chỉ việc" ném từng giá trị vào dấu ?
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());

            pstmt.executeUpdate();
            System.out.println("Lưu thành công!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // --- HÀM LẤY DỮ LIỆU ---
    public static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Bạn phải tự dùng vòng lặp, bới từng cột trong DB để nhét ngược vào Object Java
            while (rs.next()) {
                Product p = new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getDouble("price")
                );
                products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
