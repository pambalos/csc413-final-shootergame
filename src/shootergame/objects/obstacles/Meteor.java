package shootergame.objects.obstacles;

import shootergame.objects.Collidable;
import shootergame.objects.Obstacle;

public abstract class Meteor extends Collidable implements Obstacle {
    private int vx, vy, angle, speed, spawnTime; //randomize angle to between 20 & 150


    public void setAngle(int angle) {
        this.angle = angle;
    }

    @Override
    public int getSpawnTime() {
        return spawnTime;
    }

    void setSpawnTime(int spawnTime) {
        this.spawnTime = spawnTime;
    }
}
