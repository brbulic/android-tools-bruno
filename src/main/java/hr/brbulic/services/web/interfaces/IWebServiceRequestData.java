package hr.brbulic.services.web.interfaces;

import hr.brbulic.services.web.HttpRequestType;

import java.net.URI;
import java.util.Map;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: Bruno
 * Date: 01.10.11.
 * Time: 17:19
 * <p/>
 * TODO: Write some class comments on this one :)
 */
public interface IWebServiceRequestData {

    public String getUri();

    public Map<String,String> getParameters();

    public Object getUserData();

    public IWebResultDelegate getResultDelegate();

    public UUID getRequestUuid();

    public HttpRequestType getRequestType();


}
