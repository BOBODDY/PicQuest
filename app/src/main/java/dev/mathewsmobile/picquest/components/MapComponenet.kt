package dev.mathewsmobile.picquest.components

import android.preference.PreferenceManager
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView
import org.osmdroid.config.Configuration.*
import org.osmdroid.util.GeoPoint

@Composable
fun MapComponent() {
    val context = LocalContext.current

    AndroidView(modifier = Modifier.fillMaxSize(),
        factory = {
            getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context))

            val map = MapView(it)
            map.setTileSource(TileSourceFactory.MAPNIK)
            val mapController = map.controller
            mapController.setZoom(9.5)
            val startPoint = GeoPoint(48.8583, 2.2944)
            mapController.setCenter(startPoint)

            map
        })
}