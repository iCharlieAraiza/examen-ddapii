package com.coding.entity;

import com.coding.model.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectDB {
    String myDriver = "com.mysql.cj.jdbc.Driver";
    String myUrl = "jdbc:mysql://127.0.0.1:3306/DDAP";
    Connection conn;


    public ConnectDB() throws ClassNotFoundException, SQLException {
        Class.forName(myDriver);
        conn = DriverManager.getConnection(myUrl, "root", "Yasoricm10");
        System.out.println("✅ Se ha conectado a la base de datos 🔌");
    }

    public void insertDatabase(Car car) throws SQLException {
        String query = "insert into car (model, name, color)"
                + " values (?, ?, ?)";
        PreparedStatement preparedStmt = conn.prepareStatement(query);

        preparedStmt.setString(1, car.model);
        preparedStmt.setString(2, car.name);
        preparedStmt.setString(3, car.color);

        preparedStmt.execute();

        //System.out.println("✅ Se ha insertado correctamente el registro a la base de datos");
        //System.out.println("✅ Se ha cerrado correctamente la conexión ❌ 🔒 ");

    }

    public List<Car> getAllCars() throws SQLException, ClassNotFoundException {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM car");
        List carList = new ArrayList<Car>();

        while(rs.next()){
            carList.add(new Car(rs.getString("model"), rs.getString("name"), rs.getString("color")));
        }

        //System.out.println("✅ Se ha cerrado correctamente la conexión ❌ 🔒 ");

        return carList;
    }







}