package com.example.cameltcp;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import org.apache.camel.builder.RouteBuilder;

@Component
public class TcpRoute extends RouteBuilder {


    @Override
    public void configure() throws Exception {

        from("direct:test")
                .loadBalance().failover(1, false, true)
                .to("{{partner.endpoint1}}")
                .to("{{partner.endpoint2}}")
                .end()
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        String body = exchange.getIn().getBody(String.class);
                        System.out.println("Body " + body);
                        System.out.println("Url " + exchange.getProperty("CamelToEndpoint"));
                    }
                });


    }
}
