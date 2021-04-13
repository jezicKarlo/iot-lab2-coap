package hr.fer.iot.lab2.resources;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.eclipse.californium.core.server.resources.Resource;

import java.util.Timer;
import java.util.TimerTask;

import static org.eclipse.californium.core.coap.CoAP.ResponseCode.CREATED;
import static org.eclipse.californium.core.coap.CoAP.ResponseCode.METHOD_NOT_ALLOWED;
import static org.eclipse.californium.core.coap.CoAP.Type.CON;

public class SensorsResource extends CoapResource {

    public SensorsResource() {
        super("sensors");
    }

    @Override
    public void handlePOST(CoapExchange exchange) {
        Resource child = new TemperatureResource(exchange.getRequestText());
        add(child);
        exchange.respond(CREATED);
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.respond(METHOD_NOT_ALLOWED);
    }

    private static class TemperatureResource extends CoapResource {

        private double value;

        public TemperatureResource(String name) {
            super(name);
            value = getFirstValue();
            setObservable(true);
            setObserveType(CON);
            getAttributes().setObservable();
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    changed();
                }
            }, 0, 1000);
        }

        private double getFirstValue() {
            String pkg = "<=>€##408521538#node_01#0#TCA:26.13#";
            return Double.parseDouble(pkg.split("#")[5].split(":")[1]);
        }

        public double getValue() {
            double returnValue = value;
            value = nextValue();
            return returnValue;
        }

        private double nextValue() {
            return (double)Math.round((30 - Math.random() * 10)*100)/100;
        }

        @Override
        public void handleGET(CoapExchange exchange) {
            exchange.respond(Double.toString(getValue()));
        }
    }
}
