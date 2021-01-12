package by.test.turvo.ui

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel
import by.test.turvo.utils.NavigationCommand
import by.test.turvo.utils.SingleLiveEvent

open class BaseViewModel : ViewModel() {
    val navigationCommand: SingleLiveEvent<NavigationCommand> = SingleLiveEvent()
    fun navigate(@IdRes navigationId: Int, bundle: Bundle? = null) = navigationCommand.postValue(NavigationCommand.ToId(navigationId, bundle))
}
