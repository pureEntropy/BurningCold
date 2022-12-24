package net.pureentropy.burningcold.mixin.hooks;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.pureentropy.burningcold.BurningCold;
import net.pureentropy.burningcold.utils.ModuleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyBinding.class)
public class onKeyPress {
    @Inject(method = "onKeyPressed", at = @At("Head"))
    private static void onKeyPressed(InputUtil.Key key, CallbackInfo ci) {
        ModuleManager.onKeypressed(key.getCode());
        //BurningCold.LOGGER.info(key.getTranslationKey() + " key was pressed.");
    }
}
