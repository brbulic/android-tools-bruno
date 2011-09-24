package hr.brbulic.factory.intent;

import android.os.Parcelable;

public interface IActivityIntentData {
	
	public Class<?> getActivityClass();
	public Parcelable getActivityMessage();

}
