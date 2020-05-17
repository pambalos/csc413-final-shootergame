package shootergame.objects;

import static shootergame.helpers.GameConstants.PLAYER_HEALTH;
import static shootergame.helpers.GameConstants.PLAYER_LIVES;

import shootergame.helpers.ResourceLoader;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

enum movement { forward, backward, left, right }

public class Player extends Ship {

    private boolean UpPressed;
    private boolean downPressed;
    private boolean rightPressed;
    private boolean leftPressed;
    private boolean shootPressed;
    private boolean overDriveActive;

    private int lives;
    private int ogX, ogY;
    private int overDriveCounter;

    public void toggleOverDrive(boolean active) {
        overDriveActive = active;
    }

    public int getLives() {
        return lives;
    }

    private void loseLife() {
        if (this.lives > 0) {
            this.lives--;
        }
    }

    public void respawn() {
        this.resetHealth();
        this.loseLife();
        this.setX(ogX);
        this.setY(ogY);
        System.out.println("Respawning x: " + ogX + "  y: " + ogY + " health: " + this.getHealth());
    }

    public Player(int x, int y, BufferedImage objectImage, ArrayList<GameObject> gameObjects) {
        this.setX(x);
        this.setY(y);
        this.ogX = x;
        this.ogY = y;
        this.setImage(objectImage);
        this.setHitBox(new Rectangle(x, y, this.getImage().getWidth(), this.getImage().getHeight()));
        this.setGameObjects(gameObjects);
        this.setHealth(PLAYER_HEALTH);
        this.lives = PLAYER_LIVES;
        this.setSpeed(3);
        this.setLoadTime(80);
    }

    public void decreaseLoadTime() {
        if (this.getLoadTime()-10 >= 0) {
            this.setLoadTime(this.getLoadTime() - 10);
        } else {
            this.setLoadTime(0);
        }
    }

    public void increaseMovementSpeed() {
        this.setSpeed(this.getSpeed()+1);
    }

    public void addLife() {
        this.lives++;
    }

    public void resetHealth() {
        this.setHealth(PLAYER_HEALTH);
    }

    public void toggleUpPressed() {
        this.UpPressed = true;
    }

    public void toggleShootPressed() { this.shootPressed = true; }

    public void unToggleShootPressed() { this.shootPressed = false; }

    public void toggleDownPressed() {
        this.downPressed = true;
    }

    public void toggleRightPressed() {
        this.rightPressed = true;
    }

    public void toggleLeftPressed() {
        this.leftPressed = true;
    }

    public void unToggleUpPressed() {
        this.UpPressed = false;
    }

    public void unToggleDownPressed() {
        this.downPressed = false;
    }

    public void unToggleRightPressed() {
        this.rightPressed = false;
    }

    public void unToggleLeftPressed() {
        this.leftPressed = false;
    }

    @Override
    public void update() {
        if (this.UpPressed && !this.downPressed) {
            this.move(movement.forward);
        }
        if (this.downPressed && !this.UpPressed) {
            this.move(movement.backward);
        }

        if (this.leftPressed && !this.rightPressed) {
            this.move(movement.left);
        }
        if (this.rightPressed && !this.leftPressed) {
            this.move(movement.right);
        }

        if (this.shootPressed) {
            long loadTime = this.getLoadTime();
            if (overDriveActive) {
                loadTime = 0;
            }
            if (this.getCounter() > loadTime) {
                this.setCounter(0);
                Laser b = new Laser(this.getX() + 24, this.getY() - 25, ResourceLoader.getResourceImage("playerLaser"), this, -2 * this.getSpeed());
                this.getGameObjects().add(b);
            }
        }

        this.setCounter(this.getCounter()+1);

        if (overDriveActive) {
            this.overDriveCounter++;
            if (this.overDriveCounter > 600) {
                toggleOverDrive(false);
                this.overDriveCounter = 0;
            }
        }

        if (this.getHealth() < 1 && this.getLives() > 0) {
            this.respawn();
        }
    }

    private void move(movement m) {
        int speed = this.getSpeed();
        if (overDriveActive) {
            speed *= 1.5;
        }
        switch (m) {
            case forward:
                this.setY(this.getY() - speed);
                break;
            case backward:
                this.setY(this.getY() + speed);
                break;
            case left:
                this.setX(this.getX() - speed);
                break;
            case right:
                this.setX(this.getX() + speed);
                break;
        }
        this.checkBorder();
        this.getHitBox().setLocation(this.getX(), this.getY());
    }

    @Override
    public String toString() {
        return "x=" + this.getX() + ", y=" + this.getY();
    }

    @Override
    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(this.getX(), this.getY());
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.getImage(), rotation, null);
    }
}
