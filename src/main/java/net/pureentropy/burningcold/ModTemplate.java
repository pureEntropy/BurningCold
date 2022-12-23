package net.pureentropy.burningcold;

import net.minecraft.client.util.InputUtil;
import net.pureentropy.burningcold.BurningCold;
import net.pureentropy.burningcold.utils.Category;
import net.pureentropy.burningcold.utils.Module;

public class ModTemplate extends Module
{

    public ModTemplate()
    {
        super("MOD NAME", Category.MOVEMENT, InputUtil.GLFW_KEY_J, 00);
    }

    @Override
    public void onEnable()
    {
        super.onEnable();
    }

    @Override
    public void onDisable()
    {
        super.onDisable();
    }

    @Override
    public void onUpdate()
    {
        if (!isEnabled())
            return;

        super.onUpdate();
    }
}