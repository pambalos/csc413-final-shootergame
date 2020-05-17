package shootergame.objects.powerups;

import shootergame.objects.Player;

public class Life extends PowerUp {

    public Life(int x, int spawnTime) {
        super(x, spawnTime, "life");
    }

    @Override
    public void powerUpPlayer(Player player) {
        player.addLife();
    }
}
