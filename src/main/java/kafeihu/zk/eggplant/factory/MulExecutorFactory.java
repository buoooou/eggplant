package kafeihu.zk.eggplant.factory;

import kafeihu.zk.eggplant.api.MulExecutorService;
import kafeihu.zk.eggplant.executor.MulThreadPoolExecutor;

public class MulExecutorFactory {

    public static MulExecutorService newDefaultInstance(){
        return new MulThreadPoolExecutor();
    }
}
