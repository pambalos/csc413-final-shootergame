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
import shootergame.objects.layout.HUD;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
    private boolean gameOver = false;


    public static void main(String[] args) {
        ShooterGame shooterGame = new ShooterGame();
        shooterGame.init();
        CollisionHandler chandler = new CollisionHandler();
        LevelManager levelManager = new LevelManager(shooterGame.gameObjects, shooterGame.player);
        try {
            while (true) {

                for (int i = 0; i < shooterGame.gameObjects.size(); i++) {
                    shooterGame.gameObjects.get(i).update();
                }
                shooterGame.repaint();

                levelManager.manageLevel(); //should call manager tick

                ArrayList<GameObject> toRemove = chandler.handleCollisions(shooterGame.gameObjects);

                for (GameObject o : toRemove) {
                    shooterGame.gameObjects.remove(o);
                    if (o instanceof Player) { //ship died so end game
                        shooterGame.gameOver = true;
                    }
                }
                Thread.sleep(1000 / 120);
                shooterGame.gameOver = levelManager.isGameOver();
            }

        } catch (InterruptedException nm) {
            nm.printStackTrace();
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
        if (this.gameOver) {
            Font font = new Font("Serif", Font.PLAIN, 50);
            buffer.setFont(font);
            buffer.setColor(Color.CYAN);
            buffer.drawString("GAME OVER!!", SCREEN_WIDTH/2-150, SCREEN_HEIGHT/2);
        }
        g2.drawImage(world, 0, 0, null);
    }

    /**
     * This iterates through the objects and draws them to buffer
     * @param buffer the buffer to be drawn to (base world)
     */
    private void drawBufferImages(Graphics2D buffer) {
        for (int i = 0; i < this.gameObjects.size(); i++) {
            this.gameObjects.get(i).drawImage(buffer);
        }
    }
}
