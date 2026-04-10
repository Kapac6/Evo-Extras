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
                0xFFFFFF, 0xFFFF55, 0xAAAAAA));

        tabs.add(new ChatTab("КЛАН", Arrays.asList("[Клан]"),
                0xFFFFFF, 0xFFFF55, 0xAAAAAA));

        tabs.add(new ChatTab("ЛС",  Arrays.asList("ЛС | "),
                0xFFFFFF, 0xFFFF55, 0xAAAAAA));

        tabs.add(new ChatTab("L",  Arrays.asList("Ⓛ "),
                0xFFFFFF, 0xFFFF55, 0xAAAAAA));

        tabs.add(new ChatTab("M", Arrays.asList("Ⓜ "),
                0xFFFFFF, 0xFFFF55, 0xAAAAAA));
    }

    public boolean shouldDisplayMessage(Text message) {
        return activeTab != null && activeTab.matches(message);
    }

    public void setActiveTab(ChatTab tab) {
        if (tab != null && tab != this.activeTab) {
            this.activeTab = tab;
            MinecraftClient.getInstance().inGameHud.getChatHud().reset();
        }
    }

    public ChatTab getActiveTab() { return this.activeTab; }
    public List<ChatTab> getTabs() { return this.tabs; }

    public ChatPrefix getCurrentPrefix() { return this.currentPrefix; }
    public void setPrefix(ChatPrefix prefix) { this.currentPrefix = prefix; }
}
