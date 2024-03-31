package com.relio.nbahof

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import coil.load

class PlayerDetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PLAYER = "extra_player"
    }

    private lateinit var ppgTextView: TextView
    private lateinit var astTextView: TextView
    private lateinit var rpgTextView: TextView
    private lateinit var playerImageView: ImageView
    private lateinit var playerNameTextView: TextView
    private lateinit var playerDescriptionTextView: TextView
    private lateinit var playerPositionTextView: TextView
    private lateinit var shareButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_details)

        ppgTextView = findViewById(R.id.ppg_value_tv)
        rpgTextView = findViewById(R.id.rpg_value_tv)
        astTextView = findViewById(R.id.ast_value_tv)
        playerImageView = findViewById(R.id.player_image_view)
        playerNameTextView = findViewById(R.id.player_name_tv)
        playerPositionTextView = findViewById(R.id.player_position_tv)
        playerDescriptionTextView = findViewById(R.id.player_description_value_tv)
        shareButton = findViewById(R.id.share_button)

        val player = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_PLAYER, Player::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_PLAYER)
        }

        if (player != null) {
            title = player.name
            ppgTextView.text = player.ppg.toString()
            astTextView.text = player.ast.toString()
            rpgTextView.text = player.rpg.toString()
            playerImageView.load(player.imageUrl)
            playerNameTextView.text = player.name
            playerDescriptionTextView.text = player.description
            playerPositionTextView.text = player.position

            shareButton.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT, player.imageUrl)
                intent.type = "text/plain"
                startActivity(intent)
            }
        }

    }
}