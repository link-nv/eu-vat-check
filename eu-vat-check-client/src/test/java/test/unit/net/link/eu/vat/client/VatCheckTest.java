/*
 * SafeOnline project.
 *
 * Copyright 2006-2013 Lin.k N.V. All rights reserved.
 * Lin.k N.V. proprietary/confidential. Use is subject to license terms.
 */

package test.unit.net.link.eu.vat.client;

import net.link.eu.vat.client.CountryCode;
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

        assertEquals( true, valid );
        assertEquals( "NV LIN.K", vatCheckServiceClient.getName() );
    }
}
