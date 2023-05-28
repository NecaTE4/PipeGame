//Selin Aydýn 150120061
//Necati Koçak 150120053
// CLASSIN AMACI
package application;

public class Properties {
	public String numberOfCells; // This variable is equal to the number of each cells. Gets the number at the beginning of each line in the input text.

	public String featureOfCells; // This variable is equal to the feature of each cell. For example: Empty, Pipe, PipeStatic, Starter, End.

	public String styleOfCells; // This variable is equal to the style of each cell. For example: Vertical, Horizontal, Free, None, 00, 01, 10, 11.

	public Properties(String numberOfCells, String featureOfCells, String styleOfCells) // We created the constructor of the cells.
	{
		this.numberOfCells = numberOfCells;
		this.featureOfCells = featureOfCells;
		this.styleOfCells = styleOfCells;
	}
}
