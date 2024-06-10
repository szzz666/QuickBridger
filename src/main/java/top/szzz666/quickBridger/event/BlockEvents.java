package top.szzz666.quickBridger.event;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.block.Block;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.item.Item;
import top.szzz666.quickBridger.entity.QBer;

import static top.szzz666.quickBridger.QuickBridgerMian.*;
import static top.szzz666.quickBridger.config.QbConfig.*;


public class BlockEvents implements Listener {

    // 监听玩家放置方块
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (isQBer(player)) {//如果这个玩家是搭路练习者
            // 获取玩家放置方块的坐标
            Block placedBlock = event.getBlock();
            QBer qber = AllQBer.get(player);
            qber.setQbername(player.getName());
            qber.addQberBlock(placedBlock);

            Item item = event.getItem();
            Item itemInfo = getBridgingBlockItem();
            if (item.equals(itemInfo) && item.getCount() <= 1) {
                nkServer.getScheduler().scheduleDelayedTask(plugin,
                        () -> player.getInventory().addItem(itemInfo), 1);
            }
        }
    }

    // 监听玩家破坏方块
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block placedBlock = event.getBlock();
        String[] bridgingBlock = BridgingBlock.split(":");
        double bridgingBlockId = Double.parseDouble(bridgingBlock[0]);
        if (isQBer(player)) {
            Item ItemInHand = player.getInventory().getItemInHand();
            if (ItemInHand.getId() == getPickaxeItem().getId()) {
                //设置耐久为最大值
                ItemInHand.setDamage(0);
                nkServer.getScheduler().scheduleDelayedTask(plugin,
                        () -> player.getInventory().setItemInHand(ItemInHand), 1);
            }
        }
        if (player.getLevel().getName().equals(LevelName) && placedBlock.getId() != bridgingBlockId) {//玩家在世界名字为"world"
            //取消破坏方块事件
            event.setCancelled(true);
        }
    }


}
