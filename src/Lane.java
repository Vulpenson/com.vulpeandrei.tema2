public class Lane extends Cell{
	public Lane(int x, int y) {
		super(x, y);
	}

	@Override
	public String toString() {
		if(x == 4) {
			return "-";
		} else {
			return "|";
		}
	}
}
