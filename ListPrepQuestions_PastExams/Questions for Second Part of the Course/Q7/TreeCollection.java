import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nanwang
 *
 The goal of this task it to write a program that loads/stores a list of trees in XML format. `TreeCollection.java` class contains a
 list of `Tree` instances. Each tree has its own `kind`, which needs to be saved as an attribute of XML node. Additionally, each tree
 can have three possible properties: `dimension`, `color` and `types`. `dimension` property has two integer attributes: `diameter` and
 `height`. `types` property has a list of `type` elements. Note that not every tree has all three properties. Some properties of trees
 may be missing (for example, see the test cases in TreesTest.java). You job is to implement the below methods in `TreeCollection.java`:

* `saveToFile`
* `loadFromFile`

Note that these methods should take into account the available properties of a given tree. You are allowed to add additional asserts
and test cases to test your solutions. You can use `getAttribute(String name)` and `setAttribute(String name, String value)` of `Element`
class to get and set the attributes of XML node. **Please upload `TreeCollection.java` file to wattle!**
 *
 */
public class TreeCollection {

	private final List<Tree> trees;

	public TreeCollection(List<Tree> trees) {
		this.trees = trees;
	}

	public List<Tree> getTrees() {
		return trees;
	}

	/**
	 * Implement this method to save the list of trees to XML file
	 * HINT: `setAttribute(String name, String value)` in `Element` can be used to set `kind`, `diameter` and `height` properties
	 * @param file
	 */
	public void saveToFile(File file) {
		//START YOUT CODE
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document d = db.newDocument();
//----------------------------------
			Element rootElement = d.createElement("TreeCollection");
			d.appendChild(rootElement);

			for (Tree tree:trees) {
				Element treeElement = d.createElement("Tree");
				treeElement.setAttribute("kind", tree.getKind());
				//Dimension child for tree element

				if (tree.getDimension() != null) {
					Element dimensionElement = d.createElement("Dimension");
					dimensionElement.setAttribute("diameter", Integer.toString(tree.getDimension().getDiameter()));
					dimensionElement.setAttribute("height", Integer.toString(tree.getDimension().getHeight()));
					treeElement.appendChild(dimensionElement);
				}



				//Color child for tree element

				if (tree.getColor() != null){
					Element colorElement = d.createElement("Color");
					colorElement.appendChild(d.createTextNode(tree.getColor()));
					treeElement.appendChild(colorElement);
				}



				//type child for tree element

				if (tree.getTypes() != null) {
					Element typeElement = d.createElement("TreeType");
					for (String type:tree.getTypes()) {
						//int index = tree.getTypes().indexOf(type);
						Element typeChild = d.createElement("Type");
						typeChild.appendChild(d.createTextNode(type));
						typeElement.appendChild(typeChild);
					}
					treeElement.appendChild(typeElement);
				}



				rootElement.appendChild(treeElement);
			}

			Transformer transformer = TransformerFactory.newInstance().newTransformer();

			transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");

			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			DOMSource source = new DOMSource(d); //Acts as a holder for a transformation Source tree in the form of a Document Object Model (DOM) tree.
			StreamResult result = new StreamResult(file);//Acts as a holder for a transformation result, which may be XML,..
			transformer.transform(source, result); //Transform the XML Source to a Result.

		} catch (Exception e) {
			e.printStackTrace();
		}

		//END YOUT CODE
	}

	/**
	 * Implement this method to load from the XML file to create a `TreeCollection`
	 * HINT: `getAttribute(String name)`in `Element` can be used to get `kind`, `diameter` and `height` properties
	 * @param file
	 * @return
	 */
	public static TreeCollection loadFromFile(File file) {
		//START YOUT CODE
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		List<Tree> tree = new ArrayList<Tree>();

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document d = db.parse(file);
			d.getDocumentElement().normalize();

			NodeList nl = d.getElementsByTagName("Tree");

			for (int i = 0; i < nl.getLength(); i++) {
				Node n = nl.item(i);
				if (n.getNodeType() == Node.ELEMENT_NODE) {
					Element element	= (Element) n;
					String kind = element.getAttribute("kind");

					//Dimension
					Element dimensionElement =  (Element)element.getElementsByTagName("Dimension").item(0);
					Dimension di =  new Dimension();
					if (dimensionElement != null) {
						int diameter = Integer.parseInt(dimensionElement.getAttribute("diameter"));
						int height = Integer.parseInt(dimensionElement.getAttribute("height"));
						di = new Dimension(diameter, height);
					}


					//Color
					String color = null;
					if (element.getElementsByTagName("Color").item(0) != null) {
						color = element.getElementsByTagName("Color").item(0).getTextContent();
					}

					//Type
					List<String> type = null;
					if(element.getElementsByTagName("Type") != null) {
						NodeList typeList =  element.getElementsByTagName("Type");
						type = new ArrayList<String>();
						for (int j = 0; j < typeList.getLength(); j++) {
							Node typeNode = typeList.item(j);
							if (typeNode.getNodeType() == Node.ELEMENT_NODE) {
								type.add(typeNode.getTextContent());
							}
						}
					}


					//Create new tree node
					Tree newTree = new Tree().withKind(kind).withDimension(di).withColor(color);

					for (String t:type
					) {
						newTree.addType(t);
					}
					tree.add(newTree);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		//END YOUT CODE
		return new TreeCollection(tree);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o instanceof TreeCollection) {
			TreeCollection treeCollection = (TreeCollection) o;
			return this.trees.equals(treeCollection.trees);
		}

		return false;
	}
}
