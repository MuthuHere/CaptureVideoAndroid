package com.app.videocapture

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.net.Uri
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    private var videoPath = ""

    companion object {
        const val CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        getPermissionToRecordVideo()

        fab.setOnClickListener {
            getPermissionToRecordVideo()
            val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 3)
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1)
            // intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri) // set the image file
            startActivityForResult(intent, CAMERA_CAPTURE_VIDEO_REQUEST_CODE)
        }
    }


    private fun getPermissionToRecordVideo() {
        Dexter.withActivity(this)
            .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA)
            .withListener(object :MultiplePermissionsListener{
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {


                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {


                }


            })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode === Activity.RESULT_OK) {
            val vid = data?.data
            videoPath = getRealPathFromURI(vid!!)
            vv.setVideoURI(Uri.parse(videoPath))
            vv.requestFocus()
            vv.start()
        }
    }

    private fun getRealPathFromURI(contentUri: Uri): String {
        return FilePath.getPath(this, contentUri)
    }


}
