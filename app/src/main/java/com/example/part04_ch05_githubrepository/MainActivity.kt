package com.example.part04_ch05_githubrepository

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import com.example.part04_ch05_githubrepository.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

    }

    private fun initViews() = with(binding) {
        loginBtn.setOnClickListener {
            loginGithub()
        }
    }

    // https://github.com/login/auth/authorize?client_id=~~
    private fun loginGithub() {
        val loginUri = Uri.Builder().scheme("https").authority("github.com")
            .appendPath("login")
            .appendPath("oauth")
            .appendPath("authorize")
            .appendQueryParameter("client_id", BuildConfig.GITHUB_CLIENT_ID)
            .build()

        // 브라우저 라이브러리를 기반으로 선언해서 실행
        CustomTabsIntent.Builder().build().also {
            it.launchUrl(this, loginUri)
        }
    }

    // 앱연동 시 인텐를 받아서 토큰을 받아온다.
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        intent?.data?.getQueryParameter("code")?.let {
            // AccessToken을 받아온다.
        }
    }


}

/*

 1) 코루틴 개념 소개, 비동기적으로 리모트데이터 불러오기
 2) 검색 기능추가하기
 3) 룸 개념 소개
 4) 비동기적으로 로컬데이터 쓰기, 읽기

 */