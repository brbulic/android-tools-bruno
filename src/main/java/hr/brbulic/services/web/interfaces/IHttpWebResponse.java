package hr.brbulic.services.web.interfaces;

import org.apache.http.HttpStatus;

import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: brbulic
 * Date: 10/1/11
 * Time: 2:49 PM
 *
 * TODO: Write some class comments on this one :)
 */
public interface IHttpWebResponse {

    public InputStream getInputStream();
    public int getStatus();

}
