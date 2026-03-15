package com.kapac6.evo_extras.client.event;

import com.kapac6.evo_extras.client.features.mine.blockPH.BlockProfitPerHour;
import com.kapac6.evo_extras.client.features.runes.RuneDurationBar;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.text.Text;

public class ChatGameEvent implements ClientReceiveMessageEvents.Game {
    @Override
    public void onReceiveGameMessage(Text text, boolean b) {
        BlockProfitPerHour.getInstance().getMessage(text, b);
        RuneDurationBar.getInstance().getMessage(text, b);
    }
}
