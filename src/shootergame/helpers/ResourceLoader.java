package shootergame.helpers;

import static javax.imageio.ImageIO.read;

import shootergame.ShooterGame;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ResourceLoader {
    private static Map<String, BufferedImage> resources;
    private static final String SPACE_SHOOTER_PATH = "resources/spaceshooter/PNG/";

    static {
        ResourceLoader.resources = new HashMap<>();
        try {
            String path;

            //Player
            ResourceLoader.resources.put("playerShip", read(
                    Objects.requireNonNull(ShooterGame.class.getClassLoader().getResourceAsStream(SPACE_SHOOTER_PATH + "playerShip1_red.png"))));

            //Lasers
            path = SPACE_SHOOTER_PATH + "Lasers/";
            ResourceLoader.resources.put("playerLaser", read(Objects.requireNonNull(ShooterGame.class.getClassLoader().getResourceAsStream(path + "laserBlue03.png"))));
            ResourceLoader.resources.put("enemy1Laser", read(Objects.requireNonNull(ShooterGame.class.getClassLoader().getResourceAsStream(path + "laserRed03.png"))));

            //Enemies
            path = SPACE_SHOOTER_PATH + "Enemies/";
            ResourceLoader.resources.put("enemy1", read(Objects.requireNonNull(ShooterGame.class.getClassLoader().getResourceAsStream(path + "enemyBlack1.png"))));

            //meteors
            path = SPACE_SHOOTER_PATH + "Meteors/";
            ResourceLoader.resources.put("breakableMeteorBig", read(
                    Objects.requireNonNull(ShooterGame.class.getClassLoader().getResourceAsStream(path + "meteorBrown_big1.png"))));
            ResourceLoader.resources.put("breakableMeteorMed", read(
                    Objects.requireNonNull(ShooterGame.class.getClassLoader().getResourceAsStream(path + "meteorBrown_med1.png"))));
            ResourceLoader.resources.put("breakableMeteorSmall", read(
                    Objects.requireNonNull(ShooterGame.class.getClassLoader().getResourceAsStream(path + "meteorGrey_small1.png"))));
            ResourceLoader.resources.put("unbreakableMeteorBig", read(
                    Objects.requireNonNull(ShooterGame.class.getClassLoader().getResourceAsStream(path + "meteorGrey_big1.png"))));
            ResourceLoader.resources.put("unbreakableMeteorMed", read(
                    Objects.requireNonNull(ShooterGame.class.getClassLoader().getResourceAsStream(path + "meteorGrey_med1.png"))));
            ResourceLoader.resources.put("unbreakableMeteorSmall", read(
                    Objects.requireNonNull(ShooterGame.class.getClassLoader().getResourceAsStream(path + "meteorGrey_small1.png"))));

            //power ups
            path = SPACE_SHOOTER_PATH + "Power-ups/";
            ResourceLoader.resources.put("overdrive", read(
                    Objects.requireNonNull(ShooterGame.class.getClassLoader().getResourceAsStream(path + "powerupBlue_bolt.png"))));
            ResourceLoader.resources.put("life", read(Objects.requireNonNull(ShooterGame.class.getClassLoader().getResourceAsStream(path + "star_gold.png"))));
            ResourceLoader.resources.put("mspeed", read(Objects.requireNonNull(ShooterGame.class.getClassLoader().getResourceAsStream(path + "bold_silver.png"))));
            ResourceLoader.resources.put("shield", read(Objects.requireNonNull(ShooterGame.class.getClassLoader().getResourceAsStream(path + "shield_bronze.png"))));

        } catch (IOException e) {
            System.out.println("Failed to read some resource");
            System.exit(-5);
        }
    }

    public static BufferedImage getResourceImage(String key) {
        return ResourceLoader.resources.get(key);
    }
}
