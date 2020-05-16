package shootergame.helpers;

import shootergame.objects.GameObject;
import shootergame.objects.Obstacle;
import shootergame.objects.enemies.SmallEnemy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LevelManager {

    private ArrayList<GameObject> enemiesNObstacles;
    private ArrayList<GameObject> gameObjects;
    private long t, counter;

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

    public LevelManager(ArrayList<GameObject> gameObjects, PlayerManager playerManager) {
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
                            enemiesNObstacles.add(new SmallEnemy(currCol*100, 1, currRow, ResourceLoader.getResourceImage("enemy1"), this.gameObjects, playerManager));
                            break;
                        case 13 :
                            //enemiesNObstacles.add(new BreakableMeteor(currCol*100, , -100));
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
