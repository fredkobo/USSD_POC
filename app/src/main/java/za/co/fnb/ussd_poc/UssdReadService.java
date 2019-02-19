package za.co.fnb.ussd_poc;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

public class UssdReadService extends AccessibilityService {

    private static final String TAG = UssdReadService.class.getSimpleName();

    public UssdReadService() {
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.d(TAG, "onAccessibilityEvent");
        String text = event.getText().toString();
        if (event.getClassName().equals("android.app.AlertDialog")) {
            Log.d(TAG, event.getSource().toString());
            List<AccessibilityNodeInfo> list = event.getSource().findAccessibilityNodeInfosByText("Cancel");
            for (AccessibilityNodeInfo node : list) {
                node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
            //performGlobalAction(GLOBAL_ACTION_BACK);
            Log.d(TAG, text);
            Intent intent = new Intent("za.co.fnb.ussd_poc.action.REFRESH");
            intent.putExtra("message", text);
            sendBroadcast(intent);
            stopSelf();
        }
    }

    @Override
    public void onInterrupt() {

    }
    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.d(TAG, "onServiceConnected");
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.flags = AccessibilityServiceInfo.DEFAULT;
        info.packageNames = new String[]{"com.android.phone"};
        info.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        setServiceInfo(info);
    }
}
