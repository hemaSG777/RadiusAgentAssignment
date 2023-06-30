package com.example.radiusagentassignment.Activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.radiusagentassignment.Adapters.FacilityAdapter
import com.example.radiusagentassignment.ExclutionData
import com.example.radiusagentassignment.Facility
import com.example.radiusagentassignment.FacilityData
import com.example.radiusagentassignment.Interface.Api
import com.example.radiusagentassignment.databinding.ActivityListFacilityBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFacilityActivity : AppCompatActivity() {
    private val binding by lazy { ActivityListFacilityBinding.inflate(layoutInflater) }
    private val facilityAdapter by lazy { FacilityAdapter(this, list, listOf(exclusions)) }

    private val list = ArrayList<Facility>()
    private val exclusions = ArrayList<ExclutionData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getData()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        binding.facilityRv.adapter = facilityAdapter
        binding.facilityRv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    private fun getData() {
        binding.progress.visibility = View.VISIBLE
        val apiInterface = Api.create().getData()
        apiInterface.enqueue(object : Callback<FacilityData> {
            override fun onResponse(call: Call<FacilityData>, response: Response<FacilityData>) {
                if (response.isSuccessful) {
                    binding.progress.visibility = View.GONE
                    val res = response.body()?.facilities
                    facilityAdapter.update(res!!)
                }
            }

            override fun onFailure(call: Call<FacilityData>, t: Throwable) {
                runOnUiThread {
                    Toast.makeText(
                        this@ListFacilityActivity,
                        t.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        })
    }
}
