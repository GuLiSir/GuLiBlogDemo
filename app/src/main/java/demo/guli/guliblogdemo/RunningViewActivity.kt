package demo.guli.guliblogdemo

import android.app.Activity
import android.os.Bundle
import android.view.View
import demo.guli.widget.RunningAnimationView

/**
 *
 */
class RunningViewActivity : Activity() {

    private var runView:RunningAnimationView ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_running_view)
         runView = findViewById(R.id.activity_running_view_view)
        test()

        findViewById<View>(R.id.activity_running_btn_start).setOnClickListener {
            runView?.prepare()
            runView?.startEffect(null)
        }
    }

    fun test() {
        val line2Start = RunningAnimationView.Point(300, 300)
        val line2End = RunningAnimationView.Point(400, 180)
        runView?.add(RunningAnimationView.LineItem(RunningAnimationView.Point(200, 250), line2Start, 1500))
        runView?.add(RunningAnimationView.LineItem(line2Start, line2End, 1500))
        runView?.add(RunningAnimationView.LineItem(RunningAnimationView.Point(400, 180),
                RunningAnimationView.Point(500, 280), 1500))
        runView?.add(RunningAnimationView.LineItem( RunningAnimationView.Point(500, 280),
                RunningAnimationView.Point(700, 150), 1500))
        runView?.add(RunningAnimationView.DotItem(RunningAnimationView.Point(704, 154), 8))
    }
}