package clubdev.autorestart.managers;

import clubdev.autorestart.AutoRestart;
import clubdev.autorestart.event.RestartAnnounceEvent;
import clubdev.autorestart.event.RestartEvent;
import clubdev.autorestart.utils.TimeFormatter;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

public class RestartManager {

    private AutoRestart main;

    @Getter private final Set<Integer> announcements = new HashSet<>();
    @Getter @Setter private int seconds, schedule;

    public RestartManager(AutoRestart main) {
        this.announcements.addAll(main.getConfig().getIntegerList("announcements"));
        this.schedule = main.getConfig().getInt("schedule");
        this.seconds = schedule;

        this.main = main;
    }

    public String getFormatedSeconds() {
        return TimeFormatter.format((long) seconds * 1000);
    }

    public boolean restart() {
        return this.restart(Type.DEFAULT);
    }

    public boolean restart(Type type) {
        RestartEvent event = new RestartEvent(type);
        main.getServer().getPluginManager().callEvent(event);

        if (!event.isCancelled()) {
            main.getServer().getOnlinePlayers().forEach((u, player) -> {
                player.kick(main.getConfig().getString("messages.restart"), false);
            });
            main.getServer().shutdown();
            return true;
        }
        return false;
    }

    public void broadcast(int second) {
        RestartAnnounceEvent event = new RestartAnnounceEvent(second);
        main.getServer().getPluginManager().callEvent(event);

        if (!event.isCancelled()) {
            main.getServer().broadcastMessage(main.getConfig().getString("messages.announcement")
                .replace("{prefix}", main.getPrefix())
                .replace("{time}", this.getFormatedSeconds())
            );
        }
    }

    public enum Type {
        DEFAULT,
        NORMAL,
        COMMAND;
    }

}
