package net.pureentropy.burningcold.mixin;

import net.minecraft.network.packet.s2c.play.WorldBorderInitializeS2CPacket;
import net.pureentropy.burningcold.utils.ModuleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldBorderInitializeS2CPacket.class)
public class WorldBorderPatch {
    @Inject(method = "apply(Lnet/minecraft/network/listener/ClientPlayPacketListener;)V", at = @At("HEAD"), cancellable = true)
    private void init(CallbackInfo ci) {
        if (ModuleManager.getMods().get(3).isEnabled()) {
            ci.cancel();
        }
    }
}
