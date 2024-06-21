package top.szzz666.quickBridger.event;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.level.Position;

import static top.szzz666.quickBridger.QuickBridgerMian.isQBer;
import static top.szzz666.quickBridger.config.QbConfig.getSpawnPoint;
import static top.szzz666.quickBridger.event.PlayerTeleportEvents.joinQb;

public class PlayerJoinEvents implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Position Position = player.getPosition();
        if (!isQBer(player) && Position.getLevel() == getSpawnPoint().getLevel()) {
            joinQb(player);
        }
    }
}
