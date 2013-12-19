import org.apache.log4j.PropertyConfigurator;

import com.hp.hpl.jena.query.ResultSet;


public class Recherche {
	
	private String query;
	
	Recherche(){
		PropertyConfigurator.configure("C:/apache-jena-2.11.0/jena-log4j.properties");
		query = 
	    		"PREFIX owl: <http://www.w3.org/2002/07/owl#>"+
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
				"PREFIX foaf: <http://xmlns.com/foaf/0.1/>"+
				"PREFIX dc: <http://purl.org/dc/elements/1.1/>"+
				"PREFIX : <http://dbpedia.org/resource/>"+
				"PREFIX dbpedia2: <http://dbpedia.org/property/>"+
				"PREFIX dbpedia: <http://dbpedia.org/>"+
				"PREFIX skos: <http://www.w3.org/2004/02/skos/core#>"+
	    		"PREFIX dbo:<http://dbpedia.org/ontology/> ";
	}
	
	public ResultSet rechercheArtiste(String artist){
		query = query +
				"PREFIX artist: <http://dbpedia.org/ontology/Band>";
		ResultSet result = null;
		
		/*
		SELECT distinct ?artist ?artistName ?artistGenre WHERE {
		?artist rdf:type <http://dbpedia.org/ontology/Band>.
		?artist rdfs:label ?artistName.
		FILTER(lang(?artistName) = "en").
		?artist dbpedia2:genre ?artistGenre.
		FILTER (regex(?artistGenre, "resource/Hard_rock") || (?artistGenre = "Hard Rock"@en)).
		}ORDER BY ?artistName
		LIMIT 200
		 */
		
		return result;
	}
	
	public ResultSet rechercheAlbum(String album){
		
		query = query +
				"PREFIX album: <http://dbpedia.org/ontology/MusicalWork>";
		ResultSet result = null;
		/*
		SELECT distinct ?album ?name ?artist WHERE {
		?album rdf:type <http://dbpedia.org/ontology/MusicalWork>.
		?album rdfs:label ?name.
		FILTER(lang(?name) = "en").
		?album dbpedia2:artist ?artist.
		?album <http://dbpedia.org/ontology/recordDate> ?released.
		FILTER(?released < "1985-01-01"^^xsd:date).
		}ORDER BY ?artist
		LIMIT 100
		 */
		return result;
	}

	public ResultSet rechercheGenre(String genre){
		query = query +
				"PREFIX genre: <http://dbpedia.org/ontology/MusicGenre>";
	ResultSet result = null;
	
	/*
 	SELECT distinct ?genreName
	WHERE {
	?genre rdf:type <http://dbpedia.org/ontology/MusicGenre>.
	?genre dbpedia2:name ?genreName.
	FILTER (regex(str(?genreName), "Hard") || regex(str(?genreName), "hard")).
	}ORDER BY ?genreName
	LIMIT 200
	 */
	
	return result;
	}
	
	public ResultSet rechercheGlobale(String objetRecherche){
		query = query +
				"PREFIX artist: <http://dbpedia.org/ontology/Band>"+
				"PREFIX album: <http://dbpedia.org/ontology/MusicalWork>"+
				"PREFIX genre: <http://dbpedia.org/ontology/MusicGenre>";
		ResultSet result = null;
		
		return result;
	}
	
}
