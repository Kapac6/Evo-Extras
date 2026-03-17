package com.kapac6.evo_extras.client.event;

import com.kapac6.evo_extras.client.Evo_extrasClient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundEvents {
    public static SoundEvent BOSSRESPAWN = registerSound("bossresp");


    private static SoundEvent registerSound(String id) {
        Identifier identifier = Identifier.of(Evo_extrasClient.MODID, id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static void initialize() {
        Evo_extrasClient.logger.info("[Evo Extras] Sounds initialized!");
    }
}
