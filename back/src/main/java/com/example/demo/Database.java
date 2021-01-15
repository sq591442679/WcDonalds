package com.example.demo;

import  java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    Connection conn;
    Statement sta;
    ResultSet rs;
    String sql;
    PreparedStatement psta;

    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/yzqk_database?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "shanqian0016");
            sta = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public administrator make_ad() {
        return new administrator(conn,sta,rs,sql,psta);
    }

    public customer make_cu() {
        return new customer(conn,sta,rs,sql,psta);
    }

    public query make_qu() {
        return new query(conn,sta,rs,sql,psta);
    }
}
