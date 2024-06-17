package top.szzz666.quickBridger.event;

import cn.nukkit.event.Listener;
import cn.nukkit.event.level.WeatherChangeEvent;
import cn.nukkit.level.Level;

import static top.szzz666.quickBridger.config.QbConfig.getSpawnPoint;

public class WeatherChangeEvents implements Listener {
    public void onWeatherChange(WeatherChangeEvent event) {
        Level level = event.getLevel();
        if (level.equals(getSpawnPoint().getLevel())) {
            event.setCancelled(true);
        }
    }
}
