package tech.krauwarrior.silverhelper.helpers;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;

/**
 * 唤起第三方APP
 * @author Changming Mo
 * @version 1.0
 * */
public class HAppCaller {
    public boolean isApkInstalled(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        try {
            ApplicationInfo info =
                    context.getPackageManager()
                            .getApplicationInfo(
                                    packageName,
                                    PackageManager.GET_UNINSTALLED_PACKAGES
                            );
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 启动到应用商店app详情界面
     * @param context Context
     * @param appPkg Package name of target app
     * @param marketPkg Package name of target app in App Market
     */
    public void launchAppDetail(Context context, String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg)) return;
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg)) {
                intent.setPackage(marketPkg);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Call up the specified app in param appPkg
     * @param context Context
     * @param appPkg Package name of target app
     * */
    public void callAppDirectly(Context context, String appPkg) {
        //A应用直接拉起B应用
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(appPkg);
        if (intent != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    /**
     * Call up the specified app in param appPkg, with specified Activity in param callActivity
     * */
    public void callAppWithParam(Context context, String appPkg, String callActivity) {
        //拉起B应用的某个界面，我们可以传一个type值；当然如果知道你要跳转的Activity的类名也可以这样写。
        Intent intent = new Intent();
        intent.setClassName(appPkg, appPkg + "." + callActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
