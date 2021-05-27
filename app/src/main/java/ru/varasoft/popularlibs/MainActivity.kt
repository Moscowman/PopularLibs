package ru.varasoft.popularlibs

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.varasoft.popularlibs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {
    private var vb: ActivityMainBinding? = null
    val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        val listener = View.OnClickListener {
            when (it.id) {
                R.id.btn_counter1 -> presenter.counterClick(0)
                R.id.btn_counter2 -> presenter.counterClick(1)
                R.id.btn_counter3 -> presenter.counterClick(2)
            }
        }

        vb?.btnCounter1?.setOnClickListener(listener)
        vb?.btnCounter2?.setOnClickListener(listener)
        vb?.btnCounter3?.setOnClickListener(listener)
    }

    override fun setButtonText(index: Int, text: String) {
        when (index) {
            0 -> vb?.btnCounter1?.text = text
            1 -> vb?.btnCounter2?.text = text
            2 -> vb?.btnCounter3?.text = text
        }
    }
}
