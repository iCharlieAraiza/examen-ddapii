package com.company.model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

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
    public void formJson(String XML) {

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

    @Override
    public String toXML() {
        return null;
    }
}
