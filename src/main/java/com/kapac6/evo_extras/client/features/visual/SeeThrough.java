package com.kapac6.evo_extras.client.features.visual;

import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class SeeThrough {
    public static byte latestAlpha = 101;
    public static void setOpacity(byte alpha) {
        float opacity = alpha/100f;
        if(latestAlpha != alpha) GLFW.glfwSetWindowOpacity(MinecraftClient.getInstance().getWindow().getHandle(), opacity);
        latestAlpha = alpha;
    }

}
