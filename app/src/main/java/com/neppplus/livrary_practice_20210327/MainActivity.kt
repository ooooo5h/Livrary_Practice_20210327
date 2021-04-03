package com.neppplus.livrary_practice_20210327

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        callBtn.setOnClickListener {

//            버튼이 눌리면 => 권한이 있는지 확인 => OK 이면 바로 전화 연결
//            권한이 없으면 => 연결 불가 안내 토스트 띄우자

//            권한 상태에 따른 행동 방침(가이드북)을 변수로 저장하자

            val permissionListener = object : PermissionListener {
                override fun onPermissionGranted() {

//                    권한이 허용되어 있는 경우를 이야기함 => 여기 {}안의 내용을 실행해준다
//                    실제로 전화를 걸자 - 허용되어있을때

                    val myUri = Uri.parse("tel:010-5555-7777")

                    val myIntent = Intent(Intent.ACTION_CALL, myUri)
                    startActivity(myIntent)

                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {

//                    권한을 허락해주지않으면? 최종 거절되었을 때 => 보통 toast로 안내 문구를 띄워줌
                    Toast.makeText(this@MainActivity, "권한이 거부되어  전화 연결이 불가능합니다.", Toast.LENGTH_SHORT).show()

                }

            }

//            그 방침을 가지고 => 실제로 권한 확인

            TedPermission.with(this)
                    .setPermissionListener(permissionListener)
                    .setDeniedMessage("[설정]>권한 에서 전화 권한을 허용해주세요.")
                    .setPermissions(Manifest.permission.CALL_PHONE)
                    .check()
        }



        profilePictureImg.setOnClickListener {

//            사진을 크게 보는 화면으로 이동시키자

            val myIntent = Intent(this, ViewProfilePictureActivity::class.java)
            startActivity(myIntent)


        }
    }
}