package com.zxn.accessibility;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import java.util.List;

/**
 * Created by zxn on 2019/4/29.
 */
public class PlugAccessibilityService extends AccessibilityService {

    private String TAG = PlugAccessibilityService.class.getSimpleName();


    /**
     * 打开系统的无障碍功能列表
     *
     * @param context
     */
    public static void actionAccessibilitySettings(Context context) {
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        context.startActivity(intent);
    }

    /**
     * 当启动服务的时候就会被调用,系统成功绑定该服务时被触发，也就是当你在设置中开启相应的服务，
     * 系统成功的绑定了该服务时会触发，通常我们可以在这里做一些初始化操作
     */
    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
    }

    /**
     * 通过系统监听窗口变化的回调,sendAccessibiliyEvent()不断的发送AccessibilityEvent到此处
     *
     * @param event
     */
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        Log.i(TAG, "onAccessibilityEvent: -->" + eventType);
        //根据时间回调类型进行处理.
        switch (eventType) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
//                    List<AccessibilityNodeInfo> nodeInfos = getRootInActiveWindow().findAccessibilityNodeInfosByViewId("com.zxn.accessibility:id/btn_click");
//                    AccessibilityNodeInfo nodeInfo = nodeInfos.get(0);
//                    if (null != nodeInfo) {
//                        String text = nodeInfo.getText().toString();
//                        Log.i(TAG, "onAccessibilityEvent: " + text);
//                        Toast.makeText(this, "text:" + text, Toast.LENGTH_SHORT).show();
//                    }
//                }
                break;
            //通知栏变化时
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                break;
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                //当窗口状态发生改变时.
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                //当窗口EditText文字变化时候回调时间

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    List<AccessibilityNodeInfo> nodeInfos = getRootInActiveWindow().findAccessibilityNodeInfosByViewId("com.zxn.accessibility:id/et_input");
                    AccessibilityNodeInfo nodeInfo = nodeInfos.get(0);
                    if (null != nodeInfo) {
                        String text = nodeInfo.getText().toString();
                        Log.i(TAG, "onAccessibilityEvent: " + text);
                        Toast.makeText(this, "text:" + text, Toast.LENGTH_SHORT).show();
                    }
                }
                //Log.i(TAG, "onAccessibilityEvent: ");
                break;
        }
    }

    /**
     * 中断服务时的回调.
     */
    @Override
    public void onInterrupt() {

/*        //通过文本找到对应节点集合.
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();

        List<AccessibilityNodeInfo> textList = nodeInfo.findAccessibilityNodeInfosByText("");

        //模拟点击
        nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        //模拟长安
        nodeInfo.performAction(AccessibilityNodeInfo.ACTION_LONG_CLICK);
        //模拟获取焦点
        nodeInfo.performAction(AccessibilityNodeInfo.ACTION_FOCUS);
        //模拟粘贴.
        nodeInfo.performAction(AccessibilityNodeInfo.ACTION_PASTE);

        //通过空间id找到对应的节点集合.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            List<AccessibilityNodeInfo> textIdList = nodeInfo.findAccessibilityNodeInfosByViewId("000");
        }*/


    }

    /**
     * 查找拥有特定焦点类型的控件
     *
     * @param focus
     * @return
     */
    @Override
    public AccessibilityNodeInfo findFocus(int focus) {
        return super.findFocus(focus);
    }

    /**
     * 如果配置能够获取窗口内容,则会返回当前活动窗口的根结点
     *
     * @return
     */
    @Override
    public AccessibilityNodeInfo getRootInActiveWindow() {
        return super.getRootInActiveWindow();
    }


    /**
     * 获取系统服务
     *
     * @param name
     * @return
     */
    @Override
    public Object getSystemService(String name) {
        return super.getSystemService(name);
    }

    /**
     * 如果允许服务监听按键操作，该方法是按键事件的回调，需要注意，这个过程发生了系统处理按键事件之前
     *
     * @param event
     * @return
     */
    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        return super.onKeyEvent(event);
    }

    public void setAccessibilityServiceInfo() {
        String[] packageNames = {"com.tencent.mm"};
        AccessibilityServiceInfo accessibilityServiceInfo = new AccessibilityServiceInfo();
        //相应时间的类型,(长安,点击,滑动)
        accessibilityServiceInfo.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        //反馈给用户的类型,这里是语音
        accessibilityServiceInfo.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN;

        //过滤的包名
        accessibilityServiceInfo.packageNames = packageNames;
        setServiceInfo(accessibilityServiceInfo);
    }
}
