package n1x0nj4.maidenmvvm.ui.browse

import agency.andromeda.planetreads.util.RxSchedulersOverrideRule
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
abstract class BaseViewModelTest() {

    @Rule
    @JvmField
    var testSchedulerRule = RxSchedulersOverrideRule()

    @get: Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    abstract fun setUp()
}