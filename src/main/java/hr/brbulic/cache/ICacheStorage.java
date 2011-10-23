package hr.brbulic.cache;

import android.os.Parcelable;

/**
 * Created by IntelliJ IDEA.
 * User: Bruno
 * Date: 23.10.11.
 * Time: 18:14
 * To change this template use File | Settings | File Templates.
 */
public interface ICacheStorage {

    public void writeResource(String resourceKey, Parcelable resourceValue);

    public Parcelable readResource(String resourceKey);

}
