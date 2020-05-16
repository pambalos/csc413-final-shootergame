/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootergame;


import static shootergame.helpers.GameConstants.HUD_OFFSET_X;
import static shootergame.helpers.GameConstants.HUD_OFFSET_Y;
import static shootergame.helpers.GameConstants.SCREEN_HEIGHT;
import static shootergame.helpers.GameConstants.SCREEN_WIDTH;

import shootergame.helpers.CollisionHandler;
import shootergame.helpers.LevelManager;
import shootergame.helpers.PlayerControl;
import shootergame.helpers.ResourceLoader;
import shootergame.objects.GameObject;
import shootergame.objects.Player;
import shootergame.objects.enemies.SmallEnemy;
import shootergame.objects.layout.HUD;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

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

/**
 * Main driver class of Player
 * Class is responsible for loading resources and
 * initializing game objects. Once completed, control will
 * be given to infinite loop which will act as our game loop.
 * A very simple game loop.
 *
 */
public class ShooterGame extends JPanel  {

    private BufferedImage world;
    private Graphics2D buffer;
    private JFrame jFrame;
    private ArrayList<GameObject> gameObjects;
    private Player player;


    public static void main(String[] args) {
        ShooterGame shooterGame = new ShooterGame();
        shooterGame.init();
        CollisionHandler chandler = new CollisionHandler();
        LevelManager manager = new LevelManager();
        manager.init(shooterGame.gameObjects);
        try {
            while (true) {

                for (int i = 0; i < shooterGame.gameObjects.size(); i++) {
                    shooterGame.gameObjects.get(i).update();
                }
                shooterGame.repaint();

                manager.manageLevel(); //should call manager tick

                /*
                ArrayList<GameObject> toRemove = new ArrayList<>();
                int currObjs = shooterGame.gameObjects.size();
                for (int i1 = 0; i1 < currObjs; i1++) {
                    for (int i2 = 0; i2 < currObjs; i2++) {
                        if (i1 != i2) {
                            GameObject object1 = shooterGame.gameObjects.get(i1);
                            GameObject object2 = shooterGame.gameObjects.get(i2);
                            if (chandler.collisionCompare(object1, object2)) {
                                chandler.handleCollisionInstance(object1, object2);
                            }
                        }
                    }
                }

                 */
                ArrayList<GameObject> toRemove = chandler.handleCollisions(shooterGame.gameObjects);

                for (GameObject o : toRemove) {
                    shooterGame.gameObjects.remove(o);
                    if (o instanceof Player) { //ship died so end game
                        shooterGame.endGame();
                        return;
                    }
                }
                Thread.sleep(1000 / 120);
            }

        } catch (InterruptedException nm) {
            nm.printStackTrace();
        }
    }

    private void endGame() { // this is printing to the minimaps for some reason
        this.world = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
        super.paintComponent(buffer);
        buffer = world.createGraphics();
        buffer.setColor(Color.DARK_GRAY);
        buffer.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT+30);
        Font font = new Font("Serif", Font.PLAIN, 50);
        buffer.setFont(font);
        buffer.setColor(Color.CYAN);
        buffer.drawString("GAME OVER!!", SCREEN_WIDTH/2, SCREEN_HEIGHT/2);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void init() {
        this.jFrame = new JFrame("Player Rotation");
        this.world = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
        gameObjects = new ArrayList<>();


        this.player = new Player(SCREEN_WIDTH/2, 650, ResourceLoader.getResourceImage("playerShip"), this.gameObjects);

        HUD hud = new HUD(player, HUD_OFFSET_X, HUD_OFFSET_Y);

        PlayerControl playerControl = new PlayerControl(player, KeyEvent.VK_W,
                KeyEvent.VK_S,
                KeyEvent.VK_A,
                KeyEvent.VK_D,
                KeyEvent.VK_SPACE);

        this.gameObjects.add(hud);
        this.gameObjects.add(player);
        this.jFrame.setLayout(new BorderLayout());
        this.jFrame.add(this);
        this.jFrame.addKeyListener(playerControl);
        this.jFrame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT + 30);
        this.jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame.setVisible(true);
    }

    //combine it so that this is just one list of gameobjects to draw
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        buffer = world.createGraphics();
        buffer.setColor(Color.black);
        buffer.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        drawBufferImages(buffer);
        //this.player.drawImage(buffer);
        g2.drawImage(world, 0, 0, null);
        //drawBufferImages(buffer); //needed so split my loops into two loops because if they werent painted in the right order, cant see shiza
        //drawG2Images(buffer); //see above comment
    }

    /**
     * This method needs to loop through the game objects to find the overlay objects
     * only printing them at the right stage so everything shows up and isnt missing or covered
     * @param g2 to draw all images which should be drawn on g2 overlay
     */
    private void drawG2Images(Graphics2D g2) {
        /*
        //First layer of overlays
        for (int i = 0; i < this.gameObjects.size(); i++) {
            if (this.gameObjects.get(i) instanceof Screen) {
                this.gameObjects.get(i).drawImage(g2);
            }
        }

        //Second layer of overlays - needs loop else sometimes you get concurrent error
        for (int i = 0; i < this.gameObjects.size(); i++) {
            if (this.gameObjects.get(i) instanceof HUD) {
                this.gameObjects.get(i).drawImage(g2);
            }
        }

         */
    }

    /**
     * This iterates through the objects and draws them to buffer
     * @param buffer the buffer to be drawn to (base world)
     */
    private void drawBufferImages(Graphics2D buffer) {
        for (int i = 0; i < this.gameObjects.size(); i++) {
            this.gameObjects.get(i).drawImage(buffer);
            /*
            if (!(this.gameObjects.get(i) instanceof Screen) && !(this.gameObjects.get(i) instanceof HUD)) {
                this.gameObjects.get(i).drawImage(buffer);
            }

             */
        }
    }
}
