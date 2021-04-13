package hr.fer.iot.lab2.clients;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.elements.exception.ConnectorException;

import java.io.IOException;

public class GetClient {

    public static void main(String[] args) throws ConnectorException, IOException {
        CoapClient client = new CoapClient("coap://localhost/sensors/temp");
        CoapResponse coapResponse = client.get();

        if (coapResponse != null) {
            System.out.println(coapResponse.getCode());
            System.out.println(coapResponse.getResponseText());
        } else {
            System.out.println("Request failed");
        }
    }
}
