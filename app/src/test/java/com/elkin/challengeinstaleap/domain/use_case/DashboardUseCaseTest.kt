package com.elkin.challengeinstaleap.domain.use_case

import com.elkin.challengeinstaleap.core.util.Resource
import com.elkin.challengeinstaleap.data.remote.dto.ResponseDto
import com.elkin.challengeinstaleap.data.remote.service.ResponseWrapper
import com.elkin.challengeinstaleap.domain.model.Media
import com.elkin.challengeinstaleap.domain.repository.DashboardRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DashboardUseCaseTest {

    @MockK
    private lateinit var dashboardRepository: DashboardRepository
    private lateinit var dashboardUseCase: DashboardUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        dashboardUseCase = DashboardUseCase(dashboardRepository)
    }

    @Test
    fun `repository return network error`() = runBlocking {
        //Given
        val errorMessage = "Network error"
        val errorResponse =
            ResponseWrapper.NetworkError<ResponseDto<MutableList<Media>?>>(errorMessage)
        coEvery { dashboardRepository.getTrending() } returns errorResponse

        //When
        val result = mutableListOf<Resource<out MutableList<Media>>>()
        dashboardUseCase.getTrending().collect { result.add(it) }

        //Then
        assertEquals(3, result.size)

        val loadingResult = result[0]
        assertEquals(true, loadingResult.loading)

        val loadingResultFalse = result[1]
        assertEquals(false, loadingResultFalse.loading)

        val errorResult = result[2]
        assertEquals(errorMessage, errorResult.message)

        coVerify(exactly = 1) { dashboardRepository.getTrending() }
    }


    @Test
    fun `repository return server error`() = runBlocking {
        //Given
        val errorMessage = "Intenta más tarde"
        val errorResponse =
            ResponseWrapper.ServerError<ResponseDto<MutableList<Media>?>>(errorMessage)
        coEvery { dashboardRepository.getTrending() } returns errorResponse

        //When
        val result = mutableListOf<Resource<out MutableList<Media>>>()
        dashboardUseCase.getTrending().collect { result.add(it) }

        //Then
        assertEquals(3, result.size)

        val loadingResult = result[0]
        assertEquals(true, loadingResult.loading)

        val loadingResultFalse = result[1]
        assertEquals(false, loadingResultFalse.loading)

        val errorResult = result[2]
        assertEquals(errorMessage, errorResult.message)

        coVerify { dashboardRepository.getTrending() }
    }


    @Test
    fun `when repository dashboard return data null`() = runBlocking {
        //Given
        val successResponse =
            ResponseWrapper.Success<ResponseDto<MutableList<Media>?>>(ResponseDto(result = null))
        coEvery { dashboardRepository.getTrending() } returns successResponse

        //When
        val result = mutableListOf<Resource<out MutableList<Media>>>()
        dashboardUseCase.getTrending().collect { result.add(it) }

        //Then
        assertEquals(3, result.size)

        val loadingResult = result[0]
        assertEquals(true, loadingResult.loading)

        val loadingResultFalse = result[1]
        assertEquals(false, loadingResultFalse.loading)

        val response = result[2]
        assertEquals(0, response.data?.size)

        coVerify { dashboardRepository.getTrending() }
    }

    @Test
    fun `when repository dashboard return data empty`() = runBlocking {
        //Given
        val expectedData = mutableListOf<Media>()
        val successResponse =
            ResponseWrapper.Success<ResponseDto<MutableList<Media>?>>(ResponseDto(result = expectedData))
        coEvery { dashboardRepository.getTrending() } returns successResponse

        //When
        val result = mutableListOf<Resource<out MutableList<Media>>>()
        dashboardUseCase.getTrending().collect { result.add(it) }

        //Then
        assertEquals(3, result.size)

        val loadingResult = result[0]
        assertEquals(true, loadingResult.loading)

        val loadingResultFalse = result[1]
        assertEquals(false, loadingResultFalse.loading)

        val success = result[2]
        assertEquals(expectedData, success.data)

        coVerify { dashboardRepository.getTrending() }
    }


    @Test
    fun `when repository dashboard return data success`() = runBlocking {
        //Given
        val expectedData = mutableListOf(
            Media(0, "test", "es", "afadfa.jpg", "movie", emptyList()),
            Media(1, "test1", "es", "afadfab.jpg", "tv", emptyList())
        )
        val successResponse =
            ResponseWrapper.Success<ResponseDto<MutableList<Media>?>>(ResponseDto(result = expectedData))
        coEvery { dashboardRepository.getTrending() } returns successResponse

        //When
        val result = mutableListOf<Resource<out MutableList<Media>>>()
        dashboardUseCase.getTrending().collect { result.add(it) }

        //Then
        assertEquals(3, result.size)

        val loadingResult = result[0]
        assertEquals(true, loadingResult.loading)

        val loadingResultFalse = result[1]
        assertEquals(false, loadingResultFalse.loading)

        val success = result[2]
        assertEquals(expectedData, success.data)

        coVerify { dashboardRepository.getTrending() }
    }
}