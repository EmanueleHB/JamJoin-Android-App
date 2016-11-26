
package com.hackit.hackroom.people.fakePerson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FakePeople {

    @SerializedName("results")
    @Expose
    private List<FakePerson> results = new ArrayList<FakePerson>();
    @SerializedName("info")
    @Expose
    private Info info;

    /**
     * 
     * @return
     *     The results
     */
    public List<FakePerson> getResults() {
        return results;
    }

    /**
     * 
     * @param results
     *     The results
     */
    public void setResults(List<FakePerson> results) {
        this.results = results;
    }

    /**
     * 
     * @return
     *     The info
     */
    public Info getInfo() {
        return info;
    }

    /**
     * 
     * @param info
     *     The info
     */
    public void setInfo(Info info) {
        this.info = info;
    }

}
