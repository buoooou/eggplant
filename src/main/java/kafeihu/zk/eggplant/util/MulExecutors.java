package kafeihu.zk.eggplant.util;

import kafeihu.zk.eggplant.api.MulExecutorService;
import kafeihu.zk.eggplant.factory.MulExecutorFactory;

public class MulExecutors {

    public static MulExecutorService newMulExecutorservice(){
        return MulExecutorFactory.newDefaultInstance();
    }
}
