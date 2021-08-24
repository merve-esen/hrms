package kodlamaio.hrms.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name="employees")
@PrimaryKeyJoinColumn(name="user_id")
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends User{
	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@JsonIgnore
	@OneToMany(mappedBy = "employee")
	private List<JobAdvertisement> jobAdvertisements;

	@OneToMany(mappedBy = "employee")
	private List<Employer> employers;

	@JsonIgnore
	@OneToMany(mappedBy = "employee")
	private List<EmployerUpdate> employerUpdates;
}
