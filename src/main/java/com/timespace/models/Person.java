package com.timespace.models;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Sheldon Kemper
 */


@SuppressWarnings("serial")
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class Person extends BaseEntity
{

	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
    private String lastName;

	public Person(Long id,String firstName, String lastName)
	{
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	

}