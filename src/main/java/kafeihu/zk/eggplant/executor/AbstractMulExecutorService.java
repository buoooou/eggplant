package kafeihu.zk.eggplant.executor;

import kafeihu.zk.eggplant.api.MulExecutorService;
import kafeihu.zk.eggplant.executor.job.MulJob;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;


public abstract class AbstractMulExecutorService implements MulExecutorService{

    protected ThreadPoolExecutor executor;


    @Override
    public boolean isShutdown() {
        return executor.isShutdown();
    }

    @Override
    public void shutdown() {
        executor.shutdown();
    }


    @Override
    public <T> List<Future<T>> submit(Collection<? extends MulJob<T>> callables) throws InterruptedException {
        return executor.invokeAll(callables);
    }

    @Override
    public void execute(Collection<? extends MulJob> runnables) {

    }
}
