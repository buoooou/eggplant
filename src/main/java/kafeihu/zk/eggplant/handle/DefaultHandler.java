package kafeihu.zk.eggplant.handle;

public abstract class DefaultHandler implements IHandler{


    @Override
    public void preHandle() {

    }

    @Override
    public boolean handle() {
        return false;
    }

    @Override
    public void postHandle() {

    }
}
