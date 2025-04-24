package com.app.soffyapp.data.remote.api

interface SudokuApiService {

    @GET("sudoku")
    suspend fun getSudokuPuzzle(
        @Query("difficulty") difficulty: String = "easy",
        @Query("size") size: Int = 9
    ): Response<SudokuResponse>
}
