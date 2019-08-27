package kafeihu.zk.eggplant.api;

import kafeihu.zk.eggplant.executor.job.MulJob;

import java.util.Collection;

public interface MulExecutor {

    void execute(Collection<? extends MulJob> runnables);

    void executeBegin(Collection<? extends MulJob> runnables);

    void executeEnd(Collection<? extends MulJob> runnables);

}
