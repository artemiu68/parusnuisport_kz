package clubdev.autorestart.tasks;

import clubdev.autorestart.AutoRestart;
import clubdev.autorestart.managers.RestartManager;
import clubdev.autorestart.managers.RestartManager.Type;
import cn.nukkit.scheduler.Task;

public class RestartTask extends Task {
    
    private AutoRestart main;

    public RestartTask(AutoRestart main) {
        this.main = main;
    }

    @Override
    public void onRun(int i) {

        RestartManager manager = main.getRestartManager();

        if (manager.getAnnouncements().contains(manager.getSeconds())) {
            manager.broadcast(manager.getSeconds());
        }

        if (manager.getSeconds() == 0) {
            manager.setSeconds(manager.getSchedule());

            manager.restart(Type.NORMAL);
            return;
        }

        main.getRestartManager().setSeconds(main.getRestartManager().getSeconds() - 1);
    }
}
