package hr.fer.iot.lab2.clients;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.elements.exception.ConnectorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PubSubClient {

    public static void main(String[] args) {
        CoapClient client = new CoapClient("coap://localhost/sensors/temp");

        CoapObserveRelation relation = client.observe(new CoapHandler() {
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

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            br.readLine();
        } catch (IOException e) { }

        relation.proactiveCancel();
    }
}
