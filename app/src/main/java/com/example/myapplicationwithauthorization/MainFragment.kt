package com.example.myapplicationwithauthorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplicationwithauthorization.databinding.FragmentMainBinding
import com.example.myapplicationwithauthorization.viewmodel.MainViewModel
import com.example.myapplicationwithauthorization.viewmodel.QuestionData
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeDetails()
        viewModel.retrieveDetails()

        binding.buttonSetQuestion.setOnClickListener {
            viewModel.retrieveDetails()
            binding.textAnswer.visibility = View.INVISIBLE
        }

        binding.buttonShowAnswer.setOnClickListener {
            binding.textAnswer.visibility = View.VISIBLE
        }
    }

    private fun observeDetails() {

        viewModel.details.observe(viewLifecycleOwner) {
            setQuestion(it)
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Snackbar.make(
                binding.fragmentMain,
                "Error: $it",
                Snackbar.LENGTH_INDEFINITE
            ).setAction("Retry") { observeDetails() }.show()
        }
    }

    private fun setQuestion(question: QuestionData) {
        binding.textCategory.text = getString(
            R.string.category,
            question.firstOrNull()?.category ?: "-"
        )
        binding.textQuestion.text = getString(
            R.string.question,
            question.firstOrNull()?.question ?: "-"
        )
        binding.textAnswer.text = getString(
            R.string.answer,
            question.firstOrNull()?.answer ?: "-"
        )
    }

}