package com.coding.model;

import com.coding.components.FormattedXML;
import com.google.gson.Gson;

import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "car")

public class Car implements Serialize {

    public String model;
    public String name;
    public String color;
    public double kilometers;
    public double position;

    public Car(){}

    public Car(String model, String name, String color) {
        this.model = model;
        this.name = name;
        this.color = color;
    }

    public Car(String model, String name, String color, double kilometers, double position) {
        this.model = model;
        this.name = name;
        this.color = color;
        this.kilometers = kilometers;
        this.position = position;
    }

    public String toString(){
        return  "model='" + model + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", kilometers=" + kilometers +
                ", position=" + position;
    }

    @Override
    public void formJson(String json) {
        Gson gson = new Gson();
        Car car = gson.fromJson(json, Car.class);
        name = car.name;
        color = car.color;
        model = car.model;
    }


    @Override
    public void fromXML(String xml){
        JAXBContext jaxbContext;
        try
        {
            jaxbContext = JAXBContext.newInstance(Car.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Car car = (Car) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            System.out.println(car.toString());

        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }

    }

    public String toJson() {
        return "{" +
                "model='" + model + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", kilometers=" + kilometers +
                ", position=" + position +
                '}';
    }

    public String toXML(){

        FormattedXML formattedXML = new FormattedXML();

        String xml = "";
        List<String> cars = new ArrayList<>();


        cars.add(formattedXML.createTag("model", model ));
        cars.add(formattedXML.createTag("name", name));
        cars.add(formattedXML.createTag("color", color));
        cars.add(formattedXML.createTag("position", position));
        cars.add(formattedXML.createTag("kilometers", kilometers));

        return formattedXML.createTag("car", cars);
    }





}
