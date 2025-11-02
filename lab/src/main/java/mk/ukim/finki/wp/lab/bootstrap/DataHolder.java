package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;

import java.util.ArrayList;
import java.util.List;

public class DataHolder {
    public static List<Dish> dishes = new ArrayList<>(List.of(
            new Dish("1", "Pasta Carbonara", "Italian", 25),
            new Dish("2", "Sushi Platter", "Japanese", 40),
            new Dish("3", "Beef Bourguignon", "French", 120),
            new Dish("4", "Paella", "Spanish", 45),
            new Dish("5", "Schnitzel", "German", 30)
    ));

    public static List<Chef> chefs = new ArrayList<>(List.of(
            new Chef(1L, "Mario", "Rossi", "Italian cuisine expert", new ArrayList<>()),
            new Chef(2L, "Pierre", "Dubois", "French pastry master", new ArrayList<>()),
            new Chef(3L, "Kenji", "Tanaka", "Japanese sushi specialist", new ArrayList<>()),
            new Chef(4L, "Maria", "Garcia", "Spanish tapas specialist", new ArrayList<>()),
            new Chef(5L, "Hans", "Mueller", "German traditional cook", new ArrayList<>())
    ));
}
