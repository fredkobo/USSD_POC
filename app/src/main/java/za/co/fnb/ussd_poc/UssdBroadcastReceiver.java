package za.co.fnb.ussd_poc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by F5094712 on 2019/02/19.
 */

public class UssdBroadcastReceiver extends BroadcastReceiver {
    Listner listner;
   public UssdBroadcastReceiver(Listner listner){
       this.listner = listner;
   }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equalsIgnoreCase("za.co.fnb.ussd_poc.action.REFRESH")){
            Bundle bundle = intent.getExtras();
            String message = bundle.getString("message");
            Log.d("XXX", message);
            this.listner.updateText(message);
        }
    }
}
