package net.pureentropy.burningcold.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.pureentropy.burningcold.utils.Category;

public interface ModuleInterface
{
    String getName();
    Category getCategory();
    int getKeybind();
    int getColor();
    boolean isEnabled();
    void setKeybind(int paramInt);
    void setToggle(boolean paramBoolean);
    void toggleModule();
    void onToggle();
    void onEnable();
    void onDisable();
    void onUpdate();
    void onRender();
    void onAttack(PlayerEntity player, Entity target);
}
