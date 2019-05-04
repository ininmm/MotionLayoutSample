package com.example.motionlayoutsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.motionlayoutsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    private lateinit var binding: ActivityMainBinding

    private var doShowPaths = false

    private val itemData: Array<Demo> = arrayOf(
        Demo("Basic Example (1/2)",
            "Basic motion example using referenced ConstraintLayout files",
            R.layout.motion_01_basic),
        Demo("Basic Example (2/2)",
            "Basic motion example using ConstraintSets defined in the MotionScene file",
            R.layout.motion_02_basic),
        Demo("Custom Attribute",
            "Show color interpolation (custom attribute)",
            R.layout.motion_03_custom_attribute)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.recyclerDemoList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = SampleAdapter(itemData)
        }

        binding.switchShowPaths.setOnCheckedChangeListener(this)

    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        doShowPaths = isChecked
    }

    fun start(activity: Class<*>, layoutFileId: Int) {
        val intent = Intent(this, activity).apply {
            putExtra("layout_file_id", layoutFileId)
            putExtra("showPaths", doShowPaths)
        }
        startActivity(intent)
    }
}
