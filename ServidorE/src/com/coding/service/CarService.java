package com.coding.service;

import com.coding.entity.ConnectDB;
import com.coding.model.Car;

import java.sql.SQLException;
import java.util.List;

public class CarService {
    public List<Car> cars;
    ConnectDB db;

    public CarService(ConnectDB db){
        this.db = db;
    }

    public List<Car> selectAll() throws SQLException, ClassNotFoundException {
        return db.getAllCars();
    }

    public void create(Car car) throws SQLException {
        db.insertDatabase(car);
    }


}
