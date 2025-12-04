package mk.ukim.finki.wp.lab.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String dishId;
    private String name;
    private String cuisine;
    private int preparationTime;

    @ManyToOne
    private Chef chef;

    public Dish(String dishId, String name, String cuisine, int preparationTime) {
        this.dishId = dishId;
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
    }
}
