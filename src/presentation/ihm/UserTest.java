package presentation.ihm;

public class UserTest {
	public String nom;
	public int id;
	public String prenom;
	
	public UserTest(String nom,String prenom, int id)
	{
		this.nom = nom;
		this.prenom = prenom;
		this.id = id;
	}
	
	public String toString()
	{
		return this.nom+" "+this.prenom;
	}
}
