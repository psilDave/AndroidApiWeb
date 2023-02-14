package br.com.alura.estoque.retrofit;

import androidx.annotation.NonNull;

import br.com.alura.estoque.database.service.ProdutoService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EstoqueRetrofit {

    public static final String URL_BASE = "http://192.168.1.190:8080/";
    private final ProdutoService ProdutoService;

    public EstoqueRetrofit() {
        OkHttpClient client = configuraClient();


        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL_BASE).addConverterFactory(GsonConverterFactory.create()).client(client).build();
        ProdutoService = retrofit.create(ProdutoService.class);
    }

    @NonNull
    private OkHttpClient configuraClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        return client;
    }

    public ProdutoService getProdutoService() {
        return ProdutoService;
    }


}
