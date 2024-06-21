package top.szzz666.quickBridger.event;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerMoveEvent;
import cn.nukkit.level.Position;
import cn.nukkit.math.Vector3;
import top.szzz666.quickBridger.entity.QBer;

import java.util.ArrayList;

import static top.szzz666.quickBridger.QuickBridgerMian.*;
import static top.szzz666.quickBridger.config.LangConfig.*;
import static top.szzz666.quickBridger.config.QbConfig.*;
import static top.szzz666.quickBridger.event.PlayerTeleportEvents.resetInventory;

public class PlayerPositionEvents implements Listener {
    // 定义玩家移动事件的处理方法
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        QBer qber = AllQBer.get(player);
        if (isQBer(player)) {//如果这个玩家是搭路练习者
            //玩家掉下去了
            if (player.getY() < Lowy) {
                // 传送玩家到重生点
                player.teleport(qber.getQberSpawnPoint());
                cleanQberBlock(qber.getQberBlock());
//                resetInventory(player);
            }

            Vector3 playerPos = player.getPosition();
            Vector3 feetPos = playerPos.add(0, -1, 0); // 获取玩家脚下方块的位置
            Block footBlock = player.getLevel().getBlock(feetPos);

            //设置玩家新重生点
            if (footBlock.getId() == ResBlock && qber.isIsSetSpawnPoint()) {
                qber.setQberSpawnPoint(new Position(playerPos.getX(), playerPos.getY(), playerPos.getZ(), getLevel()));
                player.sendTitle(setTheSpawnPoint, "", 5, 10, 5);
                qber.setIsSetSpawnPoint(false);
            } else if (footBlock.getId() != ResBlock) {
                qber.setIsSetSpawnPoint(true);
            }
            //冲刺！！！！！
            if (footBlock.getId() == SpeedupBlock && qber.isIsSpeedup()) {
                launchPlayer(player);
                player.sendTitle(sprint, "", 5, 10, 5);
                qber.setIsSpeedup(false);
            } else if (footBlock.getId() != SpeedupBlock) {
                qber.setIsSpeedup(true);
            }
            //搭路结束点
            if (footBlock.getId() == StopBlock && qber.isIsStop()) {
                // 传送玩家到重生点
                player.teleport(AllQBer.get(player).getQberSpawnPoint());
                player.sendTitle(success, "", 5, 10, 5);
                cleanQberBlock(qber.getQberBlock());
//                resetInventory(player);
                qber.setIsStop(false);
            } else if (footBlock.getId() != StopBlock) {
                qber.setIsStop(true);
            }
            //回到出生点
            if (footBlock.getId() == ReturnBlock && qber.isIsReturn()) {
                // 传送玩家到出生点
                player.teleport(getSpawnPoint());
                cleanQberBlock(qber.getQberBlock());
//                resetInventory(player);
                qber.setIsReturn(false);
            } else if (footBlock.getId() != ReturnBlock) {
                qber.setIsReturn(true);
            }

        }
    }

    //玩家冲刺方法
    private void launchPlayer(Player player) {
        // 获取玩家面向的向量
        Vector3 lookVector = player.getDirectionVector();
        double velocityX = lookVector.getX() * 2;
        double velocityZ = lookVector.getZ() * 2;
        // Y轴速度保持不变，作为起跳初速度
        double velocityY = 1.0;
        Vector3 motion = new Vector3(velocityX, velocityY, velocityZ);
        // 应用运动向量
        player.setMotion(motion);
    }

    //清理搭路练习者搭的方块
    public static void cleanQberBlock(ArrayList<Block> QberBlock) {
        if (QberBlock != null) {
            for (Block block : QberBlock) {
                double bx = block.getX();
                double by = block.getY();
                double bz = block.getZ();
                // 获取要修改的坐标
                Vector3 position = new Vector3(bx, by, bz); // 替换 x、y、z 为具体的坐标
                //将这个坐标位置的方块填充为空气方块
                getLevel().setBlock(position, Block.get(BlockID.AIR));
            }
        }
    }




}
