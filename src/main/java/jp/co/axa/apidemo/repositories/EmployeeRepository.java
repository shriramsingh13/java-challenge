package jp.co.axa.apidemo.repositories;

import jp.co.axa.apidemo.entities.Employee;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * EMPLOYEE table repository
 *
 * @author shriram.singh
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    /**
     * find all employee data and this field is cacheable
     *
     * @return {@Link List<Employee>} list of employee data
     */
	@Override
    @Cacheable("findall")
    List<Employee> findAll();

    /**
     * find by id the employee data and this field is cacheable
     *
     * @return {@Link Employee} employee data
     */
	@Override
    @Cacheable("findbyid")
    Optional<Employee> findById(Long id);

    /**
     * find the employee data by id or name and this field is cacheable
     *
     * @param id : employee id
     * @param name : employee name
     * @pageable : specific start and end rang for pagination
     * @return {@Link List<Employee>} top 10 ten found employee data
     */
//    @Cacheable("find10byIdOrName")
    List<Employee> findTop10ByIdOrNameContaining(Long id, String name, Pageable pageable);

    /**
     * Save employee data
     *
     * @param entity : employee table entity
     * @return {@Link Employee}
     */
	@Override
    @CacheEvict(value = "save", allEntries = true)
	<E extends Employee> E save(E entity);

    /**
     * Delete employee data by id
     *
     * @param id : employee id
     */
	@Override
    @CacheEvict(value = "delete", allEntries = true)
    void deleteById(Long id);
}
