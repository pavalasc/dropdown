package ro.procont.dropdown.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
    private String label;
    private String description;
    
    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name="developer_id", nullable=false)
    @NotNull
    private Developer developer;

    public Developer getDeveloper() {
		return developer;
	}

	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}

	public Skill() {
		super();
    }

    public Skill(String label, String description) {
		super();
		this.label = label;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Skill [id=" + id + ", label=" + label + "]";
	}
    
}
