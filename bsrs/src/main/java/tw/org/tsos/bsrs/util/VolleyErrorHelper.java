package tw.org.tsos.bsrs.util;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import tw.org.tsos.bsrs.BsrsApplication;
import tw.org.tsos.bsrs.R;

/**
 * Created by bono on 5/15/14.
 */
@SuppressWarnings({"DefaultFileTemplate", "UnusedDeclaration"})
public class VolleyErrorHelper {

    private static final String TAG = VolleyErrorHelper.class.getSimpleName();

    /**
     * Returns appropriate message which is to be displayed to the user
     * against the specified error object.
     *
     * @param error
     * @param uri
     * @return
     */
    @SuppressWarnings("JavaDoc")
    public static String getMessage(Object error, String uri) {
        if (error instanceof TimeoutError) {
            return BsrsApplication.getInstance().getResources().getString(R.string.generic_server_down);
        } else if (isServerProblem(error)) {
            return handleServerError(error, uri);
        } else if (isNetworkProblem(error)) {
            return BsrsApplication.getInstance().getResources().getString(R.string.no_internet);
        }
        return "";
    }

    /**
     * Determines whether the error is related to network
     *
     * @param error
     * @return
     */
    @SuppressWarnings("JavaDoc")
    private static boolean isNetworkProblem(Object error) {
        return (error instanceof NetworkError);
    }

    /**
     * Determines whether the error is related to server
     *
     * @param error
     * @return
     */
    @SuppressWarnings("JavaDoc")
    private static boolean isServerProblem(Object error) {
        return (error instanceof ServerError) || (error instanceof AuthFailureError);
    }

    /**
     * Handles the server error, tries to determine whether to show a stock message or to
     * show a message retrieved from the server.
     *
     * @param err
     * @param uri
     * @return
     */
    @SuppressWarnings("JavaDoc")
    private static String handleServerError(Object err, String uri) {
        VolleyError error = (VolleyError) err;
        String message = "";

        NetworkResponse response = error.networkResponse;
        String responseBody = new String(response.data);
        int statusCode = response.statusCode;
        Log.d(TAG, "status code=" + statusCode);
        Log.d(TAG, "error response headers " + response.headers);
        Log.d(TAG, "error response data " + responseBody);
        Log.d(TAG, "statusCode in VolleyErrorHelper " + statusCode);
        int errorCode = 0;

        //noinspection ConstantConditions
        if (response != null) {
            JSONObject jsonObject;
            switch (statusCode) {
                case 400:
                case 401:
                case 403:
                case 404:
                case 409:
                case 500:

                default:
                    return "";
            }
        }
        return message;
    }
}
