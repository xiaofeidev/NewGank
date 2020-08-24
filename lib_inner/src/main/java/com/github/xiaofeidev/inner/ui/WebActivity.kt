package com.github.xiaofeidev.inner.ui

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.view.Menu
import android.view.MenuItem
import android.webkit.*
import androidx.lifecycle.observe
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ToastUtils
import com.github.xiaofeidev.base.ui.BaseActivity
import com.github.xiaofeidev.comm.router.ARouterParam
import com.github.xiaofeidev.comm.router.PAGE_WEB
import com.github.xiaofeidev.inner.R
import com.github.xiaofeidev.inner.databinding.ActivityWebBinding
import com.github.xiaofeidev.inner.vm.WebViewModel
import kotlinx.android.synthetic.main.activity_web.*
import org.koin.androidx.viewmodel.ext.android.viewModel


@Route(path = PAGE_WEB)
class WebActivity :BaseActivity<ActivityWebBinding>() {
    @Autowired(name = ARouterParam.PAGE_WEB_URL)
    lateinit var url: String
    @Autowired(name = ARouterParam.PAGE_WEB_TITLE)
    lateinit var title: String

    val viewModel by viewModel<WebViewModel>()

    override val layoutId: Int
        get() = R.layout.activity_web

    override fun perSetContent() {
        ARouter.getInstance().inject(this);
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun initView() {
        setSupportActionBar(toolbar)
        setTitle(title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.isRefreshing.value = true
        web.requestFocusFromTouch() //设置支持获取手势焦点

        val webSettings: WebSettings = web.getSettings()
        webSettings.javaScriptEnabled = true //支持js

        webSettings.loadWithOverviewMode = true
        webSettings.setAppCacheEnabled(true)
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
        // 设置可以支持缩放
        webSettings.setSupportZoom(true)
        web.webChromeClient = WebChromeClient()
        web.setWebViewClient(object : WebViewClient() {
            /**
             * desc：一般的正常加载无需覆盖此方法，使用默认回调就能正常加载网页
             */
            //        @Override
            //        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            //            webView.loadUrl(url);
            //            return true;
            //        }
            //
            //        @Override
            //        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest request) {
            //            webView.loadUrl(request.getUrl().toString());
            //            return true;
            //        }
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                viewModel.isRefreshing.value = true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                viewModel.isRefreshing.value = false
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                viewModel.isRefreshing.value = false
                ToastUtils.showShort(R.string.comm_error)
            }
        })
        web.loadUrl(url)

        refresh.setOnRefreshListener {
            web.reload()
        }
    }

    override fun initData() {

    }

    override fun startObserve() {
        viewModel.apply {
            isRefreshing.value = true

            isRefreshing.observe(this@WebActivity){
                refresh.isRefreshing = it
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_web, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
            R.id.open -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
            R.id.copy -> {
                val clipboardManager: ClipboardManager =
                    getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val data = ClipData.newPlainText(resources.getString(R.string.copy_label), url)
                clipboardManager.setPrimaryClip(data)
                ToastUtils.showShort(R.string.copy_success)
            }
            R.id.share -> {
                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "$title \n $url"
                )
                sendIntent.type = resources.getString(R.string.share_type)
                startActivity(Intent.createChooser(sendIntent, resources.getString(R.string.share_type)))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (web.canGoBack()) {
            web.goBack()
        } else {
            super.onBackPressed()
        }
    }
}