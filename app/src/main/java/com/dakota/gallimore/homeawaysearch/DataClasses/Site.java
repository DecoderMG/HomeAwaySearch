package com.dakota.gallimore.homeawaysearch.DataClasses;

/**
 * Created by galli_000 on 11/6/2017.
 * Class containing information on a Listing's site information. (A listing can be on numerous HomeAway sites)
 */

public class Site {

    private String href;
    private String rel;

    public Site() {
    }

    public Site(String href, String rel) {
        this.href = href;
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }
}
