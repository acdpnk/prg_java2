public class GuiSim {
	public static void main(String[] args) {
		boolean debug = false;

		for (String arg : args) {
			if(arg.equals("-v")) debug = true;
		}

		gui.Application app = new gui.Application(debug);
	}
}