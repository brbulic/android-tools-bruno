package hr.brbulic.services.web.interfaces;

import hr.brbulic.services.web.HttpRequestType;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: brbulic
 * Date: 9/29/11
 * Time: 12:24 AM
 * <p/>
 * TODO: Write some class comments on this one :)
 */
public interface IHttpRequestInitiators {


    IWebResultEventArgs beginRequest(HttpRequestType type, String url, Map<String, String> params, Object userData);

    IWebResultEventArgs beginRequest(HttpRequestType type, String url, String compressedParams, Object userData);
}
