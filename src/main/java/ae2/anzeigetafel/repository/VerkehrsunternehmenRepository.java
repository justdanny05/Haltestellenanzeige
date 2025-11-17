// VerkehrsunternehmenRepository.java
package ae2.anzeigetafel.repository;

import ae2.anzeigetafel.entity.Verkehrsunternehmen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerkehrsunternehmenRepository extends JpaRepository<Verkehrsunternehmen, Integer> {
    // Bietet Standardmethoden für Verkehrsunternehmen:
    // findAll(), findById(), save(), deleteById() usw.
}
