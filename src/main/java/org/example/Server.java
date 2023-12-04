package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(8081)){
            while(true){
                Runnable runnable = new Runnable() {
                    final Socket input = serverSocket.accept();
                    @Override
                    public void run() {
                        try (Scanner in = new Scanner(input.getInputStream());
                             Scanner forSend = new Scanner(System.in);
                             PrintWriter outPut = new PrintWriter(input.getOutputStream())){
                            String name = Server.class.getSimpleName();
                            while(in.hasNext()){
                                System.out.println("Income message: ");
                                System.out.println(in.nextLine());
                                System.out.println("From "+name+" message: ");
                                String out = forSend.nextLine();
                                outPut.println(out + " (from " + name+ ")");
                                outPut.flush();
                            }
                        }catch(IOException ex){
                            System.out.println(ex.getMessage());
                        }
                    }
                };
                Thread thread = new Thread(runnable);
                thread.start();
            }
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }

    }
}
