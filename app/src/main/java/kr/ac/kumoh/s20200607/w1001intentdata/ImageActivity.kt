package kr.ac.kumoh.s20200607.w1001intentdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.ac.kumoh.s20200607.w1001intentdata.databinding.ActivityImageBinding

class ImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_image)
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val res = when (intent.getStringExtra("image")) {
            "gundam" -> R.drawable.gundam
            "zaku" -> R.drawable.zaku
            else -> R.drawable.ic_launcher_foreground
        }
        binding.imgGundam.setImageResource(res)
    }
}