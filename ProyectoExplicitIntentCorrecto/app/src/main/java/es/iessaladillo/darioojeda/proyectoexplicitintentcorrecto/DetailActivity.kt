package es.iessaladillo.darioojeda.proyectoexplicitintentcorrecto

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import es.iessaladillo.darioojeda.proyectoexplicitintentcorrecto.databinding.DetailActivityBinding


class DetailActivity : AppCompatActivity() {
    lateinit var binding: DetailActivityBinding

    private var name: String = ""
    private var age: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentData()
    }

    private fun getIntentData() {
        if (intent == null || !intent.hasExtra(EXTRA_NAME) || !intent.hasExtra(EXTRA_AGE)) {
            throw RuntimeException(
                "DetailActivity needs to receive name and age as extras")
        }
        name = intent.getStringExtra(EXTRA_NAME).toString()
        age = intent.getIntExtra(EXTRA_AGE, 0)
    }

    companion object {

        const val EXTRA_NAME = "EXTRA_NAME"
        const val EXTRA_AGE = "EXTRA_AGE"

        fun newIntent(context: Context, name: String, age: Int) =
                Intent(context, DetailActivity::class.java)
                        .putExtras(bundleOf(EXTRA_NAME to name, EXTRA_AGE to age))

    }


}