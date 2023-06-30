package com.example.radiusagentassignment

class FacilityData(
    val facilities: ArrayList<Facility>
)

data class Facility(
    val facility_id: String,
    val name: String,
    val options: List<Option>
)

data class Option(
    val icon: String,
    val id: String,
    val name: String
)

