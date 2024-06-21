package top.szzz666.quickBridger.event;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageEvent;

import static top.szzz666.quickBridger.QuickBridgerMian.isQBer;
import static top.szzz666.quickBridger.config.LangConfig.EntityDamage_sendTitle;

public class EntityDamageEvents implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {//判断传入的event中的entity是否是Player类型
            Player player = (Player) event.getEntity();
            if (isQBer(player)) {
                float damage = event.getDamage();
                player.sendTitle(EntityDamage_sendTitle.replaceAll("%damage%", String.valueOf(damage)), "", 5, 10, 5);
                event.setDamage(0); // 取消受伤事件
            }
        }
    }
}
