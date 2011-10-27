package hr.brbulic.services.web;

import android.util.Log;
import hr.brbulic.asserts.AssertUtils;
import hr.brbulic.services.web.interfaces.IHttpRequestInitiators;
import hr.brbulic.services.web.interfaces.IHttpWebResponse;
import hr.brbulic.services.web.interfaces.IWebResultEventArgs;
import hr.brbulic.tools.StreamUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Locale;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: brbulic
 * Date: 9/29/11
 * Time: 12:37 AM
 * <p/>
 * TODO: Write some class comments on this one :)
 */
public class WebRequestActions implements IHttpRequestInitiators {

    private final String TAG_GET = "WebRequestActionGet";
    private final String TAG_POST = "WebRequestActionPost";


    @Override
    public IWebResultEventArgs beginRequest(HttpRequestType type, String url, Map<String, String> params, Object userData) {

        AssertUtils.notNull(url, "Cannot begin a request without URL");

        String convertedParams;

        if (params != null && params.size() > 0) {
            final String getParams = WebHelpers.getHttpGetParamsFromMap(params);
            convertedParams = getParams;
        } else
            convertedParams = WebHelpers.getCurrentTimestamp();

        return beginRequest(type, url, convertedParams, userData);
    }

    @Override
    public IWebResultEventArgs beginRequest(HttpRequestType type, String url, String compressedParams, Object userData) {

        AssertUtils.notNull(url, "Cannot start Request with no URL");

        IWebResultEventArgs iWebResultEventArgs = null;

        final String urlFinal = String.format(Locale.US, "$s%1$s%2", url, compressedParams);
        IHttpWebResponse response;
        Log.d(TAG_GET, String.format("Calling for request %1$s", urlFinal));

        WebRequestBasicRoot root = new WebRequestBasicApacheHttp();


        switch (type) {
            case POST:
                try {
                    final byte[] byteParams = compressedParams.getBytes("UTF-8");
                    response = root.getResultWritableStream(url, byteParams);
                } catch (UnsupportedEncodingException e) {
                    response = null;
                }
                break;
            case GET:
            case DEFAULT:
            default:
                response = root.getRequestStream(URI.create(urlFinal));
                break;
        }

        if (null != response && response.getInputStream() != null) {
            final String result = StreamUtils.readStringFromStream(response.getInputStream());
            iWebResultEventArgs = new WebResultMessengerWithBuilder(result, null, userData);
        } else {
            Log.d(TAG_GET, "Cannot get request stream. Check weather you have added INTERNET permissions to the app!");
            iWebResultEventArgs = new WebResultMessengerWithBuilder("", new Exception("Stream is null"), userData);
        }
        return iWebResultEventArgs;
    }


    public static class WebResultMessengerWithBuilder implements IWebResultEventArgs {


        private final String resultString;
        private final Exception resultException;
        private final Object userObject;

        public WebResultMessengerWithBuilder(String resultString, Exception resultException, Object userObject) {
            this.resultString = resultString;
            this.resultException = resultException;
            this.userObject = userObject;
        }

        @Override
        public String getResultString() {
            return resultString;
        }

        @Override
        public Exception getError() {
            return resultException;
        }

        @Override
        public Object getUserState() {
            return userObject;
        }
    }


}
