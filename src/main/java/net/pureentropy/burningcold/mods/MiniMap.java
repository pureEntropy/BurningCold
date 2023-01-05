package net.pureentropy.burningcold.mods;

import java.util.ArrayList;

import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.math.MatrixStack;
import net.pureentropy.burningcold.utils.Category;
import net.pureentropy.burningcold.utils.Module;
import net.pureentropy.burningcold.utils.RenderUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.mob.Monster;

public class MiniMap extends Module {
	
	public MiniMap () {
		super("MiniMap", Category.RENDER, InputUtil.GLFW_KEY_M, 00);
	}

	public void render() {
		int width = mc.getWindow().getScaledWidth();
		int height = mc.getWindow().getScaledHeight();
		int mapXZero = width - 82;
		int mapYZero = 1;
		float deg = mc.player.getHeadYaw() % 360;
		
		if (deg < 0) {
			deg = deg + 360;
		}
		
		//Draw MiniMap Box
		RenderUtils.glColor(100, 100, 100, 140);
		RenderUtils.drawRect(width - 82, 1, width - 1, 82);

		//Compass
		RenderUtils.glColor(0, 0, 255, 255);
		if (deg < 45 || deg >= 315) {
			//South
			RenderUtils.drawRect(mapXZero + 40, 41, mapXZero + 41, 45);
		} else if (deg >= 45 && deg < 135) {
			//West
			RenderUtils.drawRect(mapXZero + 37, 41, mapXZero + 41, 42);
		} else if (deg >= 135 && deg < 225) {
			//North
			RenderUtils.drawRect(mapXZero + 40, 38, mapXZero + 41, 42);
		} else {
			//East
			RenderUtils.drawRect(mapXZero + 40, 41, mapXZero + 44, 42);
		}
		
		//Middle of Compass
		RenderUtils.glColor(0, 255, 0, 255);
		RenderUtils.drawRect(mapXZero + 40, 41, mapXZero + 41, 42);

		for (Entity entity: mc.world.getEntities()) {
			if (entity instanceof Monster) {
				RenderUtils.glColor(255, 0, 0, 255);
				RenderUtils.drawPixel((float) (mapXZero + adjustedCoords(entity).get(0)/2),(float) (mapYZero + adjustedCoords(entity).get(1)/2));
			} else if (entity instanceof AnimalEntity) {
				RenderUtils.glColor(0, 255, 0, 255);
				RenderUtils.drawPixel((float) (mapXZero + adjustedCoords(entity).get(0)/2),(float) (mapYZero + adjustedCoords(entity).get(1)/2));
			} else if (entity instanceof PlayerEntity) {
				RenderUtils.glColor(0, 0, 255, 255);
				RenderUtils.drawPixel((float) (mapXZero + adjustedCoords(entity).get(0)/2),(float) (mapYZero + adjustedCoords(entity).get(1)/2));
			} else {
				RenderUtils.glColor(255, 0, 255, 255);
				RenderUtils.drawPixel((float) (mapXZero + adjustedCoords(entity).get(0)/2),(float) (mapYZero + adjustedCoords(entity).get(1)/2));
			}
		}
		//Some testing code left here for reference
		/*mc.fontRenderer.drawString("BOTTOM", 1, height - 9, 0xffffffff);
		mc.fontRenderer.drawString("RIGHT", width - 30, 1, 0xffffffff);*/
		
		//Display X and Z cords
		mc.textRenderer.draw(new MatrixStack(), "X: " + (int) (mc.player.getX()), mapXZero + 3, 83, 0xffffffff);
		mc.textRenderer.draw(new MatrixStack(), "Z: " + (int) (mc.player.getZ()), mapXZero + (String.valueOf((int)mc.player.getX()).length()+3)*6, 83, 0xffffffff);
		
		RenderUtils.drawCircleBorder(100, 100, 10, 3);
	}
	
	private static double facingDeg() {
		float yaw = mc.player.getHeadYaw();
		int deg = (int) (yaw - ((yaw / 360) * 360));
		
		if (deg < 0) {
			return deg + 360;
		}
		
		return deg;
	}
	
	private static ArrayList<Double> adjustedCoords(Entity e) {
		double newX, newZ;
		ArrayList<Double> list = new ArrayList<>();
		double middleX = mc.player.getX();
		double middleZ = mc.player.getZ();
		
		newX = (e.getX() - middleX) + 81;
		newZ = (e.getZ() - middleZ) + 81;
		
		list.add(newX);
		list.add(newZ);
		return list;
	}
}
