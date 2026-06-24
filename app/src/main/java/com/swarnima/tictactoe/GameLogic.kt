package com.swarnima.tictactoe

class GameLogic {

    val board = Array(3) { Array(3) { "" } }
    var currPlayer = "X"

    fun checkWinner(): String? {
        for (row in 0..2) {
            if (board[row][0] == board[row][1] &&
                board[row][1] == board[row][2] &&
                board[row][0].isNotEmpty()
            ) {
                return board[row][0]
            }
        }
        //Check columns
        for (col in 0..2) {
            if (board[0][col] == board[1][col] &&
                board[1][col] == board[2][col] &&
                board[0][col].isNotEmpty()
            ) {
                return board[0][col]
            }
        }
        // Check diagonal top-left to bottom-right
        if (board[0][0] == board[1][1] &&
            board[1][1] == board[2][2] &&
            board[0][0].isNotEmpty()
        ) {
            return board[0][0]
        }

        // Check diagonal top-right to bottom-left
        if (board[0][2] == board[1][1] &&
            board[1][1] == board[2][0] &&
            board[0][2].isNotEmpty()
        ) {
            return board[0][2]
        }
        return null //No winner yet
    }
        //Checking if the board is full
        fun checkDraw(): Boolean {
            for (row in board){
                for (cell in row){
                    if (cell.isEmpty()) return false
                }
            }
            return true
        }

    //Place a mark on the board

    fun makeMove(row:Int, col:Int):Boolean{
        if(board[row][col].isEmpty()){
            board[row][col] = currPlayer
            return true
        }
            return false
        }
        //Switch players
        fun switchPlayer(){
            currPlayer = if(currPlayer == "X") "O" else "X"
        }

    //Reset everything
    fun resetGame(){
        for(row in 0..2){
            for(col in 0..2){
                board[row][col] = ""
            }
        }
        currPlayer= "X"
    }
}

