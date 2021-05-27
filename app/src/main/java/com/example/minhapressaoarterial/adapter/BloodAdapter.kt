package com.example.minhapressaoarterial.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minhapressaoarterial.R
import com.example.minhapressaoarterial.model.BloodPressure

class BloodAdapter (context: Context,
                    bloodPressure: ArrayList<BloodPressure>,
                    listener: OnItemClickListener
) :
    RecyclerView.Adapter<BloodAdapter.BloodPressureViewHolder>(){
    private var listBloodPressure: List<BloodPressure> = bloodPressure
    private var listenerBloodPressure: OnItemClickListener = listener

    interface OnItemClickListener {
        fun onItemClick(bloodPressure: BloodPressure)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BloodPressureViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bloodpressure, parent, false)
        return BloodPressureViewHolder(view)
    }

    override fun onBindViewHolder(holder: BloodPressureViewHolder, position: Int) {
        val currentBloodPressure: BloodPressure = listBloodPressure[position]
        holder.bind(currentBloodPressure, listenerBloodPressure)

    }

    override fun getItemCount(): Int {
        return listBloodPressure.size
    }

    fun setBloodPressure(bloodPressure: List<BloodPressure>) {
        this.listBloodPressure = bloodPressure
        notifyDataSetChanged()
    }

    class BloodPressureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val sisResult: TextView = itemView.findViewById(R.id.tvSisResult)
        private val diaResult: TextView = itemView.findViewById(R.id.tvDiaResult)
        private val pulResult: TextView = itemView.findViewById(R.id.tvPulResult)
        private val healthStatusImage: ImageView = itemView.findViewById(R.id.ivHealthStatus)


        fun bind(bloodPressure: BloodPressure, listener: OnItemClickListener) {
            sisResult.text = bloodPressure.sisPressure.toString()
            diaResult.text = bloodPressure.diaPressure.toString()
            pulResult.text = bloodPressure.pulPressure.toString()
            healthStatusImage.setImageResource(when(bloodPressure.healthStats) {
                "Nada bem" -> R.drawable.badhealthicon
                "Normal" -> R.drawable.normalhealthicon
                "Muito bem" -> R.drawable.goodhealthicon
                else -> R.drawable.ic_healtstatus
            })

            itemView.setOnClickListener {
                listener.onItemClick(bloodPressure)
            }

        }

    }

}
