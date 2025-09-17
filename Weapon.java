import java.io.Serializable;
public class Weapon {


  /* Class Variables */
  public class someClass implements Serializable{
    private static final long serialVersionUID = 1L;
  } 

  /* Instance Variables */
  private final String name;
  private final int damage;
  
  /* Constructors */
  public Weapon(String name, int damage) {
    this.name = name;
    this.damage = damage;
  }

  /* Getters & Setters */
  public String getName() {
    return name;
  }

  public int getDamage() {
    return damage;
  }
}
