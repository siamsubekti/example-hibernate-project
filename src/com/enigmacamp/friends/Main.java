package com.enigmacamp.friends;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Session;

import com.enigmacamp.friends.db.entities.Category;
import com.enigmacamp.friends.db.entities.Gender;
import com.enigmacamp.friends.db.entities.Person;
import com.enigmacamp.friends.db.entities.Post;
import com.enigmacamp.friends.db.repositories.CategoryRepository;
import com.enigmacamp.friends.db.repositories.GenderRepository;
import com.enigmacamp.friends.db.repositories.PersonRepository;
import com.enigmacamp.friends.db.repositories.PostRepository;

public class Main {

	
	
	public static void main(String[] args) throws ParseException {
		hibernateSample();	
	}

//	@SuppressWarnings("unchecked")
	private static void hibernateSample() throws ParseException {
		System.out.println("PROJECT: friends-orm-hibernate");
        GenderRepository genderRepo = new GenderRepository();
        PersonRepository personRepo = new PersonRepository();
        PostRepository postRepo = new PostRepository();
        CategoryRepository catRepo = new CategoryRepository();
        

        Person create = personRepo.find(3);
        
        
        List<Gender> genders = genderRepo.findAll(
        	genderRepo.getCriteriaBuilder().equal(genderRepo.getEntity().get("name"), "MALE")
        );
        
        Gender gender = genderRepo.find(1);
        
        List<Person> persons = personRepo.findAll();
        Person person = personRepo.find(1);
       
        List<Post> post = postRepo.findAll();
        Post post2 = postRepo.find(1);
        Post post4 = postRepo.findTitle("Liburan");
        List<Post> post3 = postRepo.findAll(
        		postRepo.getCriteriaBuilder().equal(postRepo.getEntity().get("title"), "Peristiwa")
        		);
        
        List<Post> post5 = postRepo.findByPostDate();
        List<Post> post6 = postRepo.findByPostToday();
        
        
        //join person join gender join post join category :
        
        List<Category> category = catRepo.findAll( 
        		catRepo.getCriteriaBuilder().like(catRepo.getEntity().get("name"), "Berita")
        		);
        
        List<Post> postname = postRepo.findAll( 
        		postRepo.getCriteriaBuilder().equal(postRepo.getEntity().get("person"), 1)
        		);
        
        List<Post> postbyname = postRepo.listPostName("berita", "Roxy");
       
        //gender
        System.out.println("find: ");
        System.out.println(gender);
        System.out.println("findAll: ");
        System.out.println(genders);
        System.out.println();
        
        //person
        System.out.println("find: ");
        System.out.println(person);
        System.out.println("findAll: ");
        System.out.println(persons);
        System.out.println();
        
        //post
        System.out.println("findAll: ");
        System.out.println(post);
        System.out.println();
        System.out.println("find By ID : ");
        System.out.println(post2);
        System.out.println();
        System.out.println("find By Title : ");
        System.out.println(post3);
        System.out.println();
        
        System.out.println("find By Title kedua : ");
        System.out.println(post4);
        System.out.println();
        
        System.out.println("find BY NewPostDate : ");
        for (Post beda:post5)
        System.out.println(beda);
        System.out.println();
        
        System.out.println("fin By Post today : ");
        for (Post ini:post6)
        System.out.println(ini);
        
        System.out.println("\n\n\n");
        
        System.out.println("find By Post in Category : ");
        System.out.println(category);
        System.out.println("\n\n");
        
        System.out.println("Postingan by name : ");
        System.out.println(postname);
        System.out.println("\n\n");
        
        System.out.println("Postingan By Berita and Name : ");
        System.out.println(postbyname);
        System.out.println("\n\n");
        
        //CRUD
        System.out.println("create, update and delete data");
        System.out.println();
        System.out.println("LIST Before : ");
        List<Post> postlist = postRepo.findAll();
        for(Post ini:postlist)
        	System.out.println(ini);
        System.out.println();
        
        //CREATE POST :
//        Person Dewi = personRepo.find(3);
//        Post insert = new Post(11, "IPA", "Alam", Dewi);
//        postRepo.create(insert);
        
        //UPDATE POST :
//        Person Roxy = personRepo.find(1);
//        Post update = new Post(10, "IPA", "Galaxi", Roxy);
//        postRepo.update(update);
        
        //DELETE POST :
//        Post Fisika = postRepo.find(3);
//        postRepo.delete(Fisika);
        
        
        System.out.println("LIST After : ");
        List<Post> postlist2 = postRepo.findAll();
        for(Post ini2:postlist2)
        	System.out.println(ini2);
        
	}
}
