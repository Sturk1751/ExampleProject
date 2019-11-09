package com.sturk.examplemod;

import com.sturk.examplemod.Commands.CheckPerms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.server.permission.PermissionAPI;
import org.apache.logging.log4j.Logger;

@Mod(modid="examplemod", name="Sturk's Example Mod", version="1.0", serverSideOnly=true, acceptableRemoteVersions="*")
public class Core {

	ExamplePermissionHandler permHandler;
	Logger logger;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();

		PermissionAPI.setPermissionHandler(permHandler = new ExamplePermissionHandler());
		permHandler.registerNode("all", PermissionLevel.ALL, "All");
		permHandler.registerNode("mod", PermissionLevel.MOD, "Mod");
		permHandler.registerNode("admin", PermissionLevel.ADMIN, "Admin");
		permHandler.registerNode("owner", PermissionLevel.OWNER, "Owner");
		permHandler.registerNode("op", PermissionLevel.OP, "OP");
		permHandler.registerNode("none", PermissionLevel.NONE, "Default");
	}

	@Mod.EventHandler
	public void start(FMLServerStartingEvent event) {
		event.registerServerCommand(new CheckPerms());
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {

	}


	@Mod.EventHandler
	public void postInit(FMLServerStartedEvent event) {
	}
}
