package com.kapac6.evo_extras.client.features.notifications;

import com.kapac6.evo_extras.client.Evo_extrasClient;
import com.kapac6.evo_extras.client.config.ConfigNotify;
import com.kapac6.evo_extras.client.event.SoundEvents;
import net.minecraft.text.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BossRespawnNotify {

    static Pattern respawnPattern = Pattern.compile("§.\\[§.Evo§.Plus§.]§. §..+ §.возродился");
    public void getMessage(Text text, boolean overlay) {
        String msg = text.getString();

        if(ConfigNotify.bossSoundNotifyToggle && msg.startsWith("§7[§bEvo§fPlus§7]")) {
            Matcher matcher = respawnPattern.matcher(msg);
            if(matcher.find()) {
                if(Evo_extrasClient.instance.player == null) return;
                Evo_extrasClient.instance.player.playSound(SoundEvents.BOSSRESPAWN, (float) ConfigNotify.bossSoundNotifyVolume /100, 1f);
            }

        }
    }
}
