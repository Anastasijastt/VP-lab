package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    User findByUsername(String username);
    User create(String username, String password, String name, String lastname);
    void delete(Long Id);
    User update(Long Id,String username, String password, String name, String lastname);
    List<User> listUsers();
    User findById(Long Id);
}
