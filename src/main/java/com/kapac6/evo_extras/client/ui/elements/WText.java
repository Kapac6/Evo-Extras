package com.kapac6.evo_extras.client.ui.elements;

import net.minecraft.text.Text;

public class WText {
    private final Text text;
    private final int color;
    private int x;
    private int y;
    private final boolean padding;
    WText(Text text, int color, int x, int y, boolean padding) {
        this.text = text;
        this.color = color;
        this.x = x;
        this.y = y;
        this.padding = padding;
    }
    public Text getText() {
        return text;
    }
    public int getColor() {
        return color;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public boolean padding() {
        return padding;
    }
}
