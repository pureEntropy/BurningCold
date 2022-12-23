package net.pureentropy.burningcold.utils;

import net.minecraft.client.MinecraftClient;

public class Module implements ModuleInterface
{
	protected static MinecraftClient mc = MinecraftClient.getInstance();
	private String name;
	private Category category;
	private int key;
	private boolean enabled;
	private int color;
	
	public Module(String modName, Category c, int keybind, int HUDColor) {
		name = modName;
		category = c;
		key = keybind;
		color = HUDColor;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public Category getCategory()
	{
		return category;
	}

	@Override
	public int getKeybind()
	{
		return key;
	}
	
	@Override
	public int getColor()
	{
		return color;
	}

	@Override
	public boolean isEnabled()
	{
		return enabled;
	}

	@Override
	public void setKeybind(int k)
	{
		key = k;
	}

	@Override
	public void setToggle(boolean b) {
		onToggle();
		enabled = b;
		if (b) {
			onEnable();
		} else {
			onDisable();
		}
	}

	@Override
	public void toggleModule()
	{
		setToggle(!enabled);
	}

	@Override
	public void onToggle() {
		//empty for now
	}

	@Override
	public void onEnable() {
		//empty for now
	}

	@Override
	public void onDisable() {
		//empty for now
	}

	@Override
	public void onUpdate() {
		//empty for now
	}

	@Override
	public void onRender() {
		//empty for now
	}
}
