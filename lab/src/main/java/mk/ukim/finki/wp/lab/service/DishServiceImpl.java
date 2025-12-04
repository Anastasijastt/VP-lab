package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
    private final ChefRepository chefRepository;

    public DishServiceImpl(DishRepository dishRepository, ChefRepository chefRepository) {
        this.dishRepository = dishRepository;
        this.chefRepository = chefRepository;
    }

    @Override
    public List<Dish> listDishes() {
        return dishRepository.findAll();
    }

    @Override
    public Dish findByDishId(String dishId) {
        return dishRepository.findByDishId(dishId)
                .orElseThrow(() -> new RuntimeException("Dish not found"));
    }

    @Override
    public Dish findById(Long id) {
        return dishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dish not found"));
    }

    @Override
    public Dish create(String dishId, String name, String cuisine, int preparationTime, Long chefId) {

        Chef chef = chefRepository.findById(chefId)
                .orElseThrow(() -> new RuntimeException("Chef not found"));

        Dish dish = new Dish(dishId, name, cuisine, preparationTime);
        dish.setChef(chef);

        return dishRepository.save(dish);
    }

    @Override
    public Dish update(Long id, String dishId, String name, String cuisine, int preparationTime, Long chefId) {

        Dish dish = findById(id);

        dish.setDishId(dishId);
        dish.setName(name);
        dish.setCuisine(cuisine);
        dish.setPreparationTime(preparationTime);

        Chef chef = chefRepository.findById(chefId)
                .orElseThrow(() -> new RuntimeException("Chef not found"));

        dish.setChef(chef);
        return dishRepository.save(dish);
    }

    @Override
    public void delete(Long id) {
        dishRepository.deleteById(id);
    }

    @Override
    public List<Dish> listDishesByChefId(Long chefId) {
        return dishRepository.findAllByChef_Id(chefId);
    }
}
