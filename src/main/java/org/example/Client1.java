package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 8081);
             Scanner scanner = new Scanner(System.in);
             Scanner in = new Scanner(socket.getInputStream());
             PrintWriter outPut = new PrintWriter(socket.getOutputStream())){
            String name = Client1.class.getSimpleName();
            while (true){
                System.out.println("From "+name+" message: ");
                String out = scanner.nextLine();
                outPut.println(out + " (from " + name+ ")");
                outPut.flush();
                String inCom = in.nextLine();
                System.out.println("Income message: ");
                System.out.println(inCom);
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
