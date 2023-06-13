/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import myDAO.AccountDAO;
import myDAO.OrderDAO;
import myDAO.OrderDetailDAO;
import myDAO.PlantDAO;
import myDTO.Account;
import myDTO.Category;
import myDTO.Order;
import myDTO.OrderDetail;
import myDTO.Plant;
import mylib.DBUtils;

/**
 *
 * @author ADMIN
 */
public class Tester {
//    public static void main(String[] args) throws SQLException {
//        try{
//            //nếu class DBUtils để public thì
//            //DBUtils db = new DBUtils();
//            //Connection cn = db.makeConnections;
//            Connection cn = DBUtils.makeConnection();
//            if(cn != null){
//                String s = "select pid, pname, price, imgpath, description, status, p.cateID as 'cateID', catename from plants p join categories c\n"
//                    + "on p.CateID = c.CateID";
//                Statement st = cn.createStatement();
//                ResultSet rs = st.executeQuery(s);
//                if(rs != null){
//                    while(rs.next()){
//                        int id = rs.getInt("PID");
//                        String fullname = rs.getString("PName");
//                        int price = rs.getInt("price");
//                        String imgPath = rs.getString("imgPath");
//                        String description = rs.getString("description");
//                        int status = rs.getInt("status");
//                        int cateId = rs.getInt("CateID");
//                        String cateName = rs.getString("CateName");
////                        System.out.println(id + "," + fullname + "," + price + "," + imgPath + "," + description + "," + status + "," + cateId + "," + cateName);
//                    }
//                }
//                cn.close();
//            }
//        }catch(Exception e){
//            System.out.println("Error Connection");
//        }
//        ArrayList<Plant> list = PlantDAO.getPlants("mon", "byname");
//        if(list == null){
//            System.out.println("hihi");
//        }else{
//            list.forEach((plant) -> {
//            System.out.println(plant.getId() + "," + plant.getName() + "," + plant.getPrice() + "," + plant.getImgpath() + "," + plant.getDescription() + "," + plant.getStatus() + "," + plant.getCateid() + ","
//                    + plant.getCatename());
//        });
//        }
//        
//    }
    public static void main(String[] args) throws SQLException {
        HashMap<Integer, String> list = new HashMap<>();
        try{
            //nếu class DBUtils để public thì
            //DBUtils db = new DBUtils();
            //Connection cn = db.makeConnections;
//            HashMap<Integer, String> list = new HashMap<>();
            Connection cn = mylib.DBUtils.makeConnection();
            if(cn != null){
                String s = "SELECT cateId, cateName FROM Categories";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(s);
                if(rs != null){
                    while(rs.next()){
                        int cateId = rs.getInt("cateId");
                        String cateName = rs.getString("cateName");
                        list.put(cateId, cateName);
                        //System.out.println(email + "," + password + "," + fullname + "," + phone + "," + status + "," + role);
                    }
                }
                cn.close();
            }
        }catch(Exception e){
            System.out.println("Error Connection");
        }
        Set<Integer> keySet = list.keySet();
        for (Integer key : keySet) {
            System.out.println(key + "-" + list.get(key));
        }
//        ArrayList<Account> list = AccountDAO.getAccountByName("Long Bui");
//        
//        for (Account account : list) {
//            
//            System.out.println(account.getAccID() + "," + account.getFullname() + "," + account.getPhone());
//        }
        
//        
//        Account acc = AccountDAO.getAccount("test2@gmail.com", "222222");
//        if(acc != null){
//            if(acc.getRole() == 1){
//                System.out.println("I am an admin");
//            }
//            else{
//                System.out.println("I am a user");
//            }
//            
//        }
//        else{
//            System.out.println("Login fail");
//        }
//        ArrayList <Account> list = AccountDAO.getAccounts();
//        for (Account account : list) {
//            System.out.println(account.getAccID() + "," + account.getEmail() + "," + account.getFullname() + "," + account.getPassword());
//        }
//        
//        
//        if(AccountDAO.updateAccountStatus("test@gmail.com", 1))
//            System.out.println("Update status successfully");
//        else System.out.println("cannot update status");
//        
//        if(AccountDAO.updateAccount("test@gmail.com", "999999", "thino", "65432111"))
//            System.out.println("update profile");
//        else System.out.println("update profile failed");
//        
//        if(AccountDAO.insertAccount("test2@gmail.com", "222222", "chi pheo", "123123123", 1, 0))
//            System.out.println("added new account");
//        else System.out.println("insert a new account failed");
//    public static void main(String[] args) throws SQLException {
//        try{
//            //nếu class DBUtils để public thì
//            //DBUtils db = new DBUtils();
//            //Connection cn = db.makeConnections;
//            Connection cn = DBUtils.makeConnection();
//            if(cn != null){
//                String s = "select OrderID, OrdDate, shipdate, o.status, o.AccID from dbo.Orders o\n"
//                        + "join dbo.Accounts a on o.AccID = a.accID";
//                Statement st = cn.createStatement();
//                ResultSet rs = st.executeQuery(s);
//                if(rs != null){
//                    while(rs.next()){
//                        int orderID = rs.getInt("OrderID");
//                        String ordDate = rs.getString("OrdDate");
//                        String shipDate = rs.getString("shipdate");
//                        int status = rs.getInt("status");
//                        int accID = rs.getInt("AccID");
//                        System.out.println(orderID + "," + ordDate + "," + shipDate + "," + status + "," + accID);
//                    }
//                }
//                cn.close();
//            }
//        }catch(Exception e){
//            System.out.println("Error Connection");
//        }
//        Account acc = AccountDAO.getAccount("test@gmail.com", "999999");
////        ArrayList <Account> list = AccountDAO.getAccounts();
////        System.out.println(list);
////        for (Account account : list) {
//        ArrayList<Order> or = OrderDAO.getOrders(acc.getEmail());
//        for (Order order : or) {
//            ArrayList<OrderDetail> li = OrderDetailDAO.getOrderDetail(order.getOrderID());
//            for (OrderDetail orderDetail : li) {
//                System.out.println(orderDetail.getOrderId() + "," + orderDetail.getPlantId());
//            }
//        }
//            if(list == null){
//                System.out.println("hi");
//            }else{
//                for (Order order : or) {
//                    ArrayList<OrderDetail> li = OrderDAO.getOrderDetail(order.getOrderID());
//                    System.out.println(li);
////                    System.out.println(order.getOrderID() + "," + order.getOrderDate() + "," + order.getShipDate() + "," + order.getStatus() + "," + order.getAccID());
//                }
//            }
//        }
        
//        ArrayList<Order> list = OrderDAO.getOrders();
//        if(list == null){
//            System.out.println("hihi");
//        }else{
//            list.forEach((plant) -> {
//            System.out.println(plant.getId() + "," + plant.getName() + "," + plant.getPrice() + "," + plant.getImgpath() + "," + plant.getDescription() + "," + plant.getStatus() + "," + plant.getCateid() + ","
//                    + plant.getCatename());
//        });
//        }
        
    }
}
