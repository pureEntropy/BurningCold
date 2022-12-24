package net.pureentropy.burningcold.mixin.hooks;

import net.minecraft.client.render.chunk.ChunkOcclusionDataBuilder;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkOcclusionDataBuilder.class)
public class OpaqueBlockHook {
    @Inject(method = "markClosed", at = @At("Head"))
    private void setOpaque(BlockPos pos, CallbackInfo ci) {
        //TODO: Client Code to be finished once XRAY mod is added
        /*if (burningcold.BurningCold.modManager.isModuleEnabled("Xray")) {
            return;
        } */
    }
}
