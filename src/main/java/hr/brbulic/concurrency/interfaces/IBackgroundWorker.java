package hr.brbulic.concurrency.interfaces;

/**
 * This owns the place
 * 
 * @author bbulic
 * 
 */
public interface IBackgroundWorker {

	/**
	 * Enqueues a delegate action with a userstate
	 * 
	 * @param delegate
	 * @param userState
	 */
	public <E> void EnqueueSimple(IBackgroundDelegate delegate, E userState);
	/**
	 * Just enqueues an action
	 * 
	 * @param delegate
	 */
	public void EnqueueSimple(IBackgroundDelegate delegate);
	
	
	/**
	 * Enqueue a Runnable interface implementation to run on this thread.
	 */
	public void EnqueueRunnable(Runnable runnable);

	/**
	 * Starts a worker (defaults?)
	 */
	public void start();

	/**
	 * Stops a worker (if running)
	 */
	public void stop();

	/**
	 * Check running
	 * 
	 * @return
	 */
	public boolean isRunning();
}
