// LinieRepository.java
package ae2.anzeigetafel.repository;

import ae2.anzeigetafel.entity.Linie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinieRepository extends JpaRepository<Linie, Integer> {
}