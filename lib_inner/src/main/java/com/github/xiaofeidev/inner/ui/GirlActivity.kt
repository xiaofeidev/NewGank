package com.github.xiaofeidev.inner.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.observe
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ImageUtils
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.github.xiaofeidev.base.ui.BaseActivity
import com.github.xiaofeidev.comm.router.ARouterParam
import com.github.xiaofeidev.comm.router.PAGE_GIRL
import com.github.xiaofeidev.inner.R
import com.github.xiaofeidev.inner.databinding.ActivityGirlBinding
import com.github.xiaofeidev.inner.vm.GirlViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File


@Route(path = PAGE_GIRL)
class GirlActivity : BaseActivity<ActivityGirlBinding>() {
    @Autowired(name = ARouterParam.PAGE_WEB_URL)
    lateinit var url: String

    val viewModel by viewModel<GirlViewModel>()

    override val layoutId: Int
        get() = R.layout.activity_girl

    override fun perSetContent() {
        ARouter.getInstance().inject(this);
    }

    override fun initView() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //图片还未加载完成时显示正在加载
        binding.refresh.setOnRefreshListener { binding.refresh.setRefreshing(false) }

        Glide.with(this)
            .asBitmap()
            .load(url)
            .listener(object : RequestListener<Bitmap>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    viewModel.isRefreshing.value = false
                    ToastUtils.showShort(R.string.comm_error)
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    viewModel.isRefreshing.value = false
                    return false
                }
            })
            .into(binding.imgImg)
    }

    override fun initData() {
    }

    override fun startObserve() {
        viewModel.apply {
            isRefreshing.value = true

            isRefreshing.observe(this@GirlActivity){
                binding.refresh.isRefreshing = it
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_girl, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            R.id.save -> ToastUtils.showShort(R.string.comm_planning)/*saveImage()*/
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                saveImage()
            } else {
                ToastUtils.showShort(R.string.deny_hint)
            }
        }
    }

    private fun saveImage(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                ToastUtils.showShort(R.string.save_explain)
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
            }
        } else {
            /*val appDir = File(Environment.getExternalStorageDirectory(), "Gank")
            if (!appDir.exists()) {
                appDir.mkdir()
            }
            val fileName = System.currentTimeMillis().toString() + ".jpg"
            val file = File(appDir, fileName)*/
//            val path = PathUtils.getExternalPicturesPath()
//            val fileRoot = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//            val fileName = System.currentTimeMillis().toString() + ".png"
//            val file = File(fileRoot, fileName)
//            val saveRes = ImageUtils.save((imgImg.drawable as BitmapDrawable).bitmap, file, Bitmap.CompressFormat.PNG)
//            if(saveRes){
//                ToastUtils.showShort("保存成功")
//                // 最后通知图库更新
//
//                // 最后通知图库更新
//                sendBroadcast(
//                    Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.path))
//                )
//            } else {
//                ToastUtils.showShort("保存失败")
//            }
            /*MediaStore.Images.Media.insertImage(
                contentResolver,
                (imgImg.drawable as BitmapDrawable).bitmap,
                "image_file",
                "file")*/
        }
    }
}