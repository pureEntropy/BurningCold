package net.pureentropy.burningcold.mixin;

import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import static net.pureentropy.burningcold.utils.MathUtils.roundCoordinate;

@Mixin(PlayerMoveC2SPacket.Full.class)
public class RoundFullMovement {

    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/packet/c2s/play/PlayerMoveC2SPacket;<init>(DDDFFZZZ)V"))
    private static void init(Args args) {
        double newX = roundCoordinate(args.get(0));
        double newZ = roundCoordinate(args.get(2));

        args.set(0, newX); //Set rounded X
        args.set(2, newZ); //Set rounded Z
        //BurningCold.LOGGER.info("Full Packet Loaded. X: " + newX + " Z: " + newZ);
    }

    /*@ModifyArgs(method = "Lnet/minecraft/network/packet/c2s/play/PlayerMoveC2SPacket$Full;<init>(DDDFFZ)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/packet/c2s/play/PlayerMoveC2SPacket$Full;<init>(DDDFFZ)V"))
    public static void init(Args args, CallbackInfo ci) {
        args.set(0, roundCoordinate(args.get(0)));  // Round x
        args.set(2, roundCoordinate(args.get(2)));  // Round z
    }*/

    /*@ModifyVariable(method = "<init>", at = @At(value = "STORE"), ordinal = 0)
    public double injectedX(double x) {
        double newX = roundCoordinate(x);
        BurningCold.LOGGER.info("X value : " + newX);
        return newX;
    }

    @ModifyVariable(method = "<init>", at = @At(value = "STORE"), ordinal = 2)
    public double injectedZ(double z) {
        double newZ = roundCoordinate(z);
        BurningCold.LOGGER.info("Z value : " + newZ);
        return newZ;
    }*/
}
