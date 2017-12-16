package net.malisis.shortcuter;

import net.malisis.core.IMalisisMod;
import net.malisis.core.MalisisCore;
import net.malisis.core.configuration.Settings;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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

	public Shortcuter()
	{
		instance = this;
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
	{}
}
