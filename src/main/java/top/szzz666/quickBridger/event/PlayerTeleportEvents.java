package top.szzz666.quickBridger.event;


import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerTeleportEvent;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import cn.nukkit.level.Position;
import top.szzz666.quickBridger.entity.QBer;

import java.util.ArrayList;
import java.util.HashMap;

import static top.szzz666.quickBridger.QuickBridgerMian.*;
import static top.szzz666.quickBridger.config.QbConfig.*;
import static top.szzz666.quickBridger.event.PlayerPositionEvents.cleanQberBlock;


public class PlayerTeleportEvents implements Listener {
    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        Position Position = player.getPosition();
        Position newPosition = event.getTo();
        if (newPosition.getLevel() == getSpawnPoint().getLevel()) {
            if (Position.getLevel() != newPosition.getLevel()) {
                joinQb(player);
//                sandMsgToQber("§e玩家 " + player.getName() + " 加入了搭路练习");
            }
        } else if (isQBer(player) && Position.getLevel() != newPosition.getLevel()) {
            quitQb(player);
        }
    }

    //重置玩家背包
    public static void resetInventory(Player player) {
        player.getInventory().clearAll();
        player.giveItem(getBridgingBlockItem());
        player.giveItem(getPickaxeItem());
        player.getInventory().setItem(8, getQuitItem());
    }

    //将Inventory中的物品添加到Player的Inventory中
//    public static void setInventoryToPlayer(Player player, Inventory inventory) {
//        for (int i = 0; i < inventory.getSize(); i++) {
//            Item item = inventory.getItem(i);
//            player.getInventory().addItem(item);
//        }
//    }

    //离开搭路练习
    public static void quitQb(Player player){
        QBer qBer = AllQBer.get(player);
        cleanQberBlock(qBer.getQberBlock());
        player.getInventory().clearAll();
        getPlayerInventory(player, qBer.getQberInventory());
        sandMsgToQber("§e玩家 " + player.getName() + " 退出了搭路练习");
        AllQBer.remove(player);
    }

    //加入搭路练习
    public static void joinQb(Player player){
        PlayerInventory inventory = player.getInventory();
        QBer qBer = new QBer();
        qBer.setQbername(player.getName());
        qBer.setQberInventory(setPlayerInventory(inventory));
        qBer.setQberSpawnPoint(getSpawnPoint());
        AllQBer.put(player, qBer);
        resetInventory(player);
        player.setGamemode(0);
        sandMsgToQber("§e玩家 " + player.getName() + " 加入了搭路练习");
    }

    public static void sandMsgToQber(String msg){
        for (Player p : AllQBer.keySet()) {
            p.sendMessage(msg);
        }
    }

    //保存玩家库存
    public static HashMap<Integer,Item> setPlayerInventory(PlayerInventory inventory){
        HashMap<Integer,Item> playerInventory = new HashMap<>();
        for (int i = 0; i < inventory.getSize(); i++) {
            Item item = inventory.getItem(i);
            playerInventory.put(i, item);
        }
        return playerInventory;
    }

    //加载玩家库存
    public static void getPlayerInventory(Player player, HashMap<Integer,Item> inventoryArr){
        for (int i = 0; i < inventoryArr.size(); i++) {
            player.getInventory().setItem(i, inventoryArr.get(i));
        }
    }
}
