package hr.brbulic.services.web;

import android.util.Log;
import hr.brbulic.services.web.interfaces.IHttpRequestBase;
import hr.brbulic.services.web.interfaces.IHttpWebResponse;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by IntelliJ IDEA.
 * User: Bruno
 * Date: 24.10.11.
 * Time: 11:35
 * To change this template use File | Settings | File Templates.
 */
public abstract class WebRequestBasicRoot implements IHttpRequestBase {

    private final static String Tag = "WebRequestBasicRoot";

    @Override
    public IHttpWebResponse getRequestStream(String url) {

        IHttpWebResponse response;

        try {
            final URI uri = new URI(url);
            response = getRequestStream(uri);
        } catch (URISyntaxException e) {
            Log.d(Tag, e.getMessage());
            response = null;
        }

        return response;
    }

    @Override
    public abstract IHttpWebResponse getRequestStream(URI uri);

    @Override
    public abstract IHttpWebResponse getResultWritableStream(String url, byte[] writableData);

    protected static class WebResponseImpl implements IHttpWebResponse {
        private final InputStream _stream;
        private final int _status;

        protected WebResponseImpl(InputStream stream, int status) {
            _stream = stream;
            _status = status;
        }

        @Override
        public InputStream getInputStream() {
            return _stream;
        }

        public int getStatus() {
            return _status;
        }
    }
}
