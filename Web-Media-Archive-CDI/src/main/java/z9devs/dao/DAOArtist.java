package z9devs.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import z9devs.annotations.ArtistQualifier;
import z9devs.entities.Artist;
import z9devs.utils.BeanInstancesCounter;

@RequestScoped
@ArtistQualifier
public class DAOArtist implements DAO 
{
	// Annotation used to get a session with a persistence unit
	@PersistenceContext(unitName="media_archive_PU")
	EntityManager em;

	// Method called after creation of a DAOArtist instance
	@PostConstruct
	public void init() 
	{
		BeanInstancesCounter.DAOArtistCounter ++;
		System.out.println("CDI management - Created DAOArtist instance number " + BeanInstancesCounter.DAOArtistCounter);
	}
	
	// Method called before destroy of a DAOArtist instance
	@PreDestroy
	public void destroy() 
	{
		BeanInstancesCounter.DAOArtistCounter --;
		System.out.println("CDI management - Destroyed DAOArtist instance. Remains " + BeanInstancesCounter.DAOArtistCounter + " instsances.");
	}
	
	// "Transactional" annotation used to do all the operations inside the 
	// method in a single transaction, so we can call method a.getAlbums().size()
	// inside the same transaction
	@Transactional
	public void create(Object o) 
	{
		System.out.println(((Artist) o).getName());
		em.persist(o);
		System.out.println("Dopo errore");
	}

	// GET ALL Artists
	@Transactional
	public List<Object> getAll() 
	{	
		List<Object> res = em.createNamedQuery("Artist.getAll").getResultList();
		for(Object o : res) 
		{
			System.out.println(((Artist) o).getName());
			((Artist) o).getAlbums().size();
			((Artist) o).prepareJsonSingleArtist();
		}
		return res;
	}
	
	// GET Artist
	@Transactional
	public Object getById(int id) 
	{
		Artist a = em.find(Artist.class, id);
		// Used to get album list, because of FetchType.Lazy
		// need to find a better way to solve this issue
		a.getAlbums().size();
		return a;
	}
	
	// UPDATE Artist
	@Transactional
	public void update(Object o) 
	{
		Artist a = em.find(Artist.class, ((Artist) o).getId());
		a.setName(((Artist) o).getName());
	}

	// DELETE Artist
	@Transactional
	public boolean deleteById(int id) 
	{
		Artist a = em.find(Artist.class, id);
		if(a != null) 
		{
			System.out.println(a != null);
			System.out.println("Dentro");
			em.remove(a);
			return true;
		}
		return false;
	}
}