package shootergame.helpers;

import static javax.imageio.ImageIO.read;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResourceLoader {
    private static Map<String, BufferedImage> resources;
    private static final String SPACE_SHOOTER_PATH = "resources/spaceshooter/PNG/";

    static {
        ResourceLoader.resources = new HashMap<>();
        try {
            String path;

            //Player
            ResourceLoader.resources.put("playerShip", read(new File(SPACE_SHOOTER_PATH + "playerShip1_red.png")));

            //Lasers
            path = SPACE_SHOOTER_PATH + "Lasers/";
            ResourceLoader.resources.put("playerLaser", read(new File(path + "laserBlue03.png")));
            ResourceLoader.resources.put("enemy1Laser", read(new File(path + "laserRed02.png")));

            //Enemies
            path = SPACE_SHOOTER_PATH + "Enemies/";
            ResourceLoader.resources.put("enemy1", read(new File(path + "enemyBlack1.png")));

            //meteors
            path = SPACE_SHOOTER_PATH + "Meteors/";
            ResourceLoader.resources.put("breakableMeteorBig", read(new File(path + "meteorBrown_big1.png")));
            ResourceLoader.resources.put("breakableMeteorMed", read(new File(path + "meteorBrown_med1.png")));
            ResourceLoader.resources.put("breakableMeteorSmall", read(new File(path + "meteorGrey_small1.png")));
            ResourceLoader.resources.put("unbreakableMeteorBig", read(new File(path + "meteorGrey_big1.png")));
            ResourceLoader.resources.put("unbreakableMeteorMed", read(new File(path + "meteorGrey_med1.png")));
            ResourceLoader.resources.put("unbreakableMeteorSmall", read(new File(path + "meteorGrey_small1.png")));

            //power ups
            path = SPACE_SHOOTER_PATH + "Power-ups/";
            ResourceLoader.resources.put("overdrive", read(new File(path + "powerupBlue_bolt.png")));
            ResourceLoader.resources.put("life", read(new File(path + "star_gold.png")));
            ResourceLoader.resources.put("mspeed", read(new File(path + "bold_silver.png")));
            ResourceLoader.resources.put("shield", read(new File(path + "shield_bronze.png")));

        } catch (IOException e) {
            System.out.println("Failed to read some resource");
            System.exit(-5);
        }
    }

    public static BufferedImage getResourceImage(String key) {
        return ResourceLoader.resources.get(key);
    }
}
