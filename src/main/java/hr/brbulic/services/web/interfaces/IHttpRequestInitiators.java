package hr.brbulic.services.web.interfaces;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: brbulic
 * Date: 9/29/11
 * Time: 12:24 AM
 *
 * TODO: Write some class comments on this one :)
 */
public interface IHttpRequestInitiators {

    IWebResultEventArgs beginRequestGet(String url, Map<String, String> params, Object userData);

    IWebResultEventArgs beginRequestPost(String url, Map<String, String> params, Object userData);
}
