package jp.co.axa.apidemo.repositories;

import jp.co.axa.apidemo.entities.Employee;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
	@Override
    @Cacheable("findall")
    List<Employee> findAll();
	
	@Override
    @Cacheable("findbyid")
    Optional<Employee> findById(Long id);
	
	@Override
    @CacheEvict(value = "save", allEntries = true)
	<E extends Employee> E save(E entity);
	
	@Override
    @CacheEvict(value = "delete", allEntries = true)
    void deleteById(Long id);
}
