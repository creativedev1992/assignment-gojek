package com.gojek.assignment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.gojek.assignment.repo.RPMRepositoryImpl
import com.gojek.assignment.util.Resource
import com.gojek.assignment.viewmodel.RPMViewModel
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


class RPMViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    private var mockVictoryRepository = RPMRepositoryImpl()

    private var viewModel: RPMViewModel? = null

    @Mock
    var observer: Observer<Resource<Any>>? = null


    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = RPMViewModel(mockVictoryRepository)
        observer?.let { viewModel?.rpmLiveData?.observeForever(it) }
    }

    @Test
    fun testNull() {
        `when`(mockVictoryRepository.getRPM()).thenReturn(null)
        assertNotNull(viewModel?.rpmLiveData)
        viewModel?.rpmLiveData?.hasObservers()?.let { assertTrue(it) }
    }

    @Test
    fun testApiFetchDataError() {
       /* val mockUtil = mock(Resource::class.java)
        `when`(mockVictoryRepository.getRPM())
            .thenReturn(null)
        viewModel?.rpmLiveData
        verify(observer)?.onChanged(mockUtil as Resource<Any>?)*/
      // verify(observer)?.onChanged(Resource.error("generic error",null))
    }
    @After
    @Throws(java.lang.Exception::class)
    fun tearDown() {
        viewModel = null
    }
}