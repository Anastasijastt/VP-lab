package mk.ukim.finki.wp.lab.model;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class Dish {
    private static long counter = 1;
    private Long id;
    private String dishId;
    private String name;
    private String cuisine;
    private int preparationTime;

    private Chef chef;

    public Dish(String dishId, String name, String cuisine, int preparationTime) {
        this.id = counter++;
        this.dishId = dishId;
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
    }
}
