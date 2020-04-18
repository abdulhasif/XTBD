import android.os.AsyncTask
import android.util.Log
import java.net.HttpURLConnection
import java.net.URL

class UploadPhoto : AsyncTask<String, String, String>() {
    override fun doInBackground(vararg params: String?): String? {
        val url: URL = URL("http://d00f103d.ngrok.io/classify")
        val httpURLConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.requestMethod = "POST"
        httpURLConnection.doOutput = true
        Log.e("sample","Code "+ httpURLConnection.responseCode)
        return "123"
    }
}