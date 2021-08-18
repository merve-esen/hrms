package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="employer_updates")
@AllArgsConstructor
@NoArgsConstructor
public class EmployerUpdate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@ManyToOne()
	@JoinColumn(name="employer_id")
	private Employer employer;

	@Column(name="company_name")
	private String companyName;

	@Column(name="web_site")
	private String webSite;

	@Column(name="phone_number")
	private String phoneNumber;

	@Column(name="create_date")
	private LocalDate createDate;

	@Column(name="deleted")
	private boolean deleted;

	@Column(name="confirmed")
	private boolean confirmed;

	@Column(name="confirm_date")
	private LocalDate confirmDate;

	@ManyToOne()
	@JoinColumn(name="confirming_employee_id")
	private Employee employee;
}
