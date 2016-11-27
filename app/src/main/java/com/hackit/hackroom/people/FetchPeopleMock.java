package com.hackit.hackroom.people;

import com.hackit.hackroom.Person;
import com.hackit.hackroom.people.fakePerson.FakePeople;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.Subject;

/**
 * Created by emanueledivizio on 26/11/16.
 */
public class FetchPeopleMock implements FetchPeople {
    private FetchFakePeopleService fetchFakePeopleService;
    private Subject<List<Person>, List<Person>> personSubject = BehaviorSubject.create();

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
    public Observable<Person> fetchPeople(int number, String query) {
        return fetchFakePeopleService.getFakePeople(number).flatMap(fakePeople -> Observable.from(fakePeople.getResults()))
                .map(fakePerson -> {
                    String name = fakePerson.getName().getFirst();
                    StringBuilder sb = new StringBuilder(name);
                    sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
                    return new Person(sb.toString(), fakePerson.getPicture().getLarge());
                }).filter(person -> person.getName().contains(query));
    }
}


interface FetchFakePeopleService {
    @GET("api/")
    Observable<FakePeople> getFakePeople(@Query("results") int number);
}


