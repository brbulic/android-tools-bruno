package hr.brbulic.cache;

import android.os.Parcelable;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Bruno
 * Date: 23.10.11.
 * Time: 18:14
 * To change this template use File | Settings | File Templates.
 */
public class CacheService {

    private final ICacheStorage _backingStorage;

    private final Map<String, WeakReference<Parcelable>> _backingMap = new ConcurrentHashMap<String, WeakReference<Parcelable>>();

    public CacheService(ICacheStorage backingStorage) {
        _backingStorage = backingStorage;
    }

    public void SaveToCache(String key, Parcelable serializableElement) {

        if (_backingMap.containsKey(key)) {
            _backingMap.remove(key);
        }

        _backingMap.put(key, new WeakReference<Parcelable>(serializableElement));
        _backingStorage.writeResource(key, serializableElement);

    }

    public <TElement extends Parcelable> TElement retrieveFromCache(String key) {

        TElement result;

        if (_backingMap.containsKey(key)) {
            final Parcelable element = _backingMap.get(key).get();

            if (null == element) {
                result = (TElement) _backingStorage.readResource(key);
            } else {
                result = (TElement) element;
            }
        } else {
            Object element = _backingStorage.readResource(key);
            if (element instanceof Parcelable) {
                result = (TElement) element;
                _backingMap.put(key, new WeakReference<Parcelable>(result));
            } else {
                result = null;
            }
        }

        return result;

    }


}
