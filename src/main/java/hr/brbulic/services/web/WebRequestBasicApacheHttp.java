package hr.brbulic.services.web;

import android.util.Log;
import hr.brbulic.services.web.interfaces.IHttpWebResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * Created by IntelliJ IDEA.
 * User: brbulic
 * Date: 9/30/11
 * Time: 1:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class WebRequestBasicApacheHttp extends WebRequestBasicRoot {

    private final static String TAG = "WebRequestBasicApacheHttp";

    public WebRequestBasicApacheHttp() {
        super();
        Log.i(TAG, "Created new instance of IHttpRequestBase implementation");
    }

    @Override
    public IHttpWebResponse getRequestStream(URI uri) {

        InputStream stream;
        int response;

        try {

            HttpResponse httpResponse = spawnHttpResponse(uri, null);
            final HttpEntity getEntity = new BufferedHttpEntity(httpResponse.getEntity());

            stream = getEntity.getContent();
            response = httpResponse.getStatusLine().getStatusCode();

        } catch (IOException e) {
            stream = null;
            Log.e(TAG, e.getLocalizedMessage());
            response = 500;
        }

        return new WebResponseImpl(stream, response);
    }


    private HttpResponse spawnHttpResponse(URI uri, ByteArrayEntity entity) throws IOException {

        HttpClient client = new DefaultHttpClient();
        HttpRequestBase base;

        if (entity != null) {
            base = new HttpPost(uri);
            ((HttpPost) base).setEntity(entity);
        } else {
            base = new HttpGet(uri);
        }

        return client.execute(base);
    }

    @Override
    public IHttpWebResponse getResultWritableStream(String url, byte[] writableData) {
        final byte[] writableDataCache = writableData.clone();
        ByteArrayEntity entity = new ByteArrayEntity(writableDataCache);

        int response;
        InputStream resultStream;

        try {
            HttpResponse httpResponse = spawnHttpResponse(URI.create(url), entity);
            HttpEntity resultEntity = new BufferedHttpEntity(httpResponse.getEntity());

            resultStream = resultEntity.getContent();
            response = httpResponse.getStatusLine().getStatusCode();

        } catch (IOException e) {
            resultStream = null;
            Log.e(TAG, e.getLocalizedMessage());
            response = 500;
        }

        return new WebResponseImpl(resultStream, response);

    }
}
