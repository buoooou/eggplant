package kafeihu.zk.eggplant.api;

import java.util.List;

public interface MulExecutor {

    void execute(List<Runnable> runnables);

    void executeBegin(List<Runnable> runnables);

    void executeEnd(List<Runnable> runnables);

}
