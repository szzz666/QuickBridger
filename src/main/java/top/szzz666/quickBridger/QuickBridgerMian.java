package top.szzz666.quickBridger;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scheduler.Task;
import cn.nukkit.scheduler.TaskHandler;
import cn.nukkit.utils.TextFormat;
import com.smallaswater.npc.variable.VariableManage;
import tip.utils.Api;
import top.szzz666.quickBridger.command.QbCommand;
import top.szzz666.quickBridger.config.QbConfig;
import top.szzz666.quickBridger.entity.QBer;
import top.szzz666.quickBridger.event.*;
import top.szzz666.quickBridger.variable.RsNpcVariableV2;
import top.szzz666.quickBridger.variable.TipsVariable;

import java.io.File;
import java.util.HashMap;

import static top.szzz666.quickBridger.config.QbConfig.LevelName;
import static top.szzz666.quickBridger.config.QbConfig.getSpawnPoint;
import static top.szzz666.quickBridger.tools.Utils.*;


public class QuickBridgerMian extends PluginBase {
    public static Server nkServer;
    public static CommandSender consoleObjects;
    public static String ConfigPath;
    public static Plugin plugin;
    public static HashMap<Player,QBer> AllQBer;
    private TaskHandler setTimeTask;
    @Override
    public void onLoad(){
        //插件读取
        this.getLogger().info("§b  ___       _    _   ___     _    _              ");
        this.getLogger().info("§b / _ \\ _  _(_)__| |_| _ )_ _(_)__| |__ _ ___ _ _ ");
        this.getLogger().info("§b| (_) | || | / _| / / _ \\ '_| / _` / _` / -_) '_|");
        this.getLogger().info("§b \\__\\_\\\\_,_|_\\__|_\\_\\___/_| |_\\__,_\\__, \\___|_|  ");
        this.getLogger().info("§b                                   |___/ ");
        QbConfig.loadConfig();
        this.getLogger().info("§bQuickBridger插件读取...");
        nkServer = getServer();
        plugin = this;
        consoleObjects = getServer().getConsoleSender();
        ConfigPath = getDataFolder().getPath();
        copyFiles(new File(ConfigPath + "/world/" + LevelName), new File("worlds"));
        if(!isFolder(ConfigPath + "/world")){
            createFolder(ConfigPath + "/world");
        }
        this.saveResource("config.yml");
        if (QbConfig.loadConfig()){
            this.getLogger().info("§bQuickBridger插件配置文件读取成功");
        }
    }

    @Override
    public void onEnable(){
        AllQBer = new HashMap<>();

        // 注册命令
        this.getServer().getCommandMap().register(this.getName(), new QbCommand());
        //注册监听
        this.getServer().getPluginManager().registerEvents(new CommandEvents(), this);
        this.getServer().getPluginManager().registerEvents(new BlockEvents(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerPositionEvents(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerJumpEvents(), this);
        this.getServer().getPluginManager().registerEvents(new EntityDamageEvents(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerTeleportEvents(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerToggleSneakEvents(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerDropItemEvents(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerQuitEvents(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoinEvents(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerFoodLevelChangeEvents(), this);
        this.getServer().getPluginManager().registerEvents(new WeatherChangeEvents(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerInteractEvents(), this);

        VariableManage.addVariableV2("QbNumOfPlayers", RsNpcVariableV2.class);
        Api.registerVariables("QbNumOfPlayers", TipsVariable.class);
        //永为白日
        setTimeTask = getServer().getScheduler().scheduleRepeatingTask(this, new Task() {
            @Override
            public void onRun(int currentTick) {
                getSpawnPoint().getLevel().setTime(6000);
            }
        }, 20*60);


        this.getLogger().info("§bQuickBridger插件开启");
        this.getLogger().info(TextFormat.colorize('&',"&b如果有bug加Q群反馈：894279534"));
    }

    @Override
    public void onDisable(){
        if (setTimeTask != null) {
            setTimeTask.cancel();
        }
        //插件关闭
        this.getLogger().info("§bQuickBridger插件关闭");
    }

    //是否是搭路练习者
    public static boolean isQBer(Player player){
        return AllQBer.containsKey(player);
    }

    //使用nk插件的控制台输出
    public static void nkConsole(String msg, boolean isError){
        if (isError){
            plugin.getLogger().error(TextFormat.colorize('&',msg));
        }else {
            plugin.getLogger().info(TextFormat.colorize('&',msg));
        }
    }

    //添加物品
//    public static void addItemToPlayer(Player player,Item item) {
//        if (player.getInventory().canAddItem(item)) {
//            player.getInventory().addItem(item);
//        }
//    }
}
