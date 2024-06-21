package top.szzz666.quickBridger.event;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerToggleSneakEvent;
import cn.nukkit.level.Position;
import cn.nukkit.math.Vector3;
import top.szzz666.quickBridger.entity.QBer;

import static top.szzz666.quickBridger.QuickBridgerMian.AllQBer;
import static top.szzz666.quickBridger.QuickBridgerMian.isQBer;
import static top.szzz666.quickBridger.config.LangConfig.elevatorDown;
import static top.szzz666.quickBridger.config.QbConfig.ElevatorBlock;

public class PlayerToggleSneakEvents implements Listener {

    @EventHandler
    public void onToggleSneakEvent(PlayerToggleSneakEvent event) {
        // 获取跳跃的玩家对象
        Player player = event.getPlayer();
        if (isQBer(player)) {//如果这个玩家是搭路练习者
            QBer qber = AllQBer.get(player);
            Vector3 playerPos = player.getPosition();
            Vector3 feetPos = playerPos.add(0, -1, 0); // 获取玩家脚下方块的位置
            Block footBlock = player.getLevel().getBlock(feetPos);

            //电梯↓
            if (player.isSneaking() && footBlock.getId() == ElevatorBlock) {
                Position elevatorTpPosition = getElevatorTpPosition(player, ElevatorBlock);
                if (elevatorTpPosition != null) {
                    player.teleport(elevatorTpPosition);
                    player.sendTitle(elevatorDown, "", 5, 10, 5);
                }
            }
        }
    }

    //获取电梯↓要传送的位置
    private Position getElevatorTpPosition(Player player, int blockId) {
        for (int y = player.getFloorY() - 2; y > 0; y--) { // 限制搜索高度到256以避免溢出
            Block block = player.level.getBlock(new Vector3(player.getX(), y, player.getZ()));
            if (block.getId() == blockId) {
                return new Position(block.getX(), block.getY() + 1, block.getZ(), block.getLevel());
            }
        }
        return null;// 没有找到指定ID的方块
    }
}