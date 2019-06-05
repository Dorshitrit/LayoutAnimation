package com.dor.layoutanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clickHere.setOnClickListener {
            setPopupAnimation()
        }
    }


    private fun setPopupAnimation() {
        if (!markerPopupLayout.isShown) {
            markerPopupLayout.visibility = View.VISIBLE
            setLayoutAnimate(markerPopupLayout) { oldY: Float?, newY: Float?, _: Int?, newHeight: Int ->
                setOpenLayoutAnimation(markerPopupLayout, oldY!!, newY!! + newHeight) {
                    clickHere.text = "Close"
                }
            }
        } else {
            setOpenLayoutAnimation(
                    markerPopupLayout,
                    markerPopupLayout.y,
                    markerPopupLayout.y - markerPopupLayout.height
            ) {
                if (markerPopupLayout != null) {
                    markerPopupLayout.visibility = View.GONE
                    clickHere.text = "Open"
                }
            }
        }
    }
}
