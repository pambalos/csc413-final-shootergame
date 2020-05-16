package shootergame.helpers;

import shootergame.objects.Obstacle;
import shootergame.objects.Ship;
import shootergame.objects.enemies.SmallEnemy;
import shootergame.objects.obstacles.BreakableMeteor;
import shootergame.objects.Laser;
import shootergame.objects.Collidable;
import shootergame.objects.GameObject;
import shootergame.objects.Player;
import shootergame.objects.obstacles.Meteor;
import shootergame.objects.powerups.PowerUp;

import java.util.ArrayList;

public class CollisionHandler {
    private ArrayList<GameObject> toRemove;

    public ArrayList<GameObject> handleCollisions(ArrayList<GameObject> gameObjects) {
        toRemove = new ArrayList<>();
        int numObjs = gameObjects.size();
        for (int i = 0; i < numObjs; i++) {
            for (int j = 0; j < numObjs; j++) {
                if (i != j) {
                    GameObject object1 = gameObjects.get(i);
                    GameObject object2 = gameObjects.get(j);
                    if (collisionCompare(object1, object2)) {
                        handleCollisionInstance(object1, object2);
                    }
                }
            }
        }

        return toRemove;
    }

    /**
     * Do a bunch of collision handling here - decided to implement all collision logic here, then objects will use functional methods to be affected
     * @param object1 gameobj 1
     * @param object2 gameobj 2
     */
    public void handleCollisionInstance(GameObject object1, GameObject object2) {
        if (object1 instanceof Laser) {
            if (object2 instanceof SmallEnemy) {
                toRemove.add(object1);
                Ship enemy = (Ship)object2;
                enemy.takeDamage(1);
                if (enemy.getHealth() < 1) {
                    toRemove.add(object2);
                    System.out.println("Destroyed Enemy");
                }
            }
        }
    }

    /**
     * Checks a bunch of things to see if these two objects collided and if so, whether it should be handled
     * @param object1 first collidable
     * @param object2 second collidable
     * @return true if collision should be handled
     */
    public boolean collisionCompare(GameObject object1, GameObject object2) {
        return object1 instanceof Collidable &&
                object2 instanceof Collidable &&
                !(object1 instanceof Obstacle && object2 instanceof Obstacle) &&

                ((Collidable)object1).getHitBoxBounds().intersects(((Collidable)object2).getHitBoxBounds()) &&
                object1 != ((Collidable) object2).getOwner() &&
                object2 != ((Collidable) object1).getOwner();
    }
}
