package com.nds.gadai.generators;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class PaymentNumberGenerator implements IdentifierGenerator{
    
    @Override
    public Serializable generate(SharedSessionContractImplementor ssci, Object o) throws HibernateException {
        Connection connection = ssci.connection();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) AS seq FROM tx_payment");

            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int seq = rs.getInt("seq") + 1;
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyMM", Locale.US);
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.US);
                String inputText = "2012-11-17T00:00:00.000-05:00";
                Date date = inputFormat.parse(inputText);
                String output = outputFormat.format(date);
                String code = String.format("PCT-" + output + "-%05d", seq);
                System.out.println("Generated Stock Code: " + code);
                return code;
            } else {
                throw new HibernateException("Generator failed to generate id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
