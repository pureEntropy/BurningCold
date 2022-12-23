package net.pureentropy.burningcold.mixin.utils;

import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.pureentropy.burningcold.utils.Category;
import net.pureentropy.burningcold.utils.Module;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class PacketLogger {

    //Sending
    @Inject(method = "send", at = @At("HEAD"))
    public void onSend(Packet<?> packet, CallbackInfo ci) {
        //BurningCold.LOGGER.info(packet.getClass().getSimpleName());
    }

    //Receiving
    @Inject(method = "channelRead0", at = @At("HEAD"))
    public void onReceive(ChannelHandlerContext channelHandlerContext, Packet<?> packet, CallbackInfo ci) {
        //BurningCold.LOGGER.info("Received packet: " + packet.getClass().getSimpleName());
    }
}
