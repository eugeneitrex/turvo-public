package by.test.turvo.ui.bookdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.test.turvo.databinding.FragmentBookDetailsBinding
import by.test.turvo.net.response.Book
import com.bumptech.glide.Glide
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class BookDetailsFragment : Fragment(), KodeinAware {
    override val kodein by closestKodein()
    private lateinit var binding: FragmentBookDetailsBinding
    private val arguments: BookDetailsFragmentArgs by navArgs()
    private val detailsViewModel: BookDetailsViewModel by instance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookDetailsBinding.inflate(inflater)
        binding.viewModel = detailsViewModel
        detailsViewModel.apply {
            bookLiveData.observe(viewLifecycleOwner) {
                setUI(it)
            }
            init(arguments.argumentBookId)
        }

        return binding.root
    }

    private fun setUI(book: Book) {
        with (binding) {

            Glide.with(detailsBookImage.context).load(book.image)
                .into(detailsBookImage)

            with (detailsAddToFavs) {
                isChecked = book.liked
                setOnClickListener {
                    detailsViewModel.favoriteChanged(book)
                }
            }
        }
    }
}
