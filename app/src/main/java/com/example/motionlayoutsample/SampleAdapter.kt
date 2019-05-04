package com.example.motionlayoutsample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.motionlayoutsample.MotionSampleViewHolder.DemoViewHolder
import com.example.motionlayoutsample.databinding.ItemRowBinding

class SampleAdapter(private val dataSet: Array<Demo>) :
    RecyclerView.Adapter<MotionSampleViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MotionSampleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.item_row -> DemoViewHolder(
                ItemRowBinding.inflate(inflater, parent, false)
            )
            else -> throw IllegalStateException("Unknown viewType $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_row
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: MotionSampleViewHolder, position: Int) {
        when (holder) {
            is DemoViewHolder -> holder.bind(dataSet[position])
        }
    }
}

sealed class MotionSampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    class DemoViewHolder(val binding: ItemRowBinding) : MotionSampleViewHolder(binding.root) {
        fun bind(demo: Demo) {
            binding.apply {
                lifecycleOwner = lifecycleOwner
                demoData = demo
                root.setOnClickListener { view ->
                    val context = view?.context as MainActivity
                    demo.activity.let {
                        context.start(it, demo.layout)
                    }
                }
                executePendingBindings()
            }
        }
    }
}

data class Demo(
    val title: String,
    val description : String,
    val layout : Int = 0,
    val activity : Class<*> = DemoActivity::class.java) {

    constructor(title: String,
                description: String,
                activity : Class<*> = DemoActivity::class.java
    ) : this(title, description, 0, activity)
}