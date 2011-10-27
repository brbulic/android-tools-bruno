package hr.brbulic.mvc;

import android.app.Activity;
import android.os.Bundle;

/**
 * User: bbulic
 * Date: 23.10.11.
 * Time: 15:29
 * Some hr.brbulic.mvc description
 */
public abstract class MvcActivityBase extends Activity {

    private ViewModelBase _baseViewModel;

    private final int _viewResourceIndex;

    public MvcActivityBase(int viewResourceIndex) {
        super();
        _viewResourceIndex = viewResourceIndex;

    }

    public MvcActivityBase(int viewResourceIndex, ViewModelBase pageViewModel) {
        super();
        _viewResourceIndex = viewResourceIndex;
        _baseViewModel = pageViewModel;
    }

    protected ViewModelBase getPageViewModel() {
        return _baseViewModel;
    }

    protected void beginInvoke(Runnable runnable) {
        this.runOnUiThread(runnable);
    }

    @Override
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(_viewResourceIndex);

        if (null != _baseViewModel)
            setTitle(_baseViewModel.getPageTitle());

        onPageCreate(bundle);
    }

    @Override
    public final void onDestroy() {
        super.onDestroy();
        onPageDestroy();

    }

    @Override
    public final void onPause() {
        super.onPause();
        onPagePaused();
    }

    @Override
    public final void onResume() {
        super.onResume();
        onPageResume();
    }


    /**
     * Implement this method to execute code on Activity Create
     * @param bundle
     */
    protected abstract void onPageCreate(Bundle bundle);

    /**
     * Implement this method to execute code on Activity Destroy
     */
    protected abstract void onPageDestroy();

    /**
     * Implement this method to execute code on Activity Paused
     */
    protected abstract void onPagePaused();

    /**
     * Implement this method to execute code on Activity destroyed!
     */
    protected abstract void onPageResume();


}
