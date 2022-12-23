package net.pureentropy.burningcold.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.math.MatrixStack;
import net.pureentropy.burningcold.mixin.GameStateChangePatcher;
import net.pureentropy.burningcold.mixin.RoundFullMovement;

import java.util.ArrayList;
/* import burningcold.BurningCold;
import burningcold.gui.InGameGui;
import burningcold.mods.AutoWalk;
import burningcold.mods.Flight;
import burningcold.mods.Freecam;
import burningcold.mods.FullBright;
import burningcold.mods.KillAura;
import burningcold.mods.NoSlowDown;
import burningcold.mods.StorageESP;
import burningcold.mods.Tracers;
import burningcold.mods.WaterWalking;
import burningcold.mods.Xray;
import burningcold.utils.XrayUtils; */

public class ModuleManager {

    public ModuleManager() {
        register();
    }
    private static ArrayList<Module> mods;

    public Module getModsByClass(Class <? extends Module> c) {
        for (Module m: mods) {
            if (m.getClass().equals(c)) {
                return m;
            }
        }
        return null;
    }

    public static void register() {

        //XrayUtils.addDefaultBlocks();
        mods = new ArrayList();

        //Adding Mixins to mod list for management
        Module GameStateChangePatcher = new Module("Game State Listener", Category.PATCH, InputUtil.GLFW_KEY_LEFT_ALT, 00);
        Module RoundFullMovement = new Module("Full Anti-Human Patch", Category.PATCH, InputUtil.GLFW_KEY_LEFT_ALT, 00);
        Module RoundOnGroundMovement = new Module("On Ground Anti-Human Patch", Category.PATCH, InputUtil.GLFW_KEY_LEFT_ALT, 00);
        Module WorldBorderPatch = new Module("World Border Patch", Category.PATCH, InputUtil.GLFW_KEY_LEFT_ALT, 00);

        addModule(GameStateChangePatcher);  //0
        addModule(RoundFullMovement);       //1
        addModule(RoundOnGroundMovement);   //2
        addModule(WorldBorderPatch);        //3

        //addModule(new Flight());
        //addModule(new FullBright());
        //addModule(new KillAura());
        //addModule(new StorageESP());
        //addModule(new Tracers());
        //addModule(new Freecam());
        //addModule(new Xray());
        //addModule(new AutoWalk());
        //addModule(new NoSlowDown());

        //addModule(new WaterWalking());

        //mods.get(3).toggleModule(); //Toggle StorageESP by default
        //mods.get(4).toggleModule(); //Toggle Tracers by default

        mods.get(0).toggleModule(); //Toggle Game State Listener by default
        mods.get(1).toggleModule(); //Full Anti-Human Patch by default
        mods.get(2).toggleModule(); //On Ground Anti-Human Patch by default
        mods.get(3).toggleModule(); //World Border Patch by default
    }

    public static ArrayList<Module> getMods() {
        return mods;
    }

    private static void addModule(Module m) {
        mods.add(m);
    }

    public static void onKeypressed(int k) {
        for (Module m: mods) {
            if (k == m.getKeybind()) {
                m.toggleModule();
            }
        }
    }

    public static void onUpdate() {
        for (Module m: mods) {
            m.onUpdate();
        }
    }

    public static void onRender() {
        for (Module m: mods) {
            m.onRender();
        }
    }
}