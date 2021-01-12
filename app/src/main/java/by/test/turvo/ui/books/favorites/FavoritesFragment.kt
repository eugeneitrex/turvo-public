package by.test.turvo.ui.books.favorites

import by.test.turvo.ui.books.BaseBooksFragment
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class FavoritesFragment : BaseBooksFragment(), KodeinAware {
    override val kodein by closestKodein()
    override fun getMyViewModel(): FavoritesViewModel {
        val viewModel: FavoritesViewModel by instance()
        return viewModel
    }
}
