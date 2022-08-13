package xyz.parkh.doing.api.model.response;

import lombok.Getter;

@Getter
public class Check {
    private Boolean check;

    public Check(Boolean check) {
        this.check = check;
    }
}
