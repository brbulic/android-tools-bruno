package hr.brbulic.android_tools;

import hr.brbulic.concurrency.BackgroundWorker;
import hr.brbulic.concurrency.interfaces.IBackgroundDelegate;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class HelloAndroidActivity extends Activity implements
		IBackgroundDelegate {

	private static String TAG = "android-tools";

	/**
	 * Called when the activity is first created.
	 * 
	 * @param savedInstanceState
	 *            If the activity is being re-initialized after previously being
	 *            shut down then this Bundle contains the data it most recently
	 *            supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it
	 *            is null.</b>
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
		setContentView(R.layout.main);

		BackgroundWorker.getInstance().start();
		BackgroundWorker.getInstance().EnqueueSimple(this);
		BackgroundWorker.getInstance().EnqueueSimple(new MyHandler());
	}

	private class MyHandler implements IBackgroundDelegate {

		@Override
		public void backgroundRequest(Object internalState) {
			int count = 5;
			while (count < 10) {
				String string = String.format("Ovo je prekršilo sve... %1$d",
						count);

				Log.i(TAG, string);
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
				}

				count++;
			}
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		BackgroundWorker.getInstance().stop();
	}

	@Override
	public void backgroundRequest(Object internalState) {
		int count = 0;

		while (count < 10) {
			System.out.println("Just a message with number " + count);
			count++;
		}

	}

}
