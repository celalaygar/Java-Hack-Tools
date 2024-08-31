package network.listener;

import org.snmp4j.*;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

/*
        <dependency>
            <groupId>org.snmp4j</groupId>
            <artifactId>snmp4j</artifactId>
            <version>3.8.2</version>
        </dependency>
 */

public class DeviceLister {

    public static void main(String[] args) {
        try {
            // SNMP community string
            String community = "public";
            // Modem IP'si
            String ipAddress = "192.168.1.1";
            int port = 161; // SNMP portu
            int version = SnmpConstants.version2c;

            TransportMapping<UdpAddress> transport = new DefaultUdpTransportMapping();
            transport.listen();

            // Community target ayarları
            CommunityTarget<UdpAddress> target = new CommunityTarget<>();
            target.setCommunity(new OctetString(community));
            target.setAddress(new UdpAddress(ipAddress + "/" + port));
            target.setRetries(2);
            target.setTimeout(1500);
            target.setVersion(version);

            Snmp snmp = new Snmp(transport);

            PDU pdu = new PDU();
            pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.4.22.1.2")));
            pdu.setType(PDU.GETNEXT);

            ResponseEvent<UdpAddress> response;
            do {
                // SNMP isteği gönderiliyor
                response = snmp.send(pdu, target);
                PDU responsePDU = response.getResponse();

                if (responsePDU != null) {
                    VariableBinding vb = responsePDU.get(0);
                    System.out.println("Device IP: " + vb.getVariable().toString());
                    pdu.setRequestID(new Integer32(0));
                    pdu.set(0, vb);
                } else {
                    System.out.println("Cihazları listeleme tamamlandı.");
                    break;
                }
            } while (response != null);

            // SNMP bağlantısı kapatılıyor
            snmp.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
