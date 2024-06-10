package top.szzz666.quickBridger.config;

import cn.nukkit.Server;
import cn.nukkit.item.Item;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import cn.nukkit.utils.Config;

import java.util.ArrayList;

import static top.szzz666.quickBridger.QuickBridgerMian.ConfigPath;

public class QbConfig {
    public static String BridgingBlock;
    public static int StopBlock;
    public static int ResBlock;
    public static int SpeedupBlock;
    public static int ElevatorBlock;
    public static int ReturnBlock;
    public static String Pickaxe;
    public static double Lowy;
    public static String LevelName;
    public static String SpawnPoint;
    public static String CommandName;
    public static String JoinCommand;
    public static String QuitCommand;
    public static ArrayList<String> CommandWhiteList;
    public static boolean loadConfig() {
        Config config = new Config(ConfigPath + "/config.yml", Config.YAML);
        BridgingBlock = config.getString("BridgingBlock");
        StopBlock = config.getInt("StopBlock");
        ResBlock = config.getInt("ResBlock");
        SpeedupBlock = config.getInt("SpeedupBlock");
        ElevatorBlock = config.getInt("ElevatorBlock");
        ReturnBlock = config.getInt("ReturnBlock");
        Pickaxe = config.getString("Pickaxe");
        Lowy = config.getDouble("Lowy");
        LevelName = config.getString("LevelName");
        SpawnPoint = config.getString("SpawnPoint");
        CommandName = config.getString("CommandName");
        JoinCommand = config.getString("JoinCommand");
        QuitCommand = config.getString("QuitCommand");
        CommandWhiteList = (ArrayList<String>) config.getStringList("CommandWhiteList");
        config.save();
        return true;
    }
    public static Level getLevel() {
        return Server.getInstance().getLevelByName(LevelName);
    }
    public static Position getSpawnPoint() {
        String[] spPos = SpawnPoint.split(",");
        double spX = Double.parseDouble(spPos[0]);
        double spY = Double.parseDouble(spPos[1]);
        double spZ = Double.parseDouble(spPos[2]);
        Level level = getLevel();
        return new Position(spX,spY,spZ,level);
    }
    public static Item getBridgingBlockItem() {
        return Item.get(Integer.parseInt(BridgingBlock.split(":")[0]),
                Integer.parseInt(BridgingBlock.split(":")[1]),
                Integer.parseInt(BridgingBlock.split(":")[2]));
    }
    public static Item getPickaxeItem(){
        return Item.get(Integer.parseInt(Pickaxe.split(":")[0]),
                Integer.parseInt(Pickaxe.split(":")[1]),
                Integer.parseInt(Pickaxe.split(":")[2]));
    }
}


