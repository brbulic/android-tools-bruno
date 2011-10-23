package hr.brbulic.mvc;

import android.app.Application;
import android.content.res.Resources;
import android.os.Handler;
import android.util.Log;

/**
 * Created by IntelliJ IDEA.
 * User: Bruno
 * Date: 23.10.11.
 * Time: 17:49
 * To change this template use File | Settings | File Templates.
 */
public class MvcApp extends Application {

    private static final String TAG = "MvcApp";
    private static Handler _applicationWideHandler;
    private static Resources _applicationWideResources;

    public static Handler getApplicationHandler() {
        return _applicationWideHandler;
    }

    public static Resources getApplicationResources() {
        return _applicationWideResources;
    }

    public MvcApp() {
        Log.d(TAG, "Application created!");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _applicationWideHandler = new Handler();
        _applicationWideResources = this.getResources();
    }
}
