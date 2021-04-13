package hr.fer.iot.lab2.clients;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.elements.exception.ConnectorException;

import java.io.IOException;

public class PubSubClient {

    public static void main(String[] args) throws ConnectorException, IOException {
        CoapClient client = new CoapClient("coap://localhost/sensors/temp");
        client.observeAndWait(new CoapHandler() {
            @Override
            public void onLoad(CoapResponse coapResponse) {
                if (coapResponse != null && coapResponse.getCode() == CoAP.ResponseCode.CONTENT) {
                    System.out.println("temperature: " + coapResponse.getResponseText());
                } else {
                    System.out.println("Request failed");
                }
            }


            @Override
            public void onError() {
                System.out.println("on error");
            }
        });
    }
}
