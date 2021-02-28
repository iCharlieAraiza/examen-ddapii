package com.company;

import com.company.model.Car;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    static private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static private Socket myClient = null;
    static private String host = "127.0.0.1";
    static private int port = 8000;
    PrintWriter socketWriter =new PrintWriter( myClient.getOutputStream(), true);

    public Main() throws IOException {
    }


    public static void main(String[] args) {

        try {
            System.out.println("Conectando al servidor");
            myClient = new Socket(host, port);

            InputStreamReader streamSocket = new InputStreamReader(myClient.getInputStream());
            PrintWriter socketWriter =new PrintWriter( myClient.getOutputStream(), true);
            BufferedReader bufferedReader =new BufferedReader(streamSocket);


            System.out.println("Escribe una opción (con número):");
            System.out.println("(1) GET");
            System.out.println("(2) POST");
            System.out.println("Cualquier otro número para salir");

            int op = 0;
            try {
                op = Integer.parseInt(br.readLine());
            }catch (Exception e){
                System.out.println(e.getMessage());
            }


            if(op==1){
                System.out.println("GET");
                GET(socketWriter);
                String response = "";

                for(int i = 0; i<5; i++){
                    response+=bufferedReader.readLine()+"\n";
                }

                System.out.println("Respuesta en XML " + response);

            }else if(op ==2){
                System.out.println("POST");
                POST(socketWriter);
                String response = "";

                for(int i = 0; i<5; i++){
                    response+=bufferedReader.readLine()+"\n";
                }

                System.out.println("Respuesta en JSON " + response);
            }else{
                socketWriter.println("");
            }

        }catch (Exception ex){
            System.out.println(ex);
        }


    }


    static void GET(PrintWriter socketWriter) throws IOException {
        String peticion = header("GET", "");

        socketWriter.println(peticion);
        //Persona persona = new Persona();
    }

    static void POST(PrintWriter socketWriter) throws IOException {
        String color, model, name;


        System.out.println("Escribe el modelo: ");
        model = br.readLine();

        System.out.println("Escribe el nombre: ");
        name = br.readLine();

        System.out.println("Escribe el color: ");
        color = br.readLine();

        Car car = new Car(model, name, color);

        String peticion = header("POST", car.toJson());

        socketWriter.println(peticion);
    }

    static String header(String method, String body){
        return method + "\n\n" + body;
    }

}

