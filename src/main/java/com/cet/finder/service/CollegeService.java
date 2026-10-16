
package com.cet.finder.service;

import com.cet.finder.model.College;

public class CollegeService {

    public String getChance(double percentile) {
        if (percentile >= 87) {
            return "Safe";
        } else if (percentile >= 83) {
            return "Target";
        } else {
            return "Keep Dreaming lol";
        }
    }

    public College createSampleCollege(String city, String branch) {
        return new College(
            "Sample Engineering College",
            city,
            branch,
            85.0
        );
    }
}
