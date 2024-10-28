package com.client;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
public class Main {
    private static Scanner s = new Scanner(System.in);
    public static void main(String[] args) throws IOException{
        System.out.println("Client pronto a collegarsi");
        Socket myClientSocket = new Socket("localhost", 3000);
        String clientChoice;
        BufferedReader in = new BufferedReader(new InputStreamReader(myClientSocket.getInputStream()));
        DataOutputStream out = new DataOutputStream(myClientSocket.getOutputStream());
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println("Inserisci la nota da memorizzare o digita LISTA per vedere tutte le note");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("ESCI")) {
                System.out.println("!");
                break;
            }else if(input.equalsIgnoreCase("LISTA")) {
                System.out.println("?");
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
        s.close();
        myClientSocket.close();
        }
}