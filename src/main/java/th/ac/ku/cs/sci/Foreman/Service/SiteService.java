package th.ac.ku.cs.sci.Foreman.Service;

import org.springframework.stereotype.Service;
import th.ac.ku.cs.sci.Foreman.Model.Site;
import th.ac.ku.cs.sci.Foreman.Data.SiteRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SiteService {

    private final SiteRepository repository ;

    public SiteService(SiteRepository repository) {
        this.repository = repository ;
    }

    public void createSite(Site site) {
        repository.save(site);
    }

    public void updateSite(Site site) {
        repository.save(site);
    }

    public void deleteSite(int id) {
        repository.deleteById(id);
    }

    public Collection<Site> getAllSite() {
        return repository.findAll();
    }

    public Collection<String> getAllSiteName() {
        List<Site> allSite = repository.findAll();
        Collection<String> siteNames = new ArrayList<>();
        for (int i = 0; i < allSite.size(); i++) {
            siteNames.add(allSite.get(i).getName());
        }
        return siteNames;
    }

    public Site findById(int id) {
        return repository.findById(id).get();
    }

    public Site findByName(String name) {
        return repository.findSitesByName(name);
    }
}