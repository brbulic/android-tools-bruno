package hr.brbulic.services.web;

import android.util.Log;
import hr.brbulic.services.web.interfaces.IWebRequest;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by IntelliJ IDEA.
 * User: brbulic
 * Date: 9/30/11
 * Time: 1:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class WebRequestBasicImpl implements IWebRequest {

    private final static String TAG = "WebRequestBasicImpl";

    private WebRequestBasicImpl() {
        Log.i(TAG, "Created new instance of IWebRequest implementation");
    }

    private transient static IWebRequest instance;

    public static IWebRequest getInstance() {
        if (instance == null)
            instance = new WebRequestBasicImpl();

        return instance;
    }


    @Override
    public InputStream getRequestStream(String url) {

        InputStream stream;
        try {
            URL updateURL = new URL(url);
            URLConnection conn = updateURL.openConnection();
            stream = conn.getInputStream();

        } catch (Exception e) {
            stream = null;
        }

        return stream;
    }

    @Override
    public InputStream getResultWritableStream(String url, byte[] writableData) {
        return null;
    }
}
