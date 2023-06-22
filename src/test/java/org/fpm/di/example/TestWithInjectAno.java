package org.fpm.di.example;

import javax.inject.Inject;

public class TestWithInjectAno {
    C args;

    @Inject
    public TestWithInjectAno(C parameter){
        this.args = parameter;
    }

    public C getC(){
        return args;
    }
}
