package space.waterbird.common.assist;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.Build;

import space.waterbird.android.log.Log;

/**
 * Android屏幕保护
 * 需求：客户是用于放在营业厅(手机相关)，通过手机或者平板来使用相关设备投射到电子屏幕上展示广告的用途，
 * 24小时不断电，只是展示用，故电量不作考虑。可以使用KeyguardLock和WakeLock加上监听屏幕亮灭的service配
 * 合使用实现Android屏幕保护。
 *
 * <!-- 解锁 对锁屏进行管理 -->
 * require <uses-permission android:name="android.permission.DISABLE_KEYGUARD"/>
 *
 */
public class KeyguardLock {
    KeyguardManager keyguardManager;
    KeyguardManager.KeyguardLock keyguardLock;

    public KeyguardLock(Context context, String tag) {
        //获取系统服务
        keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        //初始化键盘锁，可以锁定或解开键盘锁
        keyguardLock = keyguardManager.newKeyguardLock(tag);
    }

    /**
     * Call requires API level 16
     */
    public boolean isKeyguardLocked() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            Log.e("Log : ", "can not call isKeyguardLocked if SDK_INT < 16 ");
            return false;
        } else {
            return keyguardManager.isKeyguardLocked();
        }

    }

    /**
     * Call requires API level 16
     */
    public boolean isKeyguardSecure() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            Log.e("Log : ", "can not call isKeyguardSecure if SDK_INT < 16 ");
            return false;
        } else {
            return keyguardManager.isKeyguardSecure();
        }
    }

    public boolean inKeyguardRestrictedInputMode() {
        return keyguardManager.inKeyguardRestrictedInputMode();
    }

    /**
     * 解除锁屏
     */
    public void disableKeyguard() {
        keyguardLock.disableKeyguard();
    }

    /**
     * 反解除锁屏的意思是：如果在调用disableKeyguard()函数之前是锁屏的，那么就进行锁屏，否则不进行任何操作。
     */
    public void reenableKeyguard() {
        keyguardLock.reenableKeyguard();
    }

    public void release() {
        if (keyguardLock != null) {
            //禁用显示键盘锁定
            keyguardLock.reenableKeyguard();
        }
    }

    public KeyguardManager getKeyguardManager() {
        return keyguardManager;
    }

    public void setKeyguardManager(KeyguardManager keyguardManager) {
        this.keyguardManager = keyguardManager;
    }

    public KeyguardManager.KeyguardLock getKeyguardLock() {
        return keyguardLock;
    }

    public void setKeyguardLock(KeyguardManager.KeyguardLock keyguardLock) {
        this.keyguardLock = keyguardLock;
    }
}
