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
import java.util.ArrayList;
import java.util.List;
import myDTO.Plant;
import mylib.DBUtils;

/**
 *
 * @author ADMIN
 */
public class PlantDAO {
    public static ArrayList<Plant> getPlants(String keyword, String searchby) throws SQLException {
        ArrayList<Plant> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String getPlants = "SELECT pid, pName, price, imgPath, description, status, P.cateId, cateName\n"
                    + "FROM Plants P JOIN Categories C ON P.cateId = C.cateId ";
            cn = DBUtils.makeConnection();
            if (cn != null) {
                if (searchby.equalsIgnoreCase("byname")) {
                    getPlants += "WHERE pName like ?";
                } else {
                    getPlants += "WHERE cateName like ?";
                }
                pst = cn.prepareStatement(getPlants);
                pst.setString(1, "%" + keyword + "%");
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("PID");
                        String plantName = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgPath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateId = rs.getInt("CateID");
                        String cateName = rs.getString("CateName");
                        Plant plant = new Plant(id, plantName, price, imgPath, description, status, cateId, cateName);
                        list.add(plant);
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
    
    public static Plant getPlant(int pid) throws SQLException{
        Plant p = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            cn = DBUtils.makeConnection();
            if(cn != null){
                String s = "select PID, PName, price, imgPath, description, status, Plants.CateID as CateID, CateName\n"
                    + "from Plants, Categories\n"
                    + "where Plants.CateID = Categories.CateID and PID = ?";
                pst = cn.prepareStatement(s);
                pst.setInt(1, pid);
                rs = pst.executeQuery();
                if(rs != null && rs.next()){
                        pid = rs.getInt("PID");
                        String plantName = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgPath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateId = rs.getInt("cateID");
                        String cateName = rs.getString("CateName");
                        p = new Plant(pid, plantName, price, imgPath, description, status, cateId, cateName);
                }
            }
        }catch(Exception e){
            
        }finally {
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
        return p;
    }
    
    public static ArrayList<Plant> getAllPlants() throws SQLException {
        ArrayList<Plant> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.makeConnection();
            String s = "select PID, PName, price, imgPath, description, status, Plants.CateID as CateID, CateName\n"
                    + "from Plants, Categories\n"
                    + "where Plants.CateID = Categories.CateID";
            if (cn != null) {
                pst = cn.prepareStatement(s);
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("PID");
                        String fullName = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgPath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateId = rs.getInt("CateID");
                        String cateName = rs.getString("catename");
                        Plant plant = new Plant(id, fullName, price, imgPath, description, status, cateId, cateName);
                        list.add(plant);
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
    
    
    public static boolean insertNewPlant(String name, String imgPath, int price, String description, int status, int cateId) throws SQLException {
        boolean check = false;
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.makeConnection();
            String s = "INSERT INTO Plants (pName, price, imgPath, description, status, cateId) VALUES (?, ?, ?, ?, ?, ?)";
            if (cn != null) {
                pst = cn.prepareStatement(s);
                pst.setString(1, name);
                pst.setInt(2, price);
                pst.setString(3, imgPath);
                pst.setString(4, description);
                pst.setInt(5, status);
                pst.setInt(6, cateId);
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

    public static boolean updatePlantInfo(int pid, String name, String imgPath, int price, String description, int status, int cateId) throws SQLException {
        boolean check = false;
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.makeConnection();
            String s = "UPDATE Plants SET pName = ? , price = ? , imgPath = ?\n"
            + ", description = ?, status = ?, cateId = ? WHERE pId = ?";
            if (cn != null) {
                pst = cn.prepareStatement(s);
                pst.setString(1, name);
                pst.setInt(2, price);
                pst.setString(3, imgPath);
                pst.setString(4, description);
                pst.setInt(5, status);
                pst.setInt(6, cateId);
                pst.setInt(7, pid);
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
    
    public static boolean updatePlantStatus(int pid, int status) throws SQLException{
        boolean check = false;
        Connection cn = null;
        PreparedStatement pst = null;
        try{
            cn = DBUtils.makeConnection();
            if(cn != null){
                String s = "UPDATE Plants SET status = ? WHERE pid = ?";
                pst = cn.prepareStatement(s);
                pst.setInt(1, status);
                pst.setInt(2, pid);
                check = pst.executeUpdate() > 0;
            }
        }catch(Exception e){
        }finally{
            if(pst != null){
                pst.close();
            }
            if(cn != null){
                cn.close();
            }
        }
        return check;
    }
    
    public static ArrayList<Plant> getTop4Plants() throws SQLException {
        ArrayList<Plant> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.makeConnection();
            String s = "SELECT TOP(4) pid, pName, price, "
            + "imgPath, description, status, cateId, cateName FROM Plants ORDER BY pid";
            if (conn != null) {
                psm = conn.prepareStatement(s);
                rs = psm.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("PID");
                        String fullName = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgPath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateId = rs.getInt("CateID");
                        String catename = rs.getString("catename");
                        Plant plant = new Plant(id, fullName, price, imgPath, description, status, cateId, catename);
                        list.add(plant);
                    }
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (psm != null) {
                psm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
}
