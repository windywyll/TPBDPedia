import org.apache.log4j.PropertyConfigurator;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.sparql.engine.http.QueryExceptionHTTP;
 
 
public class TestJena{
         public static void main( String[] args ) {
        	/* String service = "http://dbpedia.org/sparql";
             String query = "ASK { }";
             QueryExecution qe = QueryExecutionFactory.sparqlService(service, query);
             try {
                 if (qe.execAsk()) {
                     System.out.println(service + " is UP");
                 } // end if
             } catch (QueryExceptionHTTP e) {
                 System.out.println(service + " is DOWN");
             } finally {
                 qe.close();
             } // end try/catch/finally*/
        	 
        	 //http://dbpedia.org/snorql/?query=PREFIX+dbo%3A+%3Chttp%3A%2F%2Fdbpedia.org%2Fontology%2F%3E%0D%0A%0D%0ASELECT+%3Fname+%3Fbirth+%3Fdeath+%3Fperson+WHERE+{%0D%0A+++++%3Fperson+dbo%3AbirthPlace+%3ABerlin+.%0D%0A+++++%3Fperson+dbo%3AbirthDate+%3Fbirth+.%0D%0A+++++%3Fperson+foaf%3Aname+%3Fname+.%0D%0A+++++%3Fperson+dbo%3AdeathDate+%3Fdeath+.%0D%0A+++++FILTER+%28%3Fbirth+%3C+%221900-01-01%22^^xsd%3Adate%29+.%0D%0A}%0D%0AORDER+BY+%3Fname
        	 //http://dbpedia.org/snorql/
        	 //http://dbpedia.org/OnlineAccess#h28-5
        	 PropertyConfigurator.configure("C:/Users/Exphoria/Documents/M1/729-BDAvancees/apache-jena-2.11.0/jena-log4j.properties");
        	 String service = "http://dbpedia.org/sparql";
        	    String query = 
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
        	    		"PREFIX dbo:<http://dbpedia.org/ontology/> "+
        	    		"	SELECT ?name ?birth ?death ?person WHERE {"+
        	    		"	     ?person dbo:birthPlace :Berlin ."+
        	    		"	     ?person dbo:birthDate ?birth ."+
        	    		"        ?person foaf:name ?name ."+
        	    		"	     ?person dbo:deathDate ?death ."+
        	    		"     FILTER (?birth < '1900-01-01'^^xsd:date) ."+
        	    		"	}"+
        	    		" 	ORDER BY ?name";
        	    QueryExecution qe = QueryExecutionFactory.sparqlService(service, query);
        	    try {
        	        ResultSet results = qe.execSelect();

        	        for (; results.hasNext();) {

        	            QuerySolution sol = (QuerySolution) results.next();

        	            System.out.println(sol.get("?name"));

        	        }

        	    }catch(Exception e){

        	        e.printStackTrace();
        	    }
        	    finally {

        	       qe.close();
        	    }
        }
}