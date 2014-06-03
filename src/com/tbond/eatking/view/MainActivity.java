package com.tbond.eatking.view;

import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.tbond.eatking.R;
import com.tbond.eatking.net.Api;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
			
			Log.i("eatking", "test");
			Button button = (Button)findViewById(R.id.button1);
			button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Log.i("inClick", "true");
					// TODO Auto-generated method stub
					Api.getInstance().login("tbond", "tbond", new JsonHttpResponseHandler(){
						@Override
						public void onFailure(Throwable e, JSONObject errorResponse) {
//							dialog.dismiss();
//							LogUtil.i(TAG, "login request onFailure:" + errorResponse.toString());
//							super.onFailure(e, errorResponse);
							Log.i("eatking", "onFailure");
							Log.i("response", e.toString());
						}

						@Override
						public void onSuccess(JSONObject response) {
							Log.i("eatking", "onSuccess");
							//LogUtil.i(TAG, "login request onSuccess:" + response.toString());
							//dialog.dismiss();s
							Log.i("response", response.toString());
							
//							int result;
//							try {
//								result = response.getInt("result");
//								if(result == Api.LOGIN_SUCCESS){
//				
//									Intent it = new Intent(MainActivity.this, MainActivity.class);
//									startActivity(it);
//									MainActivity.this.finish();
//								}else{
//									Toast.makeText(MainActivity.this, getString(R.string.loginac_login_failed), Toast.LENGTH_SHORT).show();
//								}
//							} catch (JSONException e) {
//								e.printStackTrace();
//							}	
						}
					});
				}
			});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
