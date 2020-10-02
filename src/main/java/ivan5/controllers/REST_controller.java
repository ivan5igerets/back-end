package ivan5.controllers;

import ivan5.models.Bulletin;
import ivan5.models.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/")
public class REST_controller {

    // "/"
    // метод возвращаюший список обьявлений
    @PostMapping
    public ArrayList<Bulletin> showBulletin() {
        ArrayList<Bulletin> list = new ArrayList<Bulletin>();

        list.add(new Bulletin("title 1", "some text", "name", "14:06"));
        list.add(new Bulletin("title 2", "some text", "name", "14:06"));
        list.add(new Bulletin("title 3", "some text", "name", "14:06"));
        list.add(new Bulletin("title 4", "some text", "name", "14:06"));
        list.add(new Bulletin("title 5", "some text", "name", "14:06"));

        System.out.println("path: /");

        return list;
    }

    // post("/add_new_bulletin")
    // добавляет новое объявление в базу данных
    @PostMapping("/add_new_bulletin")
    private boolean addNewBulletin(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String title, @RequestParam String body) {
        System.out.println("/add_new_bulletin");
        System.out.println(title);
        System.out.println(body);
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(new Date().toString());

        // нужно записать в базу данных

        return true;
    }

    // post("/edit_user_profile")
    // обновить информацию о пользователе, помимо формы нужно прислать email
    @PostMapping("/edit_user_profile")
    private boolean editUserProfile(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email) {

        System.out.println("/edit_user_profile");

        return true;
    }

    // авторизация
    @PostMapping("/login")
    private User logIn(@RequestParam String email, @RequestParam String pass) {
        // проверить существование пользователя с такой почтой
        // если такого не существует то создать и записать в бд


        User user = new User("Иван", "Пятигорец", email);

        System.out.println(user);
        System.out.print("/login");

        // но это временный отвер должен пройти анализ данный и только тогда определимся с ответом
        return user;
    }

    // регистрация
    @PostMapping("/signup")
    private boolean signUp(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String pass) {
        // проверить существование пользователя с такой почтой
        // если такого не существует то создать и записать в бд

        User user = new User(firstName, lastName, email);

        System.out.println(user);
        System.out.print("/signup");

        // но это временный отвер должен пройти анализ данный и только тогда определимся с ответом
        return true;
    }


}
