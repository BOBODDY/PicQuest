package dev.mathewsmobile.picquest.components

import android.preference.PreferenceManager
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.res.ResourcesCompat
import dev.mathewsmobile.picquest.R
import dev.mathewsmobile.picquest.data.model.Quest
import org.osmdroid.config.Configuration.getInstance
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.ItemizedIconOverlay.OnItemGestureListener
import org.osmdroid.views.overlay.OverlayItem

@Composable
fun MapComponent(quests: List<Quest>) {
    val context = LocalContext.current

    AndroidView(modifier = Modifier.fillMaxSize(),
        factory = {
            getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context))

            val map = MapView(it)
            map.configureMap(quests)

            val mapController = map.controller
            val startPoint = GeoPoint(48.8583, 2.2944)
            mapController.setZoom(9.5)
            mapController.setCenter(startPoint)

            map
        })
}

fun MapView.configureMap(quests: List<Quest>) {
    setMultiTouchControls(true)
    setTileSource(TileSourceFactory.MAPNIK)

    val items = quests.map {
        OverlayItem(it.name, it.description ?: "", GeoPoint(it.location.latitude, it.location.longitude))
    }
    val overlay = ItemizedIconOverlay(
        items,
        ResourcesCompat.getDrawable(this.context.resources, R.drawable.red_marker, null),
        object : OnItemGestureListener<OverlayItem> {
            override fun onItemSingleTapUp(index: Int, item: OverlayItem?): Boolean {
                // TODO("Not yet implemented")
                return true
            }

            override fun onItemLongPress(index: Int, item: OverlayItem?): Boolean {
                return true
            }

        },
        this.context
    )

    overlays.add(overlay)
}