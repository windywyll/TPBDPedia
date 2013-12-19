import java.util.ArrayList;

import org.apache.log4j.PropertyConfigurator;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
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
	
	public ArrayList<String> executeQuery(String requete, int mode)
	{
		String getter = "";
		switch(mode)
		{
			case 0: 
				break;
			case 1: getter = "artist";
				break;
			case 2: getter = "album";
				break;
			case 3: getter = "genre";
				break;
			
		}
		String service = "http://dbpedia.org/sparql";
		ArrayList<String> sol = new ArrayList<String>();
		QueryExecution qe = QueryExecutionFactory.sparqlService(service, requete);
	    try {
	        ResultSet results = qe.execSelect();

	        if(results.getRowNumber() != 0)
	        {
	        	for (; results.hasNext();) {
	
		            sol.add(results.next().getLiteral(getter).toString());
	
		            //System.out.println(sol.get("?name"));
		        }
	        }
	        else
	        {
	        	sol.add("Pas de r駸ultat");
	        }

	    }catch(Exception e){

	        e.printStackTrace();
	    }
	    finally {

	       qe.close();
	    }
		return sol;
	}
	
	public ArrayList<String> rechercheArtiste(String artist){
		query = query +
				"PREFIX agent: <http://dbpedia.org/ontology/Agent>"+
				"PREFIX band: <http://dbpedia.org/ontology/Band>"+
				"PREFIX artist: <http://dbpedia.org/ontology/MusicalArtist>"+
				
				"SELECT distinct ?artist ?artistName ?artistGenre WHERE {"+
				"?artist rdf:type agent:."+
				"?artist rdfs:subClassOf* ?subclass."+
				"?subclass rdf:type ?subType."+
				"FILTER ((?subType= band:) || (?subType= artist:))."+
				"?artist rdfs:label ?artistName."+
				"FILTER(lang(?artistName) = 'en')."+
				"?artist dbpedia2:genre ?artistGenre."+
				"FILTER (regex(?artist, 'resource/.*"+artist+".*', 'i'))."+
				"}ORDER BY ?artistName "+
				"OFFSET 500 " +
		 		"LIMIT 100";
		 
		
		
		return executeQuery(query, 1);
	}
	
	public ArrayList<String> rechercheAlbum(String album, String artist, String genre){
		
		query = query +
				"PREFIX album: <http://dbpedia.org/ontology/MusicalWork>"+
				"PREFIX ont: <http://dbpedia.org/ontology/>"+
				
				"SELECT distinct ?album ?name ?artist ?genre ?date WHERE {"+
				"?album rdf:type album:."+
				"?album rdfs:label ?name."+
				"FILTER (lang(?name) = 'en')."+
				"FILTER regex(?name, '.*"+album+".*', 'i')."+
				"?album dbpedia2:artist ?artist.";
		
		if(artist != null)
			query = query +
					"FILTER (regex(?artist, 'resource/.*"+artist+".*', 'i')).";
				
		query = query +
				"?album dbpedia2:genre ?genre.";
		
		if(genre != null)
			query = query +
					"FILTER (regex(?genre, 'resource/.*"+genre+".*', 'i')).";
		
		query = query +
				"?album ont:releaseDate ?date."+
				"}ORDER BY ?artist "+
				"LIMIT 200";
		 
		return executeQuery(query,2);
	}

	public ArrayList<String> rechercheGenre(String genre){
		query = query +
				"PREFIX genre: <http://dbpedia.org/ontology/MusicGenre> "+
				
			 	"SELECT distinct ?genreName"+
				"WHERE {"+
				"?genre rdf:type genre:."+
				"?genre dbpedia2:name ?genreName."+
				"FILTER regex(str(?genreName), "+genre+", 'i')."+
				"}ORDER BY ?genreName "+
				"LIMIT 100";
	
		return executeQuery(query,3);
	}
	
	public ArrayList<String> rechercheGlobale(String objetRecherche){
		query = query +
				"PREFIX artist: <http://dbpedia.org/ontology/Band>"+
				"PREFIX album: <http://dbpedia.org/ontology/MusicalWork>"+
				"PREFIX genre: <http://dbpedia.org/ontology/MusicGenre> "+
				
				"SELECT ?albumName ?genreName ?artistName WHERE {"+
				"{?genre rdf:type <http://dbpedia.org/ontology/MusicGenre>. "+
				" ?genre rdfs:label ?genreName. "+
				"FILTER (lang(?genreName) = 'en')."+
				"FILTER regex(str(?genreName), '"+objetRecherche+"','i').} UNION "+
				
				"{?artist rdf:type <http://dbpedia.org/ontology/Band>. "+
				"?artist rdfs:label ?artistName. "+
				"FILTER (lang(?artistName) = 'en')."+
				"FILTER regex(str(?artist), '"+objetRecherche+"','i').} UNION "+
				
				"{?album rdf:type <http://dbpedia.org/ontology/MusicalWork>. "+
				"?album rdfs:label ?albumName. "+
				"FILTER (lang(?albumName) = 'en')."+
				"FILTER regex(str(?album), '"+objetRecherche+"','i').}"+
				"} "+
				"limit 500";
		
		return executeQuery(query,0);
	}
	
}
