package com.tpcmswebadmin.infrastructure.client.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties
public class DataDto<T> implements Serializable {

    private static final long serialVersionUID = -5163220707702941096L;

    private List<String> thead;

    private transient List<T> tbody;

    public DataDto() {
    }

    public DataDto(List<String> thead, List<T> tbody) {
        this.thead = thead;
        this.tbody = tbody;
    }

    public List<String> getThead() {
        return thead;
    }

    public void setThead(List<String> thead) {
        this.thead = thead;
    }

    public List<T> getTbody() {
        return tbody;
    }

    public void setTbody(List<T> tbody) {
        this.tbody = tbody;
    }
}
