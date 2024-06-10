package top.szzz666.quickBridger.event;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.level.Position;
import static top.szzz666.quickBridger.QuickBridgerMian.isQBer;
import static top.szzz666.quickBridger.config.QbConfig.*;
import static top.szzz666.quickBridger.event.PlayerTeleportEvents.quitQb;

public class PlayerQuitEvents implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Position Position = player.getPosition();
        if (isQBer(player) && Position.getLevel() == getSpawnPoint().getLevel()) {
            quitQb(player);
        }
    }


}
