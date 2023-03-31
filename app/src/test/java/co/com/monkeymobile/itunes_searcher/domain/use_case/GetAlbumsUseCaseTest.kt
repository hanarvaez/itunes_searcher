package co.com.monkeymobile.itunes_searcher.domain.use_case

import android.util.Log
import co.com.monkeymobile.itunes_searcher.domain.repository.AlbumRepository
import io.mockk.every
import io.mockk.mockkStatic
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class GetAlbumsUseCaseTest {

    private val albumRepository: AlbumRepository = mock()
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default

    private val getAlbumsUseCase = GetAlbumsUseCase(albumRepository, dispatcher)

    private val getAlbumsUseCaseParams: GetAlbumsUseCaseParams = mock()

    @Before
    fun prepareEnvironment() {
        mockkStatic(Log::class)
        every { Log.v(any(), any()) } returns 0
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
    }

    @Test
    fun `test execute when success`() = runTest {
        whenever(getAlbumsUseCaseParams.terms).thenReturn("Jack Jhohnson")
        whenever(albumRepository.fetchAlbumsListByTerm(getAlbumsUseCaseParams.terms)).thenReturn(
            emptyList()
        )

        val result: Result<GetAlbumsUseCaseResult> = getAlbumsUseCase(getAlbumsUseCaseParams)

        assert(result is Result.Success)
        assert((result as Result.Success).data.albums.isEmpty())
    }

    @Test
    fun `test execute when error`() = runTest {
        whenever(getAlbumsUseCaseParams.terms).thenReturn("Jack Jhohnson")
        whenever(albumRepository.fetchAlbumsListByTerm(getAlbumsUseCaseParams.terms)).thenThrow(
            RuntimeException()
        )

        val result: Result<GetAlbumsUseCaseResult> = getAlbumsUseCase(getAlbumsUseCaseParams)

        assert(result is Result.Error)
    }

}
