package com.example.fragmentdemo.task6.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragmentdemo.R
import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import kotlin.math.abs

class SwipeActivity : AppCompatActivity() {

    private var onSwipeTouchListener: OnSwipeTouchListener? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swip)
        title = "KotlinApp"
        onSwipeTouchListener = OnSwipeTouchListener(this, findViewById(R.id.relativeLayout))
    }

    class OnSwipeTouchListener internal constructor(ctx:Context, mainView: View):View.OnTouchListener{
        private val gestureDetector: GestureDetector
        private var context: Context
        private lateinit var onSwipe:OnSwipeListener
        init{
            gestureDetector = GestureDetector(ctx, GestureListener())
            mainView.setOnTouchListener(this)
            context = ctx
        }
        override fun onTouch(v:View, event: MotionEvent):Boolean {
            return gestureDetector.onTouchEvent(event)
        }
        private companion object {
            private const val swipeThreshold = 100
            private const val swipeVelocityThreshold = 100
        }
        inner class GestureListener:GestureDetector.SimpleOnGestureListener() {
            override fun onDown(e:MotionEvent):Boolean {
                return true
            }
            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                var result = false
                try{
                    val diffY = e2.y - e1!!.y
                    val diffX = e2.x - e1!!.x
                    if (abs(diffX) > abs(diffY)){
                        if (abs(diffX) > swipeThreshold && abs(velocityX) > swipeVelocityThreshold){
                            if (diffX > 0){
                                onSwipeRight()
                            }
                            else{
                                onSwipeLeft()
                            }
                            result = true
                        }
                    }
                    else if (abs(diffY) > swipeThreshold && abs(velocityY) > swipeVelocityThreshold){
                        if (diffY > 0){
                            onSwipeBottom()
                        }
                        else{
                            onSwipeTop()
                        }
                        result = true
                    }
                }
                catch (exception:Exception) {
                    exception.printStackTrace()
                }
                return result
            }
        }
        internal fun onSwipeRight() {
            Toast.makeText(context, "Swiped Right", Toast.LENGTH_SHORT).show()
            this.onSwipe.swipeRight()
        }
        internal fun onSwipeLeft() {
            Toast.makeText(context, "Swiped Left", Toast.LENGTH_SHORT).show()
            this.onSwipe.swipeLeft()
        }
        internal fun onSwipeTop() {
            Toast.makeText(context, "Swiped Up", Toast.LENGTH_SHORT).show()
            this.onSwipe.swipeTop()
        }
        internal fun onSwipeBottom() {
            Toast.makeText(context, "Swiped Down", Toast.LENGTH_SHORT).show()
            this.onSwipe.swipeBottom()
        }
        internal interface OnSwipeListener {
            fun swipeRight()
            fun swipeTop()
            fun swipeBottom()
            fun swipeLeft()
        }
    }
}