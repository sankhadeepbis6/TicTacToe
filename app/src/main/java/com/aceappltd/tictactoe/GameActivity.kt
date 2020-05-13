package com.aceappltd.tictactoe

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.aceappltd.tictactoe.game.Board
import com.aceappltd.tictactoe.game.Opponent

class GameActivity : AppCompatActivity() {

    private var imagePosition0: ImageView? = null
    private var imagePosition1: ImageView? = null
    private var imagePosition2: ImageView? = null
    private var imagePosition3: ImageView? = null
    private var imagePosition4: ImageView? = null
    private var imagePosition5: ImageView? = null
    private var imagePosition6: ImageView? = null
    private var imagePosition7: ImageView? = null
    private var imagePosition8: ImageView? = null

    private val gameMode = "swap"
    private val pvc = "pvc"
    private val pvp = "pvp"

    private var mode: String? = null
    private var turn = 0

    var board: Board? = null
    var opponent: Opponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        imagePosition0 = findViewById<ImageView>(R.id.position_0)
        imagePosition1 = findViewById<ImageView>(R.id.position_1)
        imagePosition2 = findViewById<ImageView>(R.id.position_2)
        imagePosition3 = findViewById<ImageView>(R.id.position_3)
        imagePosition4 = findViewById<ImageView>(R.id.position_4)
        imagePosition5 = findViewById<ImageView>(R.id.position_5)
        imagePosition6 = findViewById<ImageView>(R.id.position_6)
        imagePosition7 = findViewById<ImageView>(R.id.position_7)
        imagePosition8 = findViewById<ImageView>(R.id.position_8)

        val intent = intent
        mode = intent.getStringExtra(gameMode)

        board = Board('X', 'O')
        opponent = Opponent()

    }

    fun gameTurn(view: View){

        when(view.id){
            R.id.position_0 -> gameTurnResponse(0, view)
            R.id.position_1 -> gameTurnResponse(1, view)
            R.id.position_2 -> gameTurnResponse(2, view)
            R.id.position_3 -> gameTurnResponse(3, view)
            R.id.position_4 -> gameTurnResponse(4, view)
            R.id.position_5 -> gameTurnResponse(5, view)
            R.id.position_6 -> gameTurnResponse(6, view)
            R.id.position_7 -> gameTurnResponse(7, view)
            R.id.position_8 -> gameTurnResponse(8, view)
        }

    }

    private fun gameTurnResponse(index: Int, view: View){

        Log.d("game", mode.toString())
        Log.d("game", "Turn $turn")

        if(board!!.spotAvailable(index)) {
            if (turn == 0) {
                Log.d("game", "Turn $turn")
                view.setBackgroundResource(R.drawable.cross_2)
                board?.newPiece('X', index)
                if (mode == pvp)
                    turn = 1
            } else {
                view.setBackgroundResource(R.drawable.zero_2)
                board?.newPiece('O', index)
                turn = 0
            }

            Log.d("game", "X - " + board!!.isWinner('X').toString())
            Log.d("game", "O - " + board!!.isWinner('O').toString())

            if (board!!.isWinner('X')) {
                Log.d("game", "X won")
                gameEndDialog(1)
            } else if (board!!.isWinner('O')) {
                Log.d("game", "O won")
                gameEndDialog(-1)
            }

            if (mode == pvc) {
                val position = opponent!!.getMove(board)
                Log.d("game", "Computer move $position")
                if (position == -1)
                    gameEndDialog(0)
                else{
                    setComputerTurn(position)
                    board?.newPiece('O', position)
                }
                if (board!!.isWinner('O')) {
                    Log.d("game", "O won")
                    Handler().postDelayed({gameEndDialog(-1)}, 500)
                }
            }
        }

    }

    private fun gameEndDialog(winner: Int){

        Log.d("game", "Dialog called")
        var winnerMessage: String? = null
        when(winner){
            1 -> winnerMessage = "Congratulations X has won"
            -1 -> winnerMessage = "Congratulations O has won"
            0 -> winnerMessage = "Draw"
        }

        AlertDialog.Builder(this@GameActivity)
            .setIcon(R.drawable.ic_info_outline_blue_24dp)
            .setTitle("Game Ended")
            .setMessage(winnerMessage)
            .setPositiveButton("OK") {_, _ -> }
            .show()

        resetGame()
    }

    fun resetButtonListener(view: View){
        Log.d("game" , view.id.toString())
        resetGame()
    }

    private fun resetGame(){
        board?.reset()
        turn = 0

        imagePosition0?.setBackgroundResource(R.drawable.blank_box)
        imagePosition1?.setBackgroundResource(R.drawable.blank_box)
        imagePosition2?.setBackgroundResource(R.drawable.blank_box)
        imagePosition3?.setBackgroundResource(R.drawable.blank_box)
        imagePosition4?.setBackgroundResource(R.drawable.blank_box)
        imagePosition5?.setBackgroundResource(R.drawable.blank_box)
        imagePosition6?.setBackgroundResource(R.drawable.blank_box)
        imagePosition7?.setBackgroundResource(R.drawable.blank_box)
        imagePosition8?.setBackgroundResource(R.drawable.blank_box)
    }

    private fun setComputerTurn(position: Int){
        when(position) {
            0 -> imagePosition0?.setBackgroundResource(R.drawable.zero_2)
            1 -> imagePosition1?.setBackgroundResource(R.drawable.zero_2)
            2 -> imagePosition2?.setBackgroundResource(R.drawable.zero_2)
            3 -> imagePosition3?.setBackgroundResource(R.drawable.zero_2)
            4 -> imagePosition4?.setBackgroundResource(R.drawable.zero_2)
            5 -> imagePosition5?.setBackgroundResource(R.drawable.zero_2)
            6 -> imagePosition6?.setBackgroundResource(R.drawable.zero_2)
            7 -> imagePosition7?.setBackgroundResource(R.drawable.zero_2)
            8 -> imagePosition8?.setBackgroundResource(R.drawable.zero_2)
        }
    }

}
