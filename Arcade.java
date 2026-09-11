import java.util.ArrayList;
import java.util.List;

/**
 * Assignment 9 — Arcade owns a collection of Game objects (including subclasses).
 */
public class Arcade {

    private String name;
    private String type;
    private int year;
    private List<Game> gameLibrary;

    public Arcade(String name, String type, int year) {
        this.name = name;
        this.type = type;
        this.year = year;
        this.gameLibrary = new ArrayList<>();
    }

    public void addToLibrary(Game game) {
        if (!gameLibrary.contains(game)) {
            gameLibrary.add(game);
            System.out.println("Game added");
        }
    }

    public void listGameLibrary() {
        System.out.println("The arcade has " + gameLibrary.size() + " games.");
        for (Game game : gameLibrary) {
            System.out.println(game.toString());
        }
    }
}
