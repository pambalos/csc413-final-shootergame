package shootergame.objects.powerups;

import shootergame.objects.Player;

public class OverDrive extends PowerUp {

    public OverDrive(int x, int spawnTime) {
        super(x, spawnTime, "overdrive");
    }

    @Override
    public void powerUpPlayer(Player player) {
        player.decreaseLoadTime();
        player.toggleOverDrive(true);
    }
}
