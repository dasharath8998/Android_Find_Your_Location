package gurukul.com.googlelocation

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sFrag : SupportMapFragment = supportFragmentManager.findFragmentById(R.id.fragMap) as SupportMapFragment
        sFrag.getMapAsync(object: OnMapReadyCallback{
            @SuppressLint("MissingPermission")
            override fun onMapReady(p0: GoogleMap?) {
                var lManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
                lManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000L,1F,
                    object: LocationListener{
                        override fun onLocationChanged(location: Location?) {
                            var lat = location?.latitude
                            var lang = location?.longitude

                            var option = MarkerOptions()
                            option.position(LatLng(lat!!,lang!!))
                            option.icon(BitmapDescriptorFactory.fromResource(R.drawable.moter))
                            p0?.addMarker(option)
                            p0?.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lat,lang),17F))

                        }

                        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

                        }

                        override fun onProviderEnabled(provider: String?) {

                        }

                        override fun onProviderDisabled(provider: String?) {

                        }

                    })
            }
        })
    }
}
