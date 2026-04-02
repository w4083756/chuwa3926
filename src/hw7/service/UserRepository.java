package hw7.service;

import hw7.annotation.MyComponent;
import hw7.annotation.MyScope;

import java.util.UUID;

@MyComponent
@MyScope("prototype")
public class UserRepository {
    private final String id = UUID.randomUUID().toString();

    public String getId() {
        return id;
    }
}