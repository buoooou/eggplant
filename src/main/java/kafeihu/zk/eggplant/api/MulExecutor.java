package kafeihu.zk.eggplant.api;

import kafeihu.zk.eggplant.executor.job.MulJob;

import java.util.Collection;

public interface MulExecutor {

    void execute(Collection<? extends MulJob> jobs);

    void executeBegin(Collection<? extends MulJob> jobs);

    void executeEnd(Collection<? extends MulJob> jobs);

}
