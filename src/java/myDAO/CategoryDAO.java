/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import mylib.DBUtils;

/**
 *
 * @author ADMIN
 */
public class CategoryDAO {
    public static boolean insertNewCategory(String cateName) throws SQLException {
        boolean check = false;
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.makeConnection();
            String s = "INSERT INTO Categories (cateName) VALUES (?)";
            if (cn != null) {
                pst = cn.prepareStatement(s);
                pst.setString(1, cateName);
                check = pst.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return check;
    }

    public static boolean updateCategoryInfo(int cateId, String cateName) throws SQLException {
        boolean check = false;
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.makeConnection();
            String s = "UPDATE Categories SET cateName = ? WHERE cateId = ?";
            if (cn != null) {
                pst = cn.prepareStatement(s);
                pst.setString(1, cateName);
                pst.setInt(2, cateId);
                check = pst.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return check;
    }

    public static HashMap<Integer, String> getCategories() throws SQLException {
        HashMap<Integer, String> list = new HashMap<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.makeConnection();
            String s = "SELECT cateId, cateName FROM Categories";
            if (cn != null) {
                pst = cn.prepareStatement(s);
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int cateId = rs.getInt("cateId");
                        String cateName = rs.getString("cateName");
                        list.put(cateId, cateName);
                    }
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }
}
