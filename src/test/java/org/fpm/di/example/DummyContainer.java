package org.fpm.di.example;

import org.fpm.di.Container;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class DummyContainer implements Container {

   DummyBinder dummyBinder;

    public DummyContainer(DummyBinder dummyBinder){
        this.dummyBinder = dummyBinder;
    }

    @Override
    public <T> T getComponent(Class<T> clazz) {
        T type = null;
        T instances = dummyBinder.getInstance(clazz);
        if(instances != null) {
            return instances;
        }
        Class<? extends T> extension = dummyBinder.getImplementation(clazz);
        if(extension!= null){
            return getComponent((Class<T>)dummyBinder.implementations.get(clazz));}
        for(Constructor<?> constructor: clazz.getConstructors()) {
            if (constructor.isAnnotationPresent(Inject.class)) {
                try {
                    type = (T) constructor.newInstance(getComponent(constructor.getParameterTypes()[0]));
                }
                catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            if (type == null) {
                try {
                    type = clazz.newInstance();
                }
                catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            if (clazz.isAnnotationPresent(Singleton.class)) {
                dummyBinder.bind(clazz, type);
            }
        }
        return type;
    }
}
