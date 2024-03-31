package com.relio.nbahof

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var playerList: ArrayList<Player>
    private lateinit var playerRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        playerList = getPlayersData()
        playerRecyclerView = findViewById(R.id.player_rc_view)
        playerRecyclerView.setHasFixedSize(true)
        playerRecyclerView.layoutManager = LinearLayoutManager(this)
        val playerAdapter = PlayerCardAdapter(playerList) { player ->
           onClickCard(player)
        }
        playerRecyclerView.adapter = playerAdapter
    }

    private fun onClickCard(player: Player) {
        val intent = Intent(this@MainActivity, PlayerDetailsActivity::class.java)
        intent.putExtra(PlayerDetailsActivity.EXTRA_PLAYER, player)
        startActivity(intent)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.about_page -> {
                val intent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getPlayersData(): ArrayList<Player> {
        val playersName = resources.getStringArray(R.array.player_name)
        val playersPpgRaw = resources.getStringArray(R.array.player_ppg)
        val playersAstRaw = resources.getStringArray(R.array.player_ast)
        val playersRpgRaw = resources.getStringArray(R.array.player_rpg)
        val playersDescription = resources.getStringArray(R.array.player_description)
        val playersPosition = resources.getStringArray(R.array.player_position)
        val playersPpg = playersPpgRaw.map { it.toDouble() }.toDoubleArray()
        val playersRpg = playersRpgRaw.map { it.toDouble() }.toDoubleArray()
        val playersAst = playersAstRaw.map { it.toDouble() }.toDoubleArray()
        val playerImgUrl = resources.getStringArray(R.array.player_url)
        val playerTeam = resources.getStringArray(R.array.player_team)

        val playerList = ArrayList<Player>()
        for (i in playersName.indices) {
            val player = Player(
                name = playersName[i],
                description = playersDescription[i],
                ast = playersAst[i],
                rpg = playersRpg[i],
                ppg = playersPpg[i],
                position = playersPosition[i],
                imageUrl = playerImgUrl[i],
                playerTeam = playerTeam[i],
            )
            playerList.add(player)
        }
        return playerList
    }
}
