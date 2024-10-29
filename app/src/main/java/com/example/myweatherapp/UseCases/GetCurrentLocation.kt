package com.example.myweatherapp.UseCases

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import com.example.myweatherapp.Composables.MyNavigation
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

object GetCurrentLocation {
    val getLatAndLon= MutableStateFlow(listOf("28.0871","30.7618"))

    @SuppressLint("MissingPermission")
    fun getLocation(context: Context):List<String>{
        val locManager=context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val  network_enabled = locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        var location: Location?=null
        if (network_enabled){
            location = locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)!!
        }
        if (location!=null){
            Log.d("LOC",location.longitude.toString())
            Log.d("LOC",location.latitude.toString())
            return listOf(location.latitude.toString(),
                location.longitude.toString())
        }
        else{
            return listOf("28.0871","30.7618")
        }
    }
}