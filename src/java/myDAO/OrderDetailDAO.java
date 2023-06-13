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
import myDTO.OrderDetail;
import mylib.DBUtils;

/**
 *
 * @author ADMIN
 */
public class OrderDetailDAO {
    public static ArrayList<OrderDetail> getOrderDetail(int orderID) throws SQLException{
        ArrayList<OrderDetail> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            cn = DBUtils.makeConnection();
            if(cn != null){
                String s = "select DetailId, OrderID, FID, PName, imgPath, price, quantity\n"
                        + "from dbo.OrderDetails, dbo.Plants\n"
                        + "where OrderID = ? and OrderDetails.FID = Plants.PID";
                pst = cn.prepareStatement(s);
                pst.setInt(1, orderID);
                rs = pst.executeQuery();
                if(rs != null){
                    while(rs.next()){
                        int detailID = rs.getInt("DetailId");
                        int plantID = rs.getInt("FID");
                        String plantName = rs.getString("PName");
                        String imgPath = rs.getString("imgPath");
                        int price = rs.getInt("price");
                        int quantity = rs.getInt("quantity");
                        OrderDetail od = new OrderDetail(detailID, orderID, plantID, plantName, price, imgPath, quantity);
                        list.add(od);
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
    
    public static OrderDetail getOrderDetails(int FID) throws SQLException{
        OrderDetail od = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            cn = DBUtils.makeConnection();
            if(cn != null){
                String s = "select DetailId, OrderID, FID, PName, imgPath, price, quantity\n"
                        + "from dbo.OrderDetails, dbo.Plants \n"
                        + "where FID = ? and OrderDetails.FID = Plants.PID";
                pst = cn.prepareStatement(s);
                pst.setInt(1, FID);
                rs = pst.executeQuery();
                if(rs != null){
                    while(rs.next()){
                        int detailID = rs.getInt("DetailId");
                        int orderID = rs.getInt("OrderID");
                        String plantName = rs.getString("PName");
                        String imgPath = rs.getString("imgPath");
                        int price = rs.getInt("price");
                        int quantity = rs.getInt("quantity");
                        od = new OrderDetail(detailID, orderID, FID, plantName, price, imgPath, quantity);
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
        return od;
    }
    
    
    
}
