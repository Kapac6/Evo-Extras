package com.kapac6.evo_extras.client.chat;

import net.minecraft.text.Text;

import java.util.List;

public record ChatTab(String name, List<String> startsWithFilters, int color, int hoveredColor, int selectedColor, int bannedColor) {

    public boolean matches(Text message) {
        if (startsWithFilters.isEmpty()) {
            return true;
        }
        String content = message.getString();
        for (String filter : startsWithFilters) {
            if (content != null && content.startsWith(filter)) {
                return true;
            }
        }
        return false;
    }

    public List<String> getFilter() {
        return this.startsWithFilters;
    }
}
