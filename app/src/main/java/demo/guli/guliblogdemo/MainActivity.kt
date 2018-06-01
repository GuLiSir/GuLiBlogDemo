package demo.guli.guliblogdemo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.btn_running_activity).setOnClickListener {
            startActivity(Intent(this, RunningViewActivity::class.java))
            finish()
        }
    }
}
