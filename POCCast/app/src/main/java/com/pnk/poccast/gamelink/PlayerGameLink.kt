package com.pnk.poccast.gamelink

import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONObject

class PlayerGameLink(private val gameListener: GameListener) {
    private var mSocket: Socket = IO.socket("https://cast.leoboyer.dev")

    init {
        mSocket.on("ready") {
            gameListener.onGameReady()
        }
    }

    fun setRoom(room: String) {
        mSocket.connect()
        val configData = ConfigObject(room, "player")
        val sendData = JSONObject(Gson().toJson(configData))

        mSocket.emit("config", sendData)
    }

    private class ConfigObject(val id: String, val type: String)

    companion object {
        private var instance: PlayerGameLink? = null
        fun getInstance(gameListener: GameListener): PlayerGameLink {
            if (instance == null) {
                instance = PlayerGameLink(gameListener)
            }
            return instance!!
        }
    }
}
