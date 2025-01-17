package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      CarService carService = context.getBean(CarService.class);

      //Car
      Car mazda = new Car("Mazda", 4444);
      Car mitsubishi = new Car("Mitsubishi", 1010);
      Car lada = new Car("Lada", 2107);
      Car porsche = new Car("Porsche", 3429);

      userService.addCar(mazda);
      userService.addCar(mitsubishi);
      userService.addCar(lada);
      userService.addCar(porsche);

      //User
      userService.add(new User("User1", "Lastname1", "user1@mail.ru", mazda));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", mitsubishi));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", lada));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", porsche));


      System.out.println("User with Car, : " + userService.findUserByCar("Mazda", 4444));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println("------------------");
      }



      context.close();
   }
}
