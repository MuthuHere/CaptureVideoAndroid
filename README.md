# CaptureVideoAndroid


Android capturing 3 seconds of video and play in videoview


![Alt Text](https://media.giphy.com/media/XGbIrUBGxU9BJqQxzU/giphy.gif)


### Request permission 
  To access Camera, Record audio and storage we need to get permission.
  I used ```implementation 'com.karumi:dexter:6.0.0'``` to capture permissions.
  
### Open Camera to Record
  Open camera using below code with the recoding time
    
        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 3)
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1)
        startActivityForResult(intent, CAMERA_CAPTURE_VIDEO_REQUEST_CODE)

### Get 3 second video after recorded successfully
   
          override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            if (resultCode === Activity.RESULT_OK && requestCode == CAMERA_CAPTURE_VIDEO_REQUEST_CODE) {
               val vid = data?.data
               videoPath = getRealPathFromURI(vid!!)
               vv.setVideoURI(Uri.parse(videoPath))
               vv.requestFocus()
               vv.start()
             }
         }

Happie coding:)
