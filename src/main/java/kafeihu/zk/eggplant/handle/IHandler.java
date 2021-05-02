package kafeihu.zk.eggplant.handle;

import kafeihu.zk.eggplant.handle.exception.HandleException;

/***
 * 处理器
 */
public interface IHandler {
    /***
     * 前置处理
     */
    void preHandle()throws HandleException;
    /***
     *  核心业务处理
     * @return
     */
    boolean handle() throws HandleException;

    /***
     * 后置处理
     */
    void postHandle()throws HandleException;
}
