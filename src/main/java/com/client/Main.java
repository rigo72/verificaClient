package com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
public class Main { 
    public static void main(String[] args) throws IOException{
        try(Socket s = new Socket("localhost", 3000);
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            Scanner scanner = new Scanner(System.in)){
                System.out.println("CONNESSO");
                String input;
                while (true) {
                    System.out.println("Inserisci la nota da memorizzare o digita LISTA per vedere tutte le note\n");
                    input = scanner.nextLine();
                    if (input.equalsIgnoreCase("ESCI")) {
                        out.println("!");
                        break;
                    }else if(input.equalsIgnoreCase("LISTA")) {
                        out.println("?");
                        String serverResponse;
                        while (!(serverResponse = in.readLine()).equals("@")) {
                            System.out.println(serverResponse);
                        }
                    }else{
                        System.out.println(input);
                        String response = in.readLine();
                        if ("OK".equals(response)) {
                            System.out.println("Nota salvata");
                        }
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }
            System.out.println("Disconnesso");
        }
}