package peaksoft.springbootapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.springbootapi.entity.Group;

public interface GroupRepository extends JpaRepository<Group,Long> {
}
