package pt.uminho.sysbio.biosynthframework.sbml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uminho.sysbio.biosynthframework.MultiNodeTree;
import pt.uminho.sysbio.biosynthframework.Operator;
import pt.uminho.sysbio.biosynthframework.util.CollectionUtils;

/**
 * Java XML reader for SBML models
 * @author Filipe Liu
 *
 */
public class XmlStreamSbmlReader {

  private static final Logger logger = LoggerFactory.getLogger(XmlStreamSbmlReader.class);
  
  private static final String RDF_RDF = "RDF";
  private static final String RDF_DESCRIPTION = "Description";
  private static final String RDF_Bag = "Bag";
  
  private static final String BQBIOL_U_QUALIFIER = "unknownQualifier";
  private static final String BQBIOL_IS = "is";
  private static final String BQBIOL_IS_ENCODED_BY   = "isEncodedBy";
  private static final String BQBIOL_IS_DESCRIBED_BY = "isDescribedBy";
  private static final String BQBIOL_IS_VERSION_OF   = "isVersionOf";
  private static final String BQBIOL_HAS_PART        = "hasPart";
  
  
  
  private static final String KEY_VALUE_DATA_LIST = "listOfKeyValueData";
  private static final String KEY_VALUE_DATA_ITEM = "data";
  
  private static final String FLUXNS_LIMIT = "limit";
  
  private static final String DC_RELATION        = "relation";


  private final static String RDF_LIST_ITEM = "li";

  private final static String SBML = "sbml";
  private final static String SBML_MODEL = "model";

  private final static String SBML_COMPARTMENT = "compartment";

  private final static String SBML_SPECIE = "species";
  private final static String SBML_LIST_OF_SPECIES = "listOfSpecies";
  private final static String SBML_LIST_OF_COMPARTMENTS = "listOfCompartments";
  private final static String SBML_LIST_OF_REACTIONS = "listOfReactions";

  private final static String SBML_GROUP = "group";
  private final static String SBML_GROUP_MEMBER = "member";
  private final static String SBML_GROUP_LIST_OF_MEMBER = "listOfMembers";

  private final static String SBML_LIST_OF_FLUX_BOUNDS = "listOfFluxBounds";
  private final static String SBML_FLUX_BOUND = "fluxBound";

  private final static String SBML_ANNOTATION = "annotation";
  
  private final static String SBML_NOTES = "notes";
  private final static String SBML_LIST_OF_PARAMETERS = "listOfParameters";
  private final static String SBML_LIST_OF_UNIT_DEFINITIONS = "listOfUnitDefinitions";
  
  private final static String SBML_UNIT_DEFINITION = "unitDefinition";
  private final static String SBML_LIST_OF_UNITS = "listOfUnits";
  private final static String SBML_LIST_OF_UNITS_UNIT = "unit";
  private final static String SBML_REACTION = "reaction";
  private final static String SBML_REACTION_LIST_OF_REACTANTS = "listOfReactants";
  private final static String SBML_REACTION_LIST_OF_PRODUCTS = "listOfProducts";
  private final static String SBML_REACTION_SPECIES_REFERENCE = "speciesReference";
  private final static String SBML_REACTION_KINETIC_LAW = "kineticLaw";
  
  private final static String SBML_PARAMETER = "parameter";
  private final static String SBML_KINETIC_LAW_MATH = "math";
  
  private final static String SBML_NOTES_BODY = "body";

  private String data = null;
  
  public Map<String, Integer> rejectedElements = new HashMap<> ();

  public XmlStreamSbmlReader(String path) throws IOException {
//    data = IOUtils.readFromFile(new File(path));
    
    logger.debug("Loaded {} bytes", 0);
  }

  public XmlStreamSbmlReader(InputStream inputStream) throws IOException {
//    data = IOUtils.readFromInputStream(inputStream);
    
    logger.info("reading... ");
//    IOUtils.readFully(input, buffer);
    
    byte[] buffer = new byte[1024 * 1024];
    int bread = 0;
    data = "";
    while ((bread = inputStream.read(buffer)) > 0) {
      data += new String(buffer, 0, bread);
      buffer = new byte[1024 * 1024];
//      System.out.println(s);
    }
//    List<String> lines = IOUtils.readLines(inputStream);
//    data = "";
//    for (String l : lines) {
//      data += l + "\n";
//    }
    
    
//    IOUtils.closeQuietly(inputStream);
//    data = "";
//    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
//    String line = null;
//    while ((line = br.readLine()) != null) {
//      data += line;
//      System.out.println(line);
//    }
//    br.close();
//    inputStream.close();
    
    logger.info("... done");
//    for (String s : IOUtils.readLines(inputStream)) {
//      data += s;
//    }
    logger.info("Loaded {} bytes", data.getBytes().length);
  }

  public XmlSbmlModel parse() throws IOException {
//    DefaultMetabolicModelEntity mmd = new DefaultMetabolicModelEntity();
//    OptfluxContainerReactionEntity reactionEntity = null;

    //		Map<String, DefaultMetaboliteSpecie> specieMap = new HashMap<> ();
    //		Map<String, DefaultMetaboliteSpecie> reactionMap = new HashMap<> ();
    //		Map<String, DefaultMetaboliteSpecie> metaboliteMap = new HashMap<> ();

    List<XmlSbmlCompartment> compartments = new ArrayList<> ();
    List<XmlSbmlGroup>       groups       = new ArrayList<> ();
    List<XmlSbmlSpecie>      species      = new ArrayList<> ();
    List<XmlSbmlReaction>    reactions    = new ArrayList<> ();
    List<XmlObject>          fluxBounds = new ArrayList<> ();
    List<XmlObject> listOfParameters = new ArrayList<> ();
    Map<String, String> sbmlAttributes = new HashMap<> ();
    XmlObject fluxBound = null;
    XmlSbmlModel model = null;
    try {
      XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
      XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(
          new ByteArrayInputStream(data.getBytes()));
      while (xmlEventReader.hasNext()) {
        XMLEvent xmlEvent = xmlEventReader.nextEvent();
        if (xmlEvent.isStartDocument()) {
          //System.out.println("start");
        } else if (xmlEvent.isStartElement()) {
          StartElement startElement = xmlEvent.asStartElement();
          String startElementLocalPart = startElement.getName().getLocalPart();
          String namespace = startElement.getName().getNamespaceURI();
          logger.trace("+ {} {}", namespace, startElementLocalPart);
          switch (startElementLocalPart) {
          case SBML:
            sbmlAttributes = getAttributes(startElement);
            break;
          case SBML_MODEL:
            model = new XmlSbmlModel();
            model.columnNumber = startElement.getLocation().getColumnNumber();
            model.lineNumber = startElement.getLocation().getLineNumber();
            model.setAttributes(getAttributes(startElement));
            model.getAttributes().put("size", Integer.toString(data.length()));
            break;
          case SBML_NOTES:
            if (model != null) {
              model.setNotes(parseNotes(xmlEventReader, startElement));
            }
            break;
          case SBML_LIST_OF_UNIT_DEFINITIONS:
            model.units.addAll(parseSbmlUnits(xmlEventReader, startElement, SBML_LIST_OF_UNIT_DEFINITIONS));
            break;
          case SBML_LIST_OF_COMPARTMENTS: break;
          case SBML_LIST_OF_SPECIES: break;
          case SBML_LIST_OF_REACTIONS: break;

          case SBML_COMPARTMENT:
            XmlSbmlCompartment xmlSbmlCompartment = parseCompartment(xmlEventReader, startElement);
            compartments.add(xmlSbmlCompartment);
            break;
          case SBML_SPECIE: 
            XmlSbmlSpecie xmlSbmlSpecie = parseSpecie(xmlEventReader, startElement);
            species.add(xmlSbmlSpecie);
            break;
          case SBML_REACTION:
            XmlSbmlReaction xmlSbmlReaction = parseReaction(xmlEventReader, startElement);
            reactions.add(xmlSbmlReaction);
            break;
          case SBML_REACTION_SPECIES_REFERENCE:

          case SBML_GROUP:
            XmlSbmlGroup xmlSbmlGroup = parseGroup(xmlEventReader, startElement);
            groups.add(xmlSbmlGroup);
            break;
          case SBML_LIST_OF_FLUX_BOUNDS:
            break;
          case SBML_FLUX_BOUND:
            fluxBound = new XmlObject();
            fluxBound.setAttributes(getAttributes(startElement));
            break;
          case SBML_LIST_OF_PARAMETERS:
            List<XmlObject> xmlObjects = parseListOfParameters(xmlEventReader);
            listOfParameters.addAll(xmlObjects);
            break;
          default: 
            logger.trace("+?+ " + startElement.getName().getLocalPart());
            CollectionUtils.increaseCount(rejectedElements, startElement.getName().getLocalPart(), 1);
            break;
          }
        } else if (xmlEvent.isEndElement()) {
          EndElement endElement = xmlEvent.asEndElement();
          switch (endElement.getName().getLocalPart()) {
          //						case GRAPHICS: kgmlEntry.setKgmlGraphics(kgmlGraphics); break;
          //						case RELATION: kgmlRelationList.add(kgmlRelation); break;
          //						case RELATION_SUBTYPE: kgmlRelation.setSubtype(kgmlRelationSubtype); break;
          case SBML_MODEL:
            model.setCompartments(compartments);
            model.setSpecies(species);
            model.setGroups(groups);
            model.setReactions(reactions);
            model.setFluxBounds(fluxBounds);
            model.setSbmlAttributes(sbmlAttributes);
            model.setListOfParameters(listOfParameters);
            break;
          case SBML_LIST_OF_FLUX_BOUNDS:
            break;
          case SBML_FLUX_BOUND:
            fluxBounds.add(fluxBound);
            break;
          default:
            logger.trace("-?- " + endElement.getName().getLocalPart());
            break;
          }
        } else if (xmlEvent.isEndDocument()) {

        }
      }
    } catch (XMLStreamException e) {
      throw new IOException(e);
    }

    //		System.out.println(species.size());
    //		System.out.println(groups.size());
    //		System.out.println(fluxBounds.size());
    //		model.getAttributes().remove("id");
    //		model.getAttributes().put("entry", "ymn6");
    //		System.out.println(model.getAttributes());
    //		for (XmlSbmlGroup group : groups) {
    //			
    //			String cpdEntry = group.getAttributes().get("id");
    //			String name = group.getAttributes().get("name");
    //			String kind = group.getAttributes().get("kind");
    //			for (XmlObject o : group.getListOfMembers()) {
    //				String spiEntry = o.getAttributes().get("idRef");
    //				String record = String.format("%s,%s,%s,%s", spiEntry, cpdEntry, name, kind);
    //				System.out.println(record);
    //			}
    //		}

    return model;
  }
  
  public XmlSbmlCompartment parseCompartment(XMLEventReader xmlEventReader, 
      StartElement compartmentStartElement) throws XMLStreamException {
    logger.debug("+++ <compartment> reading compartment");
    boolean read = true;
    XmlSbmlCompartment xmlSbmlCompartment = new XmlSbmlCompartment();
    xmlSbmlCompartment.lineNumber = compartmentStartElement.getLocation().getLineNumber();
    xmlSbmlCompartment.columnNumber = compartmentStartElement.getLocation().getColumnNumber();
    xmlSbmlCompartment.setAttributes(getAttributes(compartmentStartElement));
    
//    XmlObject rdfListItem = null;
//    String bqbiolOntology = null;
    while (xmlEventReader.hasNext() && read) {
      XMLEvent xmlEvent = xmlEventReader.nextEvent();
      if (xmlEvent.isStartElement()) {
        StartElement startElement = xmlEvent.asStartElement();
        String startElementLocalPart = startElement.getName().getLocalPart();
        logger.debug(" ++ <{}> reading metabolite specie", startElementLocalPart);
        //              String namespace = startElement.getName().getNamespaceURI();
        switch (startElementLocalPart) {
        case SBML_COMPARTMENT: {
          //                        specieObject = new XMLObject();  
          //                        specieObject.attributes.putAll(getAttributes(startElement));
        } break;
        default: 
          //                        LOGGER.trace("+?+ " + startElement.getName().getLocalPart());
          CollectionUtils.increaseCount(rejectedElements, startElement.getName().getLocalPart(), 1);
          break;
        }
      } else if (xmlEvent.isEndElement()) {
        EndElement endElement = xmlEvent.asEndElement();
        switch (endElement.getName().getLocalPart()) {
        case SBML_COMPARTMENT:
          read = false;
          break;
        default:
          logger.trace("-?- " + endElement.getName().getLocalPart());
          break;
        }
      } else if (xmlEvent.isEndDocument()) {

      }
    }
    logger.debug("--- reading metabolite specie");
    return xmlSbmlCompartment;
  }

  public XmlSbmlSpecie parseSpecie(XMLEventReader xmlEventReader, StartElement specieStartElement) throws XMLStreamException {
    logger.debug("+++ <species> reading metabolite specie");
    boolean read = true;
    XmlSbmlSpecie xmlSbmlSpecie = new XmlSbmlSpecie();
    setupObject(xmlSbmlSpecie, specieStartElement);
//    xmlSbmlSpecie.lineNumber = specieStartElement.getLocation().getLineNumber();
//    xmlSbmlSpecie.columnNumber = specieStartElement.getLocation().getColumnNumber();
//    xmlSbmlSpecie.setAttributes(getAttributes(specieStartElement));
//    XmlObject xmlObject = new XmlObject();
    XmlObject rdfListItem = null;
    String bqbiolOntology = null;
    while (xmlEventReader.hasNext() && read) {
      XMLEvent xmlEvent = xmlEventReader.nextEvent();
      if (xmlEvent.isStartElement()) {
        StartElement startElement = xmlEvent.asStartElement();
        String startElementLocalPart = startElement.getName().getLocalPart();
        logger.debug(" ++ <{}> reading metabolite specie", startElementLocalPart);
        //				String namespace = startElement.getName().getNamespaceURI();
        switch (startElementLocalPart) {
        case SBML_NOTES: {
          List<String> notes = parseNotes(xmlEventReader, startElement);
          xmlSbmlSpecie.setNotes(notes);
          break;
        }
        case SBML_ANNOTATION:
          Map<String, List<XmlObject>> annotation = parseAnnotation(xmlEventReader);
          xmlSbmlSpecie.setListOfAnnotations(annotation);
          break;
        case DC_RELATION:
        case BQBIOL_IS_VERSION_OF:
        case BQBIOL_HAS_PART:
        case BQBIOL_IS_DESCRIBED_BY:
        case BQBIOL_IS_ENCODED_BY:
        case BQBIOL_IS:
        case BQBIOL_U_QUALIFIER:
          bqbiolOntology = startElementLocalPart;
          if (!xmlSbmlSpecie.getListOfAnnotations().containsKey(bqbiolOntology))
            xmlSbmlSpecie.getListOfAnnotations().put(
                bqbiolOntology, new ArrayList<XmlObject> ());
          break;
        case RDF_LIST_ITEM:
          rdfListItem = assembleObject(startElement);
          rdfListItem.getAttributes().putAll(getAttributes(startElement));
          break;
        case SBML_SPECIE: {
          //						specieObject = new XMLObject();  
          //						specieObject.attributes.putAll(getAttributes(startElement));
        } break;
        default: 
          logger.trace("+?+ " + startElement.getName().getLocalPart());
          CollectionUtils.increaseCount(rejectedElements, startElement.getName().getLocalPart(), 1);
          break;
        }
      } else if (xmlEvent.isEndElement()) {
        EndElement endElement = xmlEvent.asEndElement();
        switch (endElement.getName().getLocalPart()) {
        case BQBIOL_IS:
        case BQBIOL_IS_ENCODED_BY:
        case BQBIOL_IS_DESCRIBED_BY:
        case BQBIOL_HAS_PART:
        case DC_RELATION:
          bqbiolOntology = null;
          break;
        case RDF_LIST_ITEM:
//          System.out.println(bqbiolOntology);
//          System.out.println(rdfListItem.getAttributes());
          //maybe this test should not be here !
          if (!xmlSbmlSpecie.getListOfAnnotations()
              .containsKey(bqbiolOntology)) {
            xmlSbmlSpecie.getListOfAnnotations().put(
                "error", new ArrayList<XmlObject> ());
          }
          //          System.out.println(xmlSbmlSpecie.getAttributes());
          //          System.out.println(bqbiolOntology);
//          System.out.println(bqbiolOntology);
//          System.out.println(rdfListItem);
          xmlSbmlSpecie.getListOfAnnotations()
                       .get(bqbiolOntology)
                       .add(rdfListItem);
          break;
        case SBML_SPECIE:
          read = false;
          break;
        default:
          //						LOGGER.trace("-?- " + endElement.getName().getLocalPart());
          break;
        }
      } else if (xmlEvent.isEndDocument()) {

      }
    }
    logger.debug("--- reading metabolite specie");
    return xmlSbmlSpecie;
  }
  
  public static void initMapList(Map<String, List<XmlObject>> map, String key) {
    if (!map.containsKey(key)) {
      map.put(key, new ArrayList<XmlObject> ());
    }
  }
  
  public List<XmlObject> parseKeyValueDataList(XMLEventReader xmlEventReader) throws XMLStreamException {
    List<XmlObject> kvd = new ArrayList<> ();
    boolean read = true;
    while (xmlEventReader.hasNext() && read) {
      XMLEvent xmlEvent = xmlEventReader.nextEvent();
      if (xmlEvent.isStartElement()) {
        StartElement startElement = xmlEvent.asStartElement();
        switch (startElement.getName().getLocalPart()) {
          case KEY_VALUE_DATA_ITEM:
            XmlObject xo = new XmlObject();
            xo.setAttributes(getAttributes(startElement));
            kvd.add(xo);
            break;
          default:
            logger.warn("ignored +++ {}", startElement.getName().getLocalPart());
            CollectionUtils.increaseCount(rejectedElements, startElement.getName().getLocalPart(), 1);
            break;
        }
      } else if (xmlEvent.isEndElement()) {
        EndElement endElement = xmlEvent.asEndElement();
        switch (endElement.getName().getLocalPart()) {
          case KEY_VALUE_DATA_ITEM: break;
          case KEY_VALUE_DATA_LIST:
            read = false;
            break;
          default:
            logger.warn("ignored --- {}", endElement.getName().getLocalPart());
            break;
        }
      }
    }
    return kvd;
  }
  
  public List<XmlObject> parseListOfParameters(XMLEventReader xmlEventReader) throws XMLStreamException {
    List<XmlObject> listOfParameters = new ArrayList<> ();
    logger.debug("+++ reading listOfParameters");
    boolean read = true;
    while (xmlEventReader.hasNext() && read) {
      XMLEvent xmlEvent = xmlEventReader.nextEvent();
      if (xmlEvent.isStartElement()) {
        StartElement startElement = xmlEvent.asStartElement();
        switch (startElement.getName().getLocalPart()) {
          case SBML_PARAMETER:
            XmlObject parameter = new XmlObject();
            parameter.lineNumber = startElement.getLocation().getLineNumber();
            parameter.columnNumber = startElement.getLocation().getColumnNumber();
            parameter.setAttributes(getAttributes(startElement));
            listOfParameters.add(parameter);
            break;
          default:
            logger.warn("ignored +++ {}", startElement.getName().getLocalPart());
            CollectionUtils.increaseCount(rejectedElements, startElement.getName().getLocalPart(), 1);
            break;
        }
      } else if (xmlEvent.isEndElement()) {
        EndElement endElement = xmlEvent.asEndElement();
        switch (endElement.getName().getLocalPart()) {
          case SBML_PARAMETER: break;
          case SBML_LIST_OF_PARAMETERS: read = false; break;
          default:
            logger.warn("ignored --- {}", endElement.getName().getLocalPart());
            break;
        }
      }
    }
    
    return listOfParameters;
  }
  
  public Map<String, List<XmlObject>> parseAnnotation(XMLEventReader xmlEventReader) throws XMLStreamException {
    logger.debug("+++ reading annotation");
    Map<String, List<XmlObject>> annotation = new HashMap<> ();
    boolean read = true;
    String bqbiolOntology = null;
//    XmlObject rdfListItem_ = null;
    while (xmlEventReader.hasNext() && read) {
      XMLEvent xmlEvent = xmlEventReader.nextEvent();
      if (xmlEvent.isStartElement()) {
        StartElement startElement = xmlEvent.asStartElement();
//        String namespace = startElement.getName().getNamespaceURI();
        switch (startElement.getName().getLocalPart()) {
          case KEY_VALUE_DATA_LIST:
            List<XmlObject> kvdArray = parseKeyValueDataList(xmlEventReader);
            if (!annotation.containsKey(KEY_VALUE_DATA_LIST)) {
              annotation.put(KEY_VALUE_DATA_LIST, new ArrayList<XmlObject> ());
            }
            annotation.get(KEY_VALUE_DATA_LIST).addAll(kvdArray);
            break;
          case RDF_RDF: break;
          case RDF_DESCRIPTION: break;
          case RDF_Bag: break;
          case FLUXNS_LIMIT:
            bqbiolOntology = "fluxnsLimit";
            initMapList(annotation, bqbiolOntology);
            XmlObject fluxNsLimit = buildSimpleObject(startElement);
            annotation.get(bqbiolOntology).add(fluxNsLimit);
//            System.out.println(annotation);
//            if (startElement.getNamespaces().hasNext()) {
//              Object o = startElement.getNamespaceURI("flux");
//              System.out.println("FOUND LIMIT ! " + o);
//            }
//            
//            System.out.println("FOUND LIMIT ! " + startElement.getName().getNamespaceURI());
//            System.out.println(getAttributes(startElement));
            break;
          case BQBIOL_U_QUALIFIER:
          case BQBIOL_IS_ENCODED_BY:
          case BQBIOL_IS_VERSION_OF:
          case BQBIOL_IS:
          case DC_RELATION:
          case BQBIOL_IS_DESCRIBED_BY:
            bqbiolOntology = startElement.getName().getLocalPart();
            initMapList(annotation, bqbiolOntology);
            break;
          case RDF_LIST_ITEM:
            if (bqbiolOntology == null) {
              logger.debug("unknown bqbiolOntology");
            } else {
              annotation.get(bqbiolOntology).add(assembleObject(startElement));
            }
            
//            rdfListItem = new XmlObject();
//            rdfListItem.getAttributes().putAll(getAttributes(startElement));
            break;
          default:
            logger.trace("ignored +++ {}", startElement.getName().getLocalPart());
            CollectionUtils.increaseCount(rejectedElements, startElement.getName().getLocalPart(), 1);
            break;
        }
      } else if (xmlEvent.isEndElement()) {
        EndElement endElement = xmlEvent.asEndElement();
        switch (endElement.getName().getLocalPart()) {
          case RDF_RDF: break;
          case RDF_DESCRIPTION: break;
          case RDF_Bag: break;
          case FLUXNS_LIMIT:
          case BQBIOL_IS_VERSION_OF:
          case BQBIOL_IS:
          case DC_RELATION:
          case BQBIOL_IS_DESCRIBED_BY:
            bqbiolOntology = null;
            break;
          case RDF_LIST_ITEM:
//            annotation.get(bqbiolOntology).add(rdfListItem);
            break;
          case SBML_ANNOTATION: read = false; break;
          default:
            logger.debug("ignored --- {}", endElement.getName().getLocalPart());
            break;
        }
      } else if (xmlEvent.isEndDocument()) {

      }
    }
    logger.debug("--- reading annotation");
    return annotation;
  }
  
  public List<String> parseNotes(XMLEventReader xmlEventReader, StartElement specieStartElement) throws XMLStreamException {
    boolean read = true;
    boolean readBody = false;
    List<String> notes = new ArrayList<> ();
    String note = null;
    while (xmlEventReader.hasNext() && read) {
      XMLEvent xmlEvent = xmlEventReader.nextEvent();
      if (xmlEvent.isStartElement()) {
        StartElement startElement = xmlEvent.asStartElement();

        switch (startElement.getName().getLocalPart()) {
          case "p":
          case SBML_NOTES_BODY: readBody = true; break;
          default:
            CollectionUtils.increaseCount(rejectedElements, startElement.getName().getLocalPart(), 1);
            break;
        }
        if (readBody) {
          note = String.format("<%s>", startElement.getName().getLocalPart());
        }
      }
      if (xmlEvent.isCharacters()) {
        String data = xmlEvent.asCharacters().getData();
        if (readBody) {
          note += data.trim();
        }
      }
      
      if (xmlEvent.isEndElement()) {
        EndElement endElement = xmlEvent.asEndElement();
        if (readBody) {
          note += String.format("</%s>", endElement.getName().getLocalPart());
          notes.add(note);
          note = null;
        }
        switch (endElement.getName().getLocalPart()) {
          case SBML_NOTES: read = false; break;
          case "p":
          case SBML_NOTES_BODY: readBody = false; break;
          default: break;
        }

      }
    }
    return notes;
  }
  
  private XmlObject buildSimpleObject(StartElement startElement) {
    XmlObject xmlObject = new XmlObject();
    xmlObject.lineNumber = startElement.getLocation().getLineNumber();
    xmlObject.columnNumber = startElement.getLocation().getColumnNumber();
    xmlObject.setAttributes(getAttributes(startElement));
    
    return xmlObject;
  }
  
  public List<XmlObject> parseKineticLaw(XMLEventReader xmlEventReader, StartElement specieStartElement) throws XMLStreamException {
    boolean read = true;
    List<XmlObject> parameters = new ArrayList<> ();
    
    while (xmlEventReader.hasNext() && read) {
      XMLEvent xmlEvent = xmlEventReader.nextEvent();
      if (xmlEvent.isStartElement()) {
        StartElement startElement = xmlEvent.asStartElement();

        switch (startElement.getName().getLocalPart()) {
          case SBML_PARAMETER:
            parameters.add(buildSimpleObject(startElement));
            break;
          case SBML_KINETIC_LAW_MATH:
            //for now do nothing !
            break;
          default:
            break;
        }
//        if (readBody) {
//          note = String.format("<%s>", startElement.getName().getLocalPart());
//        }
      }
//      if (xmlEvent.isCharacters()) {
//        String data = xmlEvent.asCharacters().getData();
//        if (readBody) {
//          note += data.trim();
//        }
//      }
      
      if (xmlEvent.isEndElement()) {
        EndElement endElement = xmlEvent.asEndElement();
//        if (readBody) {
//          note += String.format("</%s>", endElement.getName().getLocalPart());
//          notes.add(note);
//          note = null;
//        }
        switch (endElement.getName().getLocalPart()) {
          case SBML_REACTION_KINETIC_LAW: read = false; break;
          default: break;
        }

      }
    }
    return parameters;
  }

  public List<XmlObject> parseSpeciesReference(XMLEventReader xmlEventReader, StartElement specieStartElement) throws XMLStreamException {
    List<XmlObject> list = new ArrayList<> ();
    boolean read = true;
    while (xmlEventReader.hasNext() && read) {
      XMLEvent xmlEvent = xmlEventReader.nextEvent();
      if (xmlEvent.isStartElement()) {
        StartElement startElement = xmlEvent.asStartElement();

        switch (startElement.getName().getLocalPart()) {
          case SBML_REACTION_SPECIES_REFERENCE:
            list.add(buildSimpleObject(startElement));
            break;
          default: break;
        }
      }
      
      if (xmlEvent.isEndElement()) {
        EndElement endElement = xmlEvent.asEndElement();
        switch (endElement.getName().getLocalPart()) {
          case SBML_REACTION_LIST_OF_PRODUCTS: read = false; break;
          case SBML_REACTION_LIST_OF_REACTANTS: read = false; break;
          default: break;
        }
      }
    }
    return list;
  }
  
  public MultiNodeTree<Object> parseFbcGeneProductAssociation(XMLEventReader xmlEventReader, StartElement reactionStartElement) throws XMLStreamException {
    MultiNodeTree<Object> tree = null;
    while (xmlEventReader.hasNext()) {
      XMLEvent xmlEvent = xmlEventReader.nextEvent();
      if (xmlEvent.isStartElement()) {
        StartElement startElement = xmlEvent.asStartElement();
        logger.debug("+++ {}", startElement.getName().getLocalPart());
        switch (startElement.getName().getLocalPart()) {
          case "and":
            MultiNodeTree<Object> andTree = new MultiNodeTree<Object>(Operator.AND);
            if (tree != null) {
              tree.addChild(andTree);
            }
            tree = andTree;
            break;
          case "or":
            MultiNodeTree<Object> orTree = new MultiNodeTree<Object>(Operator.OR);
            if (tree != null) {
              tree.addChild(orTree);
            }
            tree = orTree;
            break;
          case "geneProductRef":
            MultiNodeTree<Object> leaf = 
              new MultiNodeTree<Object>(getAttributes(startElement));
//            System.out.println(getAttributes(startElement));
            if (tree == null) {
              tree = leaf;
            } else {
              tree.addChild(leaf);
            }
            break;
          default:
            logger.debug("??? {}", startElement.getName().getLocalPart());
            CollectionUtils.increaseCount(rejectedElements, startElement.getName().getLocalPart(), 1);
            break;
        }
      } else if (xmlEvent.isEndElement()) {
        EndElement endElement = xmlEvent.asEndElement();
        switch (endElement.getName().getLocalPart()) {
          // and|or go back !
          case "and":
          case "or":
            if (tree.parent != null) {
              tree = tree.parent;
            }
            break;
        }
        boolean end = xmlEvent.isEndElement() && 
            xmlEvent.asEndElement().getName().getLocalPart().equals("geneProductAssociation");

        if (end) {
          logger.debug("end");
          break;
        }
      }
    }
    
    return tree;
  }
  
  public XmlSbmlReaction parseReaction(XMLEventReader xmlEventReader, StartElement reactionStartElement) throws XMLStreamException {
    logger.debug("+++ reading reaction");
    boolean read = true;
//    List<XmlObject> listOfReactants = new ArrayList<> ();
//    List<XmlObject> listOfProducts = new ArrayList<> ();
    XmlSbmlReaction sbmlReaction = new XmlSbmlReaction();
    sbmlReaction.lineNumber = reactionStartElement.getLocation().getLineNumber();
    sbmlReaction.columnNumber = reactionStartElement.getLocation().getColumnNumber();
    sbmlReaction.setAttributes(getAttributes(reactionStartElement));
    
    while (xmlEventReader.hasNext() && read) {
      XMLEvent xmlEvent = xmlEventReader.nextEvent();
      if (xmlEvent.isStartElement()) {
        StartElement startElement = xmlEvent.asStartElement();
        
        String namespace = startElement.getName().getNamespaceURI();
        logger.trace("{}", namespace);
        switch (startElement.getName().getLocalPart()) {
          case SBML_NOTES:
            List<String> notes = parseNotes(xmlEventReader, startElement);
            sbmlReaction.setNotes(notes);
            break;
//          case SBML_REACTION: {
//          //						specieObject = new XMLObject();  
//          //						specieObject.attributes.putAll(getAttributes(startElement));
//          } break;
          case "annotation":
            sbmlReaction.setListOfAnnotations(parseAnnotation(xmlEventReader));
            break;
          case SBML_REACTION_LIST_OF_REACTANTS:
            sbmlReaction.getListOfReactants().addAll(
                parseSpeciesReference(xmlEventReader, startElement));
            break;
          case SBML_REACTION_LIST_OF_PRODUCTS:
            sbmlReaction.getListOfProducts().addAll(
                parseSpeciesReference(xmlEventReader, startElement));
            break;
          case SBML_REACTION_KINETIC_LAW:
            sbmlReaction.getListOfParameters().addAll(parseKineticLaw(xmlEventReader, startElement));
            break;
          case "geneProductAssociation":
            MultiNodeTree<Object> gpr = parseFbcGeneProductAssociation(xmlEventReader, startElement);
            sbmlReaction.setGpr(gpr);
            break;
          default:
            logger.debug("??? {}", startElement.getName().getLocalPart());
            CollectionUtils.increaseCount(rejectedElements, startElement.getName().getLocalPart(), 1);
            break;
        }
      } else if (xmlEvent.isEndElement()) {
        EndElement endElement = xmlEvent.asEndElement();
        switch (endElement.getName().getLocalPart()) {
        case SBML_REACTION:	read = false;	break;
        default: break;
        }
      } else if (xmlEvent.isEndDocument()) {

      }
    }

    logger.debug("--- reading reaction");
    return sbmlReaction;
  }

  
  public XmlObject assembleObject(StartElement startElement) {
    XmlObject object = new XmlObject();
    setupObject(object, startElement);
    
    return object;
  }
  
  public void setupObject(XmlObject xo, StartElement startElement) {
    xo.columnNumber = startElement.getLocation().getColumnNumber();
    xo.lineNumber = startElement.getLocation().getLineNumber();
    xo.setAttributes(getAttributes(startElement));
  }
  
  public List<XmlUnitDefinition> parseSbmlUnits(XMLEventReader xmlEventReader, StartElement groupStartElement, String end) throws XMLStreamException {
    List<XmlUnitDefinition> units = new ArrayList<> ();
    boolean read = true;
    XmlUnitDefinition definition = new XmlUnitDefinition();
    XmlObject unit = null;
    while (xmlEventReader.hasNext() && read) {
      XMLEvent xmlEvent = xmlEventReader.nextEvent();
      if (xmlEvent.isStartElement()) {
        StartElement startElement = xmlEvent.asStartElement();
        switch (startElement.getName().getLocalPart()) {
          case SBML_UNIT_DEFINITION:
            definition = new XmlUnitDefinition();
            setupObject(definition, startElement);
            break;
          case SBML_LIST_OF_UNITS:
            break;
          case SBML_LIST_OF_UNITS_UNIT:
            unit = assembleObject(startElement);
            definition.listOfUnits.add(unit);
            break;
          case SBML_ANNOTATION:
            Map<String, List<XmlObject>> annotation = parseAnnotation(xmlEventReader);
            definition.setListOfAnnotations(annotation);
            break;
          default:
            logger.info("+?+ " + startElement.getName().getLocalPart());
            CollectionUtils.increaseCount(rejectedElements, startElement.getName().getLocalPart(), 1);
            break;
        }
      } else if (xmlEvent.isEndElement()) {
        EndElement endElement = xmlEvent.asEndElement();
        String localPart = endElement.getName().getLocalPart();
        if (localPart.equals(end)) {
          read = false;
        }
        switch (localPart) {
          case SBML_LIST_OF_UNITS:
            units.add(definition);
            break;
          default:
            logger.trace("-?- " + endElement.getName().getLocalPart());
            break;
        }
      }
    }
    
    return units;
  }
  
  public XmlSbmlGroup parseGroup(XMLEventReader xmlEventReader, StartElement groupStartElement) throws XMLStreamException {
    logger.debug("+++ reading group");
    boolean read = true;
    XmlSbmlGroup sbmlGroup = new XmlSbmlGroup();
    sbmlGroup.setAttributes(getAttributes(groupStartElement));
    List<XmlObject> listOfMembers = null;
    XmlObject member = null;

    while (xmlEventReader.hasNext() && read) {
      XMLEvent xmlEvent = xmlEventReader.nextEvent();
      if (xmlEvent.isStartElement()) {
        StartElement startElement = xmlEvent.asStartElement();
        //				String namespace = startElement.getName().getNamespaceURI();
        switch (startElement.getName().getLocalPart()) {
        case SBML_GROUP_LIST_OF_MEMBER: 
          listOfMembers = new ArrayList<> ();
          break;
        case SBML_GROUP_MEMBER:
          member= new XmlObject();
          member.getAttributes().putAll(getAttributes(startElement));
          break;
        default:
          logger.trace("+?+ " + startElement.getName().getLocalPart());
          CollectionUtils.increaseCount(rejectedElements, startElement.getName().getLocalPart(), 1);
          break;
        }
      } else if (xmlEvent.isEndElement()) {
        EndElement endElement = xmlEvent.asEndElement();
        switch (endElement.getName().getLocalPart()) {
        case SBML_GROUP_LIST_OF_MEMBER:
          sbmlGroup.setListOfMembers(listOfMembers);
        case SBML_GROUP_MEMBER:
          listOfMembers.add(member);
          break;						
        case SBML_GROUP:
          read = false;
          break;
        default:
          logger.trace("-?- " + endElement.getName().getLocalPart());
          break;
        }
      } else if (xmlEvent.isEndDocument()) {

      }
    }
    logger.debug("--- reading group");

    return sbmlGroup;
  }

  public static Map<String, String> getAttributes(StartElement startElement) {
    Map<String, String> attributes = new HashMap<> ();
    Iterator<?> i = startElement.getAttributes();
    while (i.hasNext()) {
      Attribute attribute = (Attribute) i.next();
      attributes.put(attribute.getName().getLocalPart(), attribute.getValue());
    }
    return attributes;
  }
}