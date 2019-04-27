package me.hsgamer.nbtcommanditems.commands;

import me.hsgamer.nbtcommanditems.Utils;
import me.hsgamer.nbtcommanditems.commands.subcommands.*;
import me.hsgamer.nbtcommanditems.enums.ConfigEnums;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PluginCommand implements TabExecutor, CommandExecutor {
    private static final String SET_COMMAND = "setcommand";
    private static final String ADD_COMMAND = "addcommand";
    private static final String DEL_COMMAND = "delcommand";
    private static final String SET_ONE_TIME_USE = "setonetimeuse";
    private static final String SET_SEND_AS = "setsendas";
    private static final String GET_COMMAND = "getcommand";
    private static final String HELP = "help";
    private static final String ABOUT = "about";

    private HashMap<String, SubCommand> subcommands = new HashMap<>();

    public PluginCommand() {
        subcommands.put(SET_COMMAND, new SetCommand());
        subcommands.put(ADD_COMMAND, new AddCommand());
        subcommands.put(DEL_COMMAND, new DelCommand());
        subcommands.put(SET_ONE_TIME_USE, new SetOneTimeUse());
        subcommands.put(SET_SEND_AS, new SetSendAs());
        subcommands.put(GET_COMMAND, new GetCommand());
        subcommands.put(ABOUT, new AboutCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1 || args[0].equalsIgnoreCase(HELP) || !subcommands.containsKey(args[0].toLowerCase())) {
            // Create Help Info
            List<String> list = new ArrayList<>();
            list.addAll((List<String>) Utils.getValueFromConfig(ConfigEnums.HELP_HEADER));
            List<String> helpinfo = (List<String>) Utils.getValueFromConfig(ConfigEnums.HELP_INFO);
            for (HashMap.Entry<String, SubCommand> entry : subcommands.entrySet()) {
                List<String> helpinfocopy = new ArrayList<>();
                for (String string : helpinfo) {
                    helpinfocopy.add(string.replace("<subcommand>", entry.getKey()).replace("<description>", entry.getValue().getDescription()));
                }
                list.addAll(helpinfocopy);
            }
            for (String string : helpinfo) {
                list.add(string.replace("<subcommand>", HELP).replace("<description>", (String) Utils.getValueFromConfig(ConfigEnums.DESCRIPTION_HELP)));
            }
            list.addAll((List<String>) Utils.getValueFromConfig(ConfigEnums.HELP_FOOTER));
            // Send Help Info
            Utils.sendMessage(sender, list, false);
        } else {
            return subcommands.get(args[0].toLowerCase()).onCommand(sender, args);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length < 1 || args[0].equals("")) {
            list.add(HELP);
            list.addAll(subcommands.keySet());
        } else if (subcommands.containsKey(args[0].toLowerCase())) {
            list = subcommands.get(args[0].toLowerCase()).onTabComplete(sender, args);
        } else if (HELP.startsWith(args[0].toLowerCase())) {
            list.add(HELP);
        } else {
            for (String subcommand : subcommands.keySet()) {
                if (subcommand.startsWith(args[0].toLowerCase())) {
                    list.add(subcommand);
                }
            }
        }
        return list;
    }
}
