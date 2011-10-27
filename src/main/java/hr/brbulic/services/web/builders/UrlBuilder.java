package hr.brbulic.services.web.builders;

import android.util.AndroidRuntimeException;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Bruno
 * Date: 29.09.11.
 * Time: 12:27
 * <p/>
 * TODO: Write some class comments on this one :)
 */
public final class UrlBuilder {

    private static final String TAG = "UrlBuilder";
    private final Map<String, String> urlMaps = new HashMap<String, String>();

    private UrlBuilder() {
        _hasBeenBuilt = Boolean.FALSE;
        Log.i(TAG, "Created default instance!");
    }

    private Boolean _hasBeenBuilt;

    /**
     * Add a parameter to the string builder.
     *
     * @param key   HTTP GET parameter name
     * @param value HTTP GET parameter value
     * @return UrlBuilder to continue appending :)
     */
    public UrlBuilder addParameter(String key, String value) {

        if (_hasBeenBuilt)
            throw new AndroidRuntimeException("Parameters have already been built!");

        urlMaps.put(key, value);
        return this;
    }

    public Map<String, String> build() {
        _hasBeenBuilt = Boolean.TRUE;
        return urlMaps;
    }

    /**
     * Build Parameter Map to be used with URL builders
     *
     * @return This builder
     */
    public static UrlBuilder createParameterFactory() {
        return new UrlBuilder();
    }
}
