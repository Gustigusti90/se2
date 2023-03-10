package com.example.se2_intro;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

public class TCPClient {

    private String input;
    private String serverReply;
    private Socket clientSocket;

    public TCPClient(String input) {
        this.input = input;
    }

    public String check() {
        try {
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));     //Create Input Strem
            clientSocket = new Socket("se2-isys.aau.at", 53212);                        // Create Socket and connect to Server
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());  // Create OutputStream with created Socket

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            input = inFromUser.readLine();

            outToServer.writeBytes(input + '\n');

            serverReply = inFromServer.readLine();

            clientSocket.close();

        } catch (IOException e) {
            return e.getMessage();
        }

        return serverReply;
    }

}
