package xyz.parkh.doing.domain.model;

import lombok.Getter;

@Getter
public class Check {
    private Boolean check;

    public Check(Boolean check) {
        this.check = check;
    }
}
