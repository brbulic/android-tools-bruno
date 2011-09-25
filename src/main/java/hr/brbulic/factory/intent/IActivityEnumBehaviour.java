package hr.brbulic.factory.intent;

import android.os.Parcelable;

/**
 * Created by IntelliJ IDEA.
 * User: bbulic
 * Date: 24.09.11.
 * Time: 15:05
 * To change this template use File | Settings | File Templates.
 */
public interface IActivityEnumBehaviour {

    /**
     *
     * @param userObjectData
     * @return
     * @throws ClassNotFoundException
     */
    public IActivityIntentData getIntentDataFrom(Parcelable userObjectData) throws ClassNotFoundException;
}
