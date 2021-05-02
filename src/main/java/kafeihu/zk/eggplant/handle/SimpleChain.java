package kafeihu.zk.eggplant.handle;

import kafeihu.zk.eggplant.handle.enumeration.ChainType;

import java.util.concurrent.CopyOnWriteArrayList;

public class SimpleChain implements IChain{

    private CopyOnWriteArrayList<IHandler> handlers = new CopyOnWriteArrayList();
    private volatile ChainType chainType = ChainType.FULLALL;

    private SimpleChain(CopyOnWriteArrayList<IHandler> handlers, ChainType chainType) {
        this.handlers = handlers;
        this.chainType = chainType;
    }

    @Override
    public void init() {

    }

    @Override
    public boolean process() {
        if(chainType==ChainType.FULLALL)
            return processALl();
        else
            return processOne();
    }

    private boolean processOne() {
        for (int i=0; i<handlers.size(); i++){
            IHandler handler = handlers.get(i);
            if(handler.handle())
                return true;
        }
        return false;
    }

    private boolean processALl() {
        for (int i=0; i<handlers.size(); i++){
            IHandler handler = handlers.get(i);
            if(!handler.handle())
                return false;
        }
        return true;
    }

    public static class Builder{

        private ChainType chainType;
        /**
         * 处理器集合
         */
        private CopyOnWriteArrayList<IHandler> handlers;


        public Builder handler(IHandler handler) {
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

        public SimpleChain build() {
            SimpleChain chain = new SimpleChain(handlers,chainType);
            return chain;
        }

    }


}
