package de.syntax_institut.werwirdprogrammierer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.syntax_institut.werwirdprogrammierer.data.AppRepository
import de.syntax_institut.werwirdprogrammierer.data.model.Question

class SharedViewModel : ViewModel() {

    private val appRepository = AppRepository()
    private val questions = appRepository.questions
    private var currentQuestionIndex = 0

    private var _moneyWon = MutableLiveData(0)
    val moneyWon: LiveData<Int>
        get() = _moneyWon

    private var _currentQuestion = MutableLiveData(questions.first())
    val currentQuestion: LiveData<Question>
        get() = _currentQuestion

    private var _gameOver = MutableLiveData(false)
    val gameOver: LiveData<Boolean>
        get() = _gameOver

    fun checkAnswer(answerIndex: Int) {
        if (_currentQuestion.value?.rightAnswer == answerIndex) {
            _moneyWon.value = _currentQuestion.value?.price
            nextRound()
        } else {
            _moneyWon.value = 0
            endGame()
        }
    }

    fun restartGame(){
        _gameOver.value = false
        currentQuestionIndex = 0
        _moneyWon.value = 0
        _currentQuestion.value = questions.first()
    }

    private fun nextRound() {
        currentQuestionIndex++
        if (currentQuestionIndex >= questions.size) {
            endGame()
        } else {
            _currentQuestion.value = questions[currentQuestionIndex]
        }
    }

    private fun endGame(){
        _gameOver.value = true
    }
}