package tw.org.tsos.bsrs.util.csv;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by i_miayu on 12/08/2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class MakeRequestTask
        extends AsyncTask<Void, Void, List<String>> {
    private static final String TAG = MakeRequestTask.class.getSimpleName();
    private com.google.api.services.sheets.v4.Sheets mService = null;
    private Exception mLastError = null;

    public MakeRequestTask() {
        HttpTransport transport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        mService = new com.google.api.services.sheets.v4.Sheets.Builder(
                transport,
                jsonFactory,
                null)
                .setApplicationName("Google Sheets API Android Quickstart")
                .build();
    }

    /**
     * Background task to call Google Sheets API.
     *
     * @param params no parameters needed for this task.
     */
    @Override
    protected List<String> doInBackground(Void... params) {
        try {
            return getDataFromApi();
        }
        catch (Exception e) {
            mLastError = e;
            cancel(true);
            return null;
        }
    }

    /**
     * Fetch a list of names and majors of students in a sample spreadsheet:
     * https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
     *
     * @return List of names and majors
     * @throws IOException
     */
    @SuppressWarnings("JavaDoc")
    private List<String> getDataFromApi()
            throws
            IOException {
        String spreadsheetId = "1OkMLlgwsefu9hp_E4Jaqcm6-szVnNU_bPdgYlLT4AM0";
        String range = "ebook!A3:C";
        List<String> results = new ArrayList<>();

        ValueRange response = this.mService.spreadsheets()
                                           .values()
                                           .get(spreadsheetId,
                                                range)
                                           .setKey("AIzaSyDawHMeRHOfMubnQzIMcU8k-l493GeF2KY")
                                           .execute();
        List<List<Object>> values = response.getValues();
        if (values != null) {
//                results.add("Name, Major");
            for (List row : values) {
                Log.d(MakeRequestTask.TAG,
                      Arrays.toString(row.toArray()));
//                    results.add(row.get(0) + ", " + row.get(4));
            }
        }
        return results;
    }


    @Override
    protected void onPreExecute() {
        Log.d(MakeRequestTask.TAG,
              "pre");
    }

    @Override
    protected void onPostExecute(List<String> output) {
        if (output == null || output.size() == 0) {
            Log.d(MakeRequestTask.TAG,
                  "No results returned.");
        }
        else {
            Log.d(MakeRequestTask.TAG,
                  "Data retrieved using the Google Sheets API:");
        }
    }

    @Override
    protected void onCancelled() {
        if (mLastError != null) {
            if (mLastError instanceof GooglePlayServicesAvailabilityIOException) {
                Log.d(MakeRequestTask.TAG,
                      "error : " + mLastError.getMessage());
            }
            else if (mLastError instanceof UserRecoverableAuthIOException) {
                Log.d(MakeRequestTask.TAG,
                      "error : " + mLastError.getMessage());
            }
            else {
                Log.d(MakeRequestTask.TAG,
                      "The following error occurred:\n" + mLastError.getMessage());
            }
        }
        else {
            Log.d(MakeRequestTask.TAG,
                  "Request cancelled.");
        }
    }
}
