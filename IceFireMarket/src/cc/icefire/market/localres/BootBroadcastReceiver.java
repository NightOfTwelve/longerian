package cc.icefire.market.localres;

import cc.icefire.market.util.ILog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
//			Intent mainActivityIntent = new Intent(context, MainActivity.class); // 要启动的Activity
//			mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			context.startActivity(mainActivityIntent);
			ILog.d("BootBroadcastReceiver", "boot completeeeeeeee");
		}

	}

}
