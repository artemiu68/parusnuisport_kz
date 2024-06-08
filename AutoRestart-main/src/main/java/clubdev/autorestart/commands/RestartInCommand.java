package clubdev.autorestart.commands;

import clubdev.autorestart.AutoRestart;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

public class RestartInCommand extends Command {
    
    private AutoRestart main;

    public RestartInCommand(AutoRestart main) {
        super("restartin", "Проверить когда будет рестарт");
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

        sender.sendMessage(main.getConfig().getString("messages.restart-in")
            .replace("{prefix}", main.getPrefix())
            .replace("{time}", main.getRestartManager().getFormatedSeconds())
        );        
        return true;
    }
}
