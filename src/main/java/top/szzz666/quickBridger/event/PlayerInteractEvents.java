package top.szzz666.quickBridger.event;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import top.szzz666.quickBridger.entity.QBer;

import static top.szzz666.quickBridger.QuickBridgerMian.*;
import static top.szzz666.quickBridger.config.LangConfig.PlayerInteract_sendActionBar;
import static top.szzz666.quickBridger.config.QbConfig.getQuitItem;


public class PlayerInteractEvents implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        QBer qr = AllQBer.get(player);
       if (isQBer(player)){
           if (player.getInventory().getItemInHand().getId() == getQuitItem().getId()) {
               event.setCancelled(true);
               if (qr.getQuitCount() == 1){
                   player.teleport(player.getSpawn());
                   qr.setQuitCount(0);
               }else {
                   player.sendActionBar(PlayerInteract_sendActionBar,5,20,5);
                   qr.setQuitCount(1);
                   nkServer.getScheduler().scheduleDelayedTask(plugin,
                           () -> qr.setQuitCount(0), 40);
               }
           }
       }
    }
}
