package com.petStore.Pojo.Store.Response;

public class InventoryResponseBody {

    public String sold;
    public String SOLD;
    public String string;

    public String getSold() {
        return sold;
    }

    public void setSold(String sold) {
        this.sold = sold;
    }

    public String getSOLD() {
        return SOLD;
    }

    public void setSOLD(String SOLD) {
        this.SOLD = SOLD;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getPending() {
        return pending;
    }

    public void setPending(String pending) {
        this.pending = pending;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getHomeless() {
        return homeless;
    }

    public void setHomeless(String homeless) {
        this.homeless = homeless;
    }

    public String pending;
    public String available;
    public String homeless;

}
