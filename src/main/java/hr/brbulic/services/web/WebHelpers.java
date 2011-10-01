package hr.brbulic.services.web;

import hr.brbulic.asserts.AssertUtils;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Bruno
 * Date: 29.09.11.
 * Time: 11:11
 *
 * Standard WebHelpers class with static methods used to do basic HTTP request stuff. You'll see.
 * */
public class WebHelpers {

    private static final String HTTP_GET_PARAM_START = "?";
    private static final String HTTP_GET_PARAM_SEPARATOR = "&";
    private static final String HTTP_GET_PARAM_CONNECTOR = "=";
    private static final String HTTP_GET_PARAM_TIMESTAMP = "ts";


    /**
     * This method will parse a map of parameters in convert it to HTTP GET compatible string. Uses timestamp by default
     *
     * @param parameters HTTP GET/POST parameters
     * @return Formatted parameters
     */
    public static String getHttpGetParamsFromHashMap(Map<String, String> parameters) {

        AssertUtils.notNull(parameters, "Cannot create parameters from empty parameters class");
        AssertUtils.IsValueValid(parameters.size() != 0, "Cannot create parameters from no parameters");


        return getHttpGetParamsFromHashMap(parameters, true);
    }


    private static final StringBuilder mLocalBuilder = new StringBuilder();

    /**
     * This method will parse a map of parameters in convert it to HTTP GET compatible string.
     *
     * @param parameters   HTTP GET/POST parameters
     * @param useTimeStamp To avoid browser/request caching, you can apply a timestamp on web call. Set to <b>true</b>
     *                     to add "ts=NUMBER" to the end of the request
     * @return Formatted string
     */
    public static synchronized String getHttpGetParamsFromHashMap(Map<String, String> parameters, Boolean useTimeStamp) {

        AssertUtils.notNull(parameters, "Cannot create parameters from empty parameters class");
        AssertUtils.IsValueValid(parameters.size() != 0, "Cannot create parameters from no parameters");


        mLocalBuilder.delete(0, mLocalBuilder.length());

        mLocalBuilder.append(HTTP_GET_PARAM_START);

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            mLocalBuilder.append(entry.getKey()).append(HTTP_GET_PARAM_CONNECTOR).append(entry.getValue()).append(HTTP_GET_PARAM_SEPARATOR);
        }

        if (useTimeStamp)
            mLocalBuilder.append(HTTP_GET_PARAM_TIMESTAMP).append(HTTP_GET_PARAM_CONNECTOR).append(System.currentTimeMillis());


        return mLocalBuilder.toString();

    }

}
