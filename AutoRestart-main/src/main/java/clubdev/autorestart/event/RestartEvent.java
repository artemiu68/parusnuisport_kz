package clubdev.autorestart.event;

import clubdev.autorestart.managers.RestartManager.Type;
import cn.nukkit.event.Cancellable;
import cn.nukkit.event.Event;
import cn.nukkit.event.HandlerList;
import lombok.Getter;

@Getter
public class RestartEvent extends Event implements Cancellable {
    
    private static final HandlerList handlers = new HandlerList();

    private final Type type;

    public RestartEvent(Type type) {
        this.type = type;
    }

    public static HandlerList getHandlers() {
        return handlers;
    }

}
