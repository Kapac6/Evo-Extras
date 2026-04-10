package com.kapac6.evo_extras.client.chat;

import java.util.Arrays;
import java.util.List;

public enum ChatPrefix {
    NONE("", "-"),
    GLOBAL("!", "!"),
    CLAN("@", "@"),
    MARKET("$", "$"),
    REPLY("/r ", "/r");

    private final String prefix;
    private final String text;

    ChatPrefix(String prefix, String text) {
        this.prefix = prefix;
        this.text = text;
    }

    public String get() { return prefix; }
    public String getText() { return text; }

    public static List<ChatPrefix> getAll() {
        return Arrays.asList(values());
    }
}
