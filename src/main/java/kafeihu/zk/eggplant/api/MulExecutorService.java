package kafeihu.zk.eggplant.api;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public interface MulExecutorService extends MulExecutor{

    boolean isShutdown();


    List<Future> submit(List<Callable> callables);

    List<Future> submitBegin(List<Callable> callables);

    List<Future> submitEnd(List<Callable> callables);

}
