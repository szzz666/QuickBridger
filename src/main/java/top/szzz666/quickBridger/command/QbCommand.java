package top.szzz666.quickBridger.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;


import static top.szzz666.quickBridger.config.LangConfig.QbCommand_description;
import static top.szzz666.quickBridger.config.QbConfig.*;


public class QbCommand extends Command {
    public QbCommand() {
        super(CommandName, QbCommand_description);
        //设置命令参数在客户端显示，可以tab补全
//        HashMap<String,String> subCommand = new HashMap<String,String> ();
//        subCommand.put("join","加入搭路练习");
//        subCommand.put("quit","退出搭路练习");
//        addSubCommand(subCommand);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (sender.isPlayer()) {
            if (args.length == 0){
                sender.sendMessage("§c输入的命令不完整");
                return true;
            }
            if (args[0].equals(JoinCommand)){
                Player player = (Player) sender;
                player.teleport(getSpawnPoint());
                return true;
            }
            if (args[0].equals(QuitCommand)){
                Player player = (Player) sender;
                player.teleport(player.getSpawn());
                return true;
            }
        }
        return false;
    }

}
