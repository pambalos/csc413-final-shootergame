package shootergame.objects.powerups;

import shootergame.objects.Player;

public class MovementBoost extends PowerUp {

    public MovementBoost(int x, int spawnTime) {
        super(x, spawnTime, "mspeed");
    }

    @Override
    public void powerUpPlayer(Player player) {
        player.increaseMovementSpeed();
    }
}
