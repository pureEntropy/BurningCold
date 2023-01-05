package net.pureentropy.burningcold.mixin;

// import the necessary classes from the Fabric API and Minecraft
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(WorldRenderer.class)
public class MiniMapMixin {
    @Shadow @Final private MinecraftClient client;
    @Inject(method = "render", at = @At("HEAD"))
    private void renderMinimap(MatrixStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f matrix4f, CallbackInfo ci) {
        // get the player's position and camera direction
        Entity player = client.player;
        assert player != null;
        Vec3d pos = player.getPos();
        Vec3d dir = player.getRotationVector();

        // create a bounding box that represents the area that the player can see
        Box box = new Box(pos.x + dir.x - 5, pos.y + dir.y - 5, pos.z + dir.z - 5,
                pos.x + dir.x + 5, pos.y + dir.y + 5, pos.z + dir.z + 5);

        // iterate through all the entities within the bounding box
        assert client.world != null;
        for (Entity entity : client.world.getEntities()) {
            // get the entity's position and draw a dot on the minimap
            Vec3d entityPos = entity.getPos();
            drawMinimapDot(matrices, (int) entityPos.x, (int) entityPos.z, entity.getType());
        }
    }

    private void drawMinimapDot(MatrixStack matrices, int x, int z, EntityType<?> entityType) {
        // set the color of the dot based on the entity type
        Color color;
        if (entityType == EntityType.ZOMBIE) {
            color = Color.RED;
        } else if (entityType == EntityType.SKELETON) {
            color = Color.WHITE;
        } else if (entityType == EntityType.CREEPER) {
            color = Color.GREEN;
        } else {
            color = Color.BLACK;
        }

        // get the screen dimensions and the scale of the minimap
        int screenWidth = client.getWindow().getScaledWidth();
        int screenHeight = client.getWindow().getScaledHeight();
        int minimapScale = 4; // adjust this value to change the size of the dots on the minimap

        // calculate the position of the dot on the minimap
        int minimapX = x / minimapScale + screenWidth / 2;
        int minimapY = z / minimapScale + screenHeight / 2;

        // draw the dot on the minimap using the specified color
        RenderSystem.clearColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ZERO);
        RenderSystem.lineWidth(4.0f);
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(VertexFormat.DrawMode.LINES, VertexFormats.POSITION);
        bufferBuilder.vertex(matrix4f, minimapX, minimapY, 0.0f).next();
        bufferBuilder.vertex(matrix4f, minimapX, minimapY + 1, 0.0f).next();
        tessellator.draw();
        RenderSystem.disableBlend();
    }
}
