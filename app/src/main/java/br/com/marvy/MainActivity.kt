package br.com.marvy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.marvy.viewmodel.CharactersViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mCharactersViewModel: CharactersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}