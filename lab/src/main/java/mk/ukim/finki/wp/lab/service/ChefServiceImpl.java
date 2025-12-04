package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChefServiceImpl implements ChefService {

    private final ChefRepository chefRepository;
    private final DishRepository dishRepository;

    public ChefServiceImpl(ChefRepository chefRepository, DishRepository dishRepository) {
        this.chefRepository = chefRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Chef> listChefs() {
        return chefRepository.findAll();
    }

    @Override
    public Chef findById(Long id) {
        return chefRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chef not found"));
    }

    @Override
    public Chef addDishToChef(Long chefId, Long dishId) {

        Chef chef = chefRepository.findById(chefId)
                .orElseThrow(() -> new RuntimeException("Chef not found"));

        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new RuntimeException("Dish not found"));

        chef.getDishes().add(dish);
        dish.setChef(chef);

        dishRepository.save(dish);

        return chef;
    }
}
