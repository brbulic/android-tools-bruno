package hr.brbulic.concurrency;

import android.os.Handler;
import hr.brbulic.concurrency.interfaces.ParametrizedRunnable;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by IntelliJ IDEA.
 * User: bbulic
 * Date: 24.09.11.
 * Time: 21:11
 * <p/>
 * TODO: Write some class comments on this one :)
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
        _privateHandler.postAtFrontOfQueue(action);
    }


    private static final ExecutorService _executorService = Executors.newCachedThreadPool(_myThreadFactory);


    public static <E> Future<E> BeginInvokeParametrized(Callable<E> myCallbale) {

        return _executorService.submit(myCallbale);
    }

    public static void BeginThreadPoolInvoke(Runnable runnableOperation) {
        _executorService.submit(runnableOperation);
    }


    public static void ParametrizedRunnable(final ParametrizedRunnable action, final Object parameter) {

        BeginInvoke(new Runnable() {
            @Override
            public void run() {
                action.runWithParam(parameter);
            }
        });


    }

}
