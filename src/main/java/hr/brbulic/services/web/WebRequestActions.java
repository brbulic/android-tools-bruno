package hr.brbulic.services.web;

import android.util.Log;
import hr.brbulic.asserts.AssertUtils;
import hr.brbulic.services.web.interfaces.IHttpRequestInitiators;
import hr.brbulic.services.web.interfaces.IHttpWebResponse;
import hr.brbulic.services.web.interfaces.IWebResultEventArgs;
import hr.brbulic.tools.StreamUtils;

import java.util.Locale;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: brbulic
 * Date: 9/29/11
 * Time: 12:37 AM
 *
 * TODO: Write some class comments on this one :)
 */
public class WebRequestActions implements IHttpRequestInitiators {

    private final String TAG_GET = "WebRequestActionGet";
    private final String TAG_POST = "WebRequestActionPost";


    @Override
    public IWebResultEventArgs beginRequestGet(String url, Map<String, String> params, Object userData) {

        AssertUtils.notNull(url, "Cannot start Request with no URL");

        IWebResultEventArgs iWebResultEventArgs = null;
        Log.d(TAG_GET, String.format("Calling for request %1$s", url));

        String urlSynthetic;

        if (params != null && params.size() > 0)
            urlSynthetic = String.format(Locale.US, "%1$s%2$s", url, WebHelpers.getHttpGetParamsFromHashMap(params));
        else
            urlSynthetic = url;

        final String urlFinal = urlSynthetic;

        final IHttpWebResponse response = WebRequestBasicImpl.getInstance().getRequestStream(urlFinal);

        if (response.getInputStream() != null) {
            final String result = StreamUtils.readStringFromStream(response.getInputStream());
            iWebResultEventArgs = new WebResultMessengerWithBuilder(result, null, userData);
        } else {
            Log.d(TAG_GET, "Cannot get request stream. Check weather you have added INTERNET permissions to the app!");
            iWebResultEventArgs = new WebResultMessengerWithBuilder("", new Exception("Stream is null"), userData);
        }
        return iWebResultEventArgs;
    }

    @Override
    public IWebResultEventArgs beginRequestPost(String url, Map<String, String> params, Object userData) {

        return null;
        /*
       IWebResultEventArgs result = new WebResultMessengerWithBuilder("Izlaz", new IndexOutOfBoundsException(), new Object());

       return result;*/

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
