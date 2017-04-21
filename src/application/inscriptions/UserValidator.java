package application.inscriptions;

public class UserValidator implements Validator{

	private String nom;
	private String prenom;
	private String email;
	
	public UserValidator(String nom, String prenom, String email) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}
	
	public boolean validate() {
		return validateNom() && validatePrenom() && validateEmail();
	}
	
	private boolean validateNom() {
		return nom.matches("/^[a-z\\s]{2,30}$/i");
	}
	
	private boolean validatePrenom() {
		return prenom.matches("/^[a-z\\s]{2,30}$/i");
	}
	
	private boolean validateEmail() {
		return email.equals("/^[a-z0-9-\\._]{2,30}@[a-z0-9]{2,30}\\.[a-z]{2,5}");
	}
	
}


