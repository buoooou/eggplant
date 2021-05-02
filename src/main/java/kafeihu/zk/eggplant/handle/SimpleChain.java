package kafeihu.zk.eggplant.handle;

import kafeihu.zk.eggplant.handle.enumeration.ChainType;
import kafeihu.zk.eggplant.handle.exception.HandleException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleChain implements IChain{

    private CopyOnWriteArrayList<IHandler> handlers;
    private volatile ChainType chainType;
    private AtomicInteger retry_num;

    private List<IHandler> result = new ArrayList<>();

    public SimpleChain(CopyOnWriteArrayList<IHandler> handlers, ChainType chainType, AtomicInteger retry_num) {
        this.handlers = handlers;
        this.chainType = chainType;
        this.retry_num = retry_num;
    }

    @Override
    public void init() {

    }

    @Override
    public boolean process() {
        do{
            if(chainType==ChainType.FULLALL){
                return processALl();
            }else {
                return processOne();
            }
        }while (retry_num.decrementAndGet()>=0);
    }

    private boolean processOne() throws HandleException {
        for (int i=0; i<handlers.size(); i++){
            IHandler handler = handlers.get(i);
            handler.preHandle();
            if(handler.handle()){
                handler.postHandle();
                return true;
            }
        }
        return false;
    }

    private boolean processALl() throws HandleException{
        for (int i=0; i<handlers.size(); i++){
            IHandler handler = handlers.get(i);
            if(result.contains(handler)){
                continue;
            }
            handler.preHandle();
            if(handler.handle()){
                handler.postHandle();
                result.add(handler);
            }
        }
        if (result.size()<handlers.size())
            return false;
        else return true;

    }

    public static class Builder{

        private ChainType chainType = ChainType.FULLALL;
        /**
         * 处理器集合
         */
        private CopyOnWriteArrayList<IHandler> handlers;

        private AtomicInteger retry_num = new AtomicInteger(1);

        public Builder handler(IHandler handler) {
            if(handlers == null){
                handlers = new CopyOnWriteArrayList<>();
            }
            this.handlers.add(handler);
            return this;
        }

        public Builder chainType(ChainType chainType) {
            if (chainType != null) {
                this.chainType = chainType;
            }
            return this;
        }

        public Builder handlers(IHandler ... handlers) {
            if (handlers == null) {
                return this;
            }
            for (IHandler handler : handlers) {
                handler(handler);
            }
            return this;
        }

        public Builder retry(int num) {
            if (retry_num != null) {
                retry_num.set(num);
            }
            return this;
        }

        public SimpleChain build() {
            SimpleChain chain = new SimpleChain(handlers,chainType,retry_num);

            return chain;
        }

    }


}
