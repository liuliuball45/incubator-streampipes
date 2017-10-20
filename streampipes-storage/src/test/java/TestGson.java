import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;

import org.eclipse.rdf4j.rio.RDFHandlerException;

import com.clarkparsia.empire.annotation.InvalidRdfException;

import org.streampipes.commons.Utils;
import org.streampipes.model.impl.graph.SepaDescription;
import org.streampipes.model.transform.JsonLdTransformer;
import org.streampipes.model.util.GsonSerializer;
import org.streampipes.storage.controller.StorageManager;


public class TestGson {

	public static void main(String[] args) throws URISyntaxException, RDFHandlerException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException, ClassNotFoundException, InvalidRdfException
	{
		SepaDescription desc =new SepaDescription(StorageManager.INSTANCE.getSesameStorage().getSEPAById("http://frosch.fzi.de:8090/sepa/textfilter"));
		System.out.println(Utils.asString(new JsonLdTransformer().toJsonLd(desc)));
		desc.setName("Text Filter 2");
		System.out.println(desc.getRdfId().toString());
		System.out.println(desc.getClass().getCanonicalName());
		String json = GsonSerializer.getGson().toJson(desc);
		System.out.println(json);
		SepaDescription desc2 = GsonSerializer.getGson().fromJson(json, SepaDescription.class);
		System.out.println(desc2.getName());
		System.out.println(desc2.getRdfId().toString());
		//StorageManager.INSTANCE.getSesameStorage().deleteSEP(desc2.getRdfId().toString());
		//StorageManager.INSTANCE.getSesameStorage().storeSEPA(desc2);
	}
}
