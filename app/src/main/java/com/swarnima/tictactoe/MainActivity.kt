package com.swarnima.tictactoe

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.swarnima.tictactoe.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var gameLogic: GameLogic

    private lateinit var buttons: Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        gameLogic= GameLogic()

        buttons= arrayOf(
            arrayOf(binding.btn00, binding.btn01, binding.btn02),
            arrayOf(binding.btn10, binding.btn11, binding.btn12),
            arrayOf(binding.btn20, binding.btn21, binding.btn22)
        )

        for(row in 0..2){
            for(col in 0..2){
                buttons[row][col].setOnClickListener {
                    onCellClicked(row, col)
                }
            }
        }
        binding.btnReset.setOnClickListener {
            resetGame()
                }
            }
        private fun onCellClicked(row: Int, col: Int) {
            if (!gameLogic.makeMove(row, col)) return

            //update button text
            val mark = gameLogic.currPlayer
            buttons[row][col].text = mark
            buttons[row][col].setTextColor(
                if (mark == "X") {
                    getColor(R.color.black)
                } else {
                    getColor(R.color.white)
                }
            )
            //check for winner
            val winner = gameLogic.checkWinner()
            if (winner != null) {
                val playerName = if (winner == "X") "Player 1" else "Player 2"
                showDialog("$playerName Won!")
                return
            }
            //check for draw
            if (gameLogic.checkDraw()) {
                showDialog("It's a Draw!")
                return
            }
            gameLogic.switchPlayer()
            val nextPlayer = if (gameLogic.currPlayer == "X") "Player 1" else "Player 2"
            binding.tvStatus.text = "Next Player: $nextPlayer"
        }
            private fun showDialog(message: String){
                AlertDialog.Builder(this).setTitle("Game Over").setMessage(message)
                    .setCancelable(false)
                    .setPositiveButton("Play Again"){ dialog, _ ->
                        dialog.dismiss()
                        resetGame()
                    }.show()
            }

    private fun resetGame(){
        gameLogic.resetGame()
        binding.tvStatus.text ="Player 1's Turn"
        for(row in 0..2){
            for(col in 0..2){
                buttons[row][col].text = ""
                buttons[row][col].setTextColor(getColor(R.color.black))
            }
        }
    }
}


