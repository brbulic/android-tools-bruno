package hr.brbulic.services.web.interfaces;

import org.apache.http.HttpStatus;

import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: brbulic
 * Date: 10/1/11
 * Time: 2:49 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IHttpWebResponse {

    public InputStream getInputStream();
    public int getStatus();

}
