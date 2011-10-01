package hr.brbulic.services.web;

import android.util.Log;
import hr.brbulic.services.web.interfaces.IHttpWebResponse;
import hr.brbulic.services.web.interfaces.IHttpRequestBase;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by IntelliJ IDEA.
 * User: brbulic
 * Date: 9/30/11
 * Time: 1:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class WebRequestBasicImpl implements IHttpRequestBase {

    private final static String TAG = "WebRequestBasicImpl";

    private WebRequestBasicImpl() {
        Log.i(TAG, "Created new instance of IHttpRequestBase implementation");
    }

    private transient static IHttpRequestBase instance;

    public static IHttpRequestBase getInstance() {
        if (instance == null)
            instance = new WebRequestBasicImpl();

        return instance;
    }

    @Override
    public IHttpWebResponse getRequestStream(final String url) {

        InputStream stream;
        int response;

        try {
            URL finalUrl = new URL(url);
            URLConnection conn = finalUrl.openConnection();
            if (!(conn instanceof HttpURLConnection)) {
                return new WebResponseImpl(null, 500);
            }

            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                stream = httpConn.getInputStream();
            } else
                stream = null;

        } catch (MalformedURLException e) {
            stream = null;
            Log.e(TAG, String.format("Malformed Exception for URL: %1$s", url));
            response = 500;

        } catch (IOException e) {
            stream = null;
            Log.e(TAG, e.getLocalizedMessage());
            response = 500;
        }

        return new WebResponseImpl(stream, response);
    }


    private static class WebResponseImpl implements IHttpWebResponse {
        private final InputStream stream;
        private final int status;

        private WebResponseImpl(InputStream stream, int status) {
            this.stream = stream;
            this.status = status;
        }

        @Override
        public InputStream getInputStream() {
            return stream;
        }

        public int getStatus() {
            return status;
        }
    }


    @Override
    public IHttpWebResponse getResultWritableStream(String url, byte[] writableData) {
        return null;
    }
}
