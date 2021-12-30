package com.otamurod.animations

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Alpha
         * */
//        load animation from XML
//        val alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha)

        //load animation from its own Class
        val alphaAnimation = AlphaAnimation(0.0f, 1.0f)
        alphaAnimation.duration = 6000

        /**
         * Scale
         * */
//        load animation from XML
//        val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale)

        //load animation from its own Class
        val scaleAnimation = ScaleAnimation(0.1f, 1.0f, 0.1f, 1.0f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f)
        scaleAnimation.duration = 3000

        /**
         * Translate
         * */
//        load animation from XML
//        val translateAnimation = AnimationUtils.loadAnimation(this, R.anim.translate)

        //load animation from its own Class
        val translateAnimation = TranslateAnimation(-200f, 0f, -200f, 0f)
        translateAnimation.duration = 3000

        /**
         * Rotate
         * */
//        load animation from XML
//        val rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate)

        //load animation from its own Class
        val rotateAnimation = RotateAnimation(0.0f, 360.0f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        rotateAnimation.duration = 3000

        /**
         * Combination of rotate and scale
         * */
//        load animation from XML
        val combination = AnimationUtils.loadAnimation(this, R.anim.combination)

//        //load animation from its own Class
//        val combination = RotateAnimation(0.0f, 360.0f,
//            Animation.RELATIVE_TO_SELF, 0.5f,
//            Animation.RELATIVE_TO_SELF, 0.5f
//        )
//        rotateAnimation.duration = 3000

        /**
         * Button Listener
         */
        button.setOnClickListener {
            /**
             * Alpha
             * */
            //start animation from XML
//            textView.startAnimation(alphaAnimation)
            //start animation from object
            textView.startAnimation(alphaAnimation)

            /**
             * Scale
             * */
            //start animation from XML
//            textView2.startAnimation(scaleAnimation)
            //start animation from object
            textView2.startAnimation(scaleAnimation)

            /**
             * Translate
             * */
            //start animation from XML
//            textView3.startAnimation(translateAnimation)
            //start animation from object
            textView3.startAnimation(translateAnimation)

            /**
             * Rotate
             * */
            //start animation from XML
//            textView4.startAnimation(rotateAnimation)
            //start animation from object
            textView4.startAnimation(rotateAnimation)

            /**
             * Combination
             * */
            //start animation from XML
            textView5.startAnimation(combination)
            //start animation from object
//            textView4.startAnimation(rotateAnimation)

        }
    }
}