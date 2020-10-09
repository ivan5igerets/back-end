package ivan5.repo;

import ivan5.models.Bulletin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BulletinRepository extends CrudRepository<Bulletin, Long>, PagingAndSortingRepository<Bulletin, Long> {
    Iterable<Bulletin> findAllByOrderByIdDesc();

    Page<Bulletin> findAll(Pageable pageable);
}