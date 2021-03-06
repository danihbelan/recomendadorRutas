
package server;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "RecomendadorRutas", targetNamespace = "http://server/", wsdlLocation = "http://localhost:8080/ServidorRecomendador/RecomendadorRutas?WSDL")
public class RecomendadorRutas
    extends Service
{

    private final static URL RECOMENDADORRUTAS_WSDL_LOCATION;
    private final static WebServiceException RECOMENDADORRUTAS_EXCEPTION;
    private final static QName RECOMENDADORRUTAS_QNAME = new QName("http://server/", "RecomendadorRutas");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/ServidorRecomendador/RecomendadorRutas?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        RECOMENDADORRUTAS_WSDL_LOCATION = url;
        RECOMENDADORRUTAS_EXCEPTION = e;
    }

    public RecomendadorRutas() {
        super(__getWsdlLocation(), RECOMENDADORRUTAS_QNAME);
    }

    public RecomendadorRutas(WebServiceFeature... features) {
        super(__getWsdlLocation(), RECOMENDADORRUTAS_QNAME, features);
    }

    public RecomendadorRutas(URL wsdlLocation) {
        super(wsdlLocation, RECOMENDADORRUTAS_QNAME);
    }

    public RecomendadorRutas(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, RECOMENDADORRUTAS_QNAME, features);
    }

    public RecomendadorRutas(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public RecomendadorRutas(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Recomendador
     */
    @WebEndpoint(name = "RecomendadorPort")
    public Recomendador getRecomendadorPort() {
        return super.getPort(new QName("http://server/", "RecomendadorPort"), Recomendador.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Recomendador
     */
    @WebEndpoint(name = "RecomendadorPort")
    public Recomendador getRecomendadorPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://server/", "RecomendadorPort"), Recomendador.class, features);
    }

    private static URL __getWsdlLocation() {
        if (RECOMENDADORRUTAS_EXCEPTION!= null) {
            throw RECOMENDADORRUTAS_EXCEPTION;
        }
        return RECOMENDADORRUTAS_WSDL_LOCATION;
    }

}
