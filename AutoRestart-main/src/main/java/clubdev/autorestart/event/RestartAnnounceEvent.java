package clubdev.autorestart.event;

import cn.nukkit.event.Cancellable;
import cn.nukkit.event.Event;
import cn.nukkit.event.HandlerList;
import lombok.Getter;

@Getter
public class RestartAnnounceEvent extends Event implements Cancellable {
    
    private static final HandlerList handlers = new HandlerList();

    private final int second;

    public RestartAnnounceEvent(int second) {
        this.second = second;
    }

    public static HandlerList getHandlers() {
        return handlers;
    }

}
