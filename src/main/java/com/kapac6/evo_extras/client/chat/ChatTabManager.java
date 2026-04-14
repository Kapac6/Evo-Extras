package com.kapac6.evo_extras.client.chat;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChatTabManager {
    private static ChatTabManager instance;
    private ChatPrefix currentPrefix = ChatPrefix.NONE;
    private final List<ChatTab> bannedTabs = new ArrayList<>();

    private final List<ChatTab> tabs = new ArrayList<>();
    private ChatTab activeTab;

    private ChatTabManager() {
        createTabs();
        activeTab = tabs.getFirst();
    }

    public static ChatTabManager getInstance() {
        if (instance == null) instance = new ChatTabManager();
        return instance;
    }

    private void createTabs() {
        tabs.add(new ChatTab("ВСЁ", Collections.emptyList(),
                0xFFFFFF, 0xFFFF55, 0xAAAAAA, 0xFF5555));

        tabs.add(new ChatTab("КЛАН", Arrays.asList("[Клан]"),
                0xFFFFFF, 0xFFFF55, 0xAAAAAA, 0xFF5555));

        tabs.add(new ChatTab("ЛС",  Arrays.asList("ЛС | "),
                0xFFFFFF, 0xFFFF55, 0xAAAAAA, 0xFF5555));

        tabs.add(new ChatTab("L",  Arrays.asList("Ⓛ "),
                0xFFFFFF, 0xFFFF55, 0xAAAAAA, 0xFF5555));
        tabs.add(new ChatTab("G", Arrays.asList("Ⓖ "),
                0xFFFFFF, 0xFFFF55, 0xAAAAAA, 0xFF5555));

        tabs.add(new ChatTab("M", Arrays.asList("Ⓜ "),
                0xFFFFFF, 0xFFFF55, 0xAAAAAA, 0xFF5555));
    }

    public boolean shouldDisplayMessage(Text message) {
        for(ChatTab banned : bannedTabs) {
            if(banned != activeTab && banned.matches(message)) {
                return false;
            }
        }
        return activeTab != null && activeTab.matches(message);
    }

    public void setActiveTab(ChatTab tab) {
        if (tab != null && tab != this.activeTab) {
            this.activeTab = tab;
            MinecraftClient.getInstance().inGameHud.getChatHud().reset();
        }
    }
    public ChatTab getActiveTab() { return this.activeTab; }
    public boolean isBanned(ChatTab tab) {
        if (tab != null) {
            return this.bannedTabs.contains(tab);
        }
        return false;
    }
    public void setBannedTab(ChatTab tab) {
        if (tab != null) {
            if(this.bannedTabs.contains(tab)) {
                this.bannedTabs.remove(tab);
            } else this.bannedTabs.add(tab);

            MinecraftClient.getInstance().inGameHud.getChatHud().reset();
        }
    }
    public int getBannedSize() { return this.bannedTabs.size(); }
    public List<ChatTab> getTabs() { return this.tabs; }

    public ChatPrefix getCurrentPrefix() { return this.currentPrefix; }
    public void setPrefix(ChatPrefix prefix) { this.currentPrefix = prefix; }
}
