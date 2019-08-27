package kafeihu.zk.eggplant.api;

import kafeihu.zk.eggplant.executor.job.MulJob;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

public interface MulExecutorService extends MulExecutor{

    boolean isShutdown();

    void shutdown();

    <T> List<Future<T>> submit(Collection<? extends MulJob<T>> jobs) throws InterruptedException;

    <T> List<Future<T>> submitBegin(Collection<? extends MulJob<T>> jobs);

    <T> List<Future<T>> submitEnd(Collection<? extends MulJob<T>> jobs);

}
