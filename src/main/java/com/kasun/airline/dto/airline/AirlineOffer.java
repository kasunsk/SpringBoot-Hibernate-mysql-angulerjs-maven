package com.kasun.airline.dto.airline;

import com.kasun.airline.common.dto.Price;
import com.kasun.airline.model.airline.Route;


/**
 * Created by kasun on 2/4/17.
 */
public class AirlineOffer {

    public enum AirlineOfferStatus {
        AVAILABLE, NOT_AVAILABLE, CANCELED, FINISHED
    }

    private Long offerId;
    private Price price;
    private Route route;
    private AirlineOfferStatus status;
    private Integer availableInventory;

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public AirlineOfferStatus getStatus() {
        return status;
    }

    public void setStatus(AirlineOfferStatus status) {
        this.status = status;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Integer getAvailableInventory() {
        return availableInventory;
    }

    public void setAvailableInventory(Integer availableInventory) {
        this.availableInventory = availableInventory;
    }
}
