package ActionImplem;
import java.io.File;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public abstract class Action {
	
	public abstract void Do();
	public Map<String, String> ActionParam;
	
}
