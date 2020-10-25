package net.awwakum.plaintodo

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import net.awwakum.plaintodo.model.ToDo
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = "https://api.thecatapi.com/v1/breeds?limit=3"

        AsyncTaskHandleJson().execute(url)
    }

    inner class AsyncTaskHandleJson: AsyncTask<String, String, String>() {
        override fun doInBackground(vararg url: String?): String {
            val urlConnection = URL(url[0]).openConnection() as HttpURLConnection
            val text: String
            try {
                urlConnection.connect()
                text = urlConnection.inputStream.use { it.reader().use { reader -> reader.readText() } }
            } finally {
                urlConnection.disconnect()
            }
            return text
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            handleJson(result)
        }
    }

    private fun handleJson(jsonString: String?) {
        val jsonArray = JSONArray(jsonString)
        val list = ArrayList<ToDo>()

        var index = 0
        while (index < jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(index)

            list.add(
                ToDo(
                    jsonObject.getString("id"),
                    jsonObject.getString("name"),
                    jsonObject.getString("description")
                )
            )
            index++
        }

        todo_list.layoutManager = LinearLayoutManager(this)
        todo_list.adapter = ToDoAdapter(list)

    }
}