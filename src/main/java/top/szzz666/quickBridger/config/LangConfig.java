package top.szzz666.quickBridger.config;

import cn.nukkit.utils.Config;

import static top.szzz666.quickBridger.config.QbConfig.Language;
import static top.szzz666.quickBridger.QuickBridgerMian.ConfigPath;
import static top.szzz666.quickBridger.QuickBridgerMian.plugin;

public class LangConfig {
public static String QbCommand_description;
public static String PlayerCommand_sendMessage;
public static String EntityDamage_sendTitle;
public static String PlayerDropItem_sendMessage;
public static String PlayerInteract_sendActionBar;
public static String PlayerJoin_sandMsgToQber;
public static String PlayerQuit_sandMsgToQber;
public static String elevatorUp;
public static String elevatorDown;
public static String setTheSpawnPoint;
public static String sprint;
public static String success;
    public static boolean loadLangConfig() {
        plugin.saveResource("language/chs.yml");
        plugin.saveResource("language/eng.yml");
        Config LangConfig = new Config(ConfigPath + "/language/" + Language, Config.YAML);
        QbCommand_description = LangConfig.getString("QbCommand_description");
        PlayerCommand_sendMessage = LangConfig.getString("PlayerCommand_sendMessage");
        EntityDamage_sendTitle = LangConfig.getString("EntityDamage_sendTitle");
        PlayerDropItem_sendMessage = LangConfig.getString("PlayerDropItem_sendMessage");
        PlayerInteract_sendActionBar = LangConfig.getString("PlayerInteract_sendActionBar");
        PlayerJoin_sandMsgToQber = LangConfig.getString("PlayerJoin_sandMsgToQber");
        PlayerQuit_sandMsgToQber = LangConfig.getString("PlayerQuit_sandMsgToQber");
        elevatorUp = LangConfig.getString("elevatorUp");
        elevatorDown = LangConfig.getString("elevatorDown");
        setTheSpawnPoint = LangConfig.getString("setTheSpawnPoint");
        sprint = LangConfig.getString("sprint");
        success = LangConfig.getString("success");
        LangConfig.save();
        return true;
    }
}
