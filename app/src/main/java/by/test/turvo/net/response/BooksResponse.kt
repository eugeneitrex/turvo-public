package by.test.turvo.net.response

data class BooksResponse(
    val books: ArrayList<Book>
)

data class Book(
    val id: String,
    val name: String,
    val image: String,
    val year: String,
    val author: String,
    val isbn: String,
    val excerpt: String
) {
    var liked = false
}
