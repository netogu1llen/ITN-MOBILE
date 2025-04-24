package com.app.soffyapp.domain.model

data class SudokuResponse(
    val puzzle: List<List<Int>>,
    val solution: List<List<Int>>
)
