package hr.brbulic.concurrency;

import hr.brbulic.concurrency.interfaces.IBackgroundWorker;

/**
 * Created by IntelliJ IDEA.
 * User: bbulic
 * Date: 21.09.11.
 * Time: 23:27
 * To change this template use File | Settings | File Templates.
 */
public class GuiBackgroundWorker {

    private final IBackgroundWorker _worker = BackgroundWorker.getInstance();


}
