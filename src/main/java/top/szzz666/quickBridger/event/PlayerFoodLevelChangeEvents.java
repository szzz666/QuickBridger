package top.szzz666.quickBridger.event;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFoodLevelChangeEvent;

import static top.szzz666.quickBridger.QuickBridgerMian.isQBer;

public class PlayerFoodLevelChangeEvents implements Listener {
    @EventHandler
    public void onFoodLevelChange(PlayerFoodLevelChangeEvent event) {
        // 设置玩家饱食度为最大值
        Player player = event.getPlayer();
        if (isQBer(player)) {
            event.setFoodLevel(20);
        }
    }
}
