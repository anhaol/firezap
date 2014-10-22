package tk.firezap.aftv;

import java.io.IOException;
import java.io.InputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;

public class MainActivity extends Activity {
	
	long currentChannel = 0;
	JSONArray channelsArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().hide();
		setContentView(R.layout.activity_main);
		try {
			JSONObject obj = new JSONObject(loadJSONFromAsset());
			channelsArray = obj.getJSONArray("channels");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		boolean handled = false;
		switch (keyCode){
			case KeyEvent.KEYCODE_DPAD_UP:
				currentChannel = currentChannel + 1;
				switchChannel(currentChannel);
				handled = true;
				break;
			case KeyEvent.KEYCODE_DPAD_DOWN:
				currentChannel = currentChannel - 1;
				switchChannel(currentChannel);
                handled = true;
                break;
		}
		return handled || super.onKeyDown(keyCode, event);
	}

	public void switchChannel(long channelID) {
		//
	}
	
	public String loadJSONFromAsset() {
		String json = null;
		try {
			InputStream is = getAssets().open("firezap.json");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			json = new String(buffer, "UTF-8");
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return json;
	}
	
}
