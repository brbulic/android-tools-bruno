package hr.brbulic.factory.intent;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by IntelliJ IDEA.
 * User: bbulic
 * Date: 24.09.11.
 * Time: 16:19
 * To change this template use File | Settings | File Templates.
 */
public class IntentTransferClass<E> implements Parcelable {

    private final E _transferValue;

    private IntentTransferClass(E transferValue) {
        _transferValue = transferValue;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        Object[] array = new Object[]{_transferValue};
        parcel.writeArray(array);

    }
}
