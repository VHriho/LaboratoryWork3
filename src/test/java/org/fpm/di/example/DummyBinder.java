package org.fpm.di.example;

import org.fpm.di.Binder;

import java.util.ArrayList;
import java.util.HashMap;

public class DummyBinder implements Binder {

    ArrayList<Class<?>> clas= new ArrayList<>();
    HashMap<Class<?>, Object> instances = new HashMap<>();
    HashMap<Class<?>, Class<?>> implementations = new HashMap<>();

    //add clazz which was given to arraylist
    @Override
    public <T> void bind(Class<T> clazz) {
        clas.add(clazz);
    }

    //put key and value into Map structure, key and value will be given into method as a parameters
    @Override
    public <T> void bind(Class<T> clazz, Class<? extends T> implementation) {
        implementations.put(clazz, implementation);
    }

    //same one but with another bind method
    @Override
    public <T> void bind(Class<T> clazz, T instance) {
        instances.put(clazz, instance);
    }

    //make getMethod for implementation(find key which is class what will be given). If not contains return null
    public <T> Class<? extends T> getImplementation (Class<T> clazz){
        if (implementations.containsKey(clazz))
            return ((Class<T>) implementations.get(clazz));
        return null;
    }

    //same but for instance
    public <T> T getInstance(Class<T> clazz){
        if (instances.containsKey(clazz))
            return (T) instances.get(clazz);
        return null;
    }
}
