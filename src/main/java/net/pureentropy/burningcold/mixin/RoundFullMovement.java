package net.pureentropy.burningcold.mixin;

import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.pureentropy.burningcold.BurningCold;
import net.pureentropy.burningcold.utils.ModuleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import static net.pureentropy.burningcold.utils.MathUtils.roundCoordinate;

@Mixin(PlayerMoveC2SPacket.Full.class)
public class RoundFullMovement {

    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/packet/c2s/play/PlayerMoveC2SPacket;<init>(DDDFFZZZ)V"))
    private static void init(Args args) {
        if (ModuleManager.getMods().get(1).isEnabled()) {
            double newX = roundCoordinate(args.get(0));
            double newZ = roundCoordinate(args.get(2));

            args.set(0, newX); //Set rounded X
            args.set(2, newZ); //Set rounded Z
            //BurningCold.LOGGER.info("Full Packet Loaded. X: " + newX + " Z: " + newZ);
        }
    }
}
