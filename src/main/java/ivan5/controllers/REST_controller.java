package ivan5.controllers;

import ivan5.models.Bulletin;
import ivan5.models.User;
import ivan5.repo.BulletinRepository;
import ivan5.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/")
public class REST_controller {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BulletinRepository bulletinRepository;


    @PostMapping
    public Iterable<Bulletin> showBulletin(@RequestParam int page) {
        System.out.println("path: /");

        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("time").descending());
        Page<Bulletin> bulletins = bulletinRepository.findAll(pageable);
        List<Bulletin> list = bulletins.getContent();

        return list;
    }
    
    // post("/add_new_bulletin")
    // добавляет новое объявление в базу данных
    @PostMapping("/add_new_bulletin")
    private boolean addNewBulletin(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String title, @RequestParam String body) {
        System.out.println("/add_new_bulletin");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        bulletinRepository.save(new Bulletin(title, body, firstName + " " + lastName, dateFormat.format(new Date())));

        return true;
    }

    // post("/edit_user_profile")
    // обновить информацию о пользователе, помимо формы нужно прислать email
    @PostMapping("/edit_user_profile")
    private boolean editUserProfile(@RequestParam String firstName, @RequestParam String lastName, @RequestParam Long id, @RequestParam String email) {
        System.out.println("/edit_user_profile");

        System.out.println(id);

        User user = userRepository.findById(id).get();
        boolean isChange = false;

        if (!user.getFirstName().equals(firstName)) {
            user.setFirstName(firstName);
            isChange = true;
        }

        if (!user.getLastName().equals(lastName)) {
            user.setLastName(lastName);
            isChange = true;
        }

        if (!user.getEmail().equals(email)) {
            user.setEmail(email);
            isChange = true;
        }

        if (isChange) {
            userRepository.save(user);
        }

        return true;
    }

    // авторизация
    @PostMapping("/login")
    private User logIn(@RequestParam String email, @RequestParam String pass) {
        ArrayList<User>  users = (ArrayList<User>) userRepository.findAll();

        if (users.size() == 0) {
            return null;
        }

        for (User user : users) {
            if (user.getEmail().equals(email)) {
                if (user.getPassword().equals(pass)) {
                    user.setPassword("хуй_в_сраку");
                    System.out.println(user);
                    System.out.print("/login");
                    return user;
                }
            }
        }

        return null;
    }

    // регистрация
    @PostMapping("/signup")
    private Long signUp(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String pass) {
        ArrayList<User>  users = (ArrayList<User>) userRepository.findAll();

        if (users.size() == 0) {
            User user = new User(firstName, lastName, email, pass);
            userRepository.save(user);
            System.out.println(user.getId());
            return user.getId();
        }

        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return null;
            }
        }

        System.out.println("/signup");
        User user = new User(firstName, lastName, email, pass);
        userRepository.save(user);
        System.out.println(user.getId());
        return user.getId();
    }

    @PostMapping("get_count")
    public Long getCount() {
        return bulletinRepository.count();
    }

}
