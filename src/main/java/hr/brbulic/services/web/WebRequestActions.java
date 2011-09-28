package hr.brbulic.services.web;

import android.util.Log;
import hr.brbulic.services.web.interfaces.IWebRequestsCore;
import hr.brbulic.services.web.interfaces.IWebResultEventArgs;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: brbulic
 * Date: 9/29/11
 * Time: 12:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class WebRequestActions implements IWebRequestsCore {

    private final String TAG_GET = "WebRequestActionGet";
    private final String TAG_POST = "WebRequestActionPost";


    @Override
    public IWebResultEventArgs beginRequestGet(String url, HashMap<String, String> params, Object userData) {

        IWebResultEventArgs result = null;
        Log.d(TAG_GET, String.format("Calling for request %1$s", url));


        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(url);
            HttpResponse responseGet = client.execute(get);
            HttpEntity resEntityGet = responseGet.getEntity();
            if (resEntityGet != null) {
                final String resultString = EntityUtils.toString(resEntityGet);
                result = new WebResultMessengerWithBuilder(resultString, null, userData);
            }
        } catch (Exception e) {
            result = new WebResultMessengerWithBuilder("", e, userData);
            e.printStackTrace();
            Log.e(TAG_GET, String.format("Error recieving data! Message: %1$s", e.getMessage()));
        }

        return result;

    }

    @Override
    public IWebResultEventArgs beginRequestPost(String url, HashMap<String, String> params, Object userData) {

        return null;
                    /*
        IWebResultEventArgs result = new WebResultMessengerWithBuilder("Izlaz", new IndexOutOfBoundsException(), new Object());

        return result;*/

    }


    private class WebResultMessengerWithBuilder implements IWebResultEventArgs {


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
