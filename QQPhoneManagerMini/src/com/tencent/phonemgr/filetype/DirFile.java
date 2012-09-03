package com.tencent.phonemgr.filetype;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import com.tencent.phonemgr.R;

public class DirFile implements FileItem {
	
	private File file;
	private OnLoadListener listener;
	private FileLoaderTask loaderTask;
	
	public DirFile(File file) {
		this.file = file;
	}
	
	@Override
	public String getName() {
		return file.getName();
	}

	@Override
	public Drawable getLogo(Context context) {
		return context.getResources().getDrawable(R.drawable.ic_directory);
	}

	@Override
	public void open(Activity context) {
		if(file != null && file.isDirectory()) {
			loaderTask = new FileLoaderTask();
			loaderTask.execute(file);
		}
	}

	private class FileLoaderTask extends AsyncTask<File, Void, List<FileItem>> {

		private List<FileItem> fileItems = new ArrayList<FileItem>();
		
		@Override
		protected void onPreExecute() {
			if(listener != null) {
				listener.onStartLoading();
			}
		}
		
		@Override
		protected List<FileItem> doInBackground(File... params) {
			File[] files = params[0].listFiles();
			if(files != null) {
				//TODO need to simplify the code here
				for(File f : files) {
					if(f.isDirectory()) {
						fileItems.add(new DirFile(f));
					} else if(f.getName().toLowerCase().endsWith(ApkFile.APK_FILE)) {
						fileItems.add(new ApkFile(f));
					} else if(f.getName().toLowerCase().endsWith(ImageFile.JPG_FILE) ||
							f.getName().toLowerCase().endsWith(ImageFile.PNG_FILE)) {
						fileItems.add(new ImageFile(f));
					} else if(f.getName().toLowerCase().endsWith(AudioFile.MP3_FILE) ||
							f.getName().toLowerCase().endsWith(AudioFile.M4A_FILE)) {
						fileItems.add(new AudioFile(f));
					} else if(f.getName().toLowerCase().endsWith(VideoFile.MP4_FILE) ||
							f.getName().toLowerCase().endsWith(VideoFile.FLV_FILE) ||
							f.getName().toLowerCase().endsWith(VideoFile.THREEGP_FILE)) {
						fileItems.add(new VideoFile(f));
					} else {
						fileItems.add(new GeneralFile(f));
					}
				}
			}
			//TODO sort by strings
			return fileItems;
		}

		@Override
		protected void onPostExecute(List<FileItem> result) {
			if(listener != null) {
				listener.onFinishLoading(result);
			}
		}
		
	}

	@Override
	public void close() {
		if(loaderTask != null) {
			loaderTask.cancel(true);
		}
	}

	@Override
	public void setOnLoadListener(OnLoadListener listener) {
		this.listener= listener; 
	}

	@Override
	public File getParentDir() {
		return file.getParentFile();
	}
	
}
