package es.iessaladillo.darioojeda.proyectoexplicitintentcorrecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import es.iessaladillo.darioojeda.proyectoexplicitintentcorrecto.SoftInputUtils.hideSoftKeyboard
import es.iessaladillo.darioojeda.proyectoexplicitintentcorrecto.databinding.MainActivityBinding

private const val EXTRA_NAME = "EXTRA_NAME"
private const val EXTRA_AGE = "EXTRA_AGE"

class MainActivity : AppCompatActivity() {
    lateinit var binding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }

    private fun setupViews() {
        binding.txtAge.setOnEditorActionListener { _, _, _ ->
            btnShowOnClick()
            true
        }
        binding.btnShow.setOnClickListener { btnShowOnClick() }
    }

    private fun btnShowOnClick() {
        val name = binding.txtName.text.toString()
        val age = binding.txtAge.text.toString()
        if (isValidForm(name, age)) {
            binding.txtName.hideSoftKeyboard()
            navigateToDetailActivity(name, age.toInt())
        }
    }

    private fun isValidForm(name: String, ageText: String): Boolean {
        if (!validateName(name)) {
            binding.txtName.requestFocus()
            return false
        }
        if (!validateAge(ageText)) {
            binding.txtAge.requestFocus()
            return false
        }
        return true
    }


    private fun validateName(name: String): Boolean {
        return if (isValidName(name)) {
            binding.txtName.error = null
            true
        } else {
            binding.txtName.error = getString(R.string.main_invalid_name)
            false
        }
    }

    private fun validateAge(ageText: String): Boolean {
        return if (isValidAge(ageText)) {
            binding.txtAge.error = null
            true
        } else {
            binding.txtAge.error = getString(R.string.main_invalid_age)
            false
        }
    }

    private fun isValidName(name: String): Boolean = name.isNotEmpty()

    private fun isValidAge(ageText: String): Boolean {
        val age = ageText.toIntOrNull() ?: return false
        val validRange = 1..130
        return validRange.contains(age)
    }

    private fun navigateToDetailActivity(name: String, age: Int) {
        val intent = DetailActivity.newIntent(this, name, age)
        startActivity(intent)
    }


}