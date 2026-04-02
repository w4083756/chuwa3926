package hw7.service;

import hw7.annotation.MyAutowired;
import hw7.annotation.MyComponent;

@MyComponent
public class OrderService {

    @MyAutowired
    private UserRepository userRepository;

    public void printRepositoryId() {
        System.out.println("OrderService -> injected UserRepository id: " + userRepository.getId());
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}