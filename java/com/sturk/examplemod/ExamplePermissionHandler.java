package com.sturk.examplemod;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.server.permission.DefaultPermissionLevel;
import net.minecraftforge.server.permission.IPermissionHandler;
import net.minecraftforge.server.permission.context.IContext;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class ExamplePermissionHandler implements IPermissionHandler {

	private static final HashMap<String, PermissionLevel> PERMISSION_LEVEL_MAP = new HashMap<String, PermissionLevel>();
	private static final HashMap<String, String> DESCRIPTION_MAP = new HashMap<String, String>();

	@Override
	public void registerNode(String node, DefaultPermissionLevel level, String desc) {
		/*PERMISSION_LEVEL_MAP.put(node, level);

		if (!desc.isEmpty()) {
			DESCRIPTION_MAP.put(node, desc);
		}*/
	}

	public void registerNode(String node, PermissionLevel level, String desc) {
		PERMISSION_LEVEL_MAP.put(node, level);

		if (!desc.isEmpty()) {
			DESCRIPTION_MAP.put(node, desc);
		}
	}


	@Override
	public Collection<String> getRegisteredNodes() {
		return Collections.unmodifiableSet(PERMISSION_LEVEL_MAP.keySet());
	}

	@Override
	public boolean hasPermission(GameProfile profile, String node, @Nullable IContext context) {
		PermissionLevel level = getDefaultPermissionLevel(node);

		if(level == PermissionLevel.NONE)
			return false;
		else if(level == PermissionLevel.OP)
			return true;

		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
		return server != null && server.getPlayerList().canSendCommands(profile);
	}

	@Override
	public String getNodeDescription(String node) {
		String desc = DESCRIPTION_MAP.get(node);
		return desc == null ? "" : desc;
	}

	public PermissionLevel getDefaultPermissionLevel(String node) {
		PermissionLevel level = PERMISSION_LEVEL_MAP.get(node);
		return level == null ? PermissionLevel.NONE : level;
	}
}
