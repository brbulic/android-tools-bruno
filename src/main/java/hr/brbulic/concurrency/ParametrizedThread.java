package hr.brbulic.concurrency;

import hr.brbulic.concurrency.interfaces.ParametrizedRunnable;

/**
 * Created by IntelliJ IDEA.
 * User: bbulic
 * Date: 24.09.11.
 * Time: 21:32
 * To change this template use File | Settings | File Templates.
 */
public class ParametrizedThread extends Thread {

    private Thread _thread;

    public ParametrizedThread(ParametrizedRunnable runnable, Object parameter) {
        _thread = new Thread(new MyRunnable(runnable, parameter));
    }

    @Override
    public void start() {
        _thread.start();
    }

    @Override
    public void destroy() {
        _thread.destroy();
    }

    private static class MyRunnable implements Runnable {

        final ParametrizedRunnable _myRunnable;
        final Object object;

        public MyRunnable(ParametrizedRunnable runnable, Object param) {
            _myRunnable = runnable;
            object = param;
        }

        @Override
        public void run() {
            _myRunnable.runWithParam(object);
        }
    }


}
