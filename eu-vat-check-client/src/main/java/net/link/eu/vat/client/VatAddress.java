/*
 * SafeOnline project.
 *
 * Copyright 2006-2013 Lin.k N.V. All rights reserved.
 * Lin.k N.V. proprietary/confidential. Use is subject to license terms.
 */

package net.link.eu.vat.client;

import java.io.Serializable;

/**
 * User: gvhoecke <gianni.vanhoecke@lin-k.net>
 * Date: 29/11/13
 * Time: 13:40
 */
public class VatAddress implements Serializable {

    private final String street;
    private final String number;
    private final String zipCode;
    private final String municipality;

    public static VatAddress parse( String fullAddress ) {

        //Address returned as such:
        //BLAUWTORENPLEIN 8
        //2000  ANTWERPEN

        try {

            String lines[] = fullAddress.split( "\\r?\\n" );

            String line2[] = lines[1].split( " " );

            String street = lines[0].substring( 0, lines[0].lastIndexOf( " " ) );
            String number = lines[0].substring( lines[0].lastIndexOf( " " ) );
            String zip    = line2[0];
            String city   = lines[1].substring( lines[1].indexOf( " " ) );

            return new VatAddress( street.trim(), number.trim(), zip.trim(), city.trim() );
        }
        catch( Exception e ) {

            return new VatAddress( fullAddress, "", "", "" );
        }
    }

    private VatAddress( final String street,
                        final String number,
                        final String zipCode,
                        final String municipality ) {

        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.municipality = municipality;
    }

    public String getStreet() {

        return street;
    }

    public String getNumber() {

        return number;
    }

    public String getZipCode() {

        return zipCode;
    }

    public String getMunicipality() {

        return municipality;
    }

    @Override
    public String toString() {

        return "VatAddress{" +
                "street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", municipality='" + municipality + '\'' +
                '}';
    }
}
