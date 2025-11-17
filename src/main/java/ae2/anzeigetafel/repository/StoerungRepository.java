// StoerungRepository.java
package ae2.anzeigetafel.repository;

import ae2.anzeigetafel.entity.Stoerung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoerungRepository extends JpaRepository<Stoerung, Integer> {
    // Dieses Repository bietet alle Standardmethoden für die Tabelle "stoerung",
    // z. B. findAll(), findById(), save(), deleteById().
}
