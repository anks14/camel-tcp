package com.example.cameltcp;

import org.apache.camel.CamelContext;


import org.apache.camel.component.netty.NettyComponent;
import org.apache.camel.support.jsse.KeyManagersParameters;
import org.apache.camel.support.jsse.KeyStoreParameters;
import org.apache.camel.support.jsse.SSLContextParameters;
import org.apache.camel.support.jsse.TrustManagersParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Paths;

@Configuration
public class SSLConfig {

    @Value("${ssl.keystore.path}")
    private String keyStorePath;

    @Value("${ssl.truststore.path}")
    private String trustStorePath;


    @Value("${keystore.password}")
    private String keyStorePassword;

    @Bean
    public boolean sslContextParameters(@Autowired CamelContext camelContext) {

        KeyStoreParameters keyStoreParameters = new KeyStoreParameters();
      //  String keyPath = Paths.get(keyStorePath).toAbsolutePath().toString();
        keyStoreParameters.setResource("keyPath");
        keyStoreParameters.setPassword(keyStorePassword);
        keyStoreParameters.setType("jks");

        KeyManagersParameters keyManagersParameters = new KeyManagersParameters();
        keyManagersParameters.setKeyStore(keyStoreParameters);
        keyManagersParameters.setKeyPassword(keyStorePassword);


        KeyStoreParameters trustStoreParameters = new KeyStoreParameters();
        //String trustPath = Paths.get(trustStorePath).toAbsolutePath().toString();
        trustStoreParameters.setResource("trustPath");
        trustStoreParameters.setPassword(keyStorePassword);
        trustStoreParameters.setType("jks");

        TrustManagersParameters trustManagersParameters = new TrustManagersParameters();
        trustManagersParameters.setKeyStore(trustStoreParameters);

        SSLContextParameters sslContextParameters = new SSLContextParameters();
        sslContextParameters.setKeyManagers(keyManagersParameters);
        sslContextParameters.setTrustManagers(trustManagersParameters);
        sslContextParameters.setSecureSocketProtocol(Protocols.TLS.value);

        camelContext.setSSLContextParameters(sslContextParameters);

        camelContext.getComponent(Protocols.NETTY.value, NettyComponent.class).setUseGlobalSslContextParameters(true);

        return Boolean.TRUE;

    }

}
