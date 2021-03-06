package shootergame.objects;

import java.util.ArrayList;

public abstract class Ship extends Collidable {
    private ArrayList<GameObject> gameObjects;
    private int health;
    private long counter = 0, loadTime = 150;

    public void takeDamage(int i) {
        if (health > 0) {
            this.health -= i;
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(ArrayList<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }

    public long getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(long loadTime) {
        this.loadTime = loadTime;
    }
}
