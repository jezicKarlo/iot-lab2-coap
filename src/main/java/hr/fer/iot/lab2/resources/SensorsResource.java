package hr.fer.iot.lab2.resources;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.eclipse.californium.core.server.resources.Resource;

import static org.eclipse.californium.core.coap.CoAP.ResponseCode.*;
import static org.eclipse.californium.core.coap.CoAP.ResponseCode.CREATED;

public class SensorsResource extends CoapResource {

    public SensorsResource() {
        super("sensors");
    }

    @Override
    public void handlePOST(CoapExchange exchange) {
        Resource child = new ChildResource(exchange.getRequestText());
        add(child);
        exchange.respond(CREATED);
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.respond(METHOD_NOT_ALLOWED);
    }

    private static class ChildResource extends CoapResource {

        public ChildResource(String name) {
            super(name);
        }

        @Override
        public void handleGET(CoapExchange exchange) {
            exchange.respond(this.getName());
        }
    }
}
