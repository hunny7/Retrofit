package com.example.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
private TextView textView;
   private JsonPlaceholderApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);

       // getdata();
        getcomments();
    }

    private void getdata(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         api = retrofit.create(JsonPlaceholderApi.class);

        Call<List<Post>> call = api.getposts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    textView.setText("code: " + response.code());
                    return;
                }
                List<Post> posts =response.body();
                for (Post post : posts){
                    String content ="";
                    content += "ID: " + post.getId() + "\n";
                    content += "user ID: " + post.getUserId() + "\n";
                    content += "title: " + post.getTitle() + "\n";
                    content += "text: " + post.getText() + "\n\n";

                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }
    private void getcomments(){
     Retrofit retrofit = new Retrofit.Builder()
             .baseUrl("https://jsonplaceholder.typicode.com/")
             .addConverterFactory(GsonConverterFactory.create())
             .build();

     api = retrofit.create(JsonPlaceholderApi.class);
     Call<List<comment>> call = api.getcomments();
     call.enqueue(new Callback<List<comment>>() {
         @Override
         public void onResponse(Call<List<comment>> call, Response<List<comment>> response) {
             if(!response.isSuccessful()){
                 textView.setText("code: " + response.code());
                 return;
             }
             List<comment> comments = response.body();
         }

         @Override
         public void onFailure(Call<List<comment>> call, Throwable t) {

         }
     });


    }
}