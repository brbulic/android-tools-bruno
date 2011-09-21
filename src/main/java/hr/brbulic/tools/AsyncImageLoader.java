package hr.brbulic.tools;

import android.widget.ImageView;
import hr.brbulic.imageloader.ILoadableComponent;
import hr.brbulic.imageloader.ILoadableImageDetails;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AsyncImageLoader {


    private final Queue<ILoadableComponent> loadableComponents = new ConcurrentLinkedQueue<ILoadableComponent>();


    private final Object _lockObject = new Object();

    public void DownloadImage(ILoadableComponent component) {
        synchronized (_lockObject) {
            loadableComponents.add(component);
            _lockObject.notify();
        }


    }


    private static class CompositeObject {
        private final String _uriSource;
        private final ImageView _imageView;

        public CompositeObject(ILoadableImageDetails details) {
            super();
            this._uriSource = details.getTargetUrlString();
            this._imageView = details.getTargetView();
        }


        public String get_uriSource() {
            return _uriSource;
        }

        public ImageView get_imageView() {
            return _imageView;
        }
    }


}
