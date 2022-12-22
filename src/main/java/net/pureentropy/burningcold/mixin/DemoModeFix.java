package net.pureentropy.burningcold.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;

@Mixin(ClientPlayNetworkHandler.class)
public class DemoModeFix {
    @Inject(method = "onGameStateChange", at = @At("HEAD"), cancellable = true)
    private void stopDemoAndCreative(GameStateChangeS2CPacket packet, CallbackInfo ci) {

        //TODO: Cancels S2C Demo Mode packets
        if (packet.getReason() == GameStateChangeS2CPacket.DEMO_MESSAGE_SHOWN) {
            ci.cancel();
        }

        //TODO: Cancels S2C Creative mode packets
        if (packet.getReason() == GameStateChangeS2CPacket.GAME_MODE_CHANGED) {
            ci.cancel();
        }

        //TODO: Cancels S2C Game won packets
        if (packet.getReason() == GameStateChangeS2CPacket.GAME_WON) {
            ci.cancel();
        }
    }
}
