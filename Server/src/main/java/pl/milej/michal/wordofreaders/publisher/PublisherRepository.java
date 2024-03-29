package pl.milej.michal.wordofreaders.publisher;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends PagingAndSortingRepository<Publisher, Long> {
}
