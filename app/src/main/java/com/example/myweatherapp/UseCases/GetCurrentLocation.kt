package com.example.myweatherapp.UseCases

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

object GetCurrentLocation {
    val getLatAndLon= MutableStateFlow(listOf("28.4173","30.7818"))
    @SuppressLint("MissingPermission")
    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    fun GetLocation(context: Context) {
        val fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(context)
        val locationState = rememberPermissionState(android.Manifest.permission.ACCESS_FINE_LOCATION)
        val lat_lon=ArrayList<String>()
        val requestPermissionLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()) {
                isGranted->
            if(isGranted){
                val location=fusedLocationProviderClient.lastLocation
                location.addOnSuccessListener {
                    if (it!=null){
                        Log.d("hany",it.latitude.toString())
                        Log.d("hany",it.longitude.toString())
                        lat_lon.add(0,it.latitude.toString())
                        lat_lon.add(1,it.longitude.toString())
                        getLatAndLon.update { lat_lon }
                    }
                }

            }
            else Log.d("hany","isDenied")

        }
        LaunchedEffect(locationState) {
            if (!locationState.status.isGranted ) {
                requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
        Log.d("hany",lat_lon.size.toString())
    }
}