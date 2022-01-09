package z9devs.webservices;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.internal.build.AllowSysOut;

import z9devs.annotations.ArtistQualifier;
import z9devs.dao.DAO;
import z9devs.entities.Artist;

@Path("/artist")
public class ArtistEndpoint 
{
	
	@Inject
	@ArtistQualifier
	private DAO daoArtist;
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getArtist(@PathParam("id") int id) 
	{
		try {
			Artist a = (Artist) daoArtist.getById(id);
			a.prepareJsonSingleArtist();
			return  a;			
		} catch (Exception e) {
			Map<String, String> res = new HashMap<String, String>();
			if(e.getMessage().contains("NullPointerException")) 
			{
				res.put("Error", "No artist with id = " + id);
			}
			else {
				res.put("Error", e.getMessage());
			}
			return Response.status(404).entity(res).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllArtists() 
	{
		return Response.status(200).entity(daoArtist.getAll()).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postArtist(Artist artist) 
	{
		System.out.println(artist.getName() + " - Lettura dati da request");
		try {
			daoArtist.create(artist);
		}
		catch(Exception e) {
			System.out.println("Dentro a catch");
			Map<String, String> res = new HashMap<String, String>();
			res.put("Error", e.getMessage());
			return Response.status(400).entity(res).build();
		}
		return Response.status(201).entity(artist).build();
	}
	
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateArtist(Artist artist) 
	{
		Map<String, String> res = new HashMap<String, String>();
		try {
			daoArtist.update(artist);
		}
		catch(Exception e) {
			if(e.getMessage().contains("NullPointerException")) 
			{
				res.put("Error", "No artist with id = " + artist.getId());
			}
			else {
				res.put("Error", e.getMessage());
			}
			return Response.status(404).entity(res).build();
		}
		res.put("Response", "Updated artist with id = " + artist.getId());
		return Response.status(200).entity(res).build();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteArtist(@PathParam("id") int id)
	{
		Map<String, String> res = new HashMap<String, String>();
		try {
			
			if(daoArtist.deleteById(id))
			{
				res.put("Response", "Deleted artist with id = " + id);
				return Response.status(200).entity(res).build();
			} else {
				System.out.println("Dentro all'else, dovrebbe ritornare qui");
				res.put("Error", "No artist with id = " + id);
				return Response.status(404).entity(res).build();
			}
		} catch(Exception e) {
			System.out.println("Dentro all'else, dovrebbe ritornare li");
			res.put("Error", e.getMessage());
			return Response.status(404).entity(res).build();
		}
		
	}
}