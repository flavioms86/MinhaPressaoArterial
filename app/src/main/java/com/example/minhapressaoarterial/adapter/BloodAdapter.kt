package com.example.minhapressaoarterial.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minhapressaoarterial.R
import com.example.minhapressaoarterial.model.BloodPressure

class BloodAdapter : RecyclerView.Adapter<BloodAdapter.BloodPressureViewHolder>() {

    private val list: MutableList<BloodPressure> = mutableListOf()

    class BloodPressureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sisResult: TextView = itemView.findViewById(R.id.tvSisResult)
        val diaResult: TextView = itemView.findViewById(R.id.tvDiaResult)
        val pulResult: TextView = itemView.findViewById(R.id.tvPulResult)
        val healthStatusImage: ImageView = itemView.findViewById(R.id.ivHealthStatus)


        fun bind(bloodpressure: BloodPressure) {
            sisResult.text = bloodpressure.sisPressure.toString()
            diaResult.text = bloodpressure.diaPressure.toString()
            pulResult.text = bloodpressure.diaPressure.toString()
            healthStatusImage.setImageResource(when(bloodpressure.healthStats) {
                "Nada bem" -> R.drawable.badhealthicon
                "Normal" -> R.drawable.normalhealthicon
                "Muito bem" -> R.drawable.goodhealthicon
                else -> R.drawable.ic_healtstatus
            })

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BloodPressureViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bloodpressure, parent, false)
        return BloodPressureViewHolder(view)
    }

    override fun onBindViewHolder(holder: BloodPressureViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}