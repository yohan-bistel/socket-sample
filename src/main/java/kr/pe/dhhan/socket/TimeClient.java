package kr.pe.dhhan.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

@Slf4j
public class TimeClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 9999;
        try(Socket socket = new Socket(host, port)) {
            log.info("server {}:{} conected.", host, port);
            OutputStream output = socket.getOutputStream();
            String realStr = "This is test";
            output.write(realStr.getBytes());
            output.flush();

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String time = reader.readLine();
            log.info("time: {}", time);

        } catch (IOException e) {
            log.error("error {}", e.getMessage(), e);
        }
    }
}
