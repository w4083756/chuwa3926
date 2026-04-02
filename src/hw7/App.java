package hw7;

import hw7.context.MyBeanFactory;
import hw7.service.OrderService;
import hw7.service.UserRepository;
import hw7.service.UserService;

import java.util.Set;

public class App {
    public static void main(String[] args) {
        MyBeanFactory factory = new MyBeanFactory(Set.of(
                UserRepository.class,
                UserService.class,
                OrderService.class
        ));

        // 1) prove singleton scope
        UserService userService1 = factory.getBean(UserService.class);
        UserService userService2 = factory.getBean(UserService.class);

        System.out.println("userService1 == userService2 ? " + (userService1 == userService2));

        // 2) prove prototype scope
        UserRepository repo1 = factory.getBean(UserRepository.class);
        UserRepository repo2 = factory.getBean(UserRepository.class);

        System.out.println("repo1 == repo2 ? " + (repo1 == repo2));
        System.out.println("repo1 id = " + repo1.getId());
        System.out.println("repo2 id = " + repo2.getId());

        // 3) prove DI works
        userService1.printRepositoryId();

        OrderService orderService = factory.getBean(OrderService.class);
        orderService.printRepositoryId();

        // 4) compare injected prototype objects in different singleton beans
        System.out.println(
                "userService injected repo == orderService injected repo ? " +
                        (userService1.getUserRepository() == orderService.getUserRepository())
        );
    }
}