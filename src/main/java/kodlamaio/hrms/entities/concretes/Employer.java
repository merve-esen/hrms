package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name="employers")
@PrimaryKeyJoinColumn(name="user_id")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobAdvertisements"})
public class Employer extends User{

	@Column(name="company_name")
	private String companyName;

	@Column(name="web_site")
	private String webSite;

	@Column(name="phone_number")
	private String phoneNumber;

	@Column(name="confirmed")
	private Boolean confirmed;

	@Column(name="confirm_date")
	private LocalDate confirmDate;

	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name="confirming_employee_id")
	private Employee employee;

	@OneToMany(mappedBy = "employer")
	private List<JobAdvertisement> jobAdvertisements;

	@JsonIgnore
	@OneToMany(mappedBy = "employer")
	private List<EmployerUpdate> employerUpdates;
}
