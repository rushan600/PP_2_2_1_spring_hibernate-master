package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "LastName1", "user1@mail.io");
        User user2 = new User("User2", "LastName2", "user2@mail.io");
        User user3 = new User("User3", "LastName3", "user3@mail.io");
        User user4 = new User("User4", "LastName4", "user4@mail.io");

        Car car1 = new Car("Car1", 1);
        Car car2 = new Car("Car2", 2);
        Car car3 = new Car("Car3", 3);
        Car car4 = new Car("Car4", 4);

        userService.add(user1.setCar(car1).setUser(user1));
        userService.add(user2.setCar(car2).setUser(user2));
        userService.add(user3.setCar(car3).setUser(user3));
        userService.add(user4.setCar(car4).setUser(user4));

        for (User user : userService.listUsers()) {
            System.out.println(user + " " + user.getCar());
        }
        context.close();
    }
}