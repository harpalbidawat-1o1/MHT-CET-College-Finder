
package com.cet.finder.model;

public class College {
    private String name;
    private String city;
    private String branch;
    private double cutoff;

    public College(String name, String city, String branch, double cutoff) {
        this.name = name;
        this.city = city;
        this.branch = branch;
        this.cutoff = cutoff;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getBranch() {
        return branch;
    }

    public double getCutoff() {
        return cutoff;
    }
}
