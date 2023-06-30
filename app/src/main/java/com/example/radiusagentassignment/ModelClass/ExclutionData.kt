package com.example.radiusagentassignment

data class Exclution(
    val exclusions: List<List<ExclutionData>>
)

data class ExclutionData(
    val facilityId: String,
    val optionsId: String

)
