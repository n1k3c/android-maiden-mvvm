package n1x0nj4.maidenmvvm.ui.common

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import n1x0nj4.maidenmvvm.R
import n1x0nj4.maidenmvvm.ui.greeting.GreetingFragment

private const val GREETING_FRAG_TAG = "greeting_frag"

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addFragment(mainContent.id, GREETING_FRAG_TAG, GreetingFragment())
    }

    override val contentViewResource: Int = R.layout.activity_main
}
