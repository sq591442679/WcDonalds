package com.example.demo;

import sun.applet.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class query {
    Connection conn;
    Statement sta;
    ResultSet rs;
    String sql;
    PreparedStatement psta;
    public query(Connection conn,Statement sta,ResultSet rs,String sql,PreparedStatement psta) {
        this.conn = conn;
        this.sta = sta;
        this.rs = rs;
        this.sql = sql;
        this.psta = psta;
    }

    public List<String> queryRestaurant() throws Exception {
        sql = "SELECT loc_restaurant,accom_restaurant, tele_restaurant FROM restaurant";
        psta = conn.prepareStatement(sql);
        rs = psta.executeQuery();
        List<String> ans = new ArrayList<>();
        while(rs.next()) {
            ans.add(rs.getString(1)+":"+rs.getString(2)+":"+rs.getString(3));
        }
        return ans;
    }

    public List<String> queryEmployee() throws Exception {
        sql = "SELECT id_employee,name_employee FROM employee";
        psta = conn.prepareStatement(sql);
        rs = psta.executeQuery();
        List<String> ans = new ArrayList<>();
        while(rs.next()) {
            ans.add(rs.getInt(1)+":"+rs.getString(2));
        }
        return ans;
    }

    public List<String> queryFood() throws Exception {
        sql = "SELECT id_food,name_food,price_food FROM food";
        psta = conn.prepareStatement(sql);
        rs = psta.executeQuery();
        List<String> ans = new ArrayList<>();
        while(rs.next()) {
            ans.add(rs.getInt(1)+":"+rs.getString(2)+":"+rs.getFloat(3)+"rmb");
        }
        return ans;
    }

    public List<String> querySet() throws Exception {
        sql = "SELECT id_set_meal,name_set_meal,price_set_meal FROM set_meal";
        psta = conn.prepareStatement(sql);
        rs = psta.executeQuery();
        List<String> ans = new ArrayList<>();
        while(rs.next()) {
            ans.add(rs.getInt(1)+":"+rs.getString(2)+":"+rs.getFloat(3)+"rmb");
        }
        return ans;
    }

    public Map<String,List<String >> querySetFood(int x1) throws Exception {
        sql = "SELECT food.id_food,name_food,num FROM set_from,food WHERE set_from.id_food = food.id_food and id_set_meal = ?";
        System.out.println(x1);
        psta = conn.prepareStatement(sql);
        psta.setInt(1,x1);
        rs = psta.executeQuery();
        List<String> ans1 = new ArrayList<>();
        List<String> ans2 = new ArrayList<>();
        while(rs.next()) {
            ans1.add(rs.getInt(1)+":"+rs.getString(2));
            ans2.add(rs.getInt(3)+"");
        }
        Map<String,List<String >> ans = new HashMap<>();
        ans.put("foodName",ans1);
        ans.put("foodNum",ans2);
        return ans;
    }

    public List<String> queryOtherFood(int x1) throws Exception {
        sql = "SELECT id_food,name_food FROM food WHERE id_food not in (SELECT DISTINCT food.id_food FROM food, set_from WHERE id_set_meal = ? and food.id_food = set_from.id_food)";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,x1);
        rs = psta.executeQuery();
        List<String> ans = new ArrayList<>();
        while(rs.next()) {
            ans.add(rs.getInt(1)+":"+rs.getString(2));
        }
        return ans;
    }

    public List<String> queryRecord() throws Exception {
        sql = "SELECT employee_information.id_employee,name_employee,restaurant.id_restaurant,loc_restaurant,in_date,leave_date " +
                "FROM employee_information,employee,restaurant " +
                "WHERE employee_information.id_employee = employee.id_employee " +
                "and employee_information.id_restaurant = restaurant.id_restaurant";
        psta = conn.prepareStatement(sql);
        rs = psta.executeQuery();
        List<String> ans = new ArrayList<>();
        while(rs.next()) {
            ans.add(rs.getInt(1)+":"+rs.getString(2)+":"+rs.getInt(3)+":"+rs.getString(4)+":"+rs.getString(5)+":"+rs.getString(6));
        }
        return ans;
    }

    public List<String> queryEmployeeIdName() throws Exception {
        sql = "SELECT id_employee,name_employee FROM employee";
        psta = conn.prepareStatement(sql);
        rs = psta.executeQuery();
        List<String> ans = new ArrayList<>();
        while(rs.next()) {
            ans.add(rs.getInt(1)+":"+rs.getString(2));
        }
        return ans;
    }

    public List<String> queryRestAddress() throws Exception {
        sql = "SELECT id_restaurant,loc_restaurant FROM restaurant";
        psta = conn.prepareStatement(sql);
        rs = psta.executeQuery();
        List<String> ans = new ArrayList<>();
        while(rs.next()) {
            ans.add(rs.getInt(1)+":"+rs.getString(2));
        }
        return ans;
    }

    public List<String> queryAddress(int x) throws Exception {
        sql = "SELECT id_ai,name,tele,address FROM address_information WHERE id_customer = ?";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,x);
        rs = psta.executeQuery();
        List<String> ans = new ArrayList<>();
        while(rs.next()) {
            ans.add(rs.getInt(1)+":"+rs.getString(2)+":"+rs.getString(3)+":"+rs.getString(4));
        }
        return ans;
    }

    public List<String> queryOrderTime(int x) throws Exception {
        sql = "SELECT id_order,time_order FROM myOrder,address_information WHERE myOrder.id_ai = address_information.id_ai and id_customer = ?";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,x);
        rs = psta.executeQuery();
        List<String> ans = new ArrayList<>();
        while(rs.next()) {
            ans.add(rs.getInt(1)+":"+rs.getString(2));
        }
        return ans;
    }

    public List<String> queryPosition() throws Exception {
        sql = "SELECT name_job,salary_job FROM job";
        psta = conn.prepareStatement(sql);
        rs = psta.executeQuery();
        List<String> ans = new ArrayList<>();
        while(rs.next()) {
            ans.add(rs.getString(1)+":"+rs.getString(2)+"  rmb/month");
        }
        return ans;
    }

    public List<String> queryJob() throws Exception {
        sql = "SELECT name_job,salary_job FROM job";
        psta = conn.prepareStatement(sql);
        rs = psta.executeQuery();
        List<String> ans = new ArrayList<>();
        while(rs.next()) {
            ans.add(rs.getString(1)+":"+rs.getString(2));
        }
        return ans;
    }
}
