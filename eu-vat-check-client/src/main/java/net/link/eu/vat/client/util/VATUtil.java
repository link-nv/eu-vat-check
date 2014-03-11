/*
 * SafeOnline project.
 *
 * Copyright 2006-2013 Lin.k N.V. All rights reserved.
 * Lin.k N.V. proprietary/confidential. Use is subject to license terms.
 */

package net.link.eu.vat.client.util;

import net.link.eu.vat.client.CountryCode;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

/**
 * User: gvhoecke <gianni.vanhoecke@lin-k.net>
 * Date: 11/03/14
 * Time: 14:11
 */
public class VATUtil {

    /**
     * Cleans a VAT number, ready to send to EU VAT service.
     *
     * E.g.: "BE 0446.495.156" becomes "0446495156".
     *
     * @param vatNumber the VAT number.
     *
     * @return the cleaned VAT number.
     */
    @NotNull
    public static String cleanEnterpriseNumber( @NotNull String vatNumber ) {

        return vatNumber.replaceAll( "[^0-9]+", "" ); //Remove all non-digits
    }

    /**
     * Tries to guess the country from the given VAT number.
     * Defaults to the given country code when this methods cannot guess it.
     *
     * @param vatNumber the number.
     * @param defaultCountry the default country.
     *
     * @return the countrycode if found, or the default one.
     */
    @NotNull
    public static CountryCode guessCountryFromNumber( @NotNull String vatNumber,
                                                      @NotNull CountryCode defaultCountry ) {

        if( StringUtils.isBlank( vatNumber ) || vatNumber.length() <= 2 ) {

            return defaultCountry;
        }

        try {

            String code = vatNumber.substring( 0, 2 ).toUpperCase();

            if( StringUtils.isAlpha( code ) ) {

                return CountryCode.valueOf( code ); //throws error when not found
            }
            else {

                return defaultCountry;
            }
        }
        catch( Exception e ) {

            return defaultCountry;
        }
    }
}
