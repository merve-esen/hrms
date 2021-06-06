package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "resumes")
@AllArgsConstructor
@NoArgsConstructor
public class Resume {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "photo")
	private String photo;
	
	@Column(name = "github_link")
	private String githubLink;
	
	@Column(name = "linkedin_link")
	private String linkedinLink;
	
	@Column(name = "objective")
	private String objective;
	
	@Column(name="create_date")
	private LocalDate createDate;
	
	@Column(name="update_date")
	private LocalDate updateDate;
	
	@OneToOne()
	@JoinColumn(name = "candidate_id", referencedColumnName = "user_id")
	private Candidate candidate;
	
	@OneToMany(mappedBy = "resume")
	private List<Education> educations;
	
	@OneToMany(mappedBy = "resume")
	private List<LanguageSkill> languageSkills;
	
	@OneToMany(mappedBy = "resume")
	private List<WorkExperience> workExperiences;
	
	@OneToMany(mappedBy = "resume")
	private List<ProgrammingTechnologySkill> programmingTechnologySkills;
}
