package hw7.context;

import hw7.annotation.MyAutowired;
import hw7.annotation.MyComponent;
import hw7.annotation.MyScope;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyBeanFactory {

    private final Map<Class<?>, Object> singletonObjects = new HashMap<>();
    private final Map<Class<?>, Class<?>> beanDefinitions = new HashMap<>();

    public MyBeanFactory(Set<Class<?>> classesToRegister) {
        for (Class<?> clazz : classesToRegister) {
            if (clazz.isAnnotationPresent(MyComponent.class)) {
                beanDefinitions.put(clazz, clazz);
            }
        }

        // eagerly create singleton beans
        for (Class<?> clazz : beanDefinitions.keySet()) {
            if (isSingleton(clazz)) {
                getBean(clazz);
            }
        }
    }

    public <T> T getBean(Class<T> clazz) {
        Class<?> beanClass = findImplementation(clazz);
        if (beanClass == null) {
            throw new RuntimeException("No bean found for type: " + clazz.getName());
        }

        if (isSingleton(beanClass)) {
            if (!singletonObjects.containsKey(beanClass)) {
                Object bean = createBean(beanClass);
                singletonObjects.put(beanClass, bean);
            }
            return clazz.cast(singletonObjects.get(beanClass));
        } else {
            return clazz.cast(createBean(beanClass));
        }
    }

    private boolean isSingleton(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(MyScope.class)) {
            return true; // default singleton
        }
        return "singleton".equalsIgnoreCase(clazz.getAnnotation(MyScope.class).value());
    }

    private Object createBean(Class<?> clazz) {
        try {
            Object instance = clazz.getDeclaredConstructor().newInstance();

            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(MyAutowired.class)) {
                    Object dependency = getBean(field.getType());
                    field.setAccessible(true);
                    field.set(instance, dependency);
                }
            }

            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create bean: " + clazz.getName(), e);
        }
    }

    private Class<?> findImplementation(Class<?> type) {
        if (beanDefinitions.containsKey(type)) {
            return beanDefinitions.get(type);
        }

        for (Class<?> candidate : beanDefinitions.keySet()) {
            if (type.isAssignableFrom(candidate)) {
                return candidate;
            }
        }

        return null;
    }
}