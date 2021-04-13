package hr.fer.iot.lab2.server;

import hr.fer.iot.lab2.resources.SensorsResource;
import org.eclipse.californium.core.CoapServer;

public class TempHumCoapServer {

    public static void main(String[] args) {
        CoapServer server = new CoapServer();
        server.add(new SensorsResource());
        server.start();
    }
}
