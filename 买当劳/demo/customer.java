package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class customer {
    Connection conn;
    Statement sta;
    ResultSet rs;
    String sql;
    PreparedStatement psta;
    int customer_num = 0;
    int customer_id;
    int address_info_num = 0;
    int order_num = 0;
    float tot_price = 0;
    int feedback_num = 0;

    public customer(Connection conn,Statement sta,ResultSet rs,String sql,PreparedStatement psta) {
        this.conn = conn;
        this.sta = sta;
        this.rs = rs;
        this.sql = sql;
        this.psta = psta;
        try {
            initial_num();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initial_num() throws Exception{
        sql = "SELECT MAX(id_customer) FROM customer";
        psta = conn.prepareStatement(sql);
        rs = psta.executeQuery();
        if (rs.next()==false) {
            customer_num = 0;
        }
        else {
            customer_num = rs.getInt(1)+1;
        }
        sql = "SELECT MAX(id_ai) FROM address_information";
        psta = conn.prepareStatement(sql);
        rs = psta.executeQuery();
        if (rs.next()==false) {
            address_info_num = 0;
        }
        else {
            address_info_num = rs.getInt(1)+1;
        }
        sql = "SELECT MAX(id_order) FROM myorder";
        psta = conn.prepareStatement(sql);
        rs = psta.executeQuery();
        if (rs.next()==false) {
            order_num = 0;
        }
        else {
            order_num = rs.getInt(1)+1;
        }
        sql = "SELECT MAX(id_feedback) FROM feedback";
        psta = conn.prepareStatement(sql);
        rs = psta.executeQuery();
        if (rs.next()==false) {
            feedback_num = 0;
        }
        else {
            feedback_num = rs.getInt(1)+1;
        }
    }

    public int Regist(String x1,String x2) throws Exception {
        sql = "SELECT id_customer FROM customer WHERE username_customer = ?";
        psta = conn.prepareStatement(sql);
        psta.setString(1,x1);
        rs = psta.executeQuery();
        if (rs.next()) {
            return -1;
        }
        customer_num++;
        sql = "INSERT INTO customer (id_customer,username_customer,password_customer) VALUES (?,?,?)";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,customer_num);
        psta.setString(2,x1);
        psta.setString(3,x2);
        psta.executeUpdate();
        return 1;
    }

    public List<Integer> Login(String x1, String x2) throws Exception {
        sql = "SELECT id_customer FROM customer WHERE username_customer = ?";
        psta = conn.prepareStatement(sql);
        psta.setString(1,x1);
        rs = psta.executeQuery();
        List<Integer> ans = new ArrayList<>();
        if (rs.next() == false) {
            ans.add(-2);
            ans.add(0);
            return ans;
        }
        sql = "SELECT id_customer FROM customer WHERE username_customer = ? and password_customer = ?";
        psta = conn.prepareStatement(sql);
        psta.setString(1,x1);
        psta.setString(2,x2);
        rs = psta.executeQuery();
        if (rs.next() == false) {
            ans.add(-1);
            ans.add(0);
            return ans;
        }
        customer_id = rs.getInt(1);
        ans.add(1);
        ans.add(customer_id);
        return ans;
    }

    public int add_address_info(String x1,String x2,String x3) throws Exception {
        sql = "SELECT id_ai FROM address_information WHERE name = ? and tele = ? and address = ? and id_customer = ?";
        psta = conn.prepareStatement(sql);
        psta.setString(1,x1);
        psta.setString(2,x2);
        psta.setString(3,x3);
        psta.setInt(4,customer_id);
        rs = psta.executeQuery();
        if (rs.next()) {
            return -1;                  //已存在相同地址信息
        }
        address_info_num++;
        sql = "INSERT INTO address_information (id_ai,name,tele,address,id_customer) VALUES (?,?,?,?,?)";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,address_info_num);
        psta.setString(2,x1);
        psta.setString(3,x2);
        psta.setString(4,x3);
        psta.setInt(5,customer_id);
        psta.executeUpdate();
        return 1;
    }

    public int modify_address_info(int x1,String x2,String x3,String x4) throws Exception {
        sql = "UPDATE address_information SET name = ? , tele = ? , address = ? WHERE id_ai = ?";
        psta = conn.prepareStatement(sql);
        psta.setInt(4,x1);
        psta.setString(1,x2);
        psta.setString(2,x3);
        psta.setString(3,x4);
        psta.executeUpdate();
        return 1;
    }

    public int delete_address_info(int x1) throws Exception {
        sql = "DELETE FROM address_information WHERE id_ai = ?";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,x1);
        psta.executeUpdate();
        return 1;
    }

    public int add_order_begin() {
        order_num++;
        tot_price = 0;
        return 1;
    }

    public int add_foodorder(int x1,int x2) throws Exception  {
        sql = "SELECT price_food FROM food WHERE id_food = ?";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,x1);
        rs = psta.executeQuery();
        if (rs.next() == false) {
            return -1;                  //食物不存在
        }
        Float price = rs.getFloat(1) * x2;
        sql = "INSERT INTO food_order (id_order,id_food,num_food) VALUES (?,?,?)";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,order_num);
        psta.setInt(2,x1);
        psta.setInt(3,x2);
        psta.executeUpdate();
        tot_price+=price;
        return 1;
    }

    public int add_set_meal_order(int x1,int x2) throws Exception  {
        sql = "SELECT price_set_meal FROM set_meal WHERE id_set_meal = ?";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,x1);
        rs = psta.executeQuery();
        if (rs.next() == false) {
            return -1;                  //食物不存在
        }
        Float price = rs.getFloat(1) * x2;
        sql = "INSERT INTO set_meal_order (id_order,id_set_meal,num_set_meal) VALUES (?,?,?)";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,order_num);
        psta.setInt(2,x1);
        psta.setInt(3,x2);
        psta.executeUpdate();
        tot_price+=price;
        return 1;
    }

    public float add_order_end(String x1,int x2,String x3) throws Exception  {
        sql = "SELECT id_restaurant FROM restaurant WHERE loc_restaurant = ?";
        psta = conn.prepareStatement(sql);
        psta.setString(1,x1);
        rs=psta.executeQuery();
        if (rs.next() == false) {
            System.out.println("RFGHJ");
        }
//        rs.next();
        int t = rs.getInt(1);
        sql = "INSERT INTO myOrder (id_order,id_restaurant,id_ai,tot_price,time_order) VALUES (?,?,?,?,?)";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,order_num);
        psta.setInt(2,t);
        psta.setInt(3,x2);
        psta.setFloat(4,tot_price);
        psta.setString(5,x3);
        psta.executeUpdate();
        System.out.println(tot_price);
        return tot_price;
    }

    public int add_feedback(int x1,int x2,String x3,String x4) throws Exception  {
        sql = "SELECT time_feedback FROM feedback WHERE id_customer = ? and id_order = ?";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,x1);
        psta.setInt(2,x2);
        rs = psta.executeQuery();
        if (rs.next()) {
            return -1;                  //食物不存在
        }
        feedback_num++;
        sql = "INSERT INTO feedback (id_feedback,id_customer,id_order,time_feedback,content_feedback) VALUES (?,?,?,?,?)";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,feedback_num);
        psta.setInt(2,x1);
        psta.setInt(3,x2);
        psta.setString(4,x3);
        psta.setString(5,x4);
        psta.executeUpdate();
        return 1;
    }

}
