# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontoptimize
-dontpreverify
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable

-keepattributes *Annotation*, InnerClasses, Signature, SourceFile, LineNumberTable, Exceptions

-dump class_files.txt
-printseeds seeds.txt
-printusage unused.txt
-printmapping mapping.txt

# 对R文件下的所有类及其方法，都不能被混淆
-keepclassmembers class **.R$* {
   *;
}

-keep public class astrology.free.horoscope.palm.zodiac.astrology.predict.free.R$*{
public static final int *;
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 保留Activity中的方法参数是view的方法，
# 从而我们在layout里面编写onClick就不会影响
-keepclassmembers class * extends android.app.Activity {
    public void * (android.view.View);
}

# 枚举类不能被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 保留Parcelable序列化的类不能被混淆
-keep class * implements android.os.Parcelable{
    public static final android.os.Parcelable$Creator *;
}

# 保留Serializable 序列化的类不被混淆
-keepclassmembers class * implements java.io.Serializable {
   static final long serialVersionUID;
   private static final java.io.ObjectStreamField[] serialPersistentFields;
   !static !transient <fields>;
   private void writeObject(java.io.ObjectOutputStream);
   private void readObject(java.io.ObjectInputStream);
   java.lang.Object writeReplace();
   java.lang.Object readResolve();
}

#native
-keepclasseswithmembernames class * {
    native <methods>;
}

#support
-dontwarn android.support.**
-keep class android.support.v4.** { *; }
-keep class android.support.v7.** { *; }

-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }

#bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

#Umeng
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

#appsflyer
-dontwarn com.appsflyer.**
-keep class com.appsflyer.** { *; }

#EventBus
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(Java.lang.Throwable);
}

#aidl
-dontwarn android.content.pm.**
-keep class android.content.pm.** {
   *;
}

#java8
 -dontwarn java.lang.invoke.*
 -dontwarn **$$Lambda$*

#facebook
-dontwarn com.facebook.**
-keep class com.facebook.** {
   *;
}

#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#retrofit
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepattributes Signature
-keepattributes Exceptions

-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}

-dontwarn tv.danmaku.**
-keep class tv.danmaku.**{*;}

#rxjava
-dontwarn sun.misc.**

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn retrofit2.Platform$Java8

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

-keep class com.google.common.base.Preconditions { *; }

# For Guava:
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
-dontwarn sun.misc.Unsafe

-keep class com.umeng.commonsdk.** {*;}

#广告位
-dontwarn com.pingstart.adsdk.adapter.**
-dontwarn com.pingstart.mobileads.**
-keep class com.pingstart.adsdk.adapter.**{ *; }
-keep class com.pingstart.mobileads.**{ *; }

#-keep class com.horoscope.android.lib.ads.**{ *; }
#-keep class com.facebook.internal.Utility.**{ *; }
#-keep class android.view.autofill.**

-keepattributes SourceFile,LineNumberTable -keep class com.inmobi.** { *; }
-keep public class com.google.android.gms.**
-dontwarn com.google.android.gms.**
-dontwarn com.squareup.picasso.**
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient{ public *; }
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info{ public *; }
# for DexGuard only
#-keep resourcexmlelements manifest/application/meta-data@value=GlideModule
-keep class com.bumptech.glide.integration.okhttp.OkHttpGlideModule
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep class io.mobitech.**
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class io.mobitech.content.**{ *; }
-keep class com.bumptech.glide.**
-keep class io.mobitech.content_ui.**{ *; }

-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}


# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /home/bianque/Android/Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-dontoptimize
-dontpreverify
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable

-keepattributes *Annotation*, InnerClasses, Signature, SourceFile, LineNumberTable, Exceptions

-dump class_files.txt
-printseeds seeds.txt
-printusage unused.txt
-printmapping mapping.txt

-ignorewarnings

# 对R文件下的所有类及其方法，都不能被混淆
-keepclassmembers class **.R$* {
   *;
}

#自定义控件
-keep public class * extends android.view.View
-keep public class * extends android.widget.RelativeLayout
-keep public class * extends android.widget.FrameLayout
-keep public class * extends android.widget.LinearLayout
-keep public class * extends ViewPager
-keep public class * extends ScrollView
-keep public class * extends Scroller
-keep public class * extends ProgressBar

#android组件
-keep public class * extends android.app.AppCompatActivity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService

# 保留Activity中的方法参数是view的方法，
# 从而我们在layout里面编写onClick就不会影响
-keepclassmembers class * extends android.app.Activity {
    public void * (android.view.View);
}

# 枚举类不能被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 保留Parcelable序列化的类不能被混淆
-keep class * implements android.os.Parcelable{
    public static final android.os.Parcelable$Creator *;
}

# 保留Serializable 序列化的类不被混淆
-keepclassmembers class * implements java.io.Serializable {
   static final long serialVersionUID;
   private static final java.io.ObjectStreamField[] serialPersistentFields;
   !static !transient <fields>;
   private void writeObject(java.io.ObjectOutputStream);
   private void readObject(java.io.ObjectInputStream);
   java.lang.Object writeReplace();
   java.lang.Object readResolve();
}

#native
-keepclasseswithmembernames class * {
    native <methods>;
}

#support
-dontwarn android.support.**
-keep class android.support.v4.** { *; }
-keep class android.support.v7.** { *; }

-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }

#fresco
-dontwarn com.facebook.**
-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip

-keep @com.facebook.common.internal.DoNotStrip class *

############### facebook混淆  ###############
-keep class com.facebook.ads.** {
    <fields>;
    <methods>;
}

-keepclassmembers class * {
    @com.facebook.common.internal.DoNotStrip *;
}

-dontwarn okio.**
-dontwarn com.squareup.okhttp.**
-dontwarn javax.annotation.**
-dontwarn com.android.volley.toolbox.**

#rxjava
-dontnote
-dontwarn sun.misc.**

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

# gson
-keepattributes Signature
-keepattributes *Annotation*
-dontwarn sun.misc.Unsafe
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }

#okhttp
-dontwarn okhttp3.**
-dontwarn okio.**
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }

-keep class com.squareup.sqlbrite.**{*;}


#otto
-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}

#facebook
-dontwarn com.facebook.**
-keep class com.facebook.** {
   *;
}

#gms
-dontwarn com.google.android.gms.**
-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}

-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}

-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

#squareup
-dontwarn com.squareup.**
-keep class com.squareup.**{*;}

# butterknife7.0
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
   @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
 @butterknife.* <methods>;
}

# glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

#retrofit
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-dontwarn com.squareup.okhttp.**

-keepclassmembers,allowobfuscation interface * {
    @retrofit.http.** <methods>;
}

-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

#Umeng
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

#google billing
-keep class com.android.vending.billing.**
-keep class Security.**{
    *;
}

#EventBus
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(Java.lang.Throwable);
}

#Bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
#aidl
-keep class android.content.pm.IPackageDataObserver { *; }
-keep class android.content.pm.IPackageStatsObserver { *; }
-keep class android.content.pm.PackageStats { *; }

-keep class com.pingstart.adsdk.adapter.**{ *; }
-keep class com.pingstart.mobileads.**{ *; }

#X5
# Addidional for x5.sdk classes for apps
-keep class com.tencent.smtt.export.external.**{*;}
-keep class com.tencent.tbs.video.interfaces.IUserStateChangedListener {*;}
-keep class com.tencent.smtt.sdk.CacheManager {public *;}
-keep class com.tencent.smtt.sdk.CookieManager {public *;}
-keep class com.tencent.smtt.sdk.WebHistoryItem {public *;}
-keep class com.tencent.smtt.sdk.WebViewDatabase {public *;}
-keep class com.tencent.smtt.sdk.WebBackForwardList {public *;}
-keep public class com.tencent.smtt.sdk.WebView {public <fields>;public <methods>;}
-keep public class com.tencent.smtt.sdk.WebView$HitTestResult {
	public static final <fields>;
	public java.lang.String getExtra();
	public int getType();
}
-keep public class com.tencent.smtt.sdk.WebView$WebViewTransport {public <methods>;}
-keep public class com.tencent.smtt.sdk.WebView$PictureListener {public <fields>;public <methods>;}
-keepattributes InnerClasses
-keep public enum com.tencent.smtt.sdk.WebSettings$** {*;}
-keep public enum com.tencent.smtt.sdk.QbSdk$** {*;}
-keep public class com.tencent.smtt.sdk.WebSettings {public *;}

-keepattributes Signature
-keep public class com.tencent.smtt.sdk.ValueCallback {public <fields>;public <methods>;}
-keep public class com.tencent.smtt.sdk.WebViewClient {public <fields>;public <methods>;}
-keep public class com.tencent.smtt.sdk.DownloadListener {public <fields>;public <methods>;}
-keep public class com.tencent.smtt.sdk.WebChromeClient {public <fields>;public <methods>;}
-keep public class com.tencent.smtt.sdk.WebChromeClient$FileChooserParams {public <fields>;public <methods>;}
-keep class com.tencent.smtt.sdk.SystemWebChromeClient{public *;}
# 1. extension interfaces should be apparent
-keep public class com.tencent.smtt.export.external.extension.interfaces.* {public protected *;}
# 2. interfaces should be apparent
-keep public class com.tencent.smtt.export.external.interfaces.* {public protected *;}
-keep public class com.tencent.smtt.sdk.WebViewCallbackClient {public protected *;}
-keep public class com.tencent.smtt.sdk.WebStorage$QuotaUpdater {
	public <fields>;
	public <methods>;
}
-keep public class com.tencent.smtt.sdk.WebIconDatabase {
	public <fields>;
	public <methods>;
}
-keep public class com.tencent.smtt.sdk.WebStorage {
	public <fields>;
	public <methods>;
}
-keep public class com.tencent.smtt.sdk.DownloadListener {
	public <fields>;
	public <methods>;
}
-keep public class com.tencent.smtt.sdk.QbSdk {
	public <fields>;
	public <methods>;
}
-keep public class com.tencent.smtt.sdk.QbSdk$PreInitCallback {
	public <fields>;
	public <methods>;
}
-keep public class com.tencent.smtt.sdk.CookieSyncManager {
	public <fields>;
	public <methods>;
}
-keep public class com.tencent.smtt.sdk.Tbs* {
	public <fields>;
	public <methods>;
}
-keep public class com.tencent.smtt.utils.LogFileUtils {
	public <fields>;
	public <methods>;
}
-keep public class com.tencent.smtt.utils.TbsLog {
	public <fields>;
	public <methods>;
}
-keep public class com.tencent.smtt.utils.TbsLogClient {
	public <fields>;
	public <methods>;
}
-keep public class com.tencent.smtt.sdk.CookieSyncManager {
	public <fields>;
	public <methods>;
}
# Added for game demos
-keep public class com.tencent.smtt.sdk.TBSGamePlayer {
	public <fields>;
	public <methods>;
}
-keep public class com.tencent.smtt.sdk.TBSGamePlayerClient* {
	public <fields>;
	public <methods>;
}
-keep public class com.tencent.smtt.sdk.TBSGamePlayerClientExtension {
	public <fields>;
	public <methods>;
}
-keep public class com.tencent.smtt.sdk.TBSGamePlayerService* {
	public <fields>;
	public <methods>;
}
-keep public class com.tencent.smtt.utils.Apn {
	public <fields>;
	public <methods>;
}
# end
-keep public class com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension {
	public <fields>;
	public <methods>;
}
-keep class MTT.ThirdAppInfoNew {*;}
-keep class com.tencent.mtt.MttTraceEvent {*;}
# Game related
-keep public class com.tencent.smtt.gamesdk.* {public protected *;}
-keep public class com.tencent.smtt.sdk.TBSGameBooter {public <fields>;public <methods>;}
-keep public class com.tencent.smtt.sdk.TBSGameBaseActivity {public protected *;}
-keep public class com.tencent.smtt.sdk.TBSGameBaseActivityProxy {public protected *;}
-keep public class com.tencent.smtt.gamesdk.internal.TBSGameServiceClient {public *;}
#

-keep class cn.bingoogolapple.bgabanner.BGAViewPager { *; }

#for gaid
-keep class **.AdvertisingIdClient$** { *; }

#for js and webview interface
-keepclassmembers class * {
     @android.webkit.JavascriptInterface <methods>;
}

-keep @com.qihoo.SdkProtected.sec.Keep class **{*;}

-keep,allowobfuscation @interface com.qihoo.SdkProtected.sec.Keep

#广告混淆去除log
-keep class com.pingstart.**{ *; }

-keepattributes SourceFile,LineNumberTable
-keep class com.inmobi.** { *; }
-dontwarn com.inmobi.**
-keep public class com.google.android.gms.**
-dontwarn com.google.android.gms.**
-dontwarn com.squareup.picasso.**
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient{public *;}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info{public *;}

#skip the Picasso library classes
-keep class com.squareup.picasso.** {*;}
-dontwarn com.squareup.picasso.**
-dontwarn com.squareup.okhttp.**

#skip AVID classes
-keep class com.integralads.avid.library.** {*;}

# Vungle
-keep class com.vungle.warren.** { *; }
-dontwarn com.vungle.warren.error.VungleError$ErrorCode

# Okio
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Retrofit
-dontwarn okio.**
-dontwarn retrofit2.Platform$Java8

# Gson
-keepattributes Signature
-keepattributes *Annotation*
-dontwarn sun.misc.**
-keep class com.google.gson.examples.android.model.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Google Android Advertising ID
-keep class com.google.android.gms.internal.** { *; }
-dontwarn com.google.android.gms.ads.identifier.**


# --------------------------MoPub混淆开始---------------------------------------
-keepclassmembers class com.mopub.** { public *; }
-keep public class com.mopub.**
-keep public class android.webkit.JavascriptInterface {}

# Explicitly keep any custom event classes in any package.
-keep class * extends com.mopub.mobileads.CustomEventBanner {}
-keep class * extends com.mopub.mobileads.CustomEventInterstitial {}
-keep class * extends com.mopub.mobileads.CustomEventRewardedAd {}
-keep class * extends com.mopub.nativeads.CustomEventNative {}

# Keep methods that are accessed via reflection
-keepclassmembers class ** { @com.mopub.common.util.ReflectionTarget *; }

# Support for Android Advertiser ID.
-keep class com.google.android.gms.common.GooglePlayServicesUtil {*;}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient {*;}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info {*;}

# Support for Google Play Services
# http://developer.android.com/google/play-services/setup.html
-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}

-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}

-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}
-keepclassmembers class com.ironsource.sdk.controller.IronSourceWebView$JSInterface {
    public *;
}
-keepclassmembers class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
-keep public class com.google.android.gms.ads.** {
   public *;
}
-keep class com.ironsource.adapters.** { *;
}
-dontwarn com.ironsource.mediationsdk.**
-dontwarn com.ironsource.adapters.**
-dontwarn com.moat.**
-keep class com.moat.** { public protected private *; }
# --------------------------MoPub混淆结束---------------------------------------

#AVLoadingIndicatorView
-keep class com.wang.avi.** { *; }
-keep class com.wang.avi.indicators.** { *; }

-keepattributes *Annotation*
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

## for DexGuard only
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule

-dontwarn com.android.installreferrer

-keep class com.money.lib.**{ *;}

# Vungle
-keep class com.vungle.warren.** { *; }
-keep class com.vungle.warren.downloader.DownloadRequest
-dontwarn com.vungle.warren.error.VungleError$ErrorCode
-dontwarn com.vungle.warren.downloader.DownloadRequest$Status
-keepclassmembers enum com.vungle.warren.** { *; }

# Moat SDK
-keep class com.moat.** { *; }
-dontwarn com.moat.**
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-keepattributes *Annotation*

# Retrofit
-keepattributes Signature, InnerClasses
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn javax.annotation.**
-dontwarn kotlin.Unit
-dontwarn retrofit2.-KotlinExtensions
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Okio+OkHttp
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn org.conscrypt.**
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
-keepclassmembers class * extends com.vungle.warren.persistence.Memorable {
   public <init>(byte[]);
}

-keep class com.chartboost.** { *; }

-keep class com.tapjoy.** { *; }
-keep class com.moat.** { *; }
-keepattributes JavascriptInterface
-keepattributes *Annotation*
-keep class * extends java.util.ListResourceBundle {
protected Object[][] getContents();
}
-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
public static final *** NULL;
}
-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
@com.google.android.gms.common.annotation.KeepName *;
}
-keepnames class * implements android.os.Parcelable {
public static final ** CREATOR;
}
-keep class com.google.android.gms.ads.identifier.** { *; }
-dontwarn com.tapjoy.**

-keepclassmembers class com.ironsource.sdk.controller.IronSourceWebView$JSInterface {
    public *;
}
-keepclassmembers class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
-keep public class com.google.android.gms.ads.** {
   public *;
}
-keep class com.ironsource.adapters.** { *;
}
-dontwarn com.ironsource.mediationsdk.**
-dontwarn com.ironsource.adapters.**
-dontwarn com.moat.**
-keep class com.moat.** { public protected private *; }

-dontnote com.android.vending.licensing.ILicensingService
-dontnote com.google.vending.licensing.ILicensingService
-dontnote com.google.android.vending.licensing.ILicensingService
-dontnote android.support.**
-keep class com.google.ads.mediation.admob.AdMobAdapter { *; }
-keep class com.google.ads.mediation.AdUrlAdapter { *; }

# The following are kept explictly for safety reasons, they are invoked using reflection by admob / mopub
-keep class com.inneractive.api.ads.mediations.InneractiveBannerForGoogle { *; }
-keep class com.inneractive.api.ads.mediations.FyberBannerForMopub { *; }
-keep class com.inneractive.api.ads.mediations.InneractiveInterstitialForGoogle { *; }
-keep class com.inneractive.api.ads.mediations.FyberInterstitialForMopub { *; }
-keep class com.inneractive.api.ads.mediations.InneractiveRectangleForGoogle { *; }
-keep class com.inneractive.api.ads.mediations.InneractiveRewardedVideoForGoogle { *; }
-keep class com.inneractive.api.ads.mediations.FyberRewardedVideoForMopub { *; }

-keepattributes Signature
-keepattributes *Annotation*
-keep class com.mintegral.** {*; }
-keep interface com.mintegral.** {*; }
-keep interface androidx.** { *; }
-keep class androidx.** { *; }
-keep public class * extends androidx.** { *; }
-dontwarn com.mintegral.**
-keep class **.R$* { public static final int mintegral*; }
-keep class com.alphab.** {*; }
-keep interface com.alphab.** {*; }

-keepattributes SourceFile,LineNumberTable
-keep class com.inmobi.** { *; }
-keep public class com.google.android.gms.**
-dontwarn com.google.android.gms.**
-dontwarn com.squareup.picasso.**
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient{
     public *;
}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info{
     public *;
}
# skip the Picasso library classes
-keep class com.squareup.picasso.** {*;}
-dontwarn com.squareup.okhttp.**
# skip Moat classes
-keep class com.moat.** {*;}
-dontwarn com.moat.**
# skip IAB classes
-keep class com.iab.** {*;}
-dontwarn com.iab.**
-keep public class com.google.firebase.messaging.FirebaseMessagingService {
  public *;
}

-keep class com.google.android.material.** {*;}
-keep class androidx.** {*;}
-keep public class * extends androidx.**
-keep interface androidx.** {*;}
-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**
-dontwarn androidx.**

#手动启用support keep注解
#http://tools.android.com/tech-docs/support-annotations
-dontskipnonpubliclibraryclassmembers
-printconfiguration
-keep,allowobfuscation @interface androidx.annotation.Keep

-keep @androidx.annotation.Keep class *
-keepclassmembers class * {
    @androidx.annotation.Keep *;
}

-keep public class com.google.android.gms.** { public protected *; }

-keep public class com.alibaba.android.arouter.routes.**{*;}
-keep public class com.alibaba.android.arouter.facade.**{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}

# 如果使用了 byType 的方式获取 Service，需添加下面规则，保护接口
-keep interface * implements com.alibaba.android.arouter.facade.template.IProvider

# 如果使用了 单类注入，即不定义接口实现 IProvider，需添加下面规则，保护实现
-keep class * implements com.alibaba.android.arouter.facade.template.IProvider


-keepattributes Signature
  -keepattributes *Annotation*
  -keep class com.mintegral.** {*; }
  -keep interface com.mintegral.** {*; }
  -keep interface androidx.** { *; }
  -keep class androidx.** { *; }
  -keep public class * extends androidx.** { *; }
  -dontwarn com.mintegral.**
  -keep class **.R$* { public static final int mintegral*; }
  -keep class com.alphab.** {*; }
  -keep interface com.alphab.** {*; }

  -keepattributes SourceFile,LineNumberTable
  -keep class com.inmobi.** { *; }
  -keep public class com.google.android.gms.**
  -dontwarn com.google.android.gms.**
  -dontwarn com.squareup.picasso.**
  -keep class com.google.android.gms.ads.identifier.AdvertisingIdClient{
       public *;
  }
  -keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info{
       public *;
  }
  # skip the Picasso library classes
  -keep class com.squareup.picasso.** {*;}
  -dontwarn com.squareup.okhttp.**
  # skip Moat classes
  -keep class com.moat.** {*;}
  -dontwarn com.moat.**
  # skip IAB classes
  -keep class com.iab.** {*;}
  -dontwarn com.iab.**

  -keepclassmembers class com.ironsource.sdk.controller.IronSourceWebView$JSInterface {
      public *;
  }
  -keepclassmembers class * implements android.os.Parcelable {
      public static final android.os.Parcelable$Creator *;
  }
  -keep public class com.google.android.gms.ads.** {
     public *;
  }
  -keep class com.ironsource.adapters.** { *;
  }
  -dontwarn com.ironsource.mediationsdk.**
  -dontwarn com.ironsource.adapters.**
  -dontwarn com.moat.**
  -keep class com.moat.** { public protected private *; }


  -keep class com.tapjoy.** { *; }
  -keep class com.moat.** { *; }
  -keepattributes JavascriptInterface
  -keepattributes *Annotation*
  -keep class * extends java.util.ListResourceBundle {
  protected Object[][] getContents();
  }
  -keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
  public static final *** NULL;
  }
  -keepnames @com.google.android.gms.common.annotation.KeepName class *
  -keepclassmembernames class * {
  @com.google.android.gms.common.annotation.KeepName *;
  }
  -keepnames class * implements android.os.Parcelable {
  public static final ** CREATOR;
  }
  -keep class com.google.android.gms.ads.identifier.** { *; }
  -dontwarn com.tapjoy.**

  -keep class com.daimajia.easing.**{*;}


  -dontwarn com.tencent.bugly.**
  -keep public class com.tencent.bugly.**{*;}

  #svgaplayer
  -keep class com.squareup.wire.** { *; }
  -keep class com.opensource.svgaplayer.proto.** { *; }

  #gifImageView
  -keep public class pl.droidsonroids.gif.GifIOException{<init>(int);}
  -keep class pl.droidsonroids.gif.GifInfoHandle{<init>(long,int,int,int);}