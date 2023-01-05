package net.pureentropy.burningcold.utils;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.Vector3d;
import org.lwjgl.opengl.GL11;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glVertex3d;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glDepthMask;
import static org.lwjgl.opengl.GL11.glLineWidth;

public class RenderUtils
{	
	public static void glColor(float r, float g, float b, float a) {
		GL11.glColor4d(r/255, g/255, b/255, a/255);
	}
	
	public static final double circleSteps = 30.0;
	//private static final Logger Logger = LogManager.getLogger();
	private static MinecraftClient mc = MinecraftClient.getInstance();
	private static ClientPlayerEntity player = mc.player;
	
	public static void drawPixel(float x, float y) {
		//GL11.glColor4d(0.5F, 0.5F, 0.5F, 0.5F);
		Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        vertexbuffer.begin(VertexFormat.DrawMode.LINES, VertexFormats.POSITION);
        vertexbuffer.sortFrom(x, y + 1, 0.0F);
        vertexbuffer.sortFrom(x + 1, y + 1, 0.0F);
        vertexbuffer.sortFrom(x + 1, y, 0.0F);
        vertexbuffer.sortFrom(x, y, 0.0F);
        tessellator.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_BLEND);
	}
	
	public static void drawRect(float x, float y, float width, float height) {
		//GL11.glColor4d(0.5F, 0.5F, 0.5F, 0.5F);
		Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        vertexbuffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION);
        vertexbuffer.sortFrom(x, height, 0.0F);
        vertexbuffer.sortFrom(width, height, 0.0F);
        vertexbuffer.sortFrom(width, y, 0.0F);
        vertexbuffer.sortFrom(x, y, 0.0F);
        tessellator.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_BLEND);
	}
	
	public static void drawTracerLine(Entity entity, int presetColor)
	{
		double x = entity.prevX + (entity.getX() - entity.prevX);
		double y = entity.prevY + (entity.getY() - entity.prevY);
		double z = entity.prevZ + (entity.getZ() - entity.prevZ);
		//Vector3d cursor = new Vector3d(0, 0, 1).rotatePitch( - (float) Math.toRadians(player.rotationPitch)).rotateYaw( - (float) Math.toRadians(player.rotationYaw));
		Vector3d cursor = new Vector3d(0, 0, 1);

		glBlendFunc(770, 771);
		glEnable(GL_BLEND);
		glLineWidth(2.0F);
		glDisable(GL11.GL_TEXTURE_2D);
		glDisable(GL_DEPTH_TEST);
		glDepthMask(false);
		
		switch (presetColor)
		{
			//	(RED, GREEN, BLUE, ALPHA)
			case 0: GL11.glColor4d(0, 0, 1, 0.5F); //BLUE
				break;
			case 1: GL11.glColor4d(0, 1, 0, 0.5F); //GREEN
				break;
			case 2: GL11.glColor4d(1, 0, 0, 0.5F); //RED
				break;
			case 3: GL11.glColor4d(0, 1, 1, 0.5F); //LIGHT-BLUE
				break;
			case 4: GL11.glColor4d(1, 0, 1, 0.5F); //MAGENTA
				break;
		}
		
		glBegin(GL_LINES);
		{
			glVertex3d(cursor.x, cursor.y + player.getEyeY(), cursor.z);
			glVertex3d(x, y, z);
		}
		
		glEnd();
		glEnable(GL11.GL_TEXTURE_2D);
		glEnable(GL_DEPTH_TEST);
		glDepthMask(true);
		glDisable(GL_BLEND);
	}
	
	public static void drawEspBox(BlockPos b, double red, double green, double blue)
	{
		int x = b.getX();
		int y = b.getY();
		int z = b.getZ();
		GL11.glBlendFunc(770, 771);
		GL11.glEnable(GL_BLEND);
		GL11.glLineWidth(2.0F);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glColor4d(red, green, blue, 0.15F);

		drawBox(x, y, 1, 1, red, green, blue);
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		GL11.glDisable(GL_BLEND);
	}
	
	public static void drawBox(int x, int y, int width, int height, double r, double g, double b) {
		RenderSystem.disableTexture();
		RenderSystem.enableBlend();
		RenderSystem.disableDepthTest();
		GL11.glColor3d(r, g, b);
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glVertex2i(x, y);
			GL11.glVertex2i(x, y + height);
			GL11.glVertex2i(x + width, y + height);
			GL11.glVertex2i(x + width, y);
		}
		GL11.glEnd();
		RenderSystem.enableTexture();
		RenderSystem.enableDepthTest();
		RenderSystem.disableBlend();
	}
	
		public static void drawCircleBorder(double x, double y, double r, double width) {
			Tessellator ts = Tessellator.getInstance();
			BufferBuilder vb = ts.getBuffer();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			vb.begin(VertexFormat.DrawMode.TRIANGLE_STRIP, VertexFormats.POSITION_TEXTURE);
        	//tes.startDrawing(GL11.GL_TRIANGLE_STRIP);
        	// for some the circle is only drawn if theta is decreasing rather than ascending
        	double end = Math.PI * 2.0;
        	double incr = end / 30;
        	double r2 = r + width;
	        for (double theta = -incr; theta < end; theta += incr) {
	        	vb.sortFrom((float) (x + (r * Math.cos(-theta))), (float) (y + (r * Math.sin(-theta))), 1000.0F);
	        	//Logger.info(x +":"+ y +":"+ 80);
	        	vb.sortFrom((float) (x + (r2 * Math.cos(-theta))), (float) (y + (r2 * Math.sin(-theta))), 1000.0F);
	        }
	        ts.draw();
	        GL11.glEnable(GL11.GL_TEXTURE_2D);
	        GL11.glDisable(GL11.GL_BLEND);
		}
		
		public static void BlockEntityESPBox(BlockEntity entity, double r, double g, double b) {
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glLineWidth(1F);
			double minX = entity.getPos().getX() - mc.getCameraEntity().getX();
			double maxX = (entity.getPos().getX() - mc.getCameraEntity().getX()) + 1;
			double minY = (entity.getPos().getY() - mc.getCameraEntity().getY() - player.getEyeY());
			double maxY = (entity.getPos().getY() - mc.getCameraEntity().getY() - player.getEyeY()) + 1;
			double minZ = (entity.getPos().getZ()) - mc.getCameraEntity().getZ();
			double maxZ = (entity.getPos().getZ() - mc.getCameraEntity().getZ()) + 1;
			GL11.glColor3d(r, g, b);
			GL11.glBegin(GL11.GL_LINES);
			{

				GL11.glVertex3d(minX, minY, minZ);
				GL11.glVertex3d(maxX, minY, minZ);

				GL11.glVertex3d(maxX, minY, minZ);
				GL11.glVertex3d(maxX, minY, maxZ);

				GL11.glVertex3d(maxX, minY, maxZ);
				GL11.glVertex3d(minX, minY, maxZ);

				GL11.glVertex3d(minX, minY, maxZ);
				GL11.glVertex3d(minX, minY, minZ);

				GL11.glVertex3d(minX, minY, minZ);
				GL11.glVertex3d(minX, maxY, minZ);

				GL11.glVertex3d(maxX, minY, minZ);
				GL11.glVertex3d(maxX, maxY, minZ);

				GL11.glVertex3d(maxX, minY, maxZ);
				GL11.glVertex3d(maxX, maxY, maxZ);

				GL11.glVertex3d(minX, minY, maxZ);
				GL11.glVertex3d(minX, maxY, maxZ);

				GL11.glVertex3d(minX, maxY, minZ);
				GL11.glVertex3d(maxX, maxY, minZ);

				GL11.glVertex3d(maxX, maxY, minZ);
				GL11.glVertex3d(maxX, maxY, maxZ);

				GL11.glVertex3d(maxX, maxY, maxZ);
				GL11.glVertex3d(minX, maxY, maxZ);

				GL11.glVertex3d(minX, maxY, maxZ);
				GL11.glVertex3d(minX, maxY, minZ);
			}
			GL11.glEnd();

			GL11.glBegin(GL11.GL_QUADS);
			{
				GL11.glColor4d(r, g, b, 0.2);
				GL11.glVertex3d(minX, minY, minZ);
				GL11.glVertex3d(maxX, minY, minZ);
				GL11.glVertex3d(maxX, minY, maxZ);
				GL11.glVertex3d(minX, minY, maxZ);
				
				GL11.glVertex3d(minX, maxY, minZ);
				GL11.glVertex3d(minX, maxY, maxZ);
				GL11.glVertex3d(maxX, maxY, maxZ);
				GL11.glVertex3d(maxX, maxY, minZ);
				
				GL11.glVertex3d(minX, minY, minZ);
				GL11.glVertex3d(minX, maxY, minZ);
				GL11.glVertex3d(maxX, maxY, minZ);
				GL11.glVertex3d(maxX, minY, minZ);
				
				GL11.glVertex3d(maxX, minY, minZ);
				GL11.glVertex3d(maxX, maxY, minZ);
				GL11.glVertex3d(maxX, maxY, maxZ);
				GL11.glVertex3d(maxX, minY, maxZ);
				
				GL11.glVertex3d(minX, minY, maxZ);
				GL11.glVertex3d(maxX, minY, maxZ);
				GL11.glVertex3d(maxX, maxY, maxZ);
				GL11.glVertex3d(minX, maxY, maxZ);
				
				GL11.glVertex3d(minX, minY, minZ);
				GL11.glVertex3d(minX, minY, maxZ);
				GL11.glVertex3d(minX, maxY, maxZ);
				GL11.glVertex3d(minX, maxY, minZ);
			}
			GL11.glEnd();
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
		}
}
