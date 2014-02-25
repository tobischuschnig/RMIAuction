package model;
/**
 * Message which is send when User lists auctions
 * @author Daniel Reichmann <dreichmann@student.tgm.ac.at>
 *
 */
public class ListMessage implements Message{
	private String name;
	
	public ListMessage() {
	}
	
	public ListMessage(String name){
		this.name=name;
	}
	/**
	 * @return the name
	 */
	@Override
	public String getName() {
		return name;//null wurde vorher zurueckgeben -huang
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}