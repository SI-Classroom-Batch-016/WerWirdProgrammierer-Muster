package de.syntax_institut.werwirdprogrammierer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import de.syntax_institut.werwirdprogrammierer.R
import de.syntax_institut.werwirdprogrammierer.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentResultBinding

    /**
     * Lifecycle Funktion onCreateView
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Lifecycle Funktion onViewCreated
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvAmountWon.text = getString(R.string.you_won_amount, viewModel.moneyWon)
        if (viewModel.moneyWon > 0) {
            binding.tvResult.text = getString(R.string.game_won)
            binding.tvResultEmoji.text = getString(R.string.emoji_won)
        } else {
            binding.tvResult.text = getString(R.string.game_over)
            binding.tvResultEmoji.text = getString(R.string.emoji_lost)
        }

        binding.tvPlayAgain.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.tvExit.setOnClickListener {
            activity?.finish()
        }
    }
}