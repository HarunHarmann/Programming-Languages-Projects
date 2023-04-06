
public class Movies {
	private String filmName;
	private String path;
	private int duration;
	public Movies() {
		filmName = "";
		path = "";
		duration =0;
	}
	
	public Movies(String filmName, String path, int duration) {
		this.filmName = filmName;
		this.path = path;
		this.duration = duration;
	}
	public String getFilmName() {
		return filmName;
	}
	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
}
