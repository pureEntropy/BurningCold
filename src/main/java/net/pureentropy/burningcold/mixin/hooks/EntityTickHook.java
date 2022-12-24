package net.pureentropy.burningcold.mixin.hooks;

import net.minecraft.entity.Entity;
import net.pureentropy.burningcold.utils.ModuleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityTickHook {
    @Inject(method = "tick", at = @At("Head"))
    private void onUpdate(CallbackInfo ci) {
        ModuleManager.onUpdate();
    }
}
