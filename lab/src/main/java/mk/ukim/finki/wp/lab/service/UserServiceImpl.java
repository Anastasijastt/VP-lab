package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User findByUsername(String username) {
        User user=userRepository.findByUsername(username);
        return user;
    }

    @Override
    public User create(String username, String password, String name, String lastname) {
        User user=new User(username,password,name,lastname);

        return userRepository.save(user);
    }

    @Override
    public void delete(Long Id) {
       userRepository.deleteById(Id);

    }

    @Override
    public User update(Long Id, String username, String password, String name, String lastname) {
        User user=userRepository.findById(Id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        user.setName(user.getName());
        user.setSurname(user.getSurname());

        return userRepository.save(user);
    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }
    @Override
    public User findById(Long Id) {
        return userRepository.findById(Id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
