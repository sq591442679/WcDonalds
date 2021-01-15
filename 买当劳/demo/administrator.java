package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class administrator {
    Connection conn;
    Statement sta;
    ResultSet rs;
    String sql;
    PreparedStatement psta;
    int restaurant_num=0;
    int employee_num=0;
    int food_num=0;
    int set_num=0;

    public administrator(Connection conn,Statement sta,ResultSet rs,String sql,PreparedStatement psta) {
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
        sql = "SELECT MAX(id_restaurant) FROM restaurant";
        psta = conn.prepareStatement(sql);
        rs = psta.executeQuery();
        if (rs.next()==false) {
            restaurant_num = 0;
        }
        else {
            restaurant_num = rs.getInt(1)+1;
        }
        sql = "SELECT MAX(id_employee) FROM employee";
        psta = conn.prepareStatement(sql);
        rs = psta.executeQuery();
        if (rs.next()==false) {
            employee_num = 0;
        }
        else {
            employee_num = rs.getInt(1)+1;
        }
        sql = "SELECT MAX(id_food) FROM food";
        psta = conn.prepareStatement(sql);
        rs = psta.executeQuery();
        if (rs.next()==false) {
            food_num = 0;
        }
        else {
            food_num = rs.getInt(1)+1;
        }
        sql = "SELECT MAX(id_set_meal) FROM set_meal";
        psta = conn.prepareStatement(sql);
        rs = psta.executeQuery();
        if (rs.next()==false) {
            set_num = 0;
        }
        else {
            set_num = rs.getInt(1)+1;
        }
    }

    public int Login(String x1,String x2) throws Exception {
        sql = "SELECT id_administrator FROM administrator WHERE name_administrator = ?";
        psta = conn.prepareStatement(sql);
        psta.setString(1,x1);
        rs = psta.executeQuery();
        if (rs.next() == false) {
            return -2;
        }
        sql = "SELECT id_administrator FROM administrator WHERE name_administrator = ? and password_administrator = ?";
        psta = conn.prepareStatement(sql);
        psta.setString(1,x1);
        psta.setString(2,x2);
        rs = psta.executeQuery();
        if (rs.next() == false) {
            return -1;
        }
        return 1;
    }

    public int addrestaurant(String x1,String x2,String x3) throws Exception {
        sql = "SELECT id_restaurant FROM restaurant WHERE loc_restaurant = ?";
        psta = conn.prepareStatement(sql);
        psta.setString(1,x1);
        rs = psta.executeQuery();
        if (rs.next()) {
            return 0;
        }
        restaurant_num++;
        sql = "INSERT INTO restaurant (id_restaurant,loc_restaurant,accom_restaurant,tele_restaurant) VALUES (?,?,?,?)";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,restaurant_num);
        psta.setString(2,x1);
        psta.setString(3,x2);
        psta.setString(4,x3);
        psta.executeUpdate();
        return 1;
    }

    public int modifyrestaurant(String x1,String x2,String x3) throws Exception {
        sql = "SELECT id_restaurant FROM restaurant WHERE loc_restaurant = ?";
        psta = conn.prepareStatement(sql);
        psta.setString(1,x1);
        rs = psta.executeQuery();
        if (rs.next()==false) {
            return 0;
        }
        sql = "UPDATE restaurant SET accom_restaurant = ? , tele_restaurant = ? WHERE loc_restaurant = ?";
        psta = conn.prepareStatement(sql);
        psta.setString(1,x2);
        psta.setString(2,x3);
        psta.setString(3,x1);
        psta.executeUpdate();
        return 1;
    }

    public int deleterestaurant(String x1) throws Exception {
        sql = "SELECT id_restaurant FROM restaurant WHERE loc_restaurant = ?";
        psta = conn.prepareStatement(sql);
        psta.setString(1,x1);
        rs = psta.executeQuery();
        if (rs.next()==false) {
            return 0;
        }
        sql = "DELETE FROM restaurant WHERE loc_restaurant = ?";
        psta = conn.prepareStatement(sql);
        psta.setString(1,x1);
        psta.executeUpdate();
        return 1;
    }

    public int addemployee(String x1,String x2,String x3,String x4) throws Exception {
        employee_num++;
        sql = "INSERT INTO employee (id_employee,name_employee,age_employee,occupation_employee,tele_employee) VALUES (?,?,?,?,?)";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,employee_num);
        psta.setString(2,x1);
        psta.setString(3,x2);
        psta.setString(4,x3);
        psta.setString(5,x4);
        psta.executeUpdate();
        return 1;
    }

    public int modifyemployee(int x1,String x2,String x3,String x4,String x5) throws Exception {
        sql = "SELECT id_employee FROM employee WHERE id_employee = ?";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,x1);
        rs = psta.executeQuery();
        if (rs.next()==false) {
            return 0;
        }
        sql = "UPDATE employee SET name_employee = ? , age_employee = ? , occupation_employee = ? , tele_employee = ? WHERE id_employee = ?";
        psta = conn.prepareStatement(sql);
        psta.setInt(5,x1);
        psta.setString(1,x2);
        psta.setString(2,x3);
        psta.setString(3,x4);
        psta.setString(4,x5);
        psta.executeUpdate();
        return 1;
    }

    public int deleteemployee(int x1) throws Exception {
        sql = "SELECT id_employee FROM employee WHERE id_employee = ?";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,x1);
        rs = psta.executeQuery();
        if (rs.next()==false) {
            return 0;
        }
        sql = "DELETE FROM employee WHERE id_employee = ?";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,x1);
        psta.executeUpdate();
        return 1;
    }

    public int addfood(String x1,float x2) throws Exception {
        sql = "SELECT id_food FROM food WHERE name_food = ?";
        psta = conn.prepareStatement(sql);
        psta.setString(1,x1);
        rs = psta.executeQuery();
        if (rs.next()) {
            return 0;
        }
        food_num++;
        sql = "INSERT INTO food (id_food,name_food,price_food) VALUES (?,?,?)";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,food_num);
        psta.setString(2,x1);
        psta.setFloat(3,x2);
        psta.executeUpdate();
        return 1;
    }

    public int modifyfood(String x1,float x2) throws Exception {
        System.out.println("x:"+x1 + " " + x2);
        sql = "UPDATE food SET price_food = ? WHERE name_food = ?";
        psta = conn.prepareStatement(sql);
        psta.setString(2,x1);
        psta.setFloat(1,x2);
        psta.executeUpdate();
        return 1;
    }

    public int deletefood(String x1) throws Exception {
        sql = "DELETE FROM food WHERE name_food = ?";
        psta = conn.prepareStatement(sql);
        psta.setString(1,x1);
        psta.executeUpdate();
        return 1;
    }

    public List<Integer> addset_meal(String x1,float x2) throws Exception {
        List<Integer> ans = new ArrayList<>();
        sql = "SELECT id_set_meal FROM set_meal WHERE name_set_meal = ?";
        psta = conn.prepareStatement(sql);
        psta.setString(1,x1);
        rs = psta.executeQuery();
        if (rs.next()) {
            ans.add(rs.getInt(1));
            ans.add(0);
            return ans;
        }
        set_num++;
        sql = "INSERT INTO set_meal (id_set_meal,name_set_meal,price_set_meal) VALUES (?,?,?)";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,set_num);
        psta.setString(2,x1);
        psta.setFloat(3,x2);
        psta.executeUpdate();
        ans.add(set_num);
        ans.add(1);
        return ans;
    }

    public int modifyset_meal(int x1,String x2,float x3) throws Exception {
        sql = "SELECT id_set_meal FROM set_meal WHERE id_set_meal = ?";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,x1);
        rs = psta.executeQuery();
        if (rs.next()==false) {
            return 0;
        }
        sql = "UPDATE set_meal SET name_set_meal = ? , price_set_meal = ? WHERE id_set_meal = ?";
        psta = conn.prepareStatement(sql);
        psta.setInt(3,x1);
        psta.setString(1,x2);
        psta.setFloat(2,x3);
        psta.executeUpdate();
        return 1;
    }

    public int deleteset_meal(int x1) throws Exception {
        sql = "SELECT id_set_meal FROM set_meal WHERE id_set_meal = ?";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,x1);
        rs = psta.executeQuery();
        if (rs.next()==false) {
            return 0;
        }
        sql = "DELETE FROM set_meal WHERE id_set_meal = ?";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,x1);
        psta.executeUpdate();
        return 1;
    }

    public int addemployee_information(int x1,int x2,String x3,String x4) throws Exception {
        /*sql = "SELECT id_employee FROM employee WHERE id_employee = ?";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,x1);
        rs = psta.executeQuery();
        if (rs.next() == false)
            return -1;                  //员工不存在*/
        sql = "SELECT leave_date FROM employee_information WHERE id_employee = ? and id_restaurant = ? and occupation = ?";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,x1);
        psta.setInt(2,x2);
        psta.setString(3,x4);
        rs = psta.executeQuery();
        while(rs.next()) {
            if (rs.getString(1) == null)
                return 0;               //该餐厅存在该员工的该职位的不存在离职时间的入职记录
        }
        sql = "INSERT INTO employee_information (id_employee,id_restaurant,in_date,occupation) VALUES (?,?,?,?)";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,x1);
        psta.setInt(2,x2);
        psta.setString(3,x3);
        psta.setString(4,x4);
        psta.executeUpdate();
        return 1;
    }

    public int modifyemployee_information(int x1,int x2,String x3,String x4) throws Exception {
        /*sql = "SELECT id_employee FROM employee WHERE id_employee = ?";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,x1);
        rs = psta.executeQuery();
        if (rs.next() == false)
            return -1;                  //员工不存在*/

        sql = "SELECT leave_date FROM employee_information WHERE id_employee = ? and id_restaurant = ? and in_date = ?";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,x1);
        psta.setInt(2,x2);
        psta.setString(3,x3);
        rs = psta.executeQuery();

        if (rs.next() == false)
            return 0;                  //该记录不存在
        sql = "UPDATE employee_information SET leave_date = ? " +
                "WHERE id_employee = ? and id_restaurant = ? and in_date = ?";
        System.out.println(rs.getString(1));
        psta = conn.prepareStatement(sql);
        psta.setString(1,x4);
        psta.setInt(2,x1);
        psta.setInt(3,x2);
        psta.setString(4,x3);
        psta.executeUpdate();
        return 1;
    }

    public int deleteemployee_information(int x1,int x2,String x3) throws Exception {
        sql = "SELECT leave_date FROM employee_information WHERE id_employee = ? and id_restaurant = ? and in_date = ?";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,x1);
        psta.setInt(2,x2);
        psta.setString(3,x3);
        rs = psta.executeQuery();
        if (rs.next() == false)
            return 0;                  //该记录不存在
        sql = "DELETE FROM employee_information WHERE id_employee = ? and id_restaurant = ? and in_date = ?";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,x1);
        psta.setInt(2,x2);
        psta.setString(3,x3);
        psta.executeUpdate();
        return 1;
    }

    public int addset_from(int x1,int x2,int x3) throws Exception {
        sql = "SELECT num FROM set_from WHERE id_set_meal = ? and id_food = ?";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,x1);
        psta.setInt(2,x2);
        rs = psta.executeQuery();
        if (rs.next())
            return -1;         //已存在
        sql = "INSERT INTO set_from (id_set_meal,id_food,num) VALUES (?,?,?)";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,x1);
        psta.setInt(2,x2);
        psta.setInt(3,x3);
        psta.executeUpdate();
        return 1;
    }

    public int modifyset_from(int x1,int x2,int x3) throws Exception {
        sql = "SELECT num FROM set_from WHERE id_set_meal = ? and id_food = ?";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,x1);
        psta.setInt(2,x2);
        rs = psta.executeQuery();
        if (rs.next() == false)
            return 0;                  //该记录不存在
        sql = "UPDATE set_from SET num = ?  " +
                "WHERE id_set_meal = ? and id_food = ?";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,x3);
        psta.setInt(2,x1);
        psta.setInt(3,x2);
        psta.executeUpdate();
        return 1;
    }

    public int deleteset_from(int x1,int x2) throws Exception {
        sql = "SELECT num FROM set_from WHERE id_set_meal = ? and id_food = ?";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,x1);
        psta.setInt(2,x2);
        rs = psta.executeQuery();
        if (rs.next() == false)
            return 0;                  //该记录不存在
        sql = "DELETE FROM set_from WHERE id_set_meal = ? and id_food = ?";
        psta = conn.prepareStatement(sql);
        psta.setInt(1,x1);
        psta.setInt(2,x2);
        psta.executeUpdate();
        return 1;
    }

    public int addPosition(String x1,String x2) throws Exception {
        sql = "SELECT name_job FROM job WHERE name_job = ?";
        psta = conn.prepareStatement(sql);
        psta.setString(1,x1);
        rs = psta.executeQuery();
        if (rs.next()) {
            return 0;
        }
        sql = "INSERT INTO job (name_job,salary_job) VALUES (?,?)";
        psta = conn.prepareStatement(sql);
        psta.setString(1,x1);
        psta.setString(2,x2);
        psta.executeUpdate();
        return 1;
    }

    public int modifyPosition(String x1,String x2) throws Exception {
        sql = "UPDATE job SET salary_job = ? WHERE name_job = ?";
        psta = conn.prepareStatement(sql);
        psta.setString(2,x1);
        psta.setString(1,x2);
        psta.executeUpdate();
        return 1;
    }

    public int deletePosition(String x1) throws Exception {
        sql = "DELETE FROM job WHERE name_job = ?";
        System.out.println(x1);
        psta = conn.prepareStatement(sql);
        psta.setString(1,x1);
        psta.executeUpdate();
        return 1;
    }
}
