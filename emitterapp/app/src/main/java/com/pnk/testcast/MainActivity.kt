package com.pnk.testcast

import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.FragmentActivity
import androidx.mediarouter.app.MediaRouteButton
import com.google.android.gms.cast.framework.CastButtonFactory
import com.google.android.gms.cast.framework.CastContext

class MainActivity : FragmentActivity() {
    private lateinit var mCastContext: CastContext

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mMediaRouteButton = findViewById<MediaRouteButton>(R.id.media_route_button)
        CastButtonFactory.setUpMediaRouteButton(applicationContext, mMediaRouteButton)

        mCastContext = CastContext.getSharedInstance(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main, menu)
        CastButtonFactory.setUpMediaRouteButton(applicationContext, menu, R.id.media_route_menu_item)
        return true
    }
}
