package by.test.turvo.ui.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.test.turvo.databinding.FragmentBaseBooksBinding
import by.test.turvo.utils.NavigationCommand
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein

abstract class BaseBooksFragment : Fragment(), KodeinAware {
    override val kodein by closestKodein()
    private lateinit var booksAdapter: BooksAdapter
    private lateinit var binding: FragmentBaseBooksBinding
    private lateinit var baseViewModel: BaseBooksViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBaseBooksBinding.inflate(inflater, container, false)

        initList()

        baseViewModel = getMyViewModel()
        binding.viewModel = baseViewModel
        baseViewModel.apply {
            navigationCommand.observeSingleEvent(viewLifecycleOwner, { command ->
                when (command) {
                    is NavigationCommand.To -> findNavController().navigate(command.directions)
                    is NavigationCommand.ToId -> findNavController().navigate(
                        command.navigationId,
                        command.bundle
                    )
                }
            })

            booksLiveData.observe(viewLifecycleOwner) {
                booksAdapter.setData(it)
            }
        }

        return binding.root
    }

    private fun initList() {
        with(binding.listBooks) {
            booksAdapter = BooksAdapter({
                //favorite checked
                baseViewModel.favoriteClicked(it)
            }, {
                //item clicked
                baseViewModel.itemClicked(Bundle().apply {
                    putString("argument_book_id", it)
                })
            })
            adapter = booksAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    abstract fun getMyViewModel(): BaseBooksViewModel
}
