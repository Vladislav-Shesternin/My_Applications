package com.veldan.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.scand.svg.SVGHelper
import com.scand.svg.parser.SVGHandler
import com.veldan.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val img = SVGHelper.useContext(this).open(R.raw.img_1).bitmap
        binding.image.setImageBitmap(img)
    }
}