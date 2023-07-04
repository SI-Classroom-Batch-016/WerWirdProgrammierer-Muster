package de.syntax_institut.werwirdprogrammierer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import de.syntax_institut.werwirdprogrammierer.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {

    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentQuizBinding

    /**
     * Lifecycle Funktion onCreateView
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentQuizBinding.inflate(inflater, container, false)
        viewModel.restartGame()
        return binding.root
    }

    /**
     * Lifecycle Funktion onViewCreated
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvAnswerA.setOnClickListener {
            viewModel.checkAnswer(1)
        }
        binding.tvAnswerB.setOnClickListener {
            viewModel.checkAnswer(2)
        }
        binding.tvAnswerC.setOnClickListener {
            viewModel.checkAnswer(3)
        }
        binding.tvAnswerD.setOnClickListener {
            viewModel.checkAnswer(4)
        }

        viewModel.currentQuestion.observe(viewLifecycleOwner){
            binding.tvQuestion.text = it.question
            binding.tvAnswerA.text = it.answerA
            binding.tvAnswerB.text = it.answerB
            binding.tvAnswerC.text = it.answerC
            binding.tvAnswerD.text = it.answerD
            binding.tvPrice.text = it.price.toString()
        }

        viewModel.gameOver.observe(viewLifecycleOwner){
            if (it){
                findNavController().navigate(QuizFragmentDirections.actionQuizFragmentToResultFragment())
            }
        }
    }
}