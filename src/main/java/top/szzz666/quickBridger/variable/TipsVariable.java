package top.szzz666.quickBridger.variable;

import cn.nukkit.Player;
import com.smallaswater.npc.data.RsNpcConfig;
import tip.utils.Api;
import tip.utils.variables.BaseVariable;

import static top.szzz666.quickBridger.QuickBridgerMian.AllQBer;

public class TipsVariable extends BaseVariable {

    public TipsVariable(Player player) {
        super(player);
    }

    @Override
    public void strReplace() {
        addStrReplaceString("{QbNumOfPlayers}", String.valueOf(AllQBer.size()));
    }
}
