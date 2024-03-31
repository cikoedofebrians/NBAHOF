package com.relio.nbahof

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    val name: String,
    val description: String,
    val ppg: Double,
    val rpg: Double,
    val ast: Double,
    val position: String,
    val imageUrl: String,
    val playerTeam: String,
) : Parcelable
