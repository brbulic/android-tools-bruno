package hr.brbulic.services.web.interfaces;

import hr.brbulic.services.web.HttpRequestType;

import java.util.Map;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: Bruno
 * Date: 01.10.11.
 * Time: 17:21
 * <p/>
 * TODO: Write some class comments on this one :)
 */
public interface IWebService {

    public UUID initiateRequest(HttpRequestType requestType, String uri, Map<String,String> parameters, Object userData, IWebResultDelegate callback);

    public UUID initiateRequestString(String uri, String writableString, Object userData, IWebResultDelegate callback);

    public Boolean isRequestRunning(UUID request);

    public Boolean cancelRequest(UUID request);

    public void StopService();


}
