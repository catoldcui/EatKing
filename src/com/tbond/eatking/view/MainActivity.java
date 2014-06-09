package com.tbond.eatking.view;

import greendroid.app.GDActivity;
import greendroid.app.GDApplication;
import greendroid.util.Config;
import greendroid.widget.ActionBar;
import greendroid.widget.ActionBarItem;
import greendroid.widget.LoaderActionBarItem;
import greendroid.widget.NormalActionBarItem;
import greendroid.widget.SegmentedAdapter;
import greendroid.widget.SegmentedHost;
import greendroid.widget.ActionBar.OnActionBarListener;
import greendroid.widget.ActionBarItem.Type;
import greendroid.widget.TitleActionBarItem;

import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.tbond.eatking.R;
import com.tbond.eatking.net.Api;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends GDActivity {

	private final Handler mHandler = new Handler();
    private PeopleSegmentedAdapter mAdapter;
    SegmentedHost segmentedHost;
    
    public MainActivity(){
    	super(ActionBar.Type.Normal);
    }
    
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setActionBarContentView(R.layout.activity_main);

        segmentedHost = (SegmentedHost) findViewById(R.id.segmented_host);
        
        mAdapter = new PeopleSegmentedAdapter();
//        mHandler.postDelayed(new Runnable() {
//            public void run() {
//                mAdapter.mReverse = true;
//                mAdapter.notifyDataSetChanged();
//            }
//        }, 4000);
        getActionBar().setFirstDrawable(this, R.drawable.gd_action_bar_list);
        getActionBar().getFirstButton().setOnClickListener(new ListBarListener());;
        addActionBarItem(Type.Search, R.id.action_bar_search);
//        TitleActionBarItem titleActionBarItem = new TitleActionBarItem(getString(R.string.gd_mail));
//        addActionBarItem(titleActionBarItem, R.id.action_bar_title);
//        addActionBarItem(getActionBar()
//                .newActionBarItem(NormalActionBarItem.class)
//                .setDrawable(R.drawable.ic_title_export)
//                .setContentDescription(R.string.gd_export), R.id.action_bar_export);
//        addActionBarItem(Type.Locate, R.id.action_bar_locate);
        
        segmentedHost.setAdapter(mAdapter);
    }

    private class PeopleSegmentedAdapter extends SegmentedAdapter {

        public boolean mReverse = false;

        @Override
        public View getView(int position, ViewGroup parent) {
            TextView textView = (TextView) getLayoutInflater().inflate(R.layout.text, parent, false);
            textView.setText(getSegmentTitle(position));
            
            return textView;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public String getSegmentTitle(int position) {

            switch (mReverse ? ((getCount() - 1) - position) : position) {
                case 0:
                    return getString(R.string.segment_1);
                case 1:
                    return getString(R.string.segment_2);
                case 2:
                    return getString(R.string.segment_3);
                case 3:
                    return getString(R.string.segment_4);
            }

            return null;
        }
    }
    
    @Override
    public boolean onHandleActionBarItemClick(ActionBarItem item, int position) {
    	
        int itemId = item.getItemId();
        if (itemId == R.id.action_bar_search) {
			TextView textView = (TextView) findViewById(R.id.text);
            textView.setText("action_bar_search");
        } else {
			return super.onHandleActionBarItemClick(item, position);
		}

        return true;
    }
    
    private class ListBarListener implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			TextView textView = (TextView) findViewById(R.id.text);
            textView.setText("action_bar_first_item");
		}
    };

}
