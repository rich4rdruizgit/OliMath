package olimpiadas.sena.com.olimpiadasmath.util.webConManager;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.net.URI;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.conn.scheme.PlainSocketFactory;
import cz.msebera.android.httpclient.conn.scheme.Scheme;
import cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import cz.msebera.android.httpclient.conn.ssl.SSLSocketFactory;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.impl.conn.SingleClientConnManager;
import cz.msebera.android.httpclient.util.EntityUtils;

public class WebConnection {

    private String TAG ="WebConnection";
    Context context;

    String respStr;

    HttpClient httpClient;
    WebConnectionListener webConncetionListener;
    private String currentMethod = "";


    public interface WebConnectionListener {
        void webConnectionComplete(String type, String resp);
    }

    public WebConnection(WebConnectionListener webConnectionListener){
        this.webConncetionListener = webConnectionListener;
        ConexionSSL();
    }

    public void ConexionSSL(){
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");

            sslContext.init(null, new TrustManager[] { new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    System.out.println("getAcceptedIssuers =============");
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs,
                                               String authType) {
                    System.out.println("checkClientTrusted =============");
                }

                public void checkServerTrusted(X509Certificate[] certs,
                                               String authType) {
                    System.out.println("checkServerTrusted =============");
                }
            } }, new SecureRandom());

            SSLSocketFactory sf = new SSLSocketFactory(sslContext,
                    SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            Scheme httpsScheme = new Scheme("https", 443, sf);
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(httpsScheme);
            schemeRegistry.register( new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));


            ClientConnectionManager cm = new SingleClientConnManager(schemeRegistry);


            httpClient = new DefaultHttpClient(cm);
        }catch (Exception e){
            e.printStackTrace();
        }
    }




    //Web connection manager

    public void executePostRequest(String url, String operation, List<NameValuePair> nameValuePairs){

        String values = "";
        Log.d(TAG,"Los valores HttpEntity " + values);
        ExecuteHttpClientPostRequest executeRequest = new ExecuteHttpClientPostRequest();
        executeRequest.execute(url,operation,nameValuePairs);
    }

    public void executePostRequest(String url, String operation, HttpEntity multiPart){

        String values = "";
        Log.d(TAG,"Los valores HttpEntity " + values);
        ExecuteHttpClientPostMultipartRequest executeRequest = new ExecuteHttpClientPostMultipartRequest();
        executeRequest.execute(url,operation,multiPart);
    }

    public void executeAsyncGetRequest(String url){


        Log.d(TAG,"Url getRequest " + url);
        ExecuteHttpClientGetRequest executeRequest = new ExecuteHttpClientGetRequest();
        executeRequest.execute(url);
    }
    public void executeAsyncGetRequest(String url, String type){


        Log.d(TAG,"Url getRequest " + url);
        ExecuteHttpClientGetRequest executeRequest = new ExecuteHttpClientGetRequest();
        executeRequest.execute(url,type);
    }

    public String executeSyncPostRequest(String url, String operation, List<NameValuePair> nameValuePairs){

        return httpClientPostRequest(url,operation,nameValuePairs);
    }

    public String executeSyncPostRequest(String url, String operation, HttpEntity multiPart){


        return httpClientPostRequest(url,operation,multiPart);
    }

    public String executeSyncGetRequest(String url){


        Log.d(TAG,"Url getRequest " + url);

        return httpClientGetRequest(url);
    }



    public class ExecuteHttpClientPostRequest extends AsyncTask<Object, Void, Boolean> {

        private String method,wsResp;
        @Override
        protected Boolean doInBackground(Object... params) {
            try {
                wsResp = httpClientPostRequest((String) params[0], (String) params[1], (List<NameValuePair>) params[2]);
                currentMethod = (String) params[1];
                method = (String) params[1];

            } catch (Exception ex) {

                Log.e("ServicioRest", "Error!", ex);
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            webConncetionListener.webConnectionComplete(method, wsResp);
        }

        @Override
        protected void onCancelled() {
            //Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
        }

    }



    public class ExecuteHttpClientPostMultipartRequest extends AsyncTask<Object, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Object... params) {
            try
            {
                httpClientPostRequest((String) params[0],(String) params[1],(HttpEntity) params[2]);
                currentMethod = (String) params[1];

            }
            catch(Exception ex)
            {
                System.out.println("Entro a expeción");
                Log.e("ServicioRest","Error!", ex);
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            webConncetionListener.webConnectionComplete(currentMethod,respStr);
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
        }
    }


    public class ExecuteHttpClientGetRequest extends AsyncTask<Object, Void, Boolean> {

        private String method,wsResp;
        @Override
        protected Boolean doInBackground(Object... params) {
            try {
                wsResp = httpClientGetRequest((String) params[0]);
                method = (String) params[1];
                Log.d(TAG,"Aqui deberia ir ranking = " + method );
                //currentMethod = (String) params [1];


            } catch (Exception ex) {

                Log.e("ServicioRest", "Error!", ex);
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            webConncetionListener.webConnectionComplete(method, wsResp);
        }

        @Override
        protected void onCancelled() {
            //Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
        }

    }




    public String httpClientPostRequest(String url,String method, HttpEntity nameValuePairs){
        CloseableHttpResponse resp;
        try{
            HttpPost post = new HttpPost (new URI(url + method));
            Log.d(TAG," url " + url + " metodo = " + method);
            //Basic authentication
            post.setHeader("Authorization", "Basic " + Base64.encodeToString("enroller:enroller123".getBytes(), Base64.NO_WRAP));
            post.setEntity((HttpEntity) nameValuePairs);
            resp =(CloseableHttpResponse) httpClient.execute(post);
            respStr = EntityUtils.toString(resp.getEntity());
            return respStr;

        }catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }


    public String httpClientGetRequest(String url){
        CloseableHttpResponse resp;
        try{

            Log.d(TAG,"httpClientGetRequest Url " + url);
            URI uri = new URI(url);
            Log.d(TAG," new uri " + uri.toString());

            HttpGet get = new HttpGet(url);
            get.addHeader("Accept", "application/json");


            resp =(CloseableHttpResponse) httpClient.execute(get);
            //httpClient.
            respStr = EntityUtils.toString(resp.getEntity());
            return respStr;
        }catch (Exception e){
            Log.e(TAG,"Error en peticion get URL = " + url, e);
            e.printStackTrace();
        }


        return "";
    }

    public String httpClientPostRequest(String url,String method, List<NameValuePair> nameValuePairs){
        CloseableHttpResponse resp = null;
        try{
            Log.d(TAG," url " + url + " metodo = " + method);

            HttpPost post = new HttpPost (new URI(url + method));
            //Basic authentication
            post.setHeader("Authorization", "Basic " + Base64.encodeToString("enroller:enroller123".getBytes(), Base64.NO_WRAP));
            post.setEntity(new UrlEncodedFormEntity( nameValuePairs));

            String params = "";
            for (NameValuePair nvp :((List<NameValuePair>) nameValuePairs)) {
                params += nvp.toString() + "\n";
            }
            Log.d(TAG,"Parametros de envio \n" + params);
            respStr = "";
            resp =(CloseableHttpResponse) httpClient.execute(post);

            respStr = EntityUtils.toString(resp.getEntity());
            Log.d(TAG,"Respuesta url " + url + method + "\n" + respStr);

            return respStr;

        }catch (Exception e){
            Log.d(TAG,"excepction msg " + e.getMessage());
            e.printStackTrace();

        }
        return null;
    }
}
