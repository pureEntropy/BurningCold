package net.pureentropy.burningcold.mods;

import net.minecraft.client.util.InputUtil;
import net.pureentropy.burningcold.utils.Category;
import net.pureentropy.burningcold.utils.Module;

public class AutoWalk extends Module
{

    public AutoWalk()
    {
        super("Auto Walk", Category.MOVEMENT, InputUtil.GLFW_KEY_H, 00);
    }

    @Override
    public void onEnable()
    {
        super.onEnable();
    }

    @Override
    public void onDisable()
    {
        mc.options.forwardKey.setPressed(false);
        super.onDisable();
    }

    @Override
    public void onUpdate()
    {
        if (!isEnabled()) {
            return;
        }

        if (!mc.options.forwardKey.isPressed()) {
            mc.options.forwardKey.setPressed(true);
        }

        super.onUpdate();
    }
}