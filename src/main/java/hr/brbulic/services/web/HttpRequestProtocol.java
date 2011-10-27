package hr.brbulic.services.web;

/**
 * Created by IntelliJ IDEA.
 * User: Bruno
 * Date: 24.10.11.
 * Time: 12:49
 * To change this template use File | Settings | File Templates.
 */
public enum HttpRequestProtocol {

    Http("http"),
    Https("https"),
    Default("http");

    private final String _prefix;

    HttpRequestProtocol(String prefix) {
        _prefix = prefix;
    }

    public String getPrefix() {
        return _prefix;
    }

}
