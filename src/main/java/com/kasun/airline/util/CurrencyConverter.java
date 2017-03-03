package com.kasun.airline.util;

import com.google.gson.Gson;
import com.kasun.airline.common.execption.ErrorCode;
import com.kasun.airline.common.execption.ServiceRuntimeException;
import com.kasun.airline.dto.account.CurrencyConversionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kasun on 2/28/17.
 */
public class CurrencyConverter {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyConverter.class);

    private static final String APE_PROVIDER = "http://api.fixer.io/";

    public static double convert(String fromCurrencyCode, String toCurrencyCode) {

        if ((fromCurrencyCode != null && !fromCurrencyCode.isEmpty())
                && (toCurrencyCode != null && !toCurrencyCode.isEmpty())) {

            CurrencyConversionResponse response = getResponse(APE_PROVIDER + "/latest?base=" + fromCurrencyCode);

            if (response != null) {
                String rate = response.getRates().get(toCurrencyCode);
                return Double.valueOf((rate != null) ? rate : "0.0");
            }
        }
        return 0.0;
    }

    private static CurrencyConversionResponse getResponse(String strUrl) {

        CurrencyConversionResponse response = null;

        Gson gson = new Gson();
        StringBuffer sb = new StringBuffer();

        //TODO add error code
        if (strUrl == null || strUrl.isEmpty()) {
            logger.error("Application Error", "Url is empty");
            throw new ServiceRuntimeException("", "");
        }

        URL url;

        try {
            url = new URL(strUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream stream = connection.getInputStream();
            int data = stream.read();

            while (data != -1) {
                sb.append((char) data);
                data = stream.read();
            }
            stream.close();
            response = gson.fromJson(sb.toString(), CurrencyConversionResponse.class);

        } catch (IOException ex) {

            logger.error(ex.getMessage(), ex);
            //TODO add exception codes
            throw new ServiceRuntimeException(ErrorCode.CAN_NOT_CONVERT_CURRENCY, ex.getMessage());
        }
        return response;
    }

}
