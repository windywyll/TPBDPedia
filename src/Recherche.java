import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.PropertyConfigurator;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;


public class Recherche {
	
	private String query;
	
	/**
	 * Instancie la recherche et configure JENA pour communiquer avec DBpedia.
	 * Commence la requête en mettant tous les prefixes utiles. 
	 */
	Recherche(){
		PropertyConfigurator.configure("C:/apache-jena-2.11.0/jena-log4j.properties");
		query = "PREFIX owl: <http://www.w3.org/2002/07/owl#>"+
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
	
	/**
	 * Envoie la requête, récupère les données 
	 * et mets les données en forme dans un tableau pour l'affichage.
	 * @param requete, la requête à exécuter.
	 * @param mode, de quelle fonctions vient la requête pour savoir quoi récupérer.
	 * @return sol, une collection de tableau de String qui contient les résultats de la requête.
	 */
	public ArrayList<String[]> executeQuery(String requete, int mode)
	{
		String service = "http://dbpedia.org/sparql";
		String[] tab;
		ArrayList<String[]> sol = new ArrayList<String[]>();
		QueryExecution qe = QueryExecutionFactory.sparqlService(service, requete);
	    try {
	        ResultSet results = qe.execSelect();
	        if(results != null)
	        {
		        for (; results.hasNext();) {
		        		//if(mode != 0)
		        	tab = new String[5];
		        		QuerySolution q = (QuerySolution) results.next();
		        		if(q.get("artistName")!=null)
		        			tab[0] = q.get("artistName").toString();
		        		if(q.get("thumbnail")!=null)
		        			tab[1] = q.get("thumbnail").toString();
		        		if(q.get("abstract")!=null)
		        			tab[2] = q.get("abstract").toString();
		        		if(q.get("genreName")!=null)
		        			tab[3] = q.get("genreName").toString();
		        		if(q.get("albumName")!=null)
		        			tab[4] = q.get("albumName").toString();
		        		sol.add(tab);
		        		//else
		        			
			            //System.out.println(sol.get("?name"));
			        }
	        }
	    }catch(Exception e){

	        e.printStackTrace();
	    }
	    finally {

	       qe.close();
	    }
		return sol;
	}
	
	/**
	 * Recherche si il existe un artiste qui correspond tout ou en partie au(x) critére(s).
	 * @param artist, la string à rechercher.
	 * @param genre, le genre de l'artiste. Facultif, on peut rechercher juste une partie du genre. ex: hard trouvera Hardcore.
	 * @param offset, combien de résultat la requête doit passer.
	 * @return retourne une arrayList de String qui contient les résultats de la requête.
	 */
	public ArrayList<String[]> rechercheArtiste(String artist, String genre, int offset){
		query = query +
				"PREFIX agent: <http://dbpedia.org/ontology/Agent>"+
				"PREFIX band: <http://dbpedia.org/ontology/Band>"+
				"PREFIX artist: <http://dbpedia.org/ontology/MusicalArtist>"+
				"PREFIX ont: <http://dbpedia.org/ontology/>"+
				
				"SELECT distinct ?artist ?artistName ?artistGenre ?abstract ?thumbnail WHERE {"+
				"?artist rdf:type agent:."+
				"?artist rdfs:subClassOf* ?subclass."+
				"?subclass rdf:type ?subType."+
				"FILTER ((?subType= band:) || (?subType= artist:))."+
				"?artist rdfs:label ?artistName."+
				"FILTER(lang(?artistName) = 'en')."+
				"?artist dbpedia2:genre ?artistGenre.";
				if(genre != null)
				{
					query = query + "FILTER regex(?artistGenre,'(ressource/)?.*"+genre+".*','i').";
				}
				query = query +
						"FILTER (regex(?artist, 'resource/.*"+artist+".*', 'i'))."+
						"?artist ont:abstract ?abstract."+
						"FILTER (lang(?abstract) = 'en')."+
						"OPTIONAL{"+
						"?artist ont:thumbnail ?thumbnail."+
						"}"+
						"}ORDER BY ?artistName "+
						"OFFSET "+offset+
				 		"LIMIT 50";
		
		
		return executeQuery(query, 1);
	}
	
	/**
	 * Recherche si il existe un album qui correspond tout ou en partie au(x) critére(s).
	 * @param album, l'album à rechercher.
	 * @param artist, l'artiste de l'album à rechercher. Facultatif. Peut être partiel.
	 * @param genre, le genre de l'album à rechercher. Facultatif. Peut être partiel.
	 * @param offset, le nombre de résultats à ignorer.
	 * @return retourne une arrayList de String qui contient les résultats de la requête.
	 */
	public ArrayList<String[]> rechercheAlbum(String album, String artist, String genre, int offset){
		
		query = query +
				"PREFIX album: <http://dbpedia.org/ontology/MusicalWork>"+
				"PREFIX ont: <http://dbpedia.org/ontology/>"+ 
				
				"SELECT distinct ?name ?artist ?genre ?abstract ?thumbnail WHERE {"+
				"?album rdf:type album:."+
				"?album rdfs:label ?name."+
				"FILTER (lang(?name) = 'en')."+
				"FILTER regex(?name, '.*"+album+".*', 'i')."+
				"?album dbpedia2:artist ?artist.";
				if(artist != null)
				query = query +
						"FILTER (regex(?artist, 'resource/.*"+artist+".*', 'i')).";
				
				query = query + "?album dbpedia2:genre ?genre.";
				
				if(genre != null)
					query = query + 
							"FILTER (regex(?genre, 'resource/.*"+genre+".*', 'i')). ";
				// Attention URL abstract sous forme <Url/de/l_image>
				query = query + 
						"OPTIONAL{"+
						"?album ont:thumbnail ?thumbnail."+
						"}"+
						"?album ont:abstract ?abstract."+
						"?album ont:releaseDate ?date."+
						"}ORDER BY ?artist "+
						"LIMIT 50";
		 
		return executeQuery(query,2);
	}
	
	/**
	 * Recherche si il existe un genre qui correspond tout ou en partie au critére.
	 * @param genre, le genre à rechercher.
	 * @param offset, le nombre de résultat à ignorer.
	 * @return retourne une arrayList de String qui contient les résultats de la requête.
	 */
	public ArrayList<String[]> rechercheGenre(String genre, int offset){
		query = query +
				"PREFIX genre: <http://dbpedia.org/ontology/MusicGenre> "+
				
			 	"SELECT distinct ?genreName"+
				"WHERE {"+
				"?genre rdf:type genre:."+
				"?genre dbpedia2:name ?genreName."+
				"FILTER regex(str(?genreName), '"+genre+"', 'i')."+
				"}ORDER BY ?genreName "+
				"OFFSET "+offset+
				"LIMIT 50";
	
		return executeQuery(query,3);
	}
	
	/**
	 * Recherche global sur le genre, l'artiste et l'album sans aucune liaison entre eux.
	 * @param objetRecherche, le terme à rechercher comme partiel ou non.
	 * @return retourne une arrayList de String qui contient les résultats de la requête.
	 */
	public ArrayList<String[]> rechercheGlobale(String objetRecherche){
		query = query +
				"PREFIX artist: <http://dbpedia.org/ontology/Band> "+
				"PREFIX album: <http://dbpedia.org/ontology/MusicalWork> "+
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
				"} order by ?genreName "+
				"limit 50";
		
		
		return executeQuery(query,0);
	}
	
}
