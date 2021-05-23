package com.example.minhapressaoarterial.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minhapressaoarterial.R
import com.example.minhapressaoarterial.model.BloodPressure

class BloodAdapter : RecyclerView.Adapter<BloodAdapter.BloodPressureViewHolder>(){

    private var list = emptyList<BloodPressure>()

    class BloodPressureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val sisResult: TextView = itemView.findViewById(R.id.tvSisResult)
        private val diaResult: TextView = itemView.findViewById(R.id.tvDiaResult)
        private val pulResult: TextView = itemView.findViewById(R.id.tvPulResult)
        private val healthStatusImage: ImageView = itemView.findViewById(R.id.ivHealthStatus)


        fun bind(bloodPressure: BloodPressure) {
            sisResult.text = bloodPressure.sisPressure.toString()
            diaResult.text = bloodPressure.diaPressure.toString()
            pulResult.text = bloodPressure.pulPressure.toString()
            healthStatusImage.setImageResource(when(bloodPressure.healthStats) {
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

    fun setBloodPressure(bloodPressure: List<BloodPressure>) {
        this.list = bloodPressure
        notifyDataSetChanged()
    }

}