package hr.brbulic.tools;

import android.widget.ImageView;

public class AsyncImageLoader {
	
	
	
	public static void DownloadImage(ImageView target, String uriSource)
	{
		
	}
	
	private static class CompositeObject
	{
		private final String _uriSource;
		private final ImageView _imageView;
			
		public CompositeObject(String _uriSource, ImageView _imageView) {
			super();
			this._uriSource = _uriSource;
			this._imageView = _imageView;
		}


		public String get_uriSource() {
			return _uriSource;
		}

		public ImageView get_imageView() {
			return _imageView;
		}
	}
	
	

}
