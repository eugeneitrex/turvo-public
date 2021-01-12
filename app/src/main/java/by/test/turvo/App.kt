package by.test.turvo

import android.app.Application
import by.test.turvo.net.Api
import by.test.turvo.net.ApiRest
import by.test.turvo.net.AuthManager
import by.test.turvo.ui.bookdetails.BookDetailsViewModel
import by.test.turvo.ui.books.books.BooksViewModel
import by.test.turvo.ui.books.favorites.FavoritesViewModel
import io.reactivex.disposables.CompositeDisposable
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class App : Application() , KodeinAware {
    override val kodein = Kodein.lazy {
        bind<Api>() with singleton { ApiRest().getApi() }
        bind<ApiRest>() with singleton { ApiRest() }
        bind<CompositeDisposable>() with singleton { CompositeDisposable() }
        bind<AuthManager>() with singleton { AuthManager(instance()) }
        bind<BooksViewModel>() with provider { BooksViewModel(instance()) }
        bind<FavoritesViewModel>() with provider { FavoritesViewModel(instance()) }
        bind<BooksService>() with singleton { BooksService(instance(), instance()) }
        bind<BookDetailsViewModel>() with singleton { BookDetailsViewModel(instance()) }
    }
}
