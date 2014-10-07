package com.dp.company.domain;

import java.util.UUID;

/**
 * Created by dpyang on 2014/10/7.
 */
public class Forum {
    private UUID id;
    private String name;
    private String nameEn;
    private boolean isEnabled;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
}
