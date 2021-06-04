package ru.varasoft.popularlibs

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.varasoft.popularlibs.databinding.ActivityMainBinding

class MainActivity : MvpAppCompatActivity(), MainView {
    private var vb: ActivityMainBinding? = null
    private val presenter by moxyPresenter { MainPresenter(CountersModel())}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        val listener = View.OnClickListener {
            when (it.id) {
                R.id.btn_counter1 -> presenter.counterOneClick()
                R.id.btn_counter2 -> presenter.counterTwoClick()
                R.id.btn_counter3 -> presenter.counterThreeClick()
            }
        }

        vb?.btnCounter1?.setOnClickListener(listener)
        vb?.btnCounter2?.setOnClickListener(listener)
        vb?.btnCounter3?.setOnClickListener(listener)
    }

    override fun setButtonOneText(text: String) {
            vb?.btnCounter1?.text = text
    }

    override fun setButtonTwoText(text: String) {
        vb?.btnCounter2?.text = text
    }

    override fun setButtonThreeText(text: String) {
        vb?.btnCounter3?.text = text
    }

}
