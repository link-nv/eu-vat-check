/*
 * SafeOnline project.
 *
 * Copyright 2006-2013 Lin.k N.V. All rights reserved.
 * Lin.k N.V. proprietary/confidential. Use is subject to license terms.
 */

package net.link.eu.vat.client;

import eu.europa.ec.taxud.vies.services.checkvat.CheckVatService;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;

/**
 * User: gvhoecke <gianni.vanhoecke@lin-k.net>
 * Date: 29/11/13
 * Time: 10:38
 *
 * Client implementation for checking a VAT number issued by any Member State of the European Union.
 *
 * Example:
 * <pre>
 * {@code
 *      //Create instance
 *      VatCheckServiceClient vatCheckServiceClient = new VatCheckServiceClient( countryCode, vatNumber );
 *
 *      //If you want you can set more parameters, like name and address, but this is not necessary
 *
 *      //Check validity
 *      boolean isValid = vatCheckServiceClient.isVatNumberValid();
 *
 *      //After isVatNumberValid() you can get the name and address of the enterprise
 *      String name    = vatCheckServiceClient.getName();
 *      String address = vatCheckServiceClient.getAddress();
 * }
 * </pre>
 */
public class VatCheckServiceClient {

    private CountryCode countryCode;
    private String vatNumber;

    private XMLGregorianCalendar requestDate;
    private String name;
    private String address;

    private boolean valid;

    /**
     * Default constructor
     *
     * @param countryCode the country code of the issued VAT
     * @param vatNumber the issued VAT number
     */
    public VatCheckServiceClient( CountryCode countryCode, String vatNumber ) {

        this.countryCode = countryCode;
        this.vatNumber = vatNumber;

        this.requestDate = null;
        this.valid = false;
        this.name = null;
        this.address = null;
    }

    /**
     * Checks whether the given VAT number is valid or not
     *
     * @return true if the given VAT number is valid, false otherwise
     */
    public boolean isVatNumberValid() {

        //Country code holder
        String countryCodeString = this.countryCode.name();
        final Holder<String> countryCodeHolder = new Holder<String>();
        countryCodeHolder.value = countryCodeString;

        //VAT number holder
        final Holder<String> vatNumberHolder = new Holder<String>();
        vatNumberHolder.value = this.vatNumber;

        //Request date holder
        final Holder<XMLGregorianCalendar> requestDateHolder = new Holder<XMLGregorianCalendar>();
        requestDateHolder.value = this.requestDate;

        //Valid holder
        final Holder<Boolean> validHolder = new Holder<Boolean>();
        validHolder.value = this.valid;

        //Name holder
        final Holder<String> nameHolder = new Holder<String>();
        nameHolder.value = this.name;

        //Address holder
        final Holder<String> addressHolder = new Holder<String>();
        addressHolder.value = this.address;

        try {

            //Check the validity
            CheckVatService checkVatService = new CheckVatService();
            checkVatService.getCheckVatPort().checkVat(
                    countryCodeHolder,
                    vatNumberHolder,
                    requestDateHolder,
                    validHolder,
                    nameHolder,
                    addressHolder );

            //Get the results
            this.countryCode = CountryCode.valueOf( countryCodeHolder.value );
            this.vatNumber = vatNumberHolder.value;
            this.requestDate = requestDateHolder.value;
            this.valid = validHolder.value;
            this.name = nameHolder.value;
            this.address = addressHolder.value;
        }
        catch( Exception e ) {

            this.valid = false;
            validHolder.value = false;
        }

        //Return validity
        return validHolder.value;
    }

    public CountryCode getCountryCode() {

        return countryCode;
    }

    public void setCountryCode( CountryCode countryCode ) {

        this.countryCode = countryCode;
    }

    public String getVatNumber() {

        return vatNumber;
    }

    public void setVatNumber( String vatNumber ) {

        this.vatNumber = vatNumber;
    }

    public XMLGregorianCalendar getRequestDate() {

        return requestDate;
    }

    public void setRequestDate( XMLGregorianCalendar requestDate ) {

        this.requestDate = requestDate;
    }

    public String getName() {

        return name;
    }

    public void setName( String name ) {

        this.name = name;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress( String address ) {

        this.address = address;
    }

    public boolean isValid() {

        return valid;
    }

    public void setValid( boolean valid ) {

        this.valid = valid;
    }
}
