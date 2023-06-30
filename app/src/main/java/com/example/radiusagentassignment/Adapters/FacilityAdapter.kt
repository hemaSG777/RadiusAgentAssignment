package com.example.radiusagentassignment.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.radiusagentassignment.ExclutionData
import com.example.radiusagentassignment.Facility
import com.example.radiusagentassignment.R

class FacilityAdapter(
    val context: Context,
    private var facilities: List<Facility>,
    private val exclusions: List<List<ExclutionData>>
) :
    RecyclerView.Adapter<FacilityAdapter.FacilityViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilityViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_facility, parent, false)
        return FacilityViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FacilityViewHolder, position: Int) {
        val facility = facilities[position]
        holder.bind(facility)
    }

    override fun getItemCount(): Int {
        return facilities.size
    }

    inner class FacilityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.name_tv)
        private val optionsLayout: LinearLayout = itemView.findViewById(R.id.optionsLayout)
        private val image: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(facility: Facility) {
            nameTextView.text = facility.name
            when (nameTextView.text) {
                "Property Type" -> {
                    Glide.with(context).load(R.drawable.property)
                        .apply(RequestOptions().centerCrop())
                        .into(image)
                }
                "Number of Rooms" -> {
                    Glide.with(context).load(R.drawable.noofrooms)
                        .apply(RequestOptions().centerCrop())
                        .into(image)
                }
                else -> {
                    Glide.with(context).load(R.drawable.otherfac)
                        .apply(RequestOptions().centerCrop())
                        .into(image)
                }
            }
            nameTextView.setOnClickListener {
                optionsLayout.visibility = View.VISIBLE
            }

            optionsLayout.removeAllViews()

            for (option in facility.options) {
                val optionView = LayoutInflater.from(itemView.context)
                    .inflate(R.layout.option_item, optionsLayout, false)

                val optionNameTextView: TextView = optionView.findViewById(R.id.optionNameTextView)
                val optionIconImageView: ImageView =
                    optionView.findViewById(R.id.optionIconImageView)

                optionNameTextView.text = option.name


                optionNameTextView.setOnClickListener {
                    if (facility.facility_id == "3" && optionNameTextView.text == "Garage") {
                        Toast.makeText(
                            context,
                            "Garage is not available for Boat House",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "You have selected ${option.name}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                val iconResId = getIconResource(option.icon)
                if (iconResId != 0) {
                    optionIconImageView.setImageResource(iconResId)
                }

                optionsLayout.addView(optionView)
            }
        }

        private fun getIconResource(iconName: String): Int {
            return when (iconName) {
                "apartment" -> R.drawable.apartment
                "condo" -> R.drawable.condo
                "boat" -> R.drawable.boat
                "land" -> R.drawable.land
                "rooms" -> R.drawable.rooms
                "no-room" -> R.drawable.noroom
                "swimming" -> R.drawable.swimming
                "garden" -> R.drawable.garden
                "garage" -> R.drawable.garage
                else -> 0
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(lists: List<Facility>) {
        facilities = lists
        notifyDataSetChanged()
    }

}