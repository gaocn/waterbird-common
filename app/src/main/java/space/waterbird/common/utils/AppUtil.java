package space.waterbird.common.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

import static space.waterbird.android.log.Log.d;

public class AppUtil {
    private static final String TAG = "AppUtil";
    /**
     * 调用系统分享
     */
    public static void shareToOtherApp(Context context, String title, String content, String dialogTitle ) {
        Intent intentItem = new Intent(Intent.ACTION_SEND);
        intentItem.setType("text/plain");
        intentItem.putExtra(Intent.EXTRA_SUBJECT, title);
        intentItem.putExtra(Intent.EXTRA_TEXT, content);
        intentItem.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intentItem, dialogTitle));
    }

    /**
     * need < uses-permission android:name =“android.permission.GET_TASKS” />
     * 判断是否前台运行
     */
    public static boolean isRunningForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskList = am.getRunningTasks(1);
        if (taskList != null && !taskList.isEmpty()) {
            ComponentName componentName = taskList.get(0).topActivity;
            if (componentName != null && componentName.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取App包 信息版本号
     * @param context
     * @return
     */
    public PackageInfo getPackageInfo(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo;
    }

    /**
     * 获取正在运行进程数量
     * PackageManager 相当于程序管理器，即控件面板中显示的安装软件，管理的是静态信息
     * ActivityManager 管理手机中运行的应用程序信息，管理的是动态信息
     * @param context
     * @return
     */
    public static int getRunningProcessCount(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        int count = am.getRunningAppProcesses().size();
        d(TAG, "getRunningProcessCount: 运行进程数量" + count);
        return count;
    }

}
