package hr.brbulic.concurrency;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import android.util.Log;

import hr.brbulic.concurrency.interfaces.IBackgroundDelegate;
import hr.brbulic.concurrency.interfaces.IBackgroundWorker;

public class BackgroundWorker implements IBackgroundWorker, Runnable {

	private final Queue<BackgroundWorkerInternalComposite> _actionQueue = new ConcurrentLinkedQueue<BackgroundWorkerInternalComposite>();

	private static String TAG = "BackgroundWorker";

	private final Object _queueLockObject = new Object();

	private static transient IBackgroundWorker _instance;

	public static IBackgroundWorker getInstance() {
		if (_instance == null) {
			_instance = new BackgroundWorker();
			_instance.start();
		}

		return _instance;
	}

	private boolean _isRunning;

	private final Thread _workerThread;

	private BackgroundWorker() {
		_workerThread = new Thread(this);
	}

	@Override
	public void EnqueueSimple(IBackgroundDelegate delegate, Object userState) {
		// TODO Auto-generated method stub

		synchronized (_queueLockObject) {
			final BackgroundWorkerInternalComposite currentTask = new BackgroundWorkerInternalComposite(
					delegate, userState);
			_actionQueue.add(currentTask);

			_queueLockObject.notify();
		}
	}

	@Override
	public void EnqueueSimple(IBackgroundDelegate delegate) {
		EnqueueSimple(delegate, null);
	}

	private static class BackgroundWorkerInternalComposite {
		private final IBackgroundDelegate _delegateOperation;
		private final Object _userState;

		public BackgroundWorkerInternalComposite(IBackgroundDelegate delegate,
				Object userState) {
			_delegateOperation = delegate;
			_userState = userState;
		}

		public IBackgroundDelegate getDelegate() {
			return _delegateOperation;
		}

		public Object getUserState() {
			return _userState;
		}

	}

	@Override
	public void start() {

		if (_isRunning)
			return;

		_isRunning = true;
		_workerThread.start();

	}

	@Override
	public void stop() {
		_isRunning = true;

		synchronized (_queueLockObject) {
			_queueLockObject.notify();
		}

	}

	@Override
	public boolean isRunning() {
		return _isRunning;
	}

	@Override
	public void run() {

		final Queue<BackgroundWorkerInternalComposite> localQueue = new ConcurrentLinkedQueue<BackgroundWorker.BackgroundWorkerInternalComposite>();

		while (_isRunning) {
			synchronized (_queueLockObject) {

				if (_actionQueue.size() == 0) {
					try {
						_queueLockObject.wait();
					} catch (InterruptedException e) {
					}
				}

				if (!_isRunning)
					break;

				while (_actionQueue.size() > 0) {
					localQueue.add(_actionQueue.remove());
				}
			}

			while (localQueue.size() > 0) {
				final BackgroundWorkerInternalComposite actionData = localQueue
						.remove();

				// OK so this is the real operation... for a queue :)
				actionData.getDelegate().backgroundRequest(
						actionData.getUserState());
				Log.i(TAG, String.format(
						"Background operation with hash %d has completed!",
						actionData.hashCode()));
				Thread.yield();
			}
		}

		Log.i(TAG, "Background worker operation complete!");

	}

	@Override
	public void EnqueueRunnable(Runnable runnable) {
		final Runnable dumb = runnable;

		synchronized (_queueLockObject) {
			final BackgroundWorkerInternalComposite task = new BackgroundWorkerInternalComposite(
					new IBackgroundDelegate() {

						@Override
						public void backgroundRequest(Object internalState) {
							dumb.run();
						}
					}, null);

			_actionQueue.add(task);
			_queueLockObject.notify();
		}
	}

}
