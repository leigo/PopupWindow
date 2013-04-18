package cn.eoe.leigo.popup.window;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import cn.eoe.leigo.popup.window.adapter.GroupAdapter;

public class MainActivity extends Activity implements OnClickListener,
		OnItemClickListener {
	private GroupAdapter groupAdapter;
	private ArrayList<String> groups;
	private TextView mHomeNameTextView;
	private PopupWindow mPopupWindow;
	private View contentView;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		mHomeNameTextView = (TextView) findViewById(R.id.tv_home_name);
		mHomeNameTextView.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		showPopwindow(v);
	}

	private void showPopwindow(View parent) {
		if (mPopupWindow == null) {
			LayoutInflater mLayoutInflater = LayoutInflater.from(this);
			contentView = mLayoutInflater.inflate(R.layout.group_list, null);

			listView = (ListView) contentView.findViewById(R.id.lv_group);

			// 加载数据
			groups = new ArrayList<String>();

			groups.add("时间排序");

			groups.add("智能排序");

			groups.add("我的微博");

			groups.add("密友圈");

			groups.add("悄悄关注");

			groups.add("周边");

			groupAdapter = new GroupAdapter(this, groups);
			listView.setAdapter(groupAdapter);

			mPopupWindow = new PopupWindow(contentView, getWindowManager()
					.getDefaultDisplay().getWidth() / 2, 250);
		}
		mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
		mPopupWindow.setFocusable(true);

		// 显示的位置为:屏幕的宽度的1/16
		int xPos = getWindowManager().getDefaultDisplay().getWidth() / 16;

		mPopupWindow.showAsDropDown(parent, xPos, 0);

		listView.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		mHomeNameTextView.setText(groups.get(position));

		if (mPopupWindow != null) {
			mPopupWindow.dismiss();
		}
	}

}
