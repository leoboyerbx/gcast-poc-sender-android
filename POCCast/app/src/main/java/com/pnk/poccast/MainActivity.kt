package com.pnk.poccast

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.mediarouter.app.MediaRouteButton
import com.google.android.gms.cast.framework.CastButtonFactory
import com.google.android.gms.cast.framework.CastContext
import com.google.android.gms.cast.framework.CastSession
import com.google.android.gms.cast.framework.SessionManagerListener
import com.pnk.poccast.gamelink.GameListener
import com.pnk.poccast.gamelink.PlayerGameLink
import java.util.*

class MainActivity : AppCompatActivity(), NavigationListener, GameListener,SessionManagerListener<CastSession> {
    private lateinit var mCastContext: CastContext
    private lateinit var mMediaRouteButton: MediaRouteButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        mCastContext = CastContext.getSharedInstance(this)
        showFragment(WaitingFragment())
    }

    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
        }.commit()
    }

    override fun onResume() {
        super.onResume()
        mCastContext.sessionManager.addSessionManagerListener(this, CastSession::class.java)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_main, menu)
        CastButtonFactory.setUpMediaRouteButton(applicationContext, menu, R.id.media_route_menu_item)
        return true
    }

    override fun onSessionStarting(p0: CastSession?) {
    }

    override fun onSessionStarted(p0: CastSession?, p1: String?) {
        val session = CastContext.getSharedInstance()!!.sessionManager.currentCastSession
        val id = UUID.randomUUID()
        session.sendMessage("urn:x-cast:com.pnk.poccast", "{\"type\":\"syncID\",\"text\":\"${id}\"}")

        PlayerGameLink.getInstance(this).setRoom(id.toString())
    }

    override fun onSessionStartFailed(p0: CastSession?, p1: Int) {
    }

    override fun onSessionEnding(p0: CastSession?) {
    }

    override fun onSessionEnded(p0: CastSession?, p1: Int) {
    }

    override fun onSessionResuming(p0: CastSession?, p1: String?) {
    }

    override fun onSessionResumed(p0: CastSession?, p1: Boolean) {
    }

    override fun onSessionResumeFailed(p0: CastSession?, p1: Int) {
    }

    override fun onSessionSuspended(p0: CastSession?, p1: Int) {
    }

    override fun onGameReady() {
        showFragment(GameFragment())
    }
}
