package com.example.cameltcp;

enum Protocols {
    TLS("TLSv1.2"),
    NETTY("netty");

    public final String value;

    private Protocols(String value) {
        this.value = value;
    }
}
