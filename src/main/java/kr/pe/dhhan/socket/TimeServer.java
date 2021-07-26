package kr.pe.dhhan.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

@Slf4j
public class TimeServer {
    public static void main(String[] args) {
        int port = 9999;
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            log.info("Server listening.. ");

            while(true) {
                Socket socket = serverSocket.accept();
                log.info("[{}] client conected.", socket.getInetAddress());
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output);
                writer.println(new Date().toString());
                writer.flush();

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                log.info("-> {}", reader.readLine());
            }
        } catch (IOException e) {
            log.error("error: {}", e.getMessage(), e);
        }
    }
}
