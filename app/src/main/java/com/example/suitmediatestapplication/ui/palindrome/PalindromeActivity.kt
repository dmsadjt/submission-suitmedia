package com.example.suitmediatestapplication.ui.palindrome

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.DialogFragment
import com.example.suitmediatestapplication.R
import com.example.suitmediatestapplication.data.UserPreference
import com.example.suitmediatestapplication.databinding.ActivityPalindromeBinding
import com.example.suitmediatestapplication.ui.welcome.WelcomeActivity
import com.example.suitmedieatestapplication.ui.ViewModelFactory

class PalindromeActivity : AppCompatActivity(), PalindromeViewModel.PalindromeCallback {

    private val palindromeViewModel by viewModels<PalindromeViewModel> {
        ViewModelFactory(userPreference = UserPreference(this), this)
    }
    private lateinit var binding : ActivityPalindromeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPalindromeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        palindromeViewModel.palindromeCallback = this

        supportActionBar?.hide()

        binding.btnCheck.setOnClickListener{
            if (binding.edtPalindrome.text.isNullOrEmpty()){
                binding.edtPalindrome.error = "Palindrome text must not be null or empty"
            } else {
                binding.edtPalindrome.error = null
                palindromeViewModel.checkPalindrome(binding.edtPalindrome.text.toString())
            }
        }

        binding.btnNext.setOnClickListener {
            if(binding.edtName.text.isNullOrEmpty()) {
                binding.edtName.error = "Name must not be null or empty"
            } else {
                binding.edtName.error = null
                palindromeViewModel.saveUser(binding.edtName.text.toString())
                startActivity(Intent(this, WelcomeActivity::class.java))
                Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun palindromeCheck(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Palindrome Check")
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("Got it") { dialog, _ ->
            binding.edtPalindrome.setText("")
            dialog.dismiss()
        }

        val dialog = alertDialogBuilder.create()
        dialog.show()
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}