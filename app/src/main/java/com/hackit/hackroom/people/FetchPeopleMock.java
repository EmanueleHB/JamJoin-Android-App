package com.hackit.hackroom.people;

import com.hackit.hackroom.Person;
import com.hackit.hackroom.people.fakePerson.FakePeople;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by emanueledivizio on 26/11/16.
 */
public class FetchPeopleMock implements FetchPeople {
    private FetchFakePeopleService fetchFakePeopleService;

    public FetchPeopleMock() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        fetchFakePeopleService = retrofit.create(FetchFakePeopleService.class);
    }

    @Override
    public Observable<Person> fetchPeople(int number) {
        return fetchFakePeopleService.getFakePeople(number).flatMap(fakePeople -> Observable.from(fakePeople.getResults()))
                .map(fakePerson -> new Person(fakePerson.getName().getFirst(), fakePerson.getPicture().getLarge()));
    }
}


interface FetchFakePeopleService {
    @GET("api/")
    Observable<FakePeople> getFakePeople(@Query("results") int number);
}


