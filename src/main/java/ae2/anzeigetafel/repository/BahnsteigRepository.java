// BahnsteigRepository.java
package ae2.anzeigetafel.repository;

import ae2.anzeigetafel.entity.Bahnsteig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BahnsteigRepository extends JpaRepository<Bahnsteig, Integer> {
    // Dieses Repository ermöglicht Datenbankzugriffe auf die Tabelle "bahnsteig".
    // Durch JpaRepository bekommst du automatisch Methoden wie:
    // findAll(), findById(), save(), deleteById() usw.
}
