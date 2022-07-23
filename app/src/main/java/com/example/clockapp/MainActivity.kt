package com.example.clockapp

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.clockapp.databinding.ActivityMainBinding
import com.google.android.material.card.MaterialCardView

//@auther Ali_Assalem
class MainActivity : AppCompatActivity() {
    private val viewModel:MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindLiveData(viewModel.segmentTopLiveData,binding.segmentTop.root)
        bindLiveData(viewModel.segmentTopLeftLiveData,binding.segmentTopLeft.root)
        bindLiveData(viewModel.segmentTopRightLiveData,binding.segmentTopRight.root)
        bindLiveData(viewModel.segmentMiddleLiveData,binding.segmentMiddle.root)
        bindLiveData(viewModel.segmentBottomLiveData,binding.segmentBottom.root)
        bindLiveData(viewModel.segmentBottomLeftLiveData,binding.segmentBottomLeft.root)
        bindLiveData(viewModel.segmentBottomRightLiveData,binding.segmentBottomRight.root)

        viewModel.startCounting()
    }
    private fun bindLiveData(liveData:LiveData<Int>,materialCardView: MaterialCardView){
        liveData.observe(this){colorRes ->
            materialCardView.apply {
                val resolvedColor = ContextCompat.getColor(context,colorRes)
                setBackgroundColor(resolvedColor)
            }
        }
    }
}