package com.sturk.examplemod.Commands;

import com.sturk.examplemod.PermissionLevel;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.server.permission.PermissionAPI;

import java.awt.*;

public class CheckPerms extends CommandBase {

	private final boolean DEBUG = true;

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(DEBUG) {
			sender.sendMessage(new TextComponentString("" + args.length));
			sender.sendMessage(new TextComponentString(sender.getName()));
		}

		if(args.length == 0)
			sender.sendMessage(new TextComponentString(getPermission(sender)));
		else if (args.length > 0){
			EntityPlayerMP target = server.getPlayerList().getPlayerByUsername(args[0]);
			if(target != null) {
				sender.sendMessage(new TextComponentString(getPermission(target)));
			} else {
				sender.sendMessage(new TextComponentString("Player not found."));
			}
		} else {
			sender.sendMessage(new TextComponentString("Invalid usage."));
		}
	}

	@Override
	public String getName() {
		return "checkperms";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "xd";
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true; //getPermission(sender) == "all";
	}

	public String getPermission(ICommandSender sender) {
		EntityPlayer player = (EntityPlayer) sender.getCommandSenderEntity();
		return getPermission(player);
	}

	public String getPermission(EntityPlayer player) {
		if(PermissionAPI.hasPermission(player, "all"))
			return PermissionLevel.ALL.toString();
		if(PermissionAPI.hasPermission(player, "mod"))
			return PermissionLevel.MOD.toString();
		if(PermissionAPI.hasPermission(player, "admin"))
			return PermissionLevel.ADMIN.toString();
		if(PermissionAPI.hasPermission(player, "owner"))
			return PermissionLevel.OWNER.toString();
		if(PermissionAPI.hasPermission(player, "op"))
			return PermissionLevel.OP.toString();
		else
			return "ERROR";
	}
}
