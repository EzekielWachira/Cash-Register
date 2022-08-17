package com.ezzy.cashapp.ui

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ezzy.cashapp.R
import com.ezzy.cashapp.data.model.CashEntry
import com.ezzy.cashapp.databinding.ActivityMainBinding
import com.ezzy.cashapp.utils.gone
import com.ezzy.cashapp.utils.visible
import dagger.hilt.android.AndroidEntryPoint

const val MAX_AMOUNT = 1000000

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var numberString = ""

    private val cashEntryViewModel: CashEntryViewModel by viewModels()
    private val cashEntryAdapter: CashEntryAdapter by lazy { CashEntryAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        setUpUi()
        initUiState()
    }

    private fun setUpRecyclerView() {
        binding.bottomSection.entriesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity).apply {
                reverseLayout = true
            }
            adapter = cashEntryAdapter
        }
    }

    private fun initUiState() {
        cashEntryViewModel.cashEntries.observe(this) { entries ->
            cashEntryAdapter.submitList(entries)
            if (entries.isEmpty()) {
                binding.bottomSection.layoutNoData.visible()
            } else binding.bottomSection.layoutNoData.gone()
        }

        cashEntryViewModel.totalEntriesAmount.observe(this) { total ->
            binding.bottomSection.total.text =
                getString(
                    R.string.amt,
                    total ?: 0f
                )
        }
    }

    private fun setUpUi() {
        with(binding) {

            text1.setOnClickListener { listenCharacterChanges(it as TextView) }
            text2.setOnClickListener { listenCharacterChanges(it as TextView) }
            text3.setOnClickListener { listenCharacterChanges(it as TextView) }
            text4.setOnClickListener { listenCharacterChanges(it as TextView) }
            text5.setOnClickListener { listenCharacterChanges(it as TextView) }
            text6.setOnClickListener { listenCharacterChanges(it as TextView) }
            text7.setOnClickListener { listenCharacterChanges(it as TextView) }
            text8.setOnClickListener { listenCharacterChanges(it as TextView) }
            text9.setOnClickListener { listenCharacterChanges(it as TextView) }
            text0.setOnClickListener { listenCharacterChanges(it as TextView) }
            textDelete.setOnClickListener { clearCharacters() }

            textAdd.setOnClickListener { saveEntry() }
        }
    }

    /**
     * Save the amount to the local database
     */
    private fun saveEntry() {
        //Check if number string is empty before saving it to local database
        if (numberString.isEmpty()) return

        //Check if string code is less that 0 or greater than 1000000
        if (numberString.toFloat() <= 0f && numberString.toFloat() > MAX_AMOUNT) {
            return
        }

        if (numberString.toFloat() == 0f) return

        val entry = CashEntry(amount = numberString.toFloat())
        cashEntryViewModel.addEntry(entry)
        clearAllCharacters()
    }

    /**
     * listen when user clicks on a button to get the string code
     * for the clicked button
     */
    private fun listenCharacterChanges(button: TextView) {
        //Make sure the number string is not greater than 1000000
        if (numberString.isNotEmpty() && numberString.toFloat() > MAX_AMOUNT) {
            return
        }

        getStringCode(button)

        //if number string is 0, clear it since we cannot save 0 values
        if (numberString.toFloat() == 0f) {
            numberString = emptyString
            return
        }

        if (numberString.toFloat() > MAX_AMOUNT) {
            numberString = emptyString
            binding.topSection.numberField.setText(
                getString(
                    R.string.amt,
                    0f
                )
            )
            return
        }

        if (numberString.toFloat() > 0 && numberString.toFloat() <= MAX_AMOUNT) {
            binding.topSection.numberField.setText(getString(R.string.amt, numberString.toFloat()))
        } else return
    }

    /**
     * Get the string code for the clicked number button
     */
    private fun getStringCode(button: TextView) {
        when (button.id) {
            R.id.text0 -> numberString += "0"
            R.id.text1 -> numberString += "1"
            R.id.text2 -> numberString += "2"
            R.id.text3 -> numberString += "3"
            R.id.text4 -> numberString += "4"
            R.id.text5 -> numberString += "5"
            R.id.text6 -> numberString += "6"
            R.id.text7 -> numberString += "7"
            R.id.text8 -> numberString += "8"
            R.id.text9 -> numberString += "9"
        }
    }

    /**
     * Remove the last character from the number string
     * (The right most character / Last character added)
     * on pressing delete button
     */
    private fun clearCharacters() {
        if (numberString.isNotEmpty()) {
            numberString = removeLastChar(numberString)
            binding.topSection.numberField.setText(
                if (numberString.isEmpty()) {
                    getString(
                        R.string.amt,
                        0f
                    )
                } else {
                    getString(
                        R.string.amt,
                        numberString.toFloat()
                    )
                }
            )
        }
    }

    /**
     * Remove the last character from the number string
     * (The right most character / Last character added)
     * on pressing delete button
     */
    private fun removeLastChar(s: String?): String {
        if (s == null || s.isEmpty()) {
            return s!!
        }
        return s.substring(0, s.length - 1)
    }

    /**
     * Clear all characters from the created number string
     */
    private fun clearAllCharacters() {
        if (numberString.isNotEmpty()) {
            for (str in numberString.indices) {
                numberString = removeLastChar(numberString)
                binding.topSection.numberField.setText(
                    getString(
                        R.string.amt,
                        0f
                    )
                )
            }
        }
    }

    private val emptyString = ""
}

