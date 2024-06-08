package clubdev.autorestart;

import cn.nukkit.plugin.PluginBase;
import clubdev.autorestart.commands.RestartCommand;
import clubdev.autorestart.commands.RestartInCommand;
import clubdev.autorestart.managers.RestartManager;
import clubdev.autorestart.tasks.RestartTask;
import lombok.Getter;

public class AutoRestart extends PluginBase {
    
    @Getter private static AutoRestart instance;
    @Getter private RestartManager restartManager;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        
        instance = this;
        this.restartManager = new RestartManager(this);

        this.register();
    }

    public void register() {
        this.getServer().getScheduler().scheduleRepeatingTask(this, new RestartTask(this), restartManager.getSchedule());
        this.getServer().getCommandMap().register("Restart", new RestartCommand(this));
        this.getServer().getCommandMap().register("Restart", new RestartInCommand(this));
    }

    public String getPrefix() {
        return this.getConfig().getString("messages.prefix");
    }
}
