
public class Seat {
	private String filmName;
	private String hallName;
	private int row;
	private int col;
	private String ownerName;
	private int boughtprice;
	
	public Seat(String filmName, String hallName, int row, int col, String ownerName, int boughtprice) {
		this.filmName = filmName;
		this.hallName = hallName;
		this.row = row;
		this.col = col;
		this.ownerName = ownerName;
		this.boughtprice = boughtprice;
	}

	public String getFilmName() {
		return filmName;
	}

	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	public String getHallName() {
		return hallName;
	}

	public void setHallName(String hallName) {
		this.hallName = hallName;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public int getBoughtprice() {
		return boughtprice;
	}

	public void setBoughtprice(int boughtprice) {
		this.boughtprice = boughtprice;
	}
	
	
}
