package shootergame.helpers;

import shootergame.objects.Player;

public class PlayerManager {
    private Player player;

    public PlayerManager(Player player) {
        this.player = player;
    }

    public void takePlayerDamage() {
        player.takeDamage(1);
    }
}
