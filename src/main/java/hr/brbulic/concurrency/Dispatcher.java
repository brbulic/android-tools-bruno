package hr.brbulic.concurrency;

import android.os.Handler;
import hr.brbulic.concurrency.interfaces.ParametrizedRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by IntelliJ IDEA.
 * User: bbulic
 * Date: 24.09.11.
 * Time: 21:11
 * To change this template use File | Settings | File Templates.
 */
public class Dispatcher {


    private static final int MAXIMUM_THREADS_IN_POOL = 5;

    private static final Handler _privateHandler = new Handler();

    private static final ThreadFactory _myThreadFactory = new ThreadFactory() {

        final AtomicInteger _atomicInteger = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "WorkerThread_" + _atomicInteger.getAndIncrement());
        }
    };


    public static void BeginInvoke(Runnable action) {
        _privateHandler.post(action);
    }


    private static ExecutorService _executorService;

    public static void ParametrizedRunnable(final ParametrizedRunnable action, final Object parameter) {

        BeginInvoke(new Runnable() {
            @Override
            public void run() {
                action.runWithParam(parameter);
            }
        });


    }

}
