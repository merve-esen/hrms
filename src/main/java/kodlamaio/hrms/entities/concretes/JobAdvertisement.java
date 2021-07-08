package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;

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
@Table(name="job_advertisements")
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="job_description")
	private String jobDescription;

	@Column(name="number_of_open_positions")
	private int numberOfOpenPositions;

	@Column(name="minimum_salary")
	private double minimumSalary;

	@Column(name="maximum_salary")
	private double maximumSalary;

	@Column(name="application_deadline")
	private LocalDate applicationDeadline;

	@Column(name="create_date")
	private LocalDate createDate;

	@Column(name="is_active")
	private boolean isActive;

	@ManyToOne()
	@JoinColumn(name="employer_id")
	private Employer employer;

	@ManyToOne()
	@JoinColumn(name="job_position_id")
	private JobPosition jobPosition;

	@ManyToOne()
	@JoinColumn(name="city_id")
	private City city;

	@ManyToOne()
	@JoinColumn(name="work_time_id")
	private WorkTime workTime;

	@ManyToOne()
	@JoinColumn(name="workplace_id")
	private Workplace workplace;

	@Column(name="confirmed")
	private boolean confirmed;

	@Column(name="confirm_date")
	private LocalDate confirmDate;

	@ManyToOne()
	@JoinColumn(name="confirming_employee_id")
	private Employee employee;
}
