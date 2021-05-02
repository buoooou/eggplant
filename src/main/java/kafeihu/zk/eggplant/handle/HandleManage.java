package kafeihu.zk.eggplant.handle;

import java.util.concurrent.atomic.AtomicInteger;

public class HandleManage {

    public static void main(String args[]){

        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.decrementAndGet());
        System.out.println(atomicInteger.incrementAndGet());
    }
}
