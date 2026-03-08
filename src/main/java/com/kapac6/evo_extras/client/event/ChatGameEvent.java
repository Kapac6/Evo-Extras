package com.kapac6.evo_extras.client.event;

import com.kapac6.evo_extras.client.features.mine.blockPH.BlockProfitPerHour;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.text.Text;

public class ChatGameEvent implements ClientReceiveMessageEvents.Game {
    @Override
    public void onReceiveGameMessage(Text text, boolean b) {
        BlockProfitPerHour.getInstance().getMessage(text, b);
    }
}
