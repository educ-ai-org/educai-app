import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.educai.data.di.provideTestModule
import com.example.educai.data.model.Leaderboard
import com.example.educai.data.model.StudentPicture
import com.example.educai.data.services.LeaderboardService
import com.example.educai.data.services.UserService
import com.example.educai.data.viewmodel.LeaderboardViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.spy
import org.mockito.Mockito.`when`
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class LeaderboardViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var leaderboardService: LeaderboardService

    @Mock
    private lateinit var userService: UserService

    private lateinit var viewModel: LeaderboardViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        startKoin {
            modules(provideTestModule(leaderboardService, userService))
        }

        viewModel = LeaderboardViewModel(leaderboardService, userService)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        stopKoin()
    }

    @Test
    fun getLeaderboard_successfulResponse_updatesLeaderboard() = runTest {
        val classroomId = "1234567890L"
        val leaderboardList = listOf(Leaderboard("1", "Student 1", 100, "picture01"))
        val response = Response.success(leaderboardList)
        val spyViewModel = spy(viewModel)

        doReturn(Unit).`when`(spyViewModel).fetchStudentPictures(anyString())
        `when`(leaderboardService.getLeaderboard(classroomId)).thenReturn(response)

        spyViewModel.getLeaderboard(classroomId)

        advanceUntilIdle()

        Assert.assertEquals(leaderboardList, spyViewModel.leaderboard.value)
    }

    @Test
    fun fetchStudentPictures_successfulResponse_updatesLeaderboardWithPictures() = runTest {
        val classroomId = "1234567890L"
        val leaderboardList = listOf(Leaderboard("1", "Student 1", 100, null))
        val studentPictures = listOf(StudentPicture("1", "picture01_url"))
        val response = Response.success(studentPictures)

        `when`(userService.getProfilePictures(classroomId)).thenReturn(response)
        viewModel.leaderboard.value = leaderboardList

        viewModel.fetchStudentPictures(classroomId)

        advanceUntilIdle()

        Assert.assertEquals("picture01_url", viewModel.leaderboard.value[0].profilePicture)
    }

}