/*
 * SafeOnline project.
 *
 * Copyright 2006-2013 Lin.k N.V. All rights reserved.
 * Lin.k N.V. proprietary/confidential. Use is subject to license terms.
 */

package test.unit.net.link.eu.vat.client;

import net.link.eu.vat.client.CountryCode;
import net.link.eu.vat.client.VatAddress;
import net.link.eu.vat.client.VatCheckServiceClient;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: gvhoecke <gianni.vanhoecke@lin-k.net>
 * Date: 29/11/13
 * Time: 13:14
 */
public class VatCheckTest {

    @Test
    public void testVatNumber() {

        CountryCode countryCode = CountryCode.BE;
        String vatNumber        = "0446495156";

        VatCheckServiceClient vatCheckServiceClient = new VatCheckServiceClient( countryCode, vatNumber );

        boolean valid = vatCheckServiceClient.isVatNumberValid();
        VatAddress address = vatCheckServiceClient.getAddress();

        assertEquals( true, valid );
        assertEquals( "NV LIN.K", vatCheckServiceClient.getName() );
        assertEquals( "KLEIN-BEGIJNHOFSTRAAT", address.getStreet() );
        assertEquals( "5", address.getNumber() );
        assertEquals( "9090", address.getZipCode() );
        assertEquals( "MELLE", address.getMunicipality() );
    }
}
