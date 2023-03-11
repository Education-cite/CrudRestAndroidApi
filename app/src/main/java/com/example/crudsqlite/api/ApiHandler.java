package com.example.crudsqlite.api;

import static com.android.volley.Request.Method.DELETE;
import static com.android.volley.Request.Method.GET;
import static com.example.crudsqlite.api.ApiConstants.APPLICATION_JSON_VALUE;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class ApiHandler {

    private RequestQueue queue;

    public ApiHandler(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    public void getJsonArray(String url,
                     Response.Listener<JSONArray> successHandler,
                     Response.ErrorListener errorHandler) {
        JsonArrayRequest request = new JsonArrayRequest(GET, url, null, successHandler, errorHandler);
        queue.add(request);
    }

    public void delete(String url,
                     Response.Listener<String> successHandler,
                     Response.ErrorListener errorHandler) {
        StringRequest request = new StringRequest(DELETE, url, successHandler, errorHandler);
        queue.add(request);
    }

    public void postJson(String url, JSONObject data,
                     Response.Listener<JSONObject> successHandler,
                     Response.ErrorListener errorHandler) throws Exception {
        String json = data.toString();

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST, url, null,
                successHandler, errorHandler) {

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                return super.parseNetworkResponse(response);
            }

            @Override
            public String getBodyContentType() {
                return APPLICATION_JSON_VALUE;
            }

            @Override
            public byte[] getBody() {
                return json.getBytes(StandardCharsets.UTF_8);
            }
        };
        queue.add(request);
    }

    public void postString(String url, JSONObject data,
                     Response.Listener<String> successHandler,
                     Response.ErrorListener errorHandler) throws Exception {
        String json = data.toString();

        StringRequest request = new StringRequest(Request.Method.POST, url, successHandler, errorHandler) {

            @Override
            public String getBodyContentType() {
                return APPLICATION_JSON_VALUE;
            }

            @Override
            public byte[] getBody() {
                return json.getBytes(StandardCharsets.UTF_8);
            }
        };
        queue.add(request);
    }

//    function method1(res) {
//        console.log(res)
//    }
//
//    function method2(res) {
//        console.log(res)
//    }
//
//    function post(url, data, method1, method2) {
//        httpclient.post(url, data)
//                .subscribe()
//                .then(method1, method2);
//    }


    public void get(String url, JSONObject data,
                    Response.Listener<JSONObject> successHandler,
                    Response.ErrorListener errorHandler) {
        String json = data.toString();

        JsonObjectRequest request = new JsonObjectRequest(
                GET, url, null,
                successHandler, errorHandler) {
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                return super.parseNetworkResponse(response);
            }

            @Override
            public String getBodyContentType() {
                return APPLICATION_JSON_VALUE;
            }

            @Override
            public byte[] getBody() {
                return json.getBytes(StandardCharsets.UTF_8);
            }
        };
        queue.add(request);
    }

}
