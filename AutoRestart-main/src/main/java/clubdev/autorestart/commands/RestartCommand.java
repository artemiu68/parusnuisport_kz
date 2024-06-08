package clubdev.autorestart.commands;

import clubdev.autorestart.AutoRestart;
import clubdev.autorestart.managers.RestartManager.Type;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

public class RestartCommand extends Command {
    
    private AutoRestart main;

    public RestartCommand(AutoRestart main) {
        super("restart", "Рестарт");
        this.setPermission("autorestart.command.restart");

        this.commandParameters.clear();
        this.main = main;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {

        if (!sender.hasPermission(getPermission())) {
            sender.sendMessage("У вас нет разрешения на использование этой команды.");
            return false;
        }

        if (args.length == 1 && args[0].equals("true")) {
            boolean restart = main.getRestartManager().restart(Type.COMMAND);
            if (!restart) {
                sender.sendMessage(main.getPrefix() + "Рестарт в данный момент невозможен.");
            }
        } else {
            sender.sendMessage("§cИспользуйте: /" + label + " true, для подтверждения перезагрузки.");
        }
        
        return true;
    }
}
