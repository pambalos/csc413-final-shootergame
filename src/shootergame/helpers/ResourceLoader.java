package shootergame.helpers;

import static javax.imageio.ImageIO.read;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResourceLoader {
    private static Map<String, BufferedImage> resources;

    static {
        ResourceLoader.resources = new HashMap<>();
        try {
            ResourceLoader.resources.put("playerShip", read(new File("resources/spaceshooter/PNG/playerShip1_red.png")));
            ResourceLoader.resources.put("playerLaser", read(new File("resources/spaceshooter/PNG/Lasers/laserBlue03.png")));
            ResourceLoader.resources.put("enemy1Laser", read(new File("resources/spaceshooter/PNG/Lasers/laserRed02.png")));
            ResourceLoader.resources.put("enemy1", read(new File("resources/spaceshooter/PNG/Enemies/enemyBlack1.png")));
            ResourceLoader.resources.put("breakableMeteorBig", read(new File("resources/spaceshooter/PNG/Meteors/meteorBrown_big1.png")));
            ResourceLoader.resources.put("breakableMeteorMed", read(new File("resources/spaceshooter/PNG/Meteors/meteorBrown_med1.png")));
            ResourceLoader.resources.put("breakableMeteorSmall", read(new File("resources/spaceshooter/PNG/Meteors/meteorGrey_small1.png")));
            ResourceLoader.resources.put("unbreakableMeteorBig", read(new File("resources/spaceshooter/PNG/Meteors/meteorGrey_big1.png")));
            ResourceLoader.resources.put("unbreakableMeteorMed", read(new File("resources/spaceshooter/PNG/Meteors/meteorGrey_med1.png")));
            ResourceLoader.resources.put("unbreakableMeteorSmall", read(new File("resources/spaceshooter/PNG/Meteors/meteorGrey_small1.png")));

        } catch (IOException e) {
            System.out.println("Failed to read some resource");
            System.exit(-5);
        }
    }

    public static BufferedImage getResourceImage(String key) {
        return ResourceLoader.resources.get(key);
    }
}
