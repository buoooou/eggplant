package kafeihu.zk.eggplant.executor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;

public abstract class MulExecutorRejectedExecutionHandler implements RejectedExecutionHandler {

    private AtomicLong m_rejectedCounter = new AtomicLong(0);

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        handleRejectedExecution(r,executor);
        //拒绝次数计数器加一
        m_rejectedCounter.incrementAndGet();

    }

    /**
     * 拒绝执行处理
     *
     * @param worker
     * @param executor
     */
    protected abstract void handleRejectedExecution(Runnable worker, ThreadPoolExecutor executor);

}
