package com.example.trabalhoaeroporto.api

import com.google.gson.annotations.SerializedName

data class Voo(
    @SerializedName("flight_date")
    val flightDate: String?,

    @SerializedName("flight_status")
    val flightStatus: String?,

    @SerializedName("departure")
    val departure: Departure?,

    @SerializedName("arrival")
    val arrival: Arrival?,

    @SerializedName("airline")
    val airline: Airline?,

    @SerializedName("flight")
    val flight: Flight?
)

data class Departure(
    @SerializedName("airport")
    val airport: String?,

    @SerializedName("timezone")
    val timezone: String?,

    @SerializedName("iata")
    val iata: String?,

    @SerializedName("scheduled")
    val scheduled: String?,

    @SerializedName("estimated")
    val estimated: String?,

    @SerializedName("terminal")
    val terminal: String?,

    @SerializedName("gate")
    val gate: String?
)

data class Arrival(
    @SerializedName("airport")
    val airport: String?,

    @SerializedName("timezone")
    val timezone: String?,

    @SerializedName("iata")
    val iata: String?,

    @SerializedName("scheduled")
    val scheduled: String?,

    @SerializedName("estimated")
    val estimated: String?,

    @SerializedName("terminal")
    val terminal: String?,

    @SerializedName("gate")
    val gate: String?
)

data class Airline(
    @SerializedName("name")
    val name: String?,

    @SerializedName("iata")
    val iata: String?
)

data class Flight(
    @SerializedName("number")
    val number: String?,

    @SerializedName("iata")
    val iata: String?
)