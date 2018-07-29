package com.starboy.colourspallet

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.Palette
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val neutralTarget =
            Palette.Target.Builder()
                    .setMinimumLightness(0.20f)
                    .setTargetLightness(0.5f)
                    .setMaximumLightness(0.8f)
                    .setMinimumSaturation(0.1f)
                    .setTargetSaturation(0.6f)
                    .setMaximumSaturation(1f)
                    .setPopulationWeight(0.18f)
                    .setSaturationWeight(0.22f)
                    .setLightnessWeight(0.60f)
                    .setExclusive(false)
                    .build()

    private var currimage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun generatePalette(bitmap: Bitmap) {
        Palette.from(bitmap)
                .generate { palette ->
                    palette.vibrantSwatch?.let { swatch ->
                        bg.setBackgroundColor(swatch.rgb)
                        title.setTextColor(swatch.titleTextColor)
                        body.setTextColor(swatch.bodyTextColor)
                    }
                }
    }

    private fun setViewColour(swatch: Palette.Swatch?, background: View, titleTextView: TextView, bodyTextView: TextView) {
        swatch?.apply {
            background.setBackgroundColor(rgb)
            titleTextView.setTextColor(titleTextColor)
            bodyTextView.setTextColor(bodyTextColor)
            bodyTextView.text = "Poppulation = $population"
        } ?: let {
            background.setBackgroundColor(resources.getColor(android.R.color.transparent))
            titleTextView.setTextColor(resources.getColor(android.R.color.transparent))
            bodyTextView.setTextColor(resources.getColor(android.R.color.transparent))
        }
    }
}
