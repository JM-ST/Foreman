package th.ac.ku.cs.sci.Foreman.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.ac.ku.cs.sci.Foreman.Model.Site;

import java.util.Collection;

@Repository
public interface SiteRepository extends JpaRepository<Site,Integer> {

    Collection<Site> findAllByUserid(int id);
    Collection<Site> findByStatus(Site.Status status);
    Site findSitesByName(String name);
}
