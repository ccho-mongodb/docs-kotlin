import com.mongodb.kotlin.client.coroutine.MongoClient
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import com.mongodb.client.model.Filters.eq

// Create data class to represent a MongoDB document
data class Movie(val title: String, val year: Int, val cast: List<String>)

fun main() {

    // Replace the placeholder with your MongoDB deployment's connection string
    val uri = CONNECTION_STRING_URI_PLACEHOLDER

    val mongoClient = MongoClient.create(uri)
    val database = mongoClient.getDatabase("sample_mflix")
    // Get a collection of documents of type Movie
    val collection = database.getCollection<Movie>("movies")

    runBlocking {
        val doc = collection.find(eq("title", "Back to the Future")).first()
        if (doc != null) {
            println(doc)
        } else {
            println("No matching documents found.")
        }
    }

    mongoClient.close()
}

