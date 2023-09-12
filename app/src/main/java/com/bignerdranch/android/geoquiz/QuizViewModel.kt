package com.bignerdranch.android.geoquiz

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val IS_CHEATER_KEY = "IS_CHEATER_KEY"

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val questionBank = listOf(
        Question(R.string.question_ankylosaurus, false),
        Question(R.string.question_quetzal, true),
        Question(R.string.question_velociraptor, true),
        Question(R.string.question_tyrannosaurus, false),
        Question(R.string.question_herbivore, false),
        Question(R.string.question_birds, true)
    )

    var isCheater: Boolean get() = savedStateHandle.get(IS_CHEATER_KEY) ?: false
        set(value) = savedStateHandle.set(
        IS_CHEATER_KEY, value)

    private var currentIndex: Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    val currentQuestionAnswer: Boolean get() = questionBank[currentIndex].answer
    val currentQuestionText: Int get() = questionBank[currentIndex].textResId

    fun moveToPrev() = // prevent out of bounds array situation
        if (currentIndex == 0) {
            currentIndex = questionBank.size - 1
        }

        else {
            currentIndex = (currentIndex - 1) % questionBank.size
        }

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }


}