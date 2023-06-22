package org.fpm.di.example;

import org.fpm.di.Container;
import org.fpm.di.Environment;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Example {

    private Container container;

    @Before
    public void setUp() {
        Environment env = new DummyEnvironment();
        container = env.configure(new MyConfiguration());
    }

    @Test
    public void shouldInjectSingleton() {
        assertSame(container.getComponent(MySingleton.class), container.getComponent(MySingleton.class));
    }

    @Test
    public void shouldInjectPrototype() {
        assertNotSame(container.getComponent(MyPrototype.class), container.getComponent(MyPrototype.class));
    }

    @Test
    public void shouldBuildInjectionGraph() {
        /*
        binder.bind(A.class, B.class);
        binder.bind(B.class, new B());
        */
        final B bAsSingleton = container.getComponent(B.class);
        assertSame(container.getComponent(A.class), bAsSingleton);
        assertSame(container.getComponent(B.class), bAsSingleton);
    }

    @Test
    public void shouldBuildInjectDependencies() {
        final UseA hasADependency = container.getComponent(UseA.class);
        assertSame(hasADependency.getDependency(), container.getComponent(B.class));
    }

    @Test
    public void shouldBuildInjectDependenciesDTest(){
        final D dTest = container.getComponent(D.class);
        assertSame(container.getComponent(D.class), dTest);
    }

    @Test
    public void shouldBuildInjectDependenciesNewD(){
        final D dTest = container.getComponent(D.class);
        final D newDTest = container.getComponent(D.class);
        assertSame(dTest, newDTest);
    }

    @Test
    public void shouldBuildInjectDependenciesAnother(){
        final TestWithInjectAno testVar = container.getComponent(TestWithInjectAno.class);
        final TestWithInjectAno anotherTestVar = container.getComponent(TestWithInjectAno.class);
        assertSame(testVar.getC(),container.getComponent(C.class));
        assertSame(testVar.getC(), anotherTestVar.getC());
        assertNotSame(testVar, anotherTestVar);
    }

    @Test
    public void shouldBuildSingletonInjection(){
        final D dTest = container.getComponent(D.class);
        final D anotherDTest = container.getComponent(D.class);
        final C cTest = container.getComponent(C.class);
        final C anotherCTest = container.getComponent(C.class);
        assertSame(cTest,anotherCTest);
        assertSame(dTest, anotherDTest);
    }

    @Test
    public void NotEqualInstance() {
        final D dTest = new D();
        final D anotherDTest = container.getComponent(D.class);
        assertNotSame(dTest, anotherDTest);
    }
}
