package cc.icefire.market.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cc.icefire.market.R;

public class TitleBar extends RelativeLayout {

	private ImageView logo;
	private TextView title;
	private Button search;
	
	public TitleBar(Context context) {
		super(context);
		init(context);
	}
	
	public TitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	public TitleBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.widget_title_bar, this);
		setBackgroundResource(R.drawable.title_bar);
		logo = (ImageView) findViewById(R.id.logo);
		title = (TextView) findViewById(R.id.title);
		search = (Button) findViewById(R.id.search);
	}
}