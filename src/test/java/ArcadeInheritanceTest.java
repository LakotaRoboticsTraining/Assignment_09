import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class ArcadeInheritanceTest {

    private String captureOutput(Runnable action) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(outputStream));
            action.run();
            return outputStream.toString();
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void videoGameExtendsGame() {
        assertTrue(VideoGame.class.getSuperclass() == Game.class,
            "VideoGame should extend Game.");
    }

    @Test
    void pinballExtendsGame() {
        assertTrue(Pinball.class.getSuperclass() == Game.class,
            "Pinball should extend Game.");
    }

    @Test
    void videoGamePlayUsesSubclassBehavior() {
        Game game = new VideoGame("Pokemon", 1996, "RPG");

        String output = captureOutput(game::play);

        assertTrue(output.contains("video game"),
            "VideoGame.play() should identify itself as a video game.");
        assertTrue(output.contains("Pokemon"),
            "VideoGame.play() should use the game's name.");
    }

    @Test
    void pinballPlayUsesSubclassBehavior() {
        Game game = new Pinball("Spaceball", 1986, "Pinball");

        String output = captureOutput(game::play);

        assertTrue(output.contains("pinball"),
            "Pinball.play() should identify itself as pinball.");
        assertTrue(output.contains("Spaceball"),
            "Pinball.play() should use the game's name.");
    }

    @Test
    void arcadeStoresSubclassObjectsInLibrary() {
        Arcade arcade = new Arcade("Arcade of Legions", "Variety", 1982);
        arcade.addToLibrary(new VideoGame("Pokemon", 1996, "RPG"));
        arcade.addToLibrary(new Pinball("Spaceball", 1986, "Pinball"));

        String output = captureOutput(arcade::listGameLibrary);

        assertTrue(output.contains("2 games"),
            "The arcade library should hold both subclass objects.");
        assertTrue(output.contains("Pokemon"),
            "listGameLibrary() should print the video game.");
        assertTrue(output.contains("Spaceball"),
            "listGameLibrary() should print the pinball game.");
    }

    @Test
    void mainUsesSubclassObjectsPolymorphically() {
        String output = captureOutput(() -> Main.main(new String[]{}));

        assertTrue(output.contains("Playing the video game Pokemon"),
            "Main should call the VideoGame version of play().");
        assertTrue(output.contains("Playing the pinball game Spaceball"),
            "Main should call the Pinball version of play().");
        assertTrue(output.contains("The arcade has 2 games"),
            "Main should add both games to the arcade library.");
    }
}
