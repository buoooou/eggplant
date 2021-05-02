package kafeihu.zk.eggplant.handle;

/***
 * 链式处理
 *
 */
public interface IChain<T> {

    void init();

  //  void add(T handler);

    boolean process();

}
