package org.example;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        System.out.println("Enter the username you want");
        String user = scanner.nextLine();
        Call<List<Repo>> call = service.listRepos(user);

        List<Repo> repos = call.execute().body();
        for (Repo repo : repos) {
            System.out.println("The user: " + user + " has a repo with name: "+ repo.name + " with a description: " + repo.description + " and a total of: " +repo.stargazers_count + " stargazers");
        }
    }
}