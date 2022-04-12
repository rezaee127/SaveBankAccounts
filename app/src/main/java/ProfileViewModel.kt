import androidx.lifecycle.ViewModel
import com.example.hw13.repository.Repository

class ProfileViewModel :ViewModel(){

    var numberOfAccount=Repository.numberOfAccount
    var editProfileInfoFlag=Repository.editProfileInfoFlag


}