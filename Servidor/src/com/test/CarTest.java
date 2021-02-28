package com.test;

import com.coding.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    Car car;

    @BeforeEach
    void before_each(){
        car = new Car("A", "B", "C");
    }

    @Test
    void toXml(){
        assertEquals("<car><model>A</model><name>B</name><color>C</color><position>0.0</position><kilometers>0.0</kilometers></car>", car.toXML());
    }

    @Test
    void toJson(){
        assertEquals("{model='A', name='B', color='C', kilometers=0.0, position=0.0}", car.toJson());
    }


    @Test
    void fromXML(){
        Car carTest = new Car();

        carTest.fromXML("<car>" + "<model>A</model>" +
                                "<name>B</name>" +
                                "<color>C</color>" +
                                "<position>0.0</position>" +
                                "<kilometers>0.0</kilometers>" +
                         "</car>");

    }

    @Test
    void fromJSON() {
        Car carTest = new Car();

        carTest.formJson("{model='A', name='B', color='C', kilometers=0.0, position=0.0}");
        System.out.println(carTest.toString());
        assertEquals("{model='A', name='B', color='C', kilometers=0.0, position=0.0}", carTest.toJson());
    }



}