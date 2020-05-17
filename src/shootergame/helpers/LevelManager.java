package shootergame.helpers;

import shootergame.objects.GameObject;
import shootergame.objects.Laser;
import shootergame.objects.Obstacle;
import shootergame.objects.Player;
import shootergame.objects.enemies.SmallEnemy;
import shootergame.objects.obstacles.BreakableMeteor;
import shootergame.objects.obstacles.UnbreakableMeteor;
import shootergame.objects.powerups.OverDrive;
import shootergame.objects.powerups.Life;
import shootergame.objects.powerups.MovementBoost;
import shootergame.objects.powerups.Shield;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * map values
 * 0 - nothing
 * 1 - enemy
 * 11 - breakable meteor small
 * 12 - breakable meteor med
 * 13 - breakable meteor big
 * 21 - unbreakable meteor small
 * 22 - unbreakable meteor med
 * 23 - unbreakable meteor big
 * 4 - 8 power ups 4: health, 5: mspeed, 6: bspeed, 7: shield
 */

public class LevelManager {

    private ArrayList<GameObject> enemiesNObstacles;
    private ArrayList<GameObject> gameObjects;
    private long t, counter;

    public boolean isGameOver() {
        if (enemiesNObstacles.isEmpty()) {
            for (int i = 0; i < gameObjects.size(); i++) {
                if (gameObjects.get(i) instanceof Obstacle) {
                    return false;
                }
            }
        }
        return true;
    }

    public void manageLevel() {
        t++;
        if (t > 150) {
            counter++;
            for (int i = 0; i < enemiesNObstacles.size(); i++) {
                Obstacle object = (Obstacle)enemiesNObstacles.get(i);
                if (object.getSpawnTime() < counter) {
                    gameObjects.add(enemiesNObstacles.get(i));
                    enemiesNObstacles.remove(enemiesNObstacles.get(i));
                    i--;
                }
            }
            t = 0;
        }
    }

    public LevelManager(ArrayList<GameObject> gameObjects, Player player) {
        this.enemiesNObstacles = new ArrayList<>();
        this.gameObjects = gameObjects;
        this.t = 0; this.counter = 0;
        try {

            BufferedReader mapReader = new BufferedReader(new FileReader("resources/maps/level1"));

            String row = mapReader.readLine();
            if (row == null) throw new IOException("Nothing in map file");

            String[] mapInfo = row.split(",");
            int numCols = Integer.parseInt(mapInfo[1]);
            int numRows = Integer.parseInt(mapInfo[0]);

            for (int currRow = 0; currRow < numRows; currRow++) {
                row = mapReader.readLine();
                mapInfo = row.split(",");
                for (int currCol = 0; currCol < numCols; currCol++) {
                    switch (Integer.parseInt(mapInfo[currCol])) {
                        case 1 :
                            enemiesNObstacles.add(new SmallEnemy(currCol*100, 1, currRow, ResourceLoader.getResourceImage("enemy1"), this.gameObjects, player));
                            break;
                        case 4:
                            enemiesNObstacles.add(new Life(currCol*100, currRow));
                            break;
                        case 5:
                            enemiesNObstacles.add(new MovementBoost(currCol*100, currRow));
                            break;
                        case 6:
                            enemiesNObstacles.add(new OverDrive(currCol*100, currRow));
                            break;
                        case 7:
                            enemiesNObstacles.add(new Shield(currCol*100, currRow));
                            break;
                        case 13 :
                            System.out.println("Adding Meteor");
                            enemiesNObstacles.add(new BreakableMeteor(currCol*100, ResourceLoader.getResourceImage("breakableMeteorBig"), currRow, 2));
                            break;
                        case 23:
                            enemiesNObstacles.add(new UnbreakableMeteor(currCol*100, ResourceLoader.getResourceImage("unbreakableMeteorBig"), currRow, 1));
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
