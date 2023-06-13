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
import java.sql.Statement;
import java.util.ArrayList;
import myDTO.Account;
import mylib.DBUtils;

/**
 *
 * @author ADMIN
 */
public class AccountDAO {
    
    public static Account getAccount(String email, String password) throws SQLException{
        Account acc = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            cn = DBUtils.makeConnection();
            if(cn != null){
                String s = "SELECT accID, email, password, fullname, phone, status, role \n"
                        + "FROM dbo.Accounts\n"
                        + "WHERE email = ? and password = ? COLLATE Latin1_General_CS_AI";
                pst = cn.prepareStatement(s);
                pst.setString(1, email);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if(rs != null && rs.next()){
                    int AccID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String Fullname = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int status = rs.getInt("status");
                    int role = rs.getInt("role");
                    acc = new Account(AccID, Email, Password, Fullname, status, Phone, role);
                    
                }
            }
        }catch (Exception e){
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
        return acc;
    }
    
    public static ArrayList<Account> getAccounts() throws SQLException{
        ArrayList<Account> list = new ArrayList<>();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            cn = DBUtils.makeConnection();
            if(cn != null){
                st = cn.createStatement();//dùng statement vì câu sql thực thi là sql tĩnh, ko có tham số truyền vào
                String s = "SELECT accID, email, password, fullname, phone, status, role \n"
                        + "FROM dbo.Accounts";
                rs = st.executeQuery(s);
                while(rs.next()){
                    int AccID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String Fullname = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int status = rs.getInt("status");
                    int role = rs.getInt("role");
                    Account acc = new Account(AccID, Email, Password, Fullname, status, Phone, role);
                    list.add(acc);
                }
            }
        }catch(Exception e){
        }finally{
            if(rs != null){
                rs.close();
            }
            if(st != null){
                st.close();
            }
            if(cn != null){
                cn.close();
            }
        }
        return list;
    }
    
    public static boolean updateAccountStatus(String email, int status) throws SQLException{
        boolean check = false;
        Connection cn = null;
        PreparedStatement pst = null;
        try{
            cn = DBUtils.makeConnection();
            if(cn != null){
                String s = "UPDATE Accounts SET status = ? WHERE email = ?";
                pst = cn.prepareStatement(s);
                pst.setInt(1, status);
                pst.setString(2, email);
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
    
    public static boolean updateAccount(String email, String newPassword, String newFullname, String newPhone) throws SQLException{
        boolean check = false;
        Connection cn = null;
        PreparedStatement pst = null;
        try{
            cn = DBUtils.makeConnection();
            if(cn != null){
                String s = "UPDATE Accounts SET fullname = ?, phone = ?, password = ?\n"
                        + "WHERE email = ?";
                pst = cn.prepareStatement(s);
                pst.setString(1, newFullname);
                pst.setString(2, newPhone);
                pst.setString(3, newPassword);
                pst.setString(4, email);
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
    
    public static boolean insertAccount(String newEmail, String newPassword, String newFullname, String newPhone, int newStatus, int newRole) throws SQLException{
        boolean check = false;
        Connection cn = null;
        PreparedStatement pst = null;
        try{
            cn = DBUtils.makeConnection();
            if(cn != null){
                String s = "INSERT INTO Accounts(email, password, fullname, phone, status, role) VALUES (?, ?, ?, ?, ?, ?)";
                pst = cn.prepareStatement(s);
                pst.setString(1, newEmail);
                pst.setString(2, newPassword);
                pst.setString(3, newFullname);
                pst.setString(4, newPhone);
                pst.setInt(5, newStatus);
                pst.setInt(6, newRole);
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
    
    public static boolean updateToken(String token, String email) throws SQLException{
        boolean check = true;
        Connection cn = null;
        PreparedStatement pst = null;
        try{
            cn = DBUtils.makeConnection();
            if(cn != null){
                String s = "UPDATE Accounts Set token = ? WHERE email = ?";
                pst = cn.prepareStatement(s);
                pst.setString(1, token);
                pst.setString(2, email);
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
    
    public static Account getAccountByToken(String token) throws SQLException{
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Account acc = null;
        try{
            cn = DBUtils.makeConnection();
            String s = "SELECT accID, email, password, fullName, phone, status, role FROM dbo.Accounts WHERE token = ?";
            pst = cn.prepareStatement(s);
            pst.setString(1, token);
            rs = pst.executeQuery();
            if(rs != null){
                while(rs.next()){
                    int AccID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String Fullname = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int status = rs.getInt("status");
                    int role = rs.getInt("role");
                    acc = new Account(AccID, Email, Password, Fullname, status, Phone, role);
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
        return acc;
    }
    
    public static Account getAccountInfoByEmail(String email) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Account acc = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String s = "SELECT AccID, Email, Password, FullName, Phone, Status, Role FROM Accounts WHERE Email = ?";
                stm = conn.prepareStatement(s);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int AccId = rs.getInt("AccID");
                    String Email = rs.getString("Email");
                    String Password = rs.getString("Password");
                    String FullName = rs.getString("FullName");
                    String Phone = rs.getString("Phone");
                    int Status = rs.getInt("Status");
                    int Role = rs.getInt("Role");
                    acc = new Account(AccId, Email, Password, FullName, Status, Phone, Role);
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return acc;
    }
    
    public static boolean changeAccount(String email, String newFullname, String newPhone) throws SQLException {
        boolean check = false;
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String s = "UPDATE Accounts SET fullname = ?, phone = ? WHERE email = ?";
                pst = cn.prepareStatement(s);
                pst.setString(1, newFullname);
                pst.setString(2, newPhone);
                pst.setString(3, email);
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
    
    public static ArrayList<Account> getAccountByName(String keyword) throws SQLException{
        ArrayList<Account> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            cn = DBUtils.makeConnection();
            if(cn != null){
                String s = "SELECT accID, email, password, fullname, phone, status, role \n"
                        + "FROM dbo.Accounts\n"
                        + "WHERE fullname like ?";
                pst = cn.prepareStatement(s);
                pst.setString(1, "%" + keyword + "%");
                rs = pst.executeQuery();
                if(rs != null){
                    while(rs.next()){
                        int AccID = rs.getInt("accID");
                        String Email = rs.getString("email");
                        String Password = rs.getString("password");
                        String fullname = rs.getString("fullname");
                        String Phone = rs.getString("phone");
                        int status = rs.getInt("status");
                        int role = rs.getInt("role");
                        Account acc = new Account(AccID, Email, Password, fullname, status, Phone, role);
                        list.add(acc);
                    }
                }
            }
        }catch (Exception e){
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
    
    public static void main(String[] args) throws SQLException {
        ArrayList<Account> list = getAccounts();
        for (Account account : list) {
            System.out.println(account.getFullname());
        }
    }
}
