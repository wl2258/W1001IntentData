package kr.ac.kumoh.s20200607.w1001intentdata

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import kr.ac.kumoh.s20200607.w1001intentdata.databinding.ActivityImageBinding

class ImageActivity : AppCompatActivity(),
    View.OnClickListener {
        companion object {
            const val imageName = "image"
            const val resultName = "result"

            const val LIKE = 10
            const val DISLIKE = 20
            const val NONE = 0
        }
    private lateinit var binding: ActivityImageBinding
    private var image: String? = null

    override fun onClick(v: View?) {
        val intent = Intent()
        val value = when (v?.id) {
            binding.btnLike.id -> LIKE
            binding.btnDislike.id -> DISLIKE
            else -> NONE
        }
        intent.putExtra(imageName, image)
        intent.putExtra(resultName, value)
        setResult(RESULT_OK, intent)
        finish() //현재 액티비티 종료해라!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        image = intent.getStringExtra(MainActivity.keyName)
        val res = when (image){
            "gundam" -> R.drawable.gundam
            "zaku" -> R.drawable.zaku
            else -> R.drawable.ic_launcher_foreground
        }
        binding.imgGundam.setImageResource(res)

        binding.btnLike.setOnClickListener(this)
        binding.btnDislike.setOnClickListener(this)
    }
}
