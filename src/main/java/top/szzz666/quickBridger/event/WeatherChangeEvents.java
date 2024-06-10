package top.szzz666.quickBridger.event;

import cn.nukkit.event.Listener;
import cn.nukkit.event.level.WeatherChangeEvent;
import static top.szzz666.quickBridger.config.QbConfig.getSpawnPoint;

public class WeatherChangeEvents implements Listener {
    public void onWeatherChange(WeatherChangeEvent event) {
        if (event.getLevel() == getSpawnPoint().getLevel()) {
            event.setCancelled(true);
        }
    }
}
