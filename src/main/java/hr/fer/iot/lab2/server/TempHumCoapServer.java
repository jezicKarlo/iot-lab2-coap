package hr.fer.iot.lab2.server;

import org.eclipse.californium.core.CoapServer;

public class TempHumCoapServer {

    public static void main(String[] args) {
        CoapServer server = new CoapServer();
        server.start();
    }
}
