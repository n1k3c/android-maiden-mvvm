package n1x0nj4.maidenmvvm.ui.restaurants

import agency.andromeda.planetreads.util.RxSchedulersOverrideRule
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
abstract class BaseViewModelTest() {

    @Rule
    @JvmField
    var testSchedulerRule = RxSchedulersOverrideRule()

    @Before
    abstract fun setUp()
}