import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Challenge 1: VideoGame extends Game")
    void videoGameExtendsGame() {
        assertTrue(VideoGame.class.getSuperclass() == Game.class,
            "challenge1 failed - VideoGame should extend Game (use: class VideoGame extends Game).");
    }

    @Test
    @DisplayName("Challenge 1: Pinball extends Game")
    void pinballExtendsGame() {
        assertTrue(Pinball.class.getSuperclass() == Game.class,
            "challenge1 failed - Pinball should extend Game (use: class Pinball extends Game).");
    }

    @Test
    @DisplayName("Challenge 3: VideoGame.play override")
    void videoGamePlayUsesSubclassBehavior() {
        Game game = new VideoGame("Pokemon", 1996, "RPG");
        String output = captureOutput(game::play);

        assertTrue(output.toLowerCase().contains("video game"),
            "challenge3 failed - VideoGame.play() output must include \"video game\".");
        assertTrue(output.contains("Pokemon"),
            "challenge3 failed - VideoGame.play() output must include the game name Pokemon.");
    }

    @Test
    @DisplayName("Challenge 3: Pinball.play override")
    void pinballPlayUsesSubclassBehavior() {
        Game game = new Pinball("Spaceball", 1986, "Pinball");
        String output = captureOutput(game::play);

        assertTrue(output.toLowerCase().contains("pinball"),
            "challenge3 failed - Pinball.play() output must include \"pinball\".");
        assertTrue(output.contains("Spaceball"),
            "challenge3 failed - Pinball.play() output must include the game name Spaceball.");
    }

    @Test
    @DisplayName("Challenge 2: arcade library stores subclasses")
    void arcadeStoresSubclassObjectsInLibrary() {
        Arcade arcade = new Arcade("Arcade of Legions", "Variety", 1982);
        arcade.addToLibrary(new VideoGame("Pokemon", 1996, "RPG"));
        arcade.addToLibrary(new Pinball("Spaceball", 1986, "Pinball"));
        String output = captureOutput(arcade::listGameLibrary);

        assertTrue(output.contains("2 games"),
            "challenge2 failed - library should list 2 games after adding VideoGame and Pinball.");
        assertTrue(output.contains("Pokemon"),
            "challenge2 failed - listGameLibrary() should print Pokemon.");
        assertTrue(output.contains("Spaceball"),
            "challenge2 failed - listGameLibrary() should print Spaceball.");
    }

    @Test
    @DisplayName("Challenge 3: Main calls overridden play")
    void mainUsesSubclassObjectsPolymorphically() {
        String output = captureOutput(() -> Main.main(new String[] {}));

        assertTrue(output.contains("Playing the video game Pokemon"),
            "challenge3 failed - Main should print: Playing the video game Pokemon");
        assertTrue(output.contains("Playing the pinball game Spaceball"),
            "challenge3 failed - Main should print: Playing the pinball game Spaceball");
        assertTrue(output.contains("The arcade has 2 games"),
            "challenge3 failed - Main should add both games (print The arcade has 2 games).");
    }
}
