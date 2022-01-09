package z9devs.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import z9devs.annotations.ArtistQualifier;
import z9devs.dao.DAO;
import z9devs.entities.Artist;

@WebServlet("/api/artist")
public class ArtistServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	@Inject
	@ArtistQualifier
	private DAO daoArtist;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().append("\n").append(((Artist) daoArtist.getById(1)).getName());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}