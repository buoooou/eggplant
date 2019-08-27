package kafeihu.zk.eggplant.executor;

import kafeihu.zk.eggplant.executor.job.MulJob;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class MulThreadPoolExecutor extends AbstractMulExecutorService{


    public MulThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
                Executors.defaultThreadFactory(), new AbortHandler());
    }

    public MulThreadPoolExecutor(int corePoolSize,
                                 int maximumPoolSize,
                                 long keepAliveTime,
                                 TimeUnit unit,
                                 BlockingQueue<Runnable> workQueue,
                                 ThreadFactory threadFactory) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
                threadFactory, new AbortHandler());
    }


    public MulThreadPoolExecutor(int corePoolSize,
                                 int maximumPoolSize,
                                 long keepAliveTime,
                                 TimeUnit unit,
                                 BlockingQueue<Runnable> workQueue,
                                 RejectedExecutionHandler handler) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
                Executors.defaultThreadFactory(), handler);
    }

    public MulThreadPoolExecutor(int corePoolSize,
                                 int maximumPoolSize,
                                 long keepAliveTime,
                                 TimeUnit unit,
                                 BlockingQueue<Runnable> workQueue,
                                 ThreadFactory threadFactory,
                                 RejectedExecutionHandler handler) {
        this.executor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,
                keepAliveTime,unit,workQueue,threadFactory,handler);
    }

    @Override
    public <T> List<Future<T>> submitBegin(Collection<? extends MulJob<T>> jobs) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(jobs.size());
        List<Future<T>> futures = new ArrayList<>();
        for(MulJob<T> job: jobs){
            Callable<T> callable = new Callable<T>() {
                @Override
                public T call() throws Exception {
                    cyclicBarrier.await();
                    return job.execute();
                }
            };
            Future<T> future = executor.submit(callable);
            futures.add(future);
        }
        return futures;
    }

    @Override
    public <T> List<Future<T>> submitEnd(Collection<? extends MulJob<T>> jobs) {
        CountDownLatch latch = new CountDownLatch(jobs.size());
        List<Future<T>> futures = new ArrayList<>();
        for(MulJob<T> job: jobs){
            Callable<T> callable = new Callable<T>() {
                @Override
                public T call() throws Exception {
                    latch.countDown();
                    return job.execute();
                }
            };
            Future<T> future = executor.submit(callable);
            futures.add(future);
        }
        return futures;
    }

    @Override
    public void executeBegin(Collection<? extends MulJob> runnables) {

    }

    @Override
    public void executeEnd(Collection<? extends MulJob> runnables) {

    }


    /**
     * A handler for rejected tasks that throws a
     * {@code RejectedExecutionException}.
     */
    public static class AbortHandler extends MulRejectedExecutionHandler {
        /**
         * Creates an {@code AbortPolicy}.
         */
        public AbortHandler() { }

        /**
         * Always throws RejectedExecutionException.
         *
         * @param worker the runnable task requested to be executed
         * @param executor the executor attempting to execute this task
         * @throws RejectedExecutionException always
         */
        @Override
        protected void handleRejectedExecution(Runnable worker, ThreadPoolExecutor executor) {
            throw new RejectedExecutionException("Task " + worker.toString() +
                    " rejected from " +
                    executor.toString());
        }
    }


}
