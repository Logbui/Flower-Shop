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
import java.sql.Date;
import java.util.HashMap;
import java.util.Set;
import myDTO.Order;
import mylib.DBUtils;

/**
 *
 * @author ADMIN
 */
public class OrderDAO {
    public static ArrayList<Order> getOrders(String email) throws SQLException{
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            cn = DBUtils.makeConnection();
            if(cn != null){
                String s = "select OrderID, OrdDate, shipdate, o.status, o.AccID from dbo.Orders o\n"
                        + "join dbo.Accounts a on o.AccID = a.accID where a.email like ?";
                pst = cn.prepareStatement(s);
                pst.setString(1, "%" + email + "%");
                rs = pst.executeQuery();
                if(rs != null){
                    while(rs.next()){
                        int orderID = rs.getInt("OrderID");
                        String ordDate = rs.getString("OrdDate");
                        String shipDate = rs.getString("shipdate");
                        int status = rs.getInt("status");
                        int accID = rs.getInt("AccID");
                        Order o = new Order(orderID, ordDate, shipDate, status, accID);
                        list.add(o);
                    }
                }
            }
        }catch(Exception e){
            
        }finally{
            if(rs != null){
                rs.close();
            }
            if(pst != null){
                pst.close();
            }
            if(cn != null){
                cn.close();
            }
        }
        return list;
    }
    
    public static Order getOrderById(int orderId) throws SQLException{
        Order order = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            cn = DBUtils.makeConnection();
            if(cn != null){
                String s = "select OrderID, OrdDate, shipdate, status, AccID from dbo.Orders where OrderID = ?";
                pst = cn.prepareStatement(s);
                pst.setInt(1, orderId);
                rs = pst.executeQuery();
                if(rs != null){
                    while(rs.next()){
                        //orderId = rs.getInt("OrderID");
                        String ordDate = rs.getString("OrdDate");
                        String shipDate = rs.getString("shipdate");
                        int status = rs.getInt("status");
                        int accID = rs.getInt("AccID");
                        order = new Order(orderId, ordDate, shipDate, status, accID);
                    }
                }
            }
        }catch(Exception e){
            
        }finally{
            if(rs != null){
                rs.close();
            }
            if(pst != null){
                pst.close();
            }
            if(cn != null){
                cn.close();
            }
        }
        return order;
    }
    
    public static boolean insertOrder(String email, HashMap<String,Integer> cart) throws SQLException{
        Connection cn = null;
        boolean result = false;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            cn = DBUtils.makeConnection();
            if(cn != null){
                int accID = 0, orderID = 0;
                cn.setAutoCommit(false);
                //get account id by email
                String s = "select accID from Accounts where Accounts.email = ?";
                pst = cn.prepareStatement(s);
                pst.setString(1, email);
                rs = pst.executeQuery();
                if(rs != null && rs.next()) accID = rs.getInt("accID");
                //insert a new order
                System.out.println("Account ID: "+ accID);
                Date d = new Date(System.currentTimeMillis());
                System.out.println("Order date: "+ d);
                s = "INSERT Orders(OrdDate, status, AccID) values (?,?,?)";
                pst = cn.prepareStatement(s);
                pst.setDate(1, d);
                pst.setInt(2, 2);
                pst.setInt(3, accID);
                pst.executeUpdate();
                //get order id that is the largest number
                s = "select top 1 orderID from Orders order by orderId desc";
                pst = cn.prepareStatement(s);
                rs = pst.executeQuery();
                if(rs != null && rs.next()) orderID = rs.getInt("orderID");
                //insert order details
                System.out.println("Order ID: "+orderID);
                Set<String> pids = cart.keySet();
                for (String pid : pids) {
                    s = "INSERT OrderDetails values (?,?,?)";
                    pst = cn.prepareStatement(s);
                    pst.setInt(1, orderID);
                    pst.setInt(2, Integer.parseInt(pid.trim()));
                    pst.setInt(3, cart.get(pid));
                    pst.executeUpdate();
                    cn.commit();
                    cn.setAutoCommit(true);
                }
                return true;
            }
            else System.out.println("Cannot insert order!");
        }catch(Exception e){
            try{
                if(cn != null) cn.rollback();
            }catch(SQLException ex){
            }
            result = false;
        }finally{
            if(rs != null){
                rs.close();
            }
            if(pst != null){
                pst.close();
            }
            if(cn != null){
                cn.close();
            }
        }
        return result;
    }
    
    public static boolean updateOrderStatus(int orderId, int orderStatus) throws SQLException {
        boolean check = false;
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String s = "UPDATE Orders SET status = ?, orderDate = GETDATE() WHERE orderId = ?";
                pst = cn.prepareStatement(s);
                pst.setInt(1, orderStatus);
                pst.setInt(2, orderId);
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
    
    public static ArrayList<Order> getOrdersByPeriodTime(int accId, String from, String to) throws SQLException {
        ArrayList<Order> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String s = "select orderId, ordDate, shipDate, status, accId\n" +
                           "from Orders WHERE accId = ? AND (ordDate >= ? AND ordDate <= ?)";
                psm = conn.prepareStatement(s);
                psm.setInt(1, accId);
                psm.setString(2, from);
                psm.setString(3, to);
                rs = psm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("orderId");
                    String orderDate = rs.getString("ordDate");
                    String shipDate = rs.getString("shipDate");
                    int status = rs.getInt("status");
                    Order ord = new Order(orderID, orderDate, shipDate, status, accId);
                    list.add(ord);
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
    
    public static ArrayList<Order> getOrdersByPeriodTime(String from, String to) throws SQLException {
        ArrayList<Order> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String s = "select orderId, ordDate, shipDate, status, accId\n" +
                           "from Orders WHERE (ordDate >= ? AND ordDate <= ?)";
                psm = conn.prepareStatement(s);
                psm.setString(1, from);
                psm.setString(2, to);
                rs = psm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("orderId");
                    String orderDate = rs.getString("ordDate");
                    String shipDate = rs.getString("shipDate");
                    int status = rs.getInt("status");
                    int accId = rs.getInt("AccID");
                    Order ord = new Order(orderID, orderDate, shipDate, status, accId);
                    list.add(ord);
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
    
    public static boolean finishOrder(int orderId, int orderStatus) throws SQLException {
        boolean check = false;
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String s = "UPDATE Orders SET status = ? WHERE orderId = ?";
                pst = cn.prepareStatement(s);
                pst.setInt(1, orderStatus);
                pst.setInt(2, orderId);
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
    
    public static ArrayList<Order> getAllOrders() throws SQLException {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String s = "SELECT orderId, ordDate, shipDate, status, accId FROM Orders";
                pst = cn.prepareStatement(s);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("orderId");
                    String orderDate = rs.getString("ordDate");
                    String shipDate = rs.getString("shipDate");
                    int status = rs.getInt("status");
                    int accId = rs.getInt("accId");
                    Order ord = new Order(orderID, orderDate, shipDate, status, accId);
                    list.add(ord);
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
    
    public static ArrayList<Order> getOrdersByAccID(int accID) throws SQLException{
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            cn = DBUtils.makeConnection();
            if(cn != null){
                String s = "select OrderID, OrdDate, shipdate, status, AccID from dbo.Orders \n"
                        + "where AccID = ?";
                pst = cn.prepareStatement(s);
                pst.setInt(1, accID);
                rs = pst.executeQuery();
                if(rs != null){
                    while(rs.next()){
                        int orderID = rs.getInt("OrderID");
                        String ordDate = rs.getString("OrdDate");
                        String shipDate = rs.getString("shipdate");
                        int status = rs.getInt("status");
                        Order o = new Order(orderID, ordDate, shipDate, status, accID);
                        list.add(o);
                    }
                }
            }
        }catch(Exception e){
            
        }finally{
            if(rs != null){
                rs.close();
            }
            if(pst != null){
                pst.close();
            }
            if(cn != null){
                cn.close();
            }
        }
        return list;
    }
    
    public static ArrayList<Order> getOrdersByStatus(int status, int accID) throws SQLException{
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            cn = DBUtils.makeConnection();
            if(cn != null){
                String s = "select OrderID, OrdDate, shipdate, status, AccID from dbo.Orders \n"
                        + "where status = ? and AccID = ?";
                pst = cn.prepareStatement(s);
                pst.setInt(1, status);
                pst.setInt(2, accID);
                rs = pst.executeQuery();
                if(rs != null){
                    while(rs.next()){
                        int orderID = rs.getInt("OrderID");
                        String ordDate = rs.getString("OrdDate");
                        String shipDate = rs.getString("shipdate");
                        Order o = new Order(orderID, ordDate, shipDate, status, accID);
                        list.add(o);
                    }
                }
            }
        }catch(Exception e){
            
        }finally{
            if(rs != null){
                rs.close();
            }
            if(pst != null){
                pst.close();
            }
            if(cn != null){
                cn.close();
            }
        }
        return list;
    }
}
