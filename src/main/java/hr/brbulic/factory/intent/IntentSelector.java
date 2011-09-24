package hr.brbulic.factory.intent;

import android.os.Parcelable;
import hr.brbulic.android_tools.HelloAndroidActivity;
import hr.brbulic.android_tools.OptionsActivity;

public enum IntentSelector implements IActivityEnumBehaviour {

    MAIN_SCREEN,
    OPTIONS_SCREEN,;

    public IActivityIntentData getIntentDataFrom(Parcelable userObjectData) throws ClassNotFoundException {

        final Parcelable userObj = userObjectData;
        switch (this) {
            case MAIN_SCREEN:
                return new IActivityIntentData() {
                    @Override
                    public Class<?> getActivityClass() {
                        return HelloAndroidActivity.class;
                    }

                    @Override
                    public Parcelable getActivityMessage() {
                        return userObj;
                    }
                };
            case OPTIONS_SCREEN:
                return new IActivityIntentData() {
                    @Override
                    public Class<?> getActivityClass() {
                        return OptionsActivity.class;
                    }

                    @Override
                    public Parcelable getActivityMessage() {
                        return userObj;
                    }
                };
            default:
                throw new ClassNotFoundException();

        }
    }

}
