package top.szzz666.quickBridger.variable;

import cn.nukkit.Player;
import com.smallaswater.npc.data.RsNpcConfig;
import com.smallaswater.npc.variable.BaseVariableV2;

import static top.szzz666.quickBridger.QuickBridgerMian.AllQBer;

public class RsNpcVariableV2 extends BaseVariableV2 {
    public RsNpcVariableV2() {
    }
    public void onUpdate(Player player, RsNpcConfig rsNpcConfig) {
        this.addVariable("{QbNumOfPlayers}", String.valueOf(AllQBer.size()));
    }
}
