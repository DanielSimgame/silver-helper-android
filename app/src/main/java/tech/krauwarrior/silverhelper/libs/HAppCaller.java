package tech.krauwarrior.silverhelper.libs;

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

    // Phone brand
    private static final String SYS_XIAOMI = "\\.*(MI|Mi|Redmi).*";
    private static final String SYS_SAMSUNG = "\\.*(SAMSUNG|SM-).*";
    private static final String SYS_HUAWEI = "\\.*(HUAWEI|HONOR).*";
    private static final String SYS_VIVO = "\\.*vivo.*";
    private static final String SYS_OPPO = "\\.*OPPO.*";
    private static final String SYS_MEIZU = "\\.*MEIZU.*";

    // System App Store scheme
    private static final String SCHEME_XIAOMI = "mimarket://details?id=";
    private static final String SCHEME_SAMSUNG = "samsungapps://ProductDetail/";
    private static final String SCHEME_HUAWEI = "huaweimarket://appdetails?appId=";
    private static final String SCHEME_HUAWEI2 = "appmarket://details?id=";
    private static final String SCHEME_VIVO = "vivomarket://details?id=";
    private static final String SCHEME_OPPO = "oppomarket://details?packagename=";
    private static final String SCHEME_MEIZU = "mstore://details?package_name=";
    private static final String SCHEME_GOOGLE = "market://details?id=";
    // System App Store package name
    private static final String MARKET_MEIZU = "com.meizu.mstore";
    private static final String MARKET_XIAOMI = "com.xiaomi.market";
    private static final String MARKET_SAMSUNG = "com.sec.android.app.samsungapps";
    private static final String MARKET_HUAWEI = "com.huawei.appmarket";
    private static final String MARKET_VIVO = "com.bbk.appstore";
    private static final String MARKET_OPPO = "com.oppo.market";
    private static final String MARKET_GOOGLE = "com.android.vending";
    // Package names
    private static final String PKG_ALIPAY = "com.eg.android.AlipayGphone";
    private static final String PKG_WECHAT = "com.tencent.mm";
    private static final String PKG_ANTI_FRAUD = "com.hicorenational.antifraud";

    public static String getPkgAlipay() {
        return PKG_ALIPAY;
    }

    public static String getPkgWechat() {
        return PKG_WECHAT;
    }

    public static String getPkgAntiFraud() {
        return PKG_ANTI_FRAUD;
    }

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
     * 设置系统应用商店包名与scheme
     * @param appPkgName Package name of target app
     * */
    private Object getMarketInfo(String appPkgName) {
        String marketScheme;
        String marketPkg;

        // Check the System with RegEx.
        String sys = HSystemUtil.getDeviceBrand();

        if (sys.matches(SYS_XIAOMI)) {
            marketScheme = SCHEME_XIAOMI + appPkgName;
            marketPkg = MARKET_XIAOMI;
        } else if (sys.matches(SYS_SAMSUNG)) {
            marketScheme = SCHEME_SAMSUNG + appPkgName;
            marketPkg = MARKET_SAMSUNG;
        } else if (sys.matches(SYS_HUAWEI)) {
            marketScheme = SCHEME_HUAWEI2 + appPkgName;
            marketPkg = MARKET_HUAWEI;
        } else if (sys.matches(SYS_VIVO)) {
            marketScheme = SCHEME_VIVO + appPkgName;
            marketPkg = MARKET_VIVO;
        } else if (sys.matches(SYS_OPPO)) {
            marketScheme = SCHEME_OPPO + appPkgName;
            marketPkg = MARKET_OPPO;
        } else if (sys.matches(SYS_MEIZU)) {
            marketScheme = SCHEME_MEIZU + appPkgName;
            marketPkg = MARKET_MEIZU;
        } else {
            marketScheme = SCHEME_GOOGLE + appPkgName;
            marketPkg = MARKET_GOOGLE;
        }

        return new Object[]{marketScheme, marketPkg};
    }

    /**
     * 启动到应用商店app详情界面
     * @param context Context
     * @param appPkg Package name of target app
     */
    public void showInAppMarket(Context context, String appPkg) {
        Object[] marketInfo = (Object[]) getMarketInfo(appPkg);

        try {
            if (TextUtils.isEmpty(appPkg)) return;

            Uri uri = Uri.parse(marketInfo[0].toString());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketInfo[1].toString())) {
                intent.setPackage(marketInfo[1].toString());
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
