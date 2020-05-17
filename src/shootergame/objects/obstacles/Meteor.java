package shootergame.objects.obstacles;

import static shootergame.helpers.GameConstants.SCREEN_WIDTH;

import shootergame.objects.Collidable;
import shootergame.objects.Obstacle;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class Meteor extends Collidable implements Obstacle {
    private int sideSpeed, spawnTime, counter = 0; //randomize angle to between 20 & 150

    public Meteor(int x, BufferedImage image, int spawnTime, int sideSpeed) {
        Random random = new Random();
        this.setSpeed(1);
        int dir = random.nextInt(2 - 1 + 1) + 1;
        if (dir == 1) {
            this.setSpeed(1);
        } else {
            this.setSpeed(-1);
        }
        this.setSideSpeed(sideSpeed);
        this.setY(0-image.getHeight());
        this.setX(x);
        this.setImage(image);
        this.setHitBox(new Rectangle(this.getX(), this.getY(), image.getWidth(), image.getHeight()));
        this.setSpawnTime(spawnTime);
    }

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
