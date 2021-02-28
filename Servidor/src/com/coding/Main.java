package com.coding;

import com.coding.components.FormattedXML;
import com.coding.entity.ConnectDB;
import com.coding.model.Car;
import com.coding.service.CarService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int puerto=8000;

        try {


            CarService carService = new CarService(new ConnectDB());

            ServerSocket miServidor = new ServerSocket(puerto);
            Socket miCliente;

            System.out.println("Esperando Conexion...");

            miCliente= miServidor.accept();

            System.out.println("Recibi Conexion");


            //----- Habilitar lectura y escritura del Socket
            BufferedReader lectorSocket;
            PrintWriter escritorSocket;

            InputStreamReader entradaDatos = new InputStreamReader(miCliente.getInputStream());
            lectorSocket= new BufferedReader(entradaDatos );

            escritorSocket= new PrintWriter(miCliente.getOutputStream(),true);
            //----


            String mensajeRespuesta = "", mensajeRecibido="",lineaRecibida;
            int opcion=0;

            System.out.println("Esperando mensaje....");


            lineaRecibida= lectorSocket.readLine();
            mensajeRecibido=lineaRecibida+"\n";

            lectorSocket.readLine();
            String body = lectorSocket.readLine();

            System.out.println(lineaRecibida);

            if (lineaRecibida.contains("GET"))
            {
                opcion=1;

                FormattedXML formattedXML = new FormattedXML();
                List<Car> cars = carService.selectAll();

                String xmlResponse ="";

                for(Car car : cars){
                    xmlResponse += car.toXML();
                }

                xmlResponse = formattedXML.createTag("cars", xmlResponse);
                System.out.println(xmlResponse);

                mensajeRespuesta="HTTP/1.1 200 OK\n";
                mensajeRespuesta+="Content-Type: text/xml\n";
                mensajeRespuesta+="Content-Length: " + xmlResponse.length() + "\n\n" ;
                mensajeRespuesta+= xmlResponse;
                escritorSocket.println(mensajeRespuesta);


            }
            else if (lineaRecibida.contains("POST"))
            {
                opcion=2;

                System.out.println("Paquete recibido:" + body);

                Car car = new Car();
                car.formJson(body);
                carService.create(car);

                mensajeRespuesta="HTTP/1.1 200 OK\n";
                mensajeRespuesta+="Content-Type: text/json\n";
                mensajeRespuesta+="Content-Length: " + car.toJson().length()+ "\n\n" ;
                mensajeRespuesta+= car.toJson();

                escritorSocket.println(mensajeRespuesta);

            }
            else {
                opcion=-1;
            }

            System.out.println("Opcion: " + opcion);



        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("FIN del Programa");

    }
}
