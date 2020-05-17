package shootergame.objects.obstacles;

import static shootergame.helpers.GameConstants.SCREEN_WIDTH;

import shootergame.objects.Collidable;
import shootergame.objects.Obstacle;

public abstract class Meteor extends Collidable implements Obstacle {
    private int sideSpeed, spawnTime, counter = 0; //randomize angle to between 20 & 150

    @Override
    public void update() {
        counter++;
        if (counter > sideSpeed) {
            this.setX(this.getX() + getSpeed());
            counter = 0;
        }
        this.setY(this.getY() + Math.abs(getSpeed()));
        this.checkBorder();
        this.getHitBox().setLocation(this.getX(), this.getY());
    }

    @Override
    public int getSpawnTime() {
        return spawnTime;
    }

    @Override
    public void checkBorder() {
        if (this.getX() < 0 - this.getImage().getWidth() || this.getX() > SCREEN_WIDTH) {
            if (this.getSpeed() < 0) {
                this.setX(SCREEN_WIDTH + 10);
            } else {
                this.setX(0-this.getImage().getWidth() - 10);
            }
        }
    }

    void setSpawnTime(int spawnTime) {
        this.spawnTime = spawnTime;
    }

    public void setSideSpeed(int sideSpeed) {
        this.sideSpeed = sideSpeed;
    }
}
