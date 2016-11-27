package com.hackit.hackroom.people;

import com.hackit.hackroom.Person;
import com.hackit.hackroom.people.fakePerson.FakePeople;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by emanueledivizio on 27/11/16.
 */
public class FetchPeopleHeroku implements FetchPeople {

    private final FetchPeopleService fetchPeopleService;
    private final FetchFakePeopleService fetchFakePeopleService;
    private String[] stringArray;

    public FetchPeopleHeroku(String[] stringArray) {
        this.stringArray = stringArray;

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jamjoin.herokuapp.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        fetchPeopleService = retrofit.create(FetchPeopleService.class);


        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        fetchFakePeopleService = retrofit2.create(FetchFakePeopleService.class);
    }

    @Override
    public Observable<Person> fetchPeople(int number, String query) {
        Observable<List<HerokuPerson>> hackkings;
        if ("".equals(query)) {
            hackkings = fetchPeopleService.getPeople("hackkings");
        } else {
            hackkings = fetchPeopleService.queryPeople("hackkings", query);
        }
        return hackkings.flatMap(Observable::from).flatMap(herokuPerson -> fetchFakePeopleService.getFakePeople(1)
                .flatMap(fakePeople1 -> Observable.from(fakePeople1.getResults()))
                .map(fakePerson -> new Person(herokuPerson.getName(), fakePerson.getPicture().getLarge(), herokuPerson.getTags())))
                .concatWith(fetchFakePeopleService.getFakePeople(5).flatMap(fakePeople1 -> Observable.from(fakePeople1.getResults()))
                        .map(fakePerson1 -> {
                            List<String> list = Arrays.asList(stringArray);
                            Collections.shuffle(list);
                            return new Person(fakePerson1.getName().getFirst(), fakePerson1.getPicture().getLarge(), (String[]) Arrays.copyOfRange(list.toArray(), 0, 3));
                        }));
    }


    interface FetchPeopleService {
        @GET("{room}/")
        Observable<List<HerokuPerson>> getPeople(@Path("room") String room);

        @FormUrlEncoded
        @POST("{room}/")
        Observable<List<HerokuPerson>> queryPeople(@Path("room") String room, @Field("query") String query);
    }

    interface FetchFakePeopleService {
        @GET("api/")
        Observable<FakePeople> getFakePeople(@Query("results") int number);
    }
}
