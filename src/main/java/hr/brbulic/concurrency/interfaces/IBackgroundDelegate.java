package hr.brbulic.concurrency.interfaces;

public interface IBackgroundDelegate {
	
		public <E> void backgroundRequest(E internalState);
}
