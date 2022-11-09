package kr.ac.kumoh.s20200607.w1001intentdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import kr.ac.kumoh.s20200607.w1001intentdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.btnGundam.setOnClickListener {
//            val intent = Intent(this, ImageActivity::class.java)
//            intent.putExtra("image", "gundam") //intent data 전달 시 사용
//            startActivity(intent)
//        }
//        binding.btnZaku.setOnClickListener {
//            val intent = Intent(this, ImageActivity::class.java)
//            intent.putExtra("image", "zaku")
//            startActivity(intent)
//        }
//    }

    View.OnClickListener { //onclicklistener은 인터페이스
        private lateinit var binding: ActivityMainBinding
        private lateinit var launcher: ActivityResultLauncher<Intent> //

        companion object {
            const val keyName = "image"
        }
        override fun onClick(v: View?) {
            val intent = Intent(this, ImageActivity::class.java)
            val value = when (v?.id) {
                binding.btnGundam.id -> "gundam"
                binding.btnZaku.id -> "zaku"
                else -> return
            }
            intent.putExtra(keyName, value) //intent data 전달 시 사용
            launcher.launch(intent)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGundam.setOnClickListener(this) //내 객체를 넣어줌
        binding.btnZaku.setOnClickListener(this)

        /*
            * 교수님도 익숙하지 않다며,,, 풀 수 있는 것만 시험에 내는데,,, 이건 모르겠다 충!격!발!언!
         */
        launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { // callback 함수 같이 넣어줌. 여기로 돌아오도록

            if (it.resultCode != RESULT_OK)
                return@registerForActivityResult //어디까지 뭘 리턴하려는지 알아서 알려줌

            val result = it.data?.getIntExtra(ImageActivity.resultName, ImageActivity.NONE) //data 안에 intent가 들어있음.
            //(getIntExtra에서만) 만약 resultName이 존재하지 않는다면 어떤 값을 반환할지 지정해줄 수 있음 -> ImageActivity.NONE

            val str = when (result) {
                ImageActivity.LIKE -> "좋아요"
                ImageActivity.DISLIKE -> "싫어요"
                else -> ""
            }
            val image = it.data?.getStringExtra(ImageActivity.imageName)
            when (image) {
                "gundam" -> binding.btnGundam.text = "건담 ($str)"
                "zaku" -> binding.btnZaku.text = "자쿠 ($str)"
            }
        }
    }
    }

