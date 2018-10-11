package com.kaito.kaitopractice;

import android.location.GpsStatus;
import android.net.sip.SipAudioCall;
import android.net.sip.SipSession;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TestAsync extends AsyncTask<String, String, String> {


    private ResultListener listener;

    public TestAsync(ResultListener listener) {
        this.listener = listener;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // doInBackground前処理
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection con = null;
        URL url = null;
        String urlSt = "https://realtime-chat-dd263.firebaseapp.com/message.json";

        try {
            // URLの作成
            url = new URL(urlSt);
            // 接続用HttpURLConnectionオブジェクト作成
            con = (HttpURLConnection)url.openConnection();
            // リクエストメソッドの設定
            con.setRequestMethod("POST");
            // リダイレクトを自動で許可しない設定
            con.setInstanceFollowRedirects(false);
            // URL接続からデータを読み取る場合はtrue
            con.setDoInput(true);
            // URL接続にデータを書き込む場合はtrue
            con.setDoOutput(true);

            // 接続
            con.connect(); // ①

            InputStream in = con.getInputStream();
            String readSt = readInputStream (in);

            JSONObject jsonData = new JSONObject(readSt).getJSONObject("message");
            String result = jsonData.getString("message");

            return result;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace ();
        }

        return null;
    }
    @Override
    protected void onPostExecute(String result) {
//        super.onPostExecute(result);
        // doInBackground後処理
        listener.onResult(result);
//        textView.setText(result);
    }
    private String readInputStream(InputStream in) throws IOException, UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        String st = "";

        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        while((st = br.readLine()) != null)
        {
            sb.append(st);
        }
        try
        {
            in.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return sb.toString();
    }



}
