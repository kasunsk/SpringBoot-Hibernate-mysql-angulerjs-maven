package com.kasun.airline.adapter.airline;

import com.kasun.airline.common.dto.Price;
import com.kasun.airline.dto.airline.AirlineOffer;
import com.kasun.airline.model.airline.AirlineOfferModel;
import com.kasun.airline.model.airline.Route;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is responsible for provide adaptation facility between AirlineOfferModel and AirlineOffer
 */
@Component
public class AirlineOfferAdapter {

    public AirlineOfferModel adaptFromDto(AirlineOffer airlineOffer){

        AirlineOfferModel airlineOfferModel = null;

        if (airlineOffer != null) {
            airlineOfferModel = new AirlineOfferModel();
            airlineOfferModel.setOrigin(airlineOffer.getRoute().getFrom());
            airlineOfferModel.setDestination(airlineOffer.getRoute().getTo());
            airlineOfferModel.setPrice(airlineOffer.getPrice().getPrice());
            airlineOfferModel.setCurrency(airlineOffer.getPrice().getCurrency());
            airlineOfferModel.setStatus(AirlineOffer.AirlineOfferStatus.AVAILABLE);
            airlineOfferModel.setAvailbaleInventory(airlineOffer.getAvailableInventory());
        }
        return airlineOfferModel;
    }

    public List<AirlineOffer> adaptFromModelList(List<AirlineOfferModel> offerList) {

        return offerList.stream().map(this::adaptFromModel).collect(Collectors.toList());
    }

    public AirlineOffer adaptFromModel(AirlineOfferModel offerModel) {

        AirlineOffer airlineOffer = new AirlineOffer();

        airlineOffer.setOfferId(offerModel.getOfferId());
        Price price = new Price();
        price.setPrice(offerModel.getPrice());
        price.setCurrency(offerModel.getCurrency());
        airlineOffer.setPrice(price);

        Route route = new Route();
        route.setFrom(offerModel.getOrigin());
        route.setTo(offerModel.getDestination());
        airlineOffer.setRoute(route);

        airlineOffer.setStatus(offerModel.getStatus());
        airlineOffer.setAvailableInventory(offerModel.getAvailbaleInventory());

        return airlineOffer;
    }
}
