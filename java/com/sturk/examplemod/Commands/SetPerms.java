package com.sturk.examplemod.Commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.server.permission.PermissionAPI;

public class SetPerms extends CommandBase {

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

	}

	@Override
	public String getName() {
		return "setperms";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "i dont think i know what i'm doing";
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return PermissionAPI.hasPermission((EntityPlayer) sender.getCommandSenderEntity(), "none");
	}
}
