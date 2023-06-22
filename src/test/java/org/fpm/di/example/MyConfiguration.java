package org.fpm.di.example;

import org.fpm.di.Binder;
import org.fpm.di.Configuration;

public class MyConfiguration implements Configuration {
    @Override
    public void configure(Binder binder) {
        binder.bind(MySingleton.class);
        binder.bind(MyPrototype.class);

        binder.bind(UseA.class);

        binder.bind(A.class, B.class);
        binder.bind(B.class, new B());

        binder.bind(TestWithInjectAno.class);
        binder.bind(D.class);

        binder.bind(C.class, D.class);
        binder.bind(D.class, new D());
    }
}
