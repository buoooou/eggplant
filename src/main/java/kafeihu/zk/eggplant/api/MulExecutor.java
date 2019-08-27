package kafeihu.zk.eggplant.api;

import java.util.Collection;

public interface MulExecutor {

    void execute(Collection<? extends Runnable> runnables);

    void executeBegin(Collection<? extends Runnable> runnables);

    void executeEnd(Collection<? extends Runnable> runnables);

}
