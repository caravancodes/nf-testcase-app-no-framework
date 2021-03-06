package com.frogobox.testnoframework

import android.os.Bundle
import com.bumptech.glide.Glide
import com.frogobox.testnoframework.databinding.ActivityDetailBinding
import com.frogobox.testnoframework.model.Article
import com.google.gson.Gson

class DetailActivity : BaseActivity<ActivityDetailBinding>() {

    companion object {
        const val EXTRA_DATA = "EXTRA_DATA"
    }

    private val extraDataContent by lazy {
        Gson().fromJson(
            intent.getStringExtra(EXTRA_DATA),
            Article::class.java
        )
    }

    override fun setupViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        extraDataContent.title?.let { setupDetailActivity(it) }
        binding.apply {
            tvTitle.text = extraDataContent.title
            tvOverview.text = extraDataContent.content
            Glide.with(this@DetailActivity).load(extraDataContent.urlToImage).into(ivPoster)
        }
    }

}