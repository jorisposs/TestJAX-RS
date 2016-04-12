package edu.ap.jaxrs;

import java.io.StringReader;

import redis.clients.jedis.Jedis;

@path(/"quotes")
public class QuoteResource {



    Jedis jedis = new Jedis("localhost");

    jedis.rpush("Winston Churchill", "I may be drunk, Miss, but in the morning I will be sober and you will still be ugly.");
    jedis.rpush("W.C. Fields", "Any man who hates dogs and babies can't be all bad");
    jedis.rpush("Jeffrey Lebowski", "Yeah, that's like your opinion, man");

@GET
@Produces({"text/html"})

String htmlString = "<html><body>";
try {
	 List<String> list = jedis.values("*");
     for(int i=0; i <list.size(); i++) {
       System.out.println("List of quotes:: "+ list.get(i));
     }
     
     htmlString += list;
	
	
}
catch(Exception ex) {
	htmlString = "<html><body>" + ex.getMessage();
}

return htmlString + "</body></html>";



@POST
@Consumes({"text/html"})
public String QuoteRequest(string Author) {
	//for specific author(key) return relating quote(value)
}


}