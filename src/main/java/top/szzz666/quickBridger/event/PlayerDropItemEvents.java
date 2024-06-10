package top.szzz666.quickBridger.event;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerDropItemEvent;

import static top.szzz666.quickBridger.QuickBridgerMian.isQBer;

public class PlayerDropItemEvents implements Listener {
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event){
      Player player = event.getPlayer();
      if (isQBer(player)){
          event.setCancelled(true);
          player.sendMessage("§c你不能丢弃物品");
      }

    }

}
