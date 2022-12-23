package net.pureentropy.burningcold.mixin.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.pureentropy.burningcold.utils.Category;
import net.pureentropy.burningcold.utils.Module;
import net.pureentropy.burningcold.utils.ModuleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class renderMixin {
    @Inject(method = "render", at = @At("RETURN"), cancellable = true)
    public void onRender (MatrixStack matrices, float tickDelta, CallbackInfo info) {
        String client_name = "\247c\247lBurning \2479\247lCold \247f\247lv0.0.2";
        MinecraftClient.getInstance().textRenderer.draw(matrices, client_name, 4, 4, 0xffffffff);

        //Render enabled Modules
        int c = 0;
        for (Module mod : ModuleManager.getMods()) {
            if (!mod.isEnabled() || mod.getCategory() == Category.PATCH) {
                continue;
            }

            MinecraftClient.getInstance().textRenderer.draw(matrices, mod.getName(), 4, 20 + (10 * c), 0xffffffff);
            c++;
        }

        //Insert onRender listener for mods
        ModuleManager.onRender();
    }
}
