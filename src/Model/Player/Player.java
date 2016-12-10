package Model.Player;

public class Player {
	protected String name;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return "Player(name:" + this.name + ")";
	}

	public boolean equals(Player player) {
        return this.name.equals(player.name);
    }

	@Override
	public Player clone() {
		return new Player(this.name);
	}
}
