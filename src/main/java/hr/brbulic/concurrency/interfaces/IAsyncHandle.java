package hr.brbulic.concurrency.interfaces;

import java.util.logging.Handler;

/**
 * Created by IntelliJ IDEA.
 * User: bbulic
 * Date: 21.09.11.
 * Time: 21:02
 * To change this template use File | Settings | File Templates.
 */
public interface IAsyncHandle {

    IBackgroundWorker getParentWorker();

    Handler getHandler();

    Object getUserState();

}
