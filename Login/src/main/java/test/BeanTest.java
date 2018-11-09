package test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BeanTest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Object id;

	public BeanTest() {
	}

	public Object getId() {
		return this.id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public int hashCode() {
		return (this.id == null) ? 0 : this.id.hashCode();
	}

	public boolean equals(Object object) {
		if (object instanceof BeanTest) {
			final BeanTest obj = (BeanTest) object;
			return (this.id != null) ? this.id.equals(obj.id) : (obj.id == null);
		}
		return false;
	}

}
