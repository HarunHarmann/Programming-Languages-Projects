
public class Users {
	private String name;
	private String clubMember;
	private String admin;
	private String password;
	
	public Users() {
		name = "";
		clubMember ="";
		admin = "";
	}
	
	public Users(String name,String password,String clubMember,String admin) {
		this.name = name;
		this.clubMember = clubMember;
		this.admin = admin;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClubMember() {
		return clubMember;
	}

	public void setClubMember(String clubMember) {
		this.clubMember = clubMember;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
