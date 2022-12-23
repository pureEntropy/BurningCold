package net.pureentropy.burningcold.mixin;

import net.pureentropy.burningcold.utils.ModuleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;

@Mixin(ClientPlayNetworkHandler.class)
public class GameStateChangePatcher {

    @Inject(method = "onGameStateChange", at = @At("HEAD"), cancellable = true)
    private void stopDemoAndCreative(GameStateChangeS2CPacket packet, CallbackInfo ci) {
        //Cancels S2C Demo Mode packets
        if (ModuleManager.getMods().get(0).isEnabled() && (packet.getReason() == GameStateChangeS2CPacket.DEMO_MESSAGE_SHOWN || packet.getReason() == GameStateChangeS2CPacket.GAME_MODE_CHANGED || packet.getReason() == GameStateChangeS2CPacket.GAME_WON)) {
            ci.cancel();
        }
    }
}
