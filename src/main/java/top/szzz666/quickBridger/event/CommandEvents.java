package top.szzz666.quickBridger.event;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;

import static top.szzz666.quickBridger.QuickBridgerMian.isQBer;
import static top.szzz666.quickBridger.config.LangConfig.PlayerCommand_sendMessage;
import static top.szzz666.quickBridger.config.QbConfig.CommandName;
import static top.szzz666.quickBridger.config.QbConfig.CommandWhiteList;

public class CommandEvents implements Listener {
    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        String command = message.replaceAll("/", "").split(" ")[0];
        //命令禁用
        if (isQBer(player) && !isWhitelistCommand(command) && !command.equals(CommandName) && !player.isOp()) {
            event.setCancelled(true);
            player.sendMessage(PlayerCommand_sendMessage);
        }
    }

    //是否是白名单命令
    private boolean isWhitelistCommand(String command) {
        return CommandWhiteList.contains(command);
    }

}
