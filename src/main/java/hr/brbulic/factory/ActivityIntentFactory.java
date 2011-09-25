package hr.brbulic.factory;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import hr.brbulic.factory.intent.IActivityEnumBehaviour;
import hr.brbulic.factory.intent.IActivityIntentData;

public class ActivityIntentFactory {

    public final static String OBJECT_TRANSFER_STRING = "IntentTransferObjectKey";

    public static void LoadActivity(Context context, IActivityEnumBehaviour activityEnum, Parcelable userObject) throws ClassNotFoundException {
        final IActivityIntentData data = activityEnum.getIntentDataFrom(userObject);
        Intent intent = new Intent(context, data.getActivityClass());
        intent.putExtra(OBJECT_TRANSFER_STRING, data.getActivityMessage());
        context.startActivity(intent);
    }

}
