package com.jxkj.newfuubo;

import java.util.Arrays;
import java.util.LinkedList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ListView mListView;
	private LinkedList<String> mListItems;
	private ArrayAdapter<String> mAdapter;

	private int mMotionY;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mListView = (ListView) findViewById(R.id.list);

		mListItems = new LinkedList<String>();
		mListItems.addAll(Arrays.asList(mStrings));
		mAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mListItems);
		mListView.setAdapter(mAdapter);
		mListView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				View view2 = findViewById(R.id.bottom_bar);
				Animation up = AnimationUtils.loadAnimation(MainActivity.this,
						R.anim.actionbar_up);
				Animation down = AnimationUtils.loadAnimation(
						MainActivity.this, R.anim.actionbar_down);
				// 记录点击时Y的坐标
				int y = (int) event.getY();
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					mMotionY = y;

					break;
				case MotionEvent.ACTION_MOVE:
					// 在这里做逻辑处理
					if ((y - mMotionY) > 0) {
						if (view2.getVisibility() == View.GONE) {
							view2.startAnimation(up);
//							view2.setVisibility(View.VISIBLE);
						}

					} else {
						if (view2.getVisibility() == View.VISIBLE) {
							view2.startAnimation(down);
//							view2.setVisibility(View.VISIBLE);
						}
					}
					mMotionY = y;
					break;

				}

				return false;
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private String[] mStrings = { "中国", "阿联酋", "阿根廷", "奥地利", "澳大利亚", "比利时",
			"巴西", "墨西哥", "中国", "阿联酋", "阿根廷", "奥地利", "澳大利亚", "比利时", "巴西", "墨西哥" };

}
