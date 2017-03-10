
package nl.hu.fnt.maarten.wsInterface;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the nl.hu.fnt.maarten.wsInterface package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PostRequest_QNAME = new QName("http://nl.hu.fnt/dataservice/postRequest", "postRequest");
    private final static QName _DataFault_QNAME = new QName("http://nl.hu.fnt/dataservice/fault", "dataFault");
    private final static QName _Response_QNAME = new QName("http://nl.hu.fnt/dataservice/response", "response");
    private final static QName _GetRequest_QNAME = new QName("http://nl.hu.fnt/dataservice/getRequest", "getRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: nl.hu.fnt.maarten.wsInterface
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link GetRequest }
     * 
     */
    public GetRequest createGetRequest() {
        return new GetRequest();
    }

    /**
     * Create an instance of {@link DataFault }
     * 
     */
    public DataFault createDataFault() {
        return new DataFault();
    }

    /**
     * Create an instance of {@link PostRequest }
     * 
     */
    public PostRequest createPostRequest() {
        return new PostRequest();
    }

    /**
     * Create an instance of {@link Message }
     * 
     */
    public Message createMessage() {
        return new Message();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PostRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nl.hu.fnt/dataservice/postRequest", name = "postRequest")
    public JAXBElement<PostRequest> createPostRequest(PostRequest value) {
        return new JAXBElement<PostRequest>(_PostRequest_QNAME, PostRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nl.hu.fnt/dataservice/fault", name = "dataFault")
    public JAXBElement<DataFault> createDataFault(DataFault value) {
        return new JAXBElement<DataFault>(_DataFault_QNAME, DataFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nl.hu.fnt/dataservice/response", name = "response")
    public JAXBElement<Response> createResponse(Response value) {
        return new JAXBElement<Response>(_Response_QNAME, Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nl.hu.fnt/dataservice/getRequest", name = "getRequest")
    public JAXBElement<GetRequest> createGetRequest(GetRequest value) {
        return new JAXBElement<GetRequest>(_GetRequest_QNAME, GetRequest.class, null, value);
    }

}
