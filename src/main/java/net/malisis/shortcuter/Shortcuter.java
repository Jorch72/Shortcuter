package net.malisis.shortcuter;

import java.lang.reflect.Method;

import org.lwjgl.input.Keyboard;

import net.malisis.core.IMalisisMod;
import net.malisis.core.MalisisCore;
import net.malisis.core.asm.AsmUtils;
import net.malisis.core.configuration.Settings;
import net.malisis.core.network.MalisisNetwork;
import net.malisis.core.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

@Mod(	modid = Shortcuter.modid,
		name = Shortcuter.modname,
		version = Shortcuter.version,
		dependencies = "required-after:malisiscore",
		acceptedMinecraftVersions = "[1.12, 1.13)")
public class Shortcuter implements IMalisisMod
{
	public static final String modid = "shortcuter";
	public static final String modname = "Shortcuter";
	public static final String version = "${version}";

	public static Shortcuter instance;
	public static MalisisNetwork network;

	public Shortcuter()
	{
		instance = this;
		network = new MalisisNetwork(this);
		MalisisCore.registerMod(this);
	}

	@Override
	public String getModId()
	{
		return modid;
	}

	@Override
	public String getName()
	{
		return modname;
	}

	@Override
	public String getVersion()
	{
		return version;
	}

	@Override
	public Settings getSettings()
	{
		return null;
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(this);
		ShortcutRegistry.register(Shortcut.create(Keyboard.KEY_NUMPAD1, new ItemStack(Blocks.COBBLESTONE)));
	}

	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event)
	{
		if (Utils.getClientWorld() != null)
			ShortcutRegistry.executeShortcut();
	}

	static Method rcm = AsmUtils.changeMethodAccess(Minecraft.class, "rightClickMouse", "func_147121_ag");

	public static void rightClickMouse()
	{
		try
		{
			rcm.invoke(Minecraft.getMinecraft());
		}
		catch (ReflectiveOperationException e)
		{
			e.printStackTrace();
		}
	}
}
