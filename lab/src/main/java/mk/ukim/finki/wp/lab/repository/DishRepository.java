package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface DishRepository {
    List<Dish> findAll();
    Optional<Dish> findByDishId(String dishId);
    Optional<Dish> findById(Long id);
    Dish save(Dish dish);
    void deleteById(Long id);
}
