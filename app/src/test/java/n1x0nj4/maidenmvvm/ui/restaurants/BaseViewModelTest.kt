package n1x0nj4.maidenmvvm.ui.restaurants

import agency.andromeda.planetreads.util.RxSchedulersOverrideRule
import android.arch.core.executor.testing.InstantTaskExecutorRule
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