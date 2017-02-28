package com.crossover.techtrial.java.se.adapter.airline;

import com.crossover.techtrial.java.se.common.dto.Price;
import com.crossover.techtrial.java.se.dto.airline.AirlineOffer;
import com.crossover.techtrial.java.se.model.airline.AirlineOfferModel;
import com.crossover.techtrial.java.se.model.airline.Route;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kasun on 2/4/17.
 */
@Component
public class AirlineOfferAdapter {

    public AirlineOfferModel adaptFromDto(AirlineOffer airlineOffer){

        AirlineOfferModel airlineOfferModel = new AirlineOfferModel();
        airlineOfferModel.setOrigin(airlineOffer.getRoute().getFrom());
        airlineOfferModel.setDestination(airlineOffer.getRoute().getTo());
        airlineOfferModel.setPrice(airlineOffer.getPrice().getPrice());
        airlineOfferModel.setCurrency(airlineOffer.getPrice().getCurrency());
        airlineOfferModel.setStatus(AirlineOffer.AirlineOfferStatus.AVAILABLE);
        airlineOfferModel.setAvailbaleInventory(airlineOffer.getAvailableInventory());
        return airlineOfferModel;
    }

    public List<AirlineOffer> adaptFromModelList(List<AirlineOfferModel> offerList) {

        List<AirlineOffer> offers = new ArrayList<AirlineOffer>();

        for (AirlineOfferModel offerModel : offerList) {
            offers.add(adaptFromModel(offerModel));
        }
        return offers;
    }

    private AirlineOffer adaptFromModel(AirlineOfferModel offerModel) {

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
