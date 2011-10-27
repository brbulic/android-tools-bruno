package hr.brbulic.services.web;

import android.util.Log;
import hr.brbulic.services.web.interfaces.IHttpWebResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;

/**
 * Created by IntelliJ IDEA.
 * User: Bruno
 * Date: 24.10.11.
 * Time: 11:54
 * To change this template use File | Settings | File Templates.
 */
public class WebRequestBasicJavaNet extends WebRequestBasicRoot {

    private final static String TAG = "WebRequestBasicJavaNet";

    @Override
    public IHttpWebResponse getRequestStream(URI uri) {
        int response;
        InputStream stream;

        try {
            URL finalUrl = uri.toURL();
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
            Log.e(TAG, String.format("Malformed Exception for URL: %1$s", uri));
            response = 500;

        } catch (IOException e) {
            stream = null;
            Log.e(TAG, e.getLocalizedMessage());
            response = 500;
        }

        return new WebResponseImpl(stream, response);
    }




    @Override
    public IHttpWebResponse getResultWritableStream(String url, byte[] writableData) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
